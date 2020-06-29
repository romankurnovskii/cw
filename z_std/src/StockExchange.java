

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class StockExchange {
	private static class DailyQuote implements Comparable<DailyQuote> {
		int quote;
		int dayOfPurchase;
		int dayOfSale;

		public DailyQuote(int quote, int dayOfPurchase, int dayOfSale) {
			this.quote = quote;
			this.dayOfPurchase = dayOfPurchase;
			this.dayOfSale = dayOfSale;
		}

		public String toString() {
			return ("quote = " + quote + " " + dayOfPurchase + "_" + dayOfSale);
		}

		@Override
		public int compareTo(DailyQuote o) {
			if (this.quote == o.quote) {
				return Integer.compare(o.dayOfPurchase, this.dayOfPurchase);
			}
			return Integer.compare(o.quote, this.quote);
		}
	}

	private int[] dailyPrices;
	private final int MAX_OPERATIONS;
	private DailyQuote[] dailyQuoteArr;

	public StockExchange(int[] dailyPrices, int maxOperation) {
		this.dailyPrices = dailyPrices;
		this.MAX_OPERATIONS = maxOperation;
		preprocessing();
	}

	public void preprocessing() {
		extremum();
		createDailyQuoteArr();
	}

	private void createDailyQuoteArr() {
		LinkedList<DailyQuote> listOfDailyQuote = new LinkedList<>();
		fillListOfDailyQuote(listOfDailyQuote);
		listOfDailyQuote.sort(Comparator.naturalOrder());
		listOfDailyQuote = removeDuplicates(listOfDailyQuote);

		dailyQuoteArr = listOfDailyQuote.toArray(new DailyQuote[listOfDailyQuote.size()]);
	}

	public void extremum() {
		int prev = dailyPrices[1];
		boolean up = dailyPrices[0] <= dailyPrices[1];

		for (int i = 2; i < dailyPrices.length; i++) {
			if (up) {
				if (prev <= dailyPrices[i]) {
					dailyPrices[i - 1] = -1;
				} else {
					up = false;
				}

			} else {
				if (prev >= dailyPrices[i]) {
					dailyPrices[i - 1] = -1;
				} else {
					up = true;
				}

			}

			prev = dailyPrices[i];
		}
	}

	public int findBestDeal() {
		int res = 0;

		for (int i = 0; i < dailyQuoteArr.length; i++) {
			int max = dailyQuoteArr[i].quote;
			LinkedList<DailyQuote> listOfRange = new LinkedList<>();
			listOfRange.add(dailyQuoteArr[i]);

			max += comingDisjoint(listOfRange, i + 1);

			if (max > res) {
				res = max;
			}

			int length = MAX_OPERATIONS + i + 1;
			length = length < dailyQuoteArr.length ? length : dailyQuoteArr.length;
			max = 0;

			for (int j = i + 1; j < length; j++) {
				max += dailyQuoteArr[j].quote;
				if (max > res) {
					break;
				}
			}
			if (res > max) {
				break;
			}
		}

		return res;
	}

	private int comingDisjoint(LinkedList<DailyQuote> listOfRange, int startIndex) {
		int res = 0;
		int counter = 1;
		for (int j = startIndex; j < dailyQuoteArr.length; j++) {
			if (invalidRange(listOfRange, dailyQuoteArr[j])) {
				continue;
			}
			listOfRange.add(dailyQuoteArr[j]);
			res += dailyQuoteArr[j].quote;
			counter++;
			if (counter == MAX_OPERATIONS) {
				break;
			}
		}
		return res;
	}

	private boolean invalidRange(LinkedList<DailyQuote> listOfRange, DailyQuote dailyQuoteArr) {
		for (DailyQuote dQ : listOfRange) {
			if (dailyQuoteArr.dayOfPurchase < dQ.dayOfSale && dailyQuoteArr.dayOfSale > dQ.dayOfPurchase) {
				return true;
			}
		}
		return false;
	}

	private void fillListOfDailyQuote(LinkedList<DailyQuote> listOfDailyQuote) {
		for (int i = 0; i < dailyPrices.length - 1; i++) {
			if (dailyPrices[i] == -1) {
				continue;
			}
			for (int j = i + 1; j < dailyPrices.length; j++) {
				if (dailyPrices[j] == -1 || dailyPrices[i] >= dailyPrices[j]) {
					continue;
				}
				DailyQuote dQ = new DailyQuote(dailyPrices[j] - dailyPrices[i], i, j);
				listOfDailyQuote.add(dQ);
			}
		}
	}

	private LinkedList<DailyQuote> removeDuplicates(LinkedList<DailyQuote> listOfDailyQuote) {
		var res = new LinkedList<DailyQuote>();
		res.add(listOfDailyQuote.getFirst());
		Iterator<DailyQuote> iterator = listOfDailyQuote.iterator();
		iterator.next();

		while (iterator.hasNext()) {
			DailyQuote last = res.getLast();
			DailyQuote carrent = iterator.next();

			if (carrent.quote != last.quote) {
				res.add(carrent);
				continue;
			}

			if (carrent.dayOfPurchase <= last.dayOfPurchase && carrent.dayOfSale >= last.dayOfSale) {
				continue;
			}

			if (carrent.dayOfPurchase >= last.dayOfPurchase && carrent.dayOfSale <= last.dayOfSale) {
				last.dayOfPurchase = carrent.dayOfPurchase;
				last.dayOfSale = carrent.dayOfSale;
				continue;
			}

			res.add(carrent);

		}
		return res;
	}

}
