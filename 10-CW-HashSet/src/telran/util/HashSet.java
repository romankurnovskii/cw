package telran.util;

import java.util.Iterator;
import java.util.function.Predicate;


public class HashSet<T> implements Set<T> {
	private static final float FACTOR = 0.75f;
	IndexedList<T>[] hashTable = new IndexedLinkedList[16];
	int size;
	
	
	public HashSet(int initialSize) {
		hashTable = new IndexedList[initialSize];
	}
	
	public HashSet() {
		this(16);		
	}
	

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object remove(Object pattern) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		// TODO Auto-generated method stub
		return false;
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
