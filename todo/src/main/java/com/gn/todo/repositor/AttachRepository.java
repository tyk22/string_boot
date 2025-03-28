package com.gn.todo.repositor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gn.todo.entity.Attach;

@Repository
public interface AttachRepository extends JpaRepository<Attach, Long>{

	
}
