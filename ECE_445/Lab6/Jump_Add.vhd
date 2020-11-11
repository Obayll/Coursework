library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity Jump_Add is
	Port(In28: in STD_LOGIC_VECTOR(27 downto 0);
		 In4: in STD_LOGIC_VECTOR(3 downto 0);
		 JAddress: out STD_LOGIC_VECTOR(31 downto 0));
end Jump_Add;

architecture Behavioral of Jump_Add is
begin
	JAddress <= In4 & In28;
end Behavioral;