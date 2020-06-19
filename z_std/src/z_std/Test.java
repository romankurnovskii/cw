package z_std;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Test {

	public static void main(String args[]) {

		Scanner scanner = new Scanner(System.in);
		System.out.println(0.1 + 0.2);
		
		test("abfbdkfsgkldfjglknsddsjfiejrjifudflab");
		
		BigDecimal aBigDecimal = new BigDecimal(5);
		
		aBigDecimal.setScale(6);
		System.out.println(aBigDecimal.setScale(25));
		
		Date aDate = new Date();
		LocalDate localDate;

		String aString = "dsfsF";
		aString.toLowerCase();
		
		
	}
	
	
	
	
	public static void test(String string) {
		
		int count = 0;
		int i = 0;
		
		String subString = "ab";
		
		while(string.indexOf(subString, i) != -1) {
			count++;
			i =  i + subString.length() + string.indexOf(subString, i);	
		}
		
		System.out.println(count);
		
		
		
	}



}