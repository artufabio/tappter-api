package com.tappter.api.controllers_test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tappter.api.dto.PersonDTO;
import com.tappter.api.model.Person;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"/test-schema.sql", "/test-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class PersonControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	@WithMockUser(username="admin",password="password", roles= {"ADMIN"})
	void addNewPersonTest() throws Exception{
		String testPersonAsJson = this.mapper.writeValueAsString(
				new Person(null, "Elvis", "Presley", "elv@gmail.com", "333444", LocalDate.of(1948, 12, 17), 74, "iamelvis", "secret123"));
		String testPersonAsJsonResponse = this.mapper.writeValueAsString(
				new Person(3, "Elvis", "Presley", "elv@gmail.com", "333444", LocalDate.of(1948, 12, 17), 74, "iamelvis", "secret123"));
		
		this.mvc.perform(post("/api/v1/users").contentType(MediaType.APPLICATION_JSON).content(testPersonAsJson))
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(MockMvcResultMatchers.content().json(testPersonAsJsonResponse));
	}
	
	
	@Test
	@WithMockUser(username="admin",password="password", roles= {"ADMIN"})
	void deletePersonTest() throws Exception {
		this.mvc.perform(delete("/api/v1/users/delete/2")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username="admin",password="password", roles= {"ADMIN"})
	void updatePersonTest() throws Exception{
		String updatedPersonAsJson = this.mapper.writeValueAsString(new Person(1, "Bob", "Marley", "bob@gmail.com", "333444", LocalDate.of(1948, 12, 17), 74, "iamelvis", "secret123"));
		
		this.mvc.perform(put("/api/v1/users/update/1").contentType(MediaType.APPLICATION_JSON).content(updatedPersonAsJson))
			.andExpect(MockMvcResultMatchers.status().isAccepted());
	}
	
	@Test
	@WithMockUser(username="admin",password="password", roles= {"ADMIN"})
	void getPersonByIdTest() throws Exception{
		String personAsJson = this.mapper.writeValueAsString(
						new Person(1, "Elvis", "Presley", "elvis@gmail.com", "333444", LocalDate.of(1948, 12, 17), 74, "iamelvis", "secret123")
						);
		
		this.mvc.perform(get("/api/v1/users/user/1"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().json(personAsJson));
	}
	
	@Test
	@WithMockUser(username="admin",password="password", roles= {"ADMIN", "GUEST"})
	void getListPersonByNameAndAgeTest() throws Exception{
		String personDTOAsJson = this.mapper.writeValueAsString(List.of(
				new PersonDTO(
						new Person(2, "Freddy", "Mercury", "freddy@gmail.com", "555666", LocalDate.of(1960, 12, 17), 62,"iamfreddy", "secret456"))
				));
		
		this.mvc.perform(get("/api/v1/users/nameandage/?name=eddy&age=62"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().json(personDTOAsJson));
	}
}
