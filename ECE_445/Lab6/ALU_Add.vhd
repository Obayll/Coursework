library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.NUMERIC_STD.ALL;

entity ALU_Add is
Port (A,B : in STD_LOGIC_VECTOR(31 downto 0);
	  ALU_ADDout : out STD_LOGIC_VECTOR(31 downto 0));
end ALU_Add;

architecture Behavioral of ALU_Add is
signal a32, b32, add_result : STD_LOGIC_VECTOR(32 downto 0);
begin
   a32 <= '0' & A;
   b32 <= '0' & B;
   add_result <= a32 + b32;
   ALU_ADDout <= add_result(31 downto 0);
end Behavioral;