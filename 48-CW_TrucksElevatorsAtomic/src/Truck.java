import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.*;

public class Truck extends Thread {
private int load;
private int nLoads;	
private static AtomicInteger elevator1 = new AtomicInteger(0);
private static AtomicInteger elevator2 = new AtomicInteger(0);

public Truck(int load, int nLoads) {
	super();
	this.load = load;
	this.nLoads = nLoads;
}
public static int getElevator1() {
	return elevator1.get();
}
public static int getElevator2() {
	return elevator2.get();
}

@Override
public void run() {

	for (int i = 0; i < nLoads; i++) {
		loadElev1(load);
		loadElev2(load);
		
	}
}
 private static void loadElev1(int load) {
	 elevator1.getAndAdd(load);
}
 private static  void loadElev2(int load) {
	
	 elevator2.addAndGet(load);
}
}
