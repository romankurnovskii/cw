package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import telran.util.ArrayInt;

class ArrayIntTest {

	@Test
	void testSearch() {
		int array[] = { 10, -20, 12 };
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
//		int nNumbers = 1000;
//		int expectedIndex = 20;
//		int array[] = getRandomArray(nNumbers);
//		ArrayInt.sort(array);
//		int number = array[expectedIndex];
//		assertEquals(expectedIndex, ArrayInt.binarySearch(array, number));
//		assertEquals(-1, ArrayInt.binarySearch(array, -1));
		int ar[] = { 10, 20, 20, 30, 30, 40, 50, 60, 70, 70, 70, 200, 300, 400 };
		assertEquals(0, ArrayInt.binarySearch(ar, 10));
		assertEquals(6, ArrayInt.binarySearch(ar, 50));
		assertEquals(-2, ArrayInt.binarySearch(ar, 15));
		assertEquals(-4, ArrayInt.binarySearch(ar, 25));
		assertEquals(1, ArrayInt.binarySearch(ar, 20));
		assertEquals(3, ArrayInt.binarySearch(ar, 30));
		assertEquals(3, ArrayInt.countSorted(ar, 70));
	}

	int[] getRandomArray(int nNumbers) {
		int array[] = new int[nNumbers];
		for (int i = 0; i < nNumbers; i++) {
			array[i] = (int) (Math.random() * 1000000);
		}
		return array;
	}

	@Test
	void testInsertNumber() {
		int ar[] = { 1, 2, -5, 10, 8 };
		int exp0[] = { 100, 1, 2, -5, 10, 8 };
		int exp5[] = { 1, 2, -5, 10, 8, 50 };
		int exp3[] = { 1, 2, -5, 30, 10, 8 };
		assertArrayEquals(exp0, ArrayInt.insert(ar, 0, 100));
		assertArrayEquals(exp5, ArrayInt.insert(ar, 5, 50));
		assertArrayEquals(exp3, ArrayInt.insert(ar, 3, 30));
		assertArrayEquals(ar, ArrayInt.insert(ar, -5, 50));
	}

	@Test
	void testRemoveNumber() {
		int ar[] = { 1, 2, -5, 10, 8 };
		int exp0[] = { 2, -5, 10, 8 };
		int exp4[] = { 1, 2, -5, 10 };
		int exp3[] = { 1, 2, -5, 8 };
		assertArrayEquals(exp0, ArrayInt.remove(ar, 0));
		assertArrayEquals(exp4, ArrayInt.remove(ar, 4));
		assertArrayEquals(exp3, ArrayInt.remove(ar, 3));
		assertArrayEquals(ar, ArrayInt.remove(ar, -1));
		assertArrayEquals(ar, ArrayInt.remove(ar, ar.length));
	}

	@Test
	void testInsertSorted() {
		int ar[] = { 10, 20, 30, 40, 50 };
		int exp0[] = { 5, 10, 20, 30, 40, 50 };
		int exp5[] = { 10, 20, 30, 40, 50, 55 };
		int exp3[] = { 10, 20, 30, 35, 40, 50 };
		assertArrayEquals(exp0, ArrayInt.insertSorted(ar, 5));
		assertArrayEquals(exp5, ArrayInt.insertSorted(ar, 55));
		assertArrayEquals(exp3, ArrayInt.insertSorted(ar, 35));
	}

	// We use the method to simplify comparing of arrays
	private void compareArrayWithSorting(int[] expected, int[] actual) {
		Arrays.sort(expected);
		Arrays.sort(actual);
		assertArrayEquals(expected, actual);
	}

	/* tests for HW #3 */
	@Test
	void testUnion() {
		int ar1[] = { 10, 30, -8, 20 };
		int ar2[] = { 0, -3, 7, 11 };
		int ar3[] = { 0, -8, 20, 10 };
		int exp1[] = { 10, 30, -8, 20, 0, -3, 7, 11 };
		int exp2[] = { 10, 30, -8, 20, 0 };
		compareArrayWithSorting(exp1, ArrayInt.union(ar1, ar2));
		compareArrayWithSorting(exp2, ArrayInt.union(ar1, ar3));
	}

	@Test
	void testIntersection() {
		int ar1[] = { 10, 30, -8, 20 };
		int ar2[] = { 0, -3, 7, 11 };
		int ar3[] = { 0, -8, 20, 10 };
		int exp1[] = {};
		int exp2[] = { 10, -8, 20 };
		compareArrayWithSorting(exp1, ArrayInt.intersection(ar1, ar2));
		compareArrayWithSorting(exp2, ArrayInt.intersection(ar1, ar3));
	}

	@Test
	void testDifference() {
		int ar1[] = { 10, 30, -8, 20 };
		int ar2[] = { 0, -3, 7, 11 };
		int ar3[] = { 0, -8, 20, 10 };
		int exp1[] = ar1;
		int exp2[] = { 30 };
		compareArrayWithSorting(exp1, ArrayInt.difference(ar1, ar2));
		compareArrayWithSorting(exp2, ArrayInt.difference(ar1, ar3));
	}

}
