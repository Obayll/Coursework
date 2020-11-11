library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity Mux32 is
    Port(A : in STD_LOGIC_VECTOR(31 downto 0);
	     B : in STD_LOGIC_VECTOR(31 downto 0);
	     Control : in STD_LOGIC;
	     Mux32Out : out STD_LOGIC_VECTOR(31 downto 0));
end Mux32;

architecture Behavioral of Mux32 is
begin
	with Control select
	Mux32Out <= A when '0',
			    B when '1',
			    "00000000000000000000000000000000" when others;
end Behavioral;