package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

import telran.util.IndexedLinkedList;
import telran.util.IndexedList;
import telran.util.Set;

@SuppressWarnings("unchecked")
public class HashSet<T> implements Set<T> {

	private static final float FACTOR = 0.75f;
	IndexedList<T>[] hashTable;
	int size;

	public HashSet(int initialSize) {
		hashTable = new IndexedList[initialSize];
	}

	public HashSet() {
		this(16);
	}

	@Override
	public Iterator<T> iterator() {
		return new HashSetIterator();
	}

	@Override
	public boolean add(T obj) {
		if (contains(obj))
			return false;
		size++;
		if (size > FACTOR * hashTable.length) {
			recreateHashTable();
		}
		int index = getHashTableIndex(obj);
		if (hashTable[index] == null) {
			hashTable[index] = new IndexedLinkedList<T>();
		}
		hashTable[index].add(obj);
		return true;
	}

	private void recreateHashTable() {
		HashSet<T> tmp = new HashSet<>(hashTable.length * 2);
		for (IndexedList<T> list : hashTable) {
			if (list != null) {
				for (T obj : list) {
					tmp.add(obj);
				}
			}
		}
		hashTable = tmp.hashTable;
	}

	@Override
	public Set<T> filter(Predicate<T> predicate) {
		HashSet<T> res = new HashSet<>();
		for (T obj : this) {
			if (predicate.test(obj)) {
				res.add(obj);
			}
		}
		return res;
	}

	@Override
	public Object remove(Object pattern) {
		Object res = null;
		int ind = getHashTableIndex((T) pattern);
		if (hashTable[ind] != null) {
			res = hashTable[ind].remove(pattern);
			if (res != null) {
				size--;
				if (hashTable[ind].size() == 0) {
					hashTable[ind] = null;
				}
			}
		}
		return res;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int initialSize = size;
		Iterator<T> it = iterator();
		while (it.hasNext()) {
			T obj = it.next();
			if (predicate.test(obj)) {
				it.remove();
			}
		}
		return initialSize > size;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(T pattern) {
		int index = getHashTableIndex(pattern);
		return hashTable[index] != null && hashTable[index].indexOf(pattern) >= 0;
	}

	private int getHashTableIndex(T pattern) {
		int hashCode = pattern.hashCode();
		int index = Math.abs(hashCode) % hashTable.length;
		return index;
	}

	private class HashSetIterator implements Iterator<T> {
		int currentIt = 0;
		Iterator<T> iterators[];
		Iterator<T> prevIterator;

		public HashSetIterator() {
			iterators = new Iterator[hashTable.length];
			int ind = 0;
			for (IndexedList<T> list : hashTable) {
				if (list != null && list.iterator().hasNext()) {
					iterators[ind++] = list.iterator();
				}
			}
			iterators = Arrays.copyOf(iterators, ind);
		}

		@Override
		public boolean hasNext() {
			return currentIt < iterators.length && iterators[iterators.length - 1].hasNext();
		}

		@Override
		public T next() {
			Iterator<T> it = iterators[currentIt];
			T res = it.next();
			if (!it.hasNext()) {
				currentIt++;
				prevIterator = it;
			} else {
				prevIterator = null;
			}
			return res;
		}

		@Override
		public void remove() {
			Iterator<T> it = prevIterator == null ? iterators[currentIt] : prevIterator;
			it.remove();
			size--;
		}
	}

}
