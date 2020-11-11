#include <msp430.h>

/*
 * Swtich S1 - P1.1
 * LED1      - P1.0
 */

void delay(void) {
    volatile unsigned loops = 25000; // Start the delay counter at 25,000
    while (--loops > 0);             // Count down until the delay counter reaches 0
}

void main(void) {
    unsigned toggle = 0;
    WDTCTL = WDTPW | WDTHOLD; // Stop watchdog timer

    P1REN |= BIT1;            // Connect resistor on P1.1 to P1OUT
    P1OUT |= BIT1;            // Set output register for P1.1 to '0'
    P1DIR |= BIT0;            // Make P1.0 an output
    PM5CTL0 &= ~LOCKLPM5;     // Unlock ports from power manager

    for (;;) {
        while (toggle) {
            P1OUT ^= 0x01;
            delay();
            if (!(P1IN & BIT1)) {
                toggle = 0;
                P1OUT &= ~BIT0;
                delay();
            }
        }
        if (!(P1IN & BIT1))  toggle = 1;
    }
}
