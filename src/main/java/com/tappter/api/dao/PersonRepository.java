package com.tappter.api.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tappter.api.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{

	@Query("SELECT p FROM Person p WHERE p.email = ?1")
	Optional<Person> findPersonByEmail(String email);
	
	@Query("SELECT p FROM Person p WHERE p.personName LIKE %:name% AND p.age = :age")
	List<Person> findPeopleByNameAndAge(String name, Integer age);
	
}
