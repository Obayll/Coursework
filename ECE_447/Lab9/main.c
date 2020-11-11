    #include <msp430.h>
    #include "graphics.h"
    #include "lcd.h"
    #include "color.h"
    #include "ports.h"
    #include "plot.h"
    #include "communication.h"

    unsigned char RXData;
    unsigned char RXCompare;

    const int sensorAddress = 0x77;

    u8 data_1[6];
////////////////////////////////////////////////////////


    const unsigned char lcd_num[10] = {
        0xFC,        // 0 - 0b 1111 1100 *
        0x60,        // 1 - 0b 0110 1111
        0xDB,        // 2 - 0b 1101 1011
        0xF3,        // 3 - 0b 1111 0011 *
        0x67,        // 4 - 0b 0110 0111 *
        0xB7,        // 5 - 0b 1011 0111
        0xBF,        // 6 - 0b 1011 1111
        0xE4,        // 7 - 0b 1110 0100
        0xFF,        // 8 - 0b 1111 1111
        0xF7,        // 9 - 0b 1111 0111
    };

    const unsigned char off = 0x00;
    const unsigned char a   = 0x80;
    const unsigned char b   = 0x40;
    const unsigned char c   = 0x20;
    const unsigned char d   = 0x10;
    const unsigned char e   = 0x08;
    const unsigned char f   = 0x04;
    const unsigned char g1  = 0x02;
    const unsigned char g2  = 0x01;
    const unsigned char top = 0xC7;
    const unsigned char bot = 0x3B;

    int onboard_seg_display_init()
    {
        PJSEL0 = BIT4 | BIT5;                   // For LFXT

        LCDCPCTL0 = 0xFFD0;     // Init. LCD segments 4, 6-15
        LCDCPCTL1 = 0xF83F;     // Init. LCD segments 16-21, 27-31
        LCDCPCTL2 = 0x00F8;     // Init. LCD segments 35-39

        // Disable the GPIO power-on default high-impedance mode
        // to activate previously configured port settings
        PM5CTL0 &= ~LOCKLPM5;

        // Configure LFXT 32kHz crystal
        CSCTL0_H = CSKEY >> 8;                  // Unlock CS registers
        CSCTL4 &= ~LFXTOFF;                     // Enable LFXT
        do
        {
          CSCTL5 &= ~LFXTOFFG;                  // Clear LFXT fault flag
          SFRIFG1 &= ~OFIFG;
        }while (SFRIFG1 & OFIFG);               // Test oscillator fault flag
        CSCTL0_H = 0;                           // Lock CS registers

        // Initialize LCD_C
        // ACLK, Divider = 1, Pre-divider = 16; 4-pin MUX
        LCDCCTL0 = LCDDIV__1 | LCDPRE__16 | LCD4MUX | LCDLP;

        // VLCD generated internally,
        // V2-V4 generated internally, v5 to ground
        // Set VLCD voltage to 2.60v
        // Enable charge pump and select internal reference for it
        LCDCVCTL = VLCD_1 | VLCDREF_0 | LCDCPEN;

        LCDCCPCTL = LCDCPCLKSYNC;               // Clock synchronization enabled

        LCDCMEMCTL = LCDCLRM;                   // Clear LCD memory

        LCDCCTL0 |= LCDON;

        return 0;
    }
    void onboard_seg_display_XXINT(int pos, int n)
    {
        // Input a 2-digit int and select 1 or 3 2-digit positions
        // (XX_XX_XX on display)

        // Does not include any screen clearing.

        int tens = n/10;
        int ones = n%10;

        if(pos == 0){
            LCDM10 = lcd_num[tens];
            LCDM6 = lcd_num[ones];
        }else if(pos == 1){
            LCDM4 = lcd_num[tens];
            LCDM19 = lcd_num[ones];
        }else{
            LCDM15 = lcd_num[tens];
            LCDM8 = lcd_num[ones];
        }
    }

    void onboard_seg_display_int(int n)
    {
        onboard_seg_display_clear();

        // Assume that the number is 6 digits
        int n01 = n/10000;                      // calculates 00####
        int n23 = n/100 - n01*100;              // calculates ##00##
        int n45 = n - (n01*10000 + n23*100);    // calculates ####00

        onboard_seg_display_XXINT(0,n01);
        onboard_seg_display_XXINT(1,n23);
        onboard_seg_display_XXINT(2,n45);
    }

    void disp_float(float num){
        int disp;
        disp = num *100;
        onboard_seg_display_int(disp);
        //display decimal point
        LCDM20 |= 0x1;

    }

    void onboard_seg_display_clear()
    {
        // Initially, clear all displays.
        LCDCMEMCTL |= LCDCLRM;
    }



///////////////////////////////////////////////////////
    void writeData(uint8_t data) {
        P2OUT &=~ LCD_CS_PIN;
        P2OUT |= LCD_DC_PIN;
        UCB0TXBUF = data;
        while(UCB0STAT&UCBUSY);
        P2OUT |= LCD_CS_PIN;
    }

    /*
     *  Needs to write commands to the device using spi
     */

    void writeCommand(uint8_t command) {
        P2OUT &=~ LCD_CS_PIN;
        P2OUT &=~ LCD_DC_PIN;
        UCB0TXBUF = command;
        while(UCB0STAT&UCBUSY);
        P2OUT |= LCD_CS_PIN;
    }

    void msp_init(void){

        /************************** PWM Backlight ******************************/
        WDTCTL = WDTPW | WDTHOLD;       // Stop watchdog timer

        P2DIR   |= BIT4;
        P2SEL0  |= BIT4;
        P2SEL1  &= ~BIT4;
        TB0CCR0  = 511;
        TB0CCTL3 = OUTMOD_7;
        TB0CCR3  = 256;
        TB0CTL   = TBSSEL__ACLK | MC__UP | TBCLR;

        /******************************** SPI **********************************/

        P2DIR  |=   LCD_DC_PIN | LCD_CS_PIN;            // DC and CS
        P1SEL0 |=   LCD_MOSI_PIN | LCD_UCBCLK_PIN;      // MOSI and UCBOCLK
        P1SEL1 &= ~(LCD_MOSI_PIN | LCD_UCBCLK_PIN);

        UCB0CTLW0 |= UCSWRST;       // Reset UCB0
        UCB0CTLW0 |= UCSSEL__SMCLK | UCCKPL | UCMSB | UCMST | UCMODE_0 | UCSYNC;

        UCB0BR0   |= 0x01;         // Clock = SMCLK/60
        UCB0BR1    = 0;
        UCB0CTL1  &= ~UCSWRST;     // Clear UCSWRST to release the eUSCI for operation


        /******************************** I2C **********************************/
        //--UCB1 for I2C
        UCB1CTLW0 |= UCSWRST;       // Reset UCB0

        /*
         * UCBxCTLW0     - eUSCI_Bx Control Register 0
         * UCSSEL__SMCLK - SMCLK in master mode
         * UCCKPL        - Clocl polarity select
         * UCMSB         - MSB first select
         * UCMST         - Master mode select
         * UCMODE_3      - eUSCI mode 3-pin i2c select
         * UCSYNC        -  Synchronous mode enable
         */
         ///-- Configer IO pins for SCL and SDA (hint look in P4SEL0 and P4SEL1 registers in Prog. Reference)
  

        //---Now configure the UCB1 to use SMCLK , Master mode, I2C , sync operation.
        //-- Set slave address
        //--set baud rate to be clock/60
        //--Release reset
        
		
        PM5CTL0   &= ~LOCKLPM5;    // Unlock ports from power manager

    }
    int main(void)

     {
      UCB1IFG = 0;
      msp_init();
      onboard_seg_display_init();
      I2C_routine();
      initLCD();
      u8 cmd[2];
      cmd[0]=(bmp280.oversamp_temperature
              << BMP280_SHIFT_BIT_POSITION_BY_05_BITS)
              + (bmp280.oversamp_pressure
              << BMP280_SHIFT_BIT_POSITION_BY_02_BITS)
              + BMP280_NORMAL_MODE;
      cmd[1]=BMP280_STANDBY_TIME_1_MS;
      BMP280_I2C_bus_write(0x77,0xF4,cmd,2);

      while (1)
      {
          BMP280_I2C_bus_read(0x77,0xF7,data_1,6);
          press=((unsigned long)data_1[0])*4096+((unsigned long)data_1[1])*16;
          temp=((unsigned long)data_1[3])*4096+((unsigned long)data_1[4])*16;
          temp_double = temp_conpensate(temp);
          press_double = press_conpensate(press);
          disp_float(temp_double);
          __delay_cycles(100000);
      }
    }
