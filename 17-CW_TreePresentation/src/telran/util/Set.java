package telran.util;

import java.util.Iterator;
import java.util.function.Predicate;

public interface Set<T> extends Iterable<T>{
boolean add(T obj);
Set<T> filter(Predicate<T> predicate);
Object remove(Object pattern);
default boolean removeIf(Predicate<T> predicate) {
	int initialSize = size();
	Iterator<T> it = iterator();
	while(it.hasNext()) {
		T obj = it.next();
		if (predicate.test(obj)) {
			it.remove();
		}
	}
	return initialSize > size();
};
int size();
boolean contains(T pattern);








}
