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
import com.crawler.biz.info.FrameHaveInfoVO;
import com.crawler.biz.info.FrameVO;
import com.crawler.biz.info.InfoVO;
import com.crawler.biz.info.impl.FrameService;
import com.crawler.biz.info.impl.InfoService;
import com.crawler.biz.room.RoomVO;
import com.crawler.biz.room.impl.RoomService;
import com.crawler.biz.tm.TmHaveInfoVO;
import com.crawler.biz.tm.TmVO;
import com.crawler.biz.tm.impl.TmService;
import com.crawler.biz.visual.VisualHaveInfoVO;
import com.crawler.biz.visual.VisualVO;
import com.crawler.biz.visual.impl.VisualService;

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
	@Autowired
	FrameService frameService;
	@Autowired
	VisualService visualService;
	@Autowired
	TmService tmService;
	
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
		BoardVO board = boardService.getBoard(bvo);
		
		if(board.getBtype().equals("frame")) {
			FrameVO fvo = new FrameVO();
			fvo.setFseq(vo.getBnum());
			List<FrameHaveInfoVO> fhiList = frameService.getFHIList(fvo);
			List<InfoVO> infoList = new ArrayList<InfoVO>();
			Map<String, List<DataVO>> dataMap = new HashMap<String, List<DataVO>>();
			FrameVO frame = frameService.getFrame(fvo);
			
			// 0-1. infoList 구축
			for(FrameHaveInfoVO fhi : fhiList) {
				InfoVO ivo = new InfoVO();
				ivo.setSeq(fhi.getInum());
				infoList.add(infoService.getInfo(ivo));
			}
			
			// 0-2. dataMap 구축
			for(FrameHaveInfoVO fhi : fhiList) {
				DataVO dvo = new DataVO();
				dvo.setInum(fhi.getInum());
				List<DataVO> dataList = dataService.getData(dvo);
				System.out.println(dataList);
				dataMap.put( fhi.getInum()+"" , dataList);
			}
			
			model.addAttribute("frame", frame);
			model.addAttribute("infoList", infoList);
			model.addAttribute("dataMap", dataMap);
		} 
		else if(board.getBtype().equals("visual")) {
			VisualVO vvo = new VisualVO();
			vvo.setVseq(board.getBnum());
			List<VisualHaveInfoVO> vhiList = visualService.getVHIList(vvo);
			VisualVO visual = visualService.getVisual(vvo);
			List<Integer> numList = new ArrayList<Integer>();
			List<String> strList = new ArrayList<String>();
			List<DataVO> dataList;
			List<String> bgList = new ArrayList<String>();
			List<String> boList = new ArrayList<String>();
			
			for(VisualHaveInfoVO vhi : vhiList) {
				if(vhi.getDtype().equals("num")) {
					DataVO num_dvo = new DataVO();
					num_dvo.setInum(vhi.getInum());
					dataList = dataService.getData(num_dvo);
					for(DataVO data : dataList) {
						numList.add(Integer.parseInt(data.getData()));
					}
				} else if (vhi.getDtype().equals("str")) {
					DataVO str_dvo = new DataVO();
					str_dvo.setInum(vhi.getInum());
					dataList = dataService.getData(str_dvo);
					for(DataVO data : dataList) {
						strList.add("\'" + data.getData() + "\'");
						bgList.add("\'rgba(" + ((int)(Math.random() * 256) + 1) + "," 
								+ ((int)(Math.random() * 256) + 1) + "," 
								+ ((int)(Math.random() * 256) + 1) + "," 
								+ "0.2)\'");
						boList.add("\'rgba(" + ((int)(Math.random() * 256) + 1) + "," 
								+ ((int)(Math.random() * 256) + 1) + "," 
								+ ((int)(Math.random() * 256) + 1) + "," 
								+ "0.2)\'");
					}
				}
			}
			
			List<InfoVO> infoList = new ArrayList<InfoVO>();
			Map<String, List<DataVO>> dataMap = new HashMap<String, List<DataVO>>();
			// 0-1. infoList 구축
			for(VisualHaveInfoVO vhi : vhiList) {
				InfoVO ivo = new InfoVO();
				ivo.setSeq(vhi.getInum());
				infoList.add(infoService.getInfo(ivo));
			}
					
			// 0-2. dataMap 구축
			for(VisualHaveInfoVO vhi : vhiList) {
				DataVO dvo = new DataVO();
				dvo.setInum(vhi.getInum());
				dataList = dataService.getData(dvo);
				System.out.println(dataList);
				dataMap.put( vhi.getInum()+"" , dataList);
			}
			
			model.addAttribute("infoList", infoList);
			model.addAttribute("dataMap", dataMap);
			model.addAttribute("numList", numList);
			model.addAttribute("strList", strList);
			model.addAttribute("bgList", bgList);
			model.addAttribute("boList", boList);
			model.addAttribute("visual",visual);
			model.addAttribute("vtype_split", "\'" + visual.getVtype().split(":")[1] + "\'");
		}
		else if(board.getBtype().equals("tm")) {
			TmVO tvo = new TmVO();
			tvo.setTseq(board.getBnum());
			model.addAttribute("tm", tmService.getTm(tvo));
			
			List<TmHaveInfoVO> thiList = tmService.getTHIList(tvo);
			
			List<InfoVO> infoList = new ArrayList<InfoVO>();
			Map<String, List<DataVO>> dataMap = new HashMap<String, List<DataVO>>();
			// 0-1. infoList 구축
			for(TmHaveInfoVO thi : thiList) {
				InfoVO ivo = new InfoVO();
				ivo.setSeq(thi.getInum());
				infoList.add(infoService.getInfo(ivo));
			}
						
			// 0-2. dataMap 구축
			for(TmHaveInfoVO thi : thiList) {
				DataVO dvo = new DataVO();
				dvo.setInum(thi.getInum());
				List<DataVO> dataList = dataService.getData(dvo);
				System.out.println(dataList);
				dataMap.put( thi.getInum()+"" , dataList);
			}
			
			model.addAttribute("infoList", infoList);
			model.addAttribute("dataMap", dataMap);
		}
		
		model.addAttribute("board", board);
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
