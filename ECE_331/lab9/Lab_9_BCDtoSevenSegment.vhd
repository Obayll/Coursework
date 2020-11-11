--------------------------------------------------------------------------------------------
-- Course: ECE 332 - Digital System Design Lab
-- Author: 
-- 
-- Create Date: 
-- Experiment Name: Modulo-100 Counter in VHDL (using Structural design) 
-- Design Name: Lab_9_BCDtoSevenSegment
-- Project Name: project_9 - Structural
-- Target Devices: xc7a35t-1cpg236c
--
-- Description: This is one of several components used in the design of the Modulo-100 Counter
--				This is the design of the BCD to Seven-segment Converter using a 'with select when' statement.
--				This design comes from Lab #4. 
-- 
-- Basys 3 Board resources used to test this component:
-- 				4 slider switches for the BCD input.
--				1 seven-segment display to display the specified digit.
--				1 slider switch to turn the Seven-segment display on/off.		
---------------------------------------------------------------------------------------------

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity lab_9_BCDtoSevenSegment is
port (
    BinaryCodedDigit : in STD_LOGIC_VECTOR(3 downto 0);
    a,b,c,d,e,f,g : out STD_LOGIC);
end lab_9_BCDtoSevenSegment;

architecture behavioral of lab_9_BCDtoSevenSegment is
signal segments : STD_LOGIC_VECTOR(7 downto 0);
begin
    with BinaryCodedDigit select segments <=
        "0000001" when "0000", -- 0
        "1001111" when "0001", -- 1
        "0010010" when "0010", -- 2
        "0000110" when "0011", -- 3
        "1001100" when "0100", -- 4
        "0100100" when "0101", -- 5
        "0100000" when "0110", -- 6
        "0001111" when "0111", -- 7
        "0000000" when "1000", -- 8
        "0000100" when "1001", -- 9
        "0000000" when others;
    
    a  <= segments(1);
    b  <= segments(2);
    c  <= segments(3);
    d  <= segments(4);
    e  <= segments(5);
    f  <= segments(6);
    g  <= segments(7);
end behavioral;