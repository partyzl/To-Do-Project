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

import com.example.demo.dto.ToDoDto;
import com.example.demo.persistence.domain.ToDo;
import com.example.demo.service.ToDoService;

@RestController
@CrossOrigin
@RequestMapping("/todo")
public class ToDoController {
	
	ToDoService service;

	@Autowired
	public ToDoController(ToDoService service) {
		super();
		this.service = service;
	}
	
	//create
	@PostMapping("/create")
	public ResponseEntity<ToDoDto> create(@RequestBody ToDo toDo){
		ToDoDto created = this.service.create(toDo);
		
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}
	
	//readAll
	@GetMapping("/read")
	public ResponseEntity<List<ToDoDto>> read(){
		return ResponseEntity.ok(this.service.readAll());
	}
	
	//readOne
	@GetMapping("/read/{id}")
	public ResponseEntity<ToDoDto> readOne(@PathVariable Long id){
		return ResponseEntity.ok(this.service.readOne(id));
	}
	
	//update
	@PutMapping("/update/{id}")
	public ResponseEntity<ToDoDto> update(@PathVariable Long id, @RequestBody ToDoDto dto){
		return new ResponseEntity<>(this.service.update(dto, id),HttpStatus.ACCEPTED);
	}
	
	//delete
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ToDoDto> delete(@PathVariable Long id){
		//return no content if deleted successfully
		return this.service.delete(id)?new ResponseEntity<>(HttpStatus.NO_CONTENT)
				//if this record isnt found, internal server error
				:new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//find by name
	@GetMapping("/findByName/{name}")
	public ResponseEntity <List<ToDoDto>> findByName(@PathVariable String name){
		return ResponseEntity.ok(this.service.findByName(name));
	}
	
	

}