package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.util.Array;
import telran.util.IndexedList;

class IndexedListTests {
	int numbers[] = {10, -8, 70, 75, 30};
	IndexedList<Integer> listNumbers;
	@BeforeEach
	void setUp() {
		listNumbers = getList();
	}
	@Test
	void testAddGetSize() {
	//	Array<Integer> listNumbers = getList();   теперь а т к онавыоплняется теперь в setUp
		for (int i = 0; i < listNumbers.size(); i++) {
			assertEquals(numbers[i], (int)listNumbers.get(i));
		}
		assertNull(listNumbers.get(listNumbers.size()));
		
	}
	private IndexedList<Integer> getList() {
		IndexedList<Integer> list = new Array<>();  //single place of updating code 
		
		for (int i = 0; i < numbers.length; i++) {
			list.add(numbers[i]);
		}
		return list;
	}
	
	@Test
	void testAddAtIndex() {
		int expectedNumbers[] = {-10, 10, -8, 70, -70, 75, 30, -30};
		assertTrue(listNumbers.add(0, -10));
		assertTrue(listNumbers.add(4, -70));
		assertTrue(listNumbers.add(7, -30));
		int actualNumbers[] = getActualNumbers(listNumbers);
		assertArrayEquals(expectedNumbers, actualNumbers );
		assertFalse(listNumbers.add(-1, 100));
		assertFalse(listNumbers.add(100, 100));
		
	}
	private int[] getActualNumbers(Array<Integer> listNumbers) {
		int res[] = new int[listNumbers.size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = (int) listNumbers.get(i);
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
		assertEquals(10, listNumbers.remove(0));
		assertEquals(70, listNumbers.remove(1));
		assertEquals(30, listNumbers.remove(2));
		assertArrayEquals(expectedNumbers, getActualNumbers(listNumbers));
		assertNull(listNumbers.remove(2));
		assertNull(listNumbers.remove(-1));
	}
	@Test
	void testSetAtIndex() {
		int expectedNumbers[] = {-10, -8, -70, 75, -30};
		assertEquals(10, listNumbers.set(0, -10));
		assertEquals(70, listNumbers.set(2, -70));
		assertEquals(30, listNumbers.set(4, -30));
		assertArrayEquals(expectedNumbers, getActualNumbers(listNumbers));
		assertNull(listNumbers.set(-1, 100));
		assertNull(listNumbers.set(100, 100));
	}
	@Test
	void testSorting() {
		
		Person personMoshe = new Person(123, "Moshe", 1980);
		Person personVova = new Person(100, "Vova", 1970);
			
		try {
			IndexedList<Person> listPersons = listNumbers.getClass() // getting object of Class
						.getConstructor() // getting constructor by default
						.newInstance();
			listPersons.add(personMoshe);
			listPersons.add(personVova);
			listPersons.sort();
			assertEquals(personVova, listPersons.get(0));
			assertEquals(personMoshe, listPersons.get(1));
			listPersons.sort(new PersonAgeComparator());
			assertEquals(personVova, listPersons.get(1));
			assertEquals(personMoshe, listPersons.get(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	void testBinarySearch() {
		String stringsNaturalOrder[]=
			{"abcd","lm", "lmnopr","x","y","z"};
		String stringsLengthOrder[]=
			{"x","y","z","lm","abcd", "lmnopr"};
		Comparator<String> compLength = new StringLengthComparator();
		IndexedList<String> stringsNatural = getListStrings(stringsNaturalOrder);
		IndexedList<String> stringsLength = getListStrings(stringsLengthOrder);
		assertEquals(-3, stringsNatural.binarySearch("lmn"));
		assertEquals(1, stringsNatural.binarySearch("lm"));
		assertEquals(-5, stringsLength.binarySearch("lmn", compLength));
		assertEquals(3, stringsLength.binarySearch("lm", compLength ));
	}
	private IndexedList<String> getListStrings(String[] strings) {
		// Array<String> listNumbers = new Array<>(strings.length);     <- this was
		try {
			// getting an object of the same class as the object listNumbers
			/*********************/
			IndexedList<String> list = 
					listNumbers.getClass().getConstructor(int.class)
					.newInstance(strings.length);
			/*********************/
			for (int i = 0; i < strings.length; i++) {
				list.add(strings[i]);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Test
	void testFilter() {
		int expected[] = {10, -8, 70, 30};
		IndexedList<Integer> listNoEven =
				listNumbers.filter(new EvenNumbersPredicate());
		int actualNumbers[] = getActualNumbers(listNoEven);
		assertArrayEquals(expected, actualNumbers);
	}
	@Test
	void testRemoveIf() {
		//{10, -8, 70, 75, 30};
		listNumbers.add(75);
		int expected[] = {75, 75};
		EvenNumbersPredicate predicateEven = new EvenNumbersPredicate();
		assertTrue(listNumbers.removeIf(predicateEven));
		assertFalse(listNumbers.removeIf(predicateEven));
		assertArrayEquals(expected,
				getActualNumbers(listNumbers));
	}
	@Test
	/**
	 * additional test for sorting listNumbers numbers according to the following
	 * all odd numbers should go before the even ones
	 * odd numbers should be sorted in the ascending order
	 * even numbers should be sorted in the descending order
	 */
	void testSortingEvenOdd( ) {
	//	Array<Integer> listNumbers = getList();
		// {10, -8, 70, 75, 30}
		listNumbers.add(73);
		listNumbers.add(3);
		int []expected = {3, 73, 75, 70, 30, 10, -8};
		listNumbers.sort(new EvenOddComparator());
		assertArrayEquals(expected,
				getActualNumbers(listNumbers));
	}
	@Test
	void testRemoveObject() {
		//{10, -8, 70, 75, 30};
		int expected[]={10, -8,  75, 30};
		listNumbers.remove((Integer)70);
		assertArrayEquals(expected,
				getActualNumbers(listNumbers));
	}


 // обойдем все элементы
	// рассчитываем сумму всех элементов
 @Test
 void testIterableSum() {
	  int sumExpected = 177;    // {10, -8, 70, 75, 30}; - сумма получается 177
	  int sumActual = 0; // реальная сумма пока что равняется нулую
	  // сейчас  обойдем стандартныйм кодом джавы
	  for (Integer num: listNumbers) {  //идем по массиву List // чтобы этот код заработал 
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
	 // IndexedList<Integer> list = getList();  // {10, -8, 70, 75, 30}; 
	  listNumbers.add(77);  // вот эти 3 добавления не выполнят тест как хотим т к будут стоять в разных местах
	  listNumbers.add(78);
	  listNumbers.add(88);
	  int Expected[] = {-8, 75};
	  for (Integer num: listNumbers) { 
		  if  ( num > 0 && num % 2 == 0 ) {   // если число больше нуля и от остатка ноль
			  listNumbers.remove(num);  			//удаляю уже напсианным методом
		  }
			  
	  }
		 assertArrayEquals(Expected, getActualNumbers(listNumbers));  //getActualNumbers - писали этот метод чтобы он вывел массив
}
 /* 
  * этот тест работает НО не сработает если мы добавим утда
  * часть чисел т к ф-я ремув работает не так как надо здесь
  * ЗАДАЧА НАПИСАТЬ МЕТОД РЕМУВ КОТОРЫЙ ПРАВИЛЬНО УДАЛЯЕТ ИЗ МАССИВА И 
  * КОРРЕКТИРУТ ПРАИВЛЬНО САМ МАССИВ В КЛАССЕ ИНДЕКС
  */
 
 
 
 


 
 

}