package AtCoder;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;
import java.util.Scanner;

public class bg172 {

	public static void main(String[] args) {

		new d().main(null);

//		Scanner scanner = new Scanner(System.in);
//
//		String[] s = scanner.nextLine().split(" ");
//		int n = Integer.parseInt(s[0]);
//		int m = Integer.parseInt(s[1]);
//		int k = Integer.parseInt(s[2]);
//
//		String[] aStrings = scanner.nextLine().split(" ");
//		String[] bStrings = scanner.nextLine().split(" ");
//
//		int maxBooks = Math.max(aStrings.length, bStrings.length);
//		int minBooks = Math.min(aStrings.length, bStrings.length);
//
//		int count = 0;
//		int sum = 0;
//		int i = 0;
//		int j = 0;
//		int n1 = 0;
//		for (int nn = 0; nn < minBooks; nn++) {
//
//			if (Integer.parseInt(aStrings[i]) <= Integer.parseInt(bStrings[j])) {
//				sum += Integer.parseInt(aStrings[i]);
//				i++;
//			} else {
//				sum += Integer.parseInt(bStrings[j]);
//				j++;
//			}
//
//			if (sum >= k) {
//				System.out.println(count);
//				return;
//			}
//			count++;
//
//			n1 = nn;
//		}
//
//		String[] ar;
//		int ii;
//		if (aStrings.length > bStrings.length) {
//			ar = aStrings;
//			ii = i;
//		} else {
//			ar = bStrings;
//			ii = j;
//		}
//
//		for (int n2 = n1; n2 < maxBooks; n2++) {
//			sum += Integer.parseInt(ar[ii]);
//			ii++;
//
//			System.out.print(sum + " " + ii + " " + n2);
//
//			if (sum >= k) {
//				System.out.println(count);
//				return;
//			}
//			count++;
//
//		}
//
//		Deque<Integer> queueA = new ArrayDeque();
//		Deque<Integer> queueB = new ArrayDeque();
//
//		for (i = 0; i < aStrings.length; i++) {
//			queueA.add(Integer.parseInt(aStrings[i]));
//		}
//		for (i = 0; i < bStrings.length; i++) {
//			queueB.add(Integer.parseInt(bStrings[i]));
//		}
//
////		while (sum <=k) {
////			queueA.element()
////		}
//
//		System.out.println(count);

	}

}

class d {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();

		int count = 1;
		int sum = 1;

		int nn = 1;

		while (nn <= n) {

			count = 1;
			int d = 1;
			while (d <= nn) {
				          System.out.println (" d " +d + " count" + count + " nn " + nn + " n " + n);
				if ((nn % d) == 0) {
					count++;
				}
				d++;
			}

			System.out.println("stopla" + d + " "+count +" "+ sum + " " +nn);
			sum = sum + count * d-1;
			
			
			System.out.println(sum);
			nn++;
			
		}
		
		

//		System.out.println(count);
		System.out.println(sum);

	}

}
