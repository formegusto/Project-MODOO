package com.modoo.wrk.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

@Controller
public class DataController {
	@Autowired
	private InfoService infoService;
	@Autowired
	private DataService dataService;
	@Autowired
	private FrameService frameService;
	
	@RequestMapping(value = {"/dataService.do", "/dataServiceByFrame.do"})
	public String infoService(InfoVO vo, FrameVO fvo,String mode, String keyword, String ctitle,
			Model model, HttpServletRequest req) {
		System.out.println(keyword);
		System.out.println(ctitle);
		
		String page = "dataService.jsp";
		String URI = req.getRequestURI();
		
		if(URI.equals("/MODOO/dataService.do")) {
			if(mode.equals("delete")) {
				page = "dataServiceDelete.jsp";
			}
			if(mode.equals("update")) {
				page = "dataServiceUpdate.jsp";
			}
			if(mode.equals("write")) {
				page = "dataServiceInsert.jsp";
			}
			
			InfoVO info = infoService.getInfo(vo);
			
			model.addAttribute("info", info);
			
			if(keyword == null) {
				DataVO dvo = new DataVO();
				dvo.setIseq(vo.getIseq());
				
				model.addAttribute("dataList", dataService.getData(dvo));
			} else {
				SearchVO svo = new SearchVO();
				svo.setIseq(vo.getIseq());
				svo.setKeyword(keyword);
				
				model.addAttribute("dataList", dataService.getDataSearch(svo));
			}
		} else {
			page = "dataServiceByFrame.jsp";
			if(mode.equals("delete")) {
				page = "dataServiceByFrameDelete.jsp";
			}
			if(mode.equals("update")) {
				page = "dataServiceByFrameUpdate.jsp";
			}
			if(mode.equals("write")) {
				page = "dataServiceByFrameInsert.jsp";
			}
			
			FrameVO frame = frameService.getFrame(fvo);
			List<FHIVO> fhiList = frameService.getFHIList(fvo);
			
			if(keyword == null) {
				for(int i=0;i<fhiList.size();i++) {
					FHIVO fhi = fhiList.get(i);
					
					InfoVO ivo = new InfoVO();
					ivo.setIseq(fhi.getIseq());
					
					InfoVO info = infoService.getInfo(ivo);
					
					DataVO dvo = new DataVO();
					dvo.setIseq(fhi.getIseq());
					
					List<DataVO> dataList = dataService.getData(dvo);
					
					fhi.setField(info.getField());
					fhi.setDataList(dataList);
					
					fhiList.set(i, fhi);
				}
			} else {
				for(int i=0;i<fhiList.size();i++) {
					FHIVO fhi = fhiList.get(i);
					
					InfoVO ivo = new InfoVO();
					ivo.setIseq(fhi.getIseq());
					
					InfoVO info = infoService.getInfo(ivo);
					
					DataVO dvo = new DataVO();
					dvo.setIseq(fhi.getIseq());
					
					SearchVO svo = new SearchVO();
					svo.setIseq(info.getIseq());
					svo.setKeyword(keyword);
					
					List<DataVO> dataList = dataService.getDataSearch(svo);
					
					fhi.setField(info.getField());
					fhi.setDataList(dataList);
					
					fhiList.set(i, fhi);
				}
			}
			
			
			
			model.addAttribute("frame", frame);
			model.addAttribute("fhiList", fhiList);
			
			if(ctitle != null && !ctitle.equals("")) {
				model.addAttribute("ctitle", ctitle);
			}
		}
		
		return page;
	}
	
	@RequestMapping(value = {"/deleteData.do", "/deleteDataByFrame.do"})
	public String deleteData(String dseqList,
			InfoVO info, FrameVO frame,
			HttpServletRequest req) {
		String[] dseqList_ = dseqList.split(",");
		String page = "redirect:dataService.do?iseq=" + info.getIseq() + "&mode=delete";
		String URI = req.getRequestURI();
		
		if(URI.equals("/MODOO/deleteDataByFrame.do")) {
			page = "redirect:dataServiceByFrame.do?fseq=" + frame.getFseq() + "&mode=delete";
		} 
		
		DataVO vo = new DataVO();
		
		for(String dseq : dseqList_) {
			vo.setDseq(Integer.parseInt(dseq));
			dataService.deleteData(vo);
		}
		
		return page;
	}
	
	@RequestMapping(value = {"/updateData.do", "/updateDataByFrame.do"})
	public String updateData(String[] dseqList, String[] dataList,
			InfoVO info, FrameVO frame,
			HttpServletRequest req) {
		System.out.println(dseqList);
		System.out.println(dataList);
		
		String page = "redirect:dataService.do?iseq=" + info.getIseq() + "&mode=update";
		String URI = req.getRequestURI();
		
		if(URI.equals("/MODOO/updateDataByFrame.do")) {
			page = "redirect:dataServiceByFrame.do?fseq=" + frame.getFseq() + "&mode=update";
		} 
		
		DataVO vo = new DataVO();
		for(int i=0;i<dseqList.length;i++) {
			vo.setDseq(Integer.parseInt(dseqList[i]));
			vo.setData(dataList[i]);
			
			dataService.updateData(vo);
		}
		
		return page;
	}
}
