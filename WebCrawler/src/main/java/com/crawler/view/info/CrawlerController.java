package com.crawler.view.info;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.crawler.biz.common.WCrawl;
import com.crawler.biz.data.DataVO;
import com.crawler.biz.data.impl.DataDAO;
import com.crawler.biz.info.InfoVO;
import com.crawler.biz.info.impl.InfoDAO;

@Controller
public class CrawlerController {
	// �Է��� ũ�ѷ� ���� �� ũ�Ѹ� ������ �̸�����
	@RequestMapping(value="/crawlerConfirm.do")
	public ModelAndView crawlerConfirm(InfoVO vo, ModelAndView mav) {
		System.out.println("[Spring Annotation MVC Framework] ũ�ѷ� Ȯ�� ������ ó��");
		// 1. ����� �Է� ���� ����
		// 2. ũ�ѷ� ������ ���� ����
		// 3.session�� ��ü ����
		mav.addObject("info",vo);
		mav.addObject("dataList", WCrawl.getData(vo));
		mav.setViewName("crawlerConfirm.jsp");
		return mav;
	}
	
	// ũ�ѷ� ���� ���
	@RequestMapping(value="/crawlerAdd_proc.do")
	public String crawlAdd(@RequestParam(value="data", required=true) List<String> datas ,InfoVO ivo, InfoDAO infoDAO, DataDAO dataDAO) {
		System.out.println("[Spring Annotation request param MVC Framework] ũ�ѷ� �� ������ ��� ó��");
		// 1. ����� �Է� ���� ����
		// 2. DB ���� ó�� (info)
		infoDAO.insertInfo(ivo);
		
		// 3. DB ���� ó�� (data)
		List<DataVO> dataList = new ArrayList<DataVO>();
		for(String data : datas){
			DataVO dvo = new DataVO();
			dvo.setData(data);
			dataList.add(dvo);
		}
		dataDAO.insertData(dataList);
		
		// 4. ȭ�� �׺���̼�
		return "redirect:getInfoList.do";
	}
}
