package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.numbers.ThreadsPoolGroupSum;

class ThreadsPoolGroupTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testComputeSum() {
		int[][] groups = { { 1, 2 }, { 3, 4 }, { 4, 5 } };
		ThreadsPoolGroupSum groupSum = new ThreadsPoolGroupSum(groups);
		assertEquals(19, groupSum.computeSum());
	}

}
