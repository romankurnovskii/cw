package telran.numbers;

import java.util.Arrays;

public class GroupSumWithoutThreads extends GroupSum {

	int[][] groups;
	
	public GroupSumWithoutThreads(int[][] groups) {
		super(groups);
		this.groups=groups;
		computeSum();
	}

	@Override
	public Long computeSum() {

		Long resLong = Arrays.stream(groups).mapToLong(
				arr -> Arrays.stream(arr).sum()).sum();

		     System.out.println(resLong);
		     return resLong;
	}

}
