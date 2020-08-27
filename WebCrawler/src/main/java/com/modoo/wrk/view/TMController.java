package com.modoo.wrk.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.modoo.wrk.data.DataVO;
import com.modoo.wrk.data.SearchVO;
import com.modoo.wrk.data.impl.DataService;
import com.modoo.wrk.info.InfoVO;
import com.modoo.wrk.tm.THIVO;
import com.modoo.wrk.tm.TVIVO;
import com.modoo.wrk.tm.TmVO;
import com.modoo.wrk.tm.impl.TmService;
import com.modoo.wrk.users.UsersVO;

@Controller
public class TMController {
	@Autowired
	private DataService dataService;
	@Autowired
	private TmService tmService;
	
	@RequestMapping("/tmService.do")
	public String tmService(HttpSession session, Model model,
			HttpServletRequest req) {
		TmVO tvo = new TmVO();
		
		tvo.setId(((UsersVO)session.getAttribute("user")).getId());
		
		String keyword = req.getParameter("keyword");
		
		List<TmVO> tmList = null;
		if(keyword != "" && keyword != null) {
			SearchVO search = new SearchVO();
			search.setId(tvo.getId());
			search.setKeyword(keyword);
			tmList = tmService.getTmListSearch(search);
		} else {
			tmList = tmService.getTmList(tvo);
		}
		
		// Sentiment 검사
		for(TmVO tm : tmList) {
			if(tm.getTtype().equals("sentiment")) {
				TVIVO tvivo = new TVIVO();
				tvivo.setTseq(tm.getTseq());
				tm.setTvi(tmService.getTVI(tvivo));
			}
		}
		
		model.addAttribute("tmList", tmList);
		
		return "tmService.jsp";
	}
	
	// 일단 워드클라우드
	@SuppressWarnings("resource")
	@RequestMapping(value="/tm.do")
	public String tm(InfoVO ivo,TmVO tvo,Model model,HttpSession session) {
		System.out.println("[TMContorller] tm 처리");
		String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
		
		// 데이터 리스트 구성
		DataVO dvo = new DataVO();
		dvo.setIseq(ivo.getIseq());
		List<DataVO> dataList_ = dataService.getData(dvo);
		List<String> dataList = new ArrayList<String>();
		for(DataVO data : dataList_){
			if(!data.getData().equals("")) {
				String inData = data.getData().replaceAll(match, "");
				if(!inData.equals("")) {
					dataList.add(inData);
				}
			}
		}
		String[] dataStrList = dataList.toArray(new String[dataList.size()]);
		
		for(String data : dataStrList ) {
			System.out.println(data);
		}
		
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
			
			if(tvo.getTtype().equals("wordcloud")) {
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
				//RList text_wordcnt = text_wordcnt_.asList();
				
				
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
				model.addAttribute("ttypeString", "워드클라우드");
				
				while((readcount=fis.read(buffer)) != -1) 
					fos.write(buffer, 0, readcount);    // 파일 복사 
			} else if(tvo.getTtype().equals("sna")) {
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
				r.eval("png(\'" + ivo.getIseq() + ".png\')");
				r.eval("plot(text_graph %>% as_tbl_graph() %>% "
						 + "ggraph() + "
						 + "geom_edge_link(aes(start_cap = label_rect(node1.name), end_cap = label_rect(node2.name))) + "
						 + "geom_node_text(aes(label=name)))");
				r.eval("dev.off()");

				String realPath = session.getServletContext().getRealPath("/confirmRview");
				System.out.println(realPath);
				FileInputStream fis = null;
				FileOutputStream fos = null;
				/* 리눅스용
				fis = new FileInputStream("/rDownload/snaTest.png"); 
				  */
				// 윈도우용 
				fis = new FileInputStream("c:\\Download\\" + ivo.getIseq() + ".png"); 
				 
				fos = new FileOutputStream(realPath+"/" + ivo.getIseq() + ".png");   
				   
				byte[] buffer = new byte[1024];
				int readcount = 0;
				  
				while((readcount=fis.read(buffer)) != -1) 
					fos.write(buffer, 0, readcount);    // 파일 복사 
				
				model.addAttribute("viewSeq",ivo.getIseq());
				model.addAttribute("ttypeString", "SNA");
			} else if(tvo.getTtype().equals("sentiment")) {
				String sentimentRealPath = session.getServletContext().getRealPath("/sentiment");
				System.out.println(sentimentRealPath);
				r.eval("setwd(\"c:\\\\rContent\")");
				
				r.eval("library(plyr)");
				r.eval("library(stringr)");
				r.eval("library(tidyverse)");
				
				r.assign("text", dataStrList);
				r.eval("positive <- read_lines(\'positive.txt\')");
				r.eval("positive = positive[-1]");
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
				
				// r.eval("png(\'" + ivo.getIseq() + ".png\')");
				// r.eval("pie(result_table, main=\"감성분석 결과\", col=c(\"blue\",\"red\",\"green\"), radius=0.8)");
				// r.eval("dev.off()");
				
				
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
				
				/*
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
				*/
				
				r.eval("detach(\"package:plyr\")");
				
				/*
				String realPath = session.getServletContext().getRealPath("/confirmRview");
				System.out.println(realPath);
				FileInputStream fis = null;
				FileOutputStream fos = null;
				*/
				/* 리눅스용
				fis = new FileInputStream("/rDownload/snaTest.png"); 
				  */
				// 윈도우용 
				/*
				fis = new FileInputStream("c:\\rContent\\" + ivo.getIseq() + ".png"); 
				 
				fos = new FileOutputStream(realPath+"/" + ivo.getIseq() + ".png");  
				   
				byte[] buffer = new byte[1024];
				int readcount = 0;
				  
				while((readcount=fis.read(buffer)) != -1) 
					fos.write(buffer, 0, readcount);    // 파일 복사 
				*/
				model.addAttribute("positive", pos_res);
				model.addAttribute("negative", neg_res);
				model.addAttribute("neutral", neu_res);
				model.addAttribute("viewSeq", ivo.getIseq());
				model.addAttribute("ttypeString", "감성분석");
			}
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
			
		model.addAttribute("ttype", tvo.getTtype());
		model.addAttribute("tc", tmService.getComment(tvo));
		
		return "tmConfirm.jsp";
	}
	
	@RequestMapping("tmAdd.do")
	public String tmAdd(TmVO tvo, THIVO thivo, TVIVO tvivo,HttpSession session) {
		System.out.println(tvo);
		System.out.println(thivo);
		
		tvo.setId(((UsersVO)session.getAttribute("user")).getId());
		
		tmService.insertTm(tvo);
		
		String realPath = session.getServletContext().getRealPath("/confirmRview");
		String savePath = session.getServletContext().getRealPath("/userRview");
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		String fileName = null;
		String fileNameTarget = null;
		String type = null;
		
		if(tvo.getTtype().equals("sentiment")) {
			tvivo.setTseq(tmService.getTmTop(tvo));
			tmService.insertTVI(tvivo);
		} else {
			if(tvo.getTtype().equals("wordcloud")) {
				type = ".html";
			} else if(tvo.getTtype().equals("sna")){
				type = ".png";
			} 
			
			// 복사할 confirmFile
			fileName = "/" + thivo.getIseq() + type; 
			// 붙여넣기 할 realFile의 경로
			fileNameTarget = "/" + tmService.getTmTop(tvo) + type;
			
			try {
				fis = new FileInputStream(realPath + fileName);
				fos = new FileOutputStream(savePath + fileNameTarget);
				
				byte[] buffer = new byte[1024];
				int readcount = 0;
				  
				while((readcount=fis.read(buffer)) != -1) 
					fos.write(buffer, 0, readcount);    // 파일 복사 
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// thi 삽입
		tmService.insertTHI(thivo);
		
		return "redirect:tmService.do";
	}
	
	@RequestMapping("deleteTm.do")
	public String deleteTm(TmVO vo) {
		tmService.deleteTm(vo);
		
		return "redirect:tmService.do";
	}
}
