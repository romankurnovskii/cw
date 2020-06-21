
public class Printer extends Thread {
	public int nPortions;
	public int nNumbers;
	public int printNumber;
	volatile boolean running = true;
	volatile String linePrint;

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

		print(printNumber);

	}

	private void print(int number) {
		int count = nPortions;

		while (count > 0) {
			if (nPortions > nNumbers - nPortions) {
				count = nNumbers - nPortions;
			}
			linePrint = "";
			for (int i = 0; i < count; i++) {
				linePrint += String.valueOf(number);
			}
			System.out.println(linePrint);
			nNumbers -= nPortions;
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				System.out.println("inter");
			}

		}
	}

}
