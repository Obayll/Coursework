#include "stack.h"

uint32_t stack[6] = {0};
signed int topofstack = -1;
const char bat[6][2] = {{0x10,0x30},{0x30,0x30},{0x30,0x70},{0x70,0x70},{0x70,0xF0},{0xF0,0xF0}};

void push(uint32_t num) {
    if (topofstack > 4) {
        lcd_clear();
        //display "FULL" on LCD
        LCDM10 = 0x8E; LCDM6 = 0x7C; LCDM4 = 0x1C; LCDM19 = 0x1C;
        delay(50000); delay(50000);
        return;
    } else {
        topofstack++;
        stack[topofstack] = num;
        LCDM14 = bat[topofstack][0];
        LCDM18 = bat[topofstack][1];
        LCDM3 = 0x00;
        return;
    }
}

uint32_t pop(void) {
    if (topofstack < 0) {
        lcd_clear();
        //display "EMPTY" on LCD
        LCDM10 = 0x9E; LCDM7 = 0xA0; LCDM6 = 0x6C; LCDM4 = 0xCF; LCDM19 = 0x80; LCDM20 = 0x50; LCDM16 = 0xB0;
        delay(50000); delay(50000);
        return 0;
    } else {
        topofstack--;
        if (topofstack > -1) {
            LCDM14 = bat[topofstack][0];
            LCDM18 = bat[topofstack][1];
        } else {
            LCDM18 = 0x10; LCDM14 = 0x10;
        }
        LCDM3 = (stack[topofstack+1] > 999999) ? 0x01 : 0x00;
        return stack[topofstack+1];
    }
}
