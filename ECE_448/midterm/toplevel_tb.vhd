library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;

entity toplevel_tb is
end toplevel_tb;

architecture Behavioral of toplevel_tb is
component toplevel is
    Port (
        CLK : in STD_LOGIC;
        RESET, START : in STD_LOGIC;
        READY : out STD_LOGIC;
        OUTPUT : out STD_LOGIC_VECTOR(7 downto 0);
        MODE : out STD_LOGIC_VECTOR(1 downto 0)
    );
end component;
signal CLK, RESET, START, READY :  STD_LOGIC;
signal OUTPUT : STD_LOGIC_VECTOR(7 downto 0);
signal MODE : STD_LOGIC_VECTOR(1 downto 0);
begin
UUT : toplevel port map(
    CLK => CLK,
    RESET => RESET,
    READY => READY,
    START => START,
    OUTPUT => OUTPUT,
    MODE => MODE
    );
simulation : process
begin
    RESET <= '0';
    CLK <= '1';
    wait for 5ns;
    CLK <= '0';
    wait for 5ns;
end process;
end Behavioral;
