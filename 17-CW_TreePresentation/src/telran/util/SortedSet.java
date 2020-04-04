package telran.util;

public interface SortedSet<T> extends Set<T>{
T getMin();//minimal element
T getMax();//maximal element
SortedSet<T> subset(T from, boolean isIncludedFrom, T to,
		boolean isIncludedTo);//range either open or close range
default SortedSet<T> head(T key, boolean isIncluded) {
	//all elements that either strong less or less/equal than key
	return subset(getMin(), true, key, isIncluded);
}
default SortedSet<T> tail(T key, boolean isIncluded){
	//all elements that either strong greater or greater/equal than key
	return subset(key, isIncluded, getMax(), true);
}
}
