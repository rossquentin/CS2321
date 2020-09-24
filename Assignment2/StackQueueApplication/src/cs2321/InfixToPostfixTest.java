package cs2321;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InfixToPostfixTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testConvert1() {
        String expression = "4 * ( 20 + 5 ) - 6";
        String expected = "4 20 5 + * 6 -";
        assertEquals(expected, InfixToPostfix.convert(expression));
	}

	
	@Test
	public void testConvert2() {
        String expression = "3 * 11 + 9 - ( 8 / 2 ) / 4";
        String expected = "3 11 * 9 + 8 2 / 4 / -";
        assertEquals(expected, InfixToPostfix.convert(expression));
	}

	@Test
	public void testConvert3() {
        String expression = "13 * 5";
        String expected = "13 5 *";
        assertEquals(expected, InfixToPostfix.convert(expression));
	}
	
	@Test
	public void testConvert4() {
        String expression = "1 + 2 / 3 * 4 + 5 - ( 6 * 7 )";
        String expected = "1 2 3 / 4 * + 5 + 6 7 * -";
        assertEquals(expected, InfixToPostfix.convert(expression));
	}
	
	@Test
	public void testConvert5() {
        String expression = "1 + 2 / 3 * 4 + ( 5 - 6 * 7 )";
        String expected = "1 2 3 / 4 * + 5 6 7 * - +";
        assertEquals(expected, InfixToPostfix.convert(expression));
	}
	
	@Test
	public void testConvert6() {
        String expression = "10 - 9 * 8 / 4 + ( 7 * 5 - 2 ) / 11";
        String expected = "10 9 8 * 4 / - 7 5 * 2 - 11 / +";
        assertEquals(expected, InfixToPostfix.convert(expression));
	}
	
	@Test
	public void testConvert7() {
        String expression = "24 + 33 / 3 * 5 - ( 1 + 5 * 6 ) - 55";
        String expected = "24 33 3 / 5 * + 1 5 6 * + - 55 -";
        assertEquals(expected, InfixToPostfix.convert(expression));
	}
	
	@Test
	public void testConvert8() {
        String expression = "24 + 33 / ( 3 * 5 - 1 + 5 * 6 ) - 55";
        String expected = "24 33 3 5 * 1 - 5 6 * + / + 55 -";
        assertEquals(expected, InfixToPostfix.convert(expression));
	}

    @Test
    public void testConvert9() {
        String expression = "24 + 33 / ( 3 * 5 ^ ( 1 + 5 ) * 6 ) - 55";
        String expected = "24 33 3 5 1 5 + ^ * 6 * / + 55 -";
        assertEquals(expected, InfixToPostfix.convert(expression));
    }

    @Test
    public void testConvert10() {
        String expression = "-4 * ( 20 + 5 ) - 6";
        String expected = "-4 20 5 + * 6 -";
        assertEquals(expected, InfixToPostfix.convert(expression));
    }

    @Test
    public void testConvert11() {
        String expression = "-4.4 * ( 20.77 + 5.1 ) - 6";
        String expected = "-4.4 20.77 5.1 + * 6 -";
        assertEquals(expected, InfixToPostfix.convert(expression));
    }
}
