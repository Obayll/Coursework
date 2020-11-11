--------------------------------------------------------------------------------------------
-- Course: ECE 332 - Digital System Design Lab
-- Author: Craig Lorie
-- 
-- Create Date: 07/26/2018 
-- Experiment Name: BCD to Seven-segment Converter (Lab #5)
-- Project Name: project_5 - Behavioral
--
-- Description:	Test bench for Lab 5 (project_5).
--				It tests all possible combinations of the inputs (0000 through 1111).
--				This is known as an "exhaustive test".
---------------------------------------------------------------------------------------------

library ieee;
use ieee.std_logic_1164.all;

entity project_4_tb is
end project_4_tb;

architecture behavior of project_4_tb is
component lab_4_2 is
    Port ( B3, B2, B1, B0 : in STD_LOGIC;
           a, b, c, d, e, f, g : out STD_LOGIC;
		   disp_right : out STD_LOGIC
		   );
end component;

signal BBBB_tb : STD_LOGIC_VECTOR(3 downto 0);
signal a_tb, b_tb, c_tb, d_tb, e_tb, f_tb, g_tb : STD_LOGIC;
begin
	uut : lab_4_2 port map (
			B3 => BBBB_tb(3),
			B2 => BBBB_tb(2),
			B1 => BBBB_tb(1),
			B0 => BBBB_tb(0),
			a => a_tb, 
			b => b_tb, 
			c => c_tb, 
			d => d_tb, 
			e => e_tb, 
			f => f_tb, 
			g => g_tb);

			
	stimuli : process
	begin
	
		BBBB_tb <= "0000";	-- 0
		wait for 10 ns;
		
		BBBB_tb <= "0001";	-- 1
		wait for 10 ns;
		
		BBBB_tb <= "0010";	-- 2
		wait for 10 ns;
		
		BBBB_tb <= "0011";	-- 3
		wait for 10 ns;
		
		BBBB_tb <= "0100";	-- 4
		wait for 10 ns;
		
		BBBB_tb <= "0101";	-- 5
		wait for 10 ns;
		
		BBBB_tb <= "0110";	-- 6
		wait for 10 ns;
		
		BBBB_tb <= "0111";	-- 7
		wait for 10 ns;

		BBBB_tb <= "1000";	-- 8
		wait for 10 ns;
		
		BBBB_tb <= "1001";	-- 9
		wait for 10 ns;
		
		BBBB_tb <= "1010";	-- 10 (A)
		wait for 10 ns;
		
		BBBB_tb <= "1011";	-- 11 (B)
		wait for 10 ns;
		
		BBBB_tb <= "1100";	-- 12 (C)
		wait for 10 ns;
		
		BBBB_tb <= "1101";	-- 13 (D)
		wait for 10 ns;
		
		BBBB_tb <= "1110";	-- 14 (E)
		wait for 10 ns;
		
		BBBB_tb <= "1111";	-- 15 (F)
		wait for 10 ns;
		
	end process stimuli;
	
end behavior;
