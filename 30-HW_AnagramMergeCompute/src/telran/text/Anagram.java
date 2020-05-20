package telran.text;
import java.util.*;
public class Anagram {
public static boolean isAnagram(String word, String anagram) {
	if(word.length() != anagram.length()) {
		return false;
	}
	HashMap<Character, Integer> charCountsMap = getCharCounts(word);
	for(char c: anagram.toCharArray()) {

		if (charCountsMap.compute(c, (k, v) -> v == null ? -1 : v - 1 ) < 0)
			return false;
	}
	return true;
}

private static HashMap<Character, Integer> getCharCounts(String word) {
	HashMap<Character, Integer> res = new HashMap<>();
	for(char c: word.toCharArray()) {
		res.merge(c, 1, (a, b) -> a + b);
	}
	return res;
}
}
