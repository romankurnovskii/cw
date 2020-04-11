package telran.tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.util.JoinStringsImplBuilder;
import telran.util.JoinStringsImplString;

class StringAndBuilder {
	
	String[] ar = {"Hello", "World"};

	
	@Test
	void testStringReplace() {
		String str = new String("Hello");
		str = str.replace("l", "*");
		assertEquals("He**o", str);
		
	}
	
	
	@Test
	void testStringBuilderReplace() {
		StringBuilder str = new StringBuilder("Hello");
		str.replace(2, 4, "**");
		assertEquals("He**o", str.toString());
	}
	

	
	@Test
	void testJoinStringsImplString() {
		JoinStringsImplString str = new JoinStringsImplString();
		assertEquals("Hello World ", str.join(ar, " "));
		//TODO remove "space" at the end of new string
	}
	
	@Test
	void testJoinStringsImplBuilder() {
		JoinStringsImplBuilder str = new JoinStringsImplBuilder();
		assertEquals("Hello World ", str.join(ar, " "));
		//TODO remove "space" at the end of new string
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
