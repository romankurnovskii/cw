
public class Truck extends Thread {
private int load;
private int nLoads;
private static int elevator1;
private static final Object mutex = new Object();
private static int elevator2;
public Truck(int load, int nLoads) {
	super();
	this.load = load;
	this.nLoads = nLoads;
}
public static int getElevator1() {
	return elevator1;
}
public static int getElevator2() {
	return elevator2;
}
@Override
public void run() {
	for (int i = 0; i < nLoads; i++) {
		loadElev1(load);
		loadElev2(load);
	}
}
static synchronized private void loadElev2(int load) {
	elevator2 += load;
	
}
static  private void loadElev1(int load) {
	synchronized (mutex) {
		elevator1 += load;
	}
	
}
}
