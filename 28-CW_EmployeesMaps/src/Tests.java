import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Tests {	
	
	EmployeesServiceMapsImpl emplService = new EmployeesServiceMapsImpl();
	Employee e6 = new Employee(6, 1400, "Amazon", LocalDate.of(1993, 4, 14), "Max");
	Employee e7 = new Employee(7, 1500, "Amazon", LocalDate.of(1995, 4, 15), "Max");
	
	@BeforeEach
	void first() {
		
		Employee e1 = new Employee(1, 1000, "Nokia", LocalDate.of(1990, 1, 25), "Alex");
		Employee e2 = new Employee(2, 1200, "Microsoft", LocalDate.of(1991, 2, 12), "Maria");
		Employee e3 = new Employee(3, 1400, "Google", LocalDate.of(1992, 3, 13), "Andrey");
		Employee e4 = new Employee(4, 1400, "Amazon", LocalDate.of(1993, 4, 14), "Max");
		Employee e5 = new Employee(5, 1500, "Facebook", LocalDate.of(1994, 5, 15), "Victor");
		
		emplService.addEmployee(e1);
		emplService.addEmployee(e2);
		emplService.addEmployee(e3);
		emplService.addEmployee(e4);
		emplService.addEmployee(e5);
		
		emplService.addEmployee(e7);
		
							
	}

	
	@Test		//ok
	void test_addEmployee() {
		assertEquals(EmployeesReturnCodes.OK, emplService.addEmployee(e6));
	} 
	
	
	
	@Test
	void testaddEmployeeCompany() {
		fail("Not yet implemented");
	}
	
	
	@Test
	void testaddEmployeeAge() {
		fail("Not yet implemented");
	}
	
	@Test		//ok
	void test_removeEmployee() {
		assertEquals(EmployeesReturnCodes.OK, emplService.removeEmployee(7));
		
		assertEquals(null,  emplService.getEmployee(7));
	}
	
	@Test			//ok
	void test_getEmployee() {
		assertEquals(e7, emplService.getEmployee(7));
	}
	
	@Test
	void testgetEmployees() {
		fail("Not yet implemented");
	}
	
	@Test
	void testgetEmployeesCompany() {
		fail("Not yet implemented");
	}
	
	@Test
	void testgetEmployeesAges() {
		Collection<Employee> res = (Collection<Employee>) emplService.getEmployeesSalary(27, 30);
		assertEquals(3, res.size());
	}
	
	@Test
	void testgetEmployeesSalary() {
		Collection<Employee> res = (Collection<Employee>) emplService.getEmployeesSalary(1000, 1200);
		assertEquals(2, res.size());
	}
	
	@Test
	void testupdateCompany() {
		fail("Not yet implemented");
	}
	
	@Test
	void testupdateSalary() {
		fail("Not yet implemented");
	}

}
