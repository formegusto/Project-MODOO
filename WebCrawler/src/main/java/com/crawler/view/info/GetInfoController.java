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
		System.out.println("[Spring MVC Framework] ũ�ѷ� �� ���� ��� ó��");
		// 1. �˻��� ũ�ѷ� ������ ��ȣ ����
		String seq = request.getParameter("seq");

		// 2. DB ���� ó��(info)
		InfoVO ivo = new InfoVO();
		ivo.setSeq(Integer.parseInt(seq));
		InfoDAO infoDAO = new InfoDAO();
		InfoVO info = infoDAO.getInfo(ivo);
		
		// 3. DB ���� ó��(Data)
		DataVO dvo = new DataVO();
		dvo.setInum(Integer.parseInt(seq));
		DataDAO dataDAO = new DataDAO();
		List<DataVO> dataList = dataDAO.getData(dvo);
		
		// 4. �˻� ����� ���ǿ� �����ϰ� �� ȭ������ �̵��Ѵ�.
		ModelAndView mav = new ModelAndView();
		mav.addObject("info", info);
		mav.addObject("dataList", dataList);
		mav.setViewName("getInfo");
		return mav;
	}

}
