package telran.util;

public class RegularExpression {
	/**
	 * First symbol must be any ASCII letter or $ or _ if first symbol _ others
	 * symbols must be (one _ is considered as false) Other symbols may be only
	 * either ASCII letter or underscore or digit or $
	 * 
	 * @return regular expression string
	 */
	static public String variableName() {

		return "[\\p{Alpha}$][\\w$]*|_[\\w$]+";
	}

	/**
	 * string containing number less than 256
	 * 
	 * @return regular expression string
	 */
	static public String numberLess256() {
		return "[01]\\d{2}|\\d{2}|\\d|2[0-4]\\d|25[0-5]";
	}

	static public String ipV4() {
		return String.format("((%s)\\.){3}(%s)", numberLess256(), numberLess256());
	}

	static public String NumberPhoneIsr() {
		return "(\\+972-?|0)5(0|[2-8])(-?\\d){7}";
	}
	
	static public String OperatorExpressions() {
		return "^\\d+\\s?(([-+\\/\\*]\\s?\\d+\\s?)?){0,5}";
	}
	
	static public String EmailRegex() {
		return "^\\d+\\s?(([-+\\/\\*]\\s?\\d+\\s?)?){0,5}";
	}
	
	

}
