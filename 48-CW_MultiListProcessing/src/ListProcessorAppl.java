import java.util.*;

public class ListProcessorAppl {

	private static final int N_PROCESSORS = 100;
	private static final int N_NUMBERS = 1_000_000;
	private static final int N_RUNS = 10_000;
	private static final int PROB_UPDATE = 50;

	public static void main(String[] args) {
		List<Integer> list = getList();
		ListProcessor[] processors = new ListProcessor[N_PROCESSORS];
		startProcessors(processors, list);
		waitProcessors(processors);
		
		System.out.println("Blocking iterations: " + ListProcessor.getCountLockIterations());
	}

	private static List<Integer> getList() {
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < N_NUMBERS; i++) {
			res.add(100);
		}
		return res;
	}

	private static void startProcessors(ListProcessor[] processors, List<Integer> list) {
		for (int i = 0; i < processors.length; i++) {
			processors[i] = new ListProcessor(list, N_RUNS, PROB_UPDATE);
			processors[i].start();
		}		
	}

	private static void waitProcessors(ListProcessor[] processors) {
		for (ListProcessor processor : processors) {
			try {
				processor.join();
			} catch (InterruptedException e) {}
		}
	}

}
