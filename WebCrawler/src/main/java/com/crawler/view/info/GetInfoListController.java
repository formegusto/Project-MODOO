package com.crawler.view.info;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.crawler.biz.info.InfoVO;
import com.crawler.biz.info.impl.InfoDAO;

public class GetInfoListController implements Controller {
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("[Spring MVC Framework] 크롤러 목록 검색 처리");
		// 1. 사용자 입력 정보 추출
		// 2. DB 연동 처리
		InfoVO vo = new InfoVO();
		InfoDAO infoDAO = new InfoDAO();
		List<InfoVO> infoList = infoDAO.getInfoList(vo);
		
		// 3. 검색 결과를 세션에 저장하고 목록화면으로 이동한다.
		ModelAndView mav = new ModelAndView();
		mav.addObject("infoList", infoList);
		mav.setViewName("getInfoList");
		return mav;
	}

}
