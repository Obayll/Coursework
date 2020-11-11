#include <msp430.h>
#include <stdint.h>

extern const char decodeVals[4];
extern const char keys[4][4];
extern unsigned int userInput;

void keypad_init();
unsigned int decode();
