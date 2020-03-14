package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.HashSet;
import telran.util.Set;




class SetTests {
Integer numbers[] = {10, 20, 11, -8, 7, 13};

Set<Integer> set;


@BeforeEach
 void setUp() {
	set = new HashSet<Integer>();
	for(Integer num: numbers) {
		set.add(num);
	}
}




	@Test   // все чилса из массива содержатся в сете
	void testAddContains() {  
		for(Integer num: numbers) {
			assertTrue(set.contains(num));   // првоеряем есть ли число это
			assertFalse(set.contains(8));   // првоеряем есть ли число которого нет в спсике
			assertFalse(set.add(-8));
			assertTrue(set.add(8));
			
		}
	}

}
