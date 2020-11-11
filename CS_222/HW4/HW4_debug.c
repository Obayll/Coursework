//  CS222  Debugging & generating a random number
//
//  Name: Jordan Ditzler
//
//  Generate a sequence of random numbers between 1 and 6 as rolling a dice
//

#include <stdio.h>
//#incldue <stdlib.h>
#include <stdlib.h>
#include <time.h>

int main()
{
  //int i, count,
  int i, count;
  long my_random_num = 0; // declare your own var to hold random number

  srandom((unsigned int)(time(NULL)));
  // "Seed" the random number generator with the current time

  printf("Enter the number of rolling  -> ");
  //scanf("%d", count);
  scanf("%d",&count);

  //for (i == 1; i < count; i++);
  //for (i = 1; i < count; i++);
  //for (i = 1; i < count; i++)
  for (i = 1; i <= count; i++)
  {
    my_random_num = random();

    //system("stty raw -echo"); getchar(); System("stty cooked echo");
    system("stty raw -echo"); getchar(); system("stty cooked echo");

    printf("This rolling you get: %d.\nPress a key to continue:\n",
        //1 + my_random_num % 6);
        1 + (int)my_random_num % 6);

    printf("i = %d\n", i); // Added for Debugging
  }

  //Printf("\n  Thanks for playing!  \n");
  printf("\n  Thanks for playing!  \n");
  //return;
  return(0);
}
