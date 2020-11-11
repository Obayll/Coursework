library IEEE;
use ieee.std_logic_1164.all;

entity hw_fsm is
	port(Clk, X , Reset: in std_logic;
		Z : out std_logic);
end hw_fsm;

architecture behavioral of hw_fsm is
type state_type is (S0, S1, S2, S3);
signal Qpres, Qnext : state_type;
begin
	next_state: process(X, Qpres)
	begin
		case Qpres is
			when S0 =>
				if (X = '0') then Qnext <= S1;
				else Qnext <= S0;
				end if;
			when S1 =>
				if (X = '0') then Qnext <= S1;
				else Qnext <= S2;
				end if;
			when S2 =>
				if (X = '0') then Qnext <= S3;
				else Qnext <= S0;
				end if;
			when S3 =>
				if (X = '0') then Qnext <= S1;
				else Qnext <= S2;
				end if;
	end process;
	state_reg: process(Clk)
	begin
		if (Reset = '0') then
			Qpres <= S0;
		elsif rising_edge(Clk) then Qpres <= Qnext;
		end if;
	end process;
	Z <= '1' when (Qpres = S2 and X = '1') else '0';
end behavioral;