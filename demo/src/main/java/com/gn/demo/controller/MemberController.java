package com.gn.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gn.demo.vo.Member;

@Controller
public class MemberController {
	
	@GetMapping("/member")
	public String selectMeberList(Model model) {
		List<Member> resultList = new ArrayList<Member>();
		// Member vo에 객체 2개 넣음 ( 이름 , 나이 ) 작성해서 List에 추가
		resultList.add(new Member("김철수",25));
		resultList.add(new Member("이영희",7));
		// 어떤 이름으로 뭐를 보낼꺼냐
		model.addAttribute("members",resultList);
		
		return "/member/list";
	}

}
