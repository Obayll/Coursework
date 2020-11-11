library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity LeftShiftJump is
	PORT(InstIn: in STD_LOGIC_VECTOR(25 downto 0);
		 Jout: out STD_LOGIC_VECTOR(27 downto 0));
end LeftShiftJump;

architecture Behavioral of LeftShiftJump is
begin
	Jout <= InstIn & "00";
end Behavioral;