------------------------------------------------------------------------------------------------------
-- Course: ECE 332 - Digital System Design Lab
-- Author: Jordan Ditzler
-- 
-- Create Date: 
-- Experiment Name: Multiplexers, Decoders, and Encoders in VHDL (Lab #7)
-- Design Name: Lab_7_2 (Decoder and Encoder)
-- Project Name: project_7 - Behavioral
-- Target Devices: xc7a35t-1cpg236c
--
-- Description:	Design a 3-to-8 Decoder with Enable and an 8-to-3 Priority Encoder using behavioral VHDL.
-- 				Use a 'with select when' statement to describe the decoder.
-- 				Use a 'when else' statement to describe the encoder.  Append a '0' to the 
--				3-bit encoder output and use the resulting 4-bit value as the input to the 
-- 				BCD to Seven-segment converter.  Use the valid bit (output from encoder) to
-- 				turn the seven-segment display on/off.
-- 
-- Basys 3 Board resources used for this design:
-- 				3 slider switches for the decoder inputs.
-- 				1 slider switch for the decoder enable.
-- 				8 slider switches for the encoder inputs.
-- 				8 LEDs for the decoder output.
-- 				1 Seven-segment display for the encoder output.
------------------------------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity lab_7_2 is
port( DEC_in: in STD_LOGIC_VECTOR(2 downto 0);
    DEC_enable: in STD_LOGIC;
    DEC_out: out STD_LOGIC_VECTOR(7 downto 0);
    ENC_in: in STD_LOGIC_VECTOR(7 downto 0);
    Segments: out STD_LOGIC_VECTOR(1 to 7);
    disp_left, disp_midleft, disp_right, disp_midright : out STD_LOGIC);
end lab_7_2;

architecture behavioral of lab_7_2 is
signal EnA: STD_LOGIC_VECTOR(3 downto 0);
signal ENC_out: STD_LOGIC_VECTOR(2 downto 0);
begin
    -- Decoder
    EnA <= DEC_enable & DEC_in;
    with EnA select
        DEC_out <= 
        "10000000" when "1111",
        "01000000" when "1110",
        "00100000" when "1101",
        "00010000" when "1100",
        "00001000" when "1011",
        "00000100" when "1010",
        "00000010" when "1001",
        "00000001" when "1000",
        "00000000" when others;
    -- Encoder    
    ENC_out <=
    "111" when ENC_in(7) = '1' else
    "110" when ENC_in(6) = '1' else
    "101" when ENC_in(5) = '1' else
    "100" when ENC_in(4) = '1' else
    "011" when ENC_in(3) = '1' else
    "010" when ENC_in(2) = '1' else
    "001" when ENC_in(1) = '1' else
    "000" when ENC_in(0) = '1' else
    "000";
    
    with ENC_out select 
    Segments <= "0000001" when "000",
                "1001111" when "001",
                "0010010" when "010",
                "0000110" when "011",
                "1001100" when "100",
                "0100100" when "101",
                "0100000" when "110",
                "0001111" when "111",  
                "1111111" when others;
	disp_left <= '1';				
	disp_midleft <= '1';
	disp_midright <= '1';
	disp_right <= '0';
end behavioral;
