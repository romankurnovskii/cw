package telran.util;

public class ArrayInt {
/**
 * searches for index of a given number in a given array
 * @param ar - array of numbers
 * @param number - number for search
 * @return index value or -1
 */
	public static int search(int ar[], int number) {
	for(int i = 0; i < ar.length; i++) {
		if (ar[i] == number) {
			return i;
		}
	}
	return -1;
}
	/**
	 * sorting of numbers
	 * @param ar
	 */
	public static void sort(int[] ar) {
		boolean flSort = false;
		int length = ar.length;
		do {
			flSort = true;
			length--;
			for (int i = 0; i < length; i++) {
				if (ar[i] > ar[i + 1]) {
					int tmp = ar[i];
					ar[i] = ar[i + 1];
					ar[i + 1] = tmp;
					flSort = false;
				}
			}
			
			
		}while (!flSort);
	}
	/**
	 * searches for index of a given number in a given sorted array
	 * @param ar - array of numbers
	 * @param number - number for search
	 * @return index value or -1
	 */
	public static int binarySearch(int[] ar, int number) {
		int left = 0;
		int right = ar.length - 1;
		int middle = (left + right) / 2;
		while (left <= right && ar[middle] != number) {
			if (number < ar[middle]) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
			middle = (left + right) / 2;
		}
		int res = -1;
		if (ar[middle] == number) {
			res = middle;
		}
		return res;
	}
	
	
	
	
	
	
	
	
}
