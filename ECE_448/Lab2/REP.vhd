----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 02/09/2020 05:04:23 PM
-- Design Name: 
-- Module Name: REP - Behavioral
-- Project Name: 
-- Target Devices: 
-- Tool Versions: 
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


entity REP is
   Port ( X : in STD_LOGIC_VECTOR ( 7 downto 0 );
    OP : in STD_LOGIC_VECTOR ( 1 downto 0 );
    y : out STD_LOGIC_VECTOR ( 7 downto 0 ));
end REP;

architecture Behavioral of REP is

begin
WITH OP(0) SELECT
    y<= x(7) & x(7) & x(7) & x(7)& x(7) & x(7)& x(7) & x(7)when '0',
        "00000000" when '1',
        "11111111" when others;

end Behavioral;
