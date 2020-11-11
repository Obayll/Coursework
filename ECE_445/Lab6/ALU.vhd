library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
use ieee.NUMERIC_STD.all;

entity ALU is
    Port (A, B     : in  STD_LOGIC_VECTOR (31 downto 0);
          ALUCntl  : in  STD_LOGIC_VECTOR (3 downto 0);
          Carryin  : in  STD_LOGIC;
          ALUOut   : out  STD_LOGIC_VECTOR (31 downto 0);
          Zero     : out  STD_LOGIC;
          Carryout : out std_logic;
          Overflow : out  STD_LOGIC);
end ALU; 


architecture Behavioral of ALU is
signal ALU_Result : std_logic_vector (31 downto 0);
signal add_result,sub_result,a32,b32: std_logic_vector(32 downto 0);
signal c32: std_logic_vector(32 downto 0):=(others=>'0');
signal add_ov,sub_ov:std_logic;
signal slt, sltu: std_logic_vector(31 downto 0);
signal pushout: std_logic_vector(1 downto 0);
signal check_bit : std_logic_vector (1 downto 0); -- check bits for slt and sltu

begin

			
   with ALUCntl select
   ALU_Result <= add_result(31 downto 0) when "0010", --Add/
					  add_result(31 downto 0) when "0100", -- add unsign
                 sub_result(31 downto 0) when "0110", --sub
					  sub_result(31 downto 0) when "0111", --sub unsign
                 A AND B when "0000",
                 A OR  B when "0001",
                 A XOR B when "0011",
                 A NOR B when "1100",
			     slt when "1010",
				 sltu when "1011",
                 A when others;---condition for all other alu control signals
	ALUOut  <= ALU_Result; 
----Addition Operation and carry out generation-----	
   a32   <= '0' & A;
   b32   <= '0' & B;
   c32(0) <= Carryin;
   add_result <= a32 + b32 + c32;
   sub_result <= a32 - b32;
---Zero flag-----------------------------	
   Zero <= '1' when ALU_Result =x"00000000" else '0';
---Overflow flag---------------------------------------
   add_ov <= (A(31) AND B(31) AND (NOT alu_result(31))) OR ((NOT A(31)) AND (NOT B(31)) AND alu_result(31));
   sub_ov <= (A(31) AND (NOT B(31)) AND (NOT alu_result(31))) OR ((NOT A(31)) AND B(31) AND alu_result(31)); 
   with ALUCntl select
      Overflow <= add_ov when "0010",
                  sub_ov when "0110",
                  'Z' when others;
---Carryout-------------------------------------------------
  With ALUCntl select 
     Carryout <= add_result(32) when "0010",
                 sub_result(32) when "0110",
                 'Z' when others;			 
----Slt and sltu operation-----
sltu <= "0000000000000000000000000000000" & sub_result(31);
	check_bit <= A(31) & B (31);
	with check_bit select
			slt <= x"00000000" when "01",
				   x"00000001" when "10",
				   sltu when others;
end Behavioral;