#include <stdint.h>
#include <msp430.h>

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

int onboard_seg_display_init() {
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

void onboard_seg_display_XXINT(int pos, int n) {
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

void onboard_seg_display_clear() {
    // Initially, clear all displays.
    LCDCMEMCTL |= LCDCLRM;
}

void onboard_seg_display_int(int n) {
    onboard_seg_display_clear();

    // Assume that the number is 6 digits
    int n01 = n/10000;                      // calculates 00####
    int n23 = n/100 - n01*100;              // calculates ##00##
    int n45 = n - (n01*10000 + n23*100);    // calculates ####00

    onboard_seg_display_XXINT(0,n01);
    onboard_seg_display_XXINT(1,n23);
    onboard_seg_display_XXINT(2,n45);
}

void disp_float(float num) {
    int disp;
    disp = num *100;
    onboard_seg_display_int(disp);
    //display decimal point
    LCDM20 |= 0x1;
}

int main(void) {
    WDTCTL = WDTPW + WDTHOLD;   // Stop WDT
    P1DIR |= BIT0;              // P1.0 set as output
    PM5CTL0 &= ~LOCKLPM5;       // Unlock ports from power manager
    onboard_seg_display_init();
    // Initialize the shared reference module
    // By default, REFMSTR=1 => REFCTL is used to configure the internal reference
    while(REFCTL0 & REFGENBUSY);              // If ref generator busy, WAIT
    REFCTL0 |= REFVSEL_0 + REFON;             // Enable internal 1.2V reference

    /* Initialize TA2 */
    TA2CTL = MC_1 | ID_1 | TASSEL_1 | TACLR;  // up mode, divide ACLK by 8
    //TA2EX0 = TAIDEX_7;                      // and divide again by 8
    TA2CCR0 = 0xF000;                         // 120 seconds
    TA2CCTL1 = OUTMOD_3;                      // set/reset gives one 1/512s impulse
    TA2CCR1 = 0xEFFF;                         // could be any value as this
                                              // value will be reached again in 120 seconds

    /* Initialize ADC12_A */
    ADC12CTL0 &= ~ADC12ENC;                   // Disable ADC12
    ADC12CTL0 = ADC12SHT0_8 | ADC12MSC |ADC12ON;   // Set sample time, Multisamples
    ADC12CTL1 = ADC12SHS_5 | ADC12SHP | ADC12CONSEQ_1;  // Trigger conversions
               // from timer TA2 CCR1, Enable sample timer, Sequence of Channels
    ADC12CTL3 = ADC12TCMAP;                   // Enable internal temperature sensor
    ADC12MCTL0 = ADC12VRSEL_1 | ADC12INCH_30; // ADC input ch A30 => temp sense
    ADC12MCTL1 = ADC12VRSEL_1 | ADC12INCH_30; // ADC input ch A30 => temp sense
    ADC12MCTL2 = ADC12VRSEL_1 | ADC12INCH_30; // ADC input ch A30 => temp sense
    ADC12MCTL3 = ADC12VRSEL_1 | ADC12INCH_30 | ADC12EOS; // ADC input ch A30 => temp sense, end of sequence
    ADC12IER0 = ADC12IE3;                     // ADC_IFG upon conv result-ADCMEM3

    while(!(REFCTL0 & REFGENRDY));            // Wait for reference generator
                                              // to settle
  while(1) {
      ADC12CTL0 &= ~ADC12ENC;   // Toggeling ENC to restart sequence, else have to use
      ADC12CTL0 |= ADC12ENC;    // CONCEQ_3
    //ADC12CTL0 |= ADC12SC;                   // Sampling and conversion start

    __bis_SR_register(LPM0_bits + GIE);     // LPM4 with interrupts enabled
    __no_operation();
  }
}
