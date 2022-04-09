package com.bext.webcrud.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
	public Optional<Person> getById(Long id) {
        try {
			return personJpaRepo.findById(id);
		} catch (org.springframework.dao.InvalidDataAccessApiUsageException e) {
		    throw new BusinessException("606","Person findById id cannot be null " + e.getMessage());	
		} catch (Exception e) {
			throw new BusinessException("ExServPeGetById","Exception on Service Person getById " + e.getMessage());
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

	@Override
	public Person addPerson(Person person) {
		if (person.getFirstName() == null) {
			throw new BusinessException("601", "FirstName cannot be null");
		}
		if (person.getFirstName().isEmpty() || person.getFirstName().strip().length() == 0 ) {
			throw new BusinessException("601", "FirstName cannot be empty");
		}
		try {
			Person savePerson = personJpaRepo.save(person);
			return savePerson;
		} catch (org.springframework.dao.InvalidDataAccessApiUsageException e) {
			throw new BusinessException("602", "addPerson save person cannot be null " + e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("603", "Exception on Person addPerson-save " + e.getMessage());
		}
	}

	@SuppressWarnings("finally")
	@Override
	public Optional<Person> deleteById(Long id) {
		Optional<Person> person = null ;
        try {
        	person = this.getById(id);
        	personJpaRepo.deleteById(id);
        	return person;
		} catch (IllegalArgumentException e) {
			throw new BusinessException("608","Person Id cannot be null " + e.getMessage());
		} catch (NoSuchElementException e) {
			throw new BusinessException("609", "Person id does not exist on Database " + e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("ExPeServDelById","Exception on Person Service deleteleById");
		}
	}

	

}
