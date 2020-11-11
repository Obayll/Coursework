library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity J_Mux is
    PORT(D0 : in STD_LOGIC_VECTOR(31 downto 0);
		 D1 : in STD_LOGIC_VECTOR(31 downto 0);
		 D2 : in STD_LOGIC_VECTOR(31 downto 0);
		 Control : in STD_LOGIC_VECTOR(1 downto 0);
		 JROut : out STD_LOGIC_VECTOR(31 downto 0));
end J_Mux;

architecture Behavioral of J_Mux is
begin
	with Control select
	JRout <= D0 when "00",
			 D1 when "01",
			 D2 when "10",
			 D0 when others;
end Behavioral;