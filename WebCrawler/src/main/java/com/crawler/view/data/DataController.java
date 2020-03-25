package com.crawler.view.data;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crawler.biz.data.DataVO;
import com.crawler.biz.data.impl.DataService;
import com.crawler.biz.info.InfoVO;

@Controller
public class DataController {
	@Autowired
	DataService dataService;
	
	@RequestMapping(value="/deleteDataSeq.do")
	public String deleteDataSeq(InfoVO ivo, Model model, DataVO dvo, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		
		dataService.deleteDataSeq(dvo);
		model.addAttribute("seq",ivo.getSeq());
		
		return "getInfo.do?";
	}
}
