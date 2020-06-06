package codewars;



public class FirstNonConsecutive {
	
	
	public static void main(String[] args) {
		
		System.out.println(FirstNonConsecutive.find(new int[]{4, 5, 6, 7, 8, 9, 11}));

		
	}

	static Integer find(final int[] array) {
		int step = 1;

		for (int i = 0; i < array.length-1; i++) {
			
			
			System.out.print(" " + array[i]);
			if (array[i+1] - array[i] != step) {
				
				return array[i+1];
			} 
			
		}
		return null;
    }
	
	
}
