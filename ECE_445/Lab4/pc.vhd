library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity pc is
  Port (clock : in STD_LOGIC;
        reset : in STD_LOGIC;
        pc_in : in STD_LOGIC_VECTOR(31 downto 0);
        pc_out : out STD_LOGIC_VECTOR(31 downto 0));
end pc;

architecture Behavioral of pc is
signal pc_new : STD_LOGIC_VECTOR(31 downto 0) := x"00000000";
begin
    process(clock)
	   begin
		  if(clock'event and clock = '1') then
		      if (reset = '1') then
		          pc_new <= x"00000000";
		      else
		          pc_new <= pc_in;
		      end if;
		  end if;
	end process;
	pc_out <= pc_new;
end Behavioral;