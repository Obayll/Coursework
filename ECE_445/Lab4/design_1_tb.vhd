library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity design_1_tb is
end design_1_tb;

architecture Behavioral of design_1_tb is

component design_1_wrapper
  Port (clock: in std_logic;
        reset: in std_logic;
        Zero : out std_logic;
        Carryout : out std_logic;
        Dout: out std_logic_vector(31 downto 0);
        pcout: out std_logic_vector(4 downto 0);
        AluCntl_out: out std_logic_vector(3 downto 0);
        loadout: out std_logic_vector(31 downto 0);
        storeout: out std_logic_vector(31 downto 0)
        );
end component;
signal test_reset: std_logic;
signal test_clock: std_logic;
signal test_Zero: std_logic;
signal test_Carryout : std_logic;
signal test_Dout: std_logic_vector(31 downto 0);
signal test_pcout: std_logic_vector(4 downto 0);
signal test_AluCntl_out: std_logic_vector(3 downto 0);
signal test_loadout: std_logic_vector(31 downto 0);
signal test_storeout: std_logic_vector(31 downto 0);
begin
UUT: design_1_wrapper
	port map (
	clock => test_clock,
	reset => test_reset,
	Zero => test_Zero,
	Carryout => test_Carryout,
	Dout => test_Dout,
    pcout => test_pcout,
    AluCntl_out => test_AluCntl_out,
    loadout => test_loadout,
    storeout => test_storeout
    );
	
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