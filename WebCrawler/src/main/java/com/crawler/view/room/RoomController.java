package com.crawler.view.room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crawler.biz.board.BoardVO;
import com.crawler.biz.board.impl.BoardService;
import com.crawler.biz.chat.ChatVO;
import com.crawler.biz.chat.impl.ChatService;
import com.crawler.biz.data.DataVO;
import com.crawler.biz.data.impl.DataService;
import com.crawler.biz.info.InfoVO;
import com.crawler.biz.info.impl.InfoService;
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
	@Autowired
	InfoService	infoService;
	@Autowired
	DataService dataService;
	
	@RequestMapping(value="/roomAdd_proc.do")
	public String roomAdd(RoomVO vo, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 채팅방 등록 기능 처리");
		System.out.println(vo);
		roomService.insertRoom(vo);
		return "getRoomList.do";
	}
	
	@RequestMapping(value="/deleteRoom.do")
	public String deleteRoom(RoomVO vo, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 채팅방 삭제 기능 처리");
		roomService.deleteRoom(vo);
		return "getRoomList.do";
	}
	
	/*
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
		
		// infoList Make
		List<BoardHaveInfoVO> bhiList = boardService.getBHIList(bvo);
		List<InfoVO> infoList = new ArrayList<InfoVO>();
		for(BoardHaveInfoVO bhi : bhiList) {
			InfoVO ivo = new InfoVO();
			ivo.setSeq(bhi.getInum());
			infoList.add(infoService.getInfo(ivo));
		}
		
		// dataMap Make
		Map<String, List<DataVO>> dataMap = new HashMap<String, List<DataVO>>();
		for(BoardHaveInfoVO bhi : bhiList) {
				DataVO dvo = new DataVO();
				dvo.setInum(bhi.getInum());
				List<DataVO> dataList = dataService.getData(dvo);
				dataMap.put( bhi.getInum()+"" , dataList);
		}
		
		model.addAttribute("dataMap", dataMap);
		model.addAttribute("infoList", infoList);
		model.addAttribute("board", boardService.getBoard(bvo));
		model.addAttribute("room", room);
		model.addAttribute("chatList", chatList);
		
		return "getRoom.jsp";
	}
	*/
	
	@RequestMapping(value="/getRoomList.do")
	public String getRoomList(RoomVO vo, HttpSession session, Model model) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 채팅방 조회 기능 처리");
		model.addAttribute("roomList", roomService.getRoomList(vo));
		return "getRoomList.jsp";
	}
}
