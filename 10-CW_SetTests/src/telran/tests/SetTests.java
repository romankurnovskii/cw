package telran.tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Set;
import telran.util.SortedSet;
import telran.util.HashSet;

class SetTests {
Integer numbers[] = {10, 20, 11,-8, 7, 13, 9, 100, 2, 70, 15, 21, 121, 500};
Set<Integer> set;
@BeforeEach
void setUp() {
	set = new HashSet<Integer>();
	for(Integer num: numbers) {
		set.add(num);
	}
}
	@Test
	void testAddContains() {
		for(Integer num: numbers) {
			assertTrue(set.contains(num));
		}
		assertFalse(set.contains(8));
		assertFalse(set.add(-8));
		assertTrue(set.add(8));
	}
	@Test
	void testIterator() {
		testSetArray(set, numbers);
	}
	private void testSetArray(Set<Integer> setTest, Integer[] numbersExpected) {
		Integer[] numbersActual = new Integer[setTest.size()];
		int ind = 0;
		for(Integer num: setTest) {
			numbersActual[ind++] = num;
			
		}
		Arrays.sort(numbersExpected);
		Arrays.sort(numbersActual);
		assertArrayEquals(numbersExpected, numbersActual);
		
	}
	@Test
	void testFilter() {
		Set<Integer> evenSet = set.filter(new EvenNumbersPredicate());
		Integer[] evenNumbers = {10, 20, -8,  100, 2, 70,  500};
		testSetArray(evenSet, evenNumbers);
	}
	@Test
	void testRemoveObj() {
		Integer[] numbersNo100 = {10, 20, 11,-8, 7, 13, 9,  2, 70, 15, 21, 121, 500};
		assertEquals(100, set.remove(100));
		assertNull(set.remove(100));
		testSetArray(set, numbersNo100);
	}
	@Test
	void testRemoveIf() {
		Integer[] oddNumbers = {11,7, 13, 9, 15, 21, 121};
		assertTrue(set.removeIf(new EvenNumbersPredicate()));
		testSetArray(set, oddNumbers);
		
	}
	@Test
	void testRemoveAll() {
		set.removeIf(new TruePredicate());
		assertEquals(0, set.size());
	}
	

	
	
	// test for sorted test
	@Test
	void testMinElement() {
		if (set instanceof SortedSet) {			
			SortedSet<Integer> sortedSet = (SortedSet<Integer>) set;
			assertEquals(-8, sortedSet.getMin());
		}
	}
	
	@Test
	void testMaxElement() {
		if (set instanceof SortedSet) {
			SortedSet<Integer> sortedSet = (SortedSet<Integer>) set;
			assertEquals(500, sortedSet.getMax());
		}
	}
	@Test
	void testHead() {
		if (set instanceof SortedSet) {
			SortedSet<Integer> sortedSet = (SortedSet<Integer>) set;
			Integer[] lessThan10 = {2,-8,7,9};
			Integer[] lessThanEqual10 =  {2,-8,7,9,10};
//			testSetArray(sortedSet.head(10, false), lessThan10);
//			testSetArray(sortedSet.head(10, true), lessThanEqual10);
		}
	}
	
	@Test
	void testTail() {
		if (set instanceof SortedSet) {
			SortedSet<Integer> sortedSet = (SortedSet<Integer>) set;
			
			assertEquals(500, sortedSet.getMax());
		}
	}
	
	
	
	
	
	
	
}
