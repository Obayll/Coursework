#include "lcd.h"
#include "stack.h"
#include "keypad.h"

#define TIMER_CFG_STOP TASSEL__ACLK | MC__STOP | TACLR;
#define TIMER_CFG_UP   TASSEL__ACLK | MC__UP   | TACLR;

unsigned int tempBIT, tempPort;
char FLAG = 0;
const char batScroll[6][2] = {{0x10,0x30},{0x30,0x10},{0x10,0x50},{0x50,0x10},{0x10,0x90},{0x90,0x10}};

void msp_init() {
    WDTCTL = WDTPW | WDTHOLD;                   // Stop watchdog timer
    PM5CTL0 &= ~LOCKLPM5;                       // Unlock ports from power manager

    P1DIR &= ~(BIT1 + BIT2);
    P1REN |=  (BIT1 + BIT2);
    P1OUT |=  (BIT1 + BIT2);
    P1IES |=  (BIT1 + BIT2);
    P1IFG &= ~(BIT1 + BIT2);
    P1IE  |=  (BIT1 + BIT2);

    // Timers A2/3 are good for debouncing since they don't have external pins
    TA2CCR0 = 1638;                              // 50 ms * 32768 Hz = 1638.4 ticks debounce delay
    TA2CTL = TASSEL__ACLK | MC__STOP | TACLR;   // Configure debounce timer but don't start it

    __enable_interrupt();                       // Set global interrupt enable bit in SR register
}

#pragma vector = PORT1_VECTOR
__interrupt void p1_ISR() {
    _low_power_mode_off_on_exit();
    tempPort = 1;
    switch (P1IV) {
    case P1IV_P1IFG1:
        userInput = 5;
        tempBIT = BIT1;
        P1IE &= ~tempBIT;       // Disable interrupt while debouncing
        TA2CCTL0 = CCIE;        // Enable debounce timer interrupt
        TA2CTL = TIMER_CFG_UP;  // Start debounce timer
        break;
    case P1IV_P1IFG2:
        FLAG = 1;
        userInput = 6;
        tempBIT = BIT2;
        P1IE &= ~tempBIT;       // Disable interrupt while debouncing
        TA2CCTL0 = CCIE;        // Enable debounce timer interrupt
        TA2CTL = TIMER_CFG_UP;  // Start debounce timer
        break;
    default: break;
    }
}

#pragma vector = PORT2_VECTOR
__interrupt void p2_ISR() {
    _low_power_mode_off_on_exit();
    tempPort = 2;
    switch (P2IV) {
    case P2IV_P2IFG1:
        userInput = 1;
        tempBIT = BIT1;
        P2IE &= ~tempBIT;       // Disable interrupt while debouncing
        TA2CCTL0 = CCIE;        // Enable debounce timer interrupt
        TA2CTL = TIMER_CFG_UP;  // Start debounce timer
        break;
    case P2IV_P2IFG2:
        userInput = 2;
        tempBIT = BIT2;
        P2IE &= ~tempBIT;       // Disable interrupt while debouncing
        TA2CCTL0 = CCIE;        // Enable debounce timer interrupt
        TA2CTL = TIMER_CFG_UP;  // Start debounce timer
        break;
    case P2IV_P2IFG3:
        userInput = 3;
        tempBIT = BIT3;
        P2IE &= ~tempBIT;       // Disable interrupt while debouncing
        TA2CCTL0 = CCIE;        // Enable debounce timer interrupt
        TA2CTL = TIMER_CFG_UP;  // Start debounce timer
        break;
    case P2IV_P2IFG4:
        userInput = 4;
        tempBIT = BIT4;
        P2IE &= ~tempBIT;       // Disable interrupt while debouncing
        TA2CCTL0 = CCIE;        // Enable debounce timer interrupt
        TA2CTL = TIMER_CFG_UP;  // Start debounce timer
        break;
    default: break;
    }
}

#pragma vector = TIMER2_A0_VECTOR
__interrupt void debounce_ISR() {
    switch (tempPort) {
    case 1:
        TA2CCTL0 = 0;               // Disable debounce timer interrupt
        TA2CTL = TIMER_CFG_STOP;    // Stop debounce timer
        P1IE |= tempBIT;            // Re-enable interrupt after debouncing
        P1IFG &= ~tempBIT;          // Clear interrupt flag until next action
        break;
    case 2:
        TA2CCTL0 = 0;               // Disable debounce timer interrupt
        TA2CTL = TIMER_CFG_STOP;    // Stop debounce timer
        P2IE |= tempBIT;            // Re-enable interrupt after debouncing
        P2IFG &= ~tempBIT;          // Clear interrupt flag until next action
        break;
    default: break;
    }
}

void delay(unsigned int num) {
    volatile unsigned loops = num;
    while (--loops > 0);
}

void main(void) {
    msp_init();
    lcd_init();
    keypad_init();

    unsigned int realInput = 0;
    unsigned int iterator = 0;
    uint32_t tempNum = 0;

    while(1){
        realInput = decode();
        if (FLAG) {
            switch (realInput) {
            case 14: //# - echo
                push(stack[iterator]);
                break;
            case 15: //* - exit
                FLAG = 0;
                LCDM14 = bat[topofstack][0];
                LCDM18 = bat[topofstack][1];
                displayNum(0,6);
                break;
            case 16: //S1 - Scroll up
                if (iterator < topofstack) iterator++;
                break;
            case 17: //S2 - Scroll down
                if (iterator > 0) iterator--;
                break;
            default: break;
            }
            if (FLAG) {
                LCDM14 = batScroll[iterator][0];
                LCDM18 = batScroll[iterator][1];
                displayNum(stack[iterator],6);
            }
        } else {
            switch (realInput) {
            case 0 ... 9: //numeric entry
                tempNum = tempNum * 10;
                tempNum = tempNum + realInput;
                break;
            case 10: //A - add
                tempNum = pop();
                push(tempNum = pop() + tempNum);
                tempNum = 0;
                break;
            case 11: //B - subtract
                tempNum = pop();
                push(tempNum = pop() - tempNum);
                tempNum = 0;
                break;
            case 12: //C - multiply
                tempNum = pop();
                push(tempNum = pop() * tempNum);
                tempNum = 0;
                break;
            case 13: //D - divide
                tempNum = pop();
                push(tempNum = pop() / tempNum);
                tempNum = 0;
                break;
            case 14: //# - push
                push(tempNum);
                tempNum = 0;
                break;
            case 15: //* - pop
                tempNum = pop();
                break;
            default: break;
            }
            displayNum(tempNum,6);
        }

        P8OUT &= ~(BIT4 + BIT5 + BIT6 + BIT7);

        __bis_SR_register(LPM3_bits); // ACLK stays on in LPM3
    }
}
