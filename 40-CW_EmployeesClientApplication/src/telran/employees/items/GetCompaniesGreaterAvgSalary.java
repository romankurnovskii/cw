package telran.employees.items;

import telran.employees.api.EmployeesService;
import telran.menu.InputOutput;

public class GetCompaniesGreaterAvgSalary extends EmployeesItem {

	public GetCompaniesGreaterAvgSalary(EmployeesService employees, InputOutput inputOut) {
		super(employees, inputOut);
	}

	@Override
	public String displayName() {
		return "GetCompaniesGreaterAvgSalaryItem";
	}

	@Override
	public void perform() {
		inputOutput.displayLine(employees.getCompaniesGreaterAvgSalary());
	}
}
