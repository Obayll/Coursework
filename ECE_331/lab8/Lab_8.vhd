------------------------------------------------------------------------------------------------------
-- Course: ECE 332 - Digital System Design Lab
-- Author: 
-- 
-- Create Date:  
-- Experiment Name: Arithmetic and Logic Unit in VHDL (Lab #8)
-- Design Name: Lab_8
-- Project Name: project_8 - Behavioral
-- Target Devices: xc7a35t-1cpg236c
--
-- Description:	Design a 4-bit Arithmetic and Logic Unit (ALU) using behavioral VHDL.
-- 				The ALU should implement the following operations:
-- 					Buffer(A), AND, OR, NOT(A), NAND, NOR, XOR, XNOR
-- 					Addition, Subtraction
-- 					Left-shift(A), Logical Right-shift(A), Arithmetic Right-shift(A) 
-- 				The ALU should generate the following status flags:
-- 					Zero 		(Z = 1 when result of operation is zero)
-- 					Negative 	(N = 1 when result of operation is negative)
-- 					Carry		(C = 1 when there is a carry-out from the operation)
-- 					Overflow	(V = 1 when overflow occurs)
-- 				Use a 'with select when' statement, as well as arithmetic and logic operators 
-- 				to describe the 4-bit ALU.  Use Boolean expressions to describe the flags (if necessary).
-- 				The output of the ALU will be displayed on a seven-segment display.  It will be displayed
-- 				as a hexadecimal digit (0 - 9, A - F).
--
-- Basys 3 Board resources used for this design:
-- 				8 slider switches for the data inputs (A and B) to the 4-bit ALU.
-- 				4 slider switches for the control inputs to the ALU (to select an operation).
-- 				1 seven-segment display to display the output from the ALU.
-- 				4 LEDs to display the status flags (Z, N, C, V)			
------------------------------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_SIGNED.ALL;

entity lab_8 is
port (
    ALU_inA: in STD_LOGIC_VECTOR(3 downto 0);
    ALU_inB: in STD_LOGIC_VECTOR(3 downto 0);
    ALU_inSelect: in STD_LOGIC_VECTOR(3 downto 0);
    Zero: out STD_LOGIC;
    Negative: out STD_LOGIC;
    Carry: out STD_LOGIC;
    Overflow: out STD_LOGIC;
    Segments: out STD_LOGIC_VECTOR(7 downto 0));
end lab_8;

architecture behavioral of lab_8 is
signal result: STD_LOGIC_VECTOR(4 downto 0);
signal tempsum: STD_LOGIC_VECTOR(4 downto 0);
signal tempdif: STD_LOGIC_VECTOR(4 downto 0);
begin
    with ALU_inSelect select result <= 
        '0' & ALU_inA                             when "0000",
        '0' & not ALU_inA                         when "0001",
        '0' & ALU_inA nand ALU_inB                when "0010",
        '0' & ALU_inA or ALU_inB                  when "0011",
        '0' & ALU_inA nor ALU_inB                 when "0100",
        '0' & ALU_inA xor ALU_inB                 when "0101",
        '0' & ALU_inA xnor ALU_inB                when "0111",
        tempsum                                   when "1000",
        tempdif					                  when "1001",
        '0' & ALU_inA(3 downto 1) & '0'           when "1010",
        '0' & ALU_inA(3 downto 1) & ALU_inA(0)    when "1011",
        '0' & ALU_inA(3 downto 1) & '0'           when "1100",
        "00000"                                   when others;
        
    Zero <= '1' when result(3 downto 0) = "0000" else '0';
    Negative <= '1' when result(3) = '1' else '0';
    Carry <= '1' when tempsum(4) = '1' else '0';
    Overflow <= '1' when ALU_inA(3) /= ALU_inB(3) else '0';
    tempsum <= ('0' & ALU_inA) + ('0' & ALU_inB);
	tempdif <= ('0' & ALU_inA) - ('0' & ALU_inB);
    
    with result select Segments <= 
        "0000001" when "00000",
        "1001111" when "00001",
        "0010010" when "00010",
        "0000110" when "00011",
        "1001100" when "00100",
        "0100100" when "00101",
        "0100000" when "00110",
        "0001111" when "00111",
        "0000000" when "01000",
        "0000100" when "01001",
        "0001000" when "01010",
        "1100000" when "01011",
        "0110001" when "01100",
        "1000010" when "01101",
        "0110000" when "01110",
        "0111000" when "01111",   
        "1111111" when others;
						
	--disp_left <= '1';		-- turn the seven-segment display off					
	--disp_midleft <= '1';	-- turn the seven-segment display off 
	--disp_midright <= '1';	-- turn the seven-segment display off
	--disp_right <= '0';		-- turn the seven-segment display on

end behavioral;
