------------------------------------------------------------------------------------------------------
-- Course: ECE 332 - Digital System Design Lab
-- Author: 
-- 
-- Create Date: 
-- Experiment Name: Modulo-100 Counter in VHDL (using Structural design) 
-- Design Name: Lab_9_OnetoTwoDecoder
-- Project Name: project_9 - Structural
-- Target Devices: xc7a35t-1cpg236c
--
-- Description: This is one of several components used in the design of the Modulo-100 Counter
--				This is the design of One-to-Two Decoder using two simple assignment statements.
--				This design requires a single buffer and a single inverter. 
-- 
-- Basys 3 Board resources used to test this component:
--				1 slider switch for the input to the decoder.
-- 				2 LEDs for the outputs from the decoder.
------------------------------------------------------------------------------------------------------

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity lab_9_OnetoTwoDecoder is
port(
    DEC_in : in STD_LOGIC;
    DEC_out0, DEC_out1 : out STD_LOGIC);
end lab_9_OnetoTwoDecoder;

architecture behavioral of lab_9_OnetoTwoDecoder is
signal DEC_out : STD_LOGIC_VECTOR(1 downto 0);
begin
    with DEC_in select DEC_out <=
        "01" when '0',
        "10" when '1',
        "00" when others; 
    
    DEC_out0 <= DEC_out(0);
    DEC_out1 <= DEC_out(1);
end behavioral;