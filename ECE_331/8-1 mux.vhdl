library ieee;
use ieee.std_logic_1164.all;

entity mux8to1 is
port ( D : in STD_LOGIC_VECTOR(0 to 7);
	S : in STD_LOGIC_VECTOR(2 downto 0);
	F : out STD_LOGIC );
end mux8to1;

architecture behavioral of mux8to1 is
begin
	with S select
		F <=
		D(0) when “000”,
		D(1) when “001”,
		D(2) when “010”,
		D(3) when “011”,
		D(4) when “100”,
		D(5) when “101”,
		D(6) when “110”,
		D(7) when “111”,
		‘0’ when others;
end behavioral;