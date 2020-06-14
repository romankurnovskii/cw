package telran.employees.items;

import telran.employees.api.EmployeesService;
import telran.menu.InputOutput;

public class UpdateSalary extends EmployeesItem {

	public UpdateSalary(EmployeesService employees, InputOutput inputOutput) {
		super(employees, inputOutput);
	}

	@Override
	public String displayName() {
		return "Update Salary";
	}

	@Override
	public void perform() {
		long id = inputOutput.inputInteger("Enter id employee");
		
		inputOutput.displayLine("employee salary: " + employees.getEmployee(id).getSalary());

		int newSalary = inputOutput.inputInteger("Enter new salary");

		inputOutput.displayLine(employees.updateSalary(id, newSalary));
	}
}
