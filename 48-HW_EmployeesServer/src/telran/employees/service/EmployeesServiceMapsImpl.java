package telran.employees.service;

import telran.employees.api.EmployeesService;
import telran.employees.dto.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
	
	// Part for the synchronization
	static private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private Lock readLock = lock.readLock();
	private Lock writeLock = lock.writeLock();

	public EmployeesServiceMapsImpl(HashMap<Long, Employee> employees, HashMap<String, List<Employee>> employeesCompany,
			TreeMap<Integer, List<Employee>> employeesAge, TreeMap<Integer, List<Employee>> employeesSalary) {
		super();
		this.employees = employees;
		this.employeesCompany = employeesCompany;
		this.employeesAge = employeesAge;
		this.employeesSalary = employeesSalary;
	}

	@Override
	public EmployeesReturnCodes addEmployee(Employee empl) {
		try {
			writeLock.lock();
			
			Employee res = employees.putIfAbsent(empl.getId(), empl);
			if (res != null) {
				return EmployeesReturnCodes.EMPLOYEE_ALREADY_EXISTS;
			}
			addEmployeeSalary(empl);
			addEmployeeAge(empl);
			addEmployeeCompany(empl);
			return EmployeesReturnCodes.OK;
		} finally {
			writeLock.unlock();
		}
	}

	private void addEmployeeCompany(Employee empl) {
		try {
			writeLock.lock();
			
			String company = empl.getCompany();
			List<Employee> listEmployeesCompany = employeesCompany.getOrDefault(company, new ArrayList<>());
			listEmployeesCompany.add(empl);
			employeesCompany.putIfAbsent(company, listEmployeesCompany);
		} finally {
			writeLock.unlock();
		}

	}

	private void addEmployeeAge(Employee empl) {
		try {
			writeLock.lock();
			
			int age = getAge(empl.getBirthYear());
			List<Employee> listEmployeesAge = employeesAge.getOrDefault(age, new ArrayList<>());
			listEmployeesAge.add(empl);
			employeesAge.putIfAbsent(age, listEmployeesAge);
		} finally {
			writeLock.unlock();
		}

	}

	private int getAge(LocalDate birthDate) {
		try {
			readLock.lock();
			return (int) ChronoUnit.YEARS.between(birthDate, LocalDate.now());
		}finally {
			readLock.unlock();
		}
	}

	private void addEmployeeSalary(Employee empl) {
		try {
			writeLock.lock();
			
			int salary = empl.getSalary();
			List<Employee> listEmployeesSalary = employeesSalary.getOrDefault(salary, new ArrayList<>());
			listEmployeesSalary.add(empl);
			employeesSalary.putIfAbsent(salary, listEmployeesSalary);
		} finally {
			writeLock.unlock();
		}

	}

	@Override
	public EmployeesReturnCodes removeEmployee(long id) {
		
		try {
			writeLock.lock();
			
			Employee empl = employees.remove(id);
			if (empl == null) {
				return EmployeesReturnCodes.EMPLOYEE_NOT_FOUND;
			}
			removeFromIndexMaps(empl);
			return EmployeesReturnCodes.OK;
		} finally {
			writeLock.unlock();
		}
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
		try {
			readLock.lock();
			return employees.get(id);
		}finally {
			readLock.unlock();
		}
		
	}

	@Override
	public Iterable<Employee> getEmployees() {
		try {
			readLock.lock();
			return employees.values();
		}finally {
			readLock.unlock();
		}
		
	}

	@Override
	public Iterable<Employee> getEmployeesCompany(String company) {
		try {
			readLock.lock();
			return employeesCompany.getOrDefault(company, new ArrayList<>());
		}finally {
			readLock.unlock();
		}
	}

	@Override
	public Iterable<Employee> getEmployeesAges(int ageFrom, int ageTo) {
		try {
			readLock.lock();

			Collection<List<Employee>> listsEmployees = employeesAge.subMap(ageFrom, true, ageTo, true).values();
			return toListEmployees(listsEmployees);
		}finally {
			readLock.unlock();
		}
	}

	private Iterable<Employee> toListEmployees(Collection<List<Employee>> listsEmployees) {
		List<Employee> res = listsEmployees.stream().flatMap(List::stream).collect(Collectors.toList());
		return res;
	}

	@Override
	public Iterable<Employee> getEmployeesSalary(int salaryFrom, int salaryTo) {
		try {
			readLock.lock();
			
			Collection<List<Employee>> listsEmployees = employeesSalary.subMap(salaryFrom, true, salaryTo, true).values();
			return toListEmployees(listsEmployees);

		}finally {
			readLock.unlock();
		}
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

		try {
			writeLock.lock();
			Employee empl = getBeingUpdated(id);
			if (empl == null) {
				return EmployeesReturnCodes.EMPLOYEE_NOT_FOUND;
			}
			addEmployee(new Employee(id, empl.getSalary(), newCompany, empl.getBirthYear(), empl.getName()));
			return EmployeesReturnCodes.OK;
		} finally {
			writeLock.unlock();
		}
	}

	@Override
	public EmployeesReturnCodes updateSalary(long id, int newSalary) {
		try {
			writeLock.lock();
			Employee empl = getBeingUpdated(id);
			if (empl == null) {
				return EmployeesReturnCodes.EMPLOYEE_NOT_FOUND;
			}
			addEmployee(new Employee(id, newSalary, empl.getCompany(), empl.getBirthYear(), empl.getName()));
			return EmployeesReturnCodes.OK;
		} finally {
			writeLock.unlock();
		}
	}

	@Override
	public Map<String, List<Employee>> getEmployeesGroupedBySalary(int interval) {
		try {
			readLock.lock();

			Map<String, List<Employee>> res = employees.values().stream().collect(Collectors.groupingBy(e -> {
						int minIntervalValue = e.getSalary() / interval * interval;
						return String.format("%d - %d", minIntervalValue, minIntervalValue + interval);
					}));
			return res;
		}finally {
			readLock.unlock();
		}
		
	}
	/******************* Old Solution ************************/
//	@Override
//	public List<CompanySalary> getCompaniesAvgSalary() {
//		return getStreamAvgEntries().collect(Collectors.toList());
//	}
//
//	@Override
//	public List<CompanySalary> getCompaniesGreaterAvgSalary() {
//		double avgSalary = getAvgSalary();
//		return getStreamAvgEntries()
//				.filter(cs -> cs.getAvgSalary() > avgSalary)
//				.collect(Collectors.toList());
//	}
//
//	private double getAvgSalary() {
//		return employees.values()
//				.stream()
//				.collect(Collectors.averagingInt(Employee::getSalary));;
//	}
//	
//	private Stream<CompanySalary> getStreamAvgEntries(){
//		Map<String, Double> companiesSalary = employees.values()
//				.stream().collect(Collectors.groupingBy(Employee::getCompany, 
//						Collectors.averagingInt(Employee::getSalary)));
//	return companiesSalary.entrySet()
//			.stream()
//			.map(e -> new CompanySalary(e.getKey(), e.getValue()));
//	}

	/********* New Design ******/
	@Override
	public List<CompanySalary> getCompaniesAvgSalary() {
		try {
			readLock.lock();

			return getStreamCompanySalary().collect(Collectors.toList());
		}finally {
			readLock.unlock();
		}
	}

	private Stream<CompanySalary> getStreamCompanySalary() {
		try {
			readLock.lock();

			Map<String, Double> companiesSalary = employees.values().stream()
					.collect(Collectors.groupingBy(Employee::getCompany, Collectors.averagingInt(Employee::getSalary)));
			
			return companiesSalary.entrySet().stream().map(e -> new CompanySalary(e.getKey(), e.getValue()));
		}finally {
			readLock.unlock();
		}
	}

	@Override
	public List<CompanySalary> getCompaniesGreaterAvgSalary() {
		try {
			readLock.lock();

			double avgSalary = getAvgSalary();
			return getStreamCompanySalary().filter(cs -> cs.getAvgSalary() > avgSalary).collect(Collectors.toList());
		}finally {
			readLock.unlock();
		}
	}

	private Double getAvgSalary() {
		return employees.values().stream().collect(Collectors.averagingInt(Employee::getSalary));
	}
}
