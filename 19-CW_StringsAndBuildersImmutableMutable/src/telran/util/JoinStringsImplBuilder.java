package telran.util;

import telran.tests.perfomance.JoinStringsInterface;

public class JoinStringsImplBuilder implements JoinStringsInterface {

	@Override
	public String join(String[] strings, String delimiter) {
		// TODO method based on stringbulder append
		
		StringBuilder sBuilder = new StringBuilder();
		
		for(String el:strings) {
			sBuilder.append(el).append(delimiter);
		}
		
		return sBuilder.toString();
	}

}
