package com.crawler.view.info;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.crawler.biz.data.DataVO;
import com.crawler.biz.data.impl.DataDAO;
import com.crawler.biz.info.InfoVO;
import com.crawler.biz.info.impl.InfoDAO;

@Controller
public class InfoController {
	// 크롤러 상세보기
	@RequestMapping(value="/getInfo.do")
	public ModelAndView getInfo(InfoVO ivo, InfoDAO infoDAO, DataVO dvo, DataDAO dataDAO ,ModelAndView mav) {
		System.out.println("[Spring Annotation MVC Framework] 크롤러 상세 보기 기능 처리");
		// 1. 검색할 크롤러 데이터 번호 추출
		// 2. DB 연동 처리(info)
		// 3. DB 연동 처리(Data)
		// 4. 검색 결과를 세션에 저장하고 상세 화면으로 이동한다.
		dvo.setInum(ivo.getSeq());
		mav.addObject("info", infoDAO.getInfo(ivo));
		mav.addObject("dataList", dataDAO.getData(dvo));
		mav.setViewName("getInfo.jsp");
		return mav;
	}
	
	// 크롤러 리스트 보기
	@RequestMapping("/getInfoList.do")
	public ModelAndView getInfoList(InfoVO vo, InfoDAO infoDAO, ModelAndView mav) {
		System.out.println("[Spring Annotation MVC Framework] 크롤러 목록 검색 처리");
		// 1. 사용자 입력 정보 추출
		// 2. DB 연동 처리
		// 3. 검색 결과를 세션에 저장하고 목록화면으로 이동한다.
		mav.addObject("infoList", infoDAO.getInfoList(vo));
		mav.setViewName("getInfoList.jsp");
		return mav;
	}
}
