import java.util.concurrent.TimeUnit;

public class Referee {

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String name) {
		if (winner == null) {
			winner = name;
		}
	}

	private String winner = null;
	private boolean stop = false;

	private void checkWinner() {

		while (true) {
			if (this.winner != null) {
				stop = true;
				break;
			}

			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public Referee() {
		System.out.println(this.getWinner() + " " + this.isStop());
//		checkWinner();

	}

}
