package com.crawler.view.board;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crawler.biz.board.BoardVO;
import com.crawler.biz.board.CommentVO;
import com.crawler.biz.board.impl.BoardService;
import com.crawler.biz.board.impl.CommentService;
import com.crawler.biz.data.DataVO;
import com.crawler.biz.data.impl.DataService;
import com.crawler.biz.info.FrameHaveInfoVO;
import com.crawler.biz.info.FrameVO;
import com.crawler.biz.info.InfoVO;
import com.crawler.biz.info.impl.FrameService;
import com.crawler.biz.info.impl.InfoService;
import com.crawler.biz.user.UserVO;
import com.crawler.biz.visual.VisualHaveInfoVO;
import com.crawler.biz.visual.VisualVO;
import com.crawler.biz.visual.impl.VisualService;

import au.com.bytecode.opencsv.CSVWriter;

@Controller
public class BoardController {
	@Autowired
	BoardService boardService;
	@Autowired
	InfoService	infoService;
	@Autowired
	DataService	dataService;
	@Autowired
	FrameService frameService;
	@Autowired
	VisualService visualService;
	@Autowired
	CommentService commentService;
	
	// 보드 확인
	@RequestMapping(value="/boardConfirm.do")
	public String boardConfirm(BoardVO vo,
			Model model , HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 정보 리스트 여러개 보기 기능 처리");
		
		
		// 0. Frame 참조 리스트 구축
		if(vo.getBtype().equals("frame")) {
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
		// 1. Visual 참조 리스트 구축
		else if(vo.getBtype().equals("visual")) {
			VisualVO vvo = new VisualVO();
			vvo.setVseq(vo.getBnum());
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
			model.addAttribute("numList", numList);
			model.addAttribute("strList", strList);
			model.addAttribute("bgList", bgList);
			model.addAttribute("boList", boList);
			model.addAttribute("visual",visual);
			model.addAttribute("vtype_split", "\'" + visual.getVtype().split(":")[1] + "\'");
		}
		
		// 3. session에 값 저장
		model.addAttribute("board",vo);
		
		return "boardConfirm.jsp";
	}
	
	// 게시물 생성
	@RequestMapping(value="/boardAdd_proc.do")
	public String boardAdd(BoardVO vo, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 게시판 등록 기능 처리");
		
		boardService.insertBoard(vo);
		
		return "getInfoList.do";
	}
	
	// 게시물 삭제
	@RequestMapping(value="/deleteBoard.do")
	public String deleteBoard(BoardVO vo, HttpSession session, Model model,
			@RequestParam String pageNum,
			@RequestParam String startPage) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 게시판 삭제 기능 처리");
		
		
		
		boardService.deleteBoard(vo);
		
		model.addAttribute("startPage",startPage);
		model.addAttribute("pageNum",pageNum);		
		return "getBoardList.do";
	}
	
	// 게시물 리스트 조회
	@RequestMapping(value="/getBoardList.do")
	public String getBoardList(BoardVO vo, HttpSession session,
			Model model,
			@RequestParam String pageNum,
			HttpServletRequest request) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 게시판 조회 기능 처리");
		
		final int MAX_CNT = 10;
		final int MAX_PAGE_LIST = 5;
		
		// paging
		String startPage_ = request.getParameter("startPage");
		int boardCnt = boardService.boardCnt(vo);
		int startPage;
		if(startPage_==null || startPage_=="") {
			startPage=0;
		} else {
			startPage = Integer.parseInt(startPage_);
		}
		int finalPage = (boardCnt/10);
		if((boardCnt%10)!=0)
			finalPage += 1;
		int endPage = startPage + MAX_PAGE_LIST;
		if(endPage>=finalPage)
			endPage = finalPage;
		
		int pageNum_ = Integer.parseInt(pageNum);
		if(pageNum_>=finalPage)
			pageNum_ = finalPage;
		List<BoardVO> boardList_ = boardService.getBoardList(vo);
		List<BoardVO> boardList = null;
		
		if(boardCnt!=0) {
			try {
				boardList = boardList_.subList(MAX_CNT*(pageNum_-1), (MAX_CNT*pageNum_));
			} catch(IndexOutOfBoundsException e) {
				boardList = boardList_.subList(MAX_CNT*(pageNum_-1), boardCnt);
			}
		}
		model.addAttribute("pageNum",pageNum);
		model.addAttribute("startPage",startPage+"");
		model.addAttribute("endPage", endPage+"");
		model.addAttribute("finalPage",finalPage+"");
		model.addAttribute("boardList", boardList);
		return "getBoardList.jsp";
	}
	
	// 댓글 생성
	@RequestMapping(value="/commentAdd_proc.do")
	public String commentAdd(CommentVO vo, HttpSession session,
			Model model,
			@RequestParam String pageNum,
			@RequestParam String startPage) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 댓글 저장 기능 처리");
		
		vo.setId(((UserVO)session.getAttribute("user")).getId());
		commentService.insertComment(vo);
		
		model.addAttribute("bseq",vo.getBnum());
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("startPage", startPage);
		
		return "getBoard.do";
	}
	
	// 게시물 및 댓글 조회
	@RequestMapping(value="/getBoard.do")
	public String getBoard(BoardVO vo, HttpSession session,
			Model model,
			@RequestParam String pageNum,
			@RequestParam String startPage) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 게시판 상세 조회 기능 처리");

		BoardVO board = boardService.getBoard(vo);
		
		if(board.getBtype().equals("frame")) {
			FrameVO fvo = new FrameVO();
			fvo.setFseq(board.getBnum());
			List<FrameHaveInfoVO> fhiList = frameService.getFHIList(fvo);
			List<InfoVO> infoList = new ArrayList<InfoVO>();
			Map<String, List<DataVO>> dataMap = new HashMap<String, List<DataVO>>();
			FrameVO frame = frameService.getFrame(fvo);
			
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
			
			model.addAttribute("infoList", infoList);
			model.addAttribute("frame", frame);
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
			
			
			model.addAttribute("numList", numList);
			model.addAttribute("strList", strList);
			model.addAttribute("bgList", bgList);
			model.addAttribute("boList", boList);
			model.addAttribute("visual",visual);
			model.addAttribute("vtype_split", "\'" + visual.getVtype().split(":")[1] + "\'");
		}
		
		// 댓글창
		CommentVO cvo = new CommentVO();
		cvo.setBnum(vo.getBseq());
					
		model.addAttribute("commentList", commentService.getCommentList(cvo));
		model.addAttribute("pageNum",pageNum);
		model.addAttribute("startPage",startPage);
		model.addAttribute("board", board);
		
		return "getBoard.jsp";
	}
	
	/*
	@RequestMapping(value="/convertCSV.do")
	public String convertCSV(BoardVO vo, HttpSession session,
			Model model, @RequestParam String ctitle,
			@RequestParam String pageNum,
			@RequestParam String startPage) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] Table Convert To CSV 처리 기능 처리");
		
		ctitle += ".csv";
		
		
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
		
		/* Check
		System.out.println(ctitle);
		System.out.println(infoList);
		System.out.println(dataMap);
		*/
		
	/*
		// Convert List<String[]>
		List<String[]> csvList = new ArrayList<String[]>();
		String fieldList_ = "";
		for(int i=0;i<infoList.size();i++) {
			InfoVO info = infoList.get(i);
			if((i+1) == infoList.size()) fieldList_ += (info.getField());
			else fieldList_ += (info.getField() + ",");
		}
		String[] fieldList = fieldList_.split(",");
		csvList.add(fieldList);
		
		List<List<String>> dataList_ = new ArrayList<List<String>>();
		InfoVO info_ = infoList.get(0);
		int listSize = dataMap.get(info_.getSeq()+"").size(); // 첫 필드의 총 사이즈
		for(int i=0;i<infoList.size();i++) {
			InfoVO info = infoList.get(i);
			List<DataVO> dataVOList = dataMap.get(info.getSeq()+"");
			if(i==0) {
				for(int j=0;j<listSize;j++) {
					DataVO dataVO = dataVOList.get(j);
					List<String> data_ = new ArrayList<String>();
					data_.add(dataVO.getData());
					dataList_.add(data_);
				}
			} 
			/*
			 문제점 (1) 중간 필드와 마지막 필드에서 발생한다. 첫번째 필드에서 모든것의 개수가 맞추어 지기때문에 
			  개수가 넘어갈 경우에 IndexOutBoundException을 터뜨린다.
			  문제점 (2) 중간 필드와 마지막 필드에서 발생한다. 만약에 띄엄띄엄 일 경우 중간에 채워주지를 못한다.  
			  모두 하나의 테이블로서 빈칸을 채워주는 역할을 하면 될 것 같다.
			 */
	/*	
		else {
				if(listSize < dataVOList.size()) {
					for(int j=0;j<i;j++) {
						if(j==0) {
							for(int k=listSize;k<dataVOList.size();k++) {
								List<String> data_ = new ArrayList<String>();
								data_.add("");
								dataList_.add(data_);
							}
						} else {
							for(int k=listSize;k<dataVOList.size();k++) {
								dataList_.get(k).add("");
							}
						}
					}
					listSize = dataVOList.size();
				}
				for(int j=0;j<dataVOList.size();j++) {
					DataVO dataVO = dataVOList.get(j);
					dataList_.get(j).add(dataVO.getData());
				}
				if(listSize > dataVOList.size()) {
					for(int j=dataVOList.size();j<listSize;j++) {
						dataList_.get(j).add("");
					}
				}
			}
		}
		
		for(List<String> data : dataList_) {
			String[] dataList = data.toArray(new String[data.size()]);
			csvList.add(dataList);
		}
		
		// Convert CSV
		CSVWriter writer = null;
		String realPath = session.getServletContext().getRealPath("/download");
		System.out.println(realPath);
		try {
			writer = new CSVWriter(new FileWriter(realPath+"/"+ctitle));
			for(String[] csvStr : csvList)
				writer.writeNext(csvStr);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		model.addAttribute("pageNum",pageNum);
		model.addAttribute("startPage",startPage+"");
		model.addAttribute("ctitle",ctitle);
		model.addAttribute("bseq",vo.getBseq());
		
		return "csvDownload.jsp";
	}
	
	*/
}
