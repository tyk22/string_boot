package com.gn.mvc.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gn.mvc.entity.Board;
import com.gn.mvc.entity.Member;

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
	private Long board_writer;
	private LocalDateTime reg_date;
	private LocalDateTime mod_date;
	
	private List<Long> delete_files;
	
	private List<MultipartFile> files;
	// 1. BoardDto -> Board(Entity)
	public  Board toEntity() {
		return Board.builder()
				.boardTitle(board_title)
				.boardContent(board_content)
				.boardNo(board_no)
				// 빌더로 약간 조인하는 느낌으로 ?
				.member(Member.builder().memberNo(board_writer).build())
				.build();
	}
	
	
	// Entity는 데이터 베이스와 소통하기 위해
	// 2. Board(Entity) -> BoardDto 
	public BoardDto toDto(Board board) {
		return BoardDto.builder()
				.board_title(board.getBoardContent())
				.board_content(board.getBoardContent())
				.board_no(board.getBoardNo())
				.reg_date(board.getRegDate())
				.mod_date(board.getModDate())
				.build();
	}
	
	
	
}
