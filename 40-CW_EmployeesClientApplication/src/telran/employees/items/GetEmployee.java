package telran.employees.items;

import telran.employees.api.EmployeesService;
import telran.employees.dto.Employee;
import telran.menu.InputOutput;

public class GetEmployee extends EmployeesItem {

	public GetEmployee(EmployeesService employees, InputOutput inputOutput) {
		super(employees, inputOutput);
	}

	@Override
	public String displayName() {
		return "Get Employee";
	}

	@Override
	public void perform() {
		long id = inputOutput.inputInteger("Enter employees id [1000-900000]");
		Employee employee = employees.getEmployee(id);
		if (employee != null) {
			inputOutput.displayLine(employee);
		} else {
			inputOutput.displayLine("Employee not found");
		}

	}

}