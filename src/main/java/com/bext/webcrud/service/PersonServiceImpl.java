package com.bext.webcrud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bext.webcrud.entity.Person;
import com.bext.webcrud.exception.CatchException;
import com.bext.webcrud.exception.EmptyInputException;
import com.bext.webcrud.repo.PersonJpaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonServiceImpl implements IPersonService {

	@Autowired
	private PersonJpaRepository personJpaRepo;

	@Override
	public List<Person> getAllPersons() {
		return personJpaRepo.findAll();
	}

	@Override
	public Optional<Person> getById(Long id) {
		return personJpaRepo.findById(id);
	}

	@Override
	public Person addPerson(Person person) {
		return personJpaRepo.save(person);
	}

	@Override
	public Optional<Person> updatePerson(Person person) {
		Optional<Person> retPerson = Optional.empty();
		if (getById(person.getId()).isPresent()) {
			retPerson = Optional.of(person);
			personJpaRepo.save(person);
		}
		return retPerson;
	}

	@Override
	public List<Person> getByFirstName(String firstName) {
		return personJpaRepo.findByFirstName(firstName);
	}

	@Override
	public List<Person> getByLastName(String lastName) {
		return personJpaRepo.findByLastName(lastName);
	}

	@Override
	public List<Person> getByAge(String age) {
		return personJpaRepo.findByAge(Integer.valueOf(age));
	}

	@Override
	public Optional<Person> deleteById(Long id) {
		Optional<Person> personToDelete = getById(id);
		if (personToDelete.isPresent()) {
			personJpaRepo.deleteById(id);
		}
		return personToDelete;
	}
}
