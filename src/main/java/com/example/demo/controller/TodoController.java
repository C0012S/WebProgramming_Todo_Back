package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.service.TodoService;

@RestController
@RequestMapping("todo")
public class TodoController {

	@Autowired //�ڵ� ���Եȴ�.
	private TodoService service;
	
	@PostMapping
//	public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {
	public ResponseEntity<?> createTodo(
			@AuthenticationPrincipal String userId, 
			@RequestBody TodoDTO dto) {
		try {
			//String temporaryUserId = "temporary-user"; // temporary user id.
			
			// (1) TodoEntity�� ��ȯ�Ѵ�.
			TodoEntity entity = TodoDTO.toEntity(dto);
			
			// (2) id�� null�� �ʱ�ȭ�Ѵ�. ���� ��ÿ��� id�� ����� �ϱ� �����̴�.
			entity.setId(null);
			
			// (3) �ӽ� ����� ���̵� ������ �ش�. �� �κ��� 4 �� ������ �ΰ����� ������ �����̴�. ������ ������ �ΰ� ����� �����Ƿ� �� �����(temporary-user)�� �α��� ���� ����� �� �ִ� ���ø����̼��� ���̴�.
			//entity.setUserId(temporaryUserId);
			entity.setUserId(userId); // ���� temporary-user ��� @AuthenticationPrincipal���� �Ѿ�� userId�� ������ �ش�.
			
			// (4) ���񽺸� �̿��� Todo ��ƼƼ�� �����Ѵ�.
			List<TodoEntity> entities = service.create(entity);
			
			// (5) �ڹ� ��Ʈ���� �̿��� ���ϵ� ��ƼƼ ����Ʈ�� TodoDTO ����Ʈ�� ��ȯ�Ѵ�.
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			
			// (6) ��ȯ�� TodoDTO ����Ʈ�� �̿��� ResponseDTO�� �ʱ�ȭ�Ѵ�.
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
			
			// (7) ResponseDTO�� �����Ѵ�.
			return ResponseEntity.ok().body(response);			
		} catch(Exception e) {
			// (8) Ȥ�� ���ܰ� �ִ� ��� dto ��� error�� �޽����� �־� �����Ѵ�.
			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping
//	public ResponseEntity<?> retrieveTodoList() {
	public ResponseEntity<?> retrieveTodoList(@AuthenticationPrincipal String userId) {
		//String temporaryUserId = "temporary-user"; // temporary user id.
		
		// (1) ���� �޼����� retrieve() �޼��带 ����� Todo ����Ʈ�� �����´�.
		//List<TodoEntity> entities = service.retrieve(temporaryUserId);
		List<TodoEntity> entities = service.retrieve(userId);
		
		// (2) �ڹ� ��Ʈ���� �̿��� ���ϵ� ��ƼƼ ����Ʈ�� TodoDTO ����Ʈ�� ��ȯ�Ѵ�.
		List<TodoDTO> dtos = entities.stream().map((e)->(new TodoDTO(e))).collect(Collectors.toList());
		
		// (3) ��ȯ�� TodoDTO ����Ʈ�� �̿��� ResponseDTO�� �ʱ�ȭ�Ѵ�.
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
		
		// (4) ResponseDTO�� �����Ѵ�.
		return ResponseEntity.ok().body(response);
	}
	
	@PutMapping
//	public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto) {
	public ResponseEntity<?> updateTodo(
			@AuthenticationPrincipal String userId,
			@RequestBody TodoDTO dto) {
		//String temporaryUserId = "temporary-user";
		
		// (1)
		TodoEntity entity = TodoDTO.toEntity(dto);
		
		// (2)
		//entity.setUserId(temporaryUserId);
		entity.setUserId(userId);
		
		// (3)
		List<TodoEntity> entities = service.update(entity);
		
		// (4)
		List<TodoDTO> dtos = entities.stream().map((e)->(new TodoDTO(e))).collect(Collectors.toList());
		
		// (5)
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
		
		// (6)
		return ResponseEntity.ok().body(response);		
	}
	
	@DeleteMapping
//	public ResponseEntity<?> deleteTodo(@RequestBody TodoDTO dto) {
	public ResponseEntity<?> deleteTodo(
			@AuthenticationPrincipal String userId,
			@RequestBody TodoDTO dto) {
		try {
			//String temporaryUserId = "temporary-user";
			
			TodoEntity entity = TodoDTO.toEntity(dto);
			//entity.setUserId(temporaryUserId);
			entity.setUserId(userId);
			
			List<TodoEntity> entities = service.delete(entity);
			
			// (4)
			List<TodoDTO> dtos = entities.stream().map((e)->(new TodoDTO(e))).collect(Collectors.toList());
			
			// (5)
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
			
			// (6)
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			// (7)
			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}

	
/*	
	@Autowired //�ڵ� ���Եȴ�.
	TodoService service;
	
	@GetMapping("/test")
	public ResponseEntity<?> testTodo() {
		String str = service.testService();
		
		List<String> list = new ArrayList();
		list.add(str);
		
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		
		return ResponseEntity.ok().body(response);
	}

	// 2022.03.28.�� ����
	@Autowired
	TodoService service2;
	
	@GetMapping("/testTodo2")
	public ResponseEntity<?> testTodo2() {
		TodoDTO dto = service2.testService2();
		
		List<TodoDTO> list2 = new ArrayList();
		list2.add(dto);
		
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(list2).build();
		
		return ResponseEntity.ok().body(response);
	}
*/	
}
