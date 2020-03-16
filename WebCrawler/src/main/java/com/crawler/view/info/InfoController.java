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
	
	// 크롤러 상세보기
	@RequestMapping(value="/getInfo.do")
	public String getInfo(InfoVO ivo, DataVO dvo, Model model) {
		System.out.println("[Spring Service MVC Framework] 크롤러 상세 보기 기능 처리");
		// 1. 검색할 크롤러 데이터 번호 추출
		// 2. DB 연동 처리(info)
		// 3. DB 연동 처리(Data)
		// 4. 검색 결과를 세션에 저장하고 상세 화면으로 이동한다.
		dvo.setInum(ivo.getSeq());
		model.addAttribute("info", infoService.getInfo(ivo));
		model.addAttribute("dataList", dataService.getData(dvo));
		return "getInfo.jsp";
	}
	
	// 크롤러 리스트 보기
	@RequestMapping(value="/getInfoList.do")
	public String getInfoList(InfoVO vo, Model model, HttpSession session) {
		System.out.println("[Spring Service MVC Framework] 크롤러 목록 검색 처리");
		// 1. 사용자 입력 정보 추출
		// 2. DB 연동 처리
		// 3. 검색 결과를 세션에 저장하고 목록화면으로 이동한다.
		vo.setId(((UserVO)session.getAttribute("user")).getId());
		model.addAttribute("infoList", infoService.getInfoList(vo));
		return "getInfoList.jsp";
	}
}
