/*
 * Operators to process complex numbers
 */
#include <stdio.h>
#include <math.h>
#include "complex.h"
#include "mandelbrot.h"

/* Driver											*/
int main(void) {
	/* Initializes complex num to be top-left of imaginary graph				*/
	complex_t com;
	com.real = -2.0;
	com.imag = 1.12;
	
	/* Initializes counters & array and calculates X & Y step values			*/
	int i, j;
	double xStep = (0.47 - (-2)) / 40, yStep = (1.12 - (-1.12)) / 30;
	char arr[30][40] = {{'#'}};
	
	/* Loops through 2D array as you would a graph, checks if position is in the mandelbrot */
	/* set, and enters respective number (# = empty, 1 = full)				*/
	for (i = 0 ; i < 30; i++) {
		for (j = 0; j < 40; j++) {
			if(mandelbrot(com, 15).real < 10000) {
				arr[i][j] = '1';
			} else {
				arr[i][j] = '#';
			}
			printf("%c ", arr[i][j]);
			com.real += xStep;
		}
		printf("\n");
		com.imag -= yStep;
		com.real = -2.0;
    }
	
	return(0);
}
