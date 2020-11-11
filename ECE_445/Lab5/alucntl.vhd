library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity alucntl is
  Port (AluCntl_in: in STD_LOGIC_VECTOR(5 downto 0);
        ALUOp: in STD_LOGIC_VECTOR(5 downto 0);
        AluCntl_out: out STD_LOGIC_VECTOR(3 downto 0) := (others => '0');
        JR: out STD_LOGIC;
        JAL: in STD_LOGIC);
end alucntl;

architecture Behavioral of alucntl is
begin
    JR <= '1' when ALUCntl_in = "001000" and JAL = '0' else '0';
    process(AluCntl_in, ALUOp)
    begin
            if ALUCntl_in = "100100" then AluCntl_out <= "0000";
            elsif ALUCntl_in = "100101" then AluCntl_out <= "0001";
            elsif ALUCntl_in = "100000" or ALUCntl_in = "100001" then AluCntl_out <= "0010";
            elsif ALUCntl_in = "100010" or ALUCntl_in = "100011" then AluCntl_out <= "0110";
            elsif ALUCntl_in = "101010" or ALUCntl_in = "101011" then AluCntl_out <= "0111";
            elsif ALUCntl_in = "100110" then AluCntl_out <= "0011";
            elsif ALUCntl_in = "100111" then AluCntl_out <= "1100";
            elsif ALUCntl_in = "001000" then AluCntl_out <= "0010";
            end if;
            if ALUOp = "001100" then AluCntl_out <= "0000";
            elsif ALUOp = "001101" then AluCntl_out <= "0001";
            elsif ALUOp = "001000" or ALUOp = "100011" or ALUOp = "101011" or ALUOp = "000010" or ALUOp = "000011" then AluCntl_out <= "0010";
            elsif ALUOp = "000100" or ALUOp = "000101" then AluCntl_out <= "0110";
            elsif ALUOp = "001010" or ALUOp = "001011" then AluCntl_out <= "0111";
            end if;
    end process;
end Behavioral;