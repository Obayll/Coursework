library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity mux_ALU is
  Port (sel : in  STD_LOGIC;
        A   : in  STD_LOGIC_VECTOR (31 downto 0);
        B   : in  STD_LOGIC_VECTOR (31 downto 0);
        X   : out STD_LOGIC_VECTOR (31 downto 0));
end mux_ALU;

architecture Behavioral of mux_ALU is
begin
    X <= A when (sel = '0') else B;
end Behavioral;