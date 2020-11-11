library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity mux_writereg is
  Port (sel : in  STD_LOGIC;
        A   : in  STD_LOGIC_VECTOR (4 downto 0);
        B   : in  STD_LOGIC_VECTOR (4 downto 0);
        X   : out STD_LOGIC_VECTOR (4 downto 0));
end mux_writereg;

architecture Behavioral of mux_writereg is
begin
    X <= A when (sel = '0') else B;
end Behavioral;