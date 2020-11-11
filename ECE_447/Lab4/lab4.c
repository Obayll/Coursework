#include <msp430.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>

#define CLK  8192
#define GS_3 208
#define A_3  220
#define B_3  247
#define C_4  262
#define D_4  294
#define DS_4 311
#define E_4  330
#define F_4  349
#define G_4  392
#define GS_4 415
#define A_4  440
#define AS_4 466

void msp_init()
{
    WDTCTL = WDTPW | WDTHOLD;     // Stop watchdog timer

    PM5CTL0 &= ~LOCKLPM5;         // Unlock ports from power manager

    P1DIR &= ~BIT1;               // Set pin P1.1 to be an input
    P1REN |=  BIT1;               // Enable internal pullup/pulldown resistor on P1.1
    P1OUT |=  BIT1;               // Pullup selected on P1.1
    P1IES |=  BIT1;               // Make P1.1 interrupt happen on the falling edge
    P1IFG &= ~BIT1;               // Clear the P1.1 interrupt flag
    P1IE  |=  BIT1;               // Enable P1.1 interrupt

    P1DIR &= ~BIT2;               // Set pin P1.2 to be an input
    P1REN |=  BIT2;               // Enable internal pullup/pulldown resistor on P1.2
    P1OUT |=  BIT2;               // Pullup selected on P1.2
    P1IES |=  BIT2;               // Make P1.2 interrupt happen on the falling edge
    P1IFG &= ~BIT2;               // Clear the P1.2 interrupt flag
    P1IE  |=  BIT2;               // Enable P1.2 interrupt

    P2DIR &= ~BIT0;               // Set pin P2.0 to be an input
    P2REN |=  BIT0;               // Enable internal pullup/pulldown resistor on P2.0
    P2OUT |=  BIT0;               // Pullup selected on P2.0
    P2IES |=  BIT0;               // Make P2.0 interrupt happen on the falling edge
    P2IFG &= ~BIT0;               // Clear the P2.0 interrupt flag
    P2IE  |=  BIT0;               // Enable P2.0 interrupt

    P2DIR &= ~BIT1;               // Set pin P2.1 to be an input
    P2REN |=  BIT1;               // Enable internal pullup/pulldown resistor on P2.1
    P2OUT |=  BIT1;               // Pullup selected on P2.1
    P2IES |=  BIT1;               // Make P2.1 interrupt happen on the falling edge
    P2IFG &= ~BIT1;               // Clear the P2.1 interrupt flag
    P2IE  |=  BIT1;               // Enable P2.1 interrupt

    P2DIR &= ~BIT2;               // Set pin P2.2 to be an input
    P2REN |=  BIT2;               // Enable internal pullup/pulldown resistor on P2.2
    P2OUT |=  BIT2;               // Pullup selected on P2.2
    P2IES |=  BIT2;               // Make P2.2 interrupt happen on the falling edge
    P2IFG &= ~BIT2;               // Clear the P2.2 interrupt flag
    P2IE  |=  BIT2;               // Enable P2.2 interrupt

    P2DIR &= ~BIT3;               // Set pin P2.3 to be an input
    P2REN |=  BIT3;               // Enable internal pullup/pulldown resistor on P2.3
    P2OUT |=  BIT3;               // Pullup selected on P2.3
    P2IES |=  BIT3;               // Make P2.3 interrupt happen on the falling edge
    P2IFG &= ~BIT3;               // Clear the P2.3 interrupt flag
    P2IE  |=  BIT3;               // Enable P2.3 interrupt

    P3DIR |=  (BIT0);             // Set pin 3.0 to be an output
    P3DIR |=  (BIT1);             // Set pin 3.1 to be an output
    P3DIR |=  (BIT2);             // Set pin 3.2 to be an output
    P3DIR |=  (BIT3);             // Set pin 3.3 to be an output

    __enable_interrupt();         // Set global interrupt enable bit in SR register

    TB0CCR0 = 100;              // (32768 Hz / 4) / 100 ticks = 81.92 Hz
    TB0CCR3 = 80;               // 80 / 100 = 80% duty cycle
    //TB0CCR4 = 60;               // 60 / 100 = 60% duty cycle
    //TB0CCR5 = 40;               // 40 / 100 = 40% duty cycle
    //TB0CCR6 = 20;               // 20 / 100 = 20% duty cycle

    // Init PWM outputs: P2.{4-7} -> TB0.{3-6}
    // Try looking at these on an oscilloscope to see what the output looks like
    P2DIR  |= 0xF0;             // make pin P2.4-7 output
    P2SEL0 |= 0xF0;             // select module 1 of 3 (module 0 is GPIO)
    P2SEL1 &= 0x0F;             // ... see page 97 in the datasheet (slas789b)

    // set output mode to reset/set (see page 459 in user's guide - slau367f)
    TB0CCTL3 = TB0CCTL4 = TB0CCTL5 = TB0CCTL6 = OUTMOD_7;
    // clock source = ACLK divided by 4, put timer in UP mode, clear timer register
    //TB0CTL = TBSSEL__ACLK | ID__4 | MC__UP | TBCLR;
}

void onboard_seg_display_init(void) {
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

unsigned int seq[10];
unsigned int score;
unsigned int level;
unsigned int userInput;
unsigned int song[32] = {A_3,C_4,D_4,D_4,D_4,E_4,F_4,F_4,F_4,G_4,E_4,E_4,D_4,C_4,C_4,D_4,A_3,C_4,D_4,D_4,D_4,F_4,G_4,G_4,G_4,A_4,AS_4,AS_4,A_4,G_4,A_4,D_4};
unsigned int dur[32] = {1,1,2,2,1,1,2,2,1,1,2,2,1,1,1,4,1,1,2,2,1,1,2,2,1,1,2,2,1,1,1,4};
bool seqFLAG = false;
bool startFLAG = false;
bool endFLAG = false;

void delay(unsigned int x) {
    volatile unsigned loops = x;
    while (--loops > 0);
}

void blinkLED(void) {
    switch(userInput) {
        case 0: break;
        case 1: P3OUT ^= BIT0; delay(10000); P3OUT ^= BIT0; break;
        case 2: P3OUT ^= BIT1; delay(10000); P3OUT ^= BIT1; break;
        case 3: P3OUT ^= BIT2; delay(10000); P3OUT ^= BIT2; break;
        case 4: P3OUT ^= BIT3; delay(10000); P3OUT ^= BIT3; break;
        default: break;
    }
}

#pragma vector = PORT1_VECTOR
__interrupt void p1_ISR() {
    switch (P1IV)
    {
        case P1IV_NONE: break;
        case P1IV_P1IFG0: break;
        case P1IV_P1IFG1:
            __low_power_mode_off_on_exit();
            startFLAG = !startFLAG;
            break;
        case P1IV_P1IFG2:
            __low_power_mode_off_on_exit();
            seqFLAG = true;
            break;
        default:   break;
    }
}

#pragma vector = PORT2_VECTOR
__interrupt void p2_ISR() {
    switch (P2IV)
    {
        case P2IV_NONE: break;
        case P2IV_P2IFG0:
            while (1) {
                if (!(P2IN & BIT0)) {
                    //__low_power_mode_off_on_exit();
                    // red D#_4
                    userInput = 1;
                    TB0CCR0 = (CLK/DS_4);
                    TB0CCR3 = TB0CCR0/2;
                    TB0CTL = TBSSEL__ACLK | ID__4 | MC__UP | TBCLR;
                    P3OUT |= BIT0;
                    volatile unsigned loops = 2500;
                    while (--loops > 0);
                } else {
                    P3OUT &= ~BIT0;
                    TB0CTL = MC__STOP;
                    break;
                }
            }
        case P2IV_P2IFG1:
            while (1) {
                if (!(P2IN & BIT1)) {
                    //yellow B_3
                    //__low_power_mode_off_on_exit();
                    userInput = 2;
                    TB0CCR0 = (CLK/B_3);
                    TB0CCR3 = TB0CCR0/2;
                    TB0CTL = TBSSEL__ACLK | ID__4 | MC__UP | TBCLR;
                    P3OUT |= BIT1;
                    volatile unsigned loops = 2500;
                    while (--loops > 0);
                } else {
                    P3OUT &= ~BIT1;
                    TB0CTL = MC__STOP;
                    break;
                }
            }
        case P2IV_P2IFG2:
            while (1) {
                if (!(P2IN & BIT2)) {
                    //__low_power_mode_off_on_exit();
                    // Green G#_4
                    userInput = 3;
                    TB0CCR0 = (CLK/GS_4);
                    TB0CCR3 = TB0CCR0/2;
                    TB0CTL = TBSSEL__ACLK | ID__4 | MC__UP | TBCLR;
                    P3OUT |= BIT2;
                    volatile unsigned loops = 2500;
                    while (--loops > 0);
                } else {
                    P3OUT &= ~BIT2;
                    TB0CTL = MC__STOP;
                    break;
                }
            }
        case P2IV_P2IFG3:
            while (1) {
                if (!(P2IN & BIT3)) {
                    //__low_power_mode_off_on_exit();
                    //blue G#_3
                    userInput = 4;
                    TB0CCR0 = (CLK/GS_3);
                    TB0CCR3 = TB0CCR0/2;
                    TB0CTL = TBSSEL__ACLK | ID__4 | MC__UP | TBCLR;
                    P3OUT |= BIT3;
                    volatile unsigned loops = 2500;
                    while (--loops > 0);
                } else {
                    P3OUT &= ~BIT3;
                    TB0CTL = MC__STOP;
                    break;
                }
            }
        default:   break;
    }
}

int checkInput(unsigned int x) {
    switch(userInput) {
        case 0: return 0;
        case 1: if (seq[x] == 0) return 1; else return 2;
        case 2: if (seq[x] == 1) return 1; else return 2;
        case 3: if (seq[x] == 2) return 1; else return 2;
        case 4: if (seq[x] == 3) return 1; else return 2;
        default: return 0;
    }
}

uint16_t update_LFSR(uint16_t LFSR) {
    uint16_t new_val;

    new_val  = ((LFSR & BIT0)) ^  //create new bit to be rotated in
               ((LFSR & BIT1) >> 1) ^
               ((LFSR & BIT3) >> 3) ^
               ((LFSR & BIT5) >> 5);

    LFSR = LFSR >> 1;             //shift to perform rotation
    LFSR &= ~(BITF);              //have to clear bit because shift is arithmetic
    LFSR |= (new_val << 15);      //combine with new bit

    return LFSR;
}

void displaySequence() {
    P3OUT = 0x00;
    unsigned int i;
    for (i = 0; i < level ; i++) {
        switch (seq[i]) {
            case 0:
                P3OUT ^= BIT0;
                TB0CCR0 = (CLK/DS_4);
                TB0CCR3 = TB0CCR0/2;
                TB0CTL = TBSSEL__ACLK | ID__4 | MC__UP | TBCLR;
                delay(50000);
                P3OUT ^= BIT0;
                TB0CTL = MC__STOP;
                delay(50000);
                continue;
            case 1:
                P3OUT ^= BIT1;
                TB0CCR0 = (CLK/B_3);
                TB0CCR3 = TB0CCR0/2;
                TB0CTL = TBSSEL__ACLK | ID__4 | MC__UP | TBCLR;
                delay(50000);
                P3OUT ^= BIT1;
                TB0CTL = MC__STOP;
                delay(50000);
                continue;
            case 2:
                P3OUT ^= BIT2;
                TB0CCR0 = (CLK/GS_4);
                TB0CCR3 = TB0CCR0/2;
                TB0CTL = TBSSEL__ACLK | ID__4 | MC__UP | TBCLR;
                delay(50000);
                P3OUT ^= BIT2;
                TB0CTL = MC__STOP;
                delay(50000);
                continue;
            case 3:
                P3OUT ^= BIT3;
                TB0CCR0 = (CLK/GS_3);
                TB0CCR3 = TB0CCR0/2;
                TB0CTL = TBSSEL__ACLK | ID__4 | MC__UP | TBCLR;
                delay(50000);
                P3OUT ^= BIT3;
                TB0CTL = MC__STOP;
                delay(50000);
                continue;
            default: continue;
        }
    }
}

void displayScore(void) {
    LCDM10 = LCDM6 = 0x00;
    switch (score) {
        case 0: LCDM10 |= 0xFC; break;
        case 1: LCDM10 |= 0x60; break;
        case 2: LCDM10 |= 0xDB; break;
        case 3: LCDM10 |= 0xF3; break;
        case 4: LCDM10 |= 0x67; break;
        case 5: LCDM10 |= 0xB7; break;
        case 6: LCDM10 |= 0xBF; break;
        case 7: LCDM10 |= 0xE0; break;
        case 8: LCDM10 |= 0xFF; break;
        case 9: LCDM10 |= 0xE7; break;
        case 10: LCDM10 |= 0x60; LCDM6  |= 0xFC; break;
        default: break;
    }
}

void winGame(void) {
    P3OUT = 0x00;
    unsigned int i;
    for (i = 0; i < 32; i++) {
        TB0CCR0 = (CLK/song[i]);
        TB0CCR3 = TB0CCR0/2;
        TB0CTL = TBSSEL__ACLK | ID__4 | MC__UP | TBCLR;
        P3OUT ^= BIT0; P3OUT ^= BIT1; P3OUT ^= BIT2; P3OUT ^= BIT3;
        delay(dur[i]*12500);
        TB0CTL = MC__STOP;
    }
}

void loseGame(void) {
    P3OUT = 0x00;
    TB0CCR0 = (CLK/82);
    TB0CCR3 = TB0CCR0/2;
    TB0CTL = TBSSEL__ACLK | ID__4 | MC__UP | TBCLR;
    P3OUT ^= BIT0; P3OUT ^= BIT1; P3OUT ^= BIT2; P3OUT ^= BIT3;
    delay(50000); delay(50000); delay(50000);
    P3OUT ^= BIT0; P3OUT ^= BIT1; P3OUT ^= BIT2; P3OUT ^= BIT3;
    TB0CTL = MC__STOP;
}

void populateSeq(void) {
    uint16_t LFSR = 0x5A3F;
    unsigned int i;
    for (i = 0; i < 10; i++) {
        LFSR = update_LFSR(LFSR);
        LFSR = update_LFSR(LFSR);
        seq[i] = LFSR & (BIT0 | BIT1);
    }
}

void main(void) {
    msp_init();
    onboard_seg_display_init();

    populateSeq();
    level = 1;
    score = 0;
    unsigned int x = 0;
    const int endScore = 10;
    bool prevGame = false;

    displayScore();
    displaySequence();

    while(1) {
        //TB0CTL = MC__STOP;
        //__bis_SR_register(LPM3_bits);
        endGame: //label
        if (endFLAG) {
            endFLAG = false;
            prevGame = false;
            startFLAG = false;
            x = 0;
            level = 1;
            userInput = 0;
        }
        newGame: //label
        if (seqFLAG) {
            seqFLAG = false;
            level++;
            displaySequence();
        }
        if (prevGame) {
            x = 0;
            level = 1;
            score = 0;
            displayScore();
            startFLAG = true;
        }
        if (startFLAG) {
            score = 0;
            prevGame = true;
            newLevel: //label
            userInput = 0;
            if (score >= endScore) {
                displayScore();
                winGame();
                endFLAG = true;
                goto endGame;
            }
            displayScore();
            displaySequence();
            reset: //label
            if (checkInput(x) == 1) {
                x++;
                userInput = 0;
                delay(10000);
            }
            else if (checkInput(x) == 2){
                loseGame();
                endFLAG = true;
                goto endGame;
            }
            if (level == x) {
                score = level;
                if (level >= 10) level = 10;
                else level++;
                x = 0;
                userInput = 0;
                goto newLevel;
            }
            //__bis_SR_register(LPM3_bits);
            if (!startFLAG) goto newGame;
            goto reset;
        }
    }
}
