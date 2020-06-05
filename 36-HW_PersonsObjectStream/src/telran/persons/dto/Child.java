package telran.persons.dto;

import java.time.LocalDate;


public class Child extends Person {
/**
	 * 
	 */
	private static final long serialVersionUID = 300L;
String garten;
public Child() {
}
public Child(int id, String name, Address address, LocalDate birthDate, String garten) {
	super(id, name, address, birthDate);
	this.garten = garten;
}
public String getGarten() {
	return garten;
}
public void setGarten(String garten) {
	this.garten = garten;
}
@Override
public String toString() {
	return "Child [garten=" + garten + ", id=" + id + ", name=" + name + ", address=" + address + ", birthDate="
			+ birthDate + "]";
}

}
