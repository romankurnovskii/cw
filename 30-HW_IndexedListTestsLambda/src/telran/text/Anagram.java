package telran.text;

import java.util.HashMap;

public class Anagram {

  public static boolean isAnagram(String word, String anagram) {
    if (word.length() != anagram.length()) {
      return false;
    }
    HashMap<Character, Integer> charCountsMap = getCharCounts(word);
    for (char c : anagram.toCharArray()) {
      // int count = charCountsMap.compute(c, (k, v) -> (v == null) ? -1 : v - 1);
      int count = charCountsMap.merge(c, -1, (o, n) -> o - 1);
      if (count < 0) {
        return false;
      }
    }
    return true;
  }

  private static HashMap<Character, Integer> getCharCounts(String word) {
    HashMap<Character, Integer> res = new HashMap<>();
    for (char c : word.toCharArray()) {
      // res.compute(c, (k, v) -> (v == null) ? 1 : v + 1);
      res.merge(c, 1, (o, n) -> o + 1);
    }
    return res;
  }
}
