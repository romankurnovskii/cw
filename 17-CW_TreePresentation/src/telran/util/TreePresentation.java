package telran.util;

import java.util.ArrayList;

public class TreePresentation<T> {
public static class Node<T> {
	public int seqNumber;
	public T obj;
	@Override
	public String toString() {
		return String.format("seqNumber:%d ; obj: %s", seqNumber, obj);
	}
	
}
@Override
public String toString() {
	String res = "";
	for(ArrayList<Node<T>> list: levelsNodes) {
		res += list + "\n";
	}
	return res;
}

public ArrayList<ArrayList<Node<T>>> levelsNodes;
}
