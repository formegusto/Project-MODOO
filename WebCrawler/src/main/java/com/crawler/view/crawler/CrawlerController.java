package com.crawler.view.crawler;

import java.net.MalformedURLException;
import java.net.URL;
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
	@RequestMapping(value="/crawlerLinkConfirm.do")
	public String crawlerLinkConfirm(InfoVO vo, Model model, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 크롤링 기능 처리");
		// 1. 사용자 입력정보 추출
		// 2. DB 연동 처리
		// 3. session에 객체 저장
		
		
		vo.setItype("css:link");
		model.addAttribute("info",vo);
		model.addAttribute("dataList", WCrawl.getLinkData(vo));
		
		return "crawlerLinkConfirm.jsp";
	}
	
	// 크롤러로 데이터 조회
	@RequestMapping(value="/crawlerTextConfirm.do")
	public String crawlerTextConfirm(InfoVO vo, Model model, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 크롤링 기능 처리");
		// 1. 사용자 입력정보 추출
		// 2. DB 연동 처리
		// 3. session에 객체 저장
		vo.setItype("css:text");
		model.addAttribute("info",vo);
		model.addAttribute("dataList", WCrawl.getData(vo));
		return "crawlerTextConfirm.jsp";
	}
	
	// 크롤러 정보 및 데이터 저장 (Text)
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
	
	// 크롤러 정보 및 데이터 저장 (Text)
	@RequestMapping(value="/crawlerLLConfirm.do")
	public String crawlLLConfirm(@RequestParam List<String> urlList,InfoVO ivo, 
			Model model,HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service request param MVC Framework] 크롤링 정보 데이터 저장 기능 처리");
		// 1. 사용자 입력정보 추출
		// 2. DB 연동 처리(info)
		System.out.println(urlList);
		List<InfoVO> infoList = new ArrayList<InfoVO>();
		for(String url : urlList) {
			InfoVO info = new InfoVO();
			info.setLink(url);
			info.setCssQuery(ivo.getCssQuery());
			infoList.add(info);
		}
		ivo.setItype("css:linklist");
		
		model.addAttribute("info", ivo);
		model.addAttribute("dataList", WCrawl.getData(infoList));
		
		// 4. 화면 네비게이션
		return "crawlerLLConfirm.jsp";
	}
		
	
	// 데이터 업데이트 컨펌
	@RequestMapping(value="/updateDataConfirm.do")
	public String updateDataConfirm(InfoVO vo, Model model, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 크롤링 데이터 업데이트 확인 기능 처리");
		// 1. 사용자 입력정보 추출
		// 2. DB 연동 처리
		// 3. session에 객체 저장
		DataVO dvo = new DataVO();
		dvo.setInum(vo.getSeq());
		model.addAttribute("ur_dataList", dataService.getData(dvo));
		model.addAttribute("info",vo);
		model.addAttribute("new_dataList", WCrawl.getData(vo));
		return "updateDataConfirm.jsp";
	}
	
	// 데이터 업데이트 적용
	@RequestMapping(value="/updateData.do")
	public String updateData(@RequestParam(value="data", required=true) List<String> datas,
			InfoVO ivo, Model model, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 크롤링 데이터 업데이트 기능 처리");
		// 1. 사용자 입력정보 추출
		// 2. DB 연동 처리 ( delete )
		DataVO delete_dvo = new DataVO();
		delete_dvo.setInum(ivo.getSeq());
		dataService.deleteData(delete_dvo);
		
		// 2. DB 연동 처리 ( insert ) 
		List<DataVO> dataList = new ArrayList<DataVO>();
		for(String data : datas){
			DataVO dvo = new DataVO();
			dvo.setInum(ivo.getSeq());
			dvo.setData(data);
			System.out.println(dvo);
			dataList.add(dvo);
		}
		dataService.insertDataInum(dataList);
		
		// 3. session에 객체 저장
		model.addAttribute("info",ivo);
		return "getInfo.do";
	}
	
	// 데이터 추가 적용
	@RequestMapping(value="/appendData.do")
	public String appendData(@RequestParam(value="data", required=true) List<String> datas,
			InfoVO ivo, Model model, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 크롤링 데이터 업데이트 기능 처리");
		// 1. 사용자 입력정보 추출
		
		// 2. DB 연동 처리 ( insert ) 
		List<DataVO> dataList = new ArrayList<DataVO>();
		for(String data : datas){
			DataVO dvo = new DataVO();
			dvo.setInum(ivo.getSeq());
			dvo.setData(data);
			System.out.println(dvo);
			dataList.add(dvo);
		}
		dataService.insertDataInum(dataList);
		
		// 3. session에 객체 저장
		model.addAttribute("info",ivo);
		return "getInfo.do";
	}
}
