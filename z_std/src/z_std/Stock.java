package z_std;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public class Stock {
	public static int[] prices;

	public static int operations;

	public static void main(String[] args) {
		prices = new Random().ints(8500, 1, 1_000_000).toArray();

		operations = 3000;

		System.out.println(Arrays.toString(prices));

		Thread thread1 = new Thread();
		Thread thread2 = new Thread();
		Thread thread3 = new Thread();

		System.out.println("\n1 максимальная \n");
		// better realization
		System.out.print("\nTotal profit earned is " + maxProfit(prices));

		System.out.println("\nс ограничением операций \n");
		ProfitWithNumberOfOperations(prices, operations);

		System.out.println("\nРекурсия \n");
//		System.out.println(calculateProfit(prices, operations));

		System.out.println("\nВлад\n");
		StockExchange stockExchange = new StockExchange(prices, operations);
		System.out.println(stockExchange.findBestDeal());

		System.out.println("\n" + Arrays.toString(prices));

	}

	/* 
	 * 
	 * 	
	 */

	private static int calculateProfit(int[] prices, int operations) {
		if (prices.length < 2) {
			return 0;
		}

		return calculateProfit(prices, true, 0, operations * 2);
	}

	private static int calculateProfit(int[] prices, boolean buy, int index, int operations) {
		if (operations < 1 || index >= prices.length) {
			return 0;
		}

		int profit = (buy ? -1 : 1) * prices[index];

		int operationDone = profit + calculateProfit(prices, !buy, index + 1, operations - 1);
		int operationSkipped = calculateProfit(prices, buy, index + 1, operations);

		return Math.max(operationDone, operationSkipped);
	}

	/* 
	 * 
	 * 	
	 */

	// Function to find maximum profit that can be earned by buying and
	// selling shares any number of times
	public static int maxProfit(int[] price) {
		// store maximum profit gained
		int profit = 0;

		// initialize local minimum to first element's index
		int j = 0;

		// start from second element
		for (int i = 1; i < price.length; i++) {
			// update local minimum if decreasing sequence is found
			if (price[i - 1] > price[i]) {
				j = i;
			}

			// sell shares if current element is peak
			// i.e. (previous <= current > next)
			if (price[i - 1] <= price[i] && (i + 1 == price.length || price[i] > price[i + 1])) {
				profit += (price[i] - price[j]);
				System.out.printf("Buy on day %d and sell on day %d\n", j + 1, i + 1);
			}
		}

		return profit;
	}

	/*
	 * 
	 * 
	 */
	static int ProfitWithNumberOfOperations(int[] price, int operations) {
		int n = price.length; // кол-во дней всего

		int[][] res = new int[operations + 1][n + 1];

		int[] operationsAr = new int[operations + 1];
		int[] daysAr = new int[n + 1];

		for (int i = 1; i <= operations; i++) {

			for (int j = 1; j < n; j++) {
				int max = 0;

				// смотрю дипазон слева
				for (int j1 = 0; j1 < j; j1++) {
					int tmpPrice = price[j] - price[j1] + res[i - 1][j1];

					max = Math.max(max, tmpPrice);

					res[i][j] = Math.max(res[i][j - 1], max);
				}
			}
		}

//		for (int i = 0; i < res.length; i++) {
//			System.out.println(Arrays.toString(res[i]));
//		}

		System.out.println(res[operations][n - 1]);
		return res[operations][n - 1];
	}

}

/**
 * На фондовой бирже, человек покупает акцию для продажи в будущем. Приведены
 * цены на акции за N дней в виде массива A[N] и число T, представляющее
 * максимальное количество операций. Необходимо рассчитать максимальную прибыль,
 * которую человек может получить, выполнив максимум T операций.
 *
 * Действием считается операция покупки + продажи. Невозможно выполнять
 * перекрывающие операции. Новое действие можно совершать только после того, как
 * было завершено предыдущее.
 *
 * Данные: Первая строка: максимальное количество действий T Вторая строка:
 * массив чисел длиной N, представляющий диапазон цен акций.
 *
 * Пример: 2 {10, 22, 5, 75, 65, 80}
 *
 * Ответ: 87
 */