package com.modoo.wrk.view;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.modoo.wrk.data.DataVO;
import com.modoo.wrk.data.impl.DataService;
import com.modoo.wrk.info.InfoVO;
import com.modoo.wrk.info.ModooCrawler;
import com.modoo.wrk.info.impl.InfoService;

@Controller
public class DataController {
	@Autowired
	private InfoService infoService;
	@Autowired
	private DataService dataService;
	@Autowired
	private ModooCrawler modooCrawler;
	
	@RequestMapping("/dataService.do")
	public String infoService(InfoVO vo, Model model) {
		InfoVO info = infoService.getInfo(vo);
		
		DataVO dvo = new DataVO();
		dvo.setIseq(vo.getIseq());
		
		model.addAttribute("info", info);
		model.addAttribute("dataList", dataService.getData(dvo));
		
		return "dataService.jsp";
	}
}
