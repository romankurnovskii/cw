package hackerrank;

public class MyRegex {

	 static String numberLess256 = "[01]\\d{2}|\\d{2}|\\d|2[0-4]\\d|25[0-5]";
	 static String pattern = String.format("((%s)\\.){3}(%s)", numberLess256, numberLess256);
	    
}
