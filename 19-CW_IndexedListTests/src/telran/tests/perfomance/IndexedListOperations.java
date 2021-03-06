package telran.tests.perfomance;

import java.util.Random;

import telran.util.IndexedList; // from 08 

public class IndexedListOperations extends PerfomanceTest {

	public void setProbGet(int probGet) {
		this.probGet = probGet;
	}

	public IndexedListOperations(String testName, int nRuns, IndexedList<Integer> list, int nNumbers, int probGet) {
		super(testName, nRuns);
		this.list = list;
		this.nNumbers = nNumbers;
		this.probGet = probGet;

		for (int i = 0; i < nNumbers; i++) {
			this.list.add(100);
		}

	}

	int probGet; // number in [0:100] defining probability of running get operaion
	int nNumbers; // amount of numbers
	IndexedList<Integer> list;

	@Override
	public void runTest() {
		 runRemoveAddFirst();
	}

	private void runRemoveAddFirst() {
		this.list.remove(0);
		this.list.add(0, 100);
	}

	private void runGetRandomIndex() {
		
//		int rand = (int) (Math.random() * this.nNumbers);
		
		Random random = new Random();
		int rand = random.ints(0, this.nNumbers)
	      .findFirst()
	      .getAsInt();
	    
		
		this.list.get(rand); // TODO each time index should be generated
		
	}

}
