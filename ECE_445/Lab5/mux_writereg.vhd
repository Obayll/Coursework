library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity mux_writereg is
  Port (sel : in  STD_LOGIC;
        A   : in  STD_LOGIC_VECTOR (4 downto 0);
        B   : in  STD_LOGIC_VECTOR (4 downto 0);
        X   : out STD_LOGIC_VECTOR (4 downto 0);
        JAL : in STD_LOGIC);
end mux_writereg;

architecture Behavioral of mux_writereg is
signal C: STD_LOGIC_VECTOR (4 downto 0) := "11111";
begin
    X <= C when (JAL = '1') else A when (sel = '0') else B;
    --X <= A when (sel = '0') else B;
end Behavioral;