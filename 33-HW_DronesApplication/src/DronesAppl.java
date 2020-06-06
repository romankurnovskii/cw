import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DronesAppl {
private static final int N_DRONES = 20;
private static final int N_HEIGHTS = 15;
private static final int MIN_PASSENGER_TIME = 1;
private static final int MAX_PASSENGER_TIME = 15;
private static final int MODEL_TIME = 240;
static Drone[] drones;
static List<Drone> dronesInAir;
static List<Drone> dronesInQueue;
static String[] heights;
static HashMap<String, Integer> heightsCounts = new HashMap<>();

	public static void main(String[] args) {
		preProcessing();
		play();
		postProcessing();
		testApplication();
		displayResults();

	}

	private static void testApplication() {
		testSumAirQueue();
		testFlightsCount();
		
	}

	private static void testFlightsCount() {
		int sumCountsFromDrones = Arrays.stream(drones)
				.mapToInt(d -> d.getnPassengers()).sum();
		int sumCountsFromHeights = heightsCounts
				.values().stream().mapToInt(v -> v).sum();
		if (sumCountsFromDrones != sumCountsFromHeights) {
			throw new RuntimeException("Number of passengers should equal the number of flights "
		+ sumCountsFromDrones + " " + sumCountsFromHeights);
		}
		
	}

	private static void testSumAirQueue() {
		if (Arrays.stream(drones).anyMatch(d -> d.getTotalAirIterations() + d.getTotalQueueIterations() != MODEL_TIME)) {
			throw new RuntimeException("Air + Queue should equal MODEL_TIME");
		}
		
	}

	private static void displayResults() {
		displayDrones();
		displayHeights();
		
	}
	private static void displayHeights() {
		heightsCounts.forEach((k, v) ->
		System.out.printf("%s: %d flights\n", k, v));
		displayMostUsedHeights();
		
	}

	private static void displayMostUsedHeights() {
		long maxFlights = Collections.max(heightsCounts.values());
		List<String> mostUsedHeights = 
				heightsCounts.entrySet()
				.stream().filter(e -> e.getValue() ==
				maxFlights).map(Entry::getKey)
				.collect(Collectors.toList());
		System.out.printf("The most used heights: %s\n",
				mostUsedHeights);
	}

	private static void displayDrones() {
		Arrays.stream(drones).forEach(DronesAppl::displayDrone);
		
	}

	private static void displayDrone(Drone drone) {
		System.out.printf("Drone %d was in air %d"
				+ " minutes. It transferred %d"
				+ " passengers. It was in waiting queue"
				+ " %d minutes \n",
				drone.getSeqNumber(),
				drone.getTotalAirIterations(),
				drone.getnPassengers(),
				drone.getTotalQueueIterations());
	}

	private static void postProcessing() {
		postProcessingInAir();
		postProcessingInQueue();
		
	}

	private static void postProcessingInQueue() {
		dronesInQueue.forEach(d -> {
			droneQueueExit(MODEL_TIME, d);
		});
		
	}

	private static void postProcessingInAir() {
		dronesInAir.forEach(d -> {
			int timeInAir =
					MODEL_TIME - d.getStartIteration();
			d.setTotalAirIterations
			(d.getTotalAirIterations() + timeInAir);
		});
		
	}

	private static void play() {
		for (int i = 1; i <= MODEL_TIME; i++) {
			List<Drone> dronesLanded = landingDronesOnIteration(i);
			takingOffOnIteration(i, dronesLanded);
		}
		
	}

	private static void takingOffOnIteration(int nIteration,
			List<Drone> dronesLanded) {
		
		dronesLanded.forEach(d -> {
			Drone droneForTakingOff =
					dronesInQueue.isEmpty() ? d :
						getDroneFromQueue(nIteration);
			takeOff(droneForTakingOff, nIteration, d.getHeight());
			
		});
		
	}

	private static Drone getDroneFromQueue(int nIteration) {
		Drone resDrone = dronesInQueue.remove(0);
		droneQueueExit(nIteration, resDrone);
		return resDrone;
	}

	private static void droneQueueExit(int nIteration, Drone resDrone) {
		int timeInQueue = nIteration -
				resDrone.getStartQueueIteration();
		resDrone.setTotalQueueIterations
		(resDrone.getTotalQueueIterations() + timeInQueue);
	}

	private static List<Drone> landingDronesOnIteration(int nIteration) {
		List<Drone> res = new ArrayList<>();
		Iterator<Drone> it = dronesInAir.iterator();
		while(it.hasNext()) {
			Drone drone = it.next();
			
			if(drone.getFinishIteration() == nIteration) {
				it.remove();
				res.add(drone);
				landingDrone(nIteration,  drone);
			}
			
		}
		return res;
	}

	private static void landingDrone(int nIteration,  Drone drone) {
			int timeInAir =
					drone.getFinishIteration() - drone.getStartIteration();
			drone.setTotalAirIterations
			(drone.getTotalAirIterations() + timeInAir);
			putInQueue(drone, nIteration);
	}

	private static void preProcessing() {
		createDrones();
		createHeights();
		initializeInAirInQueue();
		startDronesInAir();
		startDronesInQueue();
		
	}

	private static void createHeights() {
		heights = IntStream.rangeClosed(1, N_HEIGHTS)
		.mapToObj(i -> "Height" + i).toArray(String[]::new);
		
	}

	private static void initializeInAirInQueue() {
		dronesInAir = new LinkedList<Drone>();
		dronesInQueue = new LinkedList<Drone>();
		
	}

	private static void startDronesInQueue() {
		for(int i = N_HEIGHTS; i < N_DRONES; i++) {
			putInQueue(drones[i], 0);
		}
		
	}

	private static void putInQueue(Drone drone, int nIteration) {
		dronesInQueue.add(drone);
		drone.setStartQueueIteration(nIteration);
		
	}

	private static void startDronesInAir() {
		for (int i = 0; i < N_HEIGHTS; i++) {
			takeOff(drones[i], 0, heights[i]);
		}
		
	}

	private static void takeOff(Drone drone, int nIteration, String height) {
		dronesInAir.add(drone);
		drone.setStartIteration(nIteration);
		int iterationsInAir = getIterationsInAir();
		drone.setFinishIteration(nIteration + iterationsInAir);
		drone.setHeight(height);
		drone.setnPassengers(drone.getnPassengers() + 1);
		heightsCounts.merge(height, 1, (a,b) -> a + b);
		
		
	}

	private static int getIterationsInAir() {
		
		return (int) (MIN_PASSENGER_TIME +
				Math.random() * (MAX_PASSENGER_TIME -
						MIN_PASSENGER_TIME + 1));
	}

	

	private static void createDrones() {
		
		drones = IntStream.range(0, N_DRONES)
		.mapToObj(i -> new Drone(i + 1))
		.toArray(Drone[]::new);
		
	}

}
