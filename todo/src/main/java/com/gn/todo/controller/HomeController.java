package com.gn.todo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gn.todo.dto.SearchDto;
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
	
//	@PostMapping("/todo")
//	@ResponseBody
//	public String home(Model model, SearchDto searchDto, PageDto pageDto) {
//		
//		Page<Todo> resultList = service.selectTodoAll(searchDto, pageDto);
//		model.addAttribute("todoList",resultList);
//		return "/home";
//	}
}
