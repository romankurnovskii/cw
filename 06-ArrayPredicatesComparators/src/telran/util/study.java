package telran.util;

import java.util.function.Predicate;

import telran.tests.EvenNumbersPredicate;

public class study {

	public static void main(String[] args) {
		Predicate<Integer> negative;
		negative = i ->        i < 0; // лямбда выражение - утверждает что i<0 (как бы вместо return)

		// в скобки вставляем это самое i и проверяем
		System.out.println(negative.test(-6));
		System.out.println(negative.test(6));
		System.out.println(negative.test(0));
		
	}

	
	

	
	
	
	
	
	
	
	
	
	
	
}
