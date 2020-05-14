

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
public class EmployeesServiceMapsImpl implements EmployeesService {
private HashMap<Long, Employee> employees = new HashMap<>();
//key-company, value-list of employees working for that company
private HashMap<String, List<Employee>> employeesCompany = new HashMap<>();
/**************************************************/
//key - age, value - list of employees with that age
private TreeMap<Integer, List<Employee>> employeesAge = new TreeMap<>();
/*****************************************************/
//key - salary, value - list of employees with that salary
private TreeMap<Integer, List<Employee>> employeesSalary = new TreeMap<>();
/******************************************************/

	@Override
	public EmployeesReturnCodes addEmployee(Employee empl) {
		Employee res = employees.putIfAbsent(empl.getId(), empl);
		if (res != null) {
			return EmployeesReturnCodes.EMPLOYEE_ALREADY_EXISTS;
		}
		addEmployeeSalary(empl);
		addEmployeeAge(empl);
		addEmployeeCompany(empl);
		return EmployeesReturnCodes.OK;
	}

	private void addEmployeeCompany(Employee empl) {
	String company = empl.getCompany();
	List<Employee> listEmployeesCompany =
			employeesCompany.getOrDefault(company, new ArrayList<>());
	listEmployeesCompany.add(empl);
	employeesCompany.putIfAbsent(company, listEmployeesCompany);
	
}

	private void addEmployeeAge(Employee empl) {
		int age = getAge(empl.getBirthYear());
		List<Employee> listEmployeesAge =
				employeesCompany.getOrDefault(age, new ArrayList<>());
		listEmployeesAge.add(empl);
		employeesAge.putIfAbsent(age, listEmployeesAge);
	
}

	private int getAge(LocalDate birthDate) {
		
		return (int) ChronoUnit.YEARS.between(birthDate, LocalDate.now());
	}

	private void addEmployeeSalary(Employee empl) {
		int salary = empl.getSalary();
		List<Employee> listEmployeesSalary =
				employeesSalary.getOrDefault(salary, new ArrayList<>());
		listEmployeesSalary.add(empl);
		employeesSalary.putIfAbsent(salary, listEmployeesSalary);
	
}

	@Override
	public EmployeesReturnCodes removeEmployee(long id) {
		Employee empl = employees.remove(id);
		if (empl == null) {
			return EmployeesReturnCodes.EMPLOYEE_NOT_FOUND;
		}
		removeFromIndexMaps(empl);
		
		
		return EmployeesReturnCodes.OK;
	}

	private void removeFromIndexMaps(Employee empl) {
		removeFromCompanies(empl);
		removeFromAgies(empl);
		removeFromSalaries(empl);
	}

	private void removeFromSalaries(Employee empl) {
		int salary = empl.getSalary();
		List<Employee> list = employeesSalary.get(salary);
		list.remove(empl);
		if (list.isEmpty()) {
			employeesSalary.remove(salary);
		}
		
	}

	private void removeFromAgies(Employee empl) {
		int age = getAge(empl.getBirthYear());
		List<Employee> list = employeesAge.get(age);
		list.remove(empl);
		if (list.isEmpty()) {
			employeesAge.remove(age);
		}
		
	}

	private void removeFromCompanies(Employee empl) {
		String company = empl.getCompany();
		List<Employee> list = employeesCompany.get(company);
		list.remove(empl);
		if (list.isEmpty()) {
			employeesCompany.remove(company);
		}
		
	}

	@Override
	public Employee getEmployee(long id) {
		
		return employees.get(id);
	}

	@Override
	public Iterable<Employee> getEmployees() {
		
		return employees.values();
	}

	@Override
	public Iterable<Employee> getEmployeesCompany(String company) {
		
		return employeesCompany.getOrDefault(company, new ArrayList<>());
	}

	@Override
	public Iterable<Employee> getEmployeesAges(int ageFrom, int ageTo) {
		Collection<List<Employee>> listsEmployees = 
				employeesAge.subMap(ageFrom, true, ageTo, true).values();
		return toListEmployees(listsEmployees);
	}

	private Iterable<Employee> toListEmployees(Collection<List<Employee>> listsEmployees) {
		List<Employee> res = new ArrayList<>();
		for(List<Employee> list: listsEmployees) {
			res.addAll(list);
		}
		return res;
	}

	@Override
	public Iterable<Employee> getEmployeesSalary(int salaryFrom, int salaryTo) {
		Collection<List<Employee>> listsEmployees = 
				employeesSalary.subMap(salaryFrom, true, salaryTo, true).values();
		return toListEmployees(listsEmployees);
	}
private Employee getBeingUpdated(long id) {
	Employee empl = employees.remove(id);
	if (empl != null) {
		removeFromIndexMaps(empl);
	}
	return empl;
}
	@Override
	public EmployeesReturnCodes updateCompany(long id, String newCompany) {
		 
		Employee empl = getBeingUpdated(id);
		if (empl == null) {
			return EmployeesReturnCodes.EMPLOYEE_NOT_FOUND;
		}
		addEmployee(new Employee(id, empl.getSalary(),
				newCompany, empl.getBirthYear(), empl.getName()));
		return EmployeesReturnCodes.OK;
	}

	@Override
	public EmployeesReturnCodes updateSalary(long id, int newSalary) {
		Employee empl = getBeingUpdated(id);
		if (empl == null) {
			return EmployeesReturnCodes.EMPLOYEE_NOT_FOUND;
		}
		addEmployee(new Employee(id, newSalary,
				empl.getCompany(), empl.getBirthYear(), empl.getName()));
		return EmployeesReturnCodes.OK;
	}

}
