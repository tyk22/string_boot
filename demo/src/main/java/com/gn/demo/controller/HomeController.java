package com.gn.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gn.demo.vo.Member;

@Controller
public class HomeController {
	
	@GetMapping({"","/"})
	public ModelAndView home() {
		// src/main/resources/templates/home.html
		ModelAndView mav = new ModelAndView();
		// 도달하고자 하는 위치
		mav.setViewName("home");
		// 보내주는 데이터
		mav.addObject("age",1);
		return mav;
	}
	
	
	// /test 안쓰고 {test} 되는 이유
	//@GetMapping("test") 되는 이유
	@GetMapping({"test"})
	public String test(Model model) {
		//request.setAttribute("name","김철수");
		model.addAttribute("name","김철수");
		return "test";
	}
//	@GetMapping("/bye")
//	public String bye(Model model) {
//		model.addAttribute("name","홍길동");
//		model.addAttribute("age","50");
//		//model.addAttribute("members");
//		return "/member/bye";
//	}
	
	@GetMapping("/bye")
	public String byes(Model model) {
		// 키값으로 member < 로 보냄
		model.addAttribute("member", new Member("홍길동", 50));
		return "/member/bye";
	}
}
