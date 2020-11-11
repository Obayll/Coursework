--------------------------------------------------------------------------------------------
-- Course: ECE 332 - Digital System Design Lab
-- Author: 
-- 
-- Create Date: 
-- Experiment Name: Arithmetic Circuits in VHDL (Lab #6)
-- Project Name: project_6 - Behavioral
--
-- Description:	Test bench for the Full Adder in Project_6 (Lab_6_1).
--				It tests all 2^3 = 8 combinations of the inputs.
--				This is known as an "exhaustive test".
---------------------------------------------------------------------------------------------

library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;

entity project_6_tb is
end project_6_tb;

architecture behavior of project_6_tb is

component lab_6_1
port (
    A, B, Cin : in STD_LOGIC;
    Sum, Cout : out STD_LOGIC);
end component;

signal input_tb : STD_LOGIC_VECTOR(2 downto 0);
signal Sum_tb, Cout_tb : STD_LOGIC;

begin
-- Insert component instantiation (i.e. port map statement) here.
UUT : lab_6_1 port map(
    A => input_tb(2),
    B => input_tb(1),
    Cin => input_tb(0),
    Sum => Sum_tb,
    Cout => Cout_tb);
    
	FA_simulation : process
	begin
	
	input_tb <= "000";
	for i in 0 to 7 loop
	   wait for 10 ns;
	   input_tb <= input_tb + 1;
	end loop;
	
	end process FA_simulation;
end behavior;