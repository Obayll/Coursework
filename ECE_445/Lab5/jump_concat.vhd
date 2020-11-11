library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;

entity jump_concat is
  Port (num1: in  STD_LOGIC_VECTOR (27 downto 0);
        num2: in  STD_LOGIC_VECTOR (3 downto 0);
		result: out STD_LOGIC_VECTOR (31 downto 0));
end jump_concat;

architecture Behavioral of jump_concat is	
begin
	result <= num2 & num1;
end Behavioral;