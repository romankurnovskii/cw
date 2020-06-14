package telran.employees.items;

import telran.employees.api.EmployeesService;
import telran.employees.dto.EmployeesReturnCodes;
import telran.menu.InputOutput;

public class RemoveEmployees extends EmployeesItem {

	public RemoveEmployees(EmployeesService employees, InputOutput inputOutput) {
		super(employees, inputOutput);
	}

	@Override
	public String displayName() {
		return "Remove employee";
	}

	@Override
	public void perform() {
		long id = inputOutput.inputInteger("Enter id employee");

		EmployeesReturnCodes res = employees.removeEmployee(id);
		inputOutput.displayLine(res);
	}
}
