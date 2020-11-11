library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;

entity fulladder is
    Port (
        A, B, C : in STD_LOGIC;
        S, Co : out STD_LOGIC
    );
end fulladder;

architecture Behavioral of fulladder is
signal CAT : STD_LOGIC_VECTOR(2 downto 0);
signal TAC : STD_LOGIC_VECTOR(1 downto 0);
begin
    CAT <= A & B & C;
    With CAT select TAC <= -- ("Co S")
        "00" when "000",
        "01" when "001",
        "01" when "010",
        "10" when "011",
        "01" when "100",
        "10" when "101",
        "10" when "110",
        "11" when "111",
        "00" when others;
    S <= TAC(0);
    Co <= TAC(1);
end Behavioral;
