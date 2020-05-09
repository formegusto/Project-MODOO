package com.crawler.view.tm;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.crawler.biz.data.DataVO;
import com.crawler.biz.data.impl.DataService;
import com.crawler.biz.info.InfoVO;
import com.crawler.biz.info.impl.InfoService;
import com.crawler.biz.tm.TmHaveInfoVO;
import com.crawler.biz.tm.TmVO;
import com.crawler.biz.tm.impl.TmService;
import com.crawler.biz.user.UserVO;
import com.crawler.biz.visual.VisualVO;

@Controller
public class TMController {
	@Autowired
	InfoService infoService;
	@Autowired
	DataService dataService;
	@Autowired
	TmService tmService;
	
	@SuppressWarnings("resource")
	@RequestMapping(value="/rUbuntuTest.do")
	public String rUbuntuTest(InfoVO vo,Model model,HttpSession session) {
		
		RConnection r = null;
		
		try {
			r = new RConnection();
			System.out.println("연결 성공");
			r.setStringEncoding("utf8");
			REXP pwd = r.eval("getwd()");
			model.addAttribute("rpwd",pwd.asString());
			r.setStringEncoding("utf8");
			
			r.eval("setwd(\"/rDownload\")");
			r.eval("library(rJava)");
			r.eval("library(KoNLP)");
			r.eval("library(reshape2)");
			r.eval("library(tidyverse)");
			
			
		} catch (RserveException e) {
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			r.close();
		}
		
		return "testResult.jsp";
	}
	
	@SuppressWarnings("resource")
	@RequestMapping(value="/sna.do")
	public String sna(InfoVO vo,Model model,HttpSession session) {
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
			/* 리눅스용
			r.eval("setwd(\"/rDownload\")");
			*/
			/* 윈도우용 */
			r.eval("setwd(\"c:\\\\Download\")");
			r.eval("library(rJava)");
			r.eval("library(KoNLP)");
			r.eval("library(reshape2)");
			r.eval("library(tidyverse)");;
			
			//사회 연결망 만들기
			r.assign("text", dataStrList);
			r.eval("text_sp <- SimplePos09(text)");
			r.eval("text_df <- text_sp %>% melt %>% as_tibble");
			r.eval("text_df <- text_df[,c(3,1)]");
			r.eval("text_noun <- text_df %>% mutate(noun=str_match(value,\'([가-힣]+)/N\')[,2]) %>% na.omit");
			r.eval("text_wordcnt <- text_noun %>% filter(str_length(noun)>=2) %>% count(noun,sort=TRUE)");
			r.eval("text_df2 <- text_noun %>% select(3,1)");
			r.eval("text_cnt <- text_wordcnt %>% head(15)");
			r.eval("text_df3 <- text_df2 %>% filter(noun %in% text_cnt$noun)");
			r.eval("library(igraph)");
			r.eval("library(tidygraph)");
			r.eval("library(ggraph)");
			r.eval("text_graph <- graph_from_data_frame(text_df3)");
			r.eval("V(text_graph)$type <- bipartite_mapping(text_graph)$type");
			r.eval("text_matrix <- as_incidence_matrix(text_graph) %*% t(as_incidence_matrix(text_graph))");
			r.eval("diag(text_matrix) <- 0");
			r.eval("text_graph <- graph_from_adjacency_matrix(text_matrix)");
			r.eval("png(\'snaTest.png\')");
			r.eval("plot(text_graph %>% as_tbl_graph() %>% "
					 + "ggraph() + "
					 + "geom_edge_link(aes(start_cap = label_rect(node1.name), end_cap = label_rect(node2.name))) + "
					 + "geom_node_text(aes(label=name)))");
			r.eval("dev.off()");

			String realPath = session.getServletContext().getRealPath("/rview");
			System.out.println(realPath);
			
			FileInputStream fis = null;
			FileOutputStream fos = null;
			/* 리눅스용
			 * fis = new FileInputStream("/rDownload/snaTest.png"); 
			 */
			/* 윈도우용 */
			fis = new FileInputStream("c:\\Download\\snaTest.png"); 
			fos = new FileOutputStream(realPath+"/snaTest.png");   
			   
			byte[] buffer = new byte[1024];
			int readcount = 0;
			  
			while((readcount=fis.read(buffer)) != -1) 
				fos.write(buffer, 0, readcount);    // 파일 복사 
			
			model.addAttribute("rtnseq",vo.getSeq());
		} catch (RserveException e) {
			e.printStackTrace();
		} catch (REngineException e) {
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
		
		return "snaResult.jsp";
	}
	
	@SuppressWarnings("resource")
	@RequestMapping(value="/sentimentAnal.do")
	public String sentimentAnal(InfoVO vo,Model model,HttpSession session) {
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
			/* 리눅스용
			r.eval("setwd(\"/rDownload\")");
			*/
			/* 윈도우용 */
			r.eval("setwd(\"c:\\\\Download\")");
			r.eval("library(plyr)");
			r.eval("library(stringr)");
			r.eval("library(tidyverse)");
			
			r.assign("text", dataStrList);
			/* 리눅스용
			 * r.eval("positive <- read_lines(\'positive\')");
			 */
			/* 윈도우용 */
			r.eval("positive <- read_lines(\'positive.txt\')");
			r.eval("positive = positive[-1]");
			/* 리눅스용
			 * r.eval("positive <- read_lines(\'negative\')");
			 */
			/* 윈도우용 */
			r.eval("negative <- read_lines(\'negative.txt\')");
			r.eval("negative = negative[-1]");
			r.eval("sentimental = function(sentences, positive, negative){" +
				  "  scores = laply(sentences, function(sentence, positive, negative) { " +
				       " sentence = gsub(\'[[:punct:]]\', \'\', sentence); " +
				       " sentence = gsub(\'[[:cntrl:]]\', \'\', sentence); " +
				       " sentence = gsub(\'\\\\d+\', \'\', sentence); " +
				       " word.list = str_split(sentence, \'\\\\s+\'); "+
				       " words = unlist(word.list); " +
				       " pos.matches = match(words, positive); " +
				       " neg.matches = match(words, negative); " +
				       " pos.matches = !is.na(pos.matches); " +
				       " neg.matches = !is.na(neg.matches); " +
				       " score = sum(pos.matches) - sum(neg.matches); " + 
				       " return(score); " +
				    " }, positive, negative); " +
				    " scores.df = data.frame(score=scores, text=sentences); " +
				    " return(scores.df); " +
				"}");
			r.eval("result = sentimental(text, positive, negative)");
			r.eval("result$color[result$score >= 1] = \"blue\"");
			r.eval("result$color[result$score == 0] = \"green\"");
			r.eval("result$color[result$score < 0] = \"red\"");
			r.eval("result$remark[result$score >= 1] = \"긍정\"");
			r.eval("result$remark[result$score == 0] = \"중립\"");
			r.eval("result$remark[result$score < 0] = \"부정\"");
			r.eval("result_table <- table(result$remark)");
			
			r.eval("png(\"test.png\")");
			r.eval("pie(result_table, main=\"감성분석 결과\", col=c(\"blue\",\"red\",\"green\"), radius=0.8)");
			r.eval("dev.off()");
			
			
			REXP pos_res_ = r.eval("result_table[\"긍정\"]");
			REXP neg_res_ = r.eval("result_table[\"부정\"]");
			REXP neu_res_ = r.eval("result_table[\"중립\"]");
			int pos_res = 0, neg_res = 0, neu_res = 0;
			if(pos_res_.asInteger() > -1) {
				pos_res = pos_res_.asInteger();
			}
			if(neg_res_.asInteger() > -1) {
				neg_res = neg_res_.asInteger();
			}
			if(neu_res_.asInteger() > -1) {
				neu_res = neu_res_.asInteger();
			}
			
			// 2. dataMap 구축
			Map<String, List<DataVO>> dataMap = new HashMap<String, List<DataVO>>();
			dataMap.put("positive", new ArrayList<DataVO>());
			dataMap.put("negative", new ArrayList<DataVO>());
			dataMap.put("neutrality", new ArrayList<DataVO>());
				
			DataVO posdata = new DataVO();
			posdata.setData(pos_res + "");
			dataMap.get("positive").add(posdata);
			DataVO negdata = new DataVO();
			negdata.setData(neg_res + "");
			dataMap.get("negative").add(negdata);
			DataVO neudata = new DataVO();
			neudata.setData(neu_res + "");
			dataMap.get("neutrality").add(neudata);
			model.addAttribute("dataMap",dataMap);
						
			List<InfoVO> infoList = new ArrayList<InfoVO>();
			InfoVO info_pos = new InfoVO();
			info_pos.setField("positive");
			InfoVO info_neg = new InfoVO();
			info_neg.setField("negative");
			InfoVO info_neu = new InfoVO();
			info_neu.setField("neutrality");
			infoList.add(info_pos);
			infoList.add(info_neg);
			infoList.add(info_neu);
						
			model.addAttribute("infoList", infoList);
			model.addAttribute("rtnseq",vo.getSeq());
			
			r.eval("detach(\"package:plyr\")");
			
			String realPath = session.getServletContext().getRealPath("/rview");
			System.out.println(realPath);
			
			FileInputStream fis = null;
			FileOutputStream fos = null;
			/* 리눅스용
			 * fis = new FileInputStream("/rDownload/snaTest.png"); 
			 */
			/* 윈도우용 */
			fis = new FileInputStream("c:\\Download\\test.png"); 
			fos = new FileOutputStream(realPath+"/test.png");   
			   
			byte[] buffer = new byte[1024];
			int readcount = 0;
			  
			while((readcount=fis.read(buffer)) != -1) 
				fos.write(buffer, 0, readcount);    // 파일 복사 
			
		} catch (RserveException e) {
			e.printStackTrace();
		} catch (REngineException e) {
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			r.close();
		}
		
		return "sentimentAnal.jsp";
	}
	
	@SuppressWarnings("resource")
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
			
			// wordcloud
			r.eval("library(wordcloud2)");
			r.eval("mypath <- text_wordcnt %>% wordcloud2() %>% htmltools::html_print()");
			html_path = r.eval("mypath");
			System.out.println(html_path.asString());
			
			String realPath = session.getServletContext().getRealPath("/rview");
			System.out.println(realPath);
			FileInputStream fis = null;
			FileOutputStream fos = null;
			fis = new FileInputStream(html_path.asString()); 
			fos = new FileOutputStream(realPath + "/test.html");
			   
			byte[] buffer = new byte[1024];
			int readcount = 0;
			  
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
		
		return "wordCountResult.jsp";
	}
	
	@RequestMapping(value="/tmAdd_proc.do")
	public String tmAdd(HttpSession session,Model model,
			HttpServletRequest request,
			@RequestParam String ttitle,
			@RequestParam String tcontent,
			@RequestParam String ttype,
			@RequestParam(value="ogField", required=true) List<String> ogFieldList,
			@RequestParam(value="title", required=true) List<String> titleList,
			@RequestParam(value="content", required=true) List<String> contentList,
			@RequestParam(value="link", required=true) List<String> linkList,
			@RequestParam(value="field", required=true) List<String> fieldList,
			@RequestParam(value="cssQuery", required=true) List<String> cssList) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] csv 넣는 기능 처리");
		
		List<InfoVO> infoList = new ArrayList<InfoVO>();
		for(int i=0;i<ogFieldList.size();i++) {
			// info 삽입
			InfoVO info = new InfoVO();
			info.setTitle(titleList.get(i));
			info.setContent(contentList.get(i));
			info.setLink(linkList.get(i));
			info.setField(fieldList.get(i));
			info.setCssQuery(cssList.get(i));
			info.setId(((UserVO)session.getAttribute("user")).getId());
			info.setItype(linkList.get(0));
			infoService.insertInfo(info);
			
			// 데이터 삽입
			String ogField = ogFieldList.get(i);
			String[] datas = request.getParameterValues(ogField);
			for(String data : datas) {
				DataVO dvo = new DataVO();
				dvo.setData(data);
				dataService.insertData(dvo);
			}
			
			// infoList 구축
			infoList.add(infoService.getInfoTop(info));
		}
		
		// tm 삽입
		TmVO tm = new TmVO();
		tm.setTitle(ttitle);
		tm.setContent(tcontent);
		tm.setTtype(ttype);
		tm.setId(((UserVO)session.getAttribute("user")).getId());
		tmService.insertTm(tm);
		
		// 파일 넣기
		String realPath = session.getServletContext().getRealPath("/rview");
		System.out.println(realPath);
		FileInputStream fis = null;
		FileOutputStream fos = null;
		String fileName = null;
		String fileNameTarget = null;
		TmVO topTm = tmService.getTmTop(tm);
		
		if(ttype.equals("wordcloud")) {
			fileNameTarget = "/test.html";
			fileName = "/" + topTm.getTseq() + ".html";
		} else if(ttype.equals("sentimentAnal")) {
			fileNameTarget = "/test.png";
			fileName = "/"+ topTm.getTseq() +".png";
		}
		try {
			fis = new FileInputStream(realPath + fileNameTarget);
			fos = new FileOutputStream(realPath + fileName);
			
			byte[] buffer = new byte[1024];
			int readcount = 0;
			  
			while((readcount=fis.read(buffer)) != -1) 
				fos.write(buffer, 0, readcount);    // 파일 복사 
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		// thi 삽입
		for(InfoVO info : infoList) {
			TmHaveInfoVO thi = new TmHaveInfoVO();
			thi.setInum(info.getSeq());
			tmService.insertTHI(thi);
		}
		
		return "tmObjectConfirm.do";
	}
	
	@RequestMapping(value="/snaAdd_proc.do")
	public String snaAdd(TmVO tm,HttpSession session,Model model,
			HttpServletRequest request) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] csv 넣는 기능 처리");
		
		// tm 삽입
		tm.setId(((UserVO)session.getAttribute("user")).getId());
		tmService.insertTm(tm);
		
		// 파일 넣기
		String realPath = session.getServletContext().getRealPath("/rview");
		System.out.println(realPath);
	
		
		TmVO topTm = tmService.getTmTop(tm);
		String fileName = "/" + topTm.getTseq() + ".png";
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			fis = new FileInputStream(realPath + "/snaTest.png");
			fos = new FileOutputStream(realPath + fileName);
			
			byte[] buffer = new byte[1024];
			int readcount = 0;
			  
			while((readcount=fis.read(buffer)) != -1) 
				fos.write(buffer, 0, readcount);    // 파일 복사 
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return "tmObjectConfirm.do";
	}
	
	@RequestMapping(value="/getTm.do")
	public String getTm(TmVO vo, HttpSession session, Model model) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] tm 게시판 상세 조회 기능 처리");
		
		model.addAttribute("tm", tmService.getTm(vo));
		
		return "getTm.jsp";
	}
}
