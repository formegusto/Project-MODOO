package com.crawler.view.crawler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.crawler.biz.user.UserVO;

@Controller
public class CrawlerController {
	@Autowired
	InfoService infoService;
	@Autowired
	DataService dataService;
	
	// 크롤러로 데이터 조회
	@RequestMapping(value="/crawlerConfirm.do")
	public String crawlerConfirm(InfoVO vo, Model model, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 크롤링 기능 처리");
		// 1. 사용자 입력정보 추출
		// 2. DB 연동 처리
		// 3. session에 객체 저장
		model.addAttribute("info",vo);
		model.addAttribute("dataList", WCrawl.getData(vo));
		return "crawlerConfirm.jsp";
	}
	
	// 크롤러 정보 및 데이터 저장
	@RequestMapping(value="/crawlerAdd_proc.do")
	public String crawlAdd(@RequestParam(value="data", required=true) List<String> datas ,
			InfoVO ivo, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service request param MVC Framework] 크롤링 정보 데이터 저장 기능 처리");
		// 1. 사용자 입력정보 추출
		// 2. DB 연동 처리(info)
		ivo.setId(((UserVO)session.getAttribute("user")).getId());
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
		return "getInfoList.do";
	}
}
