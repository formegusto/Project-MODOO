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
		System.out.println("[Spring MVC Framework] ũ�ѷ� ��� �˻� ó��");
		// 1. ����� �Է� ���� ����
		// 2. DB ���� ó��
		InfoVO vo = new InfoVO();
		InfoDAO infoDAO = new InfoDAO();
		List<InfoVO> infoList = infoDAO.getInfoList(vo);
		
		// 3. �˻� ����� ���ǿ� �����ϰ� ���ȭ������ �̵��Ѵ�.
		ModelAndView mav = new ModelAndView();
		mav.addObject("infoList", infoList);
		mav.setViewName("getInfoList");
		return mav;
	}

}
