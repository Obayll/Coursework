

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;
use ieee.std_logic_textio.all;
library std;
use std.textio.all;


entity CVRS is
    Port ( A : in STD_LOGIC_VECTOR ( 7 downto 0 );
    B : in STD_LOGIC_VECTOR ( 7 downto 0 );
    OP : in STD_LOGIC_VECTOR ( 1 downto 0 );
    C : out STD_LOGIC_VECTOR ( 7 downto 0 ));
end CVRS;

architecture Behavioral of CVRS is
SIGNAL l1 : STD_LOGIC_VECTOR(7 DOWNTO 0);
SIGNAL l2 : STD_LOGIC_VECTOR(7 DOWNTO 0);
SIGNAL l3 : STD_LOGIC_VECTOR(7 DOWNTO 0);
SIGNAL l4 : STD_LOGIC_VECTOR(7 DOWNTO 0);
SIGNAL l5 : STD_LOGIC_VECTOR(7 DOWNTO 0);
SIGNAL l6 : STD_LOGIC_VECTOR(7 DOWNTO 0);
SIGNAL l7 : STD_LOGIC_VECTOR(7 DOWNTO 0);
SIGNAL in8 : STD_LOGIC;
component mux2
    Port ( in0 : in STD_LOGIC_VECTOR ( 7 downto 0 );
        in1 : in STD_LOGIC_VECTOR ( 7 downto 0 );
        s : in STD_LOGIC ;
        out1 : out STD_LOGIC_VECTOR ( 7 downto 0 ));
end component;
    
component FRS1
    Port ( X : in STD_LOGIC_VECTOR ( 7 downto 0 );
    OP : in STD_LOGIC_VECTOR ( 1 downto 0 );
    y : out STD_LOGIC_VECTOR ( 7 downto 0 ));
end component;

component FRS2
    Port ( X : in STD_LOGIC_VECTOR ( 7 downto 0 );
    OP : in STD_LOGIC_VECTOR ( 1 downto 0 );
    y : out STD_LOGIC_VECTOR ( 7 downto 0 ));
end component;

component FRS4
    Port ( X : in STD_LOGIC_VECTOR ( 7 downto 0 );
    OP : in STD_LOGIC_VECTOR ( 1 downto 0 );
    y : out STD_LOGIC_VECTOR ( 7 downto 0 ));
end component;

component REP
    Port ( X : in STD_LOGIC_VECTOR ( 7 downto 0 );
    OP : in STD_LOGIC_VECTOR ( 1 downto 0 );
    y : out STD_LOGIC_VECTOR ( 7 downto 0 ));
end component;
begin
u1: FRS1 PORT MAP (x => A ,OP=>OP,y=>l1);

u2: mux2 PORT MAP (in0 => A ,
    in1 => l1,
    s => B(0),
    out1 => l2);   
u3: FRS2 PORT MAP (x => l2 ,OP=>OP,y=>l3);
u4: mux2 PORT MAP (in0 => l2 ,
    in1 => l3,
    s => B(1),
    out1 => l4);
u5: FRS4 PORT MAP (x => l4 ,OP=>OP,y=>l5);
u6: mux2 PORT MAP (in0 => l4 ,
    in1 => l5,
    s => B(2),
    out1 => l6);
in8<=(B(7) or B(6) or B(5) or B(4) or B(3))and OP(1);
u7: FRS4 PORT MAP (x => A ,OP=>OP,y=>l7);
u8: mux2 PORT MAP (in0 => l6 ,
    in1 => l7,
    s => in8,
    out1 => C);
end Behavioral;
