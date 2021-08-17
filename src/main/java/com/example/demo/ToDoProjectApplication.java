package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ToDoProjectApplication {

	public static void main(String[] args) {
		ApplicationContext beanBags = SpringApplication.run(ToDoProjectApplication.class, args);
		System.out.println(beanBags.getBean("time", String.class));
	}

}
