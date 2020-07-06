package telran.employees.net;

import static telran.employees.api.EmployeesOperationsApi.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import telran.employees.api.EmployeesService;
import telran.employees.dto.CompanySalary;
import telran.employees.dto.Employee;
import telran.employees.dto.EmployeesReturnCodes;
import telran.net.*;
import telran.net.server.ProtocolJava;

public class EmployeesOperationsProtocol implements ProtocolJava {

	EmployeesService employees;
	HashMap<String, Function<Serializable, ResponseJava>> mapFunctions;
	
	public EmployeesOperationsProtocol(EmployeesService employees) {
		this.employees = employees;
		fillMapFunctions();
		
	}

	private void fillMapFunctions() {
	mapFunctions = new HashMap<>();
//	mapFunctions.put(PROMPT, this::prompt);
//	mapFunctions.put(MOVE, this::move);
	mapFunctions.put(ADD, this::addEmployee);
	mapFunctions.put(GET_EMPLOYEE, this::getEmployee);
	mapFunctions.put(REMOVE_EMPLOYEE, this::removeEmployee);
	mapFunctions.put(GET_EMPLOYEES_SALARY, s -> getEmployeesSalary(s));
	
	mapFunctions.put(GET_COMPANIES_GREATER_AVG_SALARY, this::getCompaniesGreaterAvgSalary);
	mapFunctions.put(GET_COMPANIES_AVG_SALARY, this::getCompaniesAvgSalary);
	mapFunctions.put(GET_EMPLOYEE_GROUP_BY_SALARY, this::getEmployeeGroupBySalary);
	mapFunctions.put(GET_EMPLOYEES_AGES, s -> getEmployeesAges(s));
	
	mapFunctions.put(UPDATE_COMPANY, this::updateCompany);
	mapFunctions.put(UPDATE_SALARY, this::updateSalary);
	}
	
	@Override
	public ResponseJava getResponse(RequestJava request) {
		Function<Serializable, ResponseJava> fn =
				mapFunctions.getOrDefault(request.requestType, this::wrongRequest);
		return fn.apply(request.requestData);
	}
	
//	ResponseJava prompt (Serializable requestData) {
//		try {
//			
//						
//			EmployeesReturnCodes res = employees.prompt();
//			ResponseJava response = new ResponseJava(TcpResponseCode.OK, res);
//			return response;
//			
//		} catch (Exception e) {
//			return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
//		}
//	}
//	ResponseJava move (Serializable requestData) {
//		try {
//						
//			String res = employees.move((String) requestData);
//			ResponseJava response = new ResponseJava(TcpResponseCode.OK, res);
//			return response;
//			
//		} catch (Exception e) {
//			return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
//		}
//	}
	
	
	ResponseJava addEmployee (Serializable requestData) {
		try {
			
			EmployeesReturnCodes res = employees.addEmployee((Employee) requestData);
			ResponseJava response = new ResponseJava(TcpResponseCode.OK, res);
			return response;
			
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
		}
	}
	
	ResponseJava getEmployee (Serializable requestData) {
		try {
			
			Employee res = employees.getEmployee((long)requestData);
			ResponseJava response = new ResponseJava(TcpResponseCode.OK, (Serializable) res);
			return response;
			
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
		}
	}
	
	ResponseJava removeEmployee (Serializable requestData) {
		try {
			
			EmployeesReturnCodes res = employees.removeEmployee((long) requestData);
			ResponseJava response = new ResponseJava(TcpResponseCode.OK, (Serializable) res);
			return response;
			
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
		}
	}
	
	ResponseJava getEmployeesSalary (Serializable requestData) {
	try {
		List<Integer> requestListsalaryFrames = (List<Integer>) requestData;
		Iterable<Employee> res = employees.getEmployeesSalary(requestListsalaryFrames.get(0),requestListsalaryFrames.get(1));
		ResponseJava response = new ResponseJava(TcpResponseCode.OK, (Serializable) res);
		return response;
		
	} catch (Exception e) {
		return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
	}
}
	
	
	ResponseJava getCompaniesGreaterAvgSalary (Serializable requestData) {
	try {
		
		List<CompanySalary> res = employees.getCompaniesGreaterAvgSalary();
		ResponseJava response = new ResponseJava(TcpResponseCode.OK, (Serializable) res);
		return response;
		
	} catch (Exception e) {
		return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
	}
}

ResponseJava getEmployeesAges (Serializable requestData) {
	try {
		List<Integer> requestListAgesFrames = (List<Integer>) requestData;
		Iterable<Employee> res = employees.getEmployeesSalary(requestListAgesFrames.get(0),requestListAgesFrames.get(1));
		ResponseJava response = new ResponseJava(TcpResponseCode.OK, (Serializable) res);
		return response;
		
	} catch (Exception e) {
		return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
	}
}

ResponseJava getEmployeeGroupBySalary (Serializable requestData) {
	try {
		Map<String, List<Employee>> res = employees.getEmployeesGroupedBySalary((int)requestData);
		ResponseJava response = new ResponseJava(TcpResponseCode.OK, (Serializable) res);
		return response;
		
	} catch (Exception e) {
		return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
	}
}
ResponseJava getCompaniesAvgSalary (Serializable requestData) {
	try {		
		List<CompanySalary> res = employees.getCompaniesAvgSalary();
		ResponseJava response = new ResponseJava(TcpResponseCode.OK, (Serializable) res);
		return response;
		
	} catch (Exception e) {
		return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
	}
}


ResponseJava updateCompany(Serializable requestData) {
	try {
		Map<Long, String> mapUpdateCompany = (Map<Long, String>) requestData;
		Long id = null;
		String newCompany = null;
		for (Map.Entry<Long, String> map : mapUpdateCompany.entrySet()) {
			id = map.getKey();
			newCompany = map.getValue();
		}
	
		EmployeesReturnCodes res = employees.updateCompany(id , newCompany);
		ResponseJava response = new ResponseJava(TcpResponseCode.OK, (Serializable) res);
		return response;

	} catch (Exception e) {
		return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
	}
}

ResponseJava updateSalary(Serializable requestData) {
	try {
		Map<Long, Integer> mapUpdateSalary = (Map<Long, Integer>) requestData;
		Long id = null;
		Integer newsalary = null;
		for (Entry<Long, Integer> map : mapUpdateSalary.entrySet()) {
			id = map.getKey();
			newsalary = map.getValue();
		}
	
		EmployeesReturnCodes res = employees.updateSalary(id , newsalary);
		ResponseJava response = new ResponseJava(TcpResponseCode.OK, (Serializable) res);
		return response;

	} catch (Exception e) {
		return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
	}
}
	
		
	ResponseJava wrongRequest(Serializable requestData) {
		return new ResponseJava(TcpResponseCode.WRONG_REQUEST, "Type of request not found" );
	}
	
}
