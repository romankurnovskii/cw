package telran.employees.items;

import telran.employees.api.EmployeesService;
import telran.menu.InputOutput;

public class UpdateCompany extends EmployeesItem {

	public UpdateCompany(EmployeesService employees, InputOutput inputOutput) {
		super(employees, inputOutput);
	}

	@Override
	public String displayName() {
		return "Update Company";
	}

	@Override
	public void perform() {
		long id = inputOutput.inputInteger("Enter id of employee");

		inputOutput.displayLine("employee company: " + employees.getEmployee(id).getCompany());

		String string = inputOutput.inputString("Enter new company");

		inputOutput.displayLine(employees.updateCompany(id, string));
	}
}
