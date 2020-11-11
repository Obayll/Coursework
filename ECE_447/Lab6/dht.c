#include "dht.h"

unsigned int hum, temp, parity, i = 0;
unsigned int array[42] = {0};

void decode(void) {
    for (i = 2;i < 18;i++) {
       if (array[i] > 110)
           hum = (hum << 1) | BIT0;
       else hum = (hum << 1);
    }
    for (i = 18; i < 34;i++) {
       if (array[i] > 110)
           temp = (temp << 1) | BIT0;
       else temp = (temp << 1);
    }
    for (i = 34; i < 42;i++) {
       if (array[i] > 110)
           parity = (parity << 1) | BIT0;
       else parity = (parity << 1);
    }
    return;
}
