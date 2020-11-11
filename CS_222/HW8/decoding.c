#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include "decoding.h"

void decoding(char otxt[], char ibin[]) {
 	FILE *input, *output;
	unsigned int c, MASK = 0xFF;
	unsigned char a;

	input = fopen(ibin, "rb");
    output = fopen(otxt, "w");

    while (fread(&c, sizeof(int), 1, input) == 1) {
		c = (0xFFFFFFFF & c >> 3) | c << 29;
        a = (c >> 24) & MASK;
        if (isascii(a)) fputc(a, output);
		
        a = (c >> 16) & MASK;
        if (isascii(a)) fputc(a, output);

        a = (c >> 8) & MASK;
        if (isascii(a)) fputc(a, output);

        a = c & MASK;
        if (isascii(a)) fputc(a, output);
    }

	fclose(output);
    fclose(input);
}
