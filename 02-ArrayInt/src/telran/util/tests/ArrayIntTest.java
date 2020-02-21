package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import telran.util.ArrayInt;

class ArrayIntTest {

	@Test
	void testSearch() {
		int array[] = {10, -20, 12};
		assertEquals(1, ArrayInt.search(array, -20));
		assertTrue(ArrayInt.search(array, -200) < 0);
	}

	@Test
	void testSort() {
		int nNumbers = 1000;
		int array[] = getRandomArray(nNumbers);
		ArrayInt.sort(array);
		for (int i = 1; i < nNumbers; i++) {
			assertTrue(array[i - 1] <= array[i]);
		}
	}

	@Test
	void testBinarySearch() {
		int nNumbers = 1000;
		int expectedIndex = 20;
		int array[] = getRandomArray(nNumbers);
		ArrayInt.sort(array);
		int number = array[expectedIndex];
		assertEquals(expectedIndex, ArrayInt.binarySearch(array, number));
		assertEquals(-1, ArrayInt.binarySearch(array, -1));
	}
	int[] getRandomArray(int nNumbers) {
		int array[] = new int[nNumbers];
		for (int i = 0; i < nNumbers; i++) {
			array[i] = (int) (Math.random() * 1000000);
		}
		return array;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
