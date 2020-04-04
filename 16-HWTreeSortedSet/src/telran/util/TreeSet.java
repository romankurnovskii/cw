package telran.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class TreeSet<T> implements SortedSet<T> {
	Comparator<T> comparator;
	Node<T> root;
	int size;

	private static class Node<T> {
		T obj;
		Node<T> parent;
		Node<T> left; // reference to a less (relative to comparator)
		Node<T> right; // reference to a greater

		public Node(T obj) {
			this.obj = obj;
		}
	}

	public TreeSet(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	@SuppressWarnings("unchecked")
	public TreeSet() {
		this((Comparator<T>) Comparator.naturalOrder());

	}

	@Override
	public Iterator<T> iterator() {
		return new TreeIterator();
	}

	@Override
	public boolean add(T obj) {
		if (root == null) {
			addRoot(obj); // addRoot creates new Node that will be root
			return true;
		}
		Node<T> parent = getParent(obj);
		// if obj already exists (compare return 0) -> returns false

		if (parent == null) {
			return false;
		}
		Node<T> newNode = new Node<>(obj);
		if (comparator.compare(obj, parent.obj) < 0) {
			parent.left = newNode;
		} else {
			parent.right = newNode;
		}
		size++;
		newNode.parent = parent;
		return true;
	}

	private void addRoot(T obj) {
		size = 1;
		root = new Node<>(obj);

	}

	private Node<T> getParent(T obj) {

		// beginning from the root we are going either to left or to right
		// going to left -> current = current.left
		// going to right -> current = current.right
		// node from which you have come to null will be parent
		Node<T> current = root;
		Node<T> parent = null;
		while (current != null) {
			parent = current;
			int resComp = comparator.compare(obj, current.obj);
			if (resComp == 0) {
				parent = null;
				break;
			}
			current = resComp < 0 ? current.left : current.right;

		}
		return parent;
	}

	@Override
	public Set<T> filter(Predicate<T> predicate) {
		TreeSet<T> res = new TreeSet<>();
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
		Node<T> node = findNode(pattern);
		if (node != null) {
			res = node.obj;
			removeNode(node);
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	private Node<T> findNode(Object pattern) {
		Node<T> current = root;
		int compRes;
		while (current != null && (compRes = comparator.compare((T) pattern, current.obj)) != 0) {
			current = compRes < 0 ? current.left : current.right;
		}
		return current;
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public boolean contains(T pattern) {
		return size > 0 && getParent(pattern) == null;
	}

	private Node<T> getLeastNode(Node<T> node) {
		Node<T> current = node;
		while (current.left != null) {
			current = current.left;
		}
		return current;
	}

	private Node<T> getParentFromLeft(Node<T> node) {
		while (node.parent != null && node.parent.right == node) {
			node = node.parent;
		}
		return node.parent;
	}

	private void removeNode(Node<T> node) {
		if (isJunction(node)) {
			Node<T> substitute = getLeastNode(node.right);
			node.obj = substitute.obj;
			node = substitute;
		}
		removeNonJunctionNode(node);
		size--;
	}

	private void removeNonJunctionNode(Node<T> node) {
		Node<T> parent = node.parent;
		Node<T> child = node.left == null ? node.right : node.left;
		if (parent == null) {
			// removing root as non-junction node
			root = child;

		} else if (parent.left == node) {
			parent.left = child;
		} else
			parent.right = child;
		if (child != null) {
			child.parent = parent;
		}

	}

	private boolean isJunction(Node<T> node) {

		return node.left != null && node.right != null;
	}

	private class TreeIterator implements Iterator<T> {
		Node<T> current = root != null ? getLeastNode(root) : null;
		Node<T> previous;

		@Override
		public boolean hasNext() {

			return current != null;
		}

		@Override
		public T next() {
			T res = current.obj;
			previous = current;
			current = current.right != null ? getLeastNode(current.right) : getParentFromLeft(current);
			return res;
		}

		@Override
		public void remove() {
			if (isJunction(previous)) {
				current = previous;
			}
			removeNode(previous);
		}
	}

	@Override
	public T getMin() {
		Node<T> currentNode = root; // минимальный он и рут для начала
		T currentT = currentNode.obj; // если есть элемент справа значит он минимальный

		while (currentNode.left != null) {
			currentNode = currentNode.left;
		}
		return currentT;
	}

	@Override
	public T getMax() {
		Node<T> currentNode = root;
		T currentT = currentNode.obj;
		while (currentNode.left != null) {
			currentNode = currentNode.left;
		}
		return currentT;
	}

	@Override
	public SortedSet<T> subset(T from, boolean isIncludedFrom, T to, boolean isIncludedTo) {
		// вывести сет от from элемента до to элемента
		// результатом будет новый сет
		// если инклюдет равен то сую
		// перебираю весь сет пока хз как не перебирать весь а только диапазон
		// стопэ . тут получаю сам объект а перебираю ноды. значит мне сначала ноду
		// поулчить по объекту надо
		// а вот вторую ТУ наверн не надо если я буду сранивать объекты этих нод

		SortedSet<T> res = new TreeSet<T>(); // итоговый сет

		Node<T> current = root; // определяю ноду по объекту
		while (current != null) {
			int resComp = comparator.compare(from, current.obj);
			if (resComp == 0) {
				break;
			}
			current = resComp < 0 ? current.left : current.right;
		}
		// определил ноду от которой начинаю
		if (isIncludedFrom)
			res.add(current.obj);
		// за основну возьму что from всегда меньше чем to
		while (comparator.compare(current.obj, to) == -1) { // пока мой карент меньше ту
			// TODO добавляю в сет и получаю родителя карента
			res.add(current.obj);
			getParentFromLeft(current);
		}

		if (isIncludedTo)
			res.add(current.obj);

		return res;
	}

	private boolean isValidArguments(T from, boolean isIncludedFrom, T to, boolean isIncludedTo) {
		if (from == null || to == null)
			return false;
		int compRes = comparator.compare(from, to);
		return compRes < 0 || (compRes == 0 && isIncludedFrom && isIncludedTo);
	}

	private SortedSet<T> getSetFromNodeToObject(SortedSet<T> set, Node<T> nodeFrom, T to, boolean isIncluded) {
		SortedSet<T> res = set;
		Node<T> node = nodeFrom;
		while (node != null) {
			res.add(node.obj);
			node = getNext(node, to, isIncluded);
		}
		return res;
	}

	private Node<T> getNext(Node<T> current, T to, boolean isIncluded) {
		Node<T> res = current.right != null ? getLeastNode(current.right) : getParentFromLeft(current);
		if (res == null) {
			return null;
		}
		int compRes = comparator.compare(to, res.obj);
		return compRes < 0 || (compRes == 0 && !isIncluded) ? null : res;
	}

	private Node<T> findClosestGreaterEqual(T pattern, boolean isIncluded) {
		Node<T> current = root;
		Node<T> res = null;
		while (current != null) {
			int resComp = comparator.compare(pattern, current.obj);
			if (resComp < 0) {
				res = current;
				current = current.left;
			} else if (resComp > 0) {
				current = current.right;
			} else {
				if (isIncluded) {
					res = current;
				} else {
					if (current.right != null) {
						res = getLeastNode(current.right);
					}
				}
				break;
			}
		}
		return res;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SortedSet<T> head(T key, boolean isIncluded) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<T> tail(T key, boolean isIncluded) {
		// TODO Auto-generated method stub
		return null;
	}

	public int height() {
		return height(root);
	}

	private int height(Node<T> root) {

		int res = 0;
		if (root != null) {
			int heightRight = height(root.right);
			int heightLeft = height(root.left);
			res = 1 + Math.max(heightRight, heightLeft);
		}

		return res;
	}

	public int width() {
		return width(root);
	}

	private int width(Node<T> root) {
		if (root == null) {
			return 0;
		}
		if (root.left == null && root.right == null) {
			return 1;
		}

		return width(root.left) + width(root.right);
	}

	public void rotatedTreeDisplay() {
		rotatedDisplay(root, 1);
	}

	private void rotatedDisplay(Node<T> root, int level) {
		// gets root and its level
		if (root != null) {
			rotatedDisplay(root.right, level + 1);
			displayRoot(root, level);
			rotatedDisplay(root.left, level + 1);
		}

	}

	private void displayRoot(Node<T> root, int level) {
		printOffset(level);
		System.out.println(root.obj);
	}

	int SPACES_PER_LEVEL = 2;

	private void printOffset(int level) {
		int limit = level * SPACES_PER_LEVEL;
		for (int i = 0; i < limit; i++) {
			System.out.print("- ");

		}
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

//	ArrayList<ArrayList<Integer> levels = tree.getObjectsByLevels(); 
//	levels.get(0) – ArrayList<Integer> containing number 1
//	levels.get(1) – ArrayList<Integer> containing number 2
//	levels.get(2) – ArrayList<Integer> containing number 7
//	levels.get(3) – ArrayList<Integer> containing number 6
//	levels.get(4) – ArrayList<Integer> containing number 4
//	levels.get(5) – ArrayList<Integer> containing numbers 3 and 5

	
				
	public ArrayList<ArrayList<Node>> arrayResult = new ArrayList<>();			// создам арэйлист эрэйлистов куда буду помещать

	
	public void addToList() {	
		int levels = height();							// определю максимальное кол-во уровней
		
		addElementToSmallArray(); 						// надо както добавлять элемент 
			
	}
	
	private void addElementToSmallArray() {
		arrayResult.add(new ArrayList<>());				// добавлю рут и после него начинаю скакать
		arrayResult.get(0).add(root);
		
		addElementToSmallArray(root, 1);    			// начинаю рекурсию с первой позиции
		
		System.out.println("d");
//		if (arrayResult.size() <= this.)
		
	}
	
	
	private void addElementToSmallArray(Node<T> root, int level) {
		if (arrayResult.size() <= level) {
			arrayResult.add(new ArrayList<>());	
			arrayResult.add(new ArrayList<>());
			arrayResult.add(new ArrayList<>());
			arrayResult.add(new ArrayList<>());
		}
		
		
		if (root != null) {
			addElementToSmallArray(root.right, level + 1);
			addElementToSmallArray(root.left, level + 1);
			
			
			arrayResult.get(level).add(root);
				
		}
		
		
	}
	
	

	

}
