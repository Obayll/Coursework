library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity Control is
	Port(Opcode : in STD_LOGIC_VECTOR(5 downto 0);
		 Func : in STD_LOGIC_VECTOR(5 downto 0);
		 RegDst : out STD_LOGIC_VECTOR(1 downto 0);
		 Jump : out STD_LOGIC_VECTOR(1 downto 0);
		 Branch : out STD_LOGIC;
		 MemRead : out STD_LOGIC;
		 MemtoReg : out STD_LOGIC_VECTOR(1 downto 0);
		 ALUOp : out STD_LOGIC_VECTOR(3 downto 0);
		 MemWrite : out STD_LOGIC;
		 ALUSrc : out STD_LOGIC;	
		 RegWriteOut : out STD_LOGIC);
end Control;

architecture Behavioral of Control is
begin
	process(Opcode,Func)
	begin
		if Opcode = "000000" then
		 R_Case: case Func is
				 when "001000" => Jump <= "10"; --jr R type above
				 when others => Jump <= "00";
				 end case R_Case;
		elsif (Opcode ="000011") or (Opcode ="000010") then
			Jump <= "01";
		else
			Jump <= "00";
		end if;			
	end process;
					
	with Opcode select 
		RegWriteOut <= '0' when "101011",
					   '0' when "000100",
					   '0' when "000101",
					   '1' when others;
	with Opcode select 
		RegDst <= "01" when "000000",	
				  "10" when  "000011",
				  "00" when others;			
	with Opcode select							
		Branch <= '1'   when "000100", -- BEQ
				  '1'   when "000101", -- BNE
				  '0' when others;						 
	with Opcode select						 
		MemRead <= '1' when "100011", -- LW
				   '0' when others;				
	with Opcode select						  
		MemtoReg <= "01" when "100011", -- LW
					"10" when "000011", -- JAL
					"00" when others;
	with Opcode select								
		MemWrite <= '1' when "101011", -- SW
					'0' when others;
	with Opcode select				
		ALUOp <= "0000" when "000000", -- R-type
				 "0001" when "001000", -- Addi
				 "0010"	when "001001", -- Addiu
				 "0011"	when "001100", -- ANDi
				 "0100"	when "001101", -- ORi
				 "0101"	when "001010", -- SLTi
				 "0110"	when "001011", -- SLTiu
				 "0111" when "100011", -- SW 
				 "1000" when "101011", -- LW
				 "1001"	when "000100", -- BEQ
				 "1001"	when "000101", -- BNE
				 "1111" when others;				
	with Opcode select				 
		ALUSrc <= '0' when "000000", -- R-type
				  '0' when "000100", -- BEQ
				  '0' when "000101", -- BNE
				  '1' when others;				
end Behavioral;