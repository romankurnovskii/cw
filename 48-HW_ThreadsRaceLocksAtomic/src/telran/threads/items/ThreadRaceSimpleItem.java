package telran.threads.items;

import telran.menu.InputOutput;
import telran.threads.realisation.RaceThreadSimpleResult;

public class ThreadRaceSimpleItem extends ThreadItem {

	public ThreadRaceSimpleItem(InputOutput io) {
		super(io);
	}

	@Override
	public String displayName() {
		
		return "Start race and show winner";
	}

	@Override
	public void perform() {
		int nThreads = io.inputInteger("Enter number of threads");
		int distance = io.inputInteger("Enter distance");
		
		RaceThreadSimpleResult[] threads = new RaceThreadSimpleResult[nThreads];
		for(int i = 0; i<nThreads; i++) {
			threads[i] = new RaceThreadSimpleResult(""+(i+1), distance, io);
			threads[i].start();	
		}
		for(RaceThreadSimpleResult t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		int winner = RaceThreadSimpleResult.getWinner();
		io.displayLine(String.format("Thread #%d Is winner", winner));
		RaceThreadSimpleResult.resetWinner();
	}
}
