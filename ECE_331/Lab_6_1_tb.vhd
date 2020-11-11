--------------------------------------------------------------------------------------------
-- Course: ECE 332 - Digital System Design Lab
-- Author: Jordan Ditzler
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

entity project_6_tb is
end project_6_tb;

architecture behavior of project_6_tb is
-- Insert component declaration for device under test (DUT) here.
	component lab_6_1
	port(
		A : in STD_LOGIC;
		B : in STD_LOGIC;
		Cin : in STD_LOGIC;
		Cout : out STD_LOGIC;
		Sum : out STD_LOGIC);
	end component;
-- Insert signal declarations here.
	signal test_vector : STD_LOGIC_VECTOR(2 downto 0);
	signal cout_vector : STD_LOGIC;
	signal sum_vector : STD_LOGIC;
begin
-- Insert component instantiation (i.e. port map statement) here.
uut : lab_6_1
	port map(
		A => test_vector(2),
		B => test_vector(1),
		Cin => test_vector(0),
		Cout => cout_vector,
		Sum => sum_vector);
	FA_simulation : process
	begin
-- Insert your testbench code here.
	test_vector <= "000";
	wait for 10 ns;
	test_vector <= "001";
	wait for 10 ns;
	test_vector <= "010";
	wait for 10 ns;
	test_vector <= "011";
	wait for 10 ns;
	test_vector <= "100";
	wait for 10 ns;
	test_vector <= "101";
	wait for 10 ns;
	test_vector <= "110";
	wait for 10 ns;
	test_vector <= "111";
	wait for 10 ns;
	end process FA_simulation;

end behavior;
