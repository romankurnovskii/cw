import java.util.*;
import java.util.Map.*;

public class WordCountsTestAppl {

	public static void main(String[] args) {
		String text = "lmn b abc lmn, lmn:abc - a ab ab";
		displayWordCounts(text);
		//Output format
		/***********************
		 * lmn -> 3
		 * ab -> 2
		 * abc -> 2
		 * a -> 1
		 * b -> 1
		 */
		

	}

	private static void displayWordCounts(String text) {
		String words[] = getWords(text); //word - may contain only,
		//letters, digits, underscores
		HashMap<String, Integer> mapCounts = getMapCounts(words);
		List<Entry<String, Integer>> listEntries =
				getListEntries(mapCounts);
		listEntries.sort(new StringCountsComparator());
		displayListEntries(listEntries);
		
		
		
	}

	private static void displayListEntries
	(List<Entry<String, Integer>> listEntries) {
		for(Entry<String, Integer> entry: listEntries) {
			System.out.printf("%s -> %d\n",
					entry.getKey(), entry.getValue());
		}
		
	}

	private static List<Entry<String, Integer>> getListEntries
	(HashMap<String, Integer> mapCounts) {
	
		return new ArrayList<>(mapCounts.entrySet());
	}

	private static HashMap<String, Integer> getMapCounts(String[] words) {
		HashMap<String, Integer> res = new HashMap<>();
		for(String word: words) {
			Integer count = res.getOrDefault(word, 0);
			res.put(word, count + 1);
		}
		return res;
	}

	private static String[] getWords(String text) {
		
		return text.split("\\W+");
	}

}
