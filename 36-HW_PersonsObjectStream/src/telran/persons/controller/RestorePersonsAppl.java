package telran.persons.controller;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import telran.persons.dto.*;
public class RestorePersonsAppl {

	@SuppressWarnings("unchecked")
	public static void main(String[] args)throws Exception {
		try(ObjectInputStream input=new ObjectInputStream
				(new FileInputStream("persons.data"))){
			List<Person> persons=(List<Person>) input.readObject();
			displayMostPopulatedCities(persons);
			displayCompaniesAvgSalaries(persons);
			displayEmployeesBigSalary(persons);
			
		}

	}
	private static void displayEmployeesBigSalary(List<Person> persons) {
		System.out.println("\nEmployees with salary greater than the overal avg. salary");
		double overallAvgSalary = getAvgSalary(persons);
		getStreamEmployees(persons).filter(e -> e.getSalary() > overallAvgSalary)
		.forEach(System.out::println);
		
	}
	private static double getAvgSalary(List<Person> persons) {
		
		return getStreamEmployees(persons).collect(Collectors.averagingInt(Employee::getSalary));
	}
	private static void displayCompaniesAvgSalaries(List<Person> persons) {
		System.out.println("\nCompany    AvgSalary");
		Map<String, Double> companyAvgSalary = 
				getStreamEmployees(persons)
				.collect(Collectors.groupingBy(Employee::getCompany,
						Collectors.averagingInt(Employee::getSalary)));
		companyAvgSalary.forEach((k, v) -> {
			System.out.printf("%s  %f\n", k, v);
		});
		
	}
	private static void displayMostPopulatedCities(List<Person> persons) {
		System.out.println("\nMost populated cities");
		Map<String, Long> cityCount = persons.stream()
				.collect(Collectors.groupingBy(p -> p.getAddress().getCity(),
						Collectors.counting()));
		Long max = Collections.max(cityCount.values());
		cityCount.forEach((k,v) -> {
			if (v == max) {
				System.out.println(k);
			}
		});
		
	}
	static Stream<Employee> getStreamEmployees(List<Person> persons) {
		return persons.stream().filter(p -> p instanceof Employee).map(p -> (Employee)p);
	}

}
