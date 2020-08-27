package com.modoo.wrk.view;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.modoo.wrk.users.UsersVO;
import com.modoo.wrk.users.impl.UsersService;

@Controller
public class UsersController {
	@Autowired
	UsersService usersService;
	
	// 로그인 기능
	@RequestMapping(value="/signin.do")
	public String signin(UsersVO vo, HttpSession session) {
		System.out.println("[UsersController] Signin Welcome!");
		UsersVO user = usersService.signinUser(vo);
		if(user == null) {
			return "redirect:account.jsp";
		} else {
			session.setAttribute("user", user);
			return "redirect:infoService.do";
		}
	}
	
	// 가입 기능
	@RequestMapping(value="/signup.do")
	public String signup(UsersVO vo, HttpSession session) {
		System.out.println("[UsersController] Signup THX :)");
		usersService.signupUser(vo);
		return "redirect:infoService.do";
	}
	
	// 로그아웃 기능
	@RequestMapping(value="/signout.do")
	public String signin(HttpSession session) {
		System.out.println("[UsersController] SignOut Bye");
		session.removeAttribute("user");
				
		return "redirect:account.jsp";
	}
}
