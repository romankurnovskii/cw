import java.util.concurrent.locks.*;

public class Truck extends Thread {
private int load;
private int nLoads;	
private static int elevator1;
private static int elevator2;
private static Lock lock1 = new ReentrantLock();
private static Lock lock2 = new ReentrantLock();

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
 private static void loadElev1(int load) {
		try {
			lock1.lock();
			elevator1 += load;
		} finally {
			lock1.unlock();
		}
}
 private static  void loadElev2(int load) {
	 try {
		 lock2.lock();
		 elevator2 += load;
	 }finally {
		lock2.unlock();
	}
}
}
