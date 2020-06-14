package telran.employees.items;

import telran.employees.api.EmployeesService;
import telran.menu.InputOutput;

public class GetEmployeesAges extends EmployeesItem {

	public GetEmployeesAges(EmployeesService employees, InputOutput inputOut) {
		super(employees, inputOut);
	}

	@Override
	public String displayName() {
		return "Get ages";
	}

	@Override
	public void perform() {
		int from = inputOutput.inputInteger("Enter age from");
		int to = inputOutput.inputInteger("Enter age to");
		inputOutput.displayLine(employees.getEmployeesAges(from, to));
	}
}
