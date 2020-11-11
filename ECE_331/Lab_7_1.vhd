------------------------------------------------------------------------------------------------------
-- Course: ECE 332 - Digital System Design Lab
-- Author: 
-- 
-- Create Date: 
-- Experiment Name: Multiplexers, Decoders, and Encoders in VHDL (Lab #7)
-- Design Name: Lab_7_1 (Multiplexer)
-- Project Name: project_7 - Behavioral
-- Target Devices: xc7a35t-1cpg236c
--
-- Description:	Design a 4-bit 4-to-1 Multiplexer using behavioral VHDL.
-- 				Use a 'with select when' statement to describe the multiplexer.
--				The multiplexer outputs A*1 when select = 0, A*2 when select = 1, 
-- 				A*4 when select = 2, and A*4 + 3 when select = 3, where A is a 2-bit binary input (data).
-- 				The design may NOT use the multiplication operator.
-- 				The output from the multiplexer will be displayed on a seven-segment display.
-- 
-- Basys 3 Board resources used for this design:
-- 				2 slider switches for the select inputs to the multiplexer
-- 				2 slider switches for the data inputs to the multiplexer
-- 				1 slider switch to control (i.e turn on/off) the seven-segment display
-- 				1 Seven-segment display for the multiplexer output (outputs a hex value)
------------------------------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity lab_7_1 is
    port (Data_in, MUX_select : in STD_LOGIC_VECTOR(1 downto 0);
          disp_onoff: in STD_LOGIC;
          disp_left, disp_midleft, disp_right, disp_midright : out STD_LOGIC;
          Segments : out STD_LOGIC_VECTOR(1 to 7));  
end lab_7_1;

architecture behavioral of lab_7_1 is
signal output : STD_LOGIC_VECTOR(3 downto 0);   
signal m2 : STD_LOGIC_VECTOR(3 downto 0);
signal m4 : STD_LOGIC_VECTOR(3 downto 0);
signal m4_3 : STD_LOGIC_VECTOR(3 downto 0);
begin
     m2 <= '0' & data_in & '0';
     m4 <= data_in & '0' & '0';
     m4_3 <= m4 + "0011";
     
	with MUX_select select
	       output <= '0' & '0' & data_in when "00",
	                 m2 when "01",
	                 m4 when "10",
	                 m4_3 when "11";
	with output select
	       Segments <= "0000001" when "0000",
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
	       
	disp_left <= '1';				
	disp_midleft <= '1';
	disp_midright <= '1';
	disp_right <= disp_onoff;
end behavioral;
