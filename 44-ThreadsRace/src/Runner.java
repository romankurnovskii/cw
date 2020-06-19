
public class Runner extends Thread {

	public Runner(int sleepMilis, int distance, Referee referee) {
		super();
		this.sleepMilis = sleepMilis;
		this.distanse = distance;
		this.referee = referee;
	}

	private int sleepMilis;
	private int distanse;
	private Referee referee;

	@Override
	public void run() {
		String myName = this.getName();

		while (distanse > 0) {
			if (referee.isStop()) {
				this.interrupt();
				break;
			}
			System.out.println(myName);

			try {
				this.sleep(sleepMilis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			distanse--;
		}

		referee.setWinner(myName);
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
