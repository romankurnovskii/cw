package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static telran.util.RegularExpression.*;
class RegularExpressionTest {

	@Test
	void testVariableName() {
		//assertTrue("a2b6R4".matches("(\\p{Lower}\\d)+"));
		//assertTrue tests
		assertTrue("$".matches(variableName()));
		assertTrue("A".matches(variableName()));
		assertTrue("a$2".matches(variableName()));
		assertTrue("aA_c12".matches(variableName()));
		assertTrue("__12345_t".matches(variableName()));
		
		//assertFalse tests
		assertFalse("_".matches(variableName()));
		assertFalse("1A_".matches(variableName()));
		assertFalse("ab _".matches(variableName()));
		assertFalse("av_*".matches(variableName()));
		
	}
	@Test
	void testLessThan256() {
		//assertTrue tests
		assertTrue("0".matches(numberLess256()));
		assertTrue("12".matches(numberLess256()));
		assertTrue("129".matches(numberLess256()));
		assertTrue("249".matches(numberLess256()));
		assertTrue("205".matches(numberLess256()));
		assertTrue("255".matches(numberLess256()));
		
		//assertFalse tests
		assertFalse("-1".matches(numberLess256()));
		assertFalse("1111".matches(numberLess256()));
		assertFalse("12#".matches(numberLess256()));
		assertFalse("1 ".matches(numberLess256()));
		assertFalse("256".matches(numberLess256()));
		assertFalse("2 7".matches(numberLess256()));
	}
	@Test
	void testIpV4() {
		//assertTrue tests
		assertTrue("0.0.0.0".matches(ipV4()));
		assertTrue("12.230.188.0".matches(ipV4()));
		assertTrue("0.255.0.255".matches(ipV4()));
		assertTrue("255.255.255.255".matches(ipV4()));
		
		//assertFalse tests
		assertFalse("280.1.2.3".matches(ipV4()));
		assertFalse("240.1.2".matches(ipV4()));
		assertFalse("*.1.2.3".matches(ipV4()));
		assertFalse("255.1.2.+3".matches(ipV4()));
		
		
	}
	
	
	@Test
	void testNumberPhoneIsr() {
		assertTrue("+972-50-1-22-33-44".matches(NumberPhoneIsr()));
		assertTrue("+972541223344".matches(NumberPhoneIsr()));
		assertTrue("057-1223344".matches(NumberPhoneIsr()));
		assertTrue("058-122-33-44".matches(NumberPhoneIsr()));

		assertFalse("057+1223344".matches(NumberPhoneIsr())); 
		assertFalse("050-1-22-33-445".matches(NumberPhoneIsr())); 
		assertFalse("50-1-22-33-44".matches(NumberPhoneIsr())); 
		assertFalse("972-50-1-22-33-445".matches(NumberPhoneIsr())); 
		assertFalse("+972-050-1-22-33-44".matches(NumberPhoneIsr())); 
		assertFalse("050-1-22-33-4t5".matches(NumberPhoneIsr())); 
		assertFalse("057-122—3344".matches(NumberPhoneIsr())); 
		assertFalse("051-122-33-44".matches(NumberPhoneIsr())); 
	}
	
	
	
	@Test
	void testOperatorExpressions() {
		
		assertTrue("2+3 /7".matches(OperatorExpressions()));
		assertTrue("2".matches(OperatorExpressions()));
		assertTrue("2* 3".matches(OperatorExpressions()));

		assertFalse("*3 /7".matches(OperatorExpressions()));
		assertFalse("2.5 +8/2".matches(OperatorExpressions()));
		assertFalse("2*5 +8#2".matches(OperatorExpressions()));
		
            
	}
	
	
	@Test
	void testEmail() {
		assertTrue("tt%2@mail.ru".matches(EmailRegex()));
		assertTrue("tt_67@co.il.d-d.a-a".matches(EmailRegex()));
		assertTrue("tt_67@co.il.d-d.a-a".matches(EmailRegex()));
		assertTrue("t5&4_s@ff.gt".matches(EmailRegex()));

		assertFalse("yu ra@gmail.com".matches(EmailRegex())); // space is disallowed
		assertFalse("yu,ra@gmail.com".matches(EmailRegex())); // comma is disallowed
		assertFalse("tt%2@ma_il.ru".matches(EmailRegex())); // underscore is disallowed in a domain according to initial RFC
		assertFalse("tt_67@co.il.dd.aa.bb".matches(EmailRegex())); // domain can’t comprise of more than 4 parts according to initial RFC
		assertFalse("t5&4_s@ffgt".matches(EmailRegex())); // domain should have at least two parts separated by the dot
	}
	
	
	
	

}
