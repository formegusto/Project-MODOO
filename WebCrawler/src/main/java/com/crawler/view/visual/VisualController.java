package com.crawler.view.visual;

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
import com.crawler.biz.info.impl.InfoService;
import com.crawler.biz.user.UserVO;
import com.crawler.biz.visual.VisualHaveInfoVO;
import com.crawler.biz.visual.VisualVO;
import com.crawler.biz.visual.impl.VisualService;

@Controller
public class VisualController {
	@Autowired
	VisualService visualService;
	@Autowired
	InfoService infoService;
	@Autowired
	DataService dataService;
	
	@RequestMapping("/visualConfirm.do")
	public String visualConfirm(@RequestParam String numSet,
			@RequestParam String strSet,
			VisualVO vo, HttpSession session, Model model) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] VisualConfirm 기능 처리");
		
		// 1. infoList 구축 (numSet strSet)
		List<InfoVO> infoList = new ArrayList<InfoVO>();
		InfoVO num_vo = new InfoVO();
		num_vo.setSeq(Integer.parseInt(numSet));
		infoList.add(infoService.getInfo(num_vo));
		
		InfoVO str_vo = new InfoVO();
		str_vo.setSeq(Integer.parseInt(strSet));
		infoList.add(infoService.getInfo(str_vo));
		
		/*
		// 2. dataMap 구축
		Map<String, List<DataVO>> dataMap = new HashMap<String, List<DataVO>>();
		DataVO num_dvo = new DataVO();
		num_dvo.setInum(Integer.parseInt(numSet));
		List<DataVO> dataList = dataService.getData(num_dvo);
		dataMap.put("numSet", dataList);
		
		DataVO str_dvo = new DataVO();
		str_dvo.setInum(Integer.parseInt(strSet));
		dataList = dataService.getData(str_dvo);
		dataMap.put("strSet", dataList);
		*/
		
		// 2. 숫자 데이터 구축
		List<Integer> numList = new ArrayList<Integer>();
		DataVO num_dvo = new DataVO();
		num_dvo.setInum(Integer.parseInt(numSet));
		List<DataVO> dataList = dataService.getData(num_dvo);
		for(DataVO data : dataList) {
			numList.add(Integer.parseInt(data.getData()));
		}
		
		// 3. 문자열 데이터 구축
		List<String> strList = new ArrayList<String>();
		DataVO str_dvo = new DataVO();
		str_dvo.setInum(Integer.parseInt(strSet));
		dataList = dataService.getData(str_dvo);
		// 4. 랜덤 RGBList 구축
		List<String> bgList = new ArrayList<String>();
		List<String> boList = new ArrayList<String>();
		for(DataVO data : dataList) {
			strList.add("\'" + data.getData() + "\'");
			bgList.add("\'rgba(" + ((int)(Math.random() * 256) + 1) + "," 
					+ ((int)(Math.random() * 256) + 1) + "," 
					+ ((int)(Math.random() * 256) + 1) + "," 
					+ "0.2)\'");
			boList.add("\'rgba(" + ((int)(Math.random() * 256) + 1) + "," 
					+ ((int)(Math.random() * 256) + 1) + "," 
					+ ((int)(Math.random() * 256) + 1) + "," 
					+ "0.2)\'");
		}
		
		System.out.println(strList);
		System.out.println(numList);
		
		model.addAttribute("numSet", numSet);
		model.addAttribute("strSet", strSet);
		model.addAttribute("numList", numList);
		model.addAttribute("strList", strList);
		model.addAttribute("bgList", bgList);
		model.addAttribute("boList", boList);
		model.addAttribute("visual",vo);
		model.addAttribute("vtype_split", "\'" + vo.getVtype().split(":")[1] + "\'");
		
		return "visualConfirm.jsp";
	}
	
	@RequestMapping("/visualAdd_proc.do")
	public String visualAdd(VisualVO vo, HttpSession session,
			@RequestParam String numSet,
			@RequestParam String strSet) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] visualAdd_Proc 기능 처리");
		
		visualService.insertVisual(vo);
		
		VisualHaveInfoVO vhi = new VisualHaveInfoVO();
		vhi.setInum(Integer.parseInt(numSet));
		vhi.setDtype("num");
		visualService.insertVHI(vhi);
		
		vhi = new VisualHaveInfoVO();
		vhi.setInum(Integer.parseInt(strSet));
		vhi.setDtype("str");
		visualService.insertVHI(vhi);
		
		return "getVisualList.do";
	}
	
	@RequestMapping(value="/getVisualList.do")
	public String getVisualList(VisualVO vo, HttpSession session, Model model) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] visual 게시판 조회 기능 처리");
		
		vo.setId(((UserVO)session.getAttribute("user")).getId());
		model.addAttribute("visualList", visualService.getVisualList(vo));
		
		return "getVisualList.jsp";
	}
	
	@RequestMapping(value="/getVisual.do")
	public String getVisual(VisualVO vo, HttpSession session, Model model) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] visual 게시판 상세 조회 기능 처리");
		
		List<VisualHaveInfoVO> vhiList = visualService.getVHIList(vo);
		List<Integer> numList = new ArrayList<Integer>();
		List<String> strList = new ArrayList<String>();
		List<DataVO> dataList;
		List<String> bgList = new ArrayList<String>();
		List<String> boList = new ArrayList<String>();
		for(VisualHaveInfoVO vhi : vhiList) {
			if(vhi.getDtype().equals("num")) {
				DataVO num_dvo = new DataVO();
				num_dvo.setInum(vhi.getInum());
				dataList = dataService.getData(num_dvo);
				for(DataVO data : dataList) {
					numList.add(Integer.parseInt(data.getData()));
				}
			} else if (vhi.getDtype().equals("str")) {
				DataVO str_dvo = new DataVO();
				str_dvo.setInum(vhi.getInum());
				dataList = dataService.getData(str_dvo);
				for(DataVO data : dataList) {
					strList.add("\'" + data.getData() + "\'");
					bgList.add("\'rgba(" + ((int)(Math.random() * 256) + 1) + "," 
							+ ((int)(Math.random() * 256) + 1) + "," 
							+ ((int)(Math.random() * 256) + 1) + "," 
							+ "0.2)\'");
					boList.add("\'rgba(" + ((int)(Math.random() * 256) + 1) + "," 
							+ ((int)(Math.random() * 256) + 1) + "," 
							+ ((int)(Math.random() * 256) + 1) + "," 
							+ "0.2)\'");
				}
			}
		}
		
		VisualVO visual = visualService.getVisual(vo);
		model.addAttribute("numList", numList);
		model.addAttribute("strList", strList);
		model.addAttribute("bgList", bgList);
		model.addAttribute("boList", boList);
		model.addAttribute("visual",visual);
		model.addAttribute("vtype_split", "\'" + visual.getVtype().split(":")[1] + "\'");
		
		return "getVisual.jsp";
	}
}
