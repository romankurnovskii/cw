package telran;

/*
 * 1 Создаю класс Person 21 строка 
 * 2 для объекта который будет создавать в этом классе создаю 2 поля 23-24 строки
 * 3 создаю конструктор используя поля автоматом - 26 строка
 *   когда вызывается конструктор/класс - автоматом создается этот обьект
 * 4 Создаю автоматом сеттере и геттеры чтобы к ним обращатсья из других классов   36-48
 * 5 Создаю класс PersonMethods чтобы проводить манипуляции над Persons  
 * 10 implements Comparable<Person>
 * 11 создаю метод compareTo через консутрктор, чтобы сказать по какому полю он будет сортирвоатсья если попросят
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


public class Person implements Comparable<Person>{ //? в <> почему именно Person и что будет если укзать другой класс ?

	private String name;
	private int age;
	
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}


	@Override
	public int compareTo(Person person) { //person это атрибут типа Person
		// метод возвращает -1 0 и 1
		
		// создам условие по которому он будет возвращать
		int res=0;
		if (person.age == this.age) {  //  здесь 1-й со вторым сравнивается  
			res = 0;
		}
		if (person.age < this.age) {
			res = -1;
		}
		if (person.age > this.age) {
			res = 1;
		}
		return res;
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
}
