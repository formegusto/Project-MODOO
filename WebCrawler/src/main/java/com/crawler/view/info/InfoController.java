package com.crawler.view.info;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crawler.biz.data.DataVO;
import com.crawler.biz.data.impl.DataService;
import com.crawler.biz.info.InfoVO;
import com.crawler.biz.info.impl.InfoService;
import com.crawler.biz.user.UserVO;

@Controller
public class InfoController {
	@Autowired
	InfoService infoService;
	@Autowired
	DataService dataService;
	
	// 크롤러 정보 상세 보기
	@RequestMapping(value="/getInfo.do")
	public String getInfo(InfoVO ivo, DataVO dvo, Model model, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 정보 상세 보기 기능 처리");
		// 1. 사용자 입력정보 추출
		// 2. DB 연동 처리(info)
		// 3. DB 연동 처리(Data)
		// 4. 세션에 값 저장
		dvo.setInum(ivo.getSeq());
		model.addAttribute("info", infoService.getInfo(ivo));
		model.addAttribute("dataList", dataService.getData(dvo));
		return "getInfo.jsp";
	}
	
	// 크롤러 리스트 보기
	@RequestMapping(value= { "/getInfoList.do" , "/checkInfoList.do" })
	public String getInfoList(InfoVO vo, Model model, 
			HttpSession session , HttpServletRequest request) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 정보 리스트 보기 기능 처리");
		// 1. 사용자 입력정보 추출
		String uri = request.getServletPath();
		// 2. DB 연동 처리
		// 3. 세션에 값 저장
		vo.setId(((UserVO)session.getAttribute("user")).getId());
		model.addAttribute("infoList", infoService.getInfoList(vo));
		if(uri.equals("/getInfoList.do")) 
			return "getInfoList.jsp";
		else if(uri.equals("/checkInfoList.do"))
			return "checkInfoList.jsp";
		return null;
	}
	
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
		// 2. dataList 구축
		
		// 3. session에 값 저장
		model.addAttribute("infoList", infoList);
		return "boardConfirm.jsp";
	}
}
