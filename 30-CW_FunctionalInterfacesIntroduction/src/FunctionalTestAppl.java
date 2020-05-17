import java.util.Arrays;

public class FunctionalTestAppl {

	public static void main(String[] args) {

		String strings[] = { "p", "abc", "l", "lmno", "lmn" };

		// using lambda expression
		Arrays.sort(strings, (a, b) -> a.length() - b.length());

		System.out.println(Arrays.toString(strings));

		
		// using lambda closure
		Arrays.sort(strings, (a, b) -> {
			int res = a.length() - b.length();
			return res == 0 ? a.compareTo(b) : res;
		});

		System.out.println(Arrays.toString(strings));
		
		
		
		// using lambda method reference
		Arrays.sort(strings, (a, b) -> compareLengthNatural(a,b));

		System.out.println(Arrays.toString(strings));

	}
	
	private static Integer compareLengthNatural(String s1, String s2) {
		int res = Integer.compare(s1.length(), s2.length());
		return res;
	}
	
	
	
	
	
}
