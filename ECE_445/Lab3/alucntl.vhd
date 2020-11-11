library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity alucntl is
  Port (AluCntl_in: in STD_LOGIC_VECTOR(5 downto 0);
        AluCntl_out: out STD_LOGIC_VECTOR(3 downto 0));
end alucntl;

architecture Behavioral of alucntl is
begin
    with AluCntl_in select AluCntl_out <=
        "0010" when "100000", -- add    x"20"
        "0010" when "100001", -- addu   x"21"
        "0110" when "100010", -- sub    x"22"
        "0110" when "100011", -- subu   x"23"
        "0000" when "100100", -- and    x"24"
        "0001" when "100101", -- or     x"25"
        "0011" when "100110", -- xor    x"26"
        "1100" when "100111", -- nor    x"27"
        "0111" when "101010", -- slt    x"2a"
        "0111" when "101011", -- slt(u) x"2b"
        "1111" when others;
end Behavioral;