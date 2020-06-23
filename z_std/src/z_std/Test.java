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

	private static String[] order = {"0", "1", "2", "3"};
	
	public static void main(String args[]) {

		
		int index=0;
		
		System.out.println(order[index++]);
		
//		5 3 50 100 80 120 80
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


