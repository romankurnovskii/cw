package leetcode;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

class Solution {
	public static String minWindow(String s, String t) {
		if (t == null || t.isEmpty() || s == null || s.isEmpty()) {
			return "";
		}
		if (t.length() == 1) {
			return s.contains(t) ? t : "";
		}
		Map<Character, Integer> foundChars = new LinkedHashMap<>();
		Set<Character> unmatchedSet = new HashSet<Character>();
		for (Character c : t.toCharArray()) {
			unmatchedSet.add(c);
		}
		int start = -1;
		int len = Integer.MAX_VALUE;
		char[] chars = s.toCharArray();
		String rel = "";
		for (int i = 0; i < chars.length; i++) {
			if (unmatchedSet.contains(chars[i])) {
				unmatchedSet.remove(chars[i]);

				if (start == -1) {
					start = i;
				}
				foundChars.put(chars[i], i);
				if (unmatchedSet.isEmpty()) {
					int tempLen = i - start + 1;
					if (len > tempLen) {
						len = tempLen;
						rel = s.substring(start, i + 1);
					}
					char firstChar = foundChars.keySet().iterator().next();
					foundChars.remove(firstChar);
					unmatchedSet.add(firstChar);
					
					
					if (foundChars.keySet().iterator().hasNext()) {
						start = foundChars.get(foundChars.keySet().iterator().next());
					}
					
				
					
				}
			}
			if (foundChars.containsKey(chars[i])) {
				foundChars.remove(chars[i]);
				foundChars.put(chars[i], i);
				if (chars[start] == chars[i]) {
					start = foundChars.get(foundChars.keySet().iterator().next());
				}
			}
		}

		return rel;
	}
}