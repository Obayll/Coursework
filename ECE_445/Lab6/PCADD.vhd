library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use IEEE.NUMERIC_STD.ALL;

entity PCADD is
	Port( Din : in STD_LOGIC_VECTOR(31 downto 0);
	      Dout: out STD_LOGIC_VECTOR(31 downto 0));
end PCADD;

architecture Behavioral of PCADD is
begin
	Dout <= Din + "00000000000000000000000000000100"; --Increment the PC by 4 to move on to the next instruction
end Behavioral;