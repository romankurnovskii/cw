package telran.tests.performance;

import telran.numbers.GroupSum;

public class PerformanceGroupSumTest extends PerformanceTest {

	GroupSum groupSum;

	public PerformanceGroupSumTest(String testName, int nRuns, GroupSum groupSum) {
		super(testName, nRuns);
		this.groupSum = groupSum;
	}

	@Override
	protected void runTest() {
		groupSum.computeSum();

	}

}
