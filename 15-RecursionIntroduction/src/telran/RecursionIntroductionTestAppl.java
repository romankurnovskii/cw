package telran;

public class RecursionIntroductionTestAppl {
public static void main(String[] args) {
	//System.out.println(factorial(6));
	System.out.println(pow(5, 2));
	
}

private static int pow(int a, int b) {
if (b == 0) {
	return 1;
}
return a * pow(a, b - 1);
	
}

private static long factorial(int a) {
	if (a == 0) {
		return 1;
	}
	return a * factorial(a-1);

	
}
static int sum(int[]ar) {
	
	return sum(0, ar);
}

private static int sum(int i, int[] ar) {
	if (i == ar.length) {
		return 0;
	}
	return ar[i] + sum(i + 1, ar);
}
}

