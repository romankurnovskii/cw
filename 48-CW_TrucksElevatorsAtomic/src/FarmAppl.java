import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class FarmAppl {

	private static final int N_TRUCKS = 1000;
	private static final int LOAD = 1;
	private static final int N_LOADS = 1000;

	public static void main(String[] args) {
		Truck[] trucks = new Truck[N_TRUCKS];
		Instant start = Instant.now();
		startTrucks(trucks);
		waitingForFinishTruck(trucks);
		System.out.printf("Running time: %d; Elevator1: %d; Elevator2: %d",
				ChronoUnit.MILLIS.between(start, Instant.now()), Truck.getElevator1(), Truck.getElevator2());
	}

	private static void startTrucks(Truck[] trucks) {
		for (int i = 0; i < trucks.length; i++) {
			trucks[i] = new Truck(LOAD, N_LOADS);
			trucks[i].start();
		}
	}

	private static void waitingForFinishTruck(Truck[] trucks) {
		for (Truck truck : trucks) {
			try {
				truck.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
