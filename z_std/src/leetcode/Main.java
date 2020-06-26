package leetcode;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {

		int[][] costs = { { 259, 770 }, { 448, 54 }, { 926, 667 }, { 184, 139 }, { 840, 118 }, { 577, 469 } };

	}

//	1 184 259 667
//	2 54 118 469
//	
//	184+ 259 448+   577+
//	54+  118 139+    469+
//	
//54
//184
	
	
	
	static class Solution {
		static public int twoCitySchedCost(int[][] costs) {

			 if(costs == null || costs.length == 0) {
		            return 0;
		        }
		        Arrays.sort(costs, (a, b) -> {
		            return a[0] - a[1] - b[0] + b[1];
		        });
		        int n = costs.length / 2, result = 0;
		        for(int i = 0; i < n; i++) {
		            result += costs[i][0] + costs[2 * n - 1 - i][1];
		        }
		        return result;
		}
	}

}