package z_std;

import java.util.HashMap;
import java.util.Iterator;

public class ShoopTicketMap {

	public static void main(String[] args) {

		// [["JPN", "PHL"], ["BRA", "UAE"], ["USA", "BRA"], ["UAE", "JPN"]]

		// result: "USA, BRA, UAE, JPN, PHL"

		String[][] ar = new String[][] { { "JPN", "PHL" }, { "BRA", "UAE" }, { "USA", "BRA" }, { "UAE", "JPN" } };

		Solution(ar);

	}

	private static void Solution(String[][] ar) {

		HashMap<String, String> countriesHashMap = new HashMap<>();

		for (int i = 0; i < ar.length; i++) {
			countriesHashMap.put(ar[i][0], ar[i][1]);
		}

		StringBuilder stringBuilder = new StringBuilder("");

		// find country from
		countriesHashMap.forEach((k, v) -> {
			if (!countriesHashMap.containsValue(k)) {
				stringBuilder.append(k);
			}
		});

		System.out.print(stringBuilder.toString() + ",");

		String from = countriesHashMap.get(stringBuilder.toString());
		String to = countriesHashMap.get(from);

		System.out.print(from);
		while (to != null) {
			System.out.print("," + to);
			from = to;
			to = countriesHashMap.get(to);
		}
	}

}
