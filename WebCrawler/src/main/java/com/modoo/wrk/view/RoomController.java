package com.modoo.wrk.view;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.modoo.wrk.data.SearchVO;
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
	public String roomMake_proc(RoomVO rvo, @RequestParam(defaultValue = "0" ) int fseq,
			@RequestParam(defaultValue = "0" ) int tseq, @RequestParam(defaultValue = "0" ) int vseq, HttpSession session) {
		UsersVO user = (UsersVO) session.getAttribute("user");
		
		rvo.setId(user.getId());
		roomService.insertRoom(rvo);
		int rseq = roomService.getRoomTop(rvo);
		
		if(fseq != 0) {
			FrameVO fvo = new FrameVO();
			fvo.setFseq(fseq);
			
			FrameVO frame = frameService.getFrame(fvo);
			RHDVO frameRHD = new RHDVO();
			
			frameRHD.setRseq(rseq);
			frameRHD.setSeq(frame.getFseq());
			frameRHD.setType("frame");
			
			roomService.insertRHD(frameRHD);
		}
		
		if(tseq != 0) {
			TmVO tvo = new TmVO();
			tvo.setTseq(tseq);
			
			TmVO tm = tmService.getTm(tvo);
			RHDVO tmRHD = new RHDVO();
			
			tmRHD.setRseq(rseq);
			tmRHD.setSeq(tm.getTseq());
			tmRHD.setType("tm");
			
			roomService.insertRHD(tmRHD);
		}
		
		if(vseq != 0) {
			VisualVO vvo = new VisualVO();
			vvo.setVseq(vseq);
			
			VisualVO visual = visualService.getVisual(vvo);
			RHDVO visualRHD = new RHDVO();
			
			visualRHD.setRseq(rseq);
			visualRHD.setSeq(visual.getVseq());
			visualRHD.setType("visual");
			
			roomService.insertRHD(visualRHD);
		}
		
		return "redirect:roomService.do";
	}
	
	@RequestMapping(value = "roomService.do")
	public String roomService(Model model, String keyword) {
		if(keyword == null) {
			model.addAttribute("roomList", roomService.getRoomList());
		} else {
			SearchVO svo = new SearchVO();
			svo.setKeyword(keyword);
			
			model.addAttribute("rootList", roomService.getRoomSearch(svo));
		}
		
		return "roomService.jsp";
	}
	
	
}
