

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;


entity SRR is
    GENERIC ( K : INTEGER := 4 ) ;
    Port ( D : in std_logic_vector(k-1 downto 0);
        EN: in std_logic;
        CLK : in std_logic;
        id : in std_logic;
        sin : in std_logic;
        sout : out std_logic;
        q : out std_logic_vector(k-1 downto 0));
end SRR;

architecture Behavioral of SRR is
SIGNAL Qt: STD_LOGIC_VECTOR(k-1 DOWNTO 0);
begin
    PROCESS (Clk)
    BEGIN
        IF rising_edge(Clk) THEN
            IF En = '1' THEN
                IF id = '1' THEN
                    Qt <= D ;
                ELSE
                    Qt <= Sin & Qt(K-1 downto 1);
                end if;
            end if;
        end if;
    end process;
    Q<=Qt;
    sout<=Qt(0);
end Behavioral;
