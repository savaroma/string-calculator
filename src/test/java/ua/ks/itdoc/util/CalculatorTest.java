package ua.ks.itdoc.util;

import org.junit.Test;
import ua.ks.itdoc.util.Calculator;

import static org.junit.Assert.*;

public class CalculatorTest {
    @Test
    public void testConvertSimple() throws Exception {
        String testStr = "(2+2)+10";
        Calculator calc = new Calculator();
        int result = (int) calc(testStr);

        assertEquals(14, result);
    }

    @Test
    public void testConvertMedium() throws Exception {
        String testStr = "(2+2+5)*(4-2*2)+10";
        Calculator calc = new Calculator();
        int result = (int) calc.convert(testStr);

        assertEquals(18, result);
    }

    @Test
    public void testConvertLongString() throws Exception {
        String testStr = "(2+2)*(42-10)^2";
        Calculator calc = new Calculator();
        int result = (int) calc.convert(testStr);

        assertEquals(16384, result);
    }


}