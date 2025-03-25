package com.gn.todo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gn.todo.dto.TodoDto;
import com.gn.todo.entity.Todo;
import com.gn.todo.repositor.TodoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {
	
	private final TodoRepository repository;
	
//	public Page<Todo> selectTodoAll(SearchDto searchDto, PageDto pageDto) {
//		
//		Pageable pageable = PageRequest.of(pageDto.getNowPage()-1, pageDto.getNumPerPage());
//		Specification<Todo> spec = (root,query,CriteriaBuilder) -> null;
//		spec = spec.and(TodoSpecification.todoContentContains(searchDto.getSearch_text()));
//		Page<Todo> list = repository.findAll(spec,pageable);
//		return list;
//	}
	public int createTodo(TodoDto dto) {
		int result = 0;
		try {
			
			Todo entity = dto.toEntity();
			repository.save(entity);
			result = 1;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteTodo(Long id) {
		int result = 0;
		try {
			Todo target = repository.findById(id).orElse(null);
			if(target != null) {
				repository.deleteById(id);
			}
			result = 1;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Todo> selectTodoAll( ){
		Specification<Todo> spec = (root,query,CriteriaBuilder) -> null;
		//spec = spec.and(TodoSpecification.todoContentContains());
		
		List<Todo> list =new ArrayList<Todo>();
		//list=repository.findByContentLike();
		list= repository.findAll(spec);
		return list;
	}
	
}
