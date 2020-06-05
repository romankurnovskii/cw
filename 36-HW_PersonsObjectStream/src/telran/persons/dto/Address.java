package telran.persons.dto;

import java.io.Serializable;


public class Address implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 200L;
String city;
String street;
int building;
int aprt;
public Address() {
}
public Address(String city, String street, int building, int aprt) {
	super();
	this.city = city;
	this.street = street;
	this.building = building;
	this.aprt = aprt;
}
public String getCity() {
	return city;
}
public String getStreet() {
	return street;
}
public int getBuilding() {
	return building;
}
public int getAprt() {
	return aprt;
}
@Override
public String toString() {
	return "Address [city=" + city + ", street=" + street + ", building=" + building + ", aprt=" + aprt + "]";
}

}
