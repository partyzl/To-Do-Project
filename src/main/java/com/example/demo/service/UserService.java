package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.exceptions.UserNotFoundExceptions;
import com.example.demo.persistence.domain.User;
import com.example.demo.persistence.repo.UserRepo;
import com.example.demo.util.SpringBeanUtil;

@Service
public class UserService {

	//business logic - crud
	
	private UserRepo repo;
	
	private ModelMapper mapper;
	
	private UserDto mapToDto(User user) {
		return this.mapper.map(user, UserDto.class);
	}
	
	
	@Autowired
	public UserService(UserRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}



	//Create
	public UserDto create(User user) {
		return this.mapToDto(this.repo.save(user));
	}
	
	//Read All
	public List<UserDto> readAll(){
		return this.repo.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
		
	}
	
	//Read One
	public UserDto readOne(Long id) {
		return this.mapToDto(this.repo.findById(id).orElseThrow(UserNotFoundExceptions::new));
	}
	
	//update
	public UserDto update(UserDto userDto, Long id) {
		User toUpdate = this.repo.findById(id).orElseThrow(UserNotFoundExceptions::new);
		toUpdate.setName(userDto.getName());
		SpringBeanUtil.mergeNotNull(userDto, toUpdate);
		return this.mapToDto(this.repo.save(toUpdate));
		
	}
	
	//delete
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
	
	//find by name
	public List<UserDto> findByName(String name){
		return this.repo.findByName(name).stream().map(this::mapToDto).collect(Collectors.toList());
	}
}