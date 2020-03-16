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
	
	//���� ��ȸ(�α���)
	@RequestMapping(value="/login.do")
	public String login(UserVO vo, HttpSession session) {
		System.out.println("[Spring Service MVC Framework] �α��� ������ ó��");
		UserVO user = userService.getUser(vo);
		if(user == null) {
			System.out.println("�α��ν���");
			return "login.jsp";
		} else {
			session.setAttribute("user", user);
			return "getInfoList.do";
		}
	}
	
	//�������
	@RequestMapping(value="/register.do")
	public String register(UserVO vo, HttpSession session) {
		System.out.println("[Spring Service MVC Framework] �α��� ������ ó��");
		userService.insertUser(vo);
		session.setAttribute("user", vo);
		return "getInfoList.do";
	}
}
