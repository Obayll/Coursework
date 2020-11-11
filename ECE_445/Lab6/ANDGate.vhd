library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity ANDGate is
    Port (A : in STD_LOGIC;
          B : in STD_LOGIC;
          AndOut : out STD_LOGIC);
end ANDGate;

architecture Behavioral of ANDGate is
begin
    AndOut <= A AND B;
end Behavioral;