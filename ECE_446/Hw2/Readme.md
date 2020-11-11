# How to compile
Code compiled via command:

	**gcc -ggdb -O hw2.c -fsanitize=address -fno-omit-frame-pointer** (for checking memory errors)

	or simply

	**gcc -O hw2.c**

in directory where **hw2.c** and **hw2.h** both exist to produce output file **a.out**.

## How to run
Code ran via command:

	**./a.out**

in directory where **a.out** from the compilation above exists.