package cs2321;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PostfixExpressionTest {
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testEvaluate1() {
		String exp = "13 5 *";
		int result = 65;
		assertEquals(result, PostfixExpression.evaluate(exp));
	}
	
	@Test
	public void testEvaluate2() {
		String exp = "4 20 5 + * 6 -";
		int result = 94;
		assertEquals(result, PostfixExpression.evaluate(exp));
	}
	
	@Test
	public void testEvaluate3() {
        String exp = "2 5 -";
        int result = -3;
        assertEquals(result, PostfixExpression.evaluate(exp));
	}
	
	@Test
	public void testEvaluate4() {
        String exp = "3 4 + 2 * 7 /";
        int result = 2;
        assertEquals(result, PostfixExpression.evaluate(exp));
	}
	
	@Test
	public void testEvaluate5() {
        String exp = "7 4 3 * 1 5 + / *";
        int result = 14;
        assertEquals(result, PostfixExpression.evaluate(exp));
	}
	
	@Test
	public void testEvaluate6() {
        String exp = "11 3 4 8 * + - 6 /";
        int result = -4;
        assertEquals(result, PostfixExpression.evaluate(exp));
	}
	
	@Test
	public void testEvaluate7() {
        String exp = "44 11 / 3 + 22 11 / *";
        int result = 14;
        assertEquals(result, PostfixExpression.evaluate(exp));
	}
	
	@Test
	public void testEvaluate8() {
        String exp = "1 2 3 4 5 6 7 8 9 10 + - + - + - + - +";
        int result = 11;
        assertEquals(result, PostfixExpression.evaluate(exp));
	}

}
