package com.modoo.wrk.view;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.modoo.wrk.comment.CommentVO;
import com.modoo.wrk.comment.impl.CommentService;
import com.modoo.wrk.users.UsersVO;

@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@RequestMapping("commentService.do")
	public String commentService(CommentVO vo, HttpSession session) {
		UsersVO user = (UsersVO) session.getAttribute("user");
		
		vo.setId(user.getId());
		
		commentService.insertComment(vo);
		
		return "redirect:boardDetailService.do?bseq=" + vo.getBseq();
	}
}
