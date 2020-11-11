------------------------------------------------------------------------------------------------------
-- Course: ECE 332 - Digital System Design Lab
-- Author: 
-- 
-- Exam Date: 
-- Experiment Name: ECE 332 Final Exam
-- Design Name: Lab_FinalExam_tb (VHDL test bench)
-- Project Name: project_FinalExam - Behavioral
-- Target Devices: xc7a35t-1cpg236c
--
------------------------------------------------------------------------------------------------------


library ieee;
-- Insert USE statements here.
use ieee.std_logic_1164.all;
use ieee.std_logic_signed.all;

entity lab_FinalExam_tb is
end lab_FinalExam_tb;

architecture behavior of lab_FinalExam_tb is

component lab_FinalExam
port (
    in_A, in_B : in STD_LOGIC_VECTOR(3 downto 0);
    in_Select : in STD_LOGIC_VECTOR(2 downto 0);
    Segments : out STD_LOGIC_VECTOR(1 to 7);
    Overflow, Zero, lessThan : out STD_LOGIC;
    disp_left, disp_midleft, disp_midright, disp_right : out STD_LOGIC);
end component;

signal in_A_tb, in_B_tb : STD_LOGIC_VECTOR(3 downto 0);
signal in_Select_tb : STD_LOGIC_VECTOR(2 downto 0);
signal Segments_tb : STD_LOGIC_VECTOR(1 to 7);
signal Overflow_tb, Zero_tb, lessThan_tb : STD_LOGIC;

begin
UUT : lab_FinalExam port map(
    in_A => in_A_tb,
    in_B => in_B_tb,
    in_Select => in_Select_tb,
    Segments => Segments_tb,
    Overflow => Overflow_tb,
    Zero => Zero_tb,
    lessThan => lessThan_tb);
-- Insert component instantiation (i.e. port map statement) here.
	simulation : process
	begin
	in_A_tb        <= "0000";
	in_B_tb        <= "0000";
	in_Select_tb   <= "000";
	for i in 0 to 4 loop
	   for j in 0 to 15 loop
	       for k in 0 to 15 loop
	           wait for 10ns;
	           in_A_tb <= in_A_tb + 1;
	       end loop;
           in_B_tb <= in_B_tb + 1;
	   end loop;
	   in_Select_tb <= in_Select_tb + 1;
	end loop;
	
	end process;
-- Insert test bench code here.
	
end behavior;
