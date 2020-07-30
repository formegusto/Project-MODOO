package com.modoo.wrk.view;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.modoo.wrk.frame.FrameVO;
import com.modoo.wrk.frame.impl.FrameService;
import com.modoo.wrk.users.UsersVO;

@Controller
public class FrameController {
	@Autowired
	private FrameService frameService;
	
	@RequestMapping("frameService.do")
	public String frameService(HttpSession session,
			Model model) {
		FrameVO fvo = new FrameVO();
		
		fvo.setId(((UsersVO)session.getAttribute("user")).getId());
		
		model.addAttribute("frameList", frameService.getFrameList(fvo));
		
		return "frameService.jsp";
	}
	
	@RequestMapping("frameConfirm.do")
	public void frameConfirm(String iseqList) {
		System.out.println(iseqList);
		
		
	}
	
	@RequestMapping("frameMake.do")
	public void frameMake(FrameVO vo) {
		
	}
}
