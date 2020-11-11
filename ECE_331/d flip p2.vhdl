library ieee;
use ieee.std_logic_1164.all;

entity Dflipflop is
port ( D, Clk, Preset : in STD_LOGIC;
	Q, Qnot : out STD_LOGIC );
end Dflipflop;

architecture behavioral of Dflipflop is
begin
	process(Clk)
	begin
		if (Preset = '0') then Q <= '1';
		elseif (Clk’event) and (Clk = '1') then Q <= D;
		end if;
	end process;
	Qnot <= not(Q);
end behavioral;