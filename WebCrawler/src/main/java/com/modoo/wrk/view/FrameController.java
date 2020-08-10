package com.modoo.wrk.view;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.modoo.wrk.data.DataVO;
import com.modoo.wrk.data.SearchVO;
import com.modoo.wrk.data.impl.DataService;
import com.modoo.wrk.frame.FHIVO;
import com.modoo.wrk.frame.FrameVO;
import com.modoo.wrk.frame.impl.FrameService;
import com.modoo.wrk.info.InfoVO;
import com.modoo.wrk.info.impl.InfoService;
import com.modoo.wrk.users.UsersVO;

@Controller
public class FrameController {
	@Autowired
	private FrameService frameService;
	@Autowired
	private InfoService infoService;
	@Autowired
	private DataService dataService;
	
	@RequestMapping("frameService.do")
	public String frameService(HttpSession session,
			Model model, HttpServletRequest req) {
		FrameVO fvo = new FrameVO();
		
		fvo.setId(((UsersVO)session.getAttribute("user")).getId());
		
		String keyword = req.getParameter("keyword");
		
		if(keyword != "" && keyword != null) {
			SearchVO search = new SearchVO();
			search.setId(fvo.getId());
			search.setKeyword(keyword);
			model.addAttribute("frameList", frameService.getFrameListSearch(search));
		} else {
			model.addAttribute("frameList", frameService.getFrameList(fvo));
		}
		
		return "frameService.jsp";
	}
	
	@RequestMapping("frameConfirm.do")
	public String frameConfirm(String iseqList, Model model) {
		System.out.println(iseqList);
		
		String[] iseqList_ = iseqList.split(",");
		
		List<InfoVO> infoList = new ArrayList<InfoVO>();
		InfoVO ivo = new InfoVO();
		DataVO dvo = new DataVO();
		for(String iseq : iseqList_) {
			ivo.setIseq(Integer.parseInt(iseq));
			dvo.setIseq(Integer.parseInt(iseq));
			
			InfoVO info = infoService.getInfo(ivo);
			info.setDataList(dataService.getDataNotVO(dvo));
			
			infoList.add(info);
		}
		
		model.addAttribute("infoList", infoList);
		
		System.out.println(infoList);
		
		return "frameConfirm.jsp";
	}
	
	@RequestMapping("frameMake.do")
	public String frameMake(FrameVO vo, Integer[] iseqList,
			HttpSession session) {
		System.out.println(vo);
		
		vo.setId(((UsersVO)session.getAttribute("user")).getId());
		frameService.insertFrame(vo);
		
		FHIVO fhivo = new FHIVO(); 
		for(int iseq : iseqList) {
			fhivo.setIseq(iseq);
			
			frameService.insertFHI(fhivo);
		}
		
		return "redirect:frameService.do";
	}
}
