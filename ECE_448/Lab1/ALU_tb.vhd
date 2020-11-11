library IEEE;
use IEEE.std_logic_1164.ALL;
use ieee.std_logic_signed.all;

entity ALU_tb is
end ALU_tb;

architecture Behavioral of ALU_tb is

component ALU
port (
    A : in STD_LOGIC_VECTOR ( 3 downto 0 );
    B : in STD_LOGIC_VECTOR ( 3 downto 0 );
    OPCODE : in STD_LOGIC_VECTOR ( 2 downto 0 );
    C : out STD_LOGIC;
    V : out STD_LOGIC;
    X : out STD_LOGIC_VECTOR ( 3 downto 0 );
    Y : out STD_LOGIC_VECTOR ( 3 downto 0 ));
end component;

signal A_tb : STD_LOGIC_VECTOR ( 3 downto 0 );
signal B_tb : STD_LOGIC_VECTOR ( 3 downto 0 );
signal OPCODE_tb : STD_LOGIC_VECTOR ( 2 downto 0 );
signal C_tb : STD_LOGIC;
signal V_tb : STD_LOGIC;
signal X_tb : STD_LOGIC_VECTOR ( 3 downto 0 );
signal Y_tb : STD_LOGIC_VECTOR ( 3 downto 0 );

begin
UUT: ALU port map(
    A => A_tb,
    B => B_tb,
    OPCODE => OPCODE_tb,
    C => C_tb,
    V => V_tb,
    X => X_tb,
    Y => Y_tb);
    
simulation : process
    begin
    OPCODE_tb <= "000";
    A_tb <= x"0";
    B_tb <= x"C";
    wait for 10ns;
    A_tb <= x"1";
    B_tb <= x"4";
    wait for 10ns;
    
    OPCODE_tb <= "001";
    A_tb <= x"2";
    B_tb <= x"3";
    wait for 10ns;
    A_tb <= x"3";
    B_tb <= x"A";
    wait for 10ns;
    
    OPCODE_tb <= "010";
    A_tb <= x"4";
    B_tb <= x"7";
    wait for 10ns;
    A_tb <= x"5";
    B_tb <= x"9";
    wait for 10ns;
    
    OPCODE_tb <= "011";
    A_tb <= x"6";
    B_tb <= x"9";
    wait for 10ns;
    A_tb <= x"7";
    B_tb <= x"A";
    wait for 10ns;
    
    OPCODE_tb <= "100";
    A_tb <= x"8";
    B_tb <= x"7";
    wait for 10ns;
    A_tb <= x"9";
    B_tb <= x"8";
    wait for 10ns;
    
    OPCODE_tb <= "101";
    A_tb <= x"A";
    B_tb <= x"B";
    wait for 10ns;
    A_tb <= x"B";
    B_tb <= x"D";
    wait for 10ns;
    
    OPCODE_tb <= "110";
    A_tb <= x"C";
    B_tb <= x"4";
    wait for 10ns;
    A_tb <= x"D";
    B_tb <= x"2";
    wait for 10ns;
    
    OPCODE_tb <= "111";
    A_tb <= x"E";
    B_tb <= x"E";
    wait for 10ns;
    A_tb <= x"F";
    B_tb <= x"F";
    wait for 10ns;
    
    end process;
end Behavioral;
