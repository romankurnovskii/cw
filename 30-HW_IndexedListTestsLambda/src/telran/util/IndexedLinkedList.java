package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

public class IndexedLinkedList<T> implements IndexedList<T>{
	
private static class Node<T> {
	public T obj;
	public Node<T> next;
	public Node<T> prev;
	public Node(T obj) {
		this.obj = obj;
	}
}
private Node<T> head;
private Node<T> tail;
private int size;
public IndexedLinkedList() {
	
}
public IndexedLinkedList(int dummy) {
	
}
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(T obj) {
		Node<T> newNode = new Node<>(obj);
		if(head == null) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		size++;
	}

	@Override
	public boolean add(int ind, T obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int binarySearch(T pattern) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int binarySearch(T pattern, Comparator<T> comp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IndexedList<T> filter(Predicate<T> predicate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T get(int ind) {
		T res = null;
		if (isValidIndex(ind)) {
			res = ind < size / 2 ? getFromLeft(ind) : getFromRight(ind);
		}
		return res;
	}

	private T getFromRight(int ind) {
		Node<T> current = tail;
		for (int i = size - 1; i > ind; i--) {
			current = current.prev;
		}
		return current.obj;
	}

	private T getFromLeft(int ind) {
		
		Node<T> current = head;
		for( int i = 0; i < ind; i++) {
			current = current.next;
		}
		return current.obj;
		
		
		
		
		
		
		
	}

	private boolean isValidIndex(int ind) {
		return ind >= 0 && ind < size;
	}

	@Override
	public int indexOf(Object pattern) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(Object pattern) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object remove(int ind) {
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
	public Object set(int ind, T newObj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void sort() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sort(Comparator<T> comp) {
		// TODO Auto-generated method stub
		
	}

}
