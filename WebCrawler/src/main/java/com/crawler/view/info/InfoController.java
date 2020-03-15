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
	// ũ�ѷ� �󼼺���
	@RequestMapping(value="/getInfo.do")
	public ModelAndView getInfo(InfoVO ivo, InfoDAO infoDAO, DataVO dvo, DataDAO dataDAO ,ModelAndView mav) {
		System.out.println("[Spring Annotation MVC Framework] ũ�ѷ� �� ���� ��� ó��");
		// 1. �˻��� ũ�ѷ� ������ ��ȣ ����
		// 2. DB ���� ó��(info)
		// 3. DB ���� ó��(Data)
		// 4. �˻� ����� ���ǿ� �����ϰ� �� ȭ������ �̵��Ѵ�.
		dvo.setInum(ivo.getSeq());
		mav.addObject("info", infoDAO.getInfo(ivo));
		mav.addObject("dataList", dataDAO.getData(dvo));
		mav.setViewName("getInfo.jsp");
		return mav;
	}
	
	// ũ�ѷ� ����Ʈ ����
	@RequestMapping("/getInfoList.do")
	public ModelAndView getInfoList(InfoVO vo, InfoDAO infoDAO, ModelAndView mav) {
		System.out.println("[Spring Annotation MVC Framework] ũ�ѷ� ��� �˻� ó��");
		// 1. ����� �Է� ���� ����
		// 2. DB ���� ó��
		// 3. �˻� ����� ���ǿ� �����ϰ� ���ȭ������ �̵��Ѵ�.
		mav.addObject("infoList", infoDAO.getInfoList(vo));
		mav.setViewName("getInfoList.jsp");
		return mav;
	}
}
