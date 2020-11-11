#include <stdio.h>
#include <stdlib.h>
#include "encoding.h"

void encoding(char itxt[], char obin[]) {
	FILE *input, *output;
	unsigned int c;

	input = fopen(itxt, "r");
	output = fopen(obin, "wb");

	while ((c = fgetc(input)) != EOF) {
		c <<= 8;
		c |= fgetc(input);
		c <<= 8;
		c |= fgetc(input);
		c <<= 8;
		c |= fgetc(input);
		
		c = c << 3 | (0xFFFFFFFF & c >> 29);
		
		fwrite(&c, sizeof(int), 1, output);
	}

	fclose(input);
	fclose(output);
}
