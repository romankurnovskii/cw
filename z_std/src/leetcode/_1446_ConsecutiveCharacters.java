package leetcode;

import java.util.Arrays;


public class _1446_ConsecutiveCharacters {
	
	

	public static void main(String[] args) {
		

	maxPower("aa");

	}

	public static int maxPower(String s) {
		
		
	
		char[] res = s.toCharArray();

		
		System.out.println(Arrays.toString(res));
		
		int max=1;
		int count=1;
		for (int i = 0; i < res.length-1; i++) {
			if (res[i] != res[i+1]) {
				count = 1;
				
			} else {
				count++;
			}
			max = max > count ? max : count;
		}

	System.out.println(max);
		return max;

	}

}
