library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity pcadd is
  Port (PCadd_in : in STD_LOGIC_VECTOR(31 downto 0);
        PCadd_out : out STD_LOGIC_VECTOR(31 downto 0));
end pcadd;

architecture Behavioral of pcadd is
begin
    PCadd_out <= PCadd_in + 4;
end Behavioral;
