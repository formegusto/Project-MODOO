package com.modoo.wrk.view;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.modoo.wrk.data.DataVO;
import com.modoo.wrk.data.impl.DataService;
import com.modoo.wrk.users.UsersVO;
import com.modoo.wrk.visual.VHIVO;
import com.modoo.wrk.visual.VisualVO;
import com.modoo.wrk.visual.impl.VisualService;

@Controller
public class VisualController {
	@Autowired
	private DataService dataService;
	@Autowired
	private VisualService visualService;
	
	@RequestMapping("visualService.do")
	public String visualService(Model model, HttpSession session) {
		VisualVO vo = new VisualVO();
		vo.setId(((UsersVO)session.getAttribute("user")).getId());
		
		// VisualList
		List<VisualVO> visualList = visualService.getVisualList(vo);
		
		// datas, labels 구축
		VisualVO tmpVisual = new VisualVO();
		for(int i=0;i<visualList.size();i++) {
			tmpVisual = visualList.get(i);
			List<VHIVO> vhiList = visualService.getVHIList(tmpVisual);
			
			for(VHIVO vhi : vhiList) {
				DataVO dvo = new DataVO();
				dvo.setIseq(vhi.getIseq());
				List<String> dataList = dataService.getDataNotVO(dvo);
				if(vhi.getDtype().equals("datas")) {
					for(int j=0;j<dataList.size();j++) {
						String data = dataList.get(j);
						data = data.replaceAll("[^0-9]", "");
						
						dataList.set(j, data);
					}
					tmpVisual.setDatas(dataList);
				}
				if(vhi.getDtype().equals("labels")) {
					for(int j=0;j<dataList.size();j++) {
						String data = dataList.get(j);
						data = "\'" + data + "\'";
						
						dataList.set(j, data);
					}
					tmpVisual.setLabels(dataList);
				}
			}
			visualList.set(i, tmpVisual);
		}
		
		model.addAttribute("visualList", visualList);
		
		return "visualService.jsp";
	}
	
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
		} else if(color.equals("green")) {
			color = "\'rgb(64, 253, 106)\'";
		} else if(color.equals("purple")) {
			color = "\'rgb(226, 175, 244)\'";
		}
		
		model.addAttribute("vtype", "\'" + vtype + "\'");
		model.addAttribute("color", color);
		model.addAttribute("numList", numList);
		model.addAttribute("strList", strList);		
		model.addAttribute("numIseq", numIseq);
		model.addAttribute("strIseq", strIseq);
		
		return "visualConfirm.jsp";
	}
	
	@RequestMapping("visualAdd.do")
	public String visualAdd(VisualVO vo, Integer numIseq, Integer strIseq,
			HttpSession session) {
		// visual insert
		vo.setId(((UsersVO)session.getAttribute("user")).getId());
		visualService.insertVisual(vo);
		
		// VHI (Num) insert
		VHIVO vhivo_1 = new VHIVO();
		vhivo_1.setIseq(numIseq);
		vhivo_1.setDtype("datas");
		visualService.insertVHI(vhivo_1);

		// VHI (Str) insert
		VHIVO vhivo_2 = new VHIVO();
		vhivo_2.setIseq(strIseq);
		vhivo_2.setDtype("labels");
		visualService.insertVHI(vhivo_2);
		
		return "redirect:visualService.do";
	}
}
