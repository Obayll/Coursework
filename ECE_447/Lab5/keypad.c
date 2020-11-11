#include "keypad.h"

const char keys[4][4] = {{1,2,3,10},{4,5,6,11},{7,8,9,12},{15,0,14,13}};
const char decodeVals[4] = {0xE0,0xD0,0xB0,0x70};
unsigned int userInput = 0;

void keypad_init() {
    P8DIR |=  (BIT4 + BIT5 + BIT6 + BIT7);
    P8OUT &= ~(BIT4 + BIT5 + BIT6 + BIT7);
    P2DIR &= ~(BIT1 + BIT2 + BIT3 + BIT4);
    P2REN |=  (BIT1 + BIT2 + BIT3 + BIT4);
    P2OUT |=  (BIT1 + BIT2 + BIT3 + BIT4);
    P2IES |=  (BIT1 + BIT2 + BIT3 + BIT4);
    P2IFG &= ~(BIT1 + BIT2 + BIT3 + BIT4);
    P2IE  |=  (BIT1 + BIT2 + BIT3 + BIT4);
}

unsigned int decode() {
    unsigned int i;
    if (userInput == 5) return 16;
    else if (userInput == 6) return 17;
    for (i = 0; i < 4; i++){
        P8OUT = decodeVals[i];
        if (P2IN != (BIT1 + BIT2 + BIT3 + BIT4)) return keys[userInput-1][i];
    }
    return 0;
}
