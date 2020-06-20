
public class Printer extends Thread{
	String symbols;
	volatile boolean running = true;
	public boolean isRunning() {
		return running;
	}
	public void setRunning(boolean running) {
		this.running = running;
	}
	public Printer(String symbols) {
		this.symbols = symbols;
	}
	@Override
	public void run() {
		int index = 0;
		int length = symbols.length();
		while(running) {
			System.out.printf("%d: %c\n",getId(),symbols.charAt(index));
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				index++;
				if (index == length) {
					index = 0;
				}
			}
		}
	}
	

}
