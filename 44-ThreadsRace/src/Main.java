import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

	private static int countThreads;
	private static int distance;
	private static int sleepMilis = new Random().nextInt(2) + new Random().nextInt(3);

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter number of threads");
		countThreads = scanner.nextInt();

		System.out.println("Enter distance");
		distance = scanner.nextInt();

		Referee referee = new Referee();

		while (countThreads > 0) {
			new Runner(sleepMilis, distance, referee).start();
			countThreads--;
		}

		while (referee.getWinner() == null) {

			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("Congratulations to thread " + referee.getWinner());
		
	}

}

/*
 * 
 * Write an application-game “ ThreadsRace ” Each thread should run loop of
 * distance iterations.
 * 
 * At each iteration the thread prints out its own number and sleeps a random
 * amount of milliseconds [2-5] The sleeping time range should be the same for
 * all threads.
 * 
 * Thread that is finishing the loop the first is the winner.
 * 
 * The application should take a number of threads and a distance from the
 * console input/output.
 * 
 * At the end of a game there should be printed out the following message
 * “Congratulations to thread #X (winner)” where X - is the thread-winner number
 * 
 */