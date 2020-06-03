package tests.performance;


public abstract class PerformanceTest {
protected String name;
protected int nRuns;

protected PerformanceTest(String name, int nRuns) {
	this.name = name;
	this.nRuns = nRuns;
}
protected abstract void runTest();
public void run() {
	long timestamp = System.currentTimeMillis();
	for ( int i = 0; i < nRuns; i++) {
		runTest();
	}
	long runningTime = System.currentTimeMillis() - timestamp;
	System.out.printf("test name: %s;"
			+ " running time: %d Ms\n",
			name, runningTime);
	
}

}
