#include <msp430.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>

void msp_init()
{
    WDTCTL = WDTPW | WDTHOLD;     // Stop watchdog timer

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

    PM5CTL0 &= ~LOCKLPM5;         // Unlock ports from power manager

    __enable_interrupt();         // Set global interrupt enable bit in SR register
}

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

unsigned int seq[10] = {0,1,2,3,1,0,0,2,3,1};
unsigned int score;
unsigned int seqLen;
unsigned int userInput;
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
__interrupt void p1_ISR()
{
    switch (P1IV)
    {
        case P1IV_NONE: break;
        case P1IV_P1IFG0: break;
        case P1IV_P1IFG1:
            startFLAG = !startFLAG;
            break;
        case P1IV_P1IFG2:
            seqLen++;
            seqFLAG = true;
            break;
        case P1IV_P1IFG3: break;
        case P1IV_P1IFG4: break;
        case P1IV_P1IFG5: break;
        case P1IV_P1IFG6: break;
        case P1IV_P1IFG7: break;
        default:   break;
    }
}

#pragma vector = PORT2_VECTOR
__interrupt void p2_ISR()
{
    switch (P2IV)
    {
        case P2IV_NONE: break;
        case P2IV_P2IFG0:
            userInput = 1;
            blinkLED();
            break;
        case P2IV_P2IFG1:
            userInput = 2;
            blinkLED();
            break;
        case P2IV_P2IFG2:
            userInput = 3;
            blinkLED();
            break;
        case P2IV_P2IFG3:
            userInput = 4;
            blinkLED();
            break;
        case P2IV_P2IFG4: break;
        case P2IV_P2IFG5: break;
        case P2IV_P2IFG6: break;
        case P2IV_P2IFG7: break;
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

// Update the LFSR
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

void resetLED(void) {
    P3OUT &= ~BIT0;
    P3OUT &= ~BIT1;
    P3OUT &= ~BIT2;
    P3OUT &= ~BIT3;
}

void resetLCD(void) {
    //LCD A1
    LCDM10 &= ~0x80;
    LCDM10 &= ~0x40;
    LCDM10 &= ~0x20;
    LCDM10 &= ~0x10;
    LCDM10 &= ~0x08;
    LCDM10 &= ~0x04;
    LCDM10 &= ~0x02;
    LCDM10 &= ~0x01;
    //LCD A2
    LCDM6 &= ~0x80;
    LCDM6 &= ~0x40;
    LCDM6 &= ~0x20;
    LCDM6 &= ~0x10;
    LCDM6 &= ~0x08;
    LCDM6 &= ~0x04;
    LCDM6 &= ~0x02;
    LCDM6 &= ~0x01;
}

void displaySequence() {
    //3.0 = Blue
    //3.1 = Yellow
    //3.2 = Red
    //3.3 = Green
    unsigned int i;
    resetLED();
    for (i = 0; i<seqLen ;i++) {
        switch (seq[i]) {
            case 0:
                P3OUT ^= BIT0; delay(50000); P3OUT ^= BIT0; delay(50000); continue;
            case 1:
                P3OUT ^= BIT1; delay(50000); P3OUT ^= BIT1; delay(50000); continue;
            case 2:
                P3OUT ^= BIT2; delay(50000); P3OUT ^= BIT2; delay(50000); continue;
            case 3:
                P3OUT ^= BIT3; delay(50000); P3OUT ^= BIT3; delay(50000); continue;
            default: continue;
        }
    }
}

void displayScore(void) {
    resetLCD();
    switch (score)
    {
        case 0:
            LCDM10 |= 0x80; //A1-A
            LCDM10 |= 0x40; //A1-B
            LCDM10 |= 0x20; //A1-C
            LCDM10 |= 0x10; //A1-D
            LCDM10 |= 0x08; //A1-E
            LCDM10 |= 0x04; //A1-F
            break;
        case 1:
            LCDM10 |= 0x40; //A1-B
            LCDM10 |= 0x20; //A1-C
            break;
        case 2:
            LCDM10 |= 0x80; //A1-A
            LCDM10 |= 0x40; //A1-B
            LCDM10 |= 0x01; //A1-M
            LCDM10 |= 0x02; //A1-G
            LCDM10 |= 0x08; //A1-E
            LCDM10 |= 0x10; //A1-D
            break;
        case 3:
            LCDM10 |= 0x80; //A1-A
            LCDM10 |= 0x40; //A1-B
            LCDM10 |= 0x01; //A1-M
            LCDM10 |= 0x02; //A1-G
            LCDM10 |= 0x20; //A1-C
            LCDM10 |= 0x10; //A1-D
            break;
        case 4:
            LCDM10 |= 0x04; //A1-F
            LCDM10 |= 0x02; //A1-G
            LCDM10 |= 0x01; //A1-M
            LCDM10 |= 0x40; //A1-B
            LCDM10 |= 0x20; //A1-C
            break;
        case 5:
            LCDM10 |= 0x80; //A1-A
            LCDM10 |= 0x04; //A1-F
            LCDM10 |= 0x02; //A1-G
            LCDM10 |= 0x01; //A1-M
            LCDM10 |= 0x20; //A1-C
            LCDM10 |= 0x10; //A1-D
            break;
        case 6:
            LCDM10 |= 0x80; //A1-A
            LCDM10 |= 0x04; //A1-F
            LCDM10 |= 0x02; //A1-G
            LCDM10 |= 0x01; //A1-M
            LCDM10 |= 0x20; //A1-C
            LCDM10 |= 0x10; //A1-D
            LCDM10 |= 0x08; //A1-E
            break;
        case 7:
            LCDM10 |= 0x80; //A1-A
            LCDM10 |= 0x40; //A1-B
            LCDM10 |= 0x20; //A1-C
            break;
        case 8:
            LCDM10 |= 0x80; //A1-A
            LCDM10 |= 0x40; //A1-B
            LCDM10 |= 0x20; //A1-C
            LCDM10 |= 0x10; //A1-D
            LCDM10 |= 0x08; //A1-E
            LCDM10 |= 0x04; //A1-F
            LCDM10 |= 0x02; //A1-G
            LCDM10 |= 0x01; //A1-M
            break;
        case 9:
            LCDM10 |= 0x80; //A1-A
            LCDM10 |= 0x40; //A1-B
            LCDM10 |= 0x20; //A1-C
            LCDM10 |= 0x04; //A1-F
            LCDM10 |= 0x02; //A1-G
            LCDM10 |= 0x01; //A1-M
            break;
        case 10:
            LCDM10 |= 0x40; //A1-B
            LCDM10 |= 0x20; //A1-C
            LCDM6 |= 0x80; //A2-A
            LCDM6 |= 0x40; //A2-B
            LCDM6 |= 0x20; //A2-C
            LCDM6 |= 0x10; //A2-D
            LCDM6 |= 0x08; //A2-E
            LCDM6 |= 0x04; //A2-F
            break;
        default: break;
    }
}

void winGame(void) {
    resetLED();
    unsigned int i;
    for (i = 0; i < 6; i++) {
        P3OUT ^= BIT0;
        P3OUT ^= BIT1;
        P3OUT ^= BIT2;
        P3OUT ^= BIT3;
        delay(50000);
    }
}

void loseGame(void) {
    resetLED();
    P3OUT ^= BIT0;
    P3OUT ^= BIT1;
    P3OUT ^= BIT2;
    P3OUT ^= BIT3;
    delay(50000);
    delay(50000);
    delay(50000);
    P3OUT ^= BIT0;
    P3OUT ^= BIT1;
    P3OUT ^= BIT2;
    P3OUT ^= BIT3;
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

void main(void)
{
    WDTCTL = WDTPW | WDTHOLD;

    onboard_seg_display_init();

    populateSeq();

    seqLen = 1;
    score = 0;
    msp_init();
    unsigned int x = 0;
    bool prevGame = false;

    displayScore();
    displaySequence();

    while(1) {
        endGame: //label
        if (endFLAG) {
            startFLAG = false;
            x = 0;
            seqLen = 1;
            userInput = 0;
            displayScore();
            endFLAG = false;
            prevGame = false;
        }
        newGame: //label
        if (seqFLAG) {
            displaySequence();
            seqFLAG = false;
        }
        if (prevGame) {
            x = 0;
            seqLen = 1;
            score = 0;
            displayScore();
            startFLAG = true;
        }
        if (startFLAG) {
            score = 0;
            prevGame = true;
            newSeq: //label
            if (score >= 10) goto skip;
            displayScore();
            resetLED();
            displaySequence();
            reset: //label
            delay(25000);
            if (checkInput(x) == 1) {
                x++;
                delay(10000);
            }
            else if (checkInput(x) == 2){
                loseGame();
                endFLAG = true;
                goto endGame;
            }
            userInput = 0;
            if (seqLen == x) {
                if (seqLen >= 10) seqLen = 10;
                else seqLen++;
                score++;
                x = 0;
                goto newSeq;
            }
            skip: //label
            if (score >= 10) {
                winGame();
                endFLAG = true;
                goto endGame;
            }
            if (!startFLAG) goto newGame;
            goto reset;
        }
    }

}