package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TestRequestBodyDTO;

@RestController
@RequestMapping("test") //url�� mapping �� �ش�. //test�� /test�� �ص� �ȴ�. //Ŭ���̾�Ʈ�� ��û�� Ŭ������ ����ȴ�.
public class TestController { //TestController : �ڹ� ������ �����Ѵ�. //Controller�� ��û�� �޴´�.

	@GetMapping
	public String testController() { //Hello World�� return �ϴ� �޼ҵ��. //test url�� ��û�ϸ� testController�� ����ȴ�. //Ŭ���̾�Ʈ�� Hello World�� �������� ���� �� �ִ�.
		return "Hello World";
	}
	
	@GetMapping("/{id}")
	public String testControllerWithPathVariable(@PathVariable int id) {
		return "Hello World! ID " + id;
	}
	
	@GetMapping("/testRequestParam")
	public String testControllerWithRequestParam(@RequestParam int id) {
		return "Hello World! ID " + id;
	}
	
	@GetMapping("/testRequestBody")
	public String testControllerWithRequestBody(@RequestBody TestRequestBodyDTO dto) {
		return "Hello World! ID " + dto.getId() + " Message : " + dto.getMessage();
	}
	
	@GetMapping("/testResponseBody")
	public ResponseDTO<String> testControllerResponseBody() {
		List<String> list = new ArrayList<String>();
		list.add("�ֽ���");
		list.add("�ֽ���2");
		
		//ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).error(null).build(); //error()�� ��ȣ �ȿ� null�̳� String�� �־� �൵ �ȴ�. //��ü �����Ǿ���.
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).error("error").build();
		
		return response;
	}
	
	@GetMapping("/testResponseEntity")
	public ResponseEntity<?> testControllerResponseEntity() {
		List<String> list = new ArrayList();
		list.add("Hello");
		
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		
		return ResponseEntity.badRequest().body(response); //ok�� ��ġ�� postman���� 400 Bad Request�� 200���� �� ���̴�.
	}
}
