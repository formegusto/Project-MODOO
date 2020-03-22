package com.crawler.view.room;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crawler.biz.board.BoardVO;
import com.crawler.biz.board.impl.BoardService;
import com.crawler.biz.room.RoomVO;
import com.crawler.biz.room.impl.RoomService;

@Controller
public class RoomController {
	@Autowired
	RoomService roomService;
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value="/roomAdd_proc.do")
	public String roomAdd(RoomVO vo, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 채팅방 등록 기능 처리");
		roomService.insertRoom(vo);
		return "getBoardList.do";
	}
	
	@RequestMapping(value="/getRoom.do")
	public String getRoom(RoomVO vo, HttpSession session, Model model) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 채팅방 상세 조회 기능 처리");
		
		RoomVO room = roomService.getRoom(vo);
		BoardVO bvo = new BoardVO();
		bvo.setBseq(room.getBnum());
		
		model.addAttribute("board", boardService.getBoard(bvo));
		model.addAttribute("room", room);
		
		return "getRoom.jsp";
	}
	
	@RequestMapping(value="/getRoomList.do")
	public String getRoomList(RoomVO vo, HttpSession session, Model model) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 채팅방 조회 기능 처리");
		model.addAttribute("roomList", roomService.getRoomList(vo));
		return "getRoomList.jsp";
	}
}
