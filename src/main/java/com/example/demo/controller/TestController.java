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
@RequestMapping("test") //url과 mapping 해 준다. //test는 /test로 해도 된다. //클라이언트의 요청과 클래스가 연결된다.
public class TestController { //TestController : 자바 빈으로 동작한다. //Controller가 요청을 받는다.

	@GetMapping
	public String testController() { //Hello World를 return 하는 메소드다. //test url로 요청하면 testController가 실행된다. //클라이언트가 Hello World를 응답으로 받을 수 있다.
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
		list.add("최승훈");
		list.add("최승훈2");
		
		//ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).error(null).build(); //error()의 괄호 안에 null이나 String을 넣어 줘도 된다. //객체 생성되었다.
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).error("error").build();
		
		return response;
	}
	
	@GetMapping("/testResponseEntity")
	public ResponseEntity<?> testControllerResponseEntity() {
		List<String> list = new ArrayList();
		list.add("Hello");
		
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		
		return ResponseEntity.badRequest().body(response); //ok로 고치면 postman에서 400 Bad Request가 200으로 뜰 것이다.
	}
}
