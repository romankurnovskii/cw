package telran.util;

public class Calculator {
static public double calculate(String expr) {
	String[] operands = getOperands(expr);
	String[] operations =getOperations(expr);
	if (operands.length == 0 || operands.length != operations.length)
		return Double.NaN;
	double res = Double.parseDouble(operands[0]);
	for (int i = 1; i < operands.length; i++) {
		res = calculateOne(res, operands[i], operations[i]);
	}
	return res;
}

public static String[] getOperations(String expr) {
	
	return expr.split("[\\d ]+");
}

public  static String[] getOperands(String expr) {
	
	return expr.trim().split("\\D+");
}
public static double calculateOne(double op1, String op2,
		String operation) {
	double op2Number = Double.parseDouble(op2);
	switch(operation) {
	case "+": return op1 + op2Number;
	case "*": return op1 * op2Number;
	case "-": return op1 - op2Number;
	case "/": return op2Number == 0 ? Double.POSITIVE_INFINITY :
		op1 / op2Number;
	}
	return Double.NaN;
}
}
