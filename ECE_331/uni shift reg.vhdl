library ieee;
use ieee.std_logic_1164.all;

entity Uni_Shift is
port (
	S_In, Clk, : in STD_LOGIC;
	Mode : in STD_LOGIC_VECTOR(1 downto 0);
	Data : in STD_LOGIC_VECTOR(3 downto 0);
	Q : out STD_LOGIC_VECTOR(3 downto 0));
end Uni_Shift;

architecture behavioral of Uni_Shift is
signal temp : STD_LOGIC_VECTOR(3 downto 0);
begin
	process(Clk)
	begin
		if (Clk’event) and (Clk = ‘1’) then
			case Mode is
				when "00" => -- Hold
					null;
				when "01" => -- SL
					temp <= Data;
					temp(3 downto 1) <= temp(2 downto 0);
					temp(0) <= S_In;
				when "10" => -- SR
					temp <= Data;
					temp(2 downto 0) <= temp(3 downto 1);
					temp(3) <= S_In;
				when "11" => -- Parallel
					temp <= Data;
					Q < = temp;
				when others => null;
				end case;
		end if;
	end process;
end behavioral;