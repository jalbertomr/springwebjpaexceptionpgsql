package com.bext.webcrud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bext.webcrud.entity.Person;
import com.bext.webcrud.exception.BusinessException;
import com.bext.webcrud.exception.ControllerException;
import com.bext.webcrud.service.IPersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private IPersonService iPersonService;

	@GetMapping
	public ResponseEntity<List<Person>> getAll() {
		List<Person> persons = iPersonService.getAllPersons();
		return new ResponseEntity<List<Person>>(persons, persons.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getByid(@PathVariable Long id) {
		try {
			Optional<Person> person = iPersonService.getById(id);
			return new ResponseEntity<Optional<Person>>(person, person.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("611", "Person getById " + e.getMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/firstname/{firstname}")
	public ResponseEntity<List<Person>> getByFirstName(@PathVariable("firstname") String firstName) {
		List<Person> persons = (List<Person>) iPersonService.getByFirstName(firstName);
		return new ResponseEntity<List<Person>>(persons, persons.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}

	@GetMapping("/lastname/{lastname}")
	public ResponseEntity<List<Person>> getByLastName(@PathVariable("lastname") String lastName) {
		List<Person> persons = (List<Person>) iPersonService.getByLastName(lastName);
		return new ResponseEntity<List<Person>>(persons, persons.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}

	@GetMapping("/age/{age}")
	public ResponseEntity<List<Person>> getByAge(@PathVariable("age") String age) {
		List<Person> persons = (List<Person>) iPersonService.getByAge(age);
		return new ResponseEntity<List<Person>>(persons, persons.size() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Optional<Person>> geleteById(@PathVariable Long id) {
		Optional<Person> person = iPersonService.deleteById(id);
		return new ResponseEntity<Optional<Person>>(person, person.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.ACCEPTED);
	}

	@PostMapping
	public ResponseEntity<?> addPerson(@RequestBody Person person) {
		if ( person.getAge() <= 0 ) {
			ControllerException ce = new ControllerException("ConPerAgeLessZero","PersonController age cannot be less to zero ");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
 		try {
			Person newPerson = iPersonService.addPerson(person);
			return new ResponseEntity<Person>(newPerson, HttpStatus.CREATED);
		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("610", "addPerson" + e.getMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping
	public ResponseEntity<?> updatePerson(@RequestBody Person person) {
		if ( person.getAge() <= 0 ) {
			ControllerException ce = new ControllerException("ConPerAgeLessZero","PersonController age cannot be less to zero ");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
		try {
			Optional<Person> updatedPerson = iPersonService.updatePerson(person);
			return new ResponseEntity<Optional<Person>>(updatedPerson, updatedPerson.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.ACCEPTED);
		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("610", "updatePerson" + e.getMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> handleHttpMessageNotReadable(HttpMessageNotReadableException hmnre) {
		return new ResponseEntity("Incorrect Body in request", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handlePSQL(Exception e){
		return new ResponseEntity<String>("Error - " + e.getLocalizedMessage() + e.getCause(), HttpStatus.METHOD_FAILURE );
	}
	
}
