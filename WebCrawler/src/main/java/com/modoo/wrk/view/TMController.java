package com.modoo.wrk.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

import com.modoo.wrk.data.DataVO;
import com.modoo.wrk.data.impl.DataService;
import com.modoo.wrk.info.InfoVO;
import com.modoo.wrk.tm.TmVO;

@Controller
public class TMController {
	@Autowired
	private DataService dataService;
	
	// 일단 워드클라우드
	@SuppressWarnings("resource")
	@RequestMapping(value="/tm.do")
	public String tm(InfoVO ivo,TmVO tvo,Model model,HttpSession session) {
		System.out.println("[TMContorller] tm 처리");
			
		// 데이터 리스트 구성
		DataVO dvo = new DataVO();
		dvo.setIseq(ivo.getIseq());
		List<DataVO> dataList_ = dataService.getData(dvo);
		List<String> dataList = new ArrayList<String>();
		for(DataVO data : dataList_){
			dataList.add(data.getData());
		}
		String[] dataStrList = dataList.toArray(new String[dataList.size()]);
		
		// R 작업 수행
		RConnection r = null;
		REXP html_path = null;
			
		try {
			r = new RConnection();
			System.out.println("연결 성공");
			r.setStringEncoding("utf8");
			/* 리눅스용 
			r.eval("setwd(\"/rDownload\")");
			*/
			/* 윈도우용 */
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
			
			
			text_wordcnt_.asList();
			// 2. dataMap 구축
			/*
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
			*/
			
			// wordcloud
			r.eval("library(wordcloud2)");
			r.eval("mypath <- text_wordcnt %>% wordcloud2() %>% htmltools::html_print()");
			html_path = r.eval("mypath");
			System.out.println(html_path.asString());
			
			/* 리눅스용
			r.eval("system(\"sudo chmod 777 -R /tmp\")");
			 */
			
			String realPath = session.getServletContext().getRealPath("/confirmRview");
			System.out.println(realPath);
			FileInputStream fis = null;
			FileOutputStream fos = null;
			fis = new FileInputStream(html_path.asString()); 
			fos = new FileOutputStream(realPath + "/" + ivo.getIseq() + ".html");
				   
			byte[] buffer = new byte[1024];
			int readcount = 0;
				  
			model.addAttribute("viewSeq", ivo.getIseq());
			
			while((readcount=fis.read(buffer)) != -1) 
				fos.write(buffer, 0, readcount);    // 파일 복사 
		} catch (RserveException e) {
			e.printStackTrace();
		} catch (REngineException e) {
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			r.close();
		}
			
		return "tmConfirm.jsp";
	}
}
