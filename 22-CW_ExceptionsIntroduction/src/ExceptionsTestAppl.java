
public class ExceptionsTestAppl {

	public static void main(String[] args) {
		String strNumber = "12.78";

		System.out.printf("strNumber: %s is number, it is %s\n", strNumber, isNumber(strNumber));
	}

	private static boolean isNumber(String strNumber) {

		boolean res = false;

		try {
			Double.parseDouble(strNumber);
			res = true;
			return true;
		} catch (NumberFormatException e) {
			res = false;
			return false;
		} finally {
			System.out.printf("returned %s ", res);
		}

	}

}
