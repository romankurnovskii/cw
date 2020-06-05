package telran.persons.controller;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.*;
import java.time.LocalDate;

import telran.persons.dto.Address;
import telran.persons.dto.Child;
import telran.persons.dto.Employee;
import telran.persons.dto.Person;

public class RandomPersonsCreationAppl {
private static final long N_PERSONS = 100;
private static final int EMPL_PROBOBILITY = 30;
private static final int MIN_CHILD_YEAR = 2014;
private static final int MAX_CHILD_YEAR = 2018;
private static final int N_GARTENS = 5;
private static final int MIN_EMPL_YEAR = 1952;
private static final int MAX_EMPL_YEAR = 2000;
private static final int N_COMPANIES = 3;
private static final int MIN_SALARY = 5000;
private static final int MAX_SALARY = 30000;
private static final int N_CITIES = 3;
private static Random gen=new Random();
public static void main(String[] args) throws Exception{
	List<Person> persons=getRandomPersons();
	try(ObjectOutputStream output=
			new ObjectOutputStream(new FileOutputStream("persons.data"));){
		output.writeObject(persons);
	}
}

private static List<Person> getRandomPersons() {
	return Stream.generate(RandomPersonsCreationAppl::getRandomPerson)
			.limit(N_PERSONS).collect(Collectors.toList());
}
private static Person getRandomPerson() {
	Person person=getRandomCommonPerson();
	return getChance()<=EMPL_PROBOBILITY?
			getRandomEmployee(person):getRandomChild(person);
}

private static Person getRandomChild(Person person) {
	
	LocalDate birthDate=getRandomDate
			(MIN_CHILD_YEAR,MAX_CHILD_YEAR);
	String garten="garten"+getRandomNumber(1,N_GARTENS);
	
	return new Child(person.getId(), person.getName(),
			person.getAddress(), birthDate, garten);
}

private static int getRandomNumber(int min, int max) {
	return gen.ints(1, min, max+1).findFirst().getAsInt();
}

private static LocalDate getRandomDate(int minYear, int maxYear) {
	
	int year=getRandomNumber(minYear, maxYear);
	int month=getRandomNumber(1,12);
	int dayOfMonth=getRandomNumber(1,28);
	return LocalDate.of(year, month, dayOfMonth);
}

private static Person getRandomEmployee(Person person) {
	LocalDate birthDate=getRandomDate(MIN_EMPL_YEAR, MAX_EMPL_YEAR);
	String company="company"+getRandomNumber(1, N_COMPANIES);
	int salary=getRandomNumber(MIN_SALARY, MAX_SALARY);
	String title=getRandomTitle();
	return new Employee(person.getId(), person.getName(),
			person.getAddress(), birthDate,
			company, salary, title);
}

private static String getRandomTitle() {
	String[] titles= {"WageEmployee","SalesPerson","SalesManager","Manager"};
	return titles[getRandomNumber(0,titles.length-1)];
}

private static int getChance() {
	
	return getRandomNumber(1,100);
}

private static Person getRandomCommonPerson() {
	int id=getRandomNumber(1000000,9999999);
	String name="name"+getRandomNumber(1,20);
	Address address=getRandomAddress();
	return new Person(id, name, address, null);
}

private static Address getRandomAddress() {
	
	String city="city"+getRandomNumber(1,N_CITIES);
	String street="street"+getRandomNumber(1,20);
	int building=getRandomNumber(1,30);
	int aprt=getRandomNumber(1,20);
	return new Address(city, street, building, aprt);
}
}
