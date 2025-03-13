package com.gn.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gn.mvc.dto.BoardDto;
import com.gn.mvc.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor // 3번 - "lombok" 이 대신 만들어줌
public class BoardController {
	// 1. 필드 주입 방법 - 순환 참조 생길 가능성 있음(무한루프)
//	@Autowired
//	BoardService service;
	
	// 2. 메소드 주입 방법 (Setter) - 불변성이 보장되자 않음
//	private BoardService service;
//	@Autowired
//	public void setBoardService(BoardService service) {
//		this.service = service;
//	}
	
	// 3. 생성자 주입 방법 + final 
	private final BoardService service;
	// @RequiredArgsConstructor 로 [lombok이 만들어줌]
//	@Autowired  
//	public BoardController(BoardService service) {
//		this.service = service;
//	}
	
	
	// 하이퍼레퍼런스
	@GetMapping("/board/create")
	public String createBoardView() {
		
		return "board/create";
	}
	@PostMapping("/board")
	@ResponseBody
	public Map<String, String> createBoardApi(
			// 데이터 보내주기 
			// 1번째 방법
//			@RequestParam("board_title") String boardTitle,
//			@RequestParam("board_content") String boardContent
			// 2번째 방법
//			@RequestParam Map<String,String> param
			// 3번째 방법
			BoardDto dto
			) {
		Map<String, String> resultMap = new HashMap<String,String>();
		resultMap.put("res_code", "500");
		resultMap.put("res_msg", "게시글 등록중 오류가 발생하였습니다.");
		System.out.println(dto);
		// Service가 가지고 있는 createBoard 메소드 호출
		service.createBoard(dto);
		
		return resultMap;
	}
}
