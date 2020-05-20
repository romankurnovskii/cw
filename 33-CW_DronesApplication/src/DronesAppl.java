import java.util.*;
import java.util.stream.IntStream;

public class DronesAppl {
	
	private static final int N_DRONES = 20;   // N of drones
	private static final int N_HEIGHTS = 15;   //N heights
	private static final int MAX_PASSENGER_TIME = 0;
	private static final int MIN_PASSENGER_TIME = 0;
	private static final int MODEL_TIME = 240;
	static String[] heights;
	static Drone[] drones;
	static List<Drone> dronesInAirDrones;
	static List<Drone> dronesInQueueDrones;
	static HashMap<String, Integer> heightsCounts = new HashMap<>();

	
	public static void main(String[] args) {
		
		preProcessing();
		play();
		postProcessing();
		displayResults();

	}

	private static void displayResults() {
		// TODO Auto-generated method stub
		
	}

	private static void postProcessing() {
		// TODO Auto-generated method stub
		
	}

	private static void play() {
		for (int i = 1; i <= MODEL_TIME; i++) {
			List<String> freeHeights = landingOnIteration(i);
			takingOffOnIteration(i, freeHeights);
		}
		
	}

	private static void takingOffOnIteration(int i, List<String> freeHeights) {
		// TODO Auto-generated method stub
		
	}

	private static List<String> landingOnIteration(int nIteration) {
		List<String> res = new ArrayList<>();
		Iterator<Drone> iterator = dronesInAirDrones.iterator(); 
		while (iterator.hasNext()) {
			Drone drone = iterator.next();
			if (drone.getFinishIteration() == nIteration) {
				iterator.remove();
				int timeInAir = drone.getFinishIteration() - drone.getStartIteration();
				drone.setTotalAirIterations(drone.getTotalAirIterations() + timeInAir);
				
				putInQueue(drone, nIteration);
			}			
		}
		return res;
	}

	private static void preProcessing() {
		createDrones();
		initializeInAirInQueue();
		startDronesInAir();
		startDronesInQueue();
		
	}

	private static void initializeInAirInQueue() {
	
		dronesInAirDrones = new LinkedList<Drone>();
		dronesInQueueDrones = new LinkedList<Drone>();
		
	}

	private static void startDronesInQueue() {
		
		for (int i=N_HEIGHTS; i < N_DRONES; i++) {
		}
		
	}

	private static void startDronesInAir() {
		for (int i=0;i<N_HEIGHTS;i++) {
			takeOff(drones[i], 0, heights[i]);
			putInQueue(drones[i],0);
		}
		
	}

	private static void putInQueue(Drone drone, int i, String string) {
		// TODO Auto-generated method stub
		
	}

	private static void putInQueue(Drone drone, int nIteration) {
		dronesInQueueDrones.add(drone);
		drone.setStartQueueIteration(nIteration);
	}
	

	// поднимается в воздух
	private static void takeOff(Drone drone, int nIteration, String height) {
		dronesInAirDrones.add(drone);
		drone.setStartIteration(nIteration);
		
		int iterationsInAir = getIterationsInAir();
		drone.setFinishIteration(nIteration + iterationsInAir);
		drone.setHeight(height);
		
	}

	private static int getIterationsInAir() {
		return (int) (MIN_PASSENGER_TIME + Math.random() * (MAX_PASSENGER_TIME - MIN_PASSENGER_TIME + 1));
	}

	private static void createDrones() {

		IntStream.range(0, N_DRONES)
			.mapToObj(i -> new Drone(i + 1))
			.toArray(Drone[]::new);
		
	}

}
