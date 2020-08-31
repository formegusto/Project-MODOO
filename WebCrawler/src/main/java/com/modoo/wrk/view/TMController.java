package com.modoo.wrk.view;

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

import com.modoo.wrk.board.impl.BoardService;
import com.modoo.wrk.data.DataVO;
import com.modoo.wrk.data.SearchVO;
import com.modoo.wrk.data.impl.DataService;
import com.modoo.wrk.frame.FHIVO;
import com.modoo.wrk.frame.FrameVO;
import com.modoo.wrk.frame.impl.FrameService;
import com.modoo.wrk.info.InfoVO;
import com.modoo.wrk.info.impl.InfoService;
import com.modoo.wrk.room.impl.RoomService;
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
	@Autowired
	private InfoService infoService;
	@Autowired
	private FrameService frameService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private RoomService roomService;
	
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
				boolean chk = true;
				// 빈칸 검사
				if(!inData.equals("") ) {
					String[] inDataArr = inData.split(" ");
					// 길이 검사
					for(String inDataMem : inDataArr) {
						if(inDataMem.length() > 10) {
							chk = false;
							break;
						}
					}
					if(chk) {
						dataList.add(inData);
					}
				}
			}
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
				RList text_wordcnt = text_wordcnt_.asList();
				
				
				text_wordcnt_.asList();
				
				List<String> wordList = new ArrayList<String>();
				List<String> cntList = new ArrayList<String>();
				int rows = text_wordcnt.at(0).length();
				System.out.println("rows ==> " + rows);
				for(int i=0;i<rows;i++) {
					wordList.add(text_wordcnt.at(0).asStrings()[i]);
					cntList.add(text_wordcnt.at(1).asStrings()[i]);
				}
				
				model.addAttribute("wordList", wordList);
				model.addAttribute("cntList", cntList);
				
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
				r.eval("detach(\"package:plyr\")");
				
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
	public String tmAdd(TmVO tvo, THIVO thivo, TVIVO tvivo,HttpSession session,
			HttpServletRequest req) {
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
				
				String saveFrame = req.getParameter("saveFrame");
				
				if(saveFrame.equals("yes")) {
					String wordList_ = req.getParameter("wordList");
					String cntList_ = req.getParameter("cntList");
					
					String[] wordList = wordList_.substring(1, wordList_.length()-1).split(",");
					String[] cntList = cntList_.substring(1, cntList_.length()-1).split(",");
					
					InfoVO wordInfo = new InfoVO();
					wordInfo.setTitle(tvo.getTitle() + " (단어)");
					wordInfo.setId(((UsersVO)session.getAttribute("user")).getId());
					wordInfo.setContent(tvo.getTitle() + " wordcloud 결과물");
					wordInfo.setCssQuery("empty");
					wordInfo.setField("단어");
					wordInfo.setLink("empty");
					wordInfo.setItype("tm");
					infoService.insertInfo(wordInfo);
					
					for(String word: wordList) {
						DataVO dvo = new DataVO();
						dvo.setData(word);
						dataService.insertData(dvo);
					}
					
					FHIVO wordFHI = new FHIVO();
					wordFHI.setIseq(infoService.getInfoTop());
					
					InfoVO cntInfo = new InfoVO();
					cntInfo.setTitle(tvo.getTitle() + " (빈도수)");
					cntInfo.setId(((UsersVO)session.getAttribute("user")).getId());
					cntInfo.setContent(tvo.getTitle() + " wordcloud 결과물");
					cntInfo.setCssQuery("empty");
					cntInfo.setField("빈도수");
					cntInfo.setLink("empty");
					cntInfo.setItype("tm");
					
					infoService.insertInfo(cntInfo);
					for(String cnt: cntList) {
						DataVO dvo = new DataVO();
						dvo.setData(cnt);
						dataService.insertData(dvo);
					}
					
					FHIVO cntFHI = new FHIVO();
					cntFHI.setIseq(infoService.getInfoTop());
					
					FrameVO fvo = new FrameVO();
					fvo.setId(((UsersVO)session.getAttribute("user")).getId());
					fvo.setTitle(tvo.getTitle() + " wordcloud 결과물");
					
					frameService.insertFrame(fvo);
					frameService.insertFHI(wordFHI);
					frameService.insertFHI(cntFHI);
				}
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
		
		Map<String, Object> payload = new HashMap<String, Object>();
		{
			payload.put("type", "tm");
			payload.put("seq", vo.getTseq());
		}
		boardService.clearBHD(payload);
		roomService.clearRHD(payload);
		
		return "redirect:tmService.do";
	}
}
