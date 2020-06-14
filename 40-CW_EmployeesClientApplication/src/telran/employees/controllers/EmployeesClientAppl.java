package telran.employees.controllers;

import telran.employees.api.EmployeesService;
import telran.employees.items.AddEmployee;
import telran.employees.items.DisplayEmployeesSalary;
import telran.employees.items.ExitEmployeesItem;
import telran.employees.service.EmployeesServiceMapsImpl;
import telran.menu.*;

public class EmployeesClientAppl {
static InputOutput inputOutput = new ConsoleInputOutput();
static EmployeesService employees = new EmployeesServiceMapsImpl();
	public static void main(String[] args) {
		Item[] items = {
				new AddEmployee(employees, inputOutput),
				new DisplayEmployeesSalary(employees, inputOutput),
				new ExitEmployeesItem(employees, inputOutput)
		};
		Menu menu = new Menu(items, inputOutput);
		menu.menuRun();

	}

}
