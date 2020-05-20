package telran.tests;

import java.util.Comparator;

import telran.employees.dto.Employee;

public class ComparatorEmployees implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		
		return Long.compare(o1.getId(), o2.getId());
	}

}
