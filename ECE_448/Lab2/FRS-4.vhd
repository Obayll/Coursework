
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity FRS4 is
   Port ( X : in STD_LOGIC_VECTOR ( 7 downto 0 );
    OP : in STD_LOGIC_VECTOR ( 1 downto 0 );
    y : out STD_LOGIC_VECTOR ( 7 downto 0 ));
end FRS4;

architecture Behavioral of FRS4 is

begin
WITH OP SELECT
    Y <=  x(3 downto 0) & x(7 downto 4) when  "00",
           x(3 downto 0) & x(7 downto 4) when "01",
           x(7)& x(7)& x(7)& x(7) & x(7 downto 4) when "10",
           x(3 downto 0) & "0000" when "11",
           "11111111" when others;

end Behavioral;
