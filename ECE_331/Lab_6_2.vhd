------------------------------------------------------------------------------------------------------
-- Course: ECE 332 - Digital System Design Lab
-- Author: 
-- 
-- Create Date:  
-- Experiment Name: Arithmetic Circuits in VHDL (Lab #6)
-- Design Name: Lab_6_2
-- Project Name: project_6 - Behavioral
-- Target Devices: xc7a35t-1cpg236c
--
-- Description:	Design a 4-bit Adder and an Overflow circuit using behavioral VHDL.
-- 				Use the addition operator in a signal assignment statement to describe the 4-bit adder.
--				Assign the fifth bit of the result to the carry-out bit.
--				Use a Boolean expression to describe the overflow bit.
--
-- Basys 3 Board resources used for this design:
-- 				8 slider switches for the inputs (A and B) to the 4-bit adder.
--				1 seven-segment display to display the sum (decimal point is used for carry-out).
--				4 LEDs to display the sum.					
------------------------------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
-- Insert additional package here.

entity lab_6_2 is

-- Insert your code here.

end lab_6_2;


architecture behavioral of lab_6_2 is

-- Insert signal declaration for tempSum here.

begin

-- Insert your code for the 4-bit Adder here.

-- Insert your code for the Overflow bit here.

-- Insert your code for the BCD to seven-segment converter (from Lab 4) here.
	
							
	disp_left <= '1';		-- turn the seven-segment display off					
	disp_midleft <= '1';	-- turn the seven-segment display off 
	disp_midright <= '1';	-- turn the seven-segment display off
	disp_right <= '0';		-- turn the seven-segment display on

end behavioral;
