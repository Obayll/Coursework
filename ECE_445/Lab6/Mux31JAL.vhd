library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity Mux31JAL is
	PORT(D0: in STD_LOGIC_VECTOR(4 downto 0);
		 D1: in STD_LOGIC_VECTOR(4 downto 0);
		 RegDst : in STD_LOGIC_VECTOR(1 downto 0);
		 Dout : out STD_LOGIC_VECTOR(4 downto 0));

end Mux31JAL;

architecture Behavioral of Mux31JAL is
begin
	with RegDst select
		Dout <= D0 when "00",
				D1 when "01",
				"11111" when "10",
				"00000" when others;
end Behavioral;