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

import com.example.demo.dto.ToDoDto;
import com.example.demo.persistence.domain.ToDo;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
//sql runs in order so put schema first
@Sql(scripts = {"classpath:toDo-schema.sql", 
		"classpath:to-Do-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("dev")
public class ToDoControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper jsonifier;
	
	@Autowired
	private ModelMapper mapper;
	
	private ToDoDto mapToDto(ToDo toDo) {
		return this.mapper.map(toDo, ToDoDto.class);
	}
	
	private final ToDo TEST_TODO_1 = new ToDo(1L, "London");
	private final ToDo TEST_TODO_2 = new ToDo(2L, "Tokyo");
	
	private final List<ToDo> ListOfToDos = List.of(TEST_TODO_1,TEST_TODO_2);
	
	private final String URI = "/todo";
	
	//create
//	@Test
//	void createTest() throws Exception {
//		ToDoDto testDTO = mapToDto(new ToDo("Paris"));
//		String testDTOAsJSON = this.jsonifier.writeValueAsString(testDTO);
//		RequestBuilder request = post(URI).contentType(MediaType.APPLICATION_JSON).content(testDTOAsJSON);
//		
//		ResultMatcher checkStatus = status().isCreated();
//		
//		ToDoDto testSavedDTO = mapToDto(new ToDo("Paris"));
//		testSavedDTO.setId(3L);
//		String testSavedDTOAsJSON = this.jsonifier.writeValueAsString(testSavedDTO);
//		ResultMatcher checkBody = content().json(testSavedDTOAsJSON);
//		
//		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
//	}
//	
//	@Test
//	void readAllTest() throws Exception{
//		
//	}
//	
//	@Test
//	void readOneTest() throws Exception{
//		
//	}
//	@Test
//	void deleteTest() throws Exception {
//		RequestBuilder request = delete(URI + "/1");
//		ResultMatcher checkStatus = status().isNoContent();
//		
//		this.mvc.perform(request).andExpect(checkStatus);
//	}

}
