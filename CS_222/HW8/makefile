bitwise: main.o encoding.o decoding.o
	gcc -o bitwise main.o encoding.o decoding.o 

main.o: encoding.h decoding.h
	gcc -c  main.c

encoding.o: encoding.h 
	gcc -c encoding.c

decoding.o: decoding.h 
	gcc -c decoding.c

