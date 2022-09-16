package com.tappter.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tappter.api.dto.PersonDTO;
import com.tappter.api.model.Person;
import com.tappter.api.service.PersonService;

@RestController
@RequestMapping(path = "/api/v1/users")
public class PersonController {
	
	private final PersonService personService;
	
	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	
	@GetMapping("/{offset}/{pageSize}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<Person>> getAllPeopleWithPagination(
											@PathVariable("offset") int offset,
											@PathVariable("pageSize") int pageSize){
		Page<Person> people = personService.getAllPeopleWithPagination(offset, pageSize);
		return new ResponseEntity<Page<Person>>(people, HttpStatus.OK);
	}
	
	@GetMapping("/user/{personId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Person> getPersonById(@PathVariable("personId") Integer personId) {
		Person person = personService.findPersonById(personId);
		return new ResponseEntity<>(person, HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Person> addNewPerson(@RequestBody Person person) {
		personService.addNewPerson(person);
		return new ResponseEntity<>(person, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{personId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<HttpStatus> deletePerson(@PathVariable Integer personId) {
		personService.deletePerson(personId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/update/{personId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<HttpStatus> updatePerson(
			@PathVariable("personId") Integer personId,
			@RequestBody Person person) {
		
		personService.updatePerson(personId, person);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	
	@GetMapping("/nameandage/")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUEST')")
	public ResponseEntity<List<PersonDTO>> getListPersonByNameAndAge(
			@RequestParam(value = "name") String name,
			@RequestParam(value = "age") Integer age) {
		
		return new ResponseEntity<List<PersonDTO>>(
				personService.getListPersonByNameAndAge(name, age),
				HttpStatus.OK);
	}

	
	
	
	
	
	
	
	
}
