//CS 222, Assignment 2
//Jordan Ditzler

/* Fahrenheit to Celsius Conversion Program */
/*
 * Converts Fahrenheit degrees to Celsius degrees.
 */
#include <stdio.h>       /* printf, scanf definitions */
#include <math.h>        /* sqrt defintions           */ 
#define FCR 0.556        /* conversion constant       */

int main (void)
{
	double  fah,		/* input - degrees in fahrenheit	    */
		cel;		/* output - degrees in celsius		    */

	/* Gets degrees in Fahrenheit */
	printf("Jordan Ditzler :-) Fahrenheit to Celsius Converter. Please enter a temperature in Fahrenheit: ");
	scanf("%lf", &fah);

	/* Converts Fahrenheit to Celsius */
	cel = FCR * (fah - 32);

	/* Displays the degrees in Fahrenheit and Celsius */
	printf("%lf F ==> %lf C\n", fah, cel);

	/* Displays the square root of the input */
	printf("The square root of the input %lf is %lf.\n", fah, sqrt(fah));

	return (0);
}
