library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity LeftShift is
	Port(ShiftIn: in STD_LOGIC_VECTOR(31 downto 0);
		 ShiftOut: out STD_LOGIC_VECTOR(31 downto 0));
end LeftShift;

architecture Behavioral of LeftShift is
begin
	Shiftout <= ShiftIn(29 downto 0) & "00";
end Behavioral;