import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PrimitivesAndStringsTests {


	@Test
	void testIntegers() {
		Integer A = 500;
		Integer B = 100;
		assertEquals(A, B);
		assertTrue(A == B);		// A and B are dif objects!
		
	}
	
	
	@Test
	void testDouble() {
		double a = Double.NaN;
		double b = Double.NaN;
		assertTrue(Double.isNaN(a));
		
	}
	
	@Test
	void testStringEquals() {
		String str1 = "Hello";
		String str2 = "Hello";
		String str3 = new String("Hello");
		assertTrue(str1 == str2);
		assertTrue(str1 == str3);   // !! none
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
