package codewars;

import java.util.Arrays;

public class ReturnthefirstMmultiplesofN {

	public static void main(String[] args) {
		int[] ar = (Kata.multiples(1, 341));

		System.out.println(Arrays.toString(ar));

	}

}

class Kata {
	public static int[] multiples(int m, int n) {
		int[] ar = new int[m];

		if (ar.length == 0) {
			return ar;
		}

		if (ar.length == 1) {
			ar[0] = n;
			return ar;
		}
		ar[0] = n;
		for (int i = 1; i < ar.length; i++) {
			ar[i] = ar[i - 1] + n;
		}
		return ar;
	}
}
