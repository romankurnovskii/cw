package telran.threads.realisation;

import java.util.concurrent.atomic.AtomicInteger;

import telran.menu.InputOutput;

public class RaceThreadSimpleResult extends Thread{
	int distance;
	InputOutput io;
	private static AtomicInteger winner = new AtomicInteger(0);
	
	public RaceThreadSimpleResult(String name, int distance, InputOutput io) {
		super(name);
		this.io = io;
		this.distance = distance;
	}
	
	@Override 
	public void run() {		
		int threadId = Integer.parseInt(getName());
		for(int i = 0; i < distance; i++) {
			try {
				sleep(getRandInt(2, 5));
			} catch (InterruptedException e) {}
			io.displayLine(threadId);
		}
		winner.compareAndSet(0, threadId);		
	}

	private long getRandInt(long min, long max) {
		return (long) (Math.random() * (max - min + 1) + min);
	}
	
	public static void resetWinner() {
		winner.set(0);
	}
	public static int getWinner() {
		return winner.get();
	}
}
