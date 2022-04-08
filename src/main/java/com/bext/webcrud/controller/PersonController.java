package com.bext.webcrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bext.webcrud.entity.Person;
import com.bext.webcrud.service.IPersonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/persons")
public class PersonController {
	
    @Autowired
	private IPersonService iPersonService;
	
	@GetMapping
	public ResponseEntity<List<Person>> getAll() {
		List<Person> persons = iPersonService.getAllPersons();
		return new ResponseEntity<List<Person>>( persons, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Person> getByid(@PathVariable Long id){
		Person person = iPersonService.getById(id);
		return new ResponseEntity<Person>( person, HttpStatus.OK);
	}
	
	@GetMapping("/firstname/{firstname}")
	public ResponseEntity<List<Person>> getByFirstName(@PathVariable("firstname") String firstName){
		List<Person> persons = (List<Person>) iPersonService.getByFirstName(firstName);
		return new ResponseEntity<List<Person>>( persons, HttpStatus.OK);
	}

	@GetMapping("/lastname/{lastname}")
	public ResponseEntity<List<Person>> getByLastName(@PathVariable("lastname") String lastName){
		List<Person> persons = (List<Person>) iPersonService.getByLastName(lastName);
		return new ResponseEntity<List<Person>>( persons, HttpStatus.OK);
	}
	
	@GetMapping("/age/{age}")
	public ResponseEntity<List<Person>> getByAge(@PathVariable("age") String age){
		List<Person> persons = (List<Person>) iPersonService.getByAge(age);
		return new ResponseEntity<List<Person>>( persons, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> geleteById(@PathVariable Long id){
		iPersonService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	@PostMapping
	public ResponseEntity<Person> addPerson(@RequestBody Person person){
		Person newPerson = iPersonService.addPerson( person);
		return new ResponseEntity<Person>( newPerson, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Person> updatePerson(@RequestBody Person person){
		Person updatedPerson = iPersonService.addPerson(person);
		return new ResponseEntity<Person>( updatedPerson, HttpStatus.ACCEPTED);
	}
	
}