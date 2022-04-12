package com.bext.webcrud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="person_jpa")
@Data
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String firstName;
	private String lastName;
	@Column(nullable = false)
	private int age;
	private Integer integer;
}
