package telran.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BinaryOperator;

public class Calculator {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Введите выражение");
		Double resDouble;
		resDouble = calculate(scanner.nextLine());
		System.out.println(resDouble);
	}

	private static final Map<String, BinaryOperator<Double>> map;

	static {
		map = new HashMap<>();

		map.put("/", (value, number) -> value / number);
		map.put("*", (value, number) -> value * number);
		map.put("+", Double::sum);
		map.put("-", (value, number) -> value - number);
	}

	static public double calculate(String expression) {
		if (!expression.matches(RegularExpressions.arithmeticExpression())) {
			return Double.NaN;
		}

		String[] numbers = expression.trim().split("[-+/* ]+");
		String[] operations = expression.trim().split("[\\d. ]+");

		double result = Double.parseDouble(numbers[0]);

		for (int i = 1; i < numbers.length; i++) {
			result = calculate(result, Double.parseDouble(numbers[i]), operations[i]);

			if (Double.isNaN(result) || result == Double.POSITIVE_INFINITY) {
				break;
			}
		}

		return result;
	}

	private static double calculate(double value, double number, String operation) {
		return map.getOrDefault(operation, (v, n) -> Double.NaN).apply(value, number);
	}

	public static boolean checkParenthesis(String expression) {
		// Подразумеваем, что в строке нет ничего, кроме скобок
		expression = expression.replaceAll("[^()]", "");
		if (expression.length() % 2 != 0) {
			return false;
		}

		int balance = 0;
		for (char value : expression.toCharArray()) {
			if (value == '(') {
				balance++;
			}
			if (value == ')' && --balance < 0) {
				break;
			}
		}

		return balance == 0;
	}
}
