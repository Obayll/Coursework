library IEEE;
use ieee.std_logic_1164.all;

entity hw_fsm is
	port(Clk, X , Reset: in std_logic;
		Z : out std_logic);
end hw_fsm;

architecture behavioral of hw_fsm is
type state_type is (S0, S1, S2, S3, S4);
signal Qpres, Qnext : state_type;
begin
	next_state: process(X, Qpres)
	begin
		case Qpres is
			when S0 =>
				if (X = '0') then Qnext <= S0;
				else Qnext <= S1;
				end if;
			when S1 =>
				if (X = '0') then Qnext <= S2;
				else Qnext <= S1;
				end if;
			when S2 =>
				if (X = '0') then Qnext <= S0;
				else Qnext <= S1;
				end if;
			when S3 =>
				if (X = '0') then Qnext <= S0;
				else Qnext <= S4;
				end if;
			when S4 =>
				if (X = '0') then Qnext <= S2;
				else Qnext <= S1;
				end if;
	end process;
	state_reg: process(Clk)
	begin
		if (Reset = '0') then
			Qpres <= S0;
		elsif rising_edge(Clk) then Qpres <= Qnext;
		end if;
	end process;
	Z <= '1' when (Qpres = S3) else '0';
end behavioral;	