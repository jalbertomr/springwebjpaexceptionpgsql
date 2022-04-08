package com.bext.webcrud.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bext.webcrud.entity.Person;
import com.bext.webcrud.exception.BusinessException;
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
        try {
			return personJpaRepo.findById(id).get();
		} catch (IllegalArgumentException e) {
			throw new BusinessException("606","Person Id cannot be null");
		} catch (NoSuchElementException e) {
			throw new BusinessException("607", "Person id does not exist on Database");
		}
	}

	@Override
	public Person addPerson(Person person) {
		try {
			if ( person.getFirstName().isEmpty() || person.getFirstName().strip().length() == 0) {
				throw new BusinessException("601","FirstName cannot be empty");
			}
			Person savePerson = personJpaRepo.save(person);
			return savePerson;
		} catch( IllegalArgumentException e) {
			throw new BusinessException("602", "First Name cannot be null");
		} catch (Exception e) {
			throw new BusinessException("603", "Exception on Person addPerson-save");
		}
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

	@SuppressWarnings("finally")
	@Override
	public Person deleteById(Long id) {
		Person person = null;
        try {
        	person = this.getById(id);
        	personJpaRepo.deleteById(id);
        	return person;
		} catch (IllegalArgumentException e) {
			throw new BusinessException("608","Person Id cannot be null");
		} catch (NoSuchElementException e) {
			throw new BusinessException("609", "Person id does not exist on Database");
		} finally {
			return person;
		}
	}

	

}
