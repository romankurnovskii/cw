package codewars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.stream.IntStream;

public class LargestFiveDigitNumber {
	public static int solve(final String digits) {
		
		char[] ar = digits.toCharArray();
		

		int i = 99999;
		int index=0;
		do {
			index = digits.indexOf(Integer.toString(i));
			i--;
		}
		while(index == -1);
		
	
		String res="";
		for(i = 0; i<5; i++) {
			res += ar[index];
			index++;
		}
		
		
        return Integer.parseInt(res); 
    }
	
	
	
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		String aString ="654547763347457985203958238503849057388943275872395738";
		for(int i=0; i<100000;i++) {
									solve8(aString);
		}
				
	
		long end = System.currentTimeMillis();
		
		System.out.println(end - start);
	}
	
	

	
	
	
/*
 * Other Realizations
 */
	
	
			//tests per 100_000 starts
//196	
public static int solve1(final String digits) {
    return IntStream.range(0, digits.length() - 4)
            .mapToObj(i -> digits.substring(i, i + 5))
            .mapToInt(Integer::parseInt)
            .max()
            .orElse(0);
}


//150
public static int solve2(final String digits) {
    int largest = Integer.MIN_VALUE;
    for (int i = 0; i <= digits.length() - 5; i++) {
      int number = Integer.parseInt(digits.substring(i, i + 5));
      largest = Math.max(number, largest);
    }
    return largest;
}


//160
public static int solve3(final String digits) {
    int max = 0;
    for (int i = 0; i < digits.length() - 4; i++) {
      int sub = Integer.valueOf(digits.substring(i, i+5));
      if (sub > max)
        max = sub;
    }
    return max;
}


//219
public static int solve4(final String digits) {
    ArrayList<Integer> list = new ArrayList<Integer>();
    for (int i = 0; i < digits.length()-4 ; i++) {
        list.add(Integer.parseInt(digits.substring(i,i+5)));
    }
    return Collections.max(list);
}



//919
public static int solve5(final String digits) {
    String val = "";
     int temp = 5;
     int max = 10000;
     for(int i=0; i<digits.length()-4; i++) {
         for(int j=i; j<temp; j++) {
             val += digits.charAt(j);
         }     
         temp++;
         if(Integer.parseInt(val) > max)
                     max = Integer.parseInt(val);
         val = "";
         
     }
     
     
     return max;
 }


//162
public static int solve6(final String digits) {
    int digit = 0;
    for (int i = 0; i + 5 <= digits.length(); i++) {
        if (Integer.valueOf(digits.substring(i, i + 5)) > digit) {
            digit = Integer.valueOf(digits.substring(i, i + 5));
        }
    }
    return digit;
}


//190
static int solve7(String digits) {
    return IntStream.range(0, digits.length() - 4).map(i -> Integer.parseInt(digits.substring(i, i + 5))).max().orElse(0);
  }


//145
public static int solve8(final String digits) {
    int max = 0;
    for (int i = 0; i < digits.length()-4; i++)
        max = Math.max(Integer.valueOf(digits.substring(i, i+5)), max);
    return max;
}








}
