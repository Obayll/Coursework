library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.NUMERIC_STD.ALL;
use IEEE.STD_LOGIC_TEXTIO.ALL;

library STD;
use STD.TEXTIO.ALL;

entity CVRS_tb is
end CVRS_tb;

architecture Behavioral of CVRS_tb is
    component CVRS
    port (
        A : in STD_LOGIC_VECTOR ( 7 downto 0 );
        B : in STD_LOGIC_VECTOR ( 7 downto 0 );
        OP : in STD_LOGIC_VECTOR ( 1 downto 0 );
        C : out STD_LOGIC_VECTOR ( 7 downto 0 ));
    end component;

    signal A_tb : STD_LOGIC_VECTOR ( 7 downto 0 );
    signal B_tb : STD_LOGIC_VECTOR ( 7 downto 0 );
    signal OP_tb : STD_LOGIC_VECTOR ( 1 downto 0 );
    signal C_tb : STD_LOGIC_VECTOR ( 7 downto 0 );
    signal C_check : STD_LOGIC_VECTOR ( 7 downto 0 );
    signal test_num : integer;
    signal FLAG : integer;
    
    type debug_data_type is record
        X : STD_LOGIC_VECTOR ( 7 downto 0 );
        Y : STD_LOGIC_VECTOR ( 7 downto 0 );
    end record;

    type debug_data_array_type is array ( natural range <> ) of debug_data_type;
    
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

begin
UUT: CVRS port map (
    A => A_tb,
    B => B_tb,
    OP => OP_tb,
    C => C_tb );

simulation : process
variable debug_line : line;
begin
    OP_tb <= "00";
    test_num <= 1;
    FLAG <= 0;
    for i in 0 to 3 loop
        for j in 0 to 7 loop
            A_tb <= DEBUG_DATA(j).X;
            for k in 0 to 7 loop
                B_tb <= DEBUG_DATA(k).Y;
                
                case std_logic_vector(to_unsigned(i, OP_tb'length)) is
                    when "00" => C_check <= std_logic_vector(unsigned(DEBUG_DATA(j).X) ror to_integer(unsigned(DEBUG_DATA(k).Y)));
                    when "01" => C_check <= std_logic_vector(unsigned(DEBUG_DATA(j).X) rol to_integer(unsigned(DEBUG_DATA(k).Y)));
                    when "10" => C_check <= to_stdlogicvector(to_bitvector(DEBUG_DATA(j).X) sra to_integer(unsigned(DEBUG_DATA(k).Y)));
                    when "11" => C_check <= std_logic_vector(unsigned(DEBUG_DATA(j).X) sll to_integer(unsigned(DEBUG_DATA(k).Y)));
                    when others => null;
                end case;
                
                wait for HALF_INTERVAL;
                if C_tb /= C_check then
                    write(debug_line, string'("Test #" & integer'image(test_num) & " failed!"));
                    writeline(output, debug_line);
                    write(debug_line, string'("C expected was "));
                    for x in 7 downto 0 loop
                        write(debug_line, std_logic'image(C_check(x)));
                    end loop;
                    write(debug_line, string'(" but C actual was "));
                    for x in 7 downto 0 loop
                        write(debug_line, std_logic'image(C_tb(x)));
                    end loop;
                    write(debug_line, string'("."));
                    writeline(output, debug_line);
                    FLAG <= FLAG + 1;
                end if;
                
                if test_num < 256 then
                    test_num <= test_num + 1;
                end if;
            end loop;
        end loop;
        
        wait for TEST_INTERVAL - HALF_INTERVAL;
        
        if OP_tb < "11" then
            OP_tb <= std_logic_vector(to_unsigned(to_integer(unsigned(OP_tb)) + 1, 2));
        end if;
    end loop;
    if FLAG > 0 then
        write(debug_line, string'("Ran " & integer'image(test_num) & " tests and " & integer'image(FLAG) & " failed!"));
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