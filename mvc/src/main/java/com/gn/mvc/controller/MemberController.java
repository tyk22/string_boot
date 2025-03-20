package com.gn.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gn.mvc.dto.MemberDto;
import com.gn.mvc.entity.Member;
import com.gn.mvc.service.MemberService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	private final MemberService service;
	
	
	@PostMapping("/member/{id}/update")
	@ResponseBody
	public Map<String, String> memberUpdateApi(MemberDto param,
			HttpServletResponse response){
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("res_code", "500");
		resultMap.put("res_msg", "회원 수정중 오류가 발생하였습니다.");
		
		int result = service.updateMember(param);
		if(result >0) {
			// 어쩔 수 없이 비지니스 로직을 여기서 써야함
			// 쿠키(remember-me) 무효화
			Cookie rememberMe = new Cookie("remember-me",null);
			rememberMe.setMaxAge(0);
			rememberMe.setPath("/");
			response.addCookie(rememberMe);
			
			resultMap.put("res_code", "200");
			resultMap.put("res_msg", "회원이 수정되었습니다.");
		}
		
		return resultMap;
	}
	
	
	@GetMapping("/member/{id}/update")
	public String memberUpdateView(@PathVariable("id") Long id, Model model) {
		Member member = service.selectMemberOne(id);
		model.addAttribute("member",member);
		return "member/update";
	}
	
	@GetMapping("/login")
	public String loginView(
			@RequestParam(value="error",required=false) String error,
			@RequestParam(value="errorMsg",required=false) String errorMsg,
			Model model) {
		model.addAttribute("error",error);
		model.addAttribute("errorMsg",errorMsg);
		return "member/login";
	}
	
	@GetMapping("/signup")
	public String createMemberView() {
		return "member/create";
	}
	@PostMapping("/signup")
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
		// try~catch문은 service로 넣어야함
		try {
			if(service.createMember(dto)!=null) {
				resultMap.put("res_code", "200");
				resultMap.put("res_msg", "회원가입에 성공했습니다.");
			}
		}catch (Exception e) {
			resultMap.put("res_code", "500");
			resultMap.put("res_msg", "회원가입에 실패했습니다");
		}
		// try~catch문은 service로 넣어야함
		
		
		return resultMap;
	}
}
