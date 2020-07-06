package telran.employees.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import telran.employees.dto.Employee;
import telran.employees.net.EmployeesOperationsProtocol;
import telran.employees.service.EmployeesServiceMapsImpl;
import telran.net.server.ProtocolJava;
import telran.net.server.ServerJava;

public class EmployeesServerAppl {
private static final int PORT = 4040;

	
	public static void main(String[] args) {
		HashMap<Long, Employee> employeesMap = new HashMap<>();
		HashMap<String, List<Employee>> employeesCompany = new HashMap<>();
		TreeMap<Integer, List<Employee>> employeesAge = new TreeMap<>();
     	TreeMap<Integer, List<Employee>> employeesSalary = new TreeMap<>();
		
		EmployeesServiceMapsImpl employees = new EmployeesServiceMapsImpl(employeesMap, employeesCompany, employeesAge, employeesSalary);
		
		ProtocolJava protocol = new EmployeesOperationsProtocol(employees);
		ServerJava server = new ServerJava(protocol , PORT);
//		System.out.println("Server is listening on port " + PORT);
		server.run();
	}
}
