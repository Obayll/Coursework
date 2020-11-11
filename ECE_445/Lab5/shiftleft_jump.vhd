library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;

entity shiftleft_jump is
  port (Shift_in: in STD_LOGIC_VECTOR(25 downto 0);
        Shift_out: out STD_LOGIC_VECTOR(27 downto 0)
	);
end shiftleft_jump;

architecture Behavioral OF shiftleft_jump IS
signal A, B: STD_LOGIC_VECTOR(27 downto 0);
begin
    A <= "00" & Shift_in;
    Shift_out <=  STD_LOGIC_VECTOR(unsigned(A) sll 2);
end Behavioral;