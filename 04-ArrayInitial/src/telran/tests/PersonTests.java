package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.persons.dto.Person;

class PersonTests {

	@Test
	void testPerson() {
		Person person = new Person();
		assertNotNull(person);
		assertNull(person.getName());
		
	}

	@Test
	void testPersonLongStringInt() {
		long id = 123;
		String name = "Moshe";
		int birthYear = 1980;
		Person person = new Person(id, name, birthYear);
		assertEquals(name, person.getName());
		assertEquals(birthYear, person.getBirthYear());
		assertEquals(id, person.getId());
	}

	

	@Test
	void testSetName() {
		String name = "Moshe";
		Person person = new Person();
		person.setName(name);
		assertEquals(name, person.getName());
	}
	@Test
	void testEqualedObjects() {
		Person person1 = new Person(123,"Moshe",1980);
		Person person2 = new Person(123,"Moshe",1980);
		assertEquals(person1, person2);
	}

	

	
}
