package com.example.demo.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.demo.dto.UserDto;
import com.example.demo.persistence.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
//sql runs in order so put schema first
@Sql(scripts = {"classpath:toDo-schema.sql", 
		"classpath:to-Do-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("dev")
public class UserControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper jsonifier;
	
	@Autowired
	private ModelMapper mapper;
	
	private UserDto mapToDto(User user) {
		return this.mapper.map(user, UserDto.class);
	}
	
	private final User TEST_USER_1 = new User(1L, "James", 1);
	private final User TEST_USER_2 = new User(2L, "Kyle", 1);
	
	private final List<User> ListOfUsers = List.of(TEST_USER_1,TEST_USER_2);
	
	private final String URI = "/user";
	
//	@Test
//	void createTest() throws Exception {
//		UserDto testDTO = mapToDto(new User("Tyler", 2));
//		String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);
//		
//		RequestBuilder request = post(URI + "/create").contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);
//		
//		ResultMatcher checkStatus = status().isCreated();
//		
//		UserDto testSavedDTO = mapToDto(new User("Tyler", 2));
//		testSavedDTO.setId(3L);
//		String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);
//		
//		ResultMatcher checkBody = content().json(testSavedDTOAsJSON);
//		
//		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
//	}
//	
//	@Test
//	void deleteTest() throws Exception {
//		
//		RequestBuilder request = delete(URI + "/1");
//		
//		ResultMatcher checkStatus = status().isNoContent();
//		
//		this.mvc.perform(request).andExpect(checkStatus);
//	}
//	
	

}
