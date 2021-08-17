package com.example.demo.persistence.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	@Min(1)
	@Max(120)
	private int age;
	
	@OneToMany(mappedBy = "user", fetch= FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<ToDo> toDos;

	public User(Long id, String name, @Min(1) @Max(120) int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		
	}

	public User(String name, @Min(1) @Max(120) int age) {
		super();
		this.name = name;
		this.age = age;
		
	}
	
	
	
}
