package com.modoo.wrk.view;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.modoo.wrk.frame.FrameVO;
import com.modoo.wrk.frame.impl.FrameService;
import com.modoo.wrk.tm.TmVO;
import com.modoo.wrk.tm.impl.TmService;
import com.modoo.wrk.users.UsersVO;
import com.modoo.wrk.visual.VisualVO;
import com.modoo.wrk.visual.impl.VisualService;

@Controller
public class BoardController {
	@Autowired
	private FrameService frameService;
	@Autowired
	private TmService tmService;
	@Autowired
	private VisualService visualService;
	
	@RequestMapping("boardMake.do")
	public String boardMake(HttpSession session,
			Model model) {
		UsersVO user = (UsersVO) session.getAttribute("user");
		
		FrameVO fvo = new FrameVO();
		TmVO tvo = new TmVO();
		VisualVO vvo = new VisualVO();
		
		fvo.setId(user.getId());
		tvo.setId(user.getId());
		vvo.setId(user.getId());
		
		model.addAttribute("frameList", frameService.getFrameList(fvo));
		model.addAttribute("tmList", tmService.getTmList(tvo));
		model.addAttribute("visualList", visualService.getVisualList(vvo));
		
		return "boardMake.jsp";
	}
}
