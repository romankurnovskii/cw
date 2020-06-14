package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class _368_Largest_Divisible_Subset {

	public static void main(String[] args) {

		largestDivisibleSubset(new int[] { 1, 2, 4, 8 });

	}

	static public List<Integer> largestDivisibleSubset(int[] nums) {

		int n = nums.length;
		int max = 0;
		int index = -1;
		int[] count = new int[n];
		int[] pre = new int[n];
		Arrays.sort(nums);
		for (int i = 0; i < n; i++) {
			count[i] = 1;
			pre[i] = -1;
			for (int j = i - 1; j >= 0; j--) {
				if (nums[i] % nums[j] == 0) {
					if (1 + count[j] > count[i]) {
						count[i] = 1 + count[j];
						pre[i] = j;
					}
				}
			}
			if (count[i] > max) {
				max = count[i];
				index = i;
			}
		}
		List<Integer> res = new ArrayList<>();
		while (index != -1) {
			res.add(nums[index]);
			index = pre[index];
		}
		return res;
	}

}

/*
 * Given a set of distinct positive integers, find the largest subset such that
 * every pair (Si, Sj) of elements in this subset satisfies:
 * 
 * Si % Sj = 0 or Sj % Si = 0.
 * 
 */
