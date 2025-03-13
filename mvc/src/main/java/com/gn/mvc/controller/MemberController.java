package com.gn.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gn.mvc.dto.MemberDto;
import com.gn.mvc.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	private final MemberService service;
	
	@GetMapping("/member/create")
	public String createMemberView() {
		return "member/create";
	}
	@PostMapping("/member")
	@ResponseBody
	public Map<String, String> createMemberApi(MemberDto dto){
		Map<String, String> resultMap= new HashMap<String,String>();
//		resultMap.put("res_code", "500");
//		resultMap.put("res_msg", "회원가입에 실패했습니다");
//		
//		if(service.createMember(dto)!=null) {
//		resultMap.put("res_code", "400");
//		resultMap.put("res_msg", "회원가입에 성공했습니다.");
//		}
//		else {
//		resultMap.put("res_code", "500");
//		resultMap.put("res_msg", "회원가입에 실패했습니다");
//		}
		// fk값 중복일때 예외처리 Exception 로 넘김
		try {
			if(service.createMember(dto)!=null) {
				resultMap.put("res_code", "400");
				resultMap.put("res_msg", "회원가입에 성공했습니다.");
			}
		}catch (Exception e) {
			resultMap.put("res_code", "500");
			resultMap.put("res_msg", "회원가입에 실패했습니다");
		}
		
		return resultMap;
	}
}
