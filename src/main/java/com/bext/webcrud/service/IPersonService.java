package com.bext.webcrud.service;

import java.util.List;

import com.bext.webcrud.entity.Person;

public interface IPersonService {
	public List<Person> getAllPersons();

	public Person addPerson(Person person);

	public Person getById(Long id);

	public List<Person> getByFirstName(String firstName);

	public List<Person> getByLastName(String lastName);
	
	public List<Person> getByAge(String age);
	
	public Person deleteById(Long id);
}
