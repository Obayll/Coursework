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

entity lab_5_2 is
    Port ( B3, B2, B1, B0 : in STD_LOGIC;
           a, b, c, d, e, f, g : out STD_LOGIC;
		   disp_right, disp_left, disp_midright, disp_midleft : out STD_LOGIC
		   );
end lab_5_2;

architecture behavioral of lab_5_2 is
signal BBBB : STD_LOGIC_VECTOR(3 DOWNTO 0);
signal segments : STD_LOGIC_VECTOR(1 to 7); 

begin


-- Insert your code here.


disp_right <= '0';		-- turn the seven-segment display on
disp_left <= '1';		-- turn the seven-segment display off
disp_midright <= '1';	-- turn the seven-segment display off
disp_midleft <= '1';	-- turn the seven-segment display off 

end behavioral;
