/*
 * plot.h
 *
 *  Created on: Nov 12, 2017
 *      Author: chubb
 */

#ifndef PLOT_H_
#define PLOT_H_

#include "graphics.h"
#include <stdio.h>


char temp_txt[30];
char press_txt[30];


void drawText(double temp_double, double press_double){
    clearScreen(1);
    setColor(COLOR_16_WHITE);
    int upper_temp=(int)temp_double;
    int lower_temp=((int)(temp_double*100))%100;
    double press_k=press_double/1000.0;
    int upper_press=(int)press_k;
    int lower_press=((int)(press_k*100))%100;
    sprintf(temp_txt,"Temperature: %d.%d C",upper_temp,lower_temp);
    sprintf(press_txt,"Pressure: %d.%d kPa",upper_press,lower_press);
    drawString(2, 12, FONT_SM, temp_txt);
    drawString(2, 24, FONT_SM, press_txt);

}





#endif /* PLOT_H_ */
