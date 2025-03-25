package com.gn.todo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gn.todo.dto.SearchDto;
import com.gn.todo.dto.TodoDto;
import com.gn.todo.entity.Todo;
import com.gn.todo.service.TodoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final TodoService service;
	@GetMapping({"","/"})
	public String home(Model model, SearchDto searchDto) {
		List<Todo> resultList = service.selectTodoAll();		
		
		model.addAttribute("todoList",resultList);
		return "/home";
	}
	
	@PostMapping("/home")
	@ResponseBody
	public Map<String, String> createTodoApi(
			TodoDto dto){
		Map<String,String>resultMap = new HashMap<String, String>()	;
		System.out.println(dto.getContent());
		resultMap.put("res_code", "500");
		resultMap.put("res_msg", "추가 오류");
		int result = service.createTodo(dto);
		if(result>0) {
			resultMap.put("res_code", "200");
			resultMap.put("res_msg", "추가 성공");
		}
		return resultMap;
	}
	
	@DeleteMapping("/home/{id}")
	@ResponseBody
	public Map<String, String> deleteTodoApi(
			@PathVariable("id") Long id){
		
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("res_code", "500");
		resultMap.put("res_msg", "삭제 오류");
		
		int result = service.deleteTodo(id);
		if(result>0) {
			resultMap.put("res_code", "200");
			resultMap.put("res_msg", "삭제 성공");
		}
		return resultMap;
	}
//	@PostMapping("/todo")
//	@ResponseBody
//	public String home(Model model, SearchDto searchDto, PageDto pageDto) {
//		
//		Page<Todo> resultList = service.selectTodoAll(searchDto, pageDto);
//		model.addAttribute("todoList",resultList);
//		return "/home";
//	}
}
