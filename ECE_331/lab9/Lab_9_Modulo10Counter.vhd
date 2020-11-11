------------------------------------------------------------------------------------------------------
-- Course: ECE 332 - Digital System Design Lab
-- Author: 
-- 
-- Create Date:  
-- Experiment Name: Modulo-100 Counter in VHDL (using Structural design) 
-- Design Name: Lab_9_Modulo10Counter
-- Project Name: project_9 - Structural
-- Target Devices: xc7a35t-1cpg236c
--
-- Description: This is the primary component used in the design of the Modulo-100 Counter.
--				This is the design of the Modulo-10 counter.  
--				The design uses a PROCESS statement to describe the counter.
--				Two Modulo-10 counters are needed in the design of the Modulo-100 Counter.
-- 
-- Basys 3 Board resources used to test this component:
--				1 slider switch for the counter enable input.
--				1 push button switch for the counter clock input.
------------------------------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity lab_9_Modulo10Counter is
port(
    CNT_CLK, CNT_En : in STD_LOGIC;
    CNT_out : out STD_LOGIC_VECTOR(3 downto 0);
    CNT_inc_nxt_digit : out STD_LOGIC);
end lab_9_Modulo10Counter;

architecture behavioral of lab_9_Modulo10Counter is
signal counter : STD_LOGIC_VECTOR(3 downto 0);
begin
    CNT_out <= counter;
    process(CNT_CLK)
    begin
        if(CNT_En = '1' and rising_edge(CNT_CLK)) then
            if (counter = "1001") then
            CNT_inc_nxt_digit <= '1';
            counter <= "0000";
            end if;
        else
            counter <= counter + 1;
        end if;
    end process;
end behavioral;
