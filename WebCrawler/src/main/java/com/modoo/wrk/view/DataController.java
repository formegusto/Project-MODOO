package com.modoo.wrk.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.modoo.wrk.data.DataVO;
import com.modoo.wrk.data.SearchVO;
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
	public String infoService(InfoVO vo, String mode, String keyword,
			Model model) {
		System.out.println(keyword);
		
		String page = "dataService.jsp";
		
		if(mode.equals("delete")) {
			page = "dataServiceDelete.jsp";
		}
		if(mode.equals("update")) {
			page = "dataServiceUpdate.jsp";
		}
		
		InfoVO info = infoService.getInfo(vo);
		
		model.addAttribute("info", info);
		
		
		if(keyword == null) {
			System.out.println("키워드 따위 없는 시크한 그런 데이터 리스트!");
			DataVO dvo = new DataVO();
			dvo.setIseq(vo.getIseq());
			
			model.addAttribute("dataList", dataService.getData(dvo));
		} else {
			System.out.println("키워드 있는 똑똑한 그런 데이터 리스트!");
			SearchVO svo = new SearchVO();
			svo.setIseq(vo.getIseq());
			svo.setKeyword(keyword);
			
			model.addAttribute("dataList", dataService.getDataSearch(svo));
		}
		
		return page;
	}
	
	@RequestMapping("/deleteData.do")
	public String deleteData(String dseqList,
			InfoVO info) {
		String[] dseqList_ = dseqList.split(",");
		
		DataVO vo = new DataVO();
		
		for(String dseq : dseqList_) {
			vo.setDseq(Integer.parseInt(dseq));
			dataService.deleteData(vo);
		}
		
		return "redirect:dataService.do?iseq=" + info.getIseq() + "&mode=delete";
	}
	
	@RequestMapping("/updateData.do")
	public String updateData(String dseqList, String dataList,
			InfoVO info) {
		System.out.println(dseqList);
		System.out.println(dataList);
		
		String[] dseqList_ = dseqList.split(",");
		String[] dataList_ = dataList.split(",");
		
		DataVO vo = new DataVO();
		for(int i=0;i<dseqList_.length;i++) {
			vo.setDseq(Integer.parseInt(dseqList_[i]));
			vo.setData(dataList_[i]);
			
			dataService.updateData(vo);
		}
		
		return "redirect:dataService.do?iseq=" + info.getIseq() + "&mode=update";
	}
}
