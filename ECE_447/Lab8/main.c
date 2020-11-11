#include "lcd.h"
#include "graphics.h"
#include "color.h"
#include "ports.h"

#define CALADC12_12V_30C * ((unsigned int *)0x1A1A)    // Temperature Sensor Calibration-30 C
                                                       //See device datasheet for TLV table memory mapping
#define CALADC12_12V_85C * ((unsigned int *)0x1A1C)    // Temperature Sensor Calibration-85 C

unsigned int tempNum, tempVolt, inputSt, inputEx, val, FLAG = 0;
unsigned int x, y = 1;
float dispVolt = 0;
volatile float tempC, tempF;

//Given code
void initMSP430(void) {
    /************************** PWM Backlight ******************************/

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

    /*
     * UCBxCTLW0     - eUSCI_Bx Control Register 0
     * UCSSEL__SMCLK - SMCLK in master mode
     * UCCKPL        - Clocl polarity select
     * UCMSB         - MSB first select
     * UCMST         - Master mode select
     * UCMODE_0      - eUSCI mode 3-pin SPI select
     * UCSYNC        -  Synchronous mode enable
     */
    UCB0CTLW0 |= UCSSEL__SMCLK | UCCKPL | UCMSB | UCMST | UCMODE_0 | UCSYNC;

    UCB0BR0   |= 0x01;         // Clock = SMCLK/60
    UCB0BR1    = 0;
    UCB0CTL1  &= ~UCSWRST;     // Clear UCSWRST to release the eUSCI for operation
    PM5CTL0   &= ~LOCKLPM5;    // Unlock ports from power manager

    P1DIR  &= ~(BIT2 + BIT5);
    P1REN  |=  (BIT2 + BIT5);
    P1OUT  |=  (BIT2 + BIT5);
    P1IES  |=  (BIT2 + BIT5);
    P1IFG  &= ~(BIT2 + BIT5);
    P1IE   |=  (BIT2 + BIT5);
    P1SEL0 |=   BIT3;
    P1SEL1 |=   BIT3;
    __enable_interrupt();
}

//Kap's code
void intadc(void) {
    while(REFCTL0 & REFGENBUSY);
      REFCTL0 |= REFVSEL_0 + REFON;

      /* Initialize TA2 */
      TA2CTL = MC_1 | ID_1 | TASSEL_1 | TACLR;
      TA2CCR0 = 3277;
      TA2CCTL1 = OUTMOD_3;
      TA2CCR1 = 3276;

      /* Initialize ADC12_A */
      ADC12CTL0 &= ~ADC12ENC;
      ADC12CTL0 = ADC12SHT0_8 | ADC12MSC |ADC12ON;
      ADC12CTL1 = ADC12SHS_5 | ADC12SHP | ADC12CONSEQ_1;

      ADC12CTL3 = ADC12TCMAP;
      ADC12MCTL0 = ADC12VRSEL_1 | ADC12INCH_30;
      ADC12MCTL1 = ADC12VRSEL_1 | ADC12INCH_30;
      ADC12MCTL2 = ADC12VRSEL_1 | ADC12INCH_30;
      ADC12MCTL3 = ADC12VRSEL_1 | ADC12INCH_30;
      ADC12MCTL4 = ADC12VRSEL_0 | ADC12INCH_3;
      ADC12MCTL5 = ADC12VRSEL_0 | ADC12INCH_3;
      ADC12MCTL6 = ADC12VRSEL_0 | ADC12INCH_3;
      ADC12MCTL7 = ADC12VRSEL_0 | ADC12INCH_3 | ADC12EOS;
      ADC12IER0 = ADC12IE7;

      while(!(REFCTL0 & REFGENRDY));
}

//Kap's code
#if defined(__TI_COMPILER_VERSION__) || defined(__IAR_SYSTEMS_ICC__)
#pragma vector=ADC12_VECTOR
__interrupt void ADC12ISR (void)
#elif defined(__GNUC__)
void __attribute__ ((interrupt(ADC12_VECTOR))) ADC12ISR (void)
#else
#error Compiler not supported!
#endif
{
    switch(__even_in_range(ADC12IV, ADC12IV_ADC12RDYIFG)) {
    case ADC12IV_NONE: break;        // Vector  0:  No interrupt
    case ADC12IV_ADC12IFG7:
        tempNum = ADC12MEM0+ADC12MEM1+ADC12MEM2+ADC12MEM3;  // Move results, IFG is cleared
        tempNum = tempNum >> 2;
        tempVolt = ADC12MEM4+ADC12MEM5+ADC12MEM6+ADC12MEM7;  // Move results, IFG is cleared
        tempVolt = tempVolt >> 2;// Simple average computation
        _low_power_mode_off_on_exit();
        break;        // Vector 26:  ADC12MEM7
    default: break;
    }
}

#pragma vector = PORT1_VECTOR
__interrupt void p1_ISR() {
    switch (P1IV) {
    case P1IV_P1IFG2:
        inputSt ^= BIT0;
        break;
    case P1IV_P1IFG5:
        inputEx ^= BIT0;
        break;
    default: break;
    }
}

void writeData(uint8_t data) {
    P2OUT |= LCD_DC_PIN;
    UCB0TXBUF = data;
    while (!(UCB0STATW & UCBUSY));
}

void writeCommand(uint8_t command) {
    P2OUT &= ~LCD_DC_PIN;
    UCB0TXBUF = command;
    while (!(UCB0STATW & UCBUSY));
}

void main(void) {
    WDTCTL = WDTPW | WDTHOLD;
    initMSP430();
    initLCD();
    intadc();

    char disp[9] = "T = 00.00";
    enum states {Te,Vo} curState = Te;
    clearScreen(1);

    while (1) {
        ADC12CTL0 &= ~ADC12ENC;
        ADC12CTL0 |=  ADC12ENC;

        __bis_SR_register(LPM0_bits + GIE);

        if (inputSt == 1) {
            clearScreen(1);
            if (curState == Te) {
                disp[0] = 'V';
                setColor(COLOR_16_WHITE);
                drawRect(0,7,159,127);
            } else {
                disp[0] = 'T';
                x = 1;
            }
            curState ^= BIT0;
            inputSt = 0;
        }

        switch(curState) {
        case Te:
            TA2CCR0 = 32768;
            TA2CCR1 = 32767;

            tempC = (float)(((long)tempNum - CALADC12_12V_30C) * (85 - 30)) / (CALADC12_12V_85C - CALADC12_12V_30C) + 30.0f;
            tempF = tempC * 9.0f / 5.0f + 32.0f;

            if (inputEx == 1) val = (int)(tempC*100);
            else  val = (int)(tempF*100);

            disp[4] = (val / 1000) % 10 + '0';
            disp[5] = (val / 100)  % 10 + '0';
            disp[7] = (val / 10)   % 10 + '0';
            disp[8] = (val / 1)    % 10 + '0';

            setColor(COLOR_16_BLACK);
            fillRect(2,0,159,6);
            setColor(COLOR_16_RED);
            drawString(2,0,FONT_SM,disp);
            if (inputEx == 1) drawCharSm(56,0,'C');
            else drawCharSm(56,0,'F');
            break;
        case Vo:
            TA2CCR0 = 3277;
            TA2CCR1 = 3276;

            dispVolt = tempVolt * (3.3/4096);
            y = 126 - (dispVolt*36);    //120 / 3.3 = 36.36
            setColor(COLOR_16_YELLOW);
            drawPixel(x,y);

            val = (int)(dispVolt*100);
            disp[4] = (val / 1000) % 10 + '0';
            disp[5] = (val / 100)  % 10 + '0';
            disp[7] = (val / 10)   % 10 + '0';
            disp[8] = (val / 1)    % 10 + '0';

            setColor(COLOR_16_BLACK);
            fillRect(2,0,159,6);
            setColor(COLOR_16_YELLOW);
            drawString(2,0,FONT_SM,disp);
            drawCharSm(56,0,'V');
            x++;
            if (x >= 159){
                x = 1;
                setColor(COLOR_16_BLACK);
                fillRect(1,8,158,126);
            }
            break;
        }
    }
}
