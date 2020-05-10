package z_std;


import java.util.Scanner;

public class App {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		
	
		        
		int[] a = {9, 8, 3, 1, 5, 4};
		int[] b = {6, 3, 2, 9, 0};
		int i; 
		
		
		 for (i = 0; i < a.length; i++) {
			 

			 if (a[i] % 2 ==0) {
				 a[i] += 1;
			 } else if (a[i] < a.length) {
				a[i] += a[a[i]];
			} 
		
		 
		 } 
	

		 for (i = 0; i < a.length; i++) {
			 System.out.print(a[i] + " ");
		 }
		 
		
	
	

		
		
		
		
		
		
		
	}
	
	
	
public static int callBinarySearch(int[] nums, int key) {
	
	return java.util.Arrays.binarySearch(nums, key);
        
    }



}
