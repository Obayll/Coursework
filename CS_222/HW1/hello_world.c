// CS222, Assignment 1
// Jim X. Chen (or your name)

/* Figure 1.13  Miles-to-Kilometers Conversion Program */
/*
 * Converts distance in miles to kilometers.
 */
#include <stdio.h>              /* printf, scanf definitions */
#define KMS_PER_MILE 1.609      /* conversion constant       */

int
main(void)
{
      double miles,  /* input - distance in miles.       */
             kms;    /* output - distance in kilometers  */

      /* Get the distance in miles. */
      printf("Hello World! Enter the distance in miles> ");
      scanf("%lf", &miles); // “lf” means double 

      /* Convert the distance to kilometers. */
      kms = KMS_PER_MILE * miles;

      /* Display the distance in kilometers. */
      printf("That equals %f kilometers.\n", kms); // printf: “f” works for both float and double

      return (0);
}
/* Sample Run
Enter the distance in miles> 10.00
That equals 16.090000 kilometers. */