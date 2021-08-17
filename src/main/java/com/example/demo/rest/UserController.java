package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;
import com.example.demo.persistence.domain.User;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	
	UserService service;

	@Autowired
	public UserController(UserService service) {
		super();
		this.service = service;
	}
	
	//create
	@PostMapping("/create")
	public ResponseEntity<UserDto> create(@RequestBody User user){
		UserDto created = this.service.create(user);
		
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}
	
	//read all
	@GetMapping("/read")
	public ResponseEntity<List<UserDto>> read(){
		return ResponseEntity.ok(this.service.readAll());
	}
	
	//read one
	@GetMapping("/read/{id}")
	public ResponseEntity<UserDto> readOne(@PathVariable Long id){
		return ResponseEntity.ok(this.service.readOne(id));
	}
	
	//update
	@PutMapping("/update/{id}")
	public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody UserDto dto){
		return new ResponseEntity<>(this.service.update(dto, id), HttpStatus.ACCEPTED);
	}
	
	//delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<UserDto> delete(@PathVariable Long id){
		return this.service.delete(id)?new ResponseEntity<>(HttpStatus.NO_CONTENT)
				:new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	//read by name
	@GetMapping("/findByName/{name}")
	public ResponseEntity<List<UserDto>> readByName(@PathVariable String name){
		return ResponseEntity.ok(this.service.findByName(name));
	}
}