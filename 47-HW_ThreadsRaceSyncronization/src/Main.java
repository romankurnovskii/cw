import java.time.Instant;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import Entity.Runner;
import Service.TournamentPreparation;

public class Main {

	private static int countThreads;
	private static int countWinners; // how many Winners to print
	private static String place;
	private static int distance;
	private static int sleepMilis = new Random().nextInt(2) + new Random().nextInt(50);

	public static void main(String[] args) {

		getRunners();
		startRace();
		printResults();

	}

	private static void getRunners() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter number of threads");
		countThreads = scanner.nextInt();

		System.out.println("Enter number of Winners to print");
		countWinners = scanner.nextInt();

		System.out.println("Enter place");
		place = scanner.next();
		TournamentPreparation.createPlace(place);

		System.out.println("Enter distance");
		distance = scanner.nextInt();

	}

	private static void startRace() {
		System.out.println("Start time: " + Instant.now());

		while (countThreads > 0) {
			new Runner(sleepMilis, distance, place).start();
			countThreads--;
		}

	}

	private static void printResults() {

		while (Runner.getNumberWinners() < countWinners) {
			try {
				Thread.sleep(5);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("RESULTS \n" + "Congratulations to thread \n" + Runner.getWinner() + "\n");
		int i = 1;
		for (Runner r : TournamentPreparation.getListOfRunnersAtPlace(place)) {
			System.out.println(i++ + " " + r);
		}

	}

}

/*
 * 
 * 1. Using any common resource designates a critical section. In the
 * ThreadsRace we used winnerId as the static variable (common resource). You
 * should fix the code using synchronization of the critical area.
 * 
 * 2. Write another version of the ThreadsRace with printing out the results
 * table containing the place, thread number and the race time in milliseconds.
 * The start time for all threads should be the same
 * 
 */