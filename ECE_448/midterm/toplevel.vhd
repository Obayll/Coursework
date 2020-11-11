library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;

entity toplevel is
    Port (
        CLK : in STD_LOGIC;
        RESET, START : in STD_LOGIC;
        READY : out STD_LOGIC;
        OUTPUT : out STD_LOGIC_VECTOR(7 downto 0);
        MODE : out STD_LOGIC_VECTOR(1 downto 0)
    );
end toplevel;

architecture Behavioral of toplevel is
signal MODE_SIG : STD_LOGIC_VECTOR(1 downto 0);
signal END1, END2, END3, END4 : STD_LOGIC;
signal SET : STD_LOGIC;
begin
    DATAPATH : entity work.datapath(Behavioral)
        Port map (
            CLK => CLK,
            MODE => MODE_SIG,
            SET => SET,
            END1 => END1,
            END2 => END2,
            END3 => END3,
            END4 => END4,
            OUTPUT => OUTPUT
        );
    CONTROLLER : entity work.controller(Behavioral)
        Port map (
            CLK => CLK,
            RESET => RESET,
            START => START,
            MODE => MODE_SIG,
            SET => SET,
            END1 => END1,
            END2 => END2,
            END3 => END3,
            END4 => END4
        );
end Behavioral;
