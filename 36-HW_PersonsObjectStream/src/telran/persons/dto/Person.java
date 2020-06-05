package telran.persons.dto;

import java.io.Serializable;
import java.time.LocalDate;


public class Person implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 50L;
int id;
String name;
Address address;
LocalDate birthDate;
public Person() {
}
public Person(int id, String name, Address address, LocalDate birthDate) {
	super();
	this.id = id;
	this.name = name;
	this.address = address;
	this.birthDate = birthDate;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Address getAddress() {
	return address;
}
public void setAddress(Address address) {
	this.address = address;
}
public int getId() {
	return id;
}
public LocalDate getBirthDate() {
	return birthDate;
}
@Override
public String toString() {
	return "Person [id=" + id + ", name=" + name + ", address=" + address + ", birthDate=" + birthDate + "]";
}

}
