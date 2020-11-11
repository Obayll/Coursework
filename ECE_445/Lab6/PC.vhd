library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity PC is
	Port(reset: in std_logic;
		 clock: in std_logic;
		 Din: in std_logic_vector(31 downto 0);
		 Dout: out std_logic_vector(31 downto 0);
		 pcout: out std_logic_vector(4 downto 0) := (others=>'0')
		 );
end PC;

architecture Behavioral of PC is

begin
process(clock)
	begin
		if reset = '1' then Dout <= x"00000000";
		elsif(clock'event and clock = '1') then
			Dout <= Din;
			pcout <= Din(6 downto 2);
		end if;

end process;

end Behavioral;