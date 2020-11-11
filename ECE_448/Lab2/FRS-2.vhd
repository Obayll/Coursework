

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity FRS2 is
    Port ( X : in STD_LOGIC_VECTOR ( 7 downto 0 );
    OP : in STD_LOGIC_VECTOR ( 1 downto 0 );
    y : out STD_LOGIC_VECTOR ( 7 downto 0 ));
end FRS2;

architecture Behavioral of FRS2 is

begin
WITH OP SELECT
    y <=  x(1 downto 0) & x(7 downto 2) when  "00",
           x(5 downto 0) & x(7 downto 6 ) when "01",
           x(7)& x(7) &x(7 downto 2) when "10",
           x(5 downto 0) & "00" when "11",
           "11111111" when others;

end Behavioral;
