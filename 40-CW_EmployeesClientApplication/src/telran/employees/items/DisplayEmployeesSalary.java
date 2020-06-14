package telran.employees.items;

import telran.employees.api.EmployeesService;
import telran.menu.InputOutput;

public class DisplayEmployeesSalary extends EmployeesItem {

	public DisplayEmployeesSalary(EmployeesService employees, InputOutput inputOut) {
		super(employees, inputOut);
		
	}

	@Override
	public String displayName() {
		
		return "Display all employees with a given salary";
	}

	@Override
	public void perform() {
		int salaryFrom = inputOutput.inputInteger("Enter salary from");
		int salaryTo = inputOutput.inputInteger("Enter salary to", salaryFrom, Integer.MAX_VALUE);
		inputOutput.displayLine(employees.getEmployeesSalary(salaryFrom, salaryTo));

	}

}
