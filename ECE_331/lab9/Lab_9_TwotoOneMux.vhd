------------------------------------------------------------------------------------------------------
-- Course: ECE 332 - Digital System Design Lab
-- Author: 
-- 
-- Create Date: 
-- Experiment Name: Modulo-100 Counter in VHDL (using Structural design) 
-- Design Name: Lab_9_TwotoOneMux
-- Project Name: project_9 - Structural
-- Target Devices: xc7a35t-1cpg236c
--
-- Description: This is one of several components used in the design of the Modulo-100 Counter
--				This is the design of the 4-bit Two-to-One Multiplexer using a 'with select when' statement.
--				This design is based on the design for the 4-bit Four-to-One Mux from Lab #7. 
-- 
-- Basys 3 Board resources used to test this component:
-- 				8 slider switches for the data inputs to the multiplexer (4 each for input A and input B).
--				1 slider switch for the select input to the multiplexer.
------------------------------------------------------------------------------------------------------

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity lab_9_TwotoOneMux is
port(
    Mux_select : in STD_LOGIC;
    MUX_data_ina, MUX_data_inb: in STD_LOGIC_VECTOR(3 downto 0);
    MUX_out : out STD_LOGIC_VECTOR(3 downto 0));
end lab_9_TwotoOneMux;

architecture behavioral of lab_9_TwotoOneMux is
begin
    with MUX_select select MUX_out <=
        Mux_data_ina when '0',
        Mux_data_inb when '1',
        "0000" when others;
end behavioral;