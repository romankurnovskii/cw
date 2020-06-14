package telran.employees.items;

import telran.employees.api.EmployeesService;
import telran.menu.InputOutput;

public class GetCompaniesAvgSalary extends EmployeesItem {

	InputOutput inputOutput;
	
	public GetCompaniesAvgSalary(EmployeesService employees, InputOutput inputOutput) {
		super(employees, inputOutput);
		this.inputOutput = inputOutput;
	}

	@Override
	public String displayName() {
		return "getCompaniesAvgSalary";
	}

	@Override
	public void perform() {
		inputOutput.displayLine(employees.getCompaniesAvgSalary());

	}

}
