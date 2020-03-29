package telran.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

public class IndexedLinkedList<T> implements IndexedList<T> {

	private Node<T> head;
	private Node<T> tail;
	private int size;
	private T[] arrayT; // создал массив чтобы в нем сортировать ноды

	private static class Node<T> {
		public T obj;
		public Node<T> next;
		public Node<T> prev;

		public Node(T obj) {
			this.obj = obj;
		}
	}

	private class ListIterator implements Iterator<T> {

		Node<T> current = head;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			T res = current.obj;
			current = current.next;
			return res;
		}

		@Override
		public void remove() {
			if (current == null) {
				removeTail();
			} else {
				removeNode(current.prev);
			}

		}

	}

	public IndexedLinkedList() {

	}

	public IndexedLinkedList(int dummy) {

	}

	@Override
	public Iterator<T> iterator() {
		return new ListIterator();
	}

	@Override
	public void add(T obj) {
		Node<T> newNode = new Node<>(obj);
		addNodeTail(newNode);
	}

	@Override
	public boolean add(int index, T obj) {
		boolean res = true;
		Node<T> newNode = new Node<>(obj);
		if (index == 0) {
			addNodeHead(newNode);
		} else if (index == size) {
			addNodeTail(newNode);
		} else if (isValidIndex(index)) {
			Node<T> beforeNode = getNode(index);
			addNodeMiddle(newNode, beforeNode);
		} else {
			res = false;
		}
		return res;
	}

	private void addNodeMiddle(Node<T> newNode, Node<T> beforeNode) {
		newNode.next = beforeNode;
		newNode.prev = beforeNode.prev;
		beforeNode.prev.next = newNode;
		beforeNode.prev = newNode;
		size++;
	}

	private void addNodeHead(Node<T> newNode) {
		if (head == null) {
			head = tail = newNode;
		} else {
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		}
		size++;

	}

	private void addNodeTail(Node<T> newNode) {
		if (head == null) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		size++;
	}

	private Node<T> getNode(int ind) {
		return ind < size / 2 ? getFromLeft(ind) : getFromRight(ind);
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
			Node<T> nodeRes = getNode(ind);
			res = nodeRes.obj;
		}
		return res;
	}

	private Node<T> getFromRight(int ind) {
		Node<T> current = tail;
		for (int i = size - 1; i > ind; i--) {
			current = current.prev;
		}
		return current;
	}

	private Node<T> getFromLeft(int ind) {

		Node<T> current = head;
		for (int i = 0; i < ind; i++) {
			current = current.next;
		}
		return current;
	}

	private boolean isValidIndex(int ind) {
		return ind >= 0 && ind < size;
	}

	@Override
	public int indexOf(Object pattern) {
		int res = -1;
		if (pattern != null) {
			Node<T> current = head;
			for (int i = 0; i < size; i++) {
				if (pattern.equals(current.obj)) {
					res = i;
					break;
				}
				current = current.next;
			}
		}
		return res;
	}

	@Override
	public int lastIndexOf(Object pattern) {
		int res = -1;
		if (pattern != null) {
			Node<T> current = tail;
			for (int i = size - 1; i >= 0; i--) {
				if (pattern.equals(current.obj)) {
					res = i;
					break;
				}
				current = current.prev;
			}
		}
		return res;
	}

	@Override // находит ноду -> удаляет ноду вызывая ф-ю а сама выводит ее obj в ретерн
	public Object remove(int ind) {
		Object res = null;
		if (isValidIndex(ind)) {
			Node<T> removedNode = getNode(ind);
			res = removedNode.obj;
			removeNode(removedNode);
		}
		return res;
	}

	private void removeNode(Node<T> removedNode) {
		if (removedNode == head) {
			removeHead();
		} else if (removedNode == tail) {
			removeTail();
		} else {
			removeNodeMiddle(removedNode);
		}

	}

	private void removeNodeMiddle(Node<T> removedNode) {
		removedNode.next.prev = removedNode.prev;
		removedNode.prev.next = removedNode.next;
		size--;
	}

	private void removeTail() {
		if (head == tail) {
			head = tail = null;
		} else {
			tail.prev.next = null;
			tail = tail.prev;
		}
		size--;

	}

	private void removeHead() {
		if (head == tail) {
			head = tail = null;
		} else {
			head.next.prev = null;
			head = head.next;
		}
		size--;

	}

	/*
	 * 1-й мой вариант
	 * 
	 */
	@Override
	public Object remove(Object pattern) {
		Object res = null;
		if (pattern != null) {
			Node<T> current = head;
			for (int i = 0; i < size; i++) {
				if (pattern.equals(current.obj)) {
					remove(i);
					res = current.obj;
					break;
				}
				current = current.next;
			}
		}
		return res;
	}

	/*
	 * 2-й вариант решения: Object remove(Object pattern) - 2 passes
	 * 
	 * int index = indexOf(pattern) Object res = null; if (index >= 0) { Node<T>
	 * node = getNode(index); res = node.obj; removeNode(node); }
	 */

	/*
	 * 3-й вариант - 1 pass
	 * 
	 * Node<T> current = head; while (current != null &&
	 * !current.obj.equals(pattern)) { current = curent.next } Object res = null; if
	 * (current != null) { res = current.obj; removeNode(current); } return res;
	 * 
	 */

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		Node<T> current = head;
		boolean res = false;
		for (int i = 0; i < size; i++) {
			if (predicate.test(current.obj)) {
				this.removeNode(current);
				size--;
				res = true;
			}
		}
		return res;
	}

	// Method removeIf uses removing objects matching a predicate through
	// ListIterator
	public boolean removeIfUsingIterator(Predicate<T> predicate) {
		Iterator<T> linkIterator = new ListIterator();
		boolean res = false; // пока что не нашли
		while (linkIterator.hasNext()) { // пока есть следующая нода - сверяем
			T nodaT = (T) linkIterator.next(); // проставляем текущую
			if (predicate.test(nodaT)) {
				linkIterator.remove();
				res = true;
			}
		}
		return res;
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

		arrayT = (T[]) new Object[size]; // cоздал массив объектов типа Т размером взятым из list

		Node<T> curreNode = head; // начинаю идти с 1-й ноды
		for (int i = 0; i < arrayT.length; i++) { // добавил все объекты в массиы
			arrayT[i] = curreNode.obj;
			curreNode = curreNode.next;
		}

		Arrays.sort(arrayT, comp); // сортирую стандартно и в качестве интер использую параметр comp

		// ноды оставляю те же, а вот объекты в них вставляю согласно проведенной
		// сортировке
		for (int i = 0; i < arrayT.length; i++) {
			curreNode.obj = arrayT[i];
			curreNode = curreNode.next;
		}

	}

}

//
//
//1.
//Complete all methods of the class IndexedLinkedList and make sure that all the tests pass.
//Below are some hints for the methods
//1.1. Method removeIf uses removing objects matching a predicate through ListIterator
//1.2. Method sort and updates related to this method:
//1.2.1. Creates array T objects from list
//1.2.2.Sorts array objects using method sort of the class Arrays – Arrays.sort(array, comparator)
//1.2.3. Add new field T[] array inside the class and this field should contain the reference to the array sorted in 1.2.2
//1.2.4. Pass over whole linked list starting from the head and in each node set object in the proper order from the array
//created and sorted in the 1.2.1 1.2.3
//1.2.5. In two add functions you should add array = null. It’s done for the binarySearch methods explained below

//1.3. Method binarySearch
//1.3.1. The method inspects the field “array” explained in the 1.2
//1.3.2. In the case the field is null the binarySearch calls the method sort for sorting and
//setting the sorted array. That’s why after adding new object the array should be
//set in null because any adding breaks the sorted array. Sorting after adding doesn’t
//make any sense as only for binarySearch the list should be sorted. If the array is
//not null it means that there was sorting and after the sorting there wasn’t adding
//1.3.3. binarySearch calls the method binarySearch of the class Arrays
//(Arrays.binarySearch(array, ...) and its result should be returned from the function
