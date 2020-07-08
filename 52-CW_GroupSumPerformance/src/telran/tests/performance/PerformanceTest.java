package telran.tests.performance;

public abstract class PerformanceTest {

	private String testName;
	private int nRuns;

	public PerformanceTest(String testName, int nRuns) {
		super();
		this.testName = testName;
		this.nRuns = nRuns;
	}

	protected abstract void runTest();

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public void run() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < nRuns; i++) {
			runTest();
		}
		long finish = System.currentTimeMillis();
		System.out.printf("test: %s, nRuns: %d, running time: %d\n", testName, nRuns, finish - start);
	}
}
