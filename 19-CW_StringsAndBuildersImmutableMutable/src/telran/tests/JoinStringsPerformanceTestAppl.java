package telran.tests;

import telran.tests.perfomance.JoingStringsPerformance;
import telran.util.JoinStringsImplBuilder;
import telran.util.JoinStringsImplString;

public class JoinStringsPerformanceTestAppl {

	public static void main(String[] args) {

		System.out.println("start with JoinStringsImplBuilder \n");
		JoingStringsPerformance ob1 = new JoingStringsPerformance("test1", 10000, 500, new JoinStringsImplBuilder());
		ob1.run();

		System.out.println("\nstart with JoinStringsImplString \n");
		JoingStringsPerformance ob2 = new JoingStringsPerformance("test2", 10000, 500, new JoinStringsImplString());
		ob2.run();
		
		
		
	
		
		

	}

}
