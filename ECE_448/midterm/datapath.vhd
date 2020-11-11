library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;

entity datapath is
    Port (
        CLK : in STD_LOGIC;
        MODE : in STD_LOGIC_VECTOR(1 downto 0);
        SET : in STD_LOGIC;
        END1, END2, END3, END4 : out STD_LOGIC;
        OUTPUT : out STD_LOGIC_VECTOR(7 downto 0)
    );
end datapath;

architecture Behavioral of datapath is
signal DecodeOut : STD_LOGIC_VECTOR(3 downto 0);
signal MuxOut : STD_LOGIC_VECTOR(7 downto 0);
signal PadOut, BitOut, ComOut, ModOut : STD_LOGIC_VECTOR(7 downto 0);
begin
    With MODE select DecodeOut <=
        "0001" when "00",
        "0010" when "10",
        "0100" when "01",
        "1000" when "11",
        "0000" when others;
    PADOVAN : entity work.padovan(Behavioral)
        Port map (
            K => MuxOut,
            CLK => CLK,
            EN => DecodeOut(0),
            SET => SET,
            END1 => END1,
            F => PadOut
        );
    BITCOUNTER : entity work.bitcounter(Behavioral)
        Port map (
            K => MuxOut,
            CLK => CLK,
            EN => DecodeOut(1),
            SET => SET,
            END2 => END2,
            F => PadOut
        );
    COMPLEMENT : entity work.complement(Behavioral)
        Port map (
            K => MuxOut,
            CLK => CLK,
            EN => DecodeOut(2),
            SET => SET,
            END3 => END3,
            F => PadOut
        );
    MODULO : entity work.modulo(Behavioral)
        Port map (
            K => MuxOut,
            CLK => CLK,
            EN => DecodeOut(3),
            SET => SET,
            END4 => END4,
            F => PadOut
        );
    With Mode select MuxOut <=
        PadOut when "00",
        BitOut when "01",
        ComOut when "10",
        ModOut when "11",
        "00000000" when others;
    OUTPUT <= MuxOut;
end Behavioral;
