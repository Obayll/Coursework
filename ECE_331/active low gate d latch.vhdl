library ieee;
use ieee.std_logic_1164.all;

entity gatedDlatch is
port ( D, G : in STD_LOGIC;
	Q, Qnot : out STD_LOGIC );
end gatedDlatch;

architecture behavioral of gatedDlatch is
begin
	process(D, G)
	begin
		if (G = '0') then Q <= D;
		end if;
	end process;
	Qnot <= not(Q);
end behavioral;