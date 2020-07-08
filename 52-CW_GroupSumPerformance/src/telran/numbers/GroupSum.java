package telran.numbers;

public abstract class GroupSum {
	int[][] groups;

	public GroupSum(int[][] groups) {
		super();
		this.groups = groups;
	}

	abstract public Long computeSum();
}
