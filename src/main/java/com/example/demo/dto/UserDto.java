package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.persistence.domain.ToDo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
	
	private Long id;
	private String name;
	private int age;
	private List<ToDoDto> toDos = new ArrayList<>();

}