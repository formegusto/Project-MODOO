package com.modoo.wrk.view;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.modoo.wrk.frame.FrameVO;
import com.modoo.wrk.frame.impl.FrameService;
import com.modoo.wrk.tm.TmVO;
import com.modoo.wrk.tm.impl.TmService;
import com.modoo.wrk.users.UsersVO;
import com.modoo.wrk.visual.VisualVO;
import com.modoo.wrk.visual.impl.VisualService;
import com.modoo.wrk.board.BHDVO;
import com.modoo.wrk.board.BoardVO;
import com.modoo.wrk.board.impl.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private FrameService frameService;
	@Autowired
	private TmService tmService;
	@Autowired
	private VisualService visualService;
	
	@RequestMapping(value = "boardMake.do", method = RequestMethod.GET)
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
	
	@RequestMapping(value = "boardMake.do", method = RequestMethod.POST)
	public String boardMake_proc(BoardVO bvo, FrameVO fvo,
			TmVO tvo, VisualVO vvo, HttpSession session) {
		UsersVO user = (UsersVO) session.getAttribute("user");
		
		FrameVO frame = frameService.getFrame(fvo);
		TmVO tm = tmService.getTm(tvo);
		VisualVO visual = visualService.getVisual(vvo);
		bvo.setId(user.getId());
		boardService.insertBoard(bvo);
		
		BHDVO frameBHD = new BHDVO();
		BHDVO tmBHD = new BHDVO();
		BHDVO visualBHD = new BHDVO();
		
		int bseq = boardService.getBoardTop(bvo);
		
		frameBHD.setBseq(bseq);
		frameBHD.setSeq(frame.getFseq());
		frameBHD.setType("frame");
		tmBHD.setBseq(bseq);
		tmBHD.setSeq(tm.getTseq());
		tmBHD.setType("tm");
		visualBHD.setBseq(bseq);
		visualBHD.setSeq(visual.getVseq());
		visualBHD.setType("visual");
		
		boardService.insertBHD(frameBHD);
		boardService.insertBHD(tmBHD);
		boardService.insertBHD(visualBHD);
		
		return "redirect:boardService.do";
	}
	
	@RequestMapping(value = "boardService.do")
	public String boardService(Model model) {
		model.addAttribute("boardList", boardService.getBoardList());
		
		return "boardService.jsp";
	}
}
