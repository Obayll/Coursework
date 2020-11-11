library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_SIGNED.ALL;

entity ALU is
    Port (
        A, B : in STD_LOGIC_VECTOR(31 downto 0);
        ALUCntl: in STD_LOGIC_VECTOR(3 downto 0);
        Carryin : in STD_LOGIC;
        Zero, Carryout, Overflow : out STD_LOGIC;
        ALUout: out STD_LOGIC_VECTOR(31 downto 0));
end ALU;

architecture behavioral of ALU is
    signal temp_sum, temp_dif: STD_LOGIC_VECTOR(32 downto 0);
    signal result: STD_LOGIC_VECTOR(31 downto 0);
    signal over_sum, over_dif: STD_LOGIC;
    signal temp_car: STD_LOGIC_VECTOR(32 downto 0):=(others => '0');
begin
    with ALUCntl Select result <=
        A and B                 when "0000",
        A or B                  when "0001",
        A xor B                 when "0011",
        temp_sum(31 downto 0)    when "0010",
        temp_dif(31 downto 0)    when "0110",
        A nor B                 when "1100",
        x"00000000"             when others;
        
        with ALUCntl Select Overflow <=
            over_sum    when "0010",
            over_dif    when "0110",
            '0'         when others;
        
        with ALUCntl Select Carryout <=
            temp_sum(32)     when "0010",
            temp_dif(32)     when "0110",
            '0'             when others;
        
        Zero <= '1' when result(31 downto 0) = x"00000000" else '0';
        
        over_sum <= (A(31) and B(31) and (not result(31))) or ((not A(31))and (not B(31)) and result(31));
        over_dif <= (A(31) and (not B(31)) and (not result(31))) or ((not A(31))and B(31) and result(31));
        temp_car(0) <= Carryin;
        temp_sum <= ('0' & A) + ('0' & B) + temp_car;
        temp_dif <= ('0' & A) - ('0' & B);
        ALUout <= result;
end behavioral;