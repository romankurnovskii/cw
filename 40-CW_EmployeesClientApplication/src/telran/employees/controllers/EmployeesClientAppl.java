package telran.employees.controllers;

import telran.employees.api.EmployeesService;
import telran.employees.items.AddEmployee;
import telran.employees.items.GetEmployeesGroupedBySalary;
import telran.employees.items.RandomGeneration;
import telran.employees.items.RemoveEmployees;
import telran.employees.items.UpdateCompany;
import telran.employees.items.UpdateSalary;
import telran.employees.items.ExitEmployee;
import telran.employees.items.GetCompaniesAvgSalary;
import telran.employees.items.GetCompaniesGreaterAvgSalary;
import telran.employees.items.GetEmployee;
import telran.employees.items.GetEmployeesAges;
import telran.employees.service.EmployeesMaps;
import telran.menu.*;

public class EmployeesClientAppl {
	static InputOutput inputOutput = new ConsoleInputOutput();
	static EmployeesService employees = new EmployeesMaps();

	public static void main(String[] args) {
		Item[] items = { 
				new AddEmployee(employees, inputOutput),
				new GetEmployeesGroupedBySalary(employees, inputOutput), 
				new ExitEmployee(employees, inputOutput),
				new RandomGeneration(employees, inputOutput), 
				new RemoveEmployees(employees, inputOutput), 
				new GetEmployee(employees, inputOutput),
				new GetEmployeesAges(employees, inputOutput),
				new UpdateCompany(employees, inputOutput),
				new UpdateSalary(employees, inputOutput), 
				new GetCompaniesAvgSalary(employees, inputOutput),
				new GetCompaniesGreaterAvgSalary(employees, inputOutput)
				};
		Menu menu = new Menu(items, inputOutput);
		menu.menuRun();

	}

}
