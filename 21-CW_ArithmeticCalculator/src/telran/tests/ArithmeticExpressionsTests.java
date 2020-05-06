package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import static telran.util.Calculator.*;

class ArithmeticExpressionsTests {

	@Test
	void testReplaceAll() {
		String str = " abcd%&777777778t12g*";
		assertEquals("abcdtg", removeAllExceptLetters(str));
	}

	private String removeAllExceptLetters(String str) {
		
		return str.replaceAll("[^\\p{Alpha}]+", "");
	}
	@Test
	void testSplit() {
		String str = "    abcd%&777777778t12g*";
		String []tokensExpected = {"", "abcd", "t", "g"};
		String []tokensActual = str.split("[^\\p{Alpha}]+");
		System.out.println(Arrays.toString(tokensActual));
		assertArrayEquals(tokensExpected, tokensActual);
		
		
	}
	@Test
	void testCalculate() {
		assertEquals(10,calculate("2 *10 -5 * 2 / 3"));
		assertEquals(10, calculate("  2 *10 -5 * 2 / 3  "));
		assertEquals(Double.POSITIVE_INFINITY, calculate("2+5/0"));
		assertEquals(Double.NaN, calculate("*10 -5 * 2 / 3"));
		assertEquals(Double.NaN, calculate("2*10 -5 * 2 # 3"));
	}
	@Test 
	void testGetOperationsOperands() {
		String str = "  2 *10 -5 * 2 / 3";
		String[]operands = {"2", "10", "5", "2", "3"};
		String []operations = {"", "*", "-", "*", "/"};
		assertArrayEquals(operands, getOperands(str));
		assertArrayEquals(operations, getOperations(str));
	}
	@Test
	void testCalculateOne() {
		assertEquals(10, calculateOne(20, "2", "/"));
		assertEquals(10, calculateOne(5, "2", "*"));
		assertEquals(Double.NaN, calculateOne(20, "2", "#"));
		assertEquals(Double.POSITIVE_INFINITY,
				calculateOne(20, "0", "/"));
	}

}
