--------------------------------------------------------------------------------------------
-- Course: ECE 332 - Digital System Design Lab
-- Author: 
-- 
-- Create Date: 
-- Experiment Name: Multiplexers, Decoders and Encoders in VHDL (Lab #7)
-- Project Name: project_7 - Behavioral
--
-- Description:	Test bench for the 4-bit 4-to-1 Multiplexer in Project_7 (Lab_7_1).
--				All test conditions are covered (i.e. an "exhaustive" test.) 
---------------------------------------------------------------------------------------------

library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_signed.all;

entity project_7_tb is
end project_7_tb;

architecture behavior of project_7_tb is

 component lab_7_1
 port (Data_in, MUX_select : in STD_LOGIC_VECTOR(1 downto 0);
         disp_onoff: in STD_LOGIC;
         disp_left, disp_midleft, disp_right, disp_midright : out STD_LOGIC;
         Segments : out STD_LOGIC_VECTOR(1 to 7));  
 end component;
 signal test_data_in, test_MUX_select : STD_LOGIC_VECTOR(1 downto 0);
 signal test_Segments : STD_LOGIC_VECTOR(1 to 7);
 signal test_disp_onoff : STD_LOGIC;

begin
UUT : lab_7_1
port map(
        Data_in => test_data_in, 
        MUX_select => test_MUX_select, 
        Segments => test_Segments, 
        disp_onoff => test_disp_onoff);

	MUX_simulation: process
	begin
    test_disp_onoff <= '1';
    test_data_in <= "00";
    test_MUX_select <= "00";
    
    for I in 0 to 3 loop
        for J in 0 to 3 loop
            test_data_in <= test_data_in + 1;
        end loop;
        test_MUX_select <= test_MUX_select +1;
    end loop;
	end process MUX_simulation;
	
end behavior;
