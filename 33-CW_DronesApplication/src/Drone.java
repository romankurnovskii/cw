
public class Drone {
	
	public String getHeight() {
		return height;
	}


	public void setHeight(String height) {
		this.height = height;
	}


	public int getnPassangers() {
		return nPassangers;
	}


	public void setnPassangers(int nPassangers) {
		this.nPassangers = nPassangers;
	}


	public int getStartIteration() {
		return startIteration;
	}


	public void setStartIteration(int startIteration) {
		this.startIteration = startIteration;
	}


	public int getFinishIteration() {
		return finishIteration;
	}


	public void setFinishIteration(int finishIteration) {
		this.finishIteration = finishIteration;
	}


	public int getStartQueueIteration() {
		return startQueueIteration;
	}


	public void setStartQueueIteration(int startQueueIteration) {
		this.startQueueIteration = startQueueIteration;
	}


	public int getTotalAirIterations() {
		return totalAirIterations;
	}


	public void setTotalAirIterations(int totalAirIterations) {
		this.totalAirIterations = totalAirIterations;
	}


	public int getTotalQueueIterations() {
		return totalQueueIterations;
	}


	public void setTotalQueueIterations(int totalQueueIterations) {
		this.totalQueueIterations = totalQueueIterations;
	}


	public int getSeqNumber() {
		return seqNumber;
	}


	private int seqNumber;
	private String height;
	private int nPassangers;
	private int startIteration;
	private int finishIteration;
	private int startQueueIteration;
	private int totalAirIterations;
	private int totalQueueIterations;
	
	public Drone(int seqNumber) {
		super();
		this.seqNumber = seqNumber;
	}
	
	

	
	
	
	
	
}
