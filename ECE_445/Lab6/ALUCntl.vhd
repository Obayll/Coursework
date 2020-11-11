library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;

entity ALUControl is
	port( Func: in STD_LOGIC_VECTOR(5 downto 0);
	      Opcode : in STD_LOGIC_VECTOR(3 downto 0);
		  ALUCntl: out STD_LOGIC_VECTOR(3 downto 0));
end ALUControl;

architecture Behavioral of ALUControl is
begin
    process(Opcode, Func)
    begin
        if (Opcode = "0000") AND (Func = "100000") then ALUCntl <= "0010"; -- R-type Add
        elsif (Opcode = "0000") AND (Func = "100001") then ALUCntl <= "0100"; -- R-type Addu
        elsif (Opcode = "0000") AND (Func = "100010") then ALUCntl <= "0110"; -- R-type Sub
        elsif (Opcode = "0000") AND (Func = "100011") then ALUCntl <= "0111"; -- R-type Subu
        elsif (Opcode = "0000") AND (Func = "100100") then ALUCntl <= "0000"; -- R-type AND
        elsif (Opcode = "0000") AND (Func = "100101") then ALUCntl <= "0001"; -- R-type OR
        elsif (Opcode = "0000") AND (Func = "100110") then ALUCntl <= "0011"; -- R-type XOR
        elsif (Opcode = "0000") AND (Func = "100111") then ALUCntl <= "1100"; -- R-type NOR
        elsif (Opcode = "0000") AND (Func = "101000") then ALUCntl <= "1010"; -- R-type SLT
        elsif (Opcode = "0000") AND (Func = "101001") then ALUCntl <= "1011"; -- R-type SLTU
        
        elsif(Opcode = "0001") then ALUCntl <= "0010"; -- I-type Addi using ADD
        elsif(Opcode = "0010") then ALUCntl <= "0100"; -- I-type Addi Unsigned using ADD UNSIGNED
        elsif(Opcode = "0011") then ALUCntl <= "0000"; -- I-type ANDi using AND
        elsif(Opcode = "0100") then ALUCntl <= "0001"; -- I-type ORi using OR
        elsif(Opcode = "0101") then ALUCntl <= "1010"; -- I-type SLTi using SLT
        elsif(Opcode = "0110") then ALUCntl <= "1011"; -- I-type SLTUi using SLTU
        elsif(Opcode = "0111") then ALUCntl <= "0010"; -- I-type STORE WORD using ADD
        elsif(Opcode = "1000") then ALUCntl <= "0010"; -- I-type LOAD WORD using ADD
        elsif(Opcode = "1001") then ALUCntl <= "0111"; -- Branch
        else ALUCntl <= "1111"; -- Other
        end if;
    end process; 
end Behavioral;