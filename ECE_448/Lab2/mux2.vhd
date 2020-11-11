


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;


entity mux2 is
    Port ( in0 : in STD_LOGIC_VECTOR ( 7 downto 0 );
        in1 : in STD_LOGIC_VECTOR ( 7 downto 0 );
        s : in STD_LOGIC ;
        out1 : out STD_LOGIC_VECTOR ( 7 downto 0 ));
end mux2;

architecture Behavioral of mux2 is

begin
out1 <= in0 WHEN s = '0' ELSE  in1;

end Behavioral;
