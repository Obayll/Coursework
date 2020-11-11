library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity mux_jump is
  Port (sel : in  STD_LOGIC;
        A   : in  STD_LOGIC_VECTOR (31 downto 0);
        B   : in  STD_LOGIC_VECTOR (31 downto 0);
        C   : in  STD_LOGIC_VECTOR (31 downto 0);
        X   : out STD_LOGIC_VECTOR (31 downto 0) := (others => '0');
        JR  : in  STD_LOGIC);
end mux_jump;

architecture Behavioral of mux_jump is
begin
    X <= C when (JR = '1') else A when (sel = '0') else B;
    --X <= A when (sel = '0') else B;
end Behavioral;