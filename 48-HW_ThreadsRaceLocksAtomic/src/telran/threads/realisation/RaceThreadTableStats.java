package telran.threads.realisation;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import telran.menu.InputOutput;

public class RaceThreadTableStats extends Thread{
	int distance;
	InputOutput io;
	private static Lock lock = new  ReentrantLock(true);
	
	public static ArrayList<Integer[]> order = new ArrayList<Integer[]>();
	
	public RaceThreadTableStats(String name, int distance, InputOutput io) {
		super(name);
		this.io = io;
		this.distance = distance;
	}
	
	@Override 
	public void run() {
		Instant start = Instant.now();
		for(int i = 0; i<distance; i++) {
			waitAndDisplayName();
		}
		int duration = (int)ChronoUnit.MILLIS.between(start, Instant.now());		

		saveResult(duration);
		
	}

	private void waitAndDisplayName() {
		try {
			sleep(getRandInt(2, 5));
		} catch (InterruptedException e) {}
		io.displayLine(getName());
	}

	private void saveResult(int duration) {
		try {
			lock.lock();
			Integer[] toAdd = new Integer[2];
			toAdd[0] = Integer.parseInt(getName());
			toAdd[1] = duration;
			order.add(toAdd);
		}finally {
			lock.unlock();
		}
	}

	private long getRandInt(long min, long max) {
		return (long) (Math.random() * (max - min + 1) + min);
	}

	public static void resetStats() {
		order.clear();
	}
}
