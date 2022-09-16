package com.tappter.api.dto;

import java.time.LocalDate;

import com.tappter.api.model.Person;

public class PersonDTO {

	private Integer personId;
	private String personName;
	private String personSurname;
	private String email;
	private String phoneNumber;
	private LocalDate dateOfBirth;
	private Integer age;
	
	public PersonDTO() {}

	public PersonDTO(Person person) {
		super();
		this.personId = person.getPersonId();
		this.personName = person.getPersonName();
		this.personSurname = person.getPersonSurname();
		this.email = person.getEmail();
		this.phoneNumber = person.getPhoneNumber();
		this.dateOfBirth = person.getDateOfBirth();
		this.age = person.getAge();
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonSurname() {
		return personSurname;
	}

	public void setPersonSurname(String personSurname) {
		this.personSurname = personSurname;
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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "PersonDTO [personId=" + personId + ", personName=" + personName + ", personSurname=" + personSurname
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", dateOfBirth=" + dateOfBirth + ", age=" + age
				+ "]";
	}
	
	
	
	
	
	
}
