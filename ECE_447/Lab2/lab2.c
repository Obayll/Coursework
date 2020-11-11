#include <msp430.h>

int delayTime;

void onboard_seg_display_init(void)
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
}

void main(void) {

    WDTCTL = WDTPW | WDTHOLD;       // Stop watchdog timer

    onboard_seg_display_init();     // Init the LCD

    // init. external buttons
    P2DIR &= ~BIT0;
    P2REN |=  BIT0;
    P2OUT |=  BIT0;
    P2DIR &= ~BIT1;
    P2REN |=  BIT1;
    P2OUT |=  BIT1;
    P2DIR &= ~BIT2;
    P2REN |=  BIT2;
    P2OUT |=  BIT2;
    P2DIR &= ~BIT3;
    P2REN |=  BIT3;
    P2OUT |=  BIT3;
    P1REN |=  BIT1;
    P1OUT |=  BIT1;
    P1DIR |=  BIT1;

    unsigned int delayTime = 50000;
    signed int i = 1;
    signed int j = 0;
    unsigned int dir = 1;
    unsigned swapFLAG = 0;
    while(1) {
        if(!(P1IN & BIT1)){ //change direction
            dir = !dir;
            swapFLAG = 1;
        }
        if(!(P2IN & BIT3)){ //increase forward
            if(dir) {
                if(abs(i-j) < 15) {
                    turnSegOff(j);
                    j--;
                }
            }
            else if(!dir) { //increase backward
                if(abs(i-j) < 15) {
                    turnSegOn(i);
                    j++;
                }
            }
        }
        if(!(P2IN & BIT2)){ //decrease forward
            if(dir) {
                if(abs(i-j) > 1) {
                    turnSegOff(j);
                    i--;
                }
            }
            else if(!dir) { //decrease backward
                if(abs(i-j) > 1)
                    turnSegOff(i);
                    i++;
            }
        }
        if(dir){
            if(swapFLAG){
                i=i+j;
                j=i-j;
                i=i-j;
                swapFLAG = 0;
            }
            turnSegOn(i);
            turnSegOff(j);
            if(i>=15) {
                i = 0;

            } else i++;
            if(j>=15) {
                j = 0;
            } else j++;
        }
        if(!dir){
            if(swapFLAG){
                i=i+j;
                j=i-j;
                i=i-j;
                swapFLAG = 0;
            }
            turnSegOff(j);
            turnSegOn(i);
            if(j<=0) {
                j = 15;
            } else j--;
            if(i<=0) {
                i = 15;
            } else i--;
        }

        if(!(P2IN & BIT0)) {
            if (delayTime >= 60000) delayTime += 0;
            else {
                delayTime += 5000;
            }
        }
        if(!(P2IN & BIT1)) {
            if (delayTime <= 10000) delayTime -= 0;
            else {
                delayTime -= 5000;
            }
        }
        delay(delayTime);
    }
/*
    while(1)                        // loop continuously
    {
        if(!(P2IN & BIT0))          // poll the input
            LCDM10 |= 0x80;          // toggle segment A1A on
        else
            LCDM10 |= 0x00;          // toggle segment A1A off
    }
*/
}
/*
void changeDelay(void) {
    if(!(P2IN & BIT0)) {
        if (delayTime >= 100000) return;
        else {
            delayTime += 10000;
            return;
        }
    }
    else if(!(P2IN & BIT1)) {
        if (delayTime <= 10000) return;
        else {
            delayTime -= 10000;
            return;
        }
    }
}
*/
void delay(int x) {
    volatile unsigned loops = x; // Start the delay counter at 25,000
    while (--loops > 0);             // Count down until the delay counter reaches 0
}

void turnSegOn(int segNum) {
    switch(segNum){
        case 0:     LCDM10 |= 0x80; break;  //A1-A
        case 1:     LCDM6  |= 0x80; break;  //A2-A
        case 2:     LCDM4  |= 0x80; break;  //A3-A
        case 3:     LCDM19 |= 0x80; break;  //A4-A
        case 4:     LCDM15 |= 0x80; break;  //A5-A
        case 5:     LCDM8  |= 0x80; break;  //A6-A
        case 6:     LCDM8  |= 0x40; break;  //A6-B
        case 7:     LCDM8  |= 0x20; break;  //A6-C
        case 8:     LCDM8  |= 0x10; break;  //A6-D
        case 9:     LCDM15 |= 0x10; break;  //A5-D
        case 10:    LCDM19 |= 0x10; break;  //A4-D
        case 11:    LCDM4  |= 0x10; break;  //A3-D
        case 12:    LCDM6  |= 0x10; break;  //A2-D
        case 13:    LCDM10 |= 0x10; break;  //A1-D
        case 14:    LCDM10 |= 0x08; break;  //A1-E
        case 15:    LCDM10 |= 0x04; break;  //A1-F
    }
}

void turnSegOff(int segNum) {
    switch(segNum){
        case 0:     LCDM10 &= ~0x80; break;  //A1-A
        case 1:     LCDM6  &= ~0x80; break;  //A2-A
        case 2:     LCDM4  &= ~0x80; break;  //A3-A
        case 3:     LCDM19 &= ~0x80; break;  //A4-A
        case 4:     LCDM15 &= ~0x80; break;  //A5-A
        case 5:     LCDM8  &= ~0x80; break;  //A6-A
        case 6:     LCDM8  &= ~0x40; break;  //A6-B
        case 7:     LCDM8  &= ~0x20; break;  //A6-C
        case 8:     LCDM8  &= ~0x10; break;  //A6-D
        case 9:     LCDM15 &= ~0x10; break;  //A5-D
        case 10:    LCDM19 &= ~0x10; break;  //A4-D
        case 11:    LCDM4  &= ~0x10; break;  //A3-D
        case 12:    LCDM6  &= ~0x10; break;  //A2-D
        case 13:    LCDM10 &= ~0x10; break;  //A1-D
        case 14:    LCDM10 &= ~0x08; break;  //A1-E
        case 15:    LCDM10 &= ~0x04; break;  //A1-F
    }
}
