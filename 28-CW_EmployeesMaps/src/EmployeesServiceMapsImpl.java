import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeesServiceMapsImpl implements EmployeesService {

	public EmployeesServiceMapsImpl() {
		super();
		this.employees = new HashMap<>();
		this.employeesCompany = new HashMap<>();
		this.employeesAge = new TreeMap<>();
		this.employeesSalary = new TreeMap<>();
	}

	private HashMap<Long, Employee> employees;

//key-company, value-list of employees working for that company
	private HashMap<String, List<Employee>> employeesCompany;

//key - age, value - list of employees with that age
	private TreeMap<Integer, List<Employee>> employeesAge;

//key - salary, value - list of employees with that salary
	private TreeMap<Integer, List<Employee>> employeesSalary;

	
	
	/*
	 *  ****** ADDS
	 */
	
	@Override
	public EmployeesReturnCodes addEmployee(Employee empl) {
		Employee res = employees.putIfAbsent(empl.getId(), empl);

		employees.putIfAbsent(empl.getId(), empl);

		if (res != null) {
			return EmployeesReturnCodes.EMPLOYEE_ALREADY_EXISTS;
		}

		addEmployeeCompany(empl);
		addEmployeeAge(empl);
		addEmployeeSalary(empl);

		return EmployeesReturnCodes.OK;
	}

	private void addEmployeeCompany(Employee empl) {
		String company = empl.getCompany();
		List<Employee> listEmployeesCompany = employeesCompany.getOrDefault(company, new ArrayList<>());
		listEmployeesCompany.add(empl);
		employeesCompany.putIfAbsent(company, listEmployeesCompany);

	}

	private void addEmployeeAge(Employee empl) {
		int age = LocalDate.now().getYear() - empl.getBirthYearDate().getYear();
		List<Employee> listEmployeesAge = employeesAge.getOrDefault(age, new ArrayList<>());
		employeesAge.putIfAbsent(age, listEmployeesAge);

	}

	private void addEmployeeSalary(Employee empl) {
		int salary = empl.getSalary();
		List<Employee> listEmployeesSalary = employeesSalary.getOrDefault(salary, new ArrayList<>());
		employeesSalary.putIfAbsent(salary, listEmployeesSalary);

	}

	
	/*
	 *  ****** REMOVES
	 */
	
	
	@Override
	public EmployeesReturnCodes removeEmployee(long id) {
		if (employees.containsKey(id)) {
			employees.remove(id);
			return EmployeesReturnCodes.OK;
		}
		else {
			return EmployeesReturnCodes.EMPLOYEE_REMOVE_FAILED;
		}
		

		
	}

	
	/*
	 *  ****** GETS
	 */
	
	
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

		return (Iterable<Employee>) employeesAge.subMap(ageFrom, ageTo);
	}

	@Override
	public Iterable<Employee> getEmployeesSalary(int salaryFrom, int SalaryTo) {

		return new Iterable<Employee>() {

			@SuppressWarnings("unchecked")
			public Iterator<Employee> iterator() {
				return (Iterator<Employee>) employeesSalary.entrySet().stream()
						.filter(empl -> empl.getKey() >= salaryFrom && empl.getKey() <= SalaryTo)
						.collect(Collectors.toMap(empl -> empl.getKey(), empl -> empl.getValue()));
			}
		};
	}

	
	/*
	 *  ****** UPDATES
	 */
	
	@Override
	public EmployeesReturnCodes updateCompany(long id, String company) {
//TODO
		return getEmployee(id).getCompany() == company ? EmployeesReturnCodes.SAME_COMPANY : EmployeesReturnCodes.OK;

	}

	@Override
	public EmployeesReturnCodes updateSalary(long id, int newSalary) {
//TODO
		return getEmployee(id).getSalary() == newSalary ? EmployeesReturnCodes.SAME_SALARY : EmployeesReturnCodes.OK;
	}

}
