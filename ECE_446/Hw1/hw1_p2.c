#include <stdio.h>

int main()
{
    int myInt, *myPointerToInt;
    myPointerToInt = &myInt; /* initialise pointer */
    *myPointerToInt = 0; /* set myInt to zero */
    printf("myInt is %d\n", myInt);
    printf("*myPointerToInt is %d\n", *myPointerToInt);
    *myPointerToInt += 1; /* increment what myPointerToInt points to */
    printf("myInt is %d\n", myInt);
    (*myPointerToInt)++; /* increment what myPointerToInt points to */
    printf("myInt is %d\n", myInt); 
}