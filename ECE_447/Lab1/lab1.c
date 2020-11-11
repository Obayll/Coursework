#include <msp430.h>

/*
 * Swtich S1 - P1.1
 * LED1	     - P1.0
 */

void delay(void) {
	volatile unsigned loops = 25000; // Start the delay counter at 25,000
	while (--loops > 0);             // Count down until the delay counter reaches 0
}

void main(void) {
    WDTCTL = WDTPW | WDTHOLD; // Stop watchdog timer

    P1REN |= BIT1;            // Connect resistor on P1.1 to P1OUT
    P1OUT |= BIT1;            // Set output register for P1.1 to '1'
    P1DIR |= BIT0;            // Make P1.0 an output
    PM5CTL0 &= ~LOCKLPM5;     // Unlock ports from power manager
	
    for (;;) {
    	delay();              // Run the delay sub-routine
    	if (!(P1IN & BIT1))   // Read the input from P1.1 and check its state
    		P1OUT |= BIT0;    // If the button is pressed, turn on the LED
    	else
    		P1OUT &= ~BIT0;   // If the button isn't pressed, turn off the LED
    }
}
