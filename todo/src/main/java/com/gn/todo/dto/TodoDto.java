package com.gn.todo.dto;

import com.gn.todo.entity.Todo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TodoDto {
	private Long no;
	private String content;
	private char flag;
	// private String flag ="N";
	public Todo toEntity() {
		return Todo.builder()
				.no(no)
				.content(content)
				.flag(flag)
				.build();
	}
	
	public TodoDto toDto(Todo todo) {
		return TodoDto.builder()
				.no(no)
				.content(content)
				.flag(flag)
				.build();
	}
}
