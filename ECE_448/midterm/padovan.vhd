library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;

entity padovan is
    Port (
        K : in STD_LOGIC_VECTOR(7 downto 0);
        CLK : in STD_LOGIC;
        EN : in STD_LOGIC;
        SET : in STD_LOGIC;
        END1 : out STD_LOGIC;
        F : out STD_LOGIC_VECTOR(7 downto 0)
    );
end padovan;

architecture Behavioral of padovan is
signal Reg1Out, Reg2Out, Reg3Out : STD_LOGIC_VECTOR(7 downto 0);
signal AddOut : STD_LOGIC_VECTOR(8 downto 0);
begin
    Register1 : entity work.reg(Behavioral)
        Port map (
            D => K,
            CLK => CLK,
            EN => EN,
            SET => SET,
            Q => Reg1Out
        );
    Register2 : entity work.reg(Behavioral)
        Port map (
            D => Reg1Out,
            CLK => CLK,
            EN => EN,
            SET => SET,
            Q => Reg2Out
        );
    Register3 : entity work.reg(Behavioral)
        Port map (
            D => Reg2Out,
            CLK => CLK,
            EN => EN,
            SET => SET,
            Q => Reg3Out
        );
    AddOut <= std_logic_vector(unsigned('0' & Reg2Out) + unsigned('0' & Reg3Out));
    END1 <= AddOut(8);
    F <= AddOut(7 downto 0);
end Behavioral;
