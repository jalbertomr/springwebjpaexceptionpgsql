package com.bext.webcrud.service;

import java.util.List;
import java.util.Optional;

import com.bext.webcrud.entity.Person;

public interface IPersonService {
	public List<Person> getAllPersons();

	public Person addPerson(Person person);

	public Optional<Person> updatePerson(Person person);
	
	public Optional<Person> getById(Long id);

	public List<Person> getByFirstName(String firstName);

	public List<Person> getByLastName(String lastName);
	
	public List<Person> getByAge(String age);
	
	public Optional<Person> deleteById(Long id);

}
