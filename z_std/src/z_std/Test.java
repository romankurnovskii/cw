package z_std;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Test {

	public static void main(String args[]) {

		Scanner scanner = new Scanner(System.in);
		
		test("abfbdkfsgkldfjglknsddsjfiejrjifudflab");

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