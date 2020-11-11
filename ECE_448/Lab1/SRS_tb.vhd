library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;
use IEEE.STD_LOGIC_TEXTIO.ALL;

library STD;
use STD.TEXTIO.ALL;

entity SRS_tb is
end SRS_tb;

architecture Behavioral of SRS_tb is
    component SRS
    port (
        RST : in STD_LOGIC;
        LD : in STD_LOGIC;
        EN : in STD_LOGIC;
        OP : in STD_LOGIC_VECTOR ( 1 downto 0 );
        CLK : in STD_LOGIC;
        D : in STD_LOGIC_VECTOR ( 7 downto 0 );
        Q : out STD_LOGIC_VECTOR ( 7 downto 0 ));
    end component;
    
    signal RST_tb : STD_LOGIC;
    signal LD_tb : STD_LOGIC;
    signal EN_tb : STD_LOGIC;
    signal OP_tb : STD_LOGIC_VECTOR ( 1 downto 0 );
    signal CLK_tb : STD_LOGIC;
    signal D_tb : STD_LOGIC_VECTOR ( 7 downto 0 );
    signal Q_tb : STD_LOGIC_VECTOR ( 7 downto 0 );
    signal Q_check : STD_LOGIC_VECTOR ( 7 downto 0 );
    signal test_num : integer;
    signal fail_num : integer;
    
    type debug_data_type is record
        X : STD_LOGIC_VECTOR ( 7 downto 0 );
        Y : STD_LOGIC_VECTOR ( 7 downto 0 );
    end record;
    
    type debug_data_array_type is array (natural range <>) of debug_data_type;
    
    constant DEBUG_DATA: debug_data_array_type := (
        ("10100010", "01011101"),
        ("01110011", "00000001"),
        ("11111001", "01010111"),
        ("00001001", "11110111"),
        ("10101010", "00111111"),
        ("10101101", "11111111"),
        ("10100101", "11001111"),
        ("01100011", "00001000")
    );
    
    constant TEST_INTERVAL : time := 10 ns;
    constant HALF_INTERVAL : time := 5 ns;
    constant PROP_INTERVAL : time := 1ps;
    
begin
UUT : SRS port map (
    RST => RST_tb,
    LD => LD_tb,
    EN => EN_tb,
    OP => OP_tb,
    CLK => CLK_tb,
    D => D_tb,
    Q => Q_tb);

simulation : process
variable debug_line : line;
begin
    test_num <= 1;
    fail_num <= 0;
    CLK_tb <= '0';
    Q_check <= x"00";
    for i in 0 to 2 loop
        case i is
            when 0 =>
                RST_tb  <= '1';
                LD_tb   <= '-';
                EN_tb   <= '-';
                OP_tb   <= "--";
            when 1 =>
                RST_tb  <= '0';
                LD_tb   <= '1';
                EN_tb   <= '-';
                OP_tb   <= "--";
            when 2 =>
                RST_tb  <= '0';
                LD_tb   <= '0';
                EN_tb   <= '1';
                OP_tb   <= "00";
            when others => null;
        end case;
        wait for PROP_INTERVAL;
        for j in 0 to 7 loop
            D_tb <= DEBUG_DATA(j).X;
            if RST_tb = '1' then
                CLK_tb <= '0';
                wait for HALF_INTERVAL;
                CLK_tb <= '1';
                Q_check <= x"00";
                wait for HALF_INTERVAL;
                -- Start Error Delcaration
                if Q_tb /= Q_check then
                    write(debug_line, string'("Test #" & integer'image(test_num) & " failed!"));
                    writeline(output, debug_line);
                    write(debug_line, string'("Q expected was "));
                    for x in 7 downto 0 loop
                        write(debug_line, std_logic'image(Q_check(x)));
                    end loop;
                    write(debug_line, string'(" but Q actual was "));
                    for x in 7 downto 0 loop
                        write(debug_line, std_logic'image(Q_tb(x)));
                    end loop;
                    write(debug_line, string'("."));
                    writeline(output, debug_line);
                    fail_num <= fail_num + 1;
                end if;
                -- End Error Delcaration
                test_num <= test_num + 1;
            elsif LD_tb = '1' then
                CLK_tb <= '0';
                wait for HALF_INTERVAL;
                CLK_tb <= '1';
                Q_check <= DEBUG_DATA(j).X;
                wait for HALF_INTERVAL;
                -- Start Error Delcaration
                if Q_tb /= Q_check then
                    write(debug_line, string'("Test #" & integer'image(test_num) & " failed!"));
                    writeline(output, debug_line);
                    write(debug_line, string'("Q expected was "));
                    for x in 7 downto 0 loop
                        write(debug_line, std_logic'image(Q_check(x)));
                    end loop;
                    write(debug_line, string'(" but Q actual was "));
                    for x in 7 downto 0 loop
                        write(debug_line, std_logic'image(Q_tb(x)));
                    end loop;
                    write(debug_line, string'("."));
                    writeline(output, debug_line);
                    fail_num <= fail_num + 1;
                end if;
                -- End Error Delcaration
                test_num <= test_num + 1;
            elsif EN_tb = '1' then
                OP_tb <= "00";
                for k in 0 to 3 loop
                    CLK_tb <= '0';
                    wait for HALF_INTERVAL;
                    CLK_tb <= '1';
                    case std_logic_vector(to_unsigned(k, OP_tb'length)) is
                        when "00" => Q_check <= std_logic_vector(unsigned(Q_check) ror 1);
                        when "01" => Q_check <= std_logic_vector(unsigned(Q_check) rol 1);
                        when "10" => Q_check <= to_stdlogicvector(to_bitvector(Q_check) sra 1);
                        when "11" => Q_check <= std_logic_vector(unsigned(Q_check) sll 1);
                        when others => null;
                    end case;
                    wait for HALF_INTERVAL;
                    -- Start Error Delcaration
                    if Q_tb /= Q_check then
                        write(debug_line, string'("Test #" & integer'image(test_num) & " failed!"));
                        writeline(output, debug_line);
                        write(debug_line, string'("Q expected was "));
                        for x in 7 downto 0 loop
                            write(debug_line, std_logic'image(Q_check(x)));
                        end loop;
                        write(debug_line, string'(" but Q actual was "));
                        for x in 7 downto 0 loop
                            write(debug_line, std_logic'image(Q_tb(x)));
                        end loop;
                        write(debug_line, string'("."));
                        writeline(output, debug_line);
                        fail_num <= fail_num + 1;
                    end if;
                    -- End Error Delcaration
                    test_num <= test_num + 1;
                    if OP_tb < "11" then
                        OP_tb <= std_logic_vector(to_unsigned(to_integer(unsigned(OP_tb)) + 1, 2));
                    end if;
                    end loop;
            end if;
        end loop;
    end loop;
    if fail_num > 0 then
        write(debug_line, string'("Ran " & integer'image(test_num) & " tests and " & integer'image(fail_num) & " failed!"));
        writeline(output, debug_line);
        write(debug_line, string'("PSM calculations are not accurate!"));
        writeline(output, debug_line);
    else
        write(debug_line, string'("Ran " & integer'image(test_num) & " tests and all succeeded!"));
        writeline(output, debug_line);
    end if;
    report "End of testbench.";
    wait;
end process;
end Behavioral;
