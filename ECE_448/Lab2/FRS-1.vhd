
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;



entity FRS1 is
    Port ( X : in STD_LOGIC_VECTOR ( 7 downto 0 );
    OP : in STD_LOGIC_VECTOR ( 1 downto 0 );
    y : out STD_LOGIC_VECTOR ( 7 downto 0 ));
end FRS1;

architecture Behavioral of FRS1 is

begin
WITH OP SELECT
    y <=  x(0) & x(7 downto 1) when  "00",
           x(6 downto 0) & x(7) when "01",
           x(7)& x(7 downto 1) when "10",
           x(6 downto 0) & '0' when "11",
           "11111111" when others;

end Behavioral;
