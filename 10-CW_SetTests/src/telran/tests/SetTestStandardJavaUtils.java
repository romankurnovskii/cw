package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.HashSet;

class SetTestStandardJavaUtils {
	Integer numbers[] = {10, 20, 11, -8, 7, 13};
	Set<Integer> set;
	
	
	
	@BeforeEach
	 void setUp() {
		set = new java.util.HashSet<Integer>();
		for(Integer num: numbers) {
			set.add(num);
		}
	}
	
	

	@Test   // все чилса из массива содержатся в сете
	void testAddContains() {  
		for(Integer num: numbers) {
			assertTrue(set.contains(num));   // првоеряем есть ли это число (првоерим все из списка)
		}
		 	assertFalse(set.contains(8));   // првоеряем есть ли число которого нет в спсике
			assertFalse(set.add(-8));
			assertTrue(set.add(8));
			
	}
	
	@Test
	void testIterator() {
							System.out.println("start testIterator java util");
		int expectNumbers = 0;
		
		int realNumbers = 53;				//сумма при переборе
		for (Integer num: set) {
								System.out.println(num);
			expectNumbers +=num;
		}
		assertEquals(expectNumbers, realNumbers);
	}
	
	
	

}
