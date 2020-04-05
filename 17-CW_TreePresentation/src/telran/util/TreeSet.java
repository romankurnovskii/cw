package telran.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

import telran.util.TreePresentation.Node;

public class TreeSet<T> implements SortedSet<T> {
	private static class Node<T> {
		T obj;
		Node<T> parent;
		Node<T> left; // reference to a less (relative to comparator)
		Node<T> right; // reference to a greater

		public Node(T obj) {
			this.obj = obj;
		}
	}

	private static final int SPACES_PER_LEVEL = 2;

	Comparator<T> comparator;
	Node<T> root;
	int size;

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

		return root != null ? getLeastNode(root).obj : null;
	}

	@Override
	public T getMax() {

		return root != null ? getMostNode(root).obj : null;
	}

	private Node<T> getMostNode(Node<T> node) {
		while (node.right != null) {
			node = node.right;
		}

		return node;
	}

	@Override
	public SortedSet<T> subset(T from, boolean isIncludedFrom, T to, boolean isIncludedTo) {

		SortedSet<T> res = new TreeSet<>(comparator);
		if (isValidArguments(from, isIncludedFrom, to, isIncludedTo)) {
			Node<T> nodeFrom = findClosestGreaterEqual(from, isIncludedFrom);
			res = nodeFrom == null ? res : getSetFromNodeToObject(res, nodeFrom, to, isIncludedTo);
		}
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

	public void rotatedTreeDisplay() {
		rotatedDisplay(root, 0);

	}

	public int height() {
		return height(root);
	}

	private int height(Node<T> root) {
		int res = 0;
		if (root != null) {
			int heightRight = height(root.right);// height of the right subtree
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

	private void rotatedDisplay(Node<T> root, int level) {
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

	private void printOffset(int level) {
		int limit = level * SPACES_PER_LEVEL;
		for (int i = 0; i < limit; i++) {
			System.out.print(" ");
		}

	}

	public ArrayList<ArrayList<T>> getObjectsByLevels() {
		ArrayList<ArrayList<T>> res = new ArrayList<>();
		fillLevelsArray(root, res, 0);
		return res;
	}

	private void fillLevelsArray(Node<T> root, ArrayList<ArrayList<T>> levelsArray, int level) {
		if (root != null) {
			// action with root of tree
			if (level == levelsArray.size()) // check if already there is array for level's elements
			{
				// for first function call on the level
				levelsArray.add(new ArrayList<>());
			}
			ArrayList<T> objectsLevel = levelsArray.get(level);
			objectsLevel.add(root.obj);
			fillLevelsArray(root.left, levelsArray, level + 1);
			fillLevelsArray(root.right, levelsArray, level + 1);
		}

	}

	int seqNumber;

	public TreePresentation<T> getTreePresentation() {
		TreePresentation<T> res = new TreePresentation<>();
		ArrayList<ArrayList<TreePresentation.Node<T>>> levels = new ArrayList<>();
		// levels.get(i) - array of presentation nodes at level i
		// levels - array of arrays of presentation nodes
		seqNumber = 0;
		int nLevels = height();
		for (int i = 0; i < nLevels; i++) {
			levels.add(new ArrayList<>());
		}
		fillLevelsPresentation(root, 0, levels);
		res.levelsNodes = levels;

		return res;
	}

	private void fillLevelsPresentation(Node<T> root, int level,
			ArrayList<ArrayList<TreePresentation.Node<T>>> levels) {
		if (root != null) {
			fillLevelsPresentation(root.left, level + 1, levels);
			TreePresentation.Node<T> node = new TreePresentation.Node<>();
			node.obj = root.obj;
			node.seqNumber = seqNumber++;
			levels.get(level).add(node);
			fillLevelsPresentation(root.right, level + 1, levels);
		}

	}

	
	
	
	
	/*
	 * ************************************** balance tree
	 * 
	 */
	
	
	
	public void balance() {
		Node<T>[] arrayNodes = new Node[size];
		fillArrayNodes(arrayNodes, root); //fills array of the nodes
		root = balance(arrayNodes, 0, size - 1, null);//0 – left index, size – 1 – right index;  null – parent for new root
	}

	
	private Node<T> balance(Node<T>[] arrayNodes, int i, int j, Node<T> root) {
		Node<T> newRoot = null;
		int leftIndex = i;
		int rightIndex = j;
		
		if ( leftIndex > rightIndex) {
			return null;
		}
		
		// найдем средний индекс по данным которые получили и по формуле
		int indexRoot = (leftIndex + rightIndex) / 2;
		
		// 2 Finding root node
		newRoot = arrayNodes[indexRoot];

		// пока хз зачем это - беру из описания выше что: null – parent for new root
		// вроде это не использую
		newRoot.parent = root;
		
		// 3 Root.left = balancing of left part; left part -> same left index and right is root index - 1
		newRoot.left = balance(arrayNodes, leftIndex, rightIndex-1, newRoot);
		
		//4 Root.right = balancing of right part; right part -> left index is the root index + 1 and same right index 
		newRoot.right = balance(arrayNodes, leftIndex, rightIndex + 1, newRoot);

		
		return root;
	}

	
	private void fillArrayNodes(Node<T>[] arrayNodes, Node<T> root) {
		
		// сюда мы риходим с balance()
		// запускаемся раньше чем рут может стать нулом
		if (root != null) {
			int mid = arrayNodes.length / 2;
			arrayNodes[mid] = root;
			for (int i = mid - 1; i == 0; i--) {
				arrayNodes[i] = root.left;
			}
			for (int i = mid + 1; i == arrayNodes.length; i++) {
				arrayNodes[i] = root.right;
			}
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//
//	public void balance() {
//		ArrayList<Node<T>> list = new ArrayList<>(size);
//		fillList(root, list);
//
//		root = balance(list, 0, size - 1, null);
//
//	}
//
//	private Node<T> balance(ArrayList<Node<T>> list, int indexFrom, int indexTo, Node<T> root) {
//		if (indexFrom > indexTo) {
//			return null;
//		}
//
//		int indexMiddle = (indexFrom + indexTo) / 2;
//
//		Node<T> node = list.get(indexMiddle);
//
//		node.parent = root;
//
//		node.left = balance(list, indexFrom, indexMiddle - 1, node);
//		node.right = balance(list, indexMiddle + 1, indexTo, node);
//
//		return node;
//	}
//
//	private void fillList(Node<T> node, ArrayList<Node<T>> list) {
//		if (node != null) {
//			fillList(node.left, list);
//			list.add(node);
//			fillList(node.right, list);
//		}
//	}
	
	
	
	
	
	
	

}
