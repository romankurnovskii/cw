package telran.util;

import java.util.function.Predicate;

public interface Set<T> extends Iterable<T>{

boolean add(T obj);  //T теперь параметризован потмоучто в 3 строчке мы его описали в <>
					// задано разрабочтиком Т, предполагаем что остортироваон по компаратор
Set<T> filter(Predicate<T> predicate);


Object remove(Object pattern); //удалить по объекту
boolean removeIf(Predicate<T> predicate); //предикат говорит: котрттошка меньще 1см. значит удлаяем все картошки котоыре меньше 1см
int size();
boolean contains(T pattern);
}
