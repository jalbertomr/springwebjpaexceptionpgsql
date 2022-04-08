package com.bext.webcrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bext.webcrud.entity.Person;
import com.bext.webcrud.repo.PersonJpaRepository;

@Service
public class PersonServiceImpl implements IPersonService {
	
    @Autowired
	private PersonJpaRepository personJpaRepo;
	
	@Override
	public List<Person> getAllPersons() {
		return personJpaRepo.findAll();
	}

	@Override
	public Person getById(Long id) {
        return personJpaRepo.findById(id).get();
	}

	@Override
	public Person addPerson(Person person) {
		return personJpaRepo.save(person);
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
	public void deleteById(Long id) {
		personJpaRepo.deleteById(id);
	}


}
