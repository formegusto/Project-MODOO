package com.modoo.wrk.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.modoo.wrk.data.DataVO;
import com.modoo.wrk.data.impl.DataService;

@Controller
public class VisualController {
	@Autowired
	private DataService dataService;
	
	@RequestMapping("visualMake.do")
	public String visualMake(Integer numIseq, Integer strIseq, String color, String vtype,
			Model model) {
		System.out.println("숫자 리스트 => " + numIseq);
		System.out.println("문자 리스트 => " + strIseq);
		
		DataVO vo_1 = new DataVO();
		vo_1.setIseq(numIseq);
		List<String> numList = dataService.getDataNotVO(vo_1);
		
		DataVO vo_2 = new DataVO();
		vo_2.setIseq(strIseq);
		List<String> strList = dataService.getDataNotVO(vo_2);
		
		for(int i=0;i<numList.size();i++) {
			String num = numList.get(i);
			num = num.replaceAll("[^0-9]", "");
			
			numList.set(i,num);
		}
		
		for(int i=0;i<strList.size();i++) {
			String str = strList.get(i);
			str = "\'" + str + "\'";
			
			strList.set(i, str);
		}
		
		if(color.equals("pink")) {
			color = "\'rgb(255, 99, 132)\'";
		} else if(color.equals("skyblue")) {
			color = "\'rgb(146, 205, 177)\'";
		}
		
		model.addAttribute("vtype", "\'" + vtype + "\'");
		model.addAttribute("color", color);
		model.addAttribute("numList", numList);
		model.addAttribute("strList", strList);		
		
		return "visualConfirm.jsp";
	}
}
