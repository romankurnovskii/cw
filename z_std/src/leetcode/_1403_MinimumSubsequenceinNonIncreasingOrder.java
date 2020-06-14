package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

import hackerrank.MyRegex;

public class _1403_MinimumSubsequenceinNonIncreasingOrder {

	public static void main(String[] args) {
		
		minSubsequence(new int[] {6,4,5});

	}

	public static List<Integer> minSubsequence(int[] nums) {
		
		List<Integer> res = new ArrayList<>();
		
		
		if (nums.length == 1) {
			res.add(nums[0]);
									System.out.println(res);
			return res;
		}
		
		
		IntSummaryStatistics intSummaryStatistics = 
				Arrays.stream(nums).summaryStatistics();
		
		
		if (nums.length == 2) {
			res.add(intSummaryStatistics.getMax());
			
			if(nums[0] == nums[1]) {
				res.add(intSummaryStatistics.getMax());
			}
				
//													System.out.println(res);
			return res;
		}
		

		
		int sum = (int) intSummaryStatistics.getSum();
		
		Arrays.sort(nums);
		
		int tempSum = 0;
		for (int i = nums.length - 1; i >= 0; i--) {
			tempSum+=nums[i];
			res.add(nums[i]);

			if (tempSum > sum - tempSum) {
				res.sort((i1,i2) -> i2.compareTo(i1));
							return res;
//				System.out.println(res + "stop");
			}
			
		}
		
//		System.out.println(Arrays.toString(nums));
//		System.out.println(res);
		return null;

	}

}







/*
 * other
 * class Solution {
    public List<Integer> minSubsequence(int[] nums) {
        ArrayList<Integer> subs = new ArrayList<Integer>();
        int nLength = nums.length;
        
        if(nLength == 1){
            subs.add(nums[0]);
            return subs;
        }
        
        
        int sum = 0;
        for(int i:nums){
            sum += i;
        }
        
        int secondSum = 0;
        Arrays.sort(nums);        
        
        for(int i=nLength-1; i>=0; i--){
            sum -= nums[i];
            secondSum += nums[i];
            subs.add(nums[i]);
            
            if(secondSum > sum){                
                break;
            }
        }        
        
        return subs;
    }
}

*/



