library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_signed.all;

entity ALU_tb is
end ALU_tb;

architecture behavior of ALU_tb is
	component ALU is
	   port (
	       A, B : in STD_LOGIC_VECTOR(31 downto 0);
	       ALUCntl : in STD_LOGIC_VECTOR(3 downto 0);
	       Carryin : in STD_LOGIC;
	       Zero, Carryout, Overflow : out STD_LOGIC;
	       ALUout : out STD_LOGIC_VECTOR(31 downto 0));
	end component;
	signal A_tb, B_tb, ALUout_tb : STD_LOGIC_VECTOR(31 downto 0);
	signal ALUCntl_tb : STD_LOGIC_VECTOR(3 downto 0);
	signal Carryin_tb, Zero_tb, Carryout_tb, Overflow_tb : STD_LOGIC ;
begin
	uut : ALU port map (
	   A => A_tb,
	   B => B_tb,
	   ALUout => ALUout_tb,
	   ALUCntl => ALUCntl_tb,
	   Carryin => Carryin_tb,
	   Zero => Zero_tb,
	   Carryout => Carryout_tb,
	   Overflow => Overflow_tb);
	
	ALU_simulation : process
	begin
	-- #1
	ALUCntl_tb <= "0000";
	A_tb <= x"FFFFFFFF";
    B_tb <= x"00000000";
    Carryin_tb <= '0';
    wait for 10ns;
    -- #2
	ALUCntl_tb <= "0001";
    A_tb <= x"98989898";
    B_tb <= x"89898989";
    Carryin_tb <= '0';
    wait for 10ns;
    -- #3
    ALUCntl_tb <= "0011";
    A_tb <= x"01010101";
    B_tb <= x"10101010";
    Carryin_tb <= '0';
    wait for 10ns;
    -- #4
    ALUCntl_tb <= "0010";
    A_tb <= x"00000001";
    B_tb <= x"FFFFFFFF";
    Carryin_tb <= '0';
    wait for 10ns;
    -- #4
    ALUCntl_tb <= "0010";
    A_tb <= x"6389754F";
    B_tb <= x"AD5624E6";
    Carryin_tb <= '0';
    wait for 10ns;
    -- #5
    ALUCntl_tb <= "0010";
    A_tb <= x"00000001";
    B_tb <= x"FFFFFFFF";
    Carryin_tb <= '1';
    wait for 10ns;
    -- #5
    ALUCntl_tb <= "0010";
    A_tb <= x"6389754F";
    B_tb <= x"AD5624E6";
    Carryin_tb <= '1';
    wait for 10ns;
    -- #6
    ALUCntl_tb <= "0010";
    A_tb <= x"FFFFFFFF";
    B_tb <= x"FFFFFFFF";
    Carryin_tb <= '1';
    wait for 10ns;
    -- #7
    ALUCntl_tb <= "0110";
    A_tb <= x"00000000";
    B_tb <= x"00000001";
    Carryin_tb <= '0';
    wait for 10ns;
    -- #8
    ALUCntl_tb <= "0110";
    A_tb <= x"F9684783";
    B_tb <= x"F998D562";
    Carryin_tb <= '0';
    wait for 10ns;
    -- #9
    ALUCntl_tb <= "1100";
    A_tb <= x"9ABCDEDF";
    B_tb <= x"9ABCDEFD";
    Carryin_tb <= '0';
    wait for 10ns;
    -- #10
    ALUCntl_tb <= "1111";
    A_tb <= x"89BCDE34";
    B_tb <= x"C53BD687";
    Carryin_tb <= '0';
    wait for 10ns;
	end process ALU_simulation;
end behavior;