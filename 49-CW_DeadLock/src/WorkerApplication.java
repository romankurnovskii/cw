
public class WorkerApplication {
	public static void main(String[] args) throws InterruptedException {
		final int N_RUNS = 1000000;
		Thread worker1 = new Worker(N_RUNS);
		Thread worker2 = new Worker(N_RUNS);
		
		worker1.start();
		worker2.start();
		
		worker1.join();
		worker2.join();
		
		System.out.println("I am done");
		
	}
	
}
