library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity test_4 is
port ( A, B : in STD_LOGIC_VECTOR(3 downto 0);
    greater, lesser, equal : out std_logic);
end test_4;

architecture behavioral of test_4 is
begin
	greater <= '1' when (A > B) else '0';
	lesser <= '1' when (A < B) else '0';
	equal <= '1' when (A = B) else '0';
end behavioral;