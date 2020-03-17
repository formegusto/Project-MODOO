package com.crawler.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crawler.biz.user.UserVO;
import com.crawler.biz.user.impl.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	// 로그인 기능
	@RequestMapping(value="/login.do")
	public String login(UserVO vo, HttpSession session) {
		if(session.getAttribute("user") != null) {
			System.out.println("들어왔으면 반응좀");
			return "topHead.jsp";
		}
		
		System.out.println("[Spring Service MVC Framework] 로그인 기능 처리");
		UserVO user = userService.getUser(vo);
		if(user == null) {
			System.out.println("로그인 실패");
			return "login.jsp";
		} else {
			session.setAttribute("user", user);
			return "getInfoList.do";
		}
	}
	
	// 가입 기능
	@RequestMapping(value="/register.do")
	public String register(UserVO vo, HttpSession session) {
		if(session.getAttribute("user") != null)
			return "topHead.jsp";
		
		System.out.println("[Spring Service MVC Framework] 가입 기능 처리");
		userService.insertUser(vo);
		session.setAttribute("user", vo);
		return "getInfoList.do";
	}
}
