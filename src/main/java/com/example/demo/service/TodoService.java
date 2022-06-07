package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TodoService {
	
	@Autowired
	private TodoRepository repository;
	
	public List<TodoEntity> create(final TodoEntity entity) {
	
		validate(entity);
		
		repository.save(entity);
		
		return repository.findByUserId(entity.getUserId());
	}
	
	public List<TodoEntity> retrieve(final String userId) {
		return repository.findByUserId(userId);
	}
	
	public List<TodoEntity> update(final TodoEntity entity) {
		// (1)
		validate(entity);
		
		// (2)
		Optional<TodoEntity> original = repository.findById(entity.getId());
		
		// (3)
		original.ifPresent(todo -> {
			todo.setTitle(entity.getTitle());
			todo.setDone(entity.isDone());
			repository.save(todo);
		});
		
		// 2.3.2
		return retrieve(entity.getUserId());
	}
	
	public List<TodoEntity> delete(final TodoEntity entity) {
		// (1)
		validate(entity);
		
		// (2)
		try {
			repository.delete(entity);
		} catch(Exception e) {
			log.error("error deleting entity ", entity.getId(), e);
			throw new RuntimeException("error deleting entity" + entity.getId());
		}
		
		return retrieve(entity.getUserId());
	}
	
	//¸®ÆÑÅä¸µ
	private void validate(final TodoEntity entity) {
		if(entity == null) {
			throw new RuntimeException("Entity cannot be null");
		}
		
		if(entity.getUserId() == null) {
			throw new RuntimeException("Unknown user.");
		}
	}

	
/*	
	@Autowired
	private TodoRepository repository;
	
	public String testService() {
		TodoEntity entity = TodoEntity.builder().title("ÃÖ½ÂÈÆ Ã¹ Å¸ÀÌÆ²").build();
		
		repository.save(entity);
		
		TodoEntity savedEntity = repository.findById(entity.getId()).get();
		
		//return "Test Service";
		return savedEntity.getTitle();
	}
	
	// 2022.03.28.¿ù °úÁ¦
	public TodoDTO testService2() {		
		TodoDTO savedTodoDTO = TodoDTO.builder().id("123").title("¹Ú»óÈñ").done(true).build();
		
		return savedTodoDTO;		
	}
*/	
}
