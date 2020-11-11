#ifndef DHT_H_
#define DHT_H_

#include <msp430.h>
#include <stdint.h>

extern unsigned int hum, temp, parity, i;
extern unsigned int array[42];

void decode(void);

#endif
