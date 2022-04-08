package com.bext.webcrud.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bext.webcrud.entity.Person;

@Repository
public interface PersonJpaRepository extends JpaRepository<Person, Long>{

	List<Person> findByFirstName(String firstName);
	List<Person> findByLastName(String lastName);
	List<Person> findByAge(int age);

}
