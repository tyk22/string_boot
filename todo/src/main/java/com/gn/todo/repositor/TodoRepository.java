package com.gn.todo.repositor;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.gn.todo.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>
	,JpaSpecificationExecutor<Todo>{
	
	//List<Todo> findAll(Specification<Todo> spec, Pageable pageable);
//	List<Todo> findByAll(Specification<Todo> spec,Pageable pageable );
//	
//	List<Todo> findByTodoContentContaining(String keyword);
//
	
//	@Query(value="SELECT t FROM Todo t WHERE t.content LIKE CONCAT('%',?1,'%')")
//	List<Todo> findByContentLike(String keyword);		
}
