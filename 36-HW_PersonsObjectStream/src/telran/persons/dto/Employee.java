package telran.persons.dto;

import java.time.LocalDate;


public class Employee extends Person {
/**
	 * 
	 */
	private static final long serialVersionUID = 100L;
String company;
int salary;
String title;
public Employee() {
}
public Employee(int id, String name, Address address, LocalDate birthDate, String company, int salary, String title) {
	super(id, name, address, birthDate);
	this.company = company;
	this.salary = salary;
	this.title = title;
}
public String getCompany() {
	return company;
}
public void setCompany(String company) {
	this.company = company;
}
public int getSalary() {
	return salary;
}
public void setSalary(int salary) {
	this.salary = salary;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
@Override
public String toString() {
	return "Employee [company=" + company + ", salary=" + salary + ", title=" + title + ", id=" + id + ", name=" + name
			+ ", address=" + address + ", birthDate=" + birthDate + "]";
}

}
