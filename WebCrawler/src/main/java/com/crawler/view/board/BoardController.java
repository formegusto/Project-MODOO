package com.crawler.view.board;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crawler.biz.board.BoardHaveInfoVO;
import com.crawler.biz.board.BoardVO;
import com.crawler.biz.board.impl.BoardService;
import com.crawler.biz.data.impl.DataService;
import com.crawler.biz.info.InfoVO;
import com.crawler.biz.info.impl.InfoService;

@Controller
public class BoardController {
	@Autowired
	BoardService boardService;
	@Autowired
	InfoService	infoService;
	@Autowired
	DataService	dataService;
	
	@RequestMapping(value="/boardAdd_proc.do")
	public String boardAdd(BoardVO bvo, HttpSession session , 
			@RequestParam(value="inumList", required=true) List<String> inumList) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 게시판 등록 기능 처리");
		
		boardService.insertBoard(bvo);
		for(String inum : inumList) {
			BoardHaveInfoVO bhivo = new BoardHaveInfoVO();
			bhivo.setInum(Integer.parseInt(inum));
			boardService.insertBHI(bhivo);
		}
		
		return "getInfoList.do";
	}
	
	@RequestMapping(value="/getBoardList.do")
	public String getBoardList(BoardVO vo, HttpSession session,
			Model model) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 게시판 조회 기능 처리");
		
		model.addAttribute("boardList", boardService.getBoardList(vo));
		return "getBoardList.jsp";
	}
	
	@RequestMapping(value="/getBoard.do")
	public String getBoard(BoardVO vo, HttpSession session,
			Model model) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 게시판 상세 조회 기능 처리");

		// infoList Make
		List<BoardHaveInfoVO> bhiList = boardService.getBHIList(vo);
		List<InfoVO> infoList = new ArrayList<InfoVO>();
		for(BoardHaveInfoVO bhi : bhiList) {
			InfoVO ivo = new InfoVO();
			ivo.setSeq(bhi.getInum());
			infoList.add(infoService.getInfo(ivo));
		}
		model.addAttribute("board", boardService.getBoard(vo));
		model.addAttribute("infoList", infoList);
		
		return "getBoard.jsp";
	}
}
