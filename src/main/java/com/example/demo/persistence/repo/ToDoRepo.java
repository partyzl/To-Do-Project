  
package com.example.demo.persistence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.persistence.domain.ToDo;

@Repository
public interface ToDoRepo extends JpaRepository<ToDo, Long> {
	
	//findAllByName
		@Query(value = "SELECT * FROM TO_DO WHERE NAME =?1", nativeQuery = true)
		List<ToDo> findByName(String name);

}