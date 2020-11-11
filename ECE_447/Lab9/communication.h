
#ifndef COMMUNICATION_H_
#define COMMUNICATION_H_

#include <stdint.h>
#include <msp430.h>
#include <stdio.h>
#include "lcd.h"
#include "bmp280.h"

s8 BMP280_I2C_bus_read(u8 dev_addr, u8 reg_addr, u8 *reg_data, u8 cnt);
s8 BMP280_I2C_bus_write(u8 dev_addr, u8 reg_addr, u8 *reg_data, u8 cnt);
s8 I2C_routine(void);
void  BMP280_delay_msek(u32 msek);
int check_ack(u8 address);

long temp;
double temp_double;
long press;
double press_double;

struct bmp280_t bmp280;

struct bmp280_calib_param_t calibration;


//I2C routine
s8 I2C_routine(void){
    bmp280.bus_write = BMP280_I2C_bus_write;
    bmp280.bus_read = BMP280_I2C_bus_read;
    bmp280.dev_addr = BMP280_I2C_ADDRESS2;
    bmp280.oversamp_temperature = 1;
    bmp280.oversamp_pressure = 1;
    bmp280.delay_msec = BMP280_delay_msek;

    calibration.dig_T1=27504;
    calibration.dig_T2=26435;
    calibration.dig_T3=-1000;
    calibration.dig_P1=36477;
    calibration.dig_P2=-10685;
    calibration.dig_P3=3024;
    calibration.dig_P4=2855;
    calibration.dig_P5=140;
    calibration.dig_P6=-7;
    calibration.dig_P7=15500;
    calibration.dig_P8=-14600;
    calibration.dig_P9=6000;

    return BMP280_INIT_VALUE;
}

double temp_conpensate(long temp){

        double v_x1_u32r = BMP280_INIT_VALUE;
        double v_x2_u32r = BMP280_INIT_VALUE;
        double temperature = BMP280_INIT_VALUE;
        /* calculate x1*/
        v_x1_u32r = (((double)temp) / 16384.0 -
                ((double)calibration.dig_T1) / 1024.0) *
        ((double)calibration.dig_T2);
        /* calculate x2*/
        v_x2_u32r = ((((double)temp) / 131072.0 -
                ((double)calibration.dig_T1) / 8192.0) *
                (((double)temp) / 131072.0 -
                ((double)calibration.dig_T1) / 8192.0)) *
        ((double)calibration.dig_T3);
        /* calculate t_fine*/
        calibration.t_fine = (v_x1_u32r + v_x2_u32r);
        /* calculate true pressure*/
        temperature = (v_x1_u32r + v_x2_u32r) / 5120.0;
        return temperature;
}

double press_conpensate(long press){
        double v_x1_u32p = BMP280_INIT_VALUE;
        double v_x2_u32p = BMP280_INIT_VALUE;
        double pressure = BMP280_INIT_VALUE;

        v_x1_u32p = ((double)calibration.t_fine/2.0) - 64000.0;
        v_x1_u32p = (((double)calibration.dig_P3) *
            v_x1_u32p * v_x1_u32p / 524288.0 +
            ((double)calibration.dig_P2) * v_x1_u32p) / 524288.0;
        v_x1_u32p = (1.0 + v_x1_u32p / 32768.0) *
        ((double)calibration.dig_P1);
        pressure = 1048576.0 - (double)press;
        if ((v_x1_u32p > 0) || (v_x1_u32p < 0))
            pressure = (pressure - (v_x2_u32p / 4096.0)) *
                        6250.0 / v_x1_u32p;
        else
        return BMP280_INVALID_DATA;
        v_x1_u32p = ((double)calibration.dig_P9) *
        pressure * pressure / 2147483648.0;
        v_x2_u32p = pressure * ((double)calibration.dig_P8) / 32768.0;
        pressure = pressure + (v_x1_u32p + v_x2_u32p +
                ((double)calibration.dig_P7)) / 16.0;

        return pressure;

}

//check ack
int check_ack(u8 address){
    int err=0;
    if(UCB1IFG & UCNACKIFG){
        UCB1CTLW0 |= UCTXSTP;
        UCB1IFG &=~ UCNACKIFG;
        err=-1;
    }
    return err;
}


/************** I2C/SPI buffer length ******/

#define I2C_BUFFER_LEN 8
#define SPI_BUFFER_LEN 5
#define BUFFER_LENGTH   0xFF
#define SPI_READ    0x80
#define SPI_WRITE   0x7F
#define BMP280_DATA_INDEX   1
#define BMP280_ADDRESS_INDEX    2

/*-------------------------------------------------------------------*
*   This is a sample code for read and write the data by using I2C/SPI
*   Use either I2C or SPI based on your need
*   The device address defined in the bmp180.c
*
*-----------------------------------------------------------------------*/
 /* \Brief: The function is used as I2C bus write
 *  \Return : Status of the I2C write
 *  \param dev_addr : The device address of the sensor
 *  \param reg_addr : Address of the first register, where data is to be written
 *  \param reg_data : It is a value held in the array,
 *      which is written in the register
 *  \param cnt : The no of bytes of data to be written
 */
s8  BMP280_I2C_bus_write(u8 dev_addr, u8 reg_addr, u8 *reg_data, u8 cnt)
{
    //--Ensure stop condition has been sent( i.e UCTXSTP not set)
  
    //--Set Slave address
    //-- Set USCI as transmitter (hint look UCB1CTLW0 registrer in prog. manual)
    //-- Set start condition - This will send start condition and slave address (hint. look UCB1CTLW0 in prog. manual)
    //-- make sure STT is cleared (start condition + address sent). i.e UCTXSTT is reset
    //--check for the ACK (see check_ack function in this file)

    //--Now start writing
    //-----Send the register address then one byte of data
    //-----Increment the address, send it and another byte of data until done
    //-----You need to check if transmission is complete after each reg address/ data byte sent (Until UCTXIFG is set)
    
    //--Send stop condtion
    //-- return error code
}

/*  \Brief: The function is used as I2C bus read
 *  \Return : Status of the I2C read
 *  \param dev_addr : The device address of the sensor
 *  \param reg_addr : Address of the first register, where data is going to be read
 *  \param reg_data : This is the data read from the sensor, which is held in an array
 *  \param cnt : The no of data to be read
 */
s8  BMP280_I2C_bus_read(u8 dev_addr, u8 reg_addr, u8 *reg_data, u8 cnt)
{
    //--Ensure stop condition has been sent( i.e UCTXSTP not set)


    //--Start reading data
    //---Set slave address
    //-- Set USCI as transmitter (hint look UCB1CTLW0 registrer in prog. manual). Need to send slave addr in write mode
    //-- Set start condition - This will send start condition and slave address (hint. look UCB1CTLW0 in prog. manual)

    //--Wait to make sure STT is cleared (start condition + address sent). i.e UCTXSTT is reset
    //--Check for the ACK

    //--Send the address of the first register to be read.

    //--Wait for  transmission to end  (Until UCTXIFG is set)
   

    //send repeated start
    //-- Set USCI as receiver (hint look UCB1CTLW0 registrer in prog. manual)

    //-- Set start condition - This will send start condition and slave address (hint. look UCB1CTLW0 in prog. manual)

    //--Wait to make sure STT is cleared (start condition + address sent). i.e UCTXSTT is reset

    
    ///--Receive  cnt-1 bytes and put them in the reg_data array. Slave will give data in the first register and then increments the address and send the next byte
    //----Befor taking the byte from the buffer, make sure you wait until it is received. Hint wait for UCRXIFG
   
    //-- Set stop condition
    //-- Wait for data to be received. Hint wait until UCRXIFG is set
    //--Get the last byte form the buffer and put it in the array
    //--Wait to ensure stop condition has been sent( i.e UCTXSTP not set)
   
    //--Return error code
}

void  BMP280_delay_msek(u32 msek){

}


#endif /* COMMUNICATION_H_ */
