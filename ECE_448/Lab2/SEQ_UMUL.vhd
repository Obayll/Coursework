
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;
use ieee.std_logic_textio.all;
library std;
use std.textio.all;


entity SEQ_UMUL is
    GENERIC ( K : INTEGER := 4 ) ;
    Port (A : in std_logic_vector(k-1 downto 0);
        X : in std_logic_vector(k-1 downto 0);
        CLK : in std_logic;
        init: in std_logic;
        EN: in std_logic;
        P : out std_logic_vector(k+k-1 downto 0)
    );
end SEQ_UMUL;

architecture Behavioral of SEQ_UMUL is
signal S : std_logic_vector(k+k downto 0):=(OTHERS => '0'); 
signal MUX_OUT :  STD_LOGIC_VECTOR ( K-1 downto 0 );
signal REG_OUT :  STD_LOGIC_VECTOR ( K-1 downto 0 );
signal ADD_OUT :  STD_LOGIC_VECTOR ( K downto 0 );
signal SRR_OUT :  STD_LOGIC_VECTOR ( K-1 downto 0 );
signal AND_OUT :  STD_LOGIC_VECTOR ( K-1 downto 0 );
signal zero :  STD_LOGIC_VECTOR ( K downto 0 ) :=(OTHERS => '0');
signal S_OUT :  STD_LOGIC;

begin
    MUX_OUT <= (OTHERS => '0')  WHEN init= '1' ELSE S(k+k downto k+1) ;
    AND_OUT <=(OTHERS => '0')  WHEN S_OUT= '0' ELSE A ;
    ADD_OUT <= std_logic_vector(unsigned( '0' &REG_OUT)+unsigned('0' & AND_OUT));
    S(2*k downto k)<=ADD_OUT;
    P(K+K-1 downto k)<= REG_out;
   REG : ENTITY work.REG(behavioral)   GENERIC MAP (k => k)
   PORT MAP (D=>mux_out,
   EN=>EN,
   CLK => CLK,
   Q=> REG_OUT);
   
    SRR : ENTITY work.SRR(behavioral)   GENERIC MAP (k => k)
    PORT MAP (D=>x,
    EN=>EN,
    CLK => CLK,
    id => init,
    sin=>S(K),
    sout => S_out,
    Q=> P(k-1 downto 0));
        
end Behavioral;
