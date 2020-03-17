package com.crawler.view.info;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	@RequestMapping(value="/getInfoList.do")
	public String getInfoList(InfoVO vo, Model model, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 정보 리스트 보기 기능 처리");
		// 1. 사용자 입력정보 추출
		// 2. DB 연동 처리
		// 3. 세션에 값 저장
		vo.setId(((UserVO)session.getAttribute("user")).getId());
		model.addAttribute("infoList", infoService.getInfoList(vo));
		return "getInfoList.jsp";
	}
}
