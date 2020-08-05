import java.util.Scanner;

class Solution {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		while (true) {
			isPowerOfFour(sc.nextInt());
		}

	}

	static public boolean detectCapitalUse(String word) {

		String a = sc.next();

		if ((a.substring(1).equals(a.substring(1).toUpperCase()) || a.substring(1).equals(a.substring(1).toLowerCase()))
				& a.substring(0, 1).equals(a.substring(0, 1).toUpperCase())) {
			System.out.println(true);
		} else {
			System.out.println(false);
		}

		return false;
	}

	static public boolean isPowerOfFour(int num) {
		if (num == 1) {
			return true;
		}
		if (num == 0) {
			return false;
		}
		if (num == 4) {
			return true;
		}

		boolean res = false;
		
		double aaDouble = Math.log10(num) / Math.log10(4);
		
		if (aaDouble - (int) aaDouble == 0) {
			res = true;
		}
		

		System.out.println(res);
		return res;
	}

}