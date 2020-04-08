package com.crawler.view.info;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.crawler.biz.data.DataVO;
import com.crawler.biz.data.impl.DataService;
import com.crawler.biz.info.InfoVO;
import com.crawler.biz.info.impl.InfoService;
import com.crawler.biz.user.UserVO;

import au.com.bytecode.opencsv.CSVReader;

@Controller
public class InfoController {
	@Autowired
	InfoService infoService;
	@Autowired
	DataService dataService;
	
	// 크롤러 정보 상세 보기
	@RequestMapping(value="/getInfo.do")
	public String getInfo(InfoVO ivo, DataVO dvo, Model model, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 정보 상세 보기 기능 처리");
		// 1. 사용자 입력정보 추출
		// 2. DB 연동 처리(info)
		// 3. DB 연동 처리(Data)
		// 4. 세션에 값 저장
		dvo.setInum(ivo.getSeq());
		model.addAttribute("info", infoService.getInfo(ivo));
		model.addAttribute("dataList", dataService.getData(dvo));
		return "getInfo.jsp";
	}
	
	// 크롤러 리스트 보기
	@RequestMapping(value= { "/getInfoList.do" , "/checkInfoList.do" , "crawlerLList.do"})
	public String getInfoList(InfoVO vo, Model model, 
			HttpSession session , HttpServletRequest request) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 정보 리스트 보기 기능 처리");
		// 1. 사용자 입력정보 추출
		String uri = request.getServletPath();
		// 2. DB 연동 처리
		// 3. 세션에 값 저장
		vo.setId(((UserVO)session.getAttribute("user")).getId());
		model.addAttribute("infoList", infoService.getInfoList(vo));
		if(uri.equals("/getInfoList.do")) 
			return "getInfoList.jsp";
		else if(uri.equals("/checkInfoList.do"))
			return "checkInfoList.jsp";
		else if(uri.equals("/crawlerLList.do")) 
			return "crawlerLList.jsp";
		
		return null;
	}
	
	// 크롤러 정보 상세 보기
	@RequestMapping(value="/deleteInfo.do")
	public String getInfo(InfoVO vo, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 정보 삭제 기능 처리");
		// 1. 사용자 입력정보 추출
		// 2. DB 연동 처리(info)
		// 3. DB 연동 처리(Data)
		// 4. 세션에 값 저장
		infoService.deleteInfo(vo);
		return "getInfoList.do";
	}
	
	// csv 정보 미리보기
	@RequestMapping(value="/csvConfirm.do")
	public String csvConfirm(HttpSession session,Model model,
			@RequestParam MultipartFile csv) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] csvConfirm 기능 처리");
		System.out.println(csv.getOriginalFilename());
		
		List<String[]> datas = new ArrayList<String[]>();
		try {
			Reader reader = new InputStreamReader(csv.getInputStream(), "EUC-KR");
			CSVReader csvReader = new CSVReader(reader);
			String[] s;
			while((s = csvReader.readNext()) != null) 
				datas.add(s);
			/* data Check
			for(String[] dat : datas) {
				for(String str : dat) {
					System.out.print(str + ",");
				}
				System.out.println();
			}
			*/
		} catch (IOException e) {
			System.out.println("CSV Error");
			e.printStackTrace();
		}
		
		String csvName_ = csv.getOriginalFilename();
		int idx = csvName_.lastIndexOf(".");
		System.out.println(idx);
		String csvName = csvName_.substring(0, idx);
		List<InfoVO> infoList = new ArrayList<InfoVO>();
		Map<String, List<DataVO>> dataMap = new HashMap<String, List<DataVO>>();
		
		String[] fieldList = datas.get(0);
		for(String field : fieldList) {
			InfoVO info = new InfoVO();
			info.setTitle(csvName + " (" + field + ")");
			info.setField(field);
			infoList.add(info);
		}
		
		datas.remove(0);
		for(InfoVO info : infoList)
			dataMap.put(info.getField(), new ArrayList<DataVO>());
		
		for(String[] data : datas) {
			for(int i=0;i<infoList.size();i++) {
				InfoVO info = infoList.get(i);
				DataVO dvo = new DataVO();
				dvo.setData(data[i]);
				dataMap.get(info.getField()).add(dvo);
			}
		}
		
		/* data Check
		System.out.println(infoList);
		System.out.println(dataMap);
		*/
		model.addAttribute("infoList", infoList);
		model.addAttribute("dataMap", dataMap);
		
		return "csvConfirm.jsp";
	}
	
	// csv 정보 넣기
	@RequestMapping(value="/csvAdd_proc.do")
	public String csvAdd(HttpSession session,Model model,
			HttpServletRequest request,
			@RequestParam(value="ogField", required=true) List<String> ogFieldList,
			@RequestParam(value="title", required=true) List<String> titleList,
			@RequestParam(value="content", required=true) List<String> contentList,
			@RequestParam(value="link", required=true) List<String> linkList,
			@RequestParam(value="field", required=true) List<String> fieldList,
			@RequestParam(value="cssQuery", required=true) List<String> cssList) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] csv 넣는 기능 처리");
		
		for(int i=0;i<ogFieldList.size();i++) {
			// info 삽입
			InfoVO info = new InfoVO();
			info.setTitle(titleList.get(i));
			info.setContent(contentList.get(i));
			info.setLink(linkList.get(i));
			info.setField(fieldList.get(i));
			info.setCssQuery(cssList.get(i));
			info.setId(((UserVO)session.getAttribute("user")).getId());
			info.setItype("csv:text");
			infoService.insertInfo(info);
			
			// 데이터 삽입
			String ogField = ogFieldList.get(i);
			String[] datas = request.getParameterValues(ogField);
			for(String data : datas) {
				DataVO dvo = new DataVO();
				dvo.setData(data);
				dataService.insertData(dvo);
			}
		}
		
		return "getInfoList.do";
	}
	
	// 크롤러 정보 상세 보기
	@RequestMapping(value="/crawlerLLAdd.do")
	public String crawlerLLAdd(InfoVO ivo, Model model, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 정보 상세 보기 기능 처리");
		// 1. 사용자 입력정보 추출
		// 2. DB 연동 처리(info)
		// 3. DB 연동 처리(Data)
		// 4. 세션에 값 저장
		DataVO dvo = new DataVO();
		dvo.setInum(Integer.parseInt(ivo.getLink()));
		
		model.addAttribute("urlList",dataService.getData(dvo));
		model.addAttribute("info", ivo);

		return "crawlerLLAdd.jsp";
	}
}
