library ieee;
use ieee.std_logic_1164.all;

entity penc8to3 is
port ( A : in STD_LOGIC_VECTOR(7 downto 0);
	Y : out STD_LOGIC_VECTOR(2 downto 0);
	Z : out STD_LOGIC);
end penc8to3;

architecture behavioral of penc8to is
begin
    Y <=
    "111" when A(7) = '1' else
    "110" when A(6) = '1' else
    "101" when A(5) = '1' else
    "100" when A(4) = '1' else
    "011" when A(3) = '1' else
    "010" when A(2) = '1' else
    "001" when A(1) = '1' else
    "000" when A(0) = '1' else
    "000";
	Z <= ‘0’ when A = “00000000” else ‘1’;
end behavioral;