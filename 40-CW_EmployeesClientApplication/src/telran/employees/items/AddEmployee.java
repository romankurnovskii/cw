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
		long id = inputOutput.inputInteger("Enter employee's id  100000 to 999999", 100000, 999999);
		Employee employee = employees.getEmployee(id);
		if (employee != null) {
			inputOutput.displayLine("Employee already exiats");
			return;
		}

		int salary = inputOutput.inputInteger("Enter salary  1000-500000 ", 1000, 500000);

		String company = inputOutput.inputString("Enter company name");

		LocalDate birthday = inputOutput.inputDate("Enter birthday");
		String name = inputOutput.inputString("Enter name");

		Employee employee2 = new Employee(id, salary, company, birthday, name);
		
		EmployeesReturnCodes returnCodes = employees.addEmployee(employee2);
		
		inputOutput.displayLine(returnCodes);

	}

}
