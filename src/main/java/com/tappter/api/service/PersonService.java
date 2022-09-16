package com.tappter.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.tappter.api.dao.PersonRepository;
import com.tappter.api.dto.PersonDTO;
import com.tappter.api.exception.WrongUserRequestException;
import com.tappter.api.model.Person;

@Service
public class PersonService {
	
	private final PersonRepository personRepository;
	
	private ModelMapper mapper;
	
	@Autowired
	public PersonService(PersonRepository personRepository, ModelMapper mapper) {
		this.personRepository = personRepository;
		this.mapper = mapper;
	}
	
//	use ModelMapper to convert a Person to a PersonDTO
	private PersonDTO mapToDTO(Person person) {
		return mapper.map(person, PersonDTO.class);
	}
	
	
	public Page<Person> getAllPeopleWithPagination(int offset, int pageSize){
		return personRepository.findAll(PageRequest.of(offset, pageSize));
	}
	
	public Person findPersonById(Integer personId) {
		Optional<Person> personOptional = personRepository.findById(personId);
		if (personOptional.isEmpty()) {
			throw new WrongUserRequestException("User with id " + personId + " does not exist");
		} else {
			return personRepository.findById(personId).get();
		}
	}
	
	public Person addNewPerson(Person person) {
		Optional<Person> personOptional = personRepository.findPersonByEmail(person.getEmail());
		if (personOptional.isPresent()) {
			throw new WrongUserRequestException("Email already registered with another user");
		}
		return personRepository.save(person);
	}

	public void deletePerson(Integer personId) {
		boolean exists = personRepository.existsById(personId);
		if (!exists) {
			throw new WrongUserRequestException("User with id "+ personId + " does not exist" );
		} else {
			personRepository.deleteById(personId);
		}
	}

	public Person updatePerson(Integer personId, Person person) {
		boolean exists = personRepository.existsById(personId);
		if (!exists) {
			throw new WrongUserRequestException("User with id "+ personId + " does not exist" );
		} else {
			Person personUpdate = personRepository.findById(personId).get();
			
//			set new values for the user
			personUpdate.setPersonName(person.getPersonName());
			personUpdate.setPersonSurname(person.getPersonSurname());
			personUpdate.setEmail(person.getEmail());
			personUpdate.setPhoneNumber(person.getPhoneNumber());
			personUpdate.setDateOfBirth(person.getDateOfBirth());
			personUpdate.setAge(person.getAge());
			personUpdate.setUsername(person.getUsername());
			personUpdate.setPassword(person.getPassword());
			
	        //save updated user
	        return personRepository.save(personUpdate);
		}
	}

	public List<PersonDTO> getListPersonByNameAndAge(String name, Integer age){
		List<Person> people = personRepository.findPeopleByNameAndAge(name, age);
		return people.stream().map(person -> this.mapToDTO(person)).collect(Collectors.toList());
	} 

}
