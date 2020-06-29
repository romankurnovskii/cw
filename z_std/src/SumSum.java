import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Va {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int length1 = Integer.parseInt(scanner.nextLine());

		String numbers = scanner.nextLine();

		SumSum sussm = new SumSum(numbers);

		int length2 = Integer.parseInt(scanner.nextLine());

		sussm.run(length2, scanner);


	}

}

public class SumSum {

	public static void main(String[] args) {
		Va.main(null);
	}

	private String[] array;
	private int[] arrayNumbers = new int[100001];
	private long sum;
	StringBuilder sb = new StringBuilder();

	public SumSum(String numbers) {
		this.array = numbers.split(" ");
		createIntArray();
	}

	public void run(int length, Scanner scanner) {
		for (int i = 0; i < length; i++) {
			int oldNumber = scanner.nextInt();
			int newNumber = scanner.nextInt();

			int quantity = arrayNumbers[oldNumber];
			if (quantity == 0) {
				sb.append(sum).append("\n");
				continue;
			}

			int difference = newNumber - oldNumber;
			sum += (difference * quantity);
			sb.append(sum).append("\n");
			arrayNumbers[oldNumber] = 0;
			arrayNumbers[newNumber] += quantity;
		}

		System.out.println(sb.toString());
	}

	private void createIntArray() {
		for (String s : array) {
			int number = Integer.parseInt(s);
			arrayNumbers[number] += 1;
			sum += number;
		}
	}

}
