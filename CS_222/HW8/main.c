#include <stdio.h>
#include <stdlib.h>
#include "encoding.h"
#include "decoding.h"

int main() {
	char itxt[] = "HW8.txt", bin[] = "HW8.bin", otxt[] = "HW8c.txt"; 

	system("cat HW8.txt");
	encoding(itxt, bin);
	decoding(otxt, bin);
	printf("\n");
	system("cat HW8c.txt");
	printf("\n");

	return(0); 
}
