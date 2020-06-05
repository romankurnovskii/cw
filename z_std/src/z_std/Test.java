package z_std;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Test {

	public static void main(String args[]) {

		Scanner scanner = new Scanner(System.in);

		String[] line = scanner.nextLine().split(" ");

		System.out.println(line[0] + " --000-- ");

		System.out.println(Arrays.toString(line));

		System.out.println((int) Runtime.getRuntime().freeMemory());

		createPhoneNumber(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 });

	}

	public static String createPhoneNumber(int[] numbers) {
		String reString = String.format("(%d%d%d) %d%d%d-%d%d%d%d", numbers[0], numbers[1], numbers[2], numbers[3],
				numbers[4], numbers[5], numbers[6], numbers[7], numbers[8], numbers[9]);
		return reString;
	}

	public static int duplicateCount(String text) {
		int sum = 0;
		text = text.toLowerCase();

		while (text.length() > 0) {
			String firstletter = text.substring(0, 1);

			text = text.substring(1);

			if (text.contains(firstletter)) {
				sum++;
			}

			text = text.replace(firstletter, "");
		}

		return sum;
	}

}

	