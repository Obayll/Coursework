----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 02/10/2020 11:13:42 PM
-- Design Name: 
-- Module Name: REG - Behavioral
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

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity REG is
    GENERIC ( K : INTEGER := 4 ) ;
    Port (D : in std_logic_vector(k-1 downto 0);
        EN: in std_logic;
        CLK : in std_logic;
        Q : out std_logic_vector(k-1 downto 0));
end REG;

architecture Behavioral of REG is

begin
    PROCESS (CLK)
    BEGIN
        IF rising_edge(CLK) THEN
            IF En = '1' THEN
                Q <= D ;
            END IF ;
        END IF;
    END PROCESS ;
end Behavioral;
