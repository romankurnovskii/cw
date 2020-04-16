package telran.tests;

import telran.tests.perfomance.IndexedListOperations;
import telran.util.Array;
import telran.util.IndexedLinkedList;

public class ListOperationsTestAppl {

	public static void main(String[] args) {

//		perfomanceTest(0);
//		perfomanceTest(20);
//		perfomanceTest(40);
//		perfomanceTest(60);
//		perfomanceTest(80);
		perfomanceTest(100);

	}

	private static void perfomanceTest(int probGet) {

		int nRuns = 100;
		int nNumbers = 10000000;

		IndexedLinkedList<Integer> iList = new IndexedLinkedList<>();
		IndexedListOperations ob2 = new IndexedListOperations("test2", nRuns, iList, nNumbers, 100);
		ob2.run();

		System.out.println("\n");

		Array<Integer> array = new Array<Integer>();
		IndexedListOperations ob1 = new IndexedListOperations("test1", nRuns, array, nNumbers, 100);
		ob1.run();

	}

}
