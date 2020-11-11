library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;

entity signextend is
  Port (Extend_in: IN STD_logic_vector(15 downto 0);
		Extend_out: OUT STD_logic_vector(31 downto 0)
	);
end signextend;

architecture Behavioral of signextend is
begin
	Extend_out <= STD_LOGIC_VECTOR(resize(signed(Extend_in), Extend_out'length));
end Behavioral;