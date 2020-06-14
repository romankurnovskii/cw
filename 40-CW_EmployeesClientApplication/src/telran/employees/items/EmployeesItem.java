package telran.employees.items;

import telran.employees.api.EmployeesService;
import telran.menu.InputOutput;
import telran.menu.Item;

public abstract class EmployeesItem implements Item {

	
	 EmployeesService employees;
	 InputOutput inputOutput;
	
	public EmployeesItem(EmployeesService employees, InputOutput inputOut) {
		super();
		this.employees = employees;
		this.inputOutput = inputOut;
	}

}



