package telran;

import java.util.function.Predicate;


/*  
 * 6 Создаю метод main чтобы создавать обьекты и засунуть в массив
 * 7 Создам тест PersonTests чтобы сделать сортировку
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */

public class PersonMethods {

	
	
	public static void main(String[] args) {
        System.out.println("Запуск класса PersonMethods!");
        
        // создам 3-х персанов , person 1,2,3 - это ссылки на сами объекты
        Person person1 = new Person("max", 21);  
        Person person2 = new Person("dima", 22);
        Person person3 = new Person("dani", 23);
                        
        Person[] persons = {
  			  new Person("alina", 25),
  			  new Person("masha", 26),
  			  new Person("dasha", 27),
  		};
        
        System.out.println(persons[1].getAge()); // распечатал поле объекта из массива persons
        
        System.out.println(person1.getAge()); // либо печатаю напрямую поле отдельного объекта
     
  
      
    }
	
	

	
	
	
	
	
	
	
	
	
	
}
