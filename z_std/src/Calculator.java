

public class Calculator {

	private static final String PROIZVED = "* ";
	private static final String DELENIE = "/ ";
	private static final String SUM = "+ ";
	private static final String MINUS = "- ";

	public static Double evaluate(String expression) {
		while (true) {
			if (expression.contains(PROIZVED) && expression.contains(DELENIE))
				if (expression.indexOf(PROIZVED) < expression.indexOf(DELENIE))
					expression = operation(expression, PROIZVED);
				else
					expression = operation(expression, DELENIE);
			else if (expression.contains(PROIZVED))
				expression = operation(expression, PROIZVED);
			else if (expression.contains(DELENIE))
				expression = operation(expression, DELENIE);
			else
				break;
		}
		while (true) {
			if (expression.contains(SUM) && expression.contains(MINUS))
				if (expression.indexOf(SUM) < expression.indexOf(MINUS))
					expression = operation(expression, SUM);
				else
					expression = operation(expression, MINUS);
			else if (expression.contains(SUM))
				expression = operation(expression, SUM);
			else if (expression.contains(MINUS))
				expression = operation(expression, MINUS);
			else
				break;
		}
		return Double.parseDouble(expression);
	}

	private static String operation(String expression, String operator) {
		double[][] values = getOperandsAndIndexes(expression, operator);
		double resultNumb = 0;
		switch (operator) {
		case (PROIZVED):
			resultNumb = values[0][0] * values[0][1];
			break;
		case (DELENIE):
			resultNumb = values[0][0] / values[0][1];
			break;
		case (SUM):
			resultNumb = values[0][0] + values[0][1];
			break;
		case (MINUS):
			resultNumb = values[0][0] - values[0][1];
		}
		expression = expression.replace(
				expression.substring((int) values[1][0] + 1, ((int) values[1][1] + 1) + (int) values[1][2]),
				" ".concat(String.valueOf(resultNumb)).concat(" "));
		return expression;
	}

	private static double[][] getOperandsAndIndexes(String expression, String operator) {
		int currentOperIndex = expression.indexOf(operator);
		String leftPart = expression.substring(0, currentOperIndex);
		int prevOperIndex = findPrevOperIndex(leftPart);
		double leftNumber = Double.parseDouble(leftPart.substring(prevOperIndex + 1, currentOperIndex).trim());
		String rightPart = expression.substring(currentOperIndex + 1);
		int nextOperIndex = findNextOperIndex(rightPart);
		double rightNumber = Double.parseDouble(rightPart.substring(0, nextOperIndex).trim());
		double[][] values = { { leftNumber, rightNumber }, { prevOperIndex, currentOperIndex, nextOperIndex } };
		return values;
	}

	private static int findPrevOperIndex(String leftPart) {
		int prevOperIndex = -1;
		if (prevOperIndex < leftPart.lastIndexOf(PROIZVED))
			prevOperIndex = leftPart.lastIndexOf(PROIZVED);
		if (prevOperIndex < leftPart.lastIndexOf(DELENIE))
			prevOperIndex = leftPart.lastIndexOf(DELENIE);
		if (prevOperIndex < leftPart.lastIndexOf(SUM))
			prevOperIndex = leftPart.lastIndexOf(SUM);
		if (prevOperIndex < leftPart.lastIndexOf(MINUS))
			prevOperIndex = leftPart.lastIndexOf(MINUS);
		return prevOperIndex;
	}

	private static int findNextOperIndex(String rightPart) {
		int nextOperIndex = rightPart.length();
		if (nextOperIndex > rightPart.indexOf(PROIZVED) && rightPart.contains(PROIZVED))
			nextOperIndex = rightPart.indexOf(PROIZVED);
		if (nextOperIndex > rightPart.indexOf(DELENIE) && rightPart.contains(DELENIE))
			nextOperIndex = rightPart.indexOf(DELENIE);
		if (nextOperIndex > rightPart.indexOf(SUM) && rightPart.contains(SUM))
			nextOperIndex = rightPart.indexOf(SUM);
		if (nextOperIndex > rightPart.indexOf(MINUS) && rightPart.contains(MINUS))
			nextOperIndex = rightPart.indexOf(MINUS);
		return nextOperIndex;
	}

}



// BETTER ___ >>>>

/* 



public class Calculator {

public static Double evaluate(String expression) {
String[] parts = expression.split(" ");
Stack<Double> s = new Stack<>();

for (int i=0; i<parts.length; i+=2) {
  if (i == 0 || parts[i-1].equals("+")) {
    s.push(Double.valueOf(parts[i]));
  } else if (parts[i-1].equals("-")) {
    s.push(-Double.valueOf(parts[i]));
  } else if (parts[i-1].equals("*")) {
    s.push(s.pop()*Double.valueOf(parts[i]));
  } else if (parts[i-1].equals("/")) {
    s.push(s.pop()/Double.valueOf(parts[i]));
  }
}

double r = 0.0;
while(!s.isEmpty()) {
  r += s.pop();
}
// your code here
return r;
}
}


*/