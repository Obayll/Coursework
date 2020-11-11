library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity onecomplement is
    Port (
        A : in STD_LOGIC_VECTOR(2 downto 0);
        B : out STD_LOGIC_VECTOR(2 downto 0)
    );
end onecomplement;

architecture Behavioral of onecomplement is

begin
    B(0) <= not(A(0));
    B(1) <= not(A(1));
    B(2) <= not(A(2));
end Behavioral;
