package com.tappter.api.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;


@Entity
//@Table(name="person_table")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer personId;
	private String personName;
	private String personSurname;
	@Email
	private String email;
	@Length(min=6, max=8)
	private String phoneNumber;
	private LocalDate dateOfBirth;
	private Integer age;
	private String username;
	private String password;
	
	public Person() {}
	
	public Person(Integer personId, String personName, String personSurname, String email, String phoneNumber,
			LocalDate dateOfBirth,Integer age, String username, String password) {
		super();
		this.personId = personId;
		this.personName = personName;
		this.personSurname = personSurname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.age = age;
		this.username = username;
		this.password = password;
	}
	
	

	public Person(String personName, String personSurname, String email, String phoneNumber, LocalDate dateOfBirth, Integer age, String username, String password) {
		super();
		this.personName = personName;
		this.personSurname = personSurname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.age = age;
		this.username = username;
		this.password = password;
	}
	
	


	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public void setPersonSurname(String personSurname) {
		this.personSurname = personSurname;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPersonId() {
		return personId;
	}

	public String getPersonName() {
		return personName;
	}

	public String getPersonSurname() {
		return personSurname;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	@Override
	public String toString() {
		return "Person [personId=" + personId + ", personName=" + personName + ", personSurname=" + personSurname
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", dateOfBirth=" + dateOfBirth + ", age=" + age
				+ ", username=" + username + ", password=" + password + "]";
	}
	
	
}
