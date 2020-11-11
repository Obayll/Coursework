library ieee;
use ieee.std_logic_1164.all;

entity penc4to2 is
port ( A : in STD_LOGIC_VECTOR(0 to 3);
	Y : out STD_LOGIC_VECTOR(1 downto 0);
	Z : out STD_LOGIC);
end penc4to2;

architecture behavioral of penc4to2 is
begin
	Y <= “11” when A(3) = ‘1’ else
		“10” when A(2) = ‘1’ else
		“01” when A(1) = ‘1’ else
		“00” when A(0) = ‘1’ else
		“00”;
		Z <= ‘0’ when A = “0000” else ‘1’;
end behavioral;