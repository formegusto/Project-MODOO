package com.crawler.view.room;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crawler.biz.board.BoardVO;
import com.crawler.biz.board.impl.BoardService;
import com.crawler.biz.chat.ChatVO;
import com.crawler.biz.chat.impl.ChatService;
import com.crawler.biz.room.RoomVO;
import com.crawler.biz.room.impl.RoomService;

@Controller
public class RoomController {
	@Autowired
	RoomService roomService;
	@Autowired
	BoardService boardService;
	@Autowired
	ChatService chatService;
	
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
		
		ChatVO cvo = new ChatVO();
		cvo.setRnum(vo.getRnum());
		List<ChatVO> chatList = chatService.getChatList(cvo);
		
		model.addAttribute("board", boardService.getBoard(bvo));
		model.addAttribute("room", room);
		model.addAttribute("chatList", chatList);
		
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
