package AtCoder;

import java.util.Arrays;
import java.util.Scanner;

public class aising2020 {

	public static void main(String[] args) {

		while (true) {
			c.main(null);
		}

//		b.main(null);

	}

}

class c {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = Integer.parseInt(scanner.nextLine());

		int sum = 0;
		int x = 1;
		int y = 1;
		int z = 1;

		for (int i = 0; i < n; i++) {

			int count = 0;
			sum = 0;

			while (sum < n) {

				sum = calc(x, y, z);

				if (sum == n) {
					if (x != y) {
						count++;
					}
					if (x != z) {
						count++;
					} else if (z != y) {
						count++;
					}
					break;
				}
				if (sum > n) {
					break;
				}

				// plus 1 to in

				x += 1;
				y = Math.min(Math.min(x, y), z);
//				z = Math.max(Math.max(x, y), z);

				System.out.println(x + " " + y + " " + z);

			}
			System.out.println(count);
		}

	}

	static int calc(int x, int y, int z) {
		return x * x + y * y + z * z + x * y + y * z + x * z;
	}

}

class b {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = Integer.parseInt(scanner.nextLine());
		String[] ar = scanner.nextLine().split(" ");

		int count = 0;

		for (int i = 0; i < ar.length; i += 2) {
			if (Integer.parseInt(ar[i]) % 2 != 0) {
				count++;
			}
		}

		System.out.println(count);

	}

}

class a {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int l = scanner.nextInt();
		int r = scanner.nextInt();
		int d = scanner.nextInt();

		int count = (r - l) / d;
		if (r % d == 0) {
			count++;
		}

		if (r == l) {
			count = 1;
		}

		System.out.println(count);

	}

}