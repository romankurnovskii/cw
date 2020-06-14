package telran.employees.items;

import java.time.LocalDate;

import telran.employees.api.EmployeesService;
import telran.employees.dto.Employee;
import telran.employees.dto.EmployeesReturnCodes;
import telran.menu.InputOutput;

public class AddEmployee extends EmployeesItem {

	public AddEmployee(EmployeesService employees, InputOutput inputOut) {
		super(employees, inputOut);
		
	}

	@Override
	public String displayName() {
	
		return "Add Employee";
	}

	@Override
	public void perform() {
		long id = inputOutput.inputInteger("Enter employee's id [100000 - 999999]",100000, 999999 );
		Employee employee = employees.getEmployee(id);
		if (employee != null) {
			inputOutput.displayLine("Employee already exiats");
			return;
		}
		int salary = inputOutput.inputInteger("Enter salary [5000-50000]", 5000, 50000);
		String company = inputOutput.inputString("Enter company name");
		LocalDate birthDate = inputOutput.inputDate("Enter birthdate YYYY-MM-DD");
		String name = inputOutput.inputString("Enter name");
		Employee empl = new Employee(id, salary, company, birthDate, name);
		EmployeesReturnCodes res = employees.addEmployee(empl);
		inputOutput.displayLine(res);

	}

}
