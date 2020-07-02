package Entity;

import java.time.Instant;
import java.util.ArrayList;
import Service.TournamentPreparation;

public class Runner extends Thread {

	private static ArrayList<Runner> winnersList;
	private static String place;
	private int sleepMilis;
	private int distance;
	private String myName = this.getName();
	Instant startTime;
	Instant finishTime;

	public Runner(int sleepMilis, int distance, String place) {
		super();
		startTime = Instant.now();
		this.sleepMilis = sleepMilis;
		this.distance = distance;
		this.place = place;
		winnersList = TournamentPreparation.getListOfRunnersAtPlace(place);
	}

	@Override
	public void run() {

		while (distance > 0) {

			printName();

			try {
				this.sleep(sleepMilis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			distance--;
		}

		winnersList = TournamentPreparation.getListOfRunnersAtPlace(place);
		finishTime = Instant.now();

		synchronized (winnersList) {
			winnersList.add(this);
		}

	}

	private void printName() {
		System.out.println(myName);
	}

	public static Entity.Runner getWinner() {
		if (winnersList.isEmpty()) {
			return null;
		}
		return winnersList.get(0);
	}

	public static int getNumberWinners() {
		return winnersList.size();
	}

	public static void getWinners(int count) {
		winnersList.forEach(System.out::print);

	}

	@Override
	public String toString() {
		return "Runner: " + myName + " [place=" + place + ", startTime=" + startTime + ", finishTime=" + finishTime
				+ "]";
	}

}