library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity cntl is
  Port (Cntl_in: in STD_LOGIC_VECTOR(5 downto 0);
        Cntl_out: out STD_LOGIC);
end cntl;

architecture Behavioral of cntl is
begin
    with Cntl_in select Cntl_out <=
        '1' when "000000",
        '0' when others;
end Behavioral;