package com.crawler.view.info;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crawler.biz.common.WCrawl;
import com.crawler.biz.data.DataVO;
import com.crawler.biz.data.impl.DataService;
import com.crawler.biz.info.InfoVO;
import com.crawler.biz.info.impl.InfoService;

@Controller
public class CrawlerController {
	@Autowired
	InfoService infoService;
	@Autowired
	DataService dataService;
	
	// �Է��� ũ�ѷ� ���� �� ũ�Ѹ� ������ �̸�����
	@RequestMapping(value="/crawlerConfirm.do")
	public String crawlerConfirm(InfoVO vo, Model model) {
		System.out.println("[Spring Service MVC Framework] ũ�ѷ� Ȯ�� ������ ó��");
		// 1. ����� �Է� ���� ����
		// 2. ũ�ѷ� ������ ���� ����
		// 3.session�� ��ü ����
		model.addAttribute("info",vo);
		model.addAttribute("dataList", WCrawl.getData(vo));
		return "crawlerConfirm.jsp";
	}
	
	// ũ�ѷ� ���� ���
	@RequestMapping(value="/crawlerAdd_proc.do")
	public String crawlAdd(@RequestParam(value="data", required=true) List<String> datas ,InfoVO ivo) {
		System.out.println("[Spring Service request param MVC Framework] ũ�ѷ� �� ������ ��� ó��");
		// 1. ����� �Է� ���� ����
		// 2. DB ���� ó�� (info)
		infoService.insertInfo(ivo);
		
		// 3. DB ���� ó�� (data)
		List<DataVO> dataList = new ArrayList<DataVO>();
		for(String data : datas){
			DataVO dvo = new DataVO();
			dvo.setData(data);
			dataList.add(dvo);
		}
		dataService.insertData(dataList);
		
		// 4. ȭ�� �׺���̼�
		return "redirect:getInfoList.do";
	}
}
