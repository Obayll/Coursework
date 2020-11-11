library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity cntl is
  Port (OPcode: in  STD_LOGIC_VECTOR (5 downto 0);
        ALUOp: out  STD_LOGIC_VECTOR (5 downto 0);
        RegDst: out  STD_LOGIC;
        ALUSrc: out  STD_LOGIC;
        MemToReg: out  STD_LOGIC;
        RegWrite: out  STD_LOGIC;
        MemRead: out  STD_LOGIC;
        MemWrite: out  STD_LOGIC;
        Branch: out  STD_LOGIC;
        BranchNE: out STD_LOGIC);
end cntl;

architecture Behavioral of cntl is
signal R_type, LW, SW, BEQ, ADDI, BNE, ANDI, ORI, SLTI, SLTIU: std_logic;
begin
    R_type <= '1' when Opcode = "000000" else '0';
	LW <= '1' when Opcode = "100011" else '0';
	SW <= '1' when OPcode = "101011" else '0';
	BEQ <= '1' when  OPcode = "000100" else '0';
	BNE <= '1' when OPcode = "000101" else '0';
	ADDI <= '1' when OPcode = "001000" else '0';
	ANDI <= '1' when Opcode = "001100" else '0';
	ORI <= '1' when Opcode = "001101" else '0';
	SLTI <= '1' when Opcode = "001010" else '0';
	SLTIU <= '1' when Opcode = "001011" else '0';
	
	Branch <= BEQ;
	BranchNE <= BNE;
	MemToReg <= LW;
	ALUSrc <= LW or SW or ADDI or ORI or ANDI or SLTI or SLTIU;
	RegDst <= R_type;
	MemRead <= LW;
	MemWrite <= SW;
	RegWrite <= R_type or LW or ADDI or ORI or ANDI or SLTI or SLTIU;
	
	ALUOp <= Opcode;
end Behavioral;