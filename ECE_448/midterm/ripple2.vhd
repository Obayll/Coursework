library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity ripple2 is
    Port (
        A, B : in STD_LOGIC_VECTOR(1 downto 0);
        C : out STD_LOGIC_VECTOR(2 downto 0)
    );
end ripple2;

architecture Behavioral of ripple2 is
signal S1, S2, C1, C2 : STD_LOGIC;
begin
    FullAdder1 : entity work.fulladder(Behavioral)
    Port map (
        A => A(0),
        B => B(0),
        C => '0',
        S => S1,
        Co => C1
    );
    FullAdder2 : entity work.fulladder(Behavioral)
    Port map (
        A => A(1),
        B => B(1),
        C => C1,
        S => S2,
        Co => C2
    );
    C <= C2 & S2 & S1;
end Behavioral;
