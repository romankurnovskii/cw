package z_std;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Stock {


	
	
	public static void main(String[] args)
	{
		int[] prices = new Random().ints(40, 1, 23).toArray();
		
		//int[] prices = {1, 4, 4, 6, 4, 10};
		int operations = 10;

		System.out.println(calculateProfit(prices, operations));
		
		
		// better realization
		System.out.print("\nTotal profit earned is " + maxProfit(prices));
		
		
	}

	private static int calculateProfit(int[] prices, int operations)
	{
		if (prices.length < 2) {
			return 0;
		}

		return calculateProfit(prices, true, 0, operations * 2);
	}

	private static int calculateProfit(int[] prices, boolean buy, int index, int operations)
	{
		if (operations < 1 || index >= prices.length) {
			return 0;
		}

		int profit = (buy ? -1 : 1) * prices[index];

		int operationDone = profit + calculateProfit(prices, !buy, index + 1, operations - 1);
		int operationSkipped = calculateProfit(prices, buy, index + 1, operations);

		return Math.max(operationDone, operationSkipped);
	}
	
	
	
	
	
		// Function to find maximum profit that can be earned by buying and
		// selling shares any number of times
		public static int maxProfit(int[] price)
		{
			// store maximum profit gained
			int profit = 0;

			// initialize local minimum to first element's index
			int j = 0;

			// start from second element
			for (int i = 1; i < price.length; i++)
			{
				// update local minimum if decreasing sequence is found
				if (price[i - 1] > price[i]) {
					j = i;
				}

				// sell shares if current element is peak
				// i.e. (previous <= current > next)
				if (price[i - 1] <= price[i] &&
					(i + 1 == price.length || price[i] > price[i + 1]))
				{
					profit += (price[i] - price[j]);
					System.out.printf("Buy on day %d and sell on day %d\n", j + 1, i + 1);
				}
			}

			return profit;
		}

	
	

}



/**
 * На фондовой бирже, человек покупает акцию для продажи в будущем.
 * Приведены цены на акции за N дней в виде массива A[N] и число T, представляющее максимальное количество операций.
 * Необходимо рассчитать максимальную прибыль, которую человек может получить, выполнив максимум T операций.
 *
 * Действием считается операция покупки + продажи. Невозможно выполнять перекрывающие операции.
 * Новое действие можно совершать только после того, как было завершено предыдущее.
 *
 * Данные:
 * Первая строка: максимальное количество действий T
 * Вторая строка: массив чисел длиной N, представляющий диапазон цен акций.
 *
 * Пример:
 * 2
 * {10, 22, 5, 75, 65, 80}
 *
 * Ответ: 87
 */