package com.crawler.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crawler.biz.user.UserVO;
import com.crawler.biz.user.impl.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	//유저 조회(로그인)
	@RequestMapping(value="/login.do")
	public String login(UserVO vo, HttpSession session) {
		System.out.println("[Spring Service MVC Framework] 로그인 페이지 처리");
		UserVO user = userService.getUser(vo);
		if(user == null) {
			System.out.println("로그인실패");
			return "login.jsp";
		} else {
			session.setAttribute("user", user);
			return "getInfoList.do";
		}
	}
	
	//유저등록
	@RequestMapping(value="/register.do")
	public String register(UserVO vo, Model model) {
		System.out.println("[Spring Service MVC Framework] 로그인 페이지 처리");
		userService.insertUser(vo);
		model.addAttribute("user",vo);
		return "getInfoList.do";
	}
}
