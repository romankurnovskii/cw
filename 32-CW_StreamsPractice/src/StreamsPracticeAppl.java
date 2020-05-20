import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamsPracticeAppl {

	public static void main(String[] args) {
		displayAvgMinMaxSumArray(new int[] { 1, 2, 3, 4, 5 });
		displayAvgMinMaxSumList(new int[] { 1, 2, 3, 4, 5 });
		
		int[] array = {1,2,9,10,20,500,700,100000,200000};
		displayNumbersGroupedByDigitsAmount(array);
		
		displayNumbersGroupedByOddEven(array);

	}

	private static void displayNumbersGroupedByOddEven(int[] array) {
		Map<String, List<Integer>> map = Arrays.stream(array).boxed().
							collect(Collectors.groupingBy(n -> 
										n % 2 == 0 ? "Odd" : "Even"));
		System.out.println(map);
		
	}

	private static void displayNumbersGroupedByDigitsAmount(int[] array) {
		Map<Integer, List<Integer>> map = Arrays.stream(array).boxed().collect(Collectors.groupingBy(n -> Integer.toString(n).length()));
		
		System.out.println(map);
		
	}

	private static void displayAvgMinMaxSumList(int[] list) {
		
		

	}

	private static void displayAvgMinMaxSumArray(int[] array) {

		IntSummaryStatistics statistics = Arrays.stream(array).summaryStatistics();

		System.out.printf("avg = %.1f, max = %d, min = %d\n", statistics.getAverage(), statistics.getMax(),
				statistics.getMin());

	}

}
