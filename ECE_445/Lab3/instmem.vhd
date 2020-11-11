----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    22:00:33 10/07/2010 
-- Design Name: 
-- Module Name:    instmem - Behavioral 
-- Project Name: 
-- Target Devices: 
-- Tool versions: 
-- Description: 
--
-- Dependencies: 
--
-- Revision: 
-- Revision 0.01 - File Created
-- Additional Comments: 
--
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
USE ieee.std_logic_arith.all;
USE ieee.std_logic_unsigned.all;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity instmem is
    Port ( read_inst : in  STD_LOGIC_VECTOR (31 downto 0);
           inst_out : out  STD_LOGIC_VECTOR (31 downto 0));
end instmem;

architecture Behavioral of instmem is
type instmem is array (0 to 51) of std_logic_vector (7 downto 0);
signal RAM: instmem:=(x"01",x"2a",x"40",x"20",-- 3  1:  add  $t0, $t1, $t2
                      x"01",x"4b",x"48",x"21",-- 7  2:  addu $t1, $t2, $t3
                      x"01",x"6c",x"50",x"24",--11  3:  and  $t2, $t3, $t4
                      x"01",x"8d",x"58",x"27",--15  4:  nor  $t3, $t4, $t5
                      x"01",x"ae",x"60",x"25",--19  5:  or   $t4, $t5, $t6
                      x"01",x"cf",x"68",x"26",--23  6:  xor  $t5, $t6, $t7 
                      x"02",x"32",x"80",x"22",--27  7:  sub  $s0, $s1, $s2
                      x"02",x"53",x"88",x"23",--31  8:  subu $s1, $s2, $s3
                      x"02",x"74",x"90",x"2a",--35  9:  slt  $s2, $s3, $s4
                      x"02",x"95",x"98",x"2b",--39  10: sltu $s3, $s4, $s5
                      x"00",x"00",x"00",x"00",--43
                      x"00",x"00",x"00",x"00",--47
                      x"00",x"00",x"00",x"00" );--51
begin
inst_out(7 downto 0)<= RAM(conv_integer(unsigned(read_inst+3))) ;
inst_out(15 downto 8)<= RAM(conv_integer(unsigned(read_inst+2))); 
inst_out(23 downto 16)<= RAM(conv_integer(unsigned(read_inst+1)));
inst_out(31 downto 24)<= RAM(conv_integer(unsigned(read_inst)));

end Behavioral;