package com.modoo.wrk.view;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.modoo.wrk.frame.FrameVO;
import com.modoo.wrk.frame.impl.FrameService;
import com.modoo.wrk.room.RHDVO;
import com.modoo.wrk.room.RoomVO;
import com.modoo.wrk.room.impl.RoomService;
import com.modoo.wrk.tm.TmVO;
import com.modoo.wrk.tm.impl.TmService;
import com.modoo.wrk.users.UsersVO;
import com.modoo.wrk.visual.VisualVO;
import com.modoo.wrk.visual.impl.VisualService;

@Controller
public class RoomController {
	@Autowired
	private RoomService roomService;
	@Autowired
	private FrameService frameService;
	@Autowired
	private TmService tmService;
	@Autowired
	private VisualService visualService;
	
	@RequestMapping(value = "roomMake.do", method = RequestMethod.POST)
	public String roomMake_proc(RoomVO rvo, FrameVO fvo,
			TmVO tvo, VisualVO vvo, HttpSession session) {
		UsersVO user = (UsersVO) session.getAttribute("user");
		
		FrameVO frame = frameService.getFrame(fvo);
		TmVO tm = tmService.getTm(tvo);
		VisualVO visual = visualService.getVisual(vvo);
		rvo.setId(user.getId());
		roomService.insertRoom(rvo);
		
		RHDVO frameRHD = new RHDVO();
		RHDVO tmRHD = new RHDVO();
		RHDVO visualRHD = new RHDVO();
		
		int rseq = roomService.getRoomTop(rvo);
		
		frameRHD.setRseq(rseq);
		frameRHD.setSeq(frame.getFseq());
		frameRHD.setType("frame");
		tmRHD.setRseq(rseq);
		tmRHD.setSeq(tm.getTseq());
		tmRHD.setType("tm");
		visualRHD.setRseq(rseq);
		visualRHD.setSeq(visual.getVseq());
		visualRHD.setType("visual");
		
		roomService.insertRHD(frameRHD);
		roomService.insertRHD(tmRHD);
		roomService.insertRHD(visualRHD);
		
		return "redirect:roomService.do";
	}
	
	@RequestMapping(value = "roomService.do")
	public String roomService(Model model) {
		model.addAttribute("roomList", roomService.getRoomList());
		
		return "roomService.jsp";
	}
	
	
}
