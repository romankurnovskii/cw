
public class Printer extends Thread {
	public int nPortions;
	public int nNumbers;
	public int printNumber;
	volatile boolean running = true;
	String linePrint;

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public Printer(int printNumber, int nPortions, int nNumbers) {
		this.nPortions = nPortions;
		this.nNumbers = nNumbers;
		this.printNumber = printNumber;
	}

	@Override
	public void run() {

		while (true) {
			firstSleep();
		}

	}

	private void firstSleep() {

		try {
			this.sleep(Integer.MAX_VALUE);

		} catch (InterruptedException e) {
			print(printNumber);
		}

	}

	private void print(int number) {

		int count = nPortions;
		if (count > 0) {

			if (nPortions > nNumbers - nPortions) {
				count = nNumbers - nPortions;
			}
			linePrint = "";
			for (int i = 0; i < count; i++) {
				linePrint += String.valueOf(number);
			}
			System.out.println(linePrint);
			nNumbers -= nPortions;

		}

	}

}
