package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.persistence.domain.ToDo;
import com.example.demo.persistence.repo.ToDoRepo;

@SpringBootTest 
@ActiveProfiles("dev")
public class ToDoServiceUnitTest {
	@Autowired
	private ToDoService service;
	@MockBean
	private ToDoRepo repo;
	@Autowired
	private ModelMapper mapper;
	 
	
	private ToDo SavedToDo = new ToDo(1L, "Shopping");
	private ToDo toDo = new ToDo("Shopping");
   private List<ToDo> TEST_TODO_1 = List.of(toDo);

	@Test
	void testCreate() {
		Mockito.when(this.repo.save(toDo)).thenReturn(SavedToDo);
		
		Assertions.assertThat(this.service.create(this.toDo).getId()).isEqualTo(SavedToDo.getId());
		 Assertions.assertThat(this.service.create(this.toDo).getTodoTitle()).isEqualTo(SavedToDo.getTodoTitle());

		 
		 Mockito.verify(this.repo, Mockito.times(2)).save(this.toDo);
	}
	
	@Test
	void testReadAll() {
		Mockito.when(this.repo.findAll()).thenReturn(TEST_TODO_1);

		 Assertions.assertThat(this.service.readAll().get(0).getId()).isEqualTo(TEST_TODO_1.get(0).getId());
		 Assertions.assertThat(this.service.readAll().get(0).getTodoTitle()).isEqualTo(TEST_TODO_1.get(0).getTodoTitle());

		  Mockito.verify(this.repo, Mockito.times(2)).findAll();
		    
	}
	@Test
	void testReadOne() {
		
		Mockito.when(this.repo.findById(1L)).thenReturn(Optional.of(SavedToDo));

		 Assertions.assertThat(this.service.readOne(1l).getId()).isEqualTo(SavedToDo.getId());
		 Assertions.assertThat(this.service.readOne(1l).getTodoTitle()).isEqualTo(SavedToDo.getTodoTitle());
	  Mockito.verify(this.repo, Mockito.times(2)).findById(1l);
		    
	}
	@Test
	void testDelete() {
		Mockito.when(this.repo.existsById(toDo.getId())).thenReturn(true);
		
		Assertions.assertThat(this.service.delete(this.toDo.getId())).isEqualTo(false);

		 Mockito.verify(this.repo, Mockito.times(1)).deleteById(this.toDo.getId());
	}
	


}
