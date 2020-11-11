library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
entity MMIO is
    Port (  Din : in STD_LOGIC_VECTOR(7 downto 0);
            clock, memWrite, memRead: in STD_LOGIC;
            ALUout, Data2, ReadData : in STD_LOGIC_VECTOR(31 downto 0);
            Dout, RegOut : out STD_LOGIC_VECTOR(31 downto 0)
            );
end MMIO; 
architecture Behavioral of MMIO is
signal tempReg : STD_LOGIC_VECTOR(31 downto 0) := (others=>'0');
begin
    process(clock)
	begin
        if(clock'event and clock='1') then
            if(memRead = '1' and ALUout = x"00000030") then      -- LW and 48
                Regout <= x"000000" & Din; 
            elsif (memWrite = '1' and ALUout = x"00000034") then -- SW and 40
                tempReg <= Data2;
                RegOut <= Data2;
            else RegOut <= ReadData;
            end if;
		end if;
    end process;
    Dout <= tempReg;
end Behavioral;
