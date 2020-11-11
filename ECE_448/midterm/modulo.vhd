library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;

entity modulo is
    Port (
        K : in STD_LOGIC_VECTOR(7 downto 0);
        CLK : in STD_LOGIC;
        EN : in STD_LOGIC;
        SET : in STD_LOGIC;
        END4 : out STD_LOGIC;
        F : out STD_LOGIC_VECTOR(7 downto 0)
    );
end modulo;

architecture Behavioral of modulo is
constant AddIn : STD_LOGIC_VECTOR(7 downto 0) := x"2E";
constant SubIn : STD_LOGIC_VECTOR(7 downto 0) := x"FB";
signal AddOut : STD_LOGIC_VECTOR(8 downto 0);
signal SubOut : STD_LOGIC_VECTOR(8 downto 0);
signal MuxOut : STD_LOGIC_VECTOR(7 downto 0);
signal Q : STD_LOGIC_VECTOR(7 downto 0);
begin
    AddOut <= std_logic_vector(unsigned('0' & K) + unsigned('0' & AddIn));
    SubOut <= std_logic_vector(unsigned(AddOut) - unsigned('0' & SubIn));
    MuxOut <= AddOut(7 downto 0) when SubOut(8) = '1' else SubOut(7 downto 0);
    Reg9 : process(CLK)
    begin
        if rising_edge(CLK) then
            if EN = '1' then
                Q <= MuxOut;
            elsif SET = '1' then
                Q <= "00000001";
            end if;
        end if;
    end process;
    END4 <= '1' when Q = x"37" else '0';
    F <= Q;
end Behavioral;
