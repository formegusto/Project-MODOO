package com.crawler.view.info;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.crawler.biz.data.DataVO;
import com.crawler.biz.data.impl.DataDAO;
import com.crawler.biz.info.InfoVO;
import com.crawler.biz.info.impl.InfoDAO;

public class GetInfoController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("[Spring MVC Framework] 크롤러 상세 보기 기능 처리");
		// 1. 검색할 크롤러 데이터 번호 추출
		String seq = request.getParameter("seq");

		// 2. DB 연동 처리(info)
		InfoVO ivo = new InfoVO();
		ivo.setSeq(Integer.parseInt(seq));
		InfoDAO infoDAO = new InfoDAO();
		InfoVO info = infoDAO.getInfo(ivo);
		
		// 3. DB 연동 처리(Data)
		DataVO dvo = new DataVO();
		dvo.setInum(Integer.parseInt(seq));
		DataDAO dataDAO = new DataDAO();
		List<DataVO> dataList = dataDAO.getData(dvo);
		
		// 4. 검색 결과를 세션에 저장하고 상세 화면으로 이동한다.
		ModelAndView mav = new ModelAndView();
		mav.addObject("info", info);
		mav.addObject("dataList", dataList);
		mav.setViewName("getInfo");
		return mav;
	}

}
