--------------------------------------------------------------------------------------------
-- Course: ECE 332 - Digital System Design Lab
-- Author: Jordan Ditzler
-- 
-- Create Date: 
-- Experiment Name: Arithmetic Circuits in VHDL (Lab #6)
-- Project Name: project_6 - Behavioral
--
-- Description:	Test bench for the 4-bit Multiplier and Comparator in Project_6 (Lab_6_3).
--				It tests all 2^4 x 2^4 = 256 combinations of the inputs.
--				This is known as an "exhaustive test".
---------------------------------------------------------------------------------------------

library ieee;
use ieee.std_logic_1164.all;
use IEEE.STD_LOGIC_SIGNED.all;

entity project_6_tb is
end project_6_tb;

architecture behavior of project_6_tb is
    component lab_6_3
    port(
        A : in STD_LOGIC;
        B : in STD_LOGIC;
        Product : out STD_LOGIC);
    end component;

    signal test_vector : STD_LOGIC_VECTOR(1 downto 0);
    signal product_vector : STD_LOGIC;
begin
uut : lab_6_3
    port map(
        A => test_vector(1),
        B => test_vector(0),
        Product => product_vector);
	Mult_and_Comp_simulation : process
	begin

	test_vector <= "00";
	wait for 10 ns;
	test_vector <= "01";
	wait for 10 ns;
	test_vector <= "10";
	wait for 10 ns;
	test_vector <= "11";
	wait for 10 ns;
	end process Mult_and_Comp_simulation;
	
end behavior;
