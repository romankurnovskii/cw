package telran.numbers;

import java.util.Arrays;
import java.util.Random;

import telran.numbers.ThreadsPoolGroupSum;
import telran.tests.performance.PerformanceGroupSumTest;
import telran.tests.performance.PerformanceTest;

public class TestPerformanceApp {

	private static final int N_GROUPS = 2000;
	private static final int N_NUMBERS_IN_GROUPS = 100_000;

	private static final int[] N_OF_THREADS = { 1, 3, 10, 100, 1000, N_GROUPS };

	private static final int N_RUNS = 10;

	public static void main(String[] agrs) {
		int[][] groups = generateRandomGroups();
		System.out.printf("Test info: number of groups = %d; number of numbers %d\n", N_GROUPS, N_NUMBERS_IN_GROUPS);
		Arrays.stream(N_OF_THREADS).forEach(nThreads -> testExecution(nThreads, groups));
		
		new GroupSumWithoutThreads(groups);
	}

	public static int[][] generateRandomGroups() {
		Random rnd = new Random();
		int[][] groups = new int[N_GROUPS][N_NUMBERS_IN_GROUPS];
		for (int i = 0; i < N_GROUPS; i++) {
			for (int j = 0; j < N_NUMBERS_IN_GROUPS; j++) {
				groups[i][j] = rnd.nextInt();
			}
		}
		return groups;
	}

	public static void testExecution(int nThreads, int[][] groups) {
		String testName = "Number of threads: " + nThreads;

		ThreadsPoolGroupSum groupSum = new ThreadsPoolGroupSum(groups);
		groupSum.setnThreads(nThreads);

		PerformanceTest performanceTest = new PerformanceGroupSumTest(testName, N_RUNS, groupSum);
		performanceTest.run();
	}
	


}
