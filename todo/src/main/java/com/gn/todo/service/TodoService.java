package com.gn.todo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gn.todo.dto.PageDto;
import com.gn.todo.dto.SearchDto;
import com.gn.todo.dto.TodoDto;
import com.gn.todo.entity.Todo;
import com.gn.todo.repositor.TodoRepository;
import com.gn.todo.specification.TodoSpecification;

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
	

	public Todo updateTodo(Long id) {
		Todo result = null;
		try {
			
			Todo target = repository.findById(id).orElse(null);
			/*
			 * TodoDto dto = TodoDto.builder()
			 * 						.no(target.getNo())
			 * 						.content(target.getContent())
			 * 						.flag(target.getFlag())
			 * 						.build();
			 * if(target!=null){
			 * 		if("Y".equals(target.getFlag())) dto.setFlag("N");
			 * 		else dto.setFlag("Y");
			 * }
			 * return repository.save(dto.toEntity);
			 */
			
			
			char tests = target.getFlag();
			//System.out.println(target.getFlag());
			//System.out.println(tests);
			if(target!=null) {
				if(tests!='N') {
					target.setFlag('N');
				}else {
					target.setFlag('Y');
				}
			}
			result = repository.save(target);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/*
	 * public Todo createTodo(TodoDto dto){
	 * 		Todo entity = dto.toEntity();
	 * 		Todo result = repository.save(entity);
	 * 		return result;
	 * }
	 */
	
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
	
	public Page<Todo> selectTodoAll(SearchDto searchDto, PageDto pageDto){
		
		Pageable  pageable
				=PageRequest.of(pageDto.getNowPage()-1, pageDto.getNumPerPage());
		
		Specification<Todo> spec = (root,query,CriteriaBuilder) -> null;
		if(searchDto.getSearch_text()!=null) {
			spec = spec.and(TodoSpecification.todoContentContains(searchDto.getSearch_text()));
		}
		
		//list=repository.findByContentLike();
		
		return repository.findAll(spec,pageable);
	}
	
//	public Page<Todo> selectTodoAllTest(SearchDto searchDto, PageDto pageDto){
//		Pageable pageable = PageRequest.of(pageDto.getNowPage()-1, pageDto.getNumPerPage());
//		
//			pageable = PageRequest.of(pageDto.getNowPage()-1, pageDto.getNumPerPage());
//		
//		
//		Specification<Todo> spec = (root,query,CriteriaBuilder) -> null;
//
//		Page<Todo> list = repository.findAll(spec,pageable);
//		return list;
//	}
	
	
	
}
