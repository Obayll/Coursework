library ieee;
use ieee.std_logic_1164.all;

entity MooreFSM is
	port ( Clk, Reset : in STD_LOGIC;
		X : in STD_LOGIC;
		Y : out STD_LOGIC );
end MooreFSM;

architecture behavioral of MooreFSM is
type state_type is (S0, S1, S2, S3);
signal Qpresent, Qnext : state_type;
begin
	next_state: process(X, Qpresent)
	begin
		case Qpresent is
			when S0 =>
				if (X = ‘0’) then Qnext <= S0;
				else Qnext <= S1;
			when S1 =>
				if (X = ‘0’) then Qnext <= S0;
				else Qnext <= S2;
			when S2 =>
				if (X = ‘0’) then Qnext <= S3;
				else Qnext <= S2;
			when S3 =>
				if (X = ‘0’) then Qnext <= S0;
				else Qnext <= S1;
	end process;
	state_reg: process(Clk, Reset)
	begin
		if (Reset = ‘0’) then
		Qpresent <= S0;
		elsif (Clk’event and Clk = ‘1’) then
		Qpresent <= Qnext;
		end if;
	end process;
	Y <= ‘1’ when (Qpresent = S3) else ‘0’;
end behavioral;