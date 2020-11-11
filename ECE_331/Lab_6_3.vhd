------------------------------------------------------------------------------------------------------
-- Course: ECE 332 - Digital System Design Lab
-- Author:  Jordan Ditzler
-- 
-- Create Date: 
-- Experiment Name: Arithmetic Circuits in VHDL (Lab #6)
-- Design Name: Lab_6_3
-- Project Name: project_6 - Behavioral
-- Target Devices: xc7a35t-1cpg236c
--
-- Description: Design a 4-bit Multiplier and a 4-bit Comparator using behavioral VHDL.
-- 				Use the multiplication operator in a signal assignment statement to describe the 4-bit multiplier.
-- 				Use the appropriate relational operators in 'when else' statements to describe the 4-bit comparator.
--
-- Basys 3 Board resources used for this design:
-- 				8 slider switches for the inputs (A and B) to the 4-bit multiplier and comparator.
--				8 LEDs to display the product.
--				3 LEDs to display EQ, GT, and LT (comparator outputs).						
------------------------------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_SIGNED.all;

entity lab_6_3 is
port ( A, B : in STD_LOGIC_VECTOR(3 downto 0);
    Product : out STD_LOGIC_VECTOR(7 downto 0));
end lab_6_3;

architecture behavioral of lab_6_3 is
begin
    Product <= A * B;
end behavioral;
