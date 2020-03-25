package com.crawler.view.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crawler.biz.board.BoardHaveInfoVO;
import com.crawler.biz.board.BoardVO;
import com.crawler.biz.board.impl.BoardService;
import com.crawler.biz.data.DataVO;
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
	
	@RequestMapping(value="/boardConfirm.do")
	public String boardConfirm(@RequestParam(value="seqList", required=true) List<String> seqList,
			Model model , HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 정보 리스트 여러개 보기 기능 처리");
		
		// 1. infoList 구축
		List<InfoVO> infoList = new ArrayList<InfoVO>();
		for(String seq : seqList) {
			InfoVO vo = new InfoVO();
			vo.setSeq(Integer.parseInt(seq));
			infoList.add(infoService.getInfo(vo));
		}
		
		// 2. dataMap 구축
		Map<String, List<String>> dataMap = new HashMap<String, List<String>>();
		for(String seq : seqList) {
			DataVO dvo = new DataVO();
			dvo.setInum(Integer.parseInt(seq));
			List<String> dataList = dataService.getDataStr(dvo);
			dataMap.put(seq, dataList);
		}
		
		// 3. session에 값 저장
		model.addAttribute("infoList", infoList);
		model.addAttribute("dataMap", dataMap);
		return "boardConfirm.jsp";
	}
	
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
	
	@RequestMapping(value="/deleteBoard.do")
	public String deleteBoard(BoardVO vo, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 게시판 삭제 기능 처리");
		
		boardService.deleteBoard(vo);
		
		return "getBoardList.do";
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
		
		// dataMap Make
		Map<String, List<DataVO>> dataMap = new HashMap<String, List<DataVO>>();
		for(BoardHaveInfoVO bhi : bhiList) {
			DataVO dvo = new DataVO();
			dvo.setInum(bhi.getInum());
			List<DataVO> dataList = dataService.getData(dvo);
			System.out.println(dataList);
			dataMap.put( bhi.getInum()+"" , dataList);
		}
		
		model.addAttribute("board", boardService.getBoard(vo));
		model.addAttribute("infoList", infoList);
		model.addAttribute("dataMap", dataMap);
		return "getBoard.jsp";
	}
}
