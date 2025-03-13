package com.gn.mvc.dto;

import com.gn.mvc.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BoardDto {
	private Long board_no;
	private String board_title;
	private String board_content;
	
	// 1. BoardDto -> Board(Entity)
	public  Board toEntity() {
		return Board.builder()
				.boardTitle(board_title)
				.boardContent(board_content)
				.boardNo(board_no)
				.build();
	}
	
	
	// Entity는 데이터 베이스와 소통하기 위해
	// 2. Board(Entity) -> BoardDto 
	public BoardDto toDto(Board board) {
		return BoardDto.builder()
				.board_title(board.getBoardContent())
				.board_content(board.getBoardContent())
				.board_no(board.getBoardNo())
				.build();
	}
	
	
	
}
