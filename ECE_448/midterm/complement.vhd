library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;

entity complement is
    Port (
        K : in STD_LOGIC_VECTOR(7 downto 0);
        CLK : in STD_LOGIC;
        EN : in STD_LOGIC;
        SET : in STD_LOGIC;
        END3 : out STD_LOGIC;
        F : out STD_LOGIC_VECTOR(7 downto 0)
    );
end complement;

architecture Behavioral of complement is
signal OneOut1, OneOut2 : STD_LOGIC_VECTOR(2 downto 0);
signal MuxOut : STD_LOGIC_VECTOR(2 downto 0);
signal AddOut : STD_LOGIC_VECTOR(8 downto 0);
signal new_F : STD_LOGIC;
signal Q : STD_LOGIC_VECTOR(8 downto 0);
begin
    onecomp1 : entity work.onecomplement(Behavioral)
    Port map (
        A => K(2 downto 0),
        B => OneOut1
    );
    onecomp2 : entity work.onecomplement(Behavioral)
    Port map (
        A => K(7 downto 5),
        B => OneOut2
    );
    MuxOut <= OneOut1 when (K(3) xor K(4)) = '1' else OneOut2;
    AddOut <= std_logic_vector(unsigned('0' & K) + unsigned("000001" & MuxOut));
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
    END3 <= new_F or Q(8);
    F <= Q(7 downto 0);
end Behavioral;
