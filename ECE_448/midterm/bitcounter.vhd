library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;

entity bitcounter is
    Port (
        K : in STD_LOGIC_VECTOR(7 downto 0);
        CLK : in STD_LOGIC;
        EN : in STD_LOGIC;
        SET : in STD_LOGIC;
        END2 : out STD_LOGIC;
        F : out STD_LOGIC_VECTOR(7 downto 0)
    );
end bitcounter;

architecture Behavioral of bitcounter is
signal So1, So2, So3 : STD_LOGIC;
signal Co1, Co2, Co3 : STD_LOGIC;
signal Add1, Add2 : STD_LOGIC_VECTOR(1 downto 0);
signal Add3 : STD_LOGIC_VECTOR(2 downto 0);
signal Ripple2Out : STD_LOGIC_VECTOR(2 downto 0);
signal Ripple3Out : STD_LOGIC_VECTOR(3 downto 0);
signal AddOut : STD_LOGIC_VECTOR(8 downto 0);
signal new_F : STD_LOGIC;
signal Q : STD_LOGIC_VECTOR(8 downto 0);
begin
    FullAdder1 : entity work.fulladder(Behavioral)
    Port map (
        A => K(0),
        B => K(1),
        C => K(2),
        S => So1,
        Co => Co1
    );
    HalfAdder : entity work.fulladder(Behavioral)
    Port map (
        A => K(3),
        B => K(4),
        C => '0',
        S => So2,
        Co => Co2
    );
    FullAdder2 : entity work.fulladder(Behavioral)
    Port map (
        A => K(5),
        B => K(6),
        C => K(7),
        S => So3,
        Co => Co3
    );
    Add1 <= Co1 & So1;
    Add2 <= Co2 & So2;
    Ripple2 : entity work.ripple2(Behavioral)
    Port map (
        A => Add1,
        B => Add2,
        C => Ripple2Out
    );
    Add3 <= '0' & Co3 & So3;
    Ripple3 : entity work.ripple3(Behavioral)
    Port map (
        A => Add3,
        B => Ripple2Out,
        C => Ripple3Out
    );
    AddOut <= std_logic_vector(unsigned('0' & K) + unsigned("00000" & Ripple3Out));
    new_F <= AddOut(8);
    Reg9 : process(CLK)
    begin
        if rising_edge(CLK) then
            if EN = '1' then
                Q <= AddOut;
            elsif SET = '1' then
                Q <= '0' & "00000001";
            end if;
        end if;
    end process;
    END2 <= new_F or Q(8);
    F <= Q(7 downto 0);
end Behavioral;
