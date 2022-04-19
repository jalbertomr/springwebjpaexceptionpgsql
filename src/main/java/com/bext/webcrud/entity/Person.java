package com.bext.webcrud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name="personjpa")
@Data
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="PERSONJPA_SEQ")
	private Long id;
	
	@Column(nullable = false)
	@NotBlank @NotNull(message = "firstName cannot be null") 
	private String firstName;
	
	@Column(nullable = false)
	@NotNull
	private String lastName;
	
	@Min(1) @Max(200) 
	private int age;
	
	private Integer integer;
}
