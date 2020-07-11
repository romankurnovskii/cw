import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Test {

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		while (true) {

			ar2D();

		}

	}

	public static void ar2D() {
		int[][] arr = new int[6][6];

		for (int i = 0; i < 6; i++) {
			String[] arrRowItems = scanner.nextLine().split(" ");
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			for (int j = 0; j < 6; j++) {
				int arrItem = Integer.parseInt(arrRowItems[j]);
				arr[i][j] = arrItem;
			}
		}

		func(arr);
	}

	public static void func(int[][] ar) {
		int max = Integer.MIN_VALUE;
		int tmpsum = 0;
		for (int i = 0; i < ar.length-2; i++) {
			for (int j = 0; j < ar[i].length - 2; j++) {
				tmpsum = ar[i][j] + ar[i][j + 1] + ar[i][j + 2];
				tmpsum += ar[i + 1][j + 1];
				tmpsum += ar[i + 2][j] + ar[i + 2][j + 1] + ar[i + 2][j + 2];

				max = Math.max(max, tmpsum);
			}

		}
		System.out.print(max);
	}

	public static int convertToBinaryReverse(int n) {
		String bin = "";

		while (n > 0) {
			bin += n % 2;
			n = n / 2;
		}

		System.out.println(bin);

		int count = 1;
		int max = 1;

		for (int i = 0; i < bin.length() - 1; i++) {

			if (bin.charAt(i) == '1' & bin.charAt(i) == bin.charAt(i + 1)) {
				count++;
			} else {
				max = Math.max(max, count);
				count = 1;
			}
		}
		max = Math.max(max, count);
		return max;

	}

	static int factorial(int n) {
		if (n == 2) {
			return 2;
		} else {
			System.out.println(n * factorial(n - 1));
			return n * factorial(n - 1);
		}

	}

}
