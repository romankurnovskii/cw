package telran.tests.perfomance;

public class JoingStringsPerformance extends PerfomanceTest {

	int nStrings; // number of elements in array (Strings)
	JoinStringsInterface joinStrings;
	String myString = "Hi";
	String[] ar;

	public JoingStringsPerformance(String testName, int nRuns, int nStrings, JoinStringsInterface joinStrings) {
		super(testName, nRuns);
		this.nStrings = nStrings;
		this.joinStrings = joinStrings;

		this.ar = new String[nStrings];

		for (int i = 0; i < this.nStrings; i++) {
			this.ar[i] = myString;
		}

	}

	@Override
	protected void runTest() {

		this.joinStrings.join(this.ar, " ");

	}

}
