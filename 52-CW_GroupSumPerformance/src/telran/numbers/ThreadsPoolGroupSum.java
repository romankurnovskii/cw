package telran.numbers;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ThreadsPoolGroupSum extends GroupSum {
	int nThreads = 3;

	public int getnThreads() {
		return nThreads;
	}

	public void setnThreads(int nThreads) {
		this.nThreads = nThreads;
	}

	public ThreadsPoolGroupSum(int[][] groups) {
		super(groups);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long computeSum() {
		FutureTask<Long> tasks[] = new FutureTask[groups.length];
		ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
		fillTasks(tasks);
		startTasks(tasks, executorService);
		executorService.shutdown();
		return Arrays.stream(tasks).mapToLong(t -> {
			try {
				return t.get();
			} catch (InterruptedException | ExecutionException e) {
				return 0;
			}
		}).sum();
	}

	private void startTasks(FutureTask<Long>[] tasks, ExecutorService executorService) {
		for (FutureTask<Long> task : tasks) {
			executorService.execute(task);
		}

	}

	private void fillTasks(FutureTask<Long>[] tasks) {
		for (int i = 0; i < tasks.length; i++) {
			tasks[i] = new FutureTask<Long>(new OneGroupSum(groups[i]));
		}

	}

}
