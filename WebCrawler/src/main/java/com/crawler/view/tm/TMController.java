package com.crawler.view.tm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crawler.biz.data.DataVO;
import com.crawler.biz.data.impl.DataService;
import com.crawler.biz.info.InfoVO;
import com.crawler.biz.info.impl.InfoService;

@Controller
public class TMController {
	@Autowired
	InfoService infoService;
	@Autowired
	DataService dataService;
	
	@RequestMapping(value="/wordCount.do")
	public String wordCount(InfoVO vo,Model model,HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 정보 상세 보기 기능 처리");
		
		DataVO dvo = new DataVO();
		dvo.setInum(vo.getSeq());
		List<DataVO> dataList_ = dataService.getData(dvo);
		List<String> dataList = new ArrayList<String>();
		for(DataVO data : dataList_) {
			dataList.add(data.getData());
		}
		String[] dataStrList = dataList.toArray(new String[dataList.size()]);
		RConnection r = null;
		
		try {
			r = new RConnection();
			System.out.println("연결 성공");
			r.setStringEncoding("utf8");
			r.eval("setwd(\"c:\\\\Download\")");
			r.eval("library(rJava)");
			r.eval("library(KoNLP)");
			r.eval("library(reshape2)");
			r.eval("library(tidyverse)");
			
			//리스트 만들기
			r.assign("text", dataStrList);
			r.eval("text_sp <- SimplePos09(text)");
			r.eval("text_df <- text_sp %>% melt %>% as_tibble");
			r.eval("text_df <- text_df[,c(3,1)]");
			r.eval("text_noun <- text_df %>% mutate(noun=str_match(value,\'([가-힣]+)/N\')[,2]) %>% na.omit");		
			r.eval("text_wordcnt <- text_noun %>% filter(str_length(noun)>=2) %>% count(noun,sort=TRUE)");
			
			REXP text_wordcnt_ = r.eval("text_wordcnt");
			RList text_wordcnt = text_wordcnt_.asList();
			
			// 2. dataMap 구축
			Map<String, List<DataVO>> dataMap = new HashMap<String, List<DataVO>>();
			dataMap.put("word", new ArrayList<DataVO>());
			dataMap.put("cnt", new ArrayList<DataVO>());
			int rows = text_wordcnt.at(0).length();
			System.out.println("rows ==> " + rows);
			for(int i=0;i<rows;i++) {
				DataVO worddata = new DataVO();
				worddata.setData(text_wordcnt.at(0).asStrings()[i]);
				dataMap.get("word").add(worddata);
				
				DataVO cntdata = new DataVO();
				cntdata.setData(text_wordcnt.at(1).asStrings()[i]);
				dataMap.get("cnt").add(cntdata);
			}
			
			model.addAttribute("dataMap",dataMap);
			
			List<InfoVO> infoList = new ArrayList<InfoVO>();
			InfoVO info_word = new InfoVO();
			info_word.setField("word");
			InfoVO info_cnt = new InfoVO();
			info_cnt.setField("cnt");
			infoList.add(info_word);
			infoList.add(info_cnt);
			
			model.addAttribute("infoList", infoList);
			model.addAttribute("rtnseq",vo.getSeq());

		} catch (RserveException e) {
			e.printStackTrace();
		} catch (REngineException e) {
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			r.close();
		}
		
		return "wordCountResult.jsp";
	}
}
