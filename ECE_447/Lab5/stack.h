#include <msp430.h>
#include <stdint.h>

extern uint32_t stack[6];
extern signed int topofstack;
extern const char bat[6][2];

void push(uint32_t num);
uint32_t pop(void);
