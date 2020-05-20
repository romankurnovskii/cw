import java.util.Random;
import java.util.stream.Collectors;

public class StreamFunctionalityAppl {

	public static void main(String[] args) {
		//displaySportLotoNumbers(1, 49, 7);
		//displayShufflingArray(new int[] {10,20,30,40,50,60,70});
		//displayDigitStatistics();

	}
	private static void displayDigitStatistics() {
		new Random().ints(1_000_000, 1, Integer.MAX_VALUE)
		.flatMap(n -> Integer.toString(n).chars())/* getting stream of digits */
		.boxed() /* getting Stream<Integer> */
		.collect(Collectors.groupingBy(n -> n, Collectors.counting()))
		.entrySet().stream().sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
		.forEach(e -> System.out.printf("%c : %d\n", e.getKey(),e.getValue()));
		
	}
	
	
	public static void displaySportLotoNumbers(int min, int max, int nNumbers) {
		if (max - min + 1 < nNumbers) {
			throw new RuntimeException("Impossible to create the given amount"
					+ " of unique numbers " + nNumbers);
		}
		new Random().ints(min, max + 1)
		.distinct().limit(nNumbers).forEach(n ->
		System.out.print(n + " "));
	}
	
	
	public static void displayShufflingArray(int [] ar) {
		new Random().ints(0, ar.length )
		.distinct().limit(ar.length).forEach(i ->
		System.out.print(ar[i] + " "));
	}

}
