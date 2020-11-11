--------------------------------------------------------------------------------------------
-- Course: ECE 332 - Digital System Design Lab
-- Author: 
-- 
-- Create Date:  
-- Experiment Name: Multiplexers, Decoders and Encoders in VHDL (Lab #7)
-- Project Name: project_7 - Behavioral
--
-- Description:	Test bench for the 3-to-8 Decoder and 8-to-3 Priority Encoder in Project_7 (Lab_7_2).
--				All test conditions are covered (i.e. an "exhaustive" test.) 
---------------------------------------------------------------------------------------------

library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;

entity project_7_tb is
end project_7_tb;

architecture behavior of project_7_tb is
component lab_7_2
port( DEC_in: in STD_LOGIC_VECTOR(2 downto 0);
    DEC_enable: in STD_LOGIC;
    DEC_out: out STD_LOGIC_VECTOR(7 downto 0);
    ENC_in: in STD_LOGIC_VECTOR(7 downto 0);
    Segments: out STD_LOGIC_VECTOR(6 downto 0);
    disp_left, disp_midleft, disp_right, disp_midright : out STD_LOGIC);
end component;

signal test_DEC_in : STD_LOGIC_VECTOR(2 downto 0);
signal test_DEC_enable : STD_LOGIC;
signal test_DEC_out : STD_LOGIC_VECTOR(7 downto 0);
signal test_ENC_in :  STD_LOGIC_VECTOR(7 downto 0);
signal test_Segments : STD_LOGIC_VECTOR(6 downto 0);
begin
   UUT : Lab_7_2
   port map ( DEC_in => test_DEC_in,
               DEC_enable => test_DEC_enable,
               DEC_out => test_DEC_out,
               ENC_in => test_ENC_in,
               Segments => test_Segments);

	DEC_simulation: process
	begin
	test_DEC_in <= "000";
	test_DEC_enable <= '0';
	for I in 0 to 1 loop
	   for J in 0 to 7 loop
	       wait for 10 ns;
	       test_DEC_in <= test_DEC_in + '1';
	   end loop;
	test_DEC_enable <= '1';
	end loop;
	end process DEC_simulation;

	ENC_simulation: process
	begin
	end process ENC_simulation;
	
end behavior;
