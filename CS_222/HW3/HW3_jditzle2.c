//CS 222, Assignment 3
//Jordan Ditzler

/* Fahrenheit to Celsius and Celsius to Fahrenheit Conversion Program that displays extra functions when entered value is below -200 or above 200*/
/*
 * Converts Fahrenheit degrees to Celsius degrees or Celsius degrees to Fahrenheit degrees.
 */
#include <stdio.h>    /* printf, scanf definitions                 */
#include <stdlib.h>   /* exit defintions                           */
#include <math.h>     /* sqrt, pow defintions                      */
#define FCR 0.556     /* Fahrenheit to Celsius conversion constant */
#define CFR 1.8       /* Celsius to Fahrenheit conversion constant */

double fc_converter(double); double cf_converter(double); /* Functions to be used for temperature conversion. */

int main (void) 
{
    char    user;   /* Value to determine if the user wants Fahrenheit to Celsius, or vice versa. */           
    double  fah,    /* User entered Fahrenheit degree to be converted into Celsius.               */
            cel,    /* User entered Celsius degree to be converted into Fahrenheit.               */
            val;    /* The new degree/value to be displayed at the end of the loop.               */
    
    /* Cycles once to assure the user is asked to enter F/C at least once */
    do
    {
        /* Asks user for F/C input. f/c is also accepted. Loop terminates if a different value is entered. */
        printf("Jordan Ditzler > Fahrenheit/Celsius Converter. Please enter F or C: ");
        scanf(" %c", &user);
        
        /* Begind switch statement with user as the case. */
        switch(user)
        {
            /* When F/f is entered, asks for a Fahrenheit degree, scans user input, calls fc_converter to convert Fahrenheit to Celsius, and assigns val to return value. */
            case 'f':
            case 'F':
                printf("Jordan Ditzler > Please enter a Fahrenheit degree number: ");
                scanf("%lf", &fah);
                val = fc_converter(fah);
                break;
            
            /* When C/c is entered, asks for a Celsius degree, scans user input, calls cf_converter to convert Celsius to Fahrenheit, and assigns val to return value. */
            case 'c':
            case 'C':
                printf("Jordan Ditzler > Please enter a Celsius degree number: ");
                scanf("%lf", &cel);
                val = cf_converter(cel);
                break;
            
            /* Prints error message and terminates loop when no case statements apply. */
            default:
                printf("The input is unkown!\n");
                exit(0);
                
        }
        
        /* Displays the returned value of whichever conversion function wall called. */
        printf("The calculated value from the converter function is: %.2f\n", val);
      
      /* Will continue the loop as long as the user input is F, f, C, or c. */
    } while ((user == 'f') || (user == 'c') || (user == 'F') || (user == 'C'));
    
    return (0);
}

/* Converts Fahrenheit to Celsius if degree number is between -200 and 200 inclusively and returns the converted degree. Otherwise, returns the square root of the user enter value. */
double fc_converter(double F)
{
    /* If the user number is between -200 and 200, then Celsius is calculated, displayed and returned. */
    if ((F >= -200) && (F <= 200))
    {
        double newF = FCR * (F - 32.00);
        printf("%.2f F ==> %.2f C\n", F, newF);
        return(newF);
    }
    /* If not between -200 and 200, then an error message is displayed and returns square root of user number. */
    else
    {
        printf("Invalid Celcius temperature.\n");
        return(sqrt(F));
    }
    
}

/* Converts Celsius to Fahrenheit if degree number is between -200 and 200 inclusively and returns the converted degree. Otherwise, returns the cube of the user enter value. */
double cf_converter(double C)
{
    /* If the user number is between -200 and 200, then Fahrenheit is calculated, displayed and returned. */
    if ((C >= -200) && (C <= 200))
    {
        double newC = CFR * C + 32.00;
        printf("%.2f C ==> %.2f F\n", C, newC);
        return(newC);
    } 
    /* If not between -200 and 200, then an error message is displayed and returns cube of user number. */
    else
    {
        printf("Invalid Fahrenheit temperature.\n");
        return(pow(C, 3));
    }
}