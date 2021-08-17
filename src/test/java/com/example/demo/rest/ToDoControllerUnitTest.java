package com.example.demo.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.dto.ToDoDto;
import com.example.demo.persistence.domain.ToDo;
import com.example.demo.service.ToDoService;

@SpringBootTest //spring boot test lets spring know this is a test file and treat as such
@ActiveProfiles("dev") //lets us set the application properties file
public class ToDoControllerUnitTest {

	@Autowired
	private ToDoController controller;
	
	@MockBean
	private ToDoService service;
	
	@Autowired
	private ModelMapper mapper;
	
	private ToDoDto mapToDto(ToDo toDo) {
		return this.mapper.map(toDo, ToDoDto.class);
	}
	
	private final ToDo TEST_TODO_1 = new ToDo(1L, "London");
	private final ToDo TEST_TODO_2 = new ToDo(2L, "Tokyo");
	
	private final List<ToDo> ListOfToDos = List.of(TEST_TODO_1,TEST_TODO_2);
	
	//Create
	@Test
	void createTest() throws Exception{
		when(this.service.create(TEST_TODO_1)).thenReturn(this.mapToDto(TEST_TODO_1));
		assertThat(new ResponseEntity<ToDoDto>(this.mapToDto(TEST_TODO_1), HttpStatus.CREATED))
				.isEqualTo(this.controller.create(TEST_TODO_1));
		verify(this.service, atLeastOnce()).create(TEST_TODO_1);
	}
	//read one
	@Test
	void readOneTest() throws Exception{
		when(this.service.readOne(TEST_TODO_1.getId())).thenReturn(this.mapToDto(TEST_TODO_1));
		assertThat(new ResponseEntity<ToDoDto>(this.mapToDto(TEST_TODO_1), HttpStatus.OK))
			.isEqualTo(this.controller.readOne(TEST_TODO_1.getId()));
		verify(this.service, atLeastOnce()).readOne(TEST_TODO_1.getId());
	}
	
	//read all
	@Test
	void readAllTest() throws Exception{
		List<ToDoDto> dtos =ListOfToDos.stream().map(this::mapToDto).collect(Collectors.toList());
		when(this.service.readAll()).thenReturn(ListOfToDos.stream().map(this::mapToDto).collect(Collectors.toList()));
		assertThat(this.controller.read()).isEqualTo(new ResponseEntity<>(dtos, HttpStatus.OK));
		verify(this.service, atLeastOnce()).readAll();
	}
	//update
	@Test
	void updateTest() throws Exception{
		when(this.service.update(this.mapToDto(TEST_TODO_1), TEST_TODO_1.getId())).thenReturn(this.mapToDto(TEST_TODO_1));
		assertThat(new ResponseEntity<ToDoDto>(this.mapToDto(TEST_TODO_1), HttpStatus.ACCEPTED)).isEqualTo(this.controller.update(TEST_TODO_1.getId(), this.mapToDto(TEST_TODO_1)));
		verify(this.service, atLeastOnce()).update(this.mapToDto(TEST_TODO_1), TEST_TODO_1.getId());
	}
	//delete
	@Test
	void deleteTest() throws Exception{
		this.controller.delete(TEST_TODO_1.getId());
		verify(this.service, atLeastOnce()).delete(TEST_TODO_1.getId());
	}
	
//	@Test
//	void deleteTestOnce() throws Exception{
//		when(this.service.delete(Test_car_1.getId())).thenReturn(false);
//		assertThat(this.controller.delete(Test_car_1.getId())).isEqualTo(expected)
//	}
	//find by name
	@Test
	void findByNameTest() throws Exception{
		List<ToDoDto> dtos =ListOfToDos.stream().map(this::mapToDto).collect(Collectors.toList());
		when(this.service.findByName(TEST_TODO_1.getTodoTitle())).thenReturn(dtos);
		assertThat(this.controller.findByName(TEST_TODO_1.getTodoTitle())).isEqualTo(new ResponseEntity<List<ToDoDto>>(dtos, HttpStatus.OK));
		verify(this.service, atLeastOnce()).findByName(TEST_TODO_1.getTodoTitle());
	}
}
