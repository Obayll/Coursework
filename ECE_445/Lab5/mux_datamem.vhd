library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity mux_datamem is
  Port (sel : in  STD_LOGIC;
        A   : in  STD_LOGIC_VECTOR (31 downto 0);
        B   : in  STD_LOGIC_VECTOR (31 downto 0);
        C   : in  STD_LOGIC_VECTOR (31 downto 0);
        X   : out STD_LOGIC_VECTOR (31 downto 0);
        JAL : in STD_LOGIC);
end mux_datamem;

architecture Behavioral of mux_datamem is
begin
    X <= C when (JAL = '1') else A when (sel = '0') else B;
    --X <= A when (sel = '0') else B;
end Behavioral;