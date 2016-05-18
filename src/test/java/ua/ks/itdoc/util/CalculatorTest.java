package ua.ks.itdoc.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static ua.ks.itdoc.util.CalculatorUtil.calculateString;

public class CalculatorTest {
    @Test
    public void testCalculateSimple() throws Exception {
        String testStr = "(2+2)+10";

        assertEquals("14.0", calculateString(testStr));
    }

    @Test
    public void testCalculateMedium() throws Exception {
        String testStr = "(2+2+5)*(4-2*2)+10";

        assertEquals("10.0", calculateString(testStr));
    }

    @Test
    public void testCcalculateLongString() throws Exception {
        String testStr = "(2+2)*(42-10)^2";

        assertEquals("4096.0", calculateString(testStr));
    }

    @Test
    public void testAllOperands() throws Exception {
        String testStr = "-10+10*20/2^2";

        assertEquals("40.0", calculateString(testStr));
    }

}