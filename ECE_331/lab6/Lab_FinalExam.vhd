------------------------------------------------------------------------------------------------------
-- Course: ECE 332 - Digital System Design Lab
-- Author: 
-- 
-- Exam Date: 
-- Experiment Name: ECE 332 Final Exam
-- Design Name: Lab_FinalExam (VDHL source file)
-- Project Name: project_FinalExam - Behavioral
-- Target Devices: xc7a35t-1cpg236c
--
------------------------------------------------------------------------------------------------------


library IEEE;
-- Insert USE statements here.
use ieee.std_logic_1164.all;
use ieee.std_logic_signed.all;

entity lab_FinalExam is
port(
    in_A, in_B : in STD_LOGIC_VECTOR(3 downto 0);
    in_Select : in STD_LOGIC_VECTOR(2 downto 0);
    Segments : out STD_LOGIC_VECTOR(1 to 7);
    Overflow, Zero, lessThan : out STD_LOGIC;
    disp_left, disp_midleft, disp_midright, disp_right : out STD_LOGIC);
end lab_FinalExam;

architecture behavioral of lab_FinalExam is
Signal output : STD_LOGIC_VECTOR(4 downto 0);
begin
	with in_Select select output <=
	   ('0' & in_A) + ('0' & in_B)     when "000",
	   ('0' & in_A) - ('0' & in_B)     when "001",
	   '0' & (not(in_A))               when "010",
	   '0' & (in_A xor in_B)           when "011",
	   '0' & (in_A nor in_B)           when "100",
	   "00000"                         when others;
	process(in_Select)
	begin
	   if (in_Select = "000") then
	       if (in_A(3) /= in_B(3)) then
	           Overflow <= '1';
	       else Overflow <= '0';
	       end if;
	   elsif (in_Select = "001") then
	       if (in_A(3) = in_B(3)) then
	           Overflow <= '1';
           else Overflow <= '0';
           end if;
       end if;
	end process;
    Zero <= '1' when output = "00000" else '0';
    lessThan <= '1' when in_A < in_B else '0';
	-- Modify the code below to turn on the appropriate seven-segment display.
	-- Be sure to declare the signals in the entity statement.
	disp_left <= '1';				-- control the 'left' seven-segment display					
	disp_midleft <= '1';			-- control the 'middle-left' seven-segment display	
	disp_midright <= '1';			-- control the 'middle-right' seven-segment display	
	disp_right <= '0';				-- control the 'right' seven-segment display	

	-- Complete the code below for the Hex to Seven-segment converter.
	-- Be sure to declare the signals is the entity statement or in a signal declaration statement.
	with output(3 downto 0) select Segments <= 
	   "0000001" 	when "0000",  -- 0
	   "1001111"	when "0001",  -- 1
	   "0010010"	when "0010",  -- 2
	   "0000110"	when "0011",  -- 3
	   "1001100"	when "0100",  -- 4
	   "0100100"	when "0101",  -- 5
	   "0100000"	when "0110",  -- 6
	   "0001111"	when "0111",  -- 7
	   "0000000"	when "1000",  -- 8
	   "0000110"	when "1001",  -- 9
	   "0001000"	when "1010",  -- A
	   "1100000"	when "1011",  -- B
	   "0110001"	when "1100",  -- C
	   "1000010"	when "1101",  -- D
	   "0110000"	when "1110",  -- E
	   "0111000"	when "1111",  -- F					
	   "1111111"	when others;  -- default
end behavioral;
