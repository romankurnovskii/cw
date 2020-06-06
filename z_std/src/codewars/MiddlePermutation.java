package codewars;

import java.math.BigInteger;
import java.util.Arrays;

public class MiddlePermutation {

	private static StringBuilder res;
	private static boolean isFound;
	private static BigInteger fact;
	private static int n;

	public static String findMidPerm(String strng) {
		char[] orderArray = strng.toCharArray();
		Arrays.sort(orderArray);
		strng = new String(orderArray);
		res = new StringBuilder();
		isFound = false;
		n = orderArray.length;
		fact = factorial(new BigInteger(Integer.toString(n)));
		fact = fact.divide(new BigInteger("2"));
		findMidPerm(strng, n);
		return res.toString();
	}

	private static void findMidPerm(String str, int n) {
		if (isFound) {
			return;
		} else {
			int i;
			if (fact.remainder(fact.multiply(new BigInteger("2")).
									divide(new BigInteger(Integer.toString(n))))
					.compareTo(new BigInteger("0")) != 0) {
				i = fact.divide(fact.multiply(new BigInteger("2")).
											divide(new BigInteger(Integer.toString(n))))
						.intValue();
				res.append(str.charAt(i));
				fact = fact.remainder(fact.multiply(new BigInteger("2")).
											divide(new BigInteger(Integer.toString(n))));
				n--;
			} else {
				i = fact.divide(fact.multiply(new BigInteger("2")).
											divide(new BigInteger(Integer.toString(n))))
						.intValue() - 1;
				res.append(str.charAt(i));
				String temp = str.substring(0, i) + str.substring(i + 1);
				for (int j = temp.length() - 1; j >= 0; j--) {
					res.append(temp.charAt(j));
				}
				isFound = true;
			}
			findMidPerm(str.substring(0, i) + str.substring(i + 1), n);
		}
	}

	private static BigInteger factorial(BigInteger x) {
		if (x.intValue() == 1)
			return new BigInteger("1");
		else
			return x.multiply(factorial(x.add(new BigInteger("1").negate())));
	}

}
