------------------------------------------------------------------------------------------------------
-- Course: ECE 332 - Digital System Design Lab
-- Author: 
-- 
-- Create Date: 
-- Experiment Name: Arithmetic Circuits in VHDL (Lab #6)
-- Design Name: Lab_6_1
-- Project Name: project_6 - Behavioral
-- Target Devices: xc7a35t-1cpg236c
--
-- Description:	Design a Full Adder using behavioral VHDL.
--				Assign a Boolean expression to each output (Sum and Cout).
-- 
-- Basys 3 Board resources used for this design:
-- 				3 slider switches for FA inputs (X, Y, Cin).
-- 				2 LEDs for FA outputs (Sum, Cout).
------------------------------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity lab_6_1 is
Port(
    A, B, Cin : in STD_LOGIC;
    Sum, Cout : out STD_LOGIC);
end lab_6_1;


architecture behavioral of lab_6_1 is
begin
    Sum <= A xor B xor Cin;
    Cout <= (A and B) or (A and Cin) or (B and Cin);
end behavioral;
