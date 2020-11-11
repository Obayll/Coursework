library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity datapath_tb is
end datapath_tb;

architecture Behavioral of datapath_tb is

component datapath_wrapper
  Port (clock: in std_logic;
        reset: in std_logic;
        Dout: out std_logic_vector(31 downto 0));
end component;
signal test_reset : std_logic;
signal test_clock : std_logic;
signal test_Dout : std_logic_vector(31 downto 0);
begin
UUT: datapath_wrapper
	port map (
	clock => test_clock,
	reset => test_reset,
	Dout => test_Dout);
	
clock_process: process
begin
    test_clock <= '0';
    wait for 10 ns;
    test_clock <= '1';
    wait for 10 ns;
end process;

simulation: process
begin
    test_reset <= '0';
    wait for 20 ns;
    --test_reset <= '1';
    --wait for 20 ns;
end process;
end Behavioral;