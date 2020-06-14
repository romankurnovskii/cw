import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		
		
		

		Scanner scanner = new Scanner(System.in);
		int x = scanner.nextInt();
		int y = scanner.nextInt();  //ноги
		
		String reString = "Yes";
		
		if (y % 2 != 0) {
			reString = "No";
		}
		
		if(x * 4 < y) {
			reString = "No";
		}
		if(x * 2 > y) {
			reString = "No";
		}
		
		
		System.out.println(reString);

	}
	
	
	
	static  int isSelfReferential(int[ ] a) {
		
		
		int[] tmp = new int[a.length];
		
		for (int i = 0; i < a.length; i++) {
			
			if (a[i] > a.length) {
				return 0;
			}
			
			tmp[a[i]]++;
			
		}
		
		for(int i=0; i<a.length;i++) {
			if (tmp[i] != i) {
				return 0;
			}
		}
		
		return 1;
	}

	
	
	static int isMercurial(int[ ] a) {
		
		int index3 = -1;
		int indexLeft1 = -1;
		int indexRight1 = -1;
		
		
		for (int i = 0, j=a.length-1; i < a.length/2; i++, j--) {
			if (a[i] == 1) {
				indexLeft1 = i;
			}
			if (a[j] == 1) {
				indexRight1 = j;
			}
			if (a[i] == 3) {
				index3 = i;
			}
			if (a[j] == 3) {
				index3 = j;
			}
			
			if (indexLeft1 < index3 && indexRight1 > index3 && index3 !=-1) {	System.out.println(0);
				return 0;
			}
		}
		
		System.out.println(1);
		
		return 1;
	}
	
	
	
	static int is123Array(int a[], int len) {
		
		if (len < 3) {
			return 0;
		}
		
		int res = 1;
		for(int i=0; i<len; i+=3) {
			if (a[i] == 1 && a[i+1] == 2 && a[i+2] == 3) {
			} else {
				System.out.println(0);
				return 0;
			}
		}
		System.out.println(1);
		return res;

	}

}