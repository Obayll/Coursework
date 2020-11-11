library ieee;
use ieee.std_logic_1164.all;

entity Dflipflop is
port ( D, Clk : in STD_LOGIC;
	Q, Qnot : out STD_LOGIC );
end Dflipflop;

architecture behavioral of Dflipflop is
begin
	process(Clk)
	begin
		if (Clk’event) and (Clk = '1') then Q <= D;
		end if;
	end process;
	Qnot <= not(Q);
end behavioral;