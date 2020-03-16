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
	
	// ũ�ѷ� �󼼺���
	@RequestMapping(value="/getInfo.do")
	public String getInfo(InfoVO ivo, DataVO dvo, Model model) {
		System.out.println("[Spring Service MVC Framework] ũ�ѷ� �� ���� ��� ó��");
		// 1. �˻��� ũ�ѷ� ������ ��ȣ ����
		// 2. DB ���� ó��(info)
		// 3. DB ���� ó��(Data)
		// 4. �˻� ����� ���ǿ� �����ϰ� �� ȭ������ �̵��Ѵ�.
		dvo.setInum(ivo.getSeq());
		model.addAttribute("info", infoService.getInfo(ivo));
		model.addAttribute("dataList", dataService.getData(dvo));
		return "getInfo.jsp";
	}
	
	// ũ�ѷ� ����Ʈ ����
	@RequestMapping(value="/getInfoList.do")
	public String getInfoList(InfoVO vo, Model model, HttpSession session) {
		System.out.println("[Spring Service MVC Framework] ũ�ѷ� ��� �˻� ó��");
		// 1. ����� �Է� ���� ����
		// 2. DB ���� ó��
		// 3. �˻� ����� ���ǿ� �����ϰ� ���ȭ������ �̵��Ѵ�.
		vo.setId(((UserVO)session.getAttribute("user")).getId());
		model.addAttribute("infoList", infoService.getInfoList(vo));
		return "getInfoList.jsp";
	}
}
