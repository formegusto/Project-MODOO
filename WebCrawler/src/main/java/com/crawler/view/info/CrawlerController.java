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
	
	// 입력한 크롤러 정보 및 크롤링 데이터 미리보기
	@RequestMapping(value="/crawlerConfirm.do")
	public String crawlerConfirm(InfoVO vo, Model model) {
		System.out.println("[Spring Service MVC Framework] 크롤러 확인 페이지 처리");
		// 1. 사용자 입력 정보 추출
		// 2. 크롤러 데이터 정보 추출
		// 3.session에 객체 저장
		model.addAttribute("info",vo);
		model.addAttribute("dataList", WCrawl.getData(vo));
		return "crawlerConfirm.jsp";
	}
	
	// 크롤러 정보 등록
	@RequestMapping(value="/crawlerAdd_proc.do")
	public String crawlAdd(@RequestParam(value="data", required=true) List<String> datas ,InfoVO ivo) {
		System.out.println("[Spring Service request param MVC Framework] 크롤러 및 데이터 등록 처리");
		// 1. 사용자 입력 정보 추출
		// 2. DB 연동 처리 (info)
		infoService.insertInfo(ivo);
		
		// 3. DB 연동 처리 (data)
		List<DataVO> dataList = new ArrayList<DataVO>();
		for(String data : datas){
			DataVO dvo = new DataVO();
			dvo.setData(data);
			dataList.add(dvo);
		}
		dataService.insertData(dataList);
		
		// 4. 화면 네비게이션
		return "redirect:getInfoList.do";
	}
}
