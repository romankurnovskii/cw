package telran.util;

import java.util.Comparator;
import java.util.function.Predicate;

public interface IndexedList<T> extends Iterable<T>{


void add(T obj);  //T теперь параметризован потмоучто в 3 строчке мы его описали в <>
boolean add(int ind, T obj); //добавляет объект T по индексу ind	
int binarySearch(T pattern); //искать по образцу и подобию, здесь предполагаем что массив отсортрвоан по компэрэбл
int binarySearch(T pattern,Comparator<T> comp); //если множетсво отсортирвоано в натуральном порядке(если порядок 
					// задано разрабочтиком Т, предполагаем что остортироваон по компаратор
IndexedList<T> filter(Predicate<T> predicate);
T get (int ind);
int indexOf(Object pattern);
int lastIndexOf(Object pattern);
Object remove(int ind);  //удалить по индексу
Object remove(Object pattern); //удалить по объекту
boolean removeIf(Predicate<T> predicate); //предикат говорит: котрттошка меньще 1см. значит удлаяем все картошки котоыре меньше 1см
Object set (int ind, T newObj);
int size();
void sort();
void sort(Comparator<T> comp);






	
	



}





