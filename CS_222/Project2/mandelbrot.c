/*
 * Operators to process complex numbers
 */
#include <stdio.h>
#include <math.h>
#include "complex.h"
#include "mandelbrot.h"

/*
 * Complex number to be checked, integer determing number of steps (15)
 * 10000 return indicates number is not in mandelbrot set
 * Any other return indicates number is in mandelbrot set
 */
/* output - complex number that determines if input is in mandelbrot set			*/
complex_t mandelbrot(complex_t c, int n) {
	if (n == 0) {
		return(c);
	} else {
		complex_t num = mandelbrot(c, n - 1);
		complex_t m = abs_complex(num);
		if (m.real >= 10000) {
			m.real = 10000;
			m.imag = 0;
			return(m);
		}
		return(add_complex(multiply_complex(num, num), c));
	}
}
