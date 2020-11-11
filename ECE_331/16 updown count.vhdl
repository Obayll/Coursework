library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;

entity up_down_16bit_counter is
port (
	C_out : out std_logic_vector (15 downto 0);
	Mode : in  std_logic;
	Clk : in  std_logic;
	Reset : in  std_logic
);
end entity;

architecture behavioral of up_down_16bit_counter is
signal Count :std_logic_vector (15 downto 0);
begin
	process (Clk, Reset) begin
		if (Reset = '1') then
			Count <= "0000000000000000";
		elsif (rising_edge(Clk)) then
			if (Mode = '1') then
				Count <= Count + 1;
			else
				Count <= Count - 1;
			end if;
		end if;
	end process;
	C_out <= Count;
end behavioral;