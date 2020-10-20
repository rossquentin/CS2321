package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FractionalKnapsackTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMaximumValue1() {
	    int[][] arr  = {{10, 60}, {20, 100}, {30, 120}};
	    int W = 50;
	    double expected = 240;
	    assertEquals(expected, FractionalKnapsack.MaximumValue(arr, W),0.001);
	}

    @Test
    public void testMaximumValue2() {
        int[][] arr  = {{5,100},{15,90},{10, 60}, {20, 100}, {30, 120}};
        int W = 50;
        double expected = 350;
        assertEquals(expected, FractionalKnapsack.MaximumValue(arr, W),0.001);
    }

    @Test
    public void testMaximumValue3() {
        int[][] arr  = {{4, 12}, {8, 32}, {2, 40}, {6, 30}, {1, 50}};
        int W = 10;
        double expected = 124;
        assertEquals(expected, FractionalKnapsack.MaximumValue(arr, W),0.001);
    }

    @Test
    public void testMaximumValue4() {
        int[][] arr  = {{4,10}};
        int W = 3;
        double expected = 7.5;
        assertEquals(expected, FractionalKnapsack.MaximumValue(arr, W),0.001);
    }
}
