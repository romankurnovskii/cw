


import static org.junit.Assert.assertEquals;
import java.util.Random;
import org.junit.jupiter.api.Test;

class StockExchangeTests {

	@Test	
	void testStock() {
		int[] dailyPrices1 = { 10, 22, 5, 75, 65, 80 };
		Stock sE = new Stock();
		assertEquals(87, sE.ProfitWithNumberOfOperations(dailyPrices1, 2));

		int[] dailyPrices2 = { 2, 10, 8, 1, 68, 5, 81, 9, 20, 50, 70 };
		 sE = new Stock();
		assertEquals(143, sE.ProfitWithNumberOfOperations(dailyPrices2, 2));

		int[] dailyPrices3 = { 2, 10, 8, 1, 68, 5, 81, 9, 20, 50, 70 };
		 sE = new Stock();
		assertEquals(204, sE.ProfitWithNumberOfOperations(dailyPrices3, 3));


		int[] dailyPrices4 = { 2, 10, 8, 3, 67, 68, 5, 81, 9, 20, 50, 70 };
		 sE = new Stock();
		assertEquals(203, sE.ProfitWithNumberOfOperations(dailyPrices4, 3));

		int[] dailyPrices5 = { 2, 10, 8, 3, 71, 1, 5, 71, 81, 9, 20, 50, 70 };
		 sE = new Stock();
		assertEquals(210, sE.ProfitWithNumberOfOperations(dailyPrices5, 3));

		int[] dailyPrices6 = { 5, 1, 10, 8, 3, 71, 1, 5, 71, 81, 9, 20, 50, 300, 2 };
		 sE = new Stock();
		assertEquals(441, sE.ProfitWithNumberOfOperations(dailyPrices6, 3));

		int[] dailyPrices7 = { 9, 0, 6, 6, 9, 1, 6, 4, 8, 5, 6, 1, 6, 0, 5, 3, 9, 4, 8, 1 };
		 sE = new Stock();
		assertEquals(30, sE.ProfitWithNumberOfOperations(dailyPrices7, 4));


		int[] dailyPrices8 = new int[730];
		Random rendom = new Random();
		for (int i = 0; i < dailyPrices8.length; i++) {
			dailyPrices8[i] = rendom.nextInt(100);
		}

		// int[] dailyPrices8 = Arrays.copyOf(dailyPrices7, dailyPrices7.length);


	}

}
