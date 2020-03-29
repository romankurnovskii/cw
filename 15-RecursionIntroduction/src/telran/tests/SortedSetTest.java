package telran.tests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Set;
import telran.util.SortedSet;
import telran.util.TreeSet;

public class SortedSetTest {

	Integer numbers[] = { 10, 20, 11, -8, 7, 13, 9, 100, 2, 70, 15, 21, 121, 500 };
	Set<Integer> set;

	@BeforeEach
	void setUp() {
		set = new TreeSet<Integer>();
		for (Integer num : numbers) {
			set.add(num);
		}
	}

	private void testSetArray(Set<Integer> setTest, Integer[] numbersExpected) {
		Integer[] numbersActual = new Integer[setTest.size()];
		int ind = 0;
		for (Integer num : setTest) {
			numbersActual[ind++] = num;
		}
		Arrays.sort(numbersExpected);
		Arrays.sort(numbersActual);
		assertArrayEquals(numbersExpected, numbersActual);
	}

	@Test
	void testEmptySet() {
		SortedSet<Integer> empty = new TreeSet<Integer>();
		Integer[] expected = {};
		testSetArray(empty.subset(10, true, 100, false), expected);
		testSetArray(empty.head(100, true), expected);
		testSetArray(empty.tail(10, false), expected);
	}

	@Test
	void testWeiredSubset() {
		if (set instanceof SortedSet) {
			SortedSet<Integer> sortedSet = (SortedSet<Integer>) set;
			Integer[] expected = {};
			testSetArray(sortedSet.subset(20, true, 20, false), expected);
			testSetArray(sortedSet.subset(70, true, 13, false), expected);
		}
	}

	@Test
	void testSemiIncludSet() {
		if (set instanceof SortedSet) {
			SortedSet<Integer> sortedSet = (SortedSet<Integer>) set;
			Integer[] expected = { 20 };
			testSetArray(sortedSet.subset(19, true, 21, false), expected);
		}
	}

	@Test
	void testHead() {
		if (set instanceof SortedSet) {
			SortedSet<Integer> sortedSet = (SortedSet<Integer>) set;
			Integer[] exp1 = {};
			Integer[] exp2 = { 10, 11, -8, 7, 13, 9, 2, 15 };
			Integer[] exp3 = { 10, 20, 11, -8, 7, 13, 9, 100, 2, 70, 15, 21 };
			testSetArray(sortedSet.head(-10, true), exp1);
			testSetArray(sortedSet.head(20, false), exp2);
			testSetArray(sortedSet.head(100, true), exp3);
		}
	}

	@Test
	void testTail() {
		if (set instanceof SortedSet) {
			SortedSet<Integer> sortedSet = (SortedSet<Integer>) set;
			Integer[] exp1 = {};
			Integer[] exp2 = { 100, 70, 21, 121, 500 };
			Integer[] exp3 = { 100, 121, 500 };
			testSetArray(sortedSet.tail(600, true), exp1);
			testSetArray(sortedSet.tail(20, false), exp2);
			testSetArray(sortedSet.tail(100, true), exp3);
		}
	}

}
