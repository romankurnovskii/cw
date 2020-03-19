package telran.util;

import java.util.Iterator;
import java.util.function.Predicate;


public class HashSet<T> implements Set<T> {
	
	private static final float FACTOR = 0.75f;
	
	IndexedList<T>[] hashTable = new IndexedLinkedList[16];
	int size;
	
	
	public HashSet(int initialSize) {
		hashTable = new IndexedList[initialSize];
		                System.out.println("HashSet создан");
		                System.out.println(hashTable[0]);
		                System.out.println("длина hashTable " + hashTable.length);
	}
	
	public HashSet() {
		this(16);		
	}

	

	private class HashSetIterator implements Iterator<T> {
		// сначала обозначу как я буду определять откуда начинать итерацию
		int size; // общий размер / кол-во элементов
		int currentInd; //позиция текущего

		@Override
		public boolean hasNext() {
									System.out.println("start hasNext");
									System.out.println("hasNext currentInd " + currentInd);
									System.out.println("hasNext size " + size);
									
			return currentInd < size;   //если текущая позиция меньше чем общий размер значит есть еще элементы справа
		}

		@Override
		public T next() {
			System.out.println("DDDD");
			T resT = null;
			if (currentInd == size) {
				// пока хз что писать если последнего элемента полсе текущего нет
			}
			else {
				resT = (T) hashTable[currentInd + 1];
			}
			return resT;
		}
	}
	
		

	@Override
	public Iterator<T> iterator() { 		// метод имплементируется чтобы я выбрал какой итератор использвоать здесь
		return new HashSetIterator();
	}

	@Override
	public boolean add(T obj) {
		// сначала посмтоим а существует ли объект . сеты не могут содержать одинковых объектов
		if (contains(obj)) {
			return false;
		}
		size++;
		if (size > FACTOR * hashTable.length) {
			recreateHashTable();
		}
		
		int index = getHashTableIndex(obj);
		
		if (hashTable[index] == null ) {							// индекс путсой
			hashTable[index] = new IndexedLinkedList<T>();
		}
									// добираюсь по своему индексу по индесируему спсику и длаю фю эдд
		hashTable[index].add(obj);  				// добавляем в конец
		return true;
	}
	
	
	
	

	private void recreateHashTable() {
		// создаем новую хештаблицу или хешсет с размером таблицы в 2 раза больше чем эта таблица
		// и переписываю объекты туда (одну читаю в другую вставляю)
		
		HashSet<T> tmp = new HashSet<>(hashTable.length * 2);
		for (IndexedList<T> list: hashTable) {
			if (list != null) {
				for (T obj: list) {
					tmp.add(obj);
				}
			}
		}
		hashTable = tmp.hashTable;
	}

	@Override
	public Set<T> filter(Predicate<T> predicate) {
		Set<T> res = new HashSet<T>();
		Iterator<T> myIterator = new HashSetIterator();
		res = null;													// будет новым сетом в результате 
		while (myIterator.hasNext()) {			  //перебираем элементы в исодном сете
			T elemenT = (T) myIterator.next();  		  
			if (predicate.test(elemenT)) {				// если соответствует предикату то
				res.add(elemenT);						// добавляем
			}
		}
		return res;
	}
	

	

	@Override
	public Object remove(Object pattern) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
	//Method removeIf uses removing objects matching a predicate through Iterator
	@Override
	public boolean removeIf(Predicate<T> predicate) {
		Iterator<T> myIterator = new HashSetIterator();
		boolean res = false; 							// пока что не нашли
		while (myIterator.hasNext()) {			   // пока есть следующbй - сверяем
			T elemenT = (T) myIterator.next();  		 //  проставляем текущую
			if (predicate.test(elemenT)) {
				myIterator.remove();
				res = true;
			}
		}
		return res;
	}
	
	

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Boolean contains(T pattern) {
		int index = getHashTableIndex(pattern);
		return hashTable[index] != null && hashTable[index].indexOf(pattern) >= 0;
	}
	
	private int getHashTableIndex(T pattern) {
		int hashCode = pattern.hashCode();
		int index = Math.abs(hashCode) % hashTable.length;
		
		return index; 
	}

	

	
}
