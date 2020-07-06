package telran.threads.items;

import telran.menu.InputOutput;
import telran.threads.realisation.RaceThreadTableStats;

public class ThreadRaceTableViewResultItem extends ThreadItem {

	public ThreadRaceTableViewResultItem(InputOutput io) {
		super(io);
	}

	@Override
	public String displayName() {
		return "Start race and show table with results";
	}

	@Override
	public void perform() {
		int nThreads = io.inputInteger("Enter number of threads");
		int distance = io.inputInteger("Enter distance");
		
		RaceThreadTableStats[] threads = new RaceThreadTableStats[nThreads];
		
		generateAndRunRaceThreads(nThreads, distance, threads);
		waitForRacers(threads);
		
		printResultTable();
		RaceThreadTableStats.resetStats();

	}

	private void printResultTable() {
		printHeading();
		printRaceStats();
		
	}
	private void printHeading() {
		io.displayLine("_________________________________");
		io.displayLine("#   | Thread id | Execution time ");
		io.displayLine("---------------------------------");
	}
	private void printRaceStats() {
		int place = 1;
		for(Integer[] line : RaceThreadTableStats.order) {
			io.displayLine(String.format("%-4d| %-10d| %-14d ", place++, line[0], line[1]));
		}
		
	}

	

	private void generateAndRunRaceThreads(int nThreads, int distance, RaceThreadTableStats[] threads) {
		for(int i = 0; i<nThreads; i++) {
			threads[i] = new RaceThreadTableStats(""+(i+1), distance, io);
			threads[i].start();	
		}
	}

	private void waitForRacers(RaceThreadTableStats[] threads) {
		for(RaceThreadTableStats t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
