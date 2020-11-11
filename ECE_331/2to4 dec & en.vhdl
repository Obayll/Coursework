library ieee;
use ieee.std_logic_1164.all;

entity dec2to4wEN is
port ( A : in STD_LOGIC_VECTOR(1 downto 0);
	EN : in STD_LOGIC;
	Y : out STD_LOGIC_VECTOR(0 to 3));
end dec2to4wEN;

architecture behavioral of dec2to4 is
signal EnA : STD_LOGIC_VECTOR(2 downto 0);
begin
	EnA <= EN & A;
	with EnA select
		Y <= “1000” when “100”,
		“0100” when “101”,
		“0010” when “110”,
		“0001” when “111”,
		“0000” when others;
end behavioral;