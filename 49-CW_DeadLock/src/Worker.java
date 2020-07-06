public class Worker extends Thread {
	int nRuns;
	static final Object MUTEX1 = new Object();
	static final Object MUTEX2 = new Object();
	
	public Worker(int nRuns) {
		this.nRuns = nRuns;
	}

	@Override
	public void run() {
	for (int i = 0; i < nRuns; i++) {
		f1();
		f2();
	}	
	}

	private void f1() {
		synchronized (MUTEX1) {
			synchronized (MUTEX2) {
				
			}
		}
	}

	private void f2() {
		synchronized (MUTEX1) {
			synchronized (MUTEX2) {
				
			}
		}
	}
}
