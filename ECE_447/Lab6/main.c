#include "lcd.h"
#include "dht.h"

#define TIMER_CFG_STOP TASSEL__ACLK | MC__STOP | TACLR;
#define TIMER_CFG_UP   TASSEL__ACLK | MC__UP   | TACLR;

enum DHT_States {
    TBE,
    TGO,
    SENSOR,
} curState;

unsigned int lastLength, thisLength, iterator = 0;

void msp_init() {
    WDTCTL = WDTPW | WDTHOLD;

    PM5CTL0 &= ~LOCKLPM5;

    P1DIR |= BIT0;
    P2DIR |= BIT0;

    TA2CTL = TASSEL_1 | MC_0 | TACLR;

    TB0CTL = TASSEL_2 | MC_2 | TAIE | TACLR;
    TB0CCTL6 = CM_2 | CCIS_1 | SCS | CAP | CCIE;

    __enable_interrupt();
}

#pragma vector = TIMER2_A0_VECTOR
__interrupt void TIMER2_A0_ISR() {
    _low_power_mode_off_on_exit();
    TA2CTL = TIMER_CFG_STOP;
}

#pragma vector = TIMER0_B1_VECTOR
__interrupt void TIMER0_B1_ISR(void) {
    switch(TB0IV) {
    case 12:
        thisLength = TB0CCR6 - lastLength;
        lastLength = TB0CCR6;
        array[iterator] = thisLength;
        iterator++;
        break;
    default: break;
    }
}

int main(void) {
    msp_init();
    lcd_init();

    curState = TBE;

    while (1) {
        switch (curState) {
        case TBE:
            TA2CTL   |= TIMER_CFG_UP;
            TA2CCTL0 |= CCIE;
            TA2CCR0   = 32768;

            TB0CCTL6 |=  CCIFG;
            TB0CCTL6 &= ~CCIE;

            P2DIR  |=  BIT0;
            P2OUT  |=  BIT0;
            P2REN  &= ~BIT0;
            P2SEL1 &= ~BIT0;
            P2SEL0 &= ~BIT0;

            decode();

            P1OUT ^= BIT0;

            LCDM6  = 0x38;
            LCDM10 = 0x6F;
            LCDM11 = 0x00;
            displayNum(hum,3);

            curState = TGO;
            break;
        case TGO:
            TA2CTL   |= TIMER_CFG_UP;
            TA2CCTL0 |= CCIE;
            TA2CCR0   = 33;

            P2OUT &= ~BIT0;

            lastLength = TB0CCR6;

            curState = SENSOR;
            break;
        case SENSOR:
            TA2CTL   |=  TIMER_CFG_UP;
            TA2CCTL0 |=  CCIE;
            TA2CCR0   = 32768;

            TB0CCTL6 &= ~CCIFG;
            TB0CCTL6 |=  CCIE;

            iterator = 0;

            P1OUT ^= BIT0;

            P2DIR  &= ~BIT0;
            P2OUT  |=  BIT0;
            P2REN  |=  BIT0;
            P2SEL1 |=  BIT0;
            P2SEL0 &= ~BIT0;

            LCDM6  = 0xCF;
            LCDM10 = 0x80;
            LCDM11 = 0x50;
            displayNum(temp,3);

            curState = TBE;
            break;
        }
        __low_power_mode_0();
    }
}
