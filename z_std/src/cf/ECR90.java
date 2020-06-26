package cf;

import java.util.Scanner;

public class ECR90 {

	public static void main(String[] args) {

//		A.main(null);
		C.main(null);

	}

}

class A {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		int a = scanner.nextInt();
		int b = scanner.nextInt();
		int c = scanner.nextInt();

		int aa = 0;
		int bb = 0;

		int secondForOne = c / b;

		if (a > secondForOne) {

		}

		if (a <= secondForOne) {
			bb = -1;
			int sum = 0;
			while (sum <= c) {
				sum += a;
				aa++;
			}
		} else if (c / b % 2 != 0) {
			if (secondForOne < a) {
				if (b == a) {
					aa = 1;
				} else {
					aa = -1;
				}

				bb = b;
				System.out.println(aa + " " + bb);
				return;
			}
		}

		System.out.println(aa + " " + bb);

	}
}

class B {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
//
		int times = scanner.nextInt();

		do {

			String lineString = scanner.next();

			int length = lineString.length();
			int one = length - lineString.replaceAll("1", "").length();
			int zero = length - lineString.replaceAll("0", "").length();

			int count = Math.min(one, zero);

			if (count % 2 == 0) {
				System.out.println("NET");
			} else {
				System.out.println("DA");
			}

		} while (times-- > 1);

		return;

	}

}

class C {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
//
		int times = scanner.nextInt();

		do {

			String lineString = scanner.next();
			char[] s = lineString.toCharArray();

			long res = 0;
			int init = 0;
			int cur;
			boolean ok;
			while (true) {

				cur = init;
				ok = true;

				for (int i = 0; i < lineString.length(); i++) {
					res++;

					if (s[i] == '+') {
						cur = cur + 1;

					} else {
						cur = cur - 1;
					}

					if (cur < 0) {
						ok = false;
						break;
					}
				}

				if (ok) {
					break;
				}
				init++;
			}

			System.out.println(res);

		} while (times-- > 1);

//		return;

	}

}
