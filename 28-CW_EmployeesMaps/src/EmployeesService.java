
public interface EmployeesService {
	
EmployeesReturnCodes addEmployee(Employee empl);
EmployeesReturnCodes removeEmployee(long id);

Employee getEmployee(long id);

Iterable<Employee> getEmployees();
Iterable<Employee> getEmployeesCompany(String company);
Iterable<Employee> getEmployeesAges(int ageFrom,int ageTo);
Iterable<Employee> getEmployeesSalary(int salaryFrom, int SalaryTo);

EmployeesReturnCodes updateCompany(long id, String company);
EmployeesReturnCodes updateSalary(long id, int newSalary);

}
