package telran.util.hw;

import telran.util.HashSet;
import telran.util.Set;

public class ArrayInt {

	/**
	 * searches for index of a given number in a given array
	 * 
	 * @param ar     - array of numbers
	 * @param number - number for search
	 * @return index value or -1
	 */
	public static int search(int ar[], int number) {
		for (int i = 0; i < ar.length; i++) {
			if (ar[i] == number) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * sorting of numbers
	 * 
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
		} while (!flSort);
	}

	/**
	 * searches for index of a given number in a given sorted array
	 * 
	 * @param ar     - array of numbers
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
		int res = left > right ? -(left + 1) : middle;
		if (res >= 0) {
			// searching for first index of the equaled values
			while (res >= 0 && ar[res] == number) {
				res--;
			}
			res++;
		}
		return res;
	}

	/**
	 * 
	 * @param number
	 * @return amount of elements equaled to number
	 */
	public static int countSorted(int ar[], int number) {
		int ind = binarySearch(ar, number);
		int count = 0;
		if (ind >= 0) {
			while (ind < ar.length && ar[ind] == number) {
				count++;
				ind++;
			}
		}
		return count;
	}

	public static int[] insert(int ar[], int index, int number) {
		int res[] = ar;
		if (index >= 0 && index <= ar.length) {
			res = new int[ar.length + 1];
			System.arraycopy(ar, 0, res, 0, index);
			System.arraycopy(ar, index, res, index + 1, ar.length - index);
			res[index] = number;
		}
		return res;
	}

	public static int[] remove(int[] ar, int index) {
		int res[] = ar;
		if (index >= 0 && index < ar.length) {
			res = new int[ar.length - 1];
			System.arraycopy(ar, 0, res, 0, index);
			System.arraycopy(ar, index + 1, res, index, res.length - index);
		}
		return res;
	}

	public static int[] insertSorted(int[] ar, int number) {
		int index = binarySearch(ar, number);
		if (index < 0) {
			index = -index - 1;
		}
		int res[] = insert(ar, index, number);
		return res;
	}






	/**
	 * Assumption: no repeated numbers in each array, but numbers in first array may
	 * be repeated in the second
	 * 
	 * @param ar1 - first array
	 * @param ar2 - second array
	 * @return array containing numbers of first and second arrays with no
	 *         repetitions
	 */
	public static int[] union (int ar1[], int ar2[]) {
		Set<Integer> resTmp = new HashSet<>();
		
		for (int i = 0; i < ar1.length; i++) {				// 1st ad elements from 1st array, than from 2nd
			resTmp.add(ar1[i]);
		}
		
		for (int i = 0; i < ar2.length; i++) {
			resTmp.add(ar2[i]);
		}
		
		int i = 0;						// add each element from set to new array
		int[] res = new int[resTmp.size()];		// set size of new result array
		for(int el : resTmp) {
			res[i] = el;
			i++;
		}

		return res;
	}

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
	



