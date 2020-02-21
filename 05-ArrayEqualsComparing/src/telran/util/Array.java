package telran.util;

import java.util.Arrays;
import java.util.Comparator;

public class Array {
private Object[] array;
private int size = 0;
public Array(int capacity) {
	array = new Object[capacity];
}
public Array() {
	this(16);
}
public void add(Object obj) {
	if (size == array.length) {
		allocateArray();
	}
	array[size++] = obj;
}
private void allocateArray() {
	array = Arrays.copyOf(array, array.length * 2);
	
}
public Object get(int index) {
	Object res = null;
	if (index >= 0 && index < size) {
		res = array[index];
	}
	return res;
}
public int size() {
	return size;
}
/**
 * adds an object at a specified index
 * @param index
 * @param obj
 * @return true if index value in the range [0 - size]
 *  (size included) otherwise false
 */
public boolean add(int index, Object obj) {
	boolean res = false;
	if (index >= 0 && index <= size) {
		if (size == array.length) {
			allocateArray();
		}
		res = true;
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = obj;
		size++;
	}
	return res;
}
/**
 * removes the object at a specified index
 * @param index
 * @return reference to the removed object or null in the case of
 * wrong index
 */
public Object remove(int index) {
	Object res = null;
	if (isIndexValid(index)) {
		res = array[index];
		if (index != size - 1) {
			//if size equals capacity index + 1 for the last element will cause error
			System.arraycopy(array, index + 1, array,
					index, size - index -1);
		}
		size--;
		array[size] = null; //candidate to be collected as a garbage
		
	}
	return res;
}
/**
 * sets new object at a specified index
 * @param index
 * @param obj
 * @return old reference to the object or null in the case of
 * wrong index value
 */
public Object set(int index, Object obj) {
	Object res = null;
	if (isIndexValid(index)) {
		res = array[index];
		array[index] = obj;
	}
	return res;
}
private boolean isIndexValid(int index) {
	return index >= 0 && index < size;
}
public int indexOf(Object pattern) {
	int res = -1;
	if (pattern != null) {
		for (int i = 0; i < size; i++) {
			if (pattern.equals(array[i])) {
				res = i;
				break;
			}
		}
	}
	return res;
	
}
public int lastIndexOf(Object pattern) {
	int res = -1;
	if (pattern != null) {
		for (int i = size - 1; i >= 0; i--) {
			if (pattern.equals(array[i])) {
				res = i;
				break;
			}
		}
	}
	return res;
	
}
public int binarySearch(Object pattern, Comparator<Object> comp) {
	int left = 0; 
	int right = size - 1;
	int middle = (left + right) / 2;
	while (left <= right && !pattern.equals(array[middle])) {
		if (comp.compare(pattern, array[middle]) < 0) {
			right = middle - 1;
		} else {
			left = middle + 1;
		}
		middle = (left + right) / 2;
	}
	return left > right ? -(left + 1) : middle;
}
/**
 * the same binary search but based on Comparable<Object>
 *  rather than Comparator<Object>
 * @param pattern
 * @return
 */
public int binarySearch(Object pattern) {
	//TODO
	return -1;
}
/**
 * sorting based on Comparator<Object>
 * in the meantime the simple bubble sort that we did at class
 * may be applied O[N^2]
 * @param comp
 */
public void sort(Comparator<Object> comp) {
	//TODO
	
}
/**
 * sorting based on Comparable<Object>
 */
public void sort() {
	//TODO
}





















}
