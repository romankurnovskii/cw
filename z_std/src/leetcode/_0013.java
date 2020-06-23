package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class _0013 {

	static class Solution {
		static public int romanToInt(String s) {

			Map<Character, Integer> rome = new HashMap();

			rome.put('I', 1);
			rome.put('V', 5);
			rome.put('X', 10);
			rome.put('L', 50);
			rome.put('C', 100);
			rome.put('D', 500);
			rome.put('M', 1000);

			int sum = 0;
			int length = s.length();
			int i = 0;
			for (i = 0; i < length - 1; ++i) {
				switch (s.charAt(i)) {
				case 'I':
					if (s.charAt(i + 1) == 'V') {
						sum += rome.get('V') - rome.get('I');
						i++;
						break;
					} else if (s.charAt(i + 1) == 'X') {
						sum += rome.get('X') - rome.get('I');
						i++;
						break;
					} else {
						sum += rome.get(s.charAt(i));
						break;
					}
				case 'X':
					if (s.charAt(i + 1) == 'L') {
						sum += rome.get('L') - rome.get('X');
						i++;
						break;
					} else if (s.charAt(i + 1) == 'C') {
						sum += rome.get('C') - rome.get('X');
						i++;
						break;
					} else {
						sum += rome.get(s.charAt(i));
						break;
					}
				case 'C':
					if (s.charAt(i + 1) == 'D') {
						sum += rome.get('D') - rome.get('C');
						i++;
						break;
					} else if (s.charAt(i + 1) == 'M') {
						sum += rome.get('M') - rome.get('C');
						i++;
						break;
					} else {
						sum += rome.get(s.charAt(i));
						break;
					}
				default:
					sum += rome.get(s.charAt(i));
				}
			}

			if (i == length - 1) {
				sum += rome.get(s.charAt(i));
			}

			return sum;
		}
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println(Solution.romanToInt(scanner.next()));

	}
	
	
	
	
	
	
	

	

}
