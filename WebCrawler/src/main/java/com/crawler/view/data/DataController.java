package com.crawler.view.data;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping(value="/changeData.do")
	public String changeData(InfoVO ivo, Model model, HttpSession session,
			@RequestParam String dseqChangeList) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		
		System.out.println(ivo);
		System.out.println(dseqChangeList);

		String[] dseqList = dseqChangeList.split(",");
		List<DataVO> dataList = new ArrayList<DataVO>();
		for(String dseq : dseqList) {
			DataVO vo = new DataVO();
			vo.setDseq(Integer.parseInt(dseq));
			dataList.add(dataService.getDataDseq(vo));
		}
		
		for(String dseq : dseqList) {
			DataVO vo = new DataVO();
			vo.setDseq(Integer.parseInt(dseq));
			dataService.deleteDataSeq(vo);
		}
		
		int tmp = dataList.get(0).getDseq();
		dataList.get(0).setDseq(dataList.get(1).getDseq());
		dataList.get(1).setDseq(tmp);
		for(DataVO dvo : dataList) {
			dataService.insertDataAll(dvo);
		}
		
		model.addAttribute("info", ivo);

		return "getInfo.do";
	}
}
