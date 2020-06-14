package telran.employees.items;

import telran.employees.api.EmployeesService;
import telran.menu.InputOutput;

public class GetEmployeesGroupedBySalary extends EmployeesItem {

    public GetEmployeesGroupedBySalary(EmployeesService employees, InputOutput inputOutput) {
        super(employees, inputOutput);
    }

    @Override
    public String displayName() {
        return "Display all employees with a given salary";
    }

    @Override
    public void perform() {
        int from = inputOutput.inputInteger("Enter salary from");
        int to = inputOutput.inputInteger("Enter salary to");
        inputOutput.displayLine(employees.getEmployeesSalary(from, to));
    }
}
