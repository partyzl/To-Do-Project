package com.example.demo.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ToDo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String todoTitle;
	
	@ManyToOne
	private User user;

	public ToDo(Long id, String todoTitle) {
		super();
		this.id = id;
		this.todoTitle = todoTitle;
		
	}

	public ToDo(String todoTitle) {
		super();
		this.todoTitle = todoTitle;
		
	}
	
	
}