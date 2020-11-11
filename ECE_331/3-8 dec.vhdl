library ieee;
use ieee.std_logic_1164.all;

entity dec3to8 is
port ( A : in STD_LOGIC_VECTOR(2 downto 0);
	Y : out STD_LOGIC_VECTOR(7 downto 0));
end dec3to8;

architecture behavioral of dec3to8 is
begin
    with A select
        Y <= 
        "10000000" when "111",
        "01000000" when "110",
        "00100000" when "101",
        "00010000" when "100",
        "00001000" when "011",
        "00000100" when "010",
        "00000010" when "001",
        "00000001" when "000",
        "00000000" when others;
end behavioral;