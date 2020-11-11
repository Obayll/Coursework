library ieee;
use ieee.std_logic_1164.all;

entity Tflipflop is
port ( T, Clk : in STD_LOGIC;
	Q, Qnot : out STD_LOGIC );
end Tflipflop;

architecture behavioral of Tflipflop is
signal Qint : std_logic;
begin
	Q <= Qint;
	process(Clk)
	begin
		if (Clk’event) and (Clk = '0') then
		Qint <= T xor Qint;
		end if;
	end process;
end behavioral;