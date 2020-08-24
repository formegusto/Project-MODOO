package com.modoo.wrk.view;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

import au.com.bytecode.opencsv.CSVWriter;

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
		List<FrameVO> _frameList = null;
		
		if(keyword != "" && keyword != null) {
			SearchVO search = new SearchVO();
			search.setId(fvo.getId());
			search.setKeyword(keyword);
			_frameList = frameService.getFrameListSearch(search);
		} else {
			_frameList = frameService.getFrameList(fvo);
		}
		
		Random ran = new Random();
		List<FrameVO> frameList = new ArrayList<FrameVO>();
		for(FrameVO frame : _frameList) {
			List<FHIVO> fhiList = frameService.getFHIList(frame);
			List<String> dataList = new ArrayList<String>();
			
			for(int i=0;i<3;i++) {
				DataVO dvo = new DataVO();
				int f = ran.nextInt(fhiList.size());
				
				FHIVO fhi = fhiList.get(f);
				dvo.setIseq(fhi.getIseq());
				
				dataList.add(dataService.getDataRandOne(dvo));
			}
			
			frame.setDataList(dataList);
			frameList.add(frame);
		}
		
		model.addAttribute("frameList", frameList);
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
	
	@RequestMapping("/csvDownload.do")
	public String csvDownload(FrameVO vo, String ctitle,
			HttpSession session, Model model) {
		ctitle += ".csv";
		
		List<FHIVO> fhiList = frameService.getFHIList(vo);
		List<InfoVO> infoList = new ArrayList<InfoVO>();
		for(FHIVO fhi : fhiList) {
			InfoVO ivo = new InfoVO();
			
			ivo.setIseq(fhi.getIseq());
			infoList.add(infoService.getInfo(ivo));
		}
		
		Map<String, List<DataVO>> dataMap = new HashMap<String, List<DataVO>>();
		for(FHIVO fhi : fhiList) {
			DataVO dvo = new DataVO();
			dvo.setIseq(fhi.getIseq());
			List<DataVO> dataList = dataService.getData(dvo);
			dataMap.put(fhi.getIseq()+"", dataList);
		}
		
		List<String[]> csvList = new ArrayList<String[]>();
		String fieldList_ = "";
		for(int i=0;i<infoList.size();i++) {
			InfoVO info = infoList.get(i);
			if((i+1) == infoList.size())
				fieldList_ += (info.getField());
			else
				fieldList_ += (info.getField() + ",");
		}
		String[] fieldList = fieldList_.split(",");
		csvList.add(fieldList);
		
		List<List<String>> dataList_ = new ArrayList<List<String>>();
		InfoVO info_ = infoList.get(0);
		int listSize = dataMap.get(info_.getIseq()+"").size();
		for(int i=0;i<infoList.size();i++) {
			InfoVO info = infoList.get(i);
			List<DataVO> dataVOList = dataMap.get(info.getIseq() + "");
			if(i==0) {
				for(int j=0;j<listSize;j++) {
					DataVO dataVO = dataVOList.get(j);
					List<String> data_ = new ArrayList<String>();
					data_.add(dataVO.getData());
					dataList_.add(data_);
				}
			}
			else {
				if(listSize < dataVOList.size()) {
					for(int j=0;j<i;j++) {
						if(j==0) {
							for(int k=listSize;k<dataVOList.size();k++) {
								List<String> data_ = new ArrayList<String>();
								data_.add("");
								dataList_.add(data_);
							}
						} else {
							for(int k=listSize;k<dataVOList.size();k++) {
								dataList_.get(k).add("");
							}
						}
					}
					listSize = dataVOList.size();
				}
				for(int j=0;j<dataVOList.size();j++) {
					DataVO dataVO = dataVOList.get(j);
					dataList_.get(j).add(dataVO.getData());
				}
				if(listSize > dataVOList.size()) {
					for(int j=dataVOList.size();j<listSize;j++) {
						dataList_.get(j).add("");
					}
				}
			}
		}
		
		for(List<String> data : dataList_) {
			String[] dataList = data.toArray(new String[data.size()]);
			csvList.add(dataList);
		}
		
		// Convert CSV
		CSVWriter writer = null;
		String realPath = session.getServletContext().getRealPath("/download/csv");
		System.out.println(realPath);
		try {
			writer = new CSVWriter(new FileWriter(realPath+"/"+ctitle));
			for(String[] csvStr : csvList)
				writer.writeNext(csvStr);
			writer.close();
		} catch (IOException e) {
			System.out.println("IOException!");
			e.printStackTrace();
		}
		
		return "redirect:dataServiceByFrame.do?fseq=" + vo.getFseq() + "&mode=read&ctitle=" + ctitle;
	}
}
