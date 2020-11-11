library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;

entity shiftleft is
  port (Shift_in: in STD_LOGIC_VECTOR(31 downto 0);
        Shift_out: out STD_LOGIC_VECTOR(31 downto 0)
	);
end shiftleft;

architecture Behavioral OF shiftLeft IS
begin
    Shift_out <=  STD_LOGIC_VECTOR(unsigned(Shift_in) sll 2);
end Behavioral;