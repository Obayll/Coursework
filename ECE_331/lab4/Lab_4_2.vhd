--------------------------------------------------------------------------------------------
-- Course: ECE 332 - Digital System Design Lab
-- Author: 
-- 
-- Create Date: 
-- Experiment Name: BCD to Seven-segment Converter (Lab #4)
-- Design Name: Lab_4_2
-- Project Name: project_4 - Behavioral
-- Target Devices: xc7a35t-1cpg236c
--
-- Description:	Design a BCD to Seven-segment converter using the VHDL 'Selected signal
--				assignment statement' (i.e. the 'with select when' statement).  A seven-bit 
--				value will be assigned to the output, one bit to the output signal (port) for
--				each segment in the display, based on the decimal value to be displayed.
--				Use the truth table for the converter when writing this VHDL code.  
---------------------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity lab_4_2 is
    Port ( B3, B2, B1, B0 : in STD_LOGIC;
           a, b, c, d, e, f, g : out STD_LOGIC;
		   disp_right, disp_left, disp_midright, disp_midleft : out STD_LOGIC
		   );
end lab_4_2;

architecture behavioral of lab_4_2 is
signal BBBB : STD_LOGIC_VECTOR(3 DOWNTO 0);
signal Segments : STD_LOGIC_VECTOR(1 to 7); 

begin
BBBB <= B3 & B2 & B1 & B0;
with BBBB select Segments <=
    "0000001" when "0000",
    "1001111" when "0001",
    "0010010" when "0010",
	"0000110" when "0011",
	"1001100" when "0100",
	"0100100" when "0101",
	"0100000" when "0110",
	"0001111" when "0111",
	"0000000" when "1000",
	"0000100" when "1001",
	"0001000" when "1010",
	"1100000" when "1011",
	"0110001" when "1100",
	"1000010" when "1101",
	"0110000" when "1110",
	"0111000" when "1111",   
    "1111111" when others;
             
    a  <= segments(1);
    b  <= segments(2);
    c  <= segments(3);
    d  <= segments(4);
    e  <= segments(5);
    f  <= segments(6);
    g  <= segments(7);
        
    disp_right <= '0';		-- turn the seven-segment display on
    disp_left <= '1';		-- turn the seven-segment display off
    disp_midright <= '1';	-- turn the seven-segment display off
    disp_midleft <= '1';	-- turn the seven-segment display off 

end behavioral;
