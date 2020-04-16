package telran.util;

import telran.tests.perfomance.JoinStringsInterface;

public class JoinStringsImplString implements JoinStringsInterface {

	@Override
	public String join(String[] strings, String delimiter) {
		// TODO method operator +

		String res = "";

		for (String el : strings) {
			res = res + el + delimiter;
		}
		return res;
	}

}
