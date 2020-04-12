package com.crawler.view.data;

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

@Controller
public class DataController {
	@Autowired
	DataService dataService;
	
	@RequestMapping(value="/deleteDataSeq.do")
	public String deleteDataSeq(InfoVO ivo, Model model, DataVO dvo, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		
		dataService.deleteDataSeq(dvo);
		model.addAttribute("seq",ivo.getSeq());
		
		return "getInfo.do?";
	}
	
	@RequestMapping(value="/changeData.do")
	public String changeData(InfoVO ivo, Model model, HttpSession session,
			@RequestParam String dseqChangeList) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		
		System.out.println(ivo);
		System.out.println(dseqChangeList);

		String[] dseqList = dseqChangeList.split(",");
		List<DataVO> dataList = new ArrayList<DataVO>();
		for(String dseq : dseqList) {
			DataVO vo = new DataVO();
			vo.setDseq(Integer.parseInt(dseq));
			dataList.add(dataService.getDataDseq(vo));
		}
		
		for(String dseq : dseqList) {
			DataVO vo = new DataVO();
			vo.setDseq(Integer.parseInt(dseq));
			dataService.deleteDataSeq(vo);
		}
		
		int tmp = dataList.get(0).getDseq();
		dataList.get(0).setDseq(dataList.get(1).getDseq());
		dataList.get(1).setDseq(tmp);
		for(DataVO dvo : dataList) {
			dataService.insertDataAll(dvo);
		}
		
		model.addAttribute("info", ivo);

		return "getInfo.do";
	}
	
	// 데이터 업데이트 적용
	@RequestMapping(value="/updateDataContent.do")
	public String updateDataContent(@RequestParam(value="data", required=true) List<String> datas,
			@RequestParam String updateDseqList,
			InfoVO ivo, Model model, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 크롤링 데이터 내용 업데이트 기능 처리");

		System.out.println(ivo);
		// 1. Data 객체 얻기
		DataVO vo = new DataVO();
		vo.setInum(ivo.getSeq());
		List<DataVO> dataList = dataService.getData(vo);
		
		// 2. update 해야 하는 데이터 인덱스 찾고 업데이트
		String[] updateDseqList_ = updateDseqList.split(",");
		for(int i=0;i<dataList.size();i++) {
			for(String dseq : updateDseqList_) {
				if(dataList.get(i).getDseq() == Integer.parseInt(dseq)) {
					DataVO data = new DataVO();
					data.setDseq(Integer.parseInt(dseq));
					data.setData(datas.get(i));
					dataService.updateData(data);
					break;
				}
			}
		}
		
		// 3. session에 객체 저장
		model.addAttribute("info",ivo);
		return "getInfo.do";
	}
	
	@RequestMapping(value="/replaceData.do")
	public String replaceData(@RequestParam String word, @RequestParam String rword,
			InfoVO ivo, Model model, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 크롤링 데이터 내용 업데이트 기능 처리");

		System.out.println(ivo);
		// 1. Data 객체 얻기
		DataVO vo = new DataVO();
		vo.setInum(ivo.getSeq());
		List<DataVO> dataList = dataService.getData(vo);
		
		if(rword == null) {
			rword = "";
		}
		if(word.equals("|") || word.equals("*") || word.equals("+") || word.equals("$") ){
			word = "[" + word + "]";
		}
		
		// 2. update 해야 하는 데이터 인덱스 찾고 업데이트
		for(DataVO data : dataList) {
			data.setData(data.getData().replaceAll(word, rword));
			dataService.updateData(data);
		}
		
		// 3. session에 객체 저장
		model.addAttribute("info",ivo);
		return "getInfo.do";
	}
	
	@RequestMapping(value="/cutData.do")
	public String cutData(@RequestParam String sindex, @RequestParam String eindex,
			InfoVO ivo, Model model, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 크롤링 데이터 내용 업데이트 기능 처리");

		System.out.println(ivo);
		// 1. Data 객체 얻기
		DataVO vo = new DataVO();
		vo.setInum(ivo.getSeq());
		List<DataVO> dataList = dataService.getData(vo);
		
		int sindex_, eindex_;
		if(sindex == null || sindex == "") {
			sindex_ = 0;
		} else {
			sindex_ = Integer.parseInt(sindex);
		}
		if(eindex == null || eindex == "") {
			eindex_ = -1;
		} else {
			eindex_ = Integer.parseInt(eindex);
		}
		
		// 2. update 해야 하는 데이터 인덱스 찾고 업데이트
		for(DataVO data : dataList) {
			if(sindex_ >= (data.getData().length())) {
				data.setData(data.getData().substring(0, data.getData().length()));
			} else {
				if(eindex_  == -1) {
					data.setData(data.getData().substring(sindex_, data.getData().length()));
				} else if(eindex_ >= (data.getData().length()+1)) {
					data.setData(data.getData().substring(sindex_, data.getData().length()));
				} else {
					data.setData(data.getData().substring(sindex_, eindex_));
				}
			}
			
			dataService.updateData(data);
		}
		
		// 3. session에 객체 저장
		model.addAttribute("info",ivo);
		return "getInfo.do";
	}
}
