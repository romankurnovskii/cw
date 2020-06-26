package leetcode;

import java.util.Set;
import java.util.TreeSet;

import javax.swing.tree.TreeNode;
import org.dom4j.Node;
import org.dom4j.swing.BranchTreeNode;
import org.dom4j.swing.LeafTreeNode;

public class _0665_NonDecreasingArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] nums = {4,2,3};
		
		Solution.checkPossibility(nums);
	}

	
	private static void getNumberOfNodes(Set set) {
	    int res;  
		if (set == null) {
	            res = 0;
	       }
		

	}
	
	
	static class Solution {
		// . 10 4 9 4 2 1
		static public boolean checkPossibility(int[] nums) {
			
			
			TreeSet<Integer> set = new TreeSet<>();
			
			for (int i = 0; i < nums.length; i++) {
				set.add(nums[i]);
			}


			if (nums.length <= 1) {
				return true;
			}

			int count = 0;

			int[] indexes = new int[2];
			for (int i = 0; i < nums.length - 1; i++) {
				if (nums[i + 1] < nums[i]) {
					count++;

					if (nums[i] > nums[i + 2]) {
						count++;
					}

				}
				if (count > 1) {
					return false;
				}

			}

			if (count <= 1) {
				return true;
			}
			return false;
		}
	}

}
