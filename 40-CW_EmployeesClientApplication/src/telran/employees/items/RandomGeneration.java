package telran.employees.items;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import telran.employees.api.EmployeesService;
import telran.employees.dto.Employee;
import telran.employees.dto.EmployeesReturnCodes;
import telran.menu.InputOutput;

public class RandomGeneration extends EmployeesItem {
	private static int id = 1000;
	private static int minSalary;
	private static int maxSalary;
	private static int kolvoCompanies;
	private static int kolvoEmployees;
	
	private static LocalDate employeBirthdayYearFrom;
	private static LocalDate employeBirthdayYearTO;

	public RandomGeneration(EmployeesService employees, InputOutput inputOutput) {
		super(employees, inputOutput);
	}

	@Override
	public String displayName() {
		return "New generate";
	}

	@Override
	public void perform() {
		kolvoEmployees = inputOutput.inputInteger("Enter the number of employees");
		kolvoCompanies = inputOutput.inputInteger("Enter the number of companies");
		
		minSalary = inputOutput.inputInteger("Enter min salary");
		maxSalary = inputOutput.inputInteger("Enter max salary");
		
		employeBirthdayYearFrom = inputOutput.inputDate("Enter from birthday");
		employeBirthdayYearTO = inputOutput.inputDate("Enter to birthday");
		
//		generateEmployees();
	}

	/*
	 * сделать новых
	 */
	


}
