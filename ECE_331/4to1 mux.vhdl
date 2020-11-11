library ieee;
use ieee.std_logic_1164.all;

entity mux4to1 is
port ( D : in STD_LOGIC_VECTOR(0 to 3);
	S : in STD_LOGIC_VECTOR(1 downto 0);
	F : out STD_LOGIC );
end mux4to1;

architecture behavioral of mux4to1 is
begin
	with S select
	F <= D(0) when “00”,
	D(1) when “01”,
	D(2) when “10”,
	D(3) when “11”,
	‘0’ when others;
end behavioral;