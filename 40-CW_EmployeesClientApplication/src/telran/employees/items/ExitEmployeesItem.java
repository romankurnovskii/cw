package telran.employees.items;

import java.io.Closeable;
import java.io.IOException;

import telran.employees.api.EmployeesService;
import telran.menu.InputOutput;

public class ExitEmployeesItem extends EmployeesItem {

	public ExitEmployeesItem(EmployeesService employees, InputOutput inputOut) {
		super(employees, inputOut);
	
	}

	@Override
	public String displayName() {
		
		return "Exit";
	}

	@Override
	public void perform() {
		if (employees instanceof Closeable) {
			try {
				((Closeable)employees).close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		

	}
	@Override
	public boolean isExit() {
		return true;
	}

}
