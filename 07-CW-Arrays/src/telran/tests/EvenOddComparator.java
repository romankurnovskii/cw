package telran.tests;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Object> {

	@Override
	public int compare(Object o1, Object o2) {
		int n1 = (int)o1;
		int n2 = (int)o2;
		if (n1 % 2 == 1 && n2 % 2 == 0) {
			return -1;
		}
		if (n1 % 2 == 0 && n2 % 2 == 1) {
			return 1;
		}
		if (n1 % 2 == 1 && n2 % 2 == 1) {
			return n1 - n2;
		}
		return n2 - n1;
	}

}
