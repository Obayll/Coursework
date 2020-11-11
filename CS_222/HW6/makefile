complex: main.o complex.o
	gcc -o complex main.o complex.o -lm

main.o: main.c complex.h
	gcc -c main.c

complex.o: complex.c complex.h
	gcc -c complex.c

clean:
	rm *.o complex
	ls

