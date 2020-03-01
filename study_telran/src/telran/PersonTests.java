package telran;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import telran.Person;


/*
 * 8 Проверю как работает стандартная сортировка объектов в массиве
 * 9 стандартный массив ругается что надо чтобы было Comparable - иду в Person и пишу implements 
 * 
 *
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */

class PersonTests {

	@Test      // Проверим стандартную ф-ю Sort
	void testSortStandart() { //
		// создам массив чтобы в нем сравнивать
		Person[] persons = {
	  			  new Person("max", 25),
	  			  new Person("dima", 21),
	  			  new Person("dani", 35),
	  	};
		
		// сортируем стандартным методом
        // По какому полю он сортирует объекты? или как? - 
		    //[Сорт по полю которое мы зададим в классе Person в методе CompareTo]		???????????		
		Arrays.sort(persons);
		assertEquals(35, persons[0].getAge());				
	}
	
	

	
	
	
	
	

}
