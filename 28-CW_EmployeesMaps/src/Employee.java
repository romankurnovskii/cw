import java.time.LocalDate;

public class Employee {
	
	public Employee(long id, int salary, String company, LocalDate birthYearDate, String name) {
		super();
		this.id = id;
		this.salary = salary;
		this.company = company;
		this.birthYearDate = birthYearDate;
		this.name = name;
	}



	private long id;
	private int salary;
	private String company;
	private LocalDate birthYearDate;
	private String name;
	
	
	public long getId() {
		return id;
	}


	public int getSalary() {
		return salary;
	}


	public String getCompany() {
		return company;
	}


	public LocalDate getBirthYearDate() {
		return birthYearDate;
	}


	public String getName() {
		return name;
	}


	
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", salary=" + salary + ", company=" + company + ", birthYearDate=" + birthYearDate
				+ ", name=" + name + "]";
	}

}
