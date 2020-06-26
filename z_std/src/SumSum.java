import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Va {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int length = Integer.parseInt(scanner.nextLine());

		String numbers = scanner.nextLine();

		int quantity = Integer.parseInt(scanner.nextLine());

		String[] numbersForChange = new String[quantity];

		for (int i = 0; i < quantity; i++) {
			numbersForChange[i] = scanner.nextLine();

		}

		SumSum sussm = new SumSum(length, numbers, quantity, numbersForChange);

	}

}

public class SumSum {
	
	public static void main(String[] args) {
		Va.main(null);
	}

	private String[] array;
	private long[] arrayNumbers = new long[100001];
	private String[] numbersForChange;
	private long sum;
	StringBuilder stringBuilder = new StringBuilder();

	public SumSum(int length, String numbers, int quantity, String... numbersForChange) {
		this.array = numbers.split(" ");
		this.numbersForChange = numbersForChange;
		run();
	}

	public void run() {
//		long[] res = new long[numbersForChange.length];
		createIntArray();
		String[] twoNumber;
		long[] twoIntNumber = new long[2];
//		int i = 0;
		for (String s : numbersForChange) {
			twoNumber = s.split(" ");
			twoIntNumber[0] = Integer.parseInt(twoNumber[0]);

			twoIntNumber[1] = Integer.parseInt(twoNumber[1]);
			long n = arrayNumbers[(int) twoIntNumber[0]];
			if (n == 0) {
//				res[i] = sum;

//				System.out.println(sum);
				stringBuilder.append(sum +"\n");
//				i++;
				continue;
			}

			long ss = twoIntNumber[1] - twoIntNumber[0];
			sum += ss * n;

//			sum -= twoIntNumber[0] * n;
			arrayNumbers[(int) twoIntNumber[0]] = 0;
			arrayNumbers[(int) twoIntNumber[1]] += n;
//			sum += twoIntNumber[1] * n;
//			res[i] = sum;

//			System.out.println(sum);
			stringBuilder.append(sum +"\n" );

//			i++;
		}

		System.out.println(stringBuilder.toString());
	
	}

	private void createIntArray() {
		for (String s : array) {
			int number = Integer.parseInt(s);
			arrayNumbers[number] += 1;
			sum += number;
		}
	}

}
