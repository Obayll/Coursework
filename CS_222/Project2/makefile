mandelbrot: main.o complex.o mandelbrot.o
	gcc -o mandelbrot main.o complex.o mandelbrot.o -lm

main.o: main.c complex.h mandelbrot.h
	gcc -c main.c

complex.o: complex.c complex.h
	gcc -c complex.c

mandelbrot.o: mandelbrot.c complex.h mandelbrot.h
	gcc -c mandelbrot.c

