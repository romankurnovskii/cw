package telran.net;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import telran.employees.api.EmployeesService;
import telran.employees.dto.CompanySalary;
import telran.employees.dto.Employee;
import telran.employees.dto.EmployeesReturnCodes;
import telran.employees.dto.RequestData;
import telran.net.server.ProtocolJava;

public class EmployeesTcpProtocol implements ProtocolJava {

	private EmployeesService employees;

	private Map<String, Function<Serializable, ResponseJava>> mapFunctions;

	public EmployeesTcpProtocol(EmployeesService employees) {
		this.employees = employees;
		fillMapFunctions();
	}

	@Override
	public ResponseJava getResponse(RequestJava request) {
		
		Function<Serializable, ResponseJava> fn = mapFunctions.getOrDefault(request.requestType, this::wrongRequest);

		return fn.apply(request.requestData);
	}
	
	
	
	
	private void fillMapFunctions() {
		mapFunctions = new HashMap<>();

		mapFunctions.put("ADD_EMPLOYEE", this::addEmployee);
		mapFunctions.put(REMOVE_EMPLOYEE, this::removeEmployee);
		mapFunctions.put(GET_EMPLOYEE, this::getEmployee);
		mapFunctions.put(GET_EMPLOYEES, this::getEmployees);
		mapFunctions.put(GET_EMPLOYEES_AGES, this::getEmployeesAges);
		mapFunctions.put(GET_EMPLOYEES_COMPANY, this::getEmployeesCompany);
		mapFunctions.put(GET_EMPLOYEES_SALARY, this::getEmployeesSalary);
		mapFunctions.put(UPDATE_COMPANY, this::updateCompany);
		mapFunctions.put(UPDATE_SALARY, this::updateSalary);
		mapFunctions.put(GET_EMPLOYEES_GROUPER_BY_SALARY, this::getEmployeesGroupedBySalary);
		mapFunctions.put(GET_COMPANIES_AVG_SALARY, this::getCompaniesAvgSalary);
		mapFunctions.put(GET_COMPANIES_GREATER_AVG_SALARY, this::getCompaniesGreaterAvgSalary);
	}



	private ResponseJava addEmployee(Serializable requestData) {
		try {
			EmployeesReturnCodes res = employees.addEmployee((Employee) requestData);
			return new ResponseJava(TcpResponseCode.OK, res);
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
		}
	}

	private ResponseJava removeEmployee(Serializable requestData) {
		try {
			EmployeesReturnCodes res = employees.removeEmployee((long) requestData);
			return new ResponseJava(TcpResponseCode.OK, res);
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
		}
	}

	private ResponseJava getEmployee(Serializable requestData) {
		try {
			Employee res = employees.getEmployee((long) requestData);
			return new ResponseJava(TcpResponseCode.OK, (Serializable) res);
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
		}
	}

	private ResponseJava getEmployees(Serializable requestData) {
		try {
			Iterable<Employee> res = employees.getEmployees();
			return new ResponseJava(TcpResponseCode.OK, (Serializable) res);
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
		}
	}

	private ResponseJava getEmployeesAges(Serializable requestData) {
		try {
			RequestData data = (RequestData) requestData;
			Iterable<Employee> res = employees.getEmployeesAges((int) data.obj1, (int) data.obj2);
			return new ResponseJava(TcpResponseCode.OK, (Serializable) res);
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
		}
	}

	private ResponseJava getEmployeesCompany(Serializable requestData) {
		try {
			Iterable<Employee> res = employees.getEmployeesCompany((String) requestData);
			return new ResponseJava(TcpResponseCode.OK, (Serializable) res);
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
		}
	}

	private ResponseJava updateSalary(Serializable requestData) {
		try {
			RequestData data = (RequestData) requestData;
			EmployeesReturnCodes res = employees.updateSalary((long) data.obj1, (int) data.obj2);
			return new ResponseJava(TcpResponseCode.OK, res);
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
		}
	}

	private ResponseJava updateCompany(Serializable requestData) {
		try {
			RequestData data = (RequestData) requestData;
			EmployeesReturnCodes res = employees.updateCompany((long) data.obj1, (String) data.obj2);
			return new ResponseJava(TcpResponseCode.OK, res);
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
		}
	}

	private ResponseJava getEmployeesSalary(Serializable requestData) {
		try {
			RequestData data = (RequestData) requestData;
			Iterable<Employee> res = employees.getEmployeesSalary((int) data.obj1, (int) data.obj2);
			return new ResponseJava(TcpResponseCode.OK, (Serializable) res);
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
		}
	}

	private ResponseJava getEmployeesGroupedBySalary(Serializable requestData) {
		try {
			Map<String, List<Employee>> res = employees.getEmployeesGroupedBySalary((Integer) requestData);
			return new ResponseJava(TcpResponseCode.OK, (Serializable) res);
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
		}
	}

	private ResponseJava getCompaniesAvgSalary(Serializable serializable) {
		try {
			List<CompanySalary> res = employees.getCompaniesAvgSalary();
			return new ResponseJava(TcpResponseCode.OK, (Serializable) res);
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
		}
	}

	private ResponseJava getCompaniesGreaterAvgSalary(Serializable serializable) {
		try {
			List<CompanySalary> res = employees.getCompaniesGreaterAvgSalary();
			return new ResponseJava(TcpResponseCode.OK, (Serializable) res);
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
		}
	}

	private ResponseJava wrongRequest(Serializable requestData) {
		return new ResponseJava(TcpResponseCode.WRONG_REQUEST, "Type of request not found");
	}
}
