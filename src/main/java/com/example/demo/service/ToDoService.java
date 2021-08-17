package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ToDoDto;
import com.example.demo.exceptions.ToDoNotFoundException;
import com.example.demo.persistence.domain.ToDo;
import com.example.demo.persistence.repo.ToDoRepo;
import com.example.demo.util.SpringBeanUtil;

@Service
public class ToDoService {

	private ToDoRepo repo;
	
	private ModelMapper mapper;

	private ToDoDto mapToDTO(ToDo toDo) {
		return this.mapper.map(toDo, ToDoDto.class);
	}

	@Autowired
	public ToDoService(ToDoRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	//create
	public ToDoDto create(ToDo toDo) {
		return this.mapToDTO(this.repo.save(toDo));
	}
	
	//readAll
	public List<ToDoDto> readAll(){
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	//readOne
	public ToDoDto readOne(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow(ToDoNotFoundException::new));
	}
	
	//update
	public ToDoDto update(ToDoDto dto, Long id) {
		ToDo toUpdate = this.repo.findById(id).orElseThrow(ToDoNotFoundException::new);
		toUpdate.setTodoTitle(dto.getTodoTitle());
		SpringBeanUtil.mergeNotNull(dto, toUpdate);
		return this.mapToDTO(this.repo.save(toUpdate));
	}
	
	//delete
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

	//find by name
	public List<ToDoDto> findByName(String name){
		return this.repo.findByName(name).stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
}