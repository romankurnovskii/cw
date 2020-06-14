package telran.employees.service;

import java.util.List;
import java.util.Map;

import telran.employees.api.EmployeesService;
import telran.employees.dto.CompanySalary;
import telran.employees.dto.Employee;
import telran.employees.dto.EmployeesReturnCodes;
import telran.net.TcpClientJava;

public class EmployeesTcpProxy extends TcpClientJava implements EmployeesService {

	public EmployeesTcpProxy(String hostname, int port) {
		super(hostname, port);
		// TODO Auto-generated constructor stub
	}

	@Override
	public EmployeesReturnCodes addEmployee(Employee empl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeesReturnCodes removeEmployee(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getEmployee(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, List<Employee>> getEmployeesGroupedBySalary(int interval) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Employee> getEmployeesCompany(String company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Employee> getEmployeesAges(int ageFrom, int ageTo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Employee> getEmployeesSalary(int salaryFrom, int salaryTo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeesReturnCodes updateCompany(long id, String newCompany) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeesReturnCodes updateSalary(long id, int newSalary) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompanySalary> getCompaniesAvgSalary() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompanySalary> getCompaniesGreaterAvgSalary() {
		// TODO Auto-generated method stub
		return null;
	}

}
