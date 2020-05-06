package telran.tests;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import telran.exceptions.RangeException;
import telran.exceptions.RuleException;
import telran.numbers.DividerRule;
import telran.numbers.Generator;

class GeneratorRulesTests {
DividerRule divider10 = new DividerRule(10);
int min = 1, max = 10000, nNumbers = 1000000;
	@Test
	void testGenerate() {
		
		Generator generator = null;
		generator = new Generator(min, max, divider10);
		int ar[] = generator.generate(nNumbers);
		assertEquals(nNumbers, ar.length);
		for(int num: ar) {
			assertTrue(num % 10 == 0 && num >= min && num <= max);
		}
		try {
			generator = new Generator(max, min, divider10);
			fail("Expected IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			
		}
	}
	@Test
	void testDivider()  {
		try {
			divider10.checkRule(10, min, max);
		} catch(Exception e) {
			fail("Unexpected Exception");
		}
		ruleException(12, min, max, -2);
		ruleException(12, 11, max, 8);
		ruleException(-10, 11, max, 30);
		ruleException(20, 10, 15, -10);
		ruleException(18, min, max, 2);
		
		
		try {
			divider10.checkRule(12, 11, 19);
			fail("Expected RangeException");
		}catch (RangeException e) {
			
		} catch (RuleException e) {
			fail("Unexpected Exception");
		}
	}
	private void ruleException(int number, int min, int max, int exp) {
		try {
			divider10.checkRule(number, min, max);
			fail("Expected RuleException");
		}catch (RuleException e) {
			assertEquals(exp, e.getDelta());
		}
	}

}
