import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		int[] a = new int[] { 16, 1, 4, 2, 14 };
		int[] b = new int[] { 7, 11, 2, 0, 15 };

		mergeStrings("super", "tower");

	}

	static String mergeStrings(String s1, String s2) {
		StringBuilder stringBuilder = new StringBuilder();

		int s1Index = 0;
		int s2Index = 0;

		mergeString(stringBuilder, s1, s2, s1Index, s2Index);

		System.out.println(stringBuilder.toString());

		return s2;

	}

	static String mergeString(StringBuilder stringBuilder, String s1, String s2, int s1Index, int s2Index) {

		if (s1Index >= s1.length() && s2Index >= s2.length()) {
			return stringBuilder.toString();
		}

		if (s1Index >= s1.length()) {
			for (int i = s2Index; i < s2.length(); i++) {
				stringBuilder.append(s2.charAt(i));
				s2Index++;
			}
			return stringBuilder.toString();
		}
		if (s2Index >= s2.length()) {
			for (int i = s1Index; i < s1.length(); i++) {
				stringBuilder.append(s1.charAt(i));
				s1Index++;
			}
			return stringBuilder.toString();
		}

		try {

			if (s1.charAt(s1Index) < s2.charAt(s2Index)) {
				stringBuilder.append(s1.charAt(s1Index));
				s1Index++;
			}
			if (s1.charAt(s1Index) > s2.charAt(s2Index)) {
				stringBuilder.append(s2.charAt(s2Index));
				s2Index++;
			}

		} catch (Exception e) {
		}

		System.out.println(stringBuilder.toString());

		mergeString(stringBuilder, s1, s2, s1Index, s2Index);

		return stringBuilder.toString();
	}

	static int countTinyPairs(int[] a, int[] b, int k) {
		int count = 0;

		int n = a.length;

		for (int i = 0; i < n; i++) {
			String x = String.valueOf(a[i]);
			String y = String.valueOf(b[n - 1 - i]);
			String xy = x.concat(y);

			System.out.println(xy);

			if (Integer.parseInt(xy) < k) {
				count++;
			}

		}
		System.out.println(count);
		return count;

	}

	static int[] mutateTheArray(int n, int[] a) {
		int b[] = new int[n];

		if (n == 3) {
			b[1] = a[0] + a[1] + a[2];
			b[2] = a[1] + a[2] + 0;
			return b;
		}
		if (n == 2) {
			b[1] = a[0] + a[1] + 0;
			return b;
		}
		if (n == 1) {
			b[0] = a[0];
			return b;
		}

		b[0] = 0 + a[0] + a[1];

		for (int i = 1; i < n - 1; i++) {
			b[i] = a[i - 1] + a[i] + a[i + 1];
		}
		int i = n - 1;
		b[i] = a[i - 1] + a[i] + 0;

		System.out.println(Arrays.toString(b));
		return b;

	}

}