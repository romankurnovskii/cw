package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import telran.util.Array;
import telran.util.IndexedList;

class ArrayTests {
	int numbers[] = {10, -8, 70, 75, 30};
	@Test
	void testAddGetSize() {
		Array<Integer> array = getArray();
		for (int i = 0; i < array.size(); i++) {
			assertEquals(numbers[i], (int)array.get(i));
		}
		assertNull(array.get(array.size()));
		
	}
	private Array<Integer> getArray() {
		Array<Integer> array = new Array(4);
		
		for (int i = 0; i < numbers.length; i++) {
			array.add(numbers[i]);
		}
		return array;
	}
	@Test
	void testAddAtIndex() {
		int expectedNumbers[] = {-10, 10, -8, 70, -70, 75, 30, -30};
		Array<Integer> array = getArray();
		assertTrue(array.add(0, -10));
		assertTrue(array.add(4, -70));
		assertTrue(array.add(7, -30));
		int actualNumbers[] = getActualNumbers(array);
		assertArrayEquals(expectedNumbers, actualNumbers );
		assertFalse(array.add(-1, 100));
		assertFalse(array.add(100, 100));
		
	}
	private int[] getActualNumbers(Array<Integer> array) {
		int res[] = new int[array.size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = (int) array.get(i);
		}
		return res;
	}
	private int[] getActualNumbers(IndexedList<Integer> list) {
		int res[] = new int[list.size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = (int) list.get(i);
		}
		return res;
	}
	
	
	@Test
	void testRemoveAtIndex() {
		int expectedNumbers[] = { -8, 75};
		Array<Integer> array = getArray();
		assertEquals(10, array.remove(0));
		assertEquals(70, array.remove(1));
		assertEquals(30, array.remove(2));
		assertArrayEquals(expectedNumbers, getActualNumbers(array));
		assertNull(array.remove(2));
		assertNull(array.remove(-1));
	}
	@Test
	void testSetAtIndex() {
		int expectedNumbers[] = {-10, -8, -70, 75, -30};
		Array<Integer> array = getArray();
		assertEquals(10, array.set(0, -10));
		assertEquals(70, array.set(2, -70));
		assertEquals(30, array.set(4, -30));
		assertArrayEquals(expectedNumbers, getActualNumbers(array));
		assertNull(array.set(-1, 100));
		assertNull(array.set(100, 100));
	}
	@Test
	void testSorting() {
		
		Person personMoshe = new Person(123, "Moshe", 1980);
		Person personVova = new Person(100, "Vova", 1970);
		Array<Person> array = new Array<>();
		array.add(personMoshe);
		array.add(personVova);
		array.sort();
		assertEquals(personVova, array.get(0));
		assertEquals(personMoshe, array.get(1));
		array.sort(new PersonAgeComparator());
		assertEquals(personVova, array.get(1));
		assertEquals(personMoshe, array.get(0));
		
	}
	@Test
	void testBinarySearch() {
		String stringsNaturalOrder[]=
			{"abcd","lm", "lmnopr","x","y","z"};
		String stringsLengthOrder[]=
			{"x","y","z","lm","abcd", "lmnopr"};
		Comparator<String> compLength = new StringLengthComparator();
		Array<String> stringsNatural = getArrayStrings(stringsNaturalOrder);
		Array<String> stringsLength = getArrayStrings(stringsLengthOrder);
		assertEquals(-3, stringsNatural.binarySearch("lmn"));
		assertEquals(1, stringsNatural.binarySearch("lm"));
		assertEquals(-5, stringsLength.binarySearch("lmn", compLength));
		assertEquals(3, stringsLength.binarySearch("lm", compLength ));
	}
	private Array<String> getArrayStrings(String[] strings) {
		Array<String> array = new Array(strings.length);
		for (int i = 0; i < strings.length; i++) {
			array.add(strings[i]);
		}
		return array;
	}
	@Test
	void testFilter() {
		Array<Integer> array = getArray();
		int expected[] = {10, -8, 70, 30};
		Array<Integer> arrayNoEven =
				array.filter(new EvenNumbersPredicate());
		int actualNumbers[] = getActualNumbers(arrayNoEven);
		assertArrayEquals(expected, actualNumbers);
	}
	@Test
	void testRemoveIf() {
		Array<Integer> array = getArray(); 
		//{10, -8, 70, 75, 30};
		array.add(75);
		int expected[] = {75, 75};
		EvenNumbersPredicate predicateEven = new EvenNumbersPredicate();
		assertTrue(array.removeIf(predicateEven));
		assertFalse(array.removeIf(predicateEven));
		assertArrayEquals(expected,
				getActualNumbers(array));
	}
	@Test
	/**
	 * additional test for sorting array numbers according to the following
	 * all odd numbers should go before the even ones
	 * odd numbers should be sorted in the ascending order
	 * even numbers should be sorted in the descending order
	 */
	void testSortingEvenOdd( ) {
		Array<Integer> array = getArray();
		// {10, -8, 70, 75, 30}
		array.add(73);
		array.add(3);
		int []expected = {3, 73, 75, 70, 30, 10, -8};
		array.sort(new EvenOddComparator());
		assertArrayEquals(expected,
				getActualNumbers(array));
	}
	@Test
	void testRemoveObject() {
		Array<Integer> array = getArray();
		//{10, -8, 70, 75, 30};
		int expected[]={10, -8,  75, 30};
		array.remove((Integer)70);
		assertArrayEquals(expected,
				getActualNumbers(array));
	}


 // обойдем все элементы
	// рассчитываем сумму всех элементов
 @Test
 void testIterableSum() {
	  IndexedList<Integer> list = getArray();  // {10, -8, 70, 75, 30}; - сумма получается 177
	  int sumExpected = 177;
	  int sumActual = 0; // реальная сумма пока что равняется нулую
	  // сейчас  обойдем стандартныйм кодом джавы
	  for (Integer num: list) {  //идем по массиву List // чтобы этот код заработал 
		             			// list должен быть iterable - я в 
		  						// инетрфейс IndexedList<T> добавил extends Iterable<T> - означает что он итерабл		  
		  sumActual += num;   //суммируем элементы
	  }
	  assertEquals(sumExpected, sumActual);
	   
 }

 // удалит элементы 
 // удалим все четные положительные
 @Test
 void testIterableRemove() {
	  IndexedList<Integer> list = getArray();  // {10, -8, 70, 75, 30}; 
	  int Expected[] = {-8, 75};
	  for (Integer num: list) { 
		  if  ( num > 0 && num % 2 == 0 ) {   // если число больше нуля и от остатка ноль
			  list.remove(num);  			//удаляю уже напсианным методом
		  }
			  
	  }
		 assertArrayEquals(Expected, getActualNumbers(list));  //getActualNumbers - писали этот метод чтобы он вывел массив
}
 // этот тест работает НО не сработает если мы добавим утда часть чисел т к ф-я ремув работает не так как надо здесь

 
 // удалит элементы 
 // удалим все четные положительные
 @Test
 void testIterableRemove2() {
	  IndexedList<Integer> list = getArray();  // {10, -8, 70, 75, 30}; 
	  list.add(77);
	  list.add(78);
	  list.add(88);
	  int Expected[] = {-8, 75};
	  for (Integer num: list) { 
		  if  ( num > 0 && num % 2 == 0 ) {   // если число больше нуля и от остатка ноль
			  list.remove(num);  			//удаляю уже напсианным методом
		  }
			  
	  }
	  Iterator<Integer> it = list.iterator();
	  while (it.hasNext()) {
		  Integer num = it.next();
		  if (num >= 0 && num % 2 == 0) {
			  it.remove();  // здесь выдаст ошибку ЗАДАЧА НАПИСАТЬ МЕТОД РЕМУВ КОТОРЫЙ ПРАВИЛЬНО УДАЛЯЕТ ИЗ МАССИВА И КОРРЕКТИРУТ ПРАИВЛЬНО САМ МАССИВ В КЛАССЕ ИНДЕКС
		  }
	  }
	  assertArrayEquals(Expected, getActualNumbers(list));  //getActualNumbers - писали этот метод чтобы он вывел массив
}
 // этот тест работает НО не сработает если мы добавим утда часть чисел т к ф-я ремув работает не так как надо здесь



 

}