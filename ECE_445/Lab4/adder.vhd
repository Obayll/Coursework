library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;

entity adder is
  Port (num1: in  STD_LOGIC_VECTOR (31 downto 0);
        num2: in  STD_LOGIC_VECTOR (31 downto 0);
		result: out STD_LOGIC_VECTOR (31 downto 0));
end adder;

architecture Behavioral of adder is	
begin
	result <= STD_LOGIC_VECTOR(unsigned(num1) + unsigned(num2));
end Behavioral;