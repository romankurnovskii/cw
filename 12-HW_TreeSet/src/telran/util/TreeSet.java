package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

public class TreeSet<T> implements Set<T> {
	private static class Node<T> {
		T obj;
		Node<T> parent;
		Node<T> left; // reference to a less (relative to comparator)
		Node<T> right; // reference to a greater

		public Node(T obj) {
			this.obj = obj;
		}
	}

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
		Set<T> resSet = new TreeSet<T>();
		
	/* пойду с конца
	 * я должен получить новый сет
	 * пойду от рут
	 * если нода соответсвует предикату то оставляю
	 * если нет - то иду дальше - КУДА ИДУ?
	 * как мне вернуться обратну в голову той ветки откуда я пришел?
	 * 
	 * 
	 * или 2-й вариант
	 * копирую весь сет
	 * иду по всем элементам и удаляю элемент если он не равен моему предикату и сразу переставляю
	 * 
	 */

		return resSet;
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

	private Node<T> findNode(Object pattern) {
		Node<T> current = root; // start from root

		while (current != null) {

			int mycomp = comparator.compare((T) pattern, current.obj);

			if (mycomp == 0) { // нашли и выходим в ретерн
				break;
			}
			if (mycomp < 0) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		return current;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int initialSize = size;
		Iterator<T> it = iterator();
		while(it.hasNext()) {
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
			//removing root as non-junction node
			root = child;
		} 
		else if (parent.left == node) {
			parent.left = child;
		} 
		else parent.right = child;
		
		if (child != null) {
			child.parent = parent;
		}

	}

	private boolean isJunction(Node<T> node) {
		return node.left != null && node.right != null;
	}

	private class TreeIterator implements Iterator<T> {
		Node<T> current = getLeastNode(root);
		Node<T> useNode = null;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			T res = current.obj;
			current = current.right != null ? getLeastNode(current.right) : getParentFromLeft(current);
			return res;
		}

		@Override
		public void remove() {
			if (useNode != null) {
				removeNode(useNode);
				useNode = null;
			}
		}

	}

}





// итерация от меньшего к большему (можно использвоть компаратор)