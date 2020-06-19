package leetcode;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

class Solution {

	public static int climbStairs(int n) {

		int a = 0;
		int tmp = 0;
		int sum = 1;
		for (int i = 0; i < n; i++) {
			tmp = a + sum;
			a = sum;
			sum = tmp;
		}
		System.out.println(sum);
		return sum;

	}

	public static void main(String[] args) {
		climbStairs(4);

	}

}



 