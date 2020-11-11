library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity reg is
    Generic (
        K : integer := 8
    );
    Port (
        D : in STD_LOGIC_VECTOR(k-1 downto 0);
        CLK : in STD_LOGIC;
        EN : in STD_LOGIC;
        SET : in STD_LOGIC;
        Q : out STD_LOGIC_VECTOR(k-1 downto 0)
    );
end reg;

architecture Behavioral of reg is

begin
    process(CLK)
    begin
        if rising_edge(CLK) then
            if EN = '1' then
                Q <= D;
            elsif SET = '1' then
                Q <= "00000001";
            end if;
        end if;
    end process;
end Behavioral;
