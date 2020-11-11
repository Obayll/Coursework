library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity SignExtend is
    Port (SignIn : in  STD_LOGIC_VECTOR(15 downto 0);
          Extension : out  STD_LOGIC_VECTOR(31 downto 0));
end SignExtend;

architecture Behavioral of SignExtend is

signal SignExt : STD_LOGIC_VECTOR(15 downto 0);

begin
	with SignIn(15) select
		SignExt <= "0000000000000000" when '0',
		           "1111111111111111" when '1',
		           "1010101010101010" when others;
Extension <= SignExt & SignIn;
end Behavioral;