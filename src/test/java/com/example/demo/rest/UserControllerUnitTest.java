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

import com.example.demo.dto.UserDto;
import com.example.demo.persistence.domain.User;
import com.example.demo.service.UserService;

@SpringBootTest //spring boot test lets spring know this is a test file and treat as such
@ActiveProfiles("dev") //lets us set the application properties file
public class UserControllerUnitTest {
	
	@Autowired
	private UserController controller;
	
	@MockBean
	private UserService service;
	
	@Autowired
	private ModelMapper mapper;
	
	private UserDto mapToDto(User user) {
		return this.mapper.map(user, UserDto.class);
	}
	
	private final User TEST_USER_1 = new User(1L, "James", 15);
	private final User TEST_USER_2 = new User(1L, "Kyle", 20);
	private final User TEST_USER_3 = new User(1L, "Test", 56);
	
	private final List<User> ListOfUsers = List.of(TEST_USER_1,TEST_USER_2,TEST_USER_3);
	
	//Create
	@Test
	void createTest() throws Exception{
		when(this.service.create(TEST_USER_1)).thenReturn(this.mapToDto(TEST_USER_1));
		assertThat(new ResponseEntity<UserDto>(this.mapToDto(TEST_USER_1), HttpStatus.CREATED))
				.isEqualTo(this.controller.create(TEST_USER_1));
		verify(this.service, atLeastOnce()).create(TEST_USER_1);
	}
	//read one
	@Test
	void readOneTest() throws Exception{
		when(this.service.readOne(TEST_USER_1.getId())).thenReturn(this.mapToDto(TEST_USER_1));
		assertThat(new ResponseEntity<UserDto>(this.mapToDto(TEST_USER_1), HttpStatus.OK))
			.isEqualTo(this.controller.readOne(TEST_USER_1.getId()));
		verify(this.service, atLeastOnce()).readOne(TEST_USER_1.getId());
	}
	
	//read all
	@Test
	void readAllTest() throws Exception{
		List<UserDto> dtos =ListOfUsers.stream().map(this::mapToDto).collect(Collectors.toList());
		when(this.service.readAll()).thenReturn(ListOfUsers.stream().map(this::mapToDto).collect(Collectors.toList()));
		assertThat(this.controller.read()).isEqualTo(new ResponseEntity<>(dtos, HttpStatus.OK));
		verify(this.service, atLeastOnce()).readAll();
	}
	//update
	@Test
	void updateTest() throws Exception{
		when(this.service.update(this.mapToDto(TEST_USER_1), TEST_USER_1.getId())).thenReturn(this.mapToDto(TEST_USER_1));
		assertThat(new ResponseEntity<UserDto>(this.mapToDto(TEST_USER_1), HttpStatus.ACCEPTED)).isEqualTo(this.controller.update(TEST_USER_1.getId(), this.mapToDto(TEST_USER_1)));
		verify(this.service, atLeastOnce()).update(this.mapToDto(TEST_USER_1), TEST_USER_1.getId());
	}
	//delete
	@Test
	void deleteTest() throws Exception{
		this.controller.delete(TEST_USER_1.getId());
		verify(this.service, atLeastOnce()).delete(TEST_USER_1.getId());
	}
	
	
	//find by name
	@Test
	void findByNameTest() throws Exception{
		List<UserDto> dtos =ListOfUsers.stream().map(this::mapToDto).collect(Collectors.toList());
		when(this.service.findByName(TEST_USER_1.getName())).thenReturn(dtos);
		assertThat(this.controller.readByName(TEST_USER_1.getName())).isEqualTo(new ResponseEntity<List<UserDto>>(dtos, HttpStatus.OK));
		verify(this.service, atLeastOnce()).findByName(TEST_USER_1.getName());
	}


}
