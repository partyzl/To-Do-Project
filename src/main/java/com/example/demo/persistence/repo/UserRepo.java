package com.example.demo.persistence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.persistence.domain.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	
	//findAllByName
			@Query(value = "SELECT * FROM USER WHERE NAME =?1", nativeQuery = true)
			List<User> findByName(String name);

}
