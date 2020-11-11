library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity controller is
    Port (
        CLK : in STD_LOGIC;
        RESET, START : in STD_LOGIC;
        READY : out STD_LOGIC;
        MODE : out STD_LOGIC_VECTOR(1 downto 0);
        SET : out STD_LOGIC;
        END1, END2, END3, END4 : in STD_LOGIC
    );
end controller;

architecture Behavioral of controller is
type state is (S_WAIT, S_PADOVAN, S_BWA, S_MMC, S_MOD251);
signal state_reg, state_next : state;
begin
    ASM : process(CLK, RESET)
    begin
        if RESET = '1' then
            state_reg <= S_WAIT;
        elsif rising_edge(CLK) then
            state_reg <= state_next;
        end if;
    end process;
    Next_State : process(state_reg, START, END1, END2, END3, END4)
    begin
        state_next <= state_reg;
        case state_reg is
            when S_WAIT =>
                READY <= '1';
                if START = '1' then
                    state_next <= S_PADOVAN;
                    SET <= '1';
                else
                    state_next <= S_WAIT;
                end if;
            when S_PADOVAN =>
                SET <= '0';
                MODE <= "00";
                if END1 = '1' then
                    state_next <= S_BWA;
                    SET <= '1';
                else
                    state_next <= S_PADOVAN;
                end if;
            when S_BWA =>
                SET <= '0';
                MODE <= "01";
                if END2 = '1' then
                    state_next <= S_MMC;
                    SET <= '1';
                else
                    state_next <= S_BWA;
                end if;
            when S_MMC =>
                SET <= '0';
                MODE <= "10";
                if END3 = '1' then
                    state_next <= S_MOD251;
                    SET <= '1';
                else
                    state_next <= S_MMC;
                end if;
            when S_MOD251 =>
                SET <= '0';
                MODE <= "11";
                if END4 = '1' then
                    state_next <= S_WAIT;
                    SET <= '1';
                else
                    state_next <= S_MOD251;
                end if;
        end case;
    end process;
end Behavioral;
