package telran.tests.perfomance;

public abstract class PerfomanceTest {

	public PerfomanceTest(String testName, Integer nRuns) {
		super();
		this.testName = testName;
		this.nRuns = nRuns;
	}

	private String testName;
	private Integer nRuns;

	public String getTestName() {
		return testName;
	}

	public Integer getnRuns() {
		return nRuns;
	}

	public void run() {

		long start = System.currentTimeMillis();

		for (int i = 1; i < nRuns; i++) {
			this.runTest();
		}
 
		long finish = System.currentTimeMillis();

		System.out.println("test name: " + this.testName);
		System.out.println("test start time: " + start);
		System.out.println("test finish time: " + finish);
		System.out.println("test execute time in milis: " + (finish - start));

	}

	protected abstract void runTest();

}
