package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.util.Array;

class ArrayTests {
	int numbers[] = {10, -8, 70, 75, 30};
	@Test
	void testAddGetSize() {
		Array array = getArray();
		for (int i = 0; i < array.size(); i++) {
			assertEquals(numbers[i], array.get(i));
		}
		assertNull(array.get(array.size()));
		
	}
	private Array getArray() {
		Array array = new Array(4);
		
		for (int i = 0; i < numbers.length; i++) {
			array.add(numbers[i]);
		}
		return array;
	}
	@Test
	void testAddAtIndex() {
		int expectedNumbers[] = {-10, 10, -8, 70, -70, 75, 30, -30};
		Array array = getArray();
		assertTrue(array.add(0, -10));
		assertTrue(array.add(4, -70));
		assertTrue(array.add(7, -30));
		int actualNumbers[] = getActualNumbers(array);
		assertArrayEquals(expectedNumbers, actualNumbers );
		assertFalse(array.add(-1, 100));
		assertFalse(array.add(100, 100));
		
	}
	private int[] getActualNumbers(Array array) {
		int res[] = new int[array.size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = (int) array.get(i);
		}
		return res;
	}
	@Test
	void testRemoveAtIndex() {
		int expectedNumbers[] = { -8, 75};
		Array array = getArray();
		assertEquals(10, array.remove(0));
		assertEquals(70, array.remove(1));
		assertEquals(30, array.remove(2));
		assertArrayEquals(expectedNumbers, getActualNumbers(array));
		assertNull(array.remove(2));
		assertNull(array.remove(-1));
	}
	@Test
	void testSetAtIndex() {
		int expectedNumbers[] = {-10, -8, -70, 75, -30};
		Array array = getArray();
		assertEquals(10, array.set(0, -10));
		assertEquals(70, array.set(2, -70));
		assertEquals(30, array.set(4, -30));
		assertArrayEquals(expectedNumbers, getActualNumbers(array));
		assertNull(array.set(-1, 100));
		assertNull(array.set(100, 100));
	}

}
