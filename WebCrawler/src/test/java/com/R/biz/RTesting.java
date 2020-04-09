package com.R.biz;

import java.io.IOException;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class RTesting {

	public static void main(String[] args) {
		RConnection r = null;
		try {
			r = new RConnection();
			r.setStringEncoding("utf8");
			r.eval("setwd(\"c:\\\\Download\")");
			REXP pwd = r.eval("getwd()");
			System.out.println("getwd() ==> " + pwd.asString());
			r.eval("library(rJava)");
			r.eval("library(KoNLP)");
			r.eval("library(reshape2)");
			r.eval("library(tidyverse)");
			
			/* List 정리
			 * List<List<String>> 으로 이루어져있었다.
			 * 열 번호가 리스트의 갯수이고.
			 * 행 번호가 멤버의 개수
			 * 열 번호가 최상위가 된다. 
			 *       get(0) get(1) get(2) get(3) ...
			 * get(0)  -     -       -      -
			 * get(1)  -     -       -      -
			 *   의 형태
			 */
			System.out.println("------SimplePos09Test------");
			r.eval("musinsa_text <- read_lines(\'musinsa.csv\')");
			r.eval("musinsa_sp <- SimplePos09(musinsa_text)"); // 결과물은 List<List<String>
			REXP musinsa_sp_ = r.eval("musinsa_sp");
			RList musinsa_sp = musinsa_sp_.asList();
			int cols = musinsa_sp.size();
			System.out.println("cols ==> " + cols);
			int rows = musinsa_sp.at(0).length();
			System.out.println("rows ==> " + rows);
			for(int i=0;i<cols;i++) {
				System.out.println("---[" + i + "]---");
				for(int j=0;j<rows;j++) {
					System.out.println(musinsa_sp.at(i).asList().at(j).asString());
				}
			}
			
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/* DataFrame 정리
			 * List<String[]> 으로 이루어져있었다.
			 * 열 번호가 멤버의 개수이고
			 * 행 번호가 String 배열의 개수인 것.
			 * 행 번호가 최상위가 된다.
			 *       [1][2][3]
			 * get(0) -  -  -
			 * get(1) -  -  -
			 * get(2) -  -  -
			 * get(3) -  -  - 
			 * ...
			 */
			System.out.println("------meltTest------");
			r.eval("musinsa_df <- musinsa_sp %>% melt %>% as_tibble");
			REXP musinsa_df_ = r.eval("musinsa_df");
			RList musinsa_df = musinsa_df_.asList();
			cols = musinsa_df.size();
			System.out.println("cols ==> " + cols);
			rows = musinsa_df.at(0).length();
			System.out.println("rows ==> " + rows);
			for(int i=0;i<rows;i++) {
				System.out.println("---[" + i + "]---");
				for(int j=0;j<cols;j++) {
					System.out.println(musinsa_df.at(j).asStrings()[i]);
				}
			}
			r.eval("musinsa_df <- musinsa_df[,c(3,1)]");
			
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("------nounTest------");
			System.out.println("musinsa_noun <- musinsa_df %>% mutate(noun=str_match(value,\'([가-힣]+)/N\')[,2]) %>% na.omit");
			r.eval("musinsa_noun <- musinsa_df %>% mutate(noun=str_match(value,\'([가-힣]+)/N\')[,2]) %>% na.omit");
			REXP musinsa_noun_ = r.eval("musinsa_noun");
			RList musinsa_noun = musinsa_noun_.asList();
			cols = musinsa_noun.size();
			System.out.println("cols ==> " + cols);
			rows = musinsa_noun.at(0).length();
			System.out.println("rows ==> " + rows);
			for(int i=0;i<rows;i++) {
				System.out.println("---[" + i + "]---");
				for(int j=0;j<cols;j++) {
					System.out.println(musinsa_noun.at(j).asStrings()[i]);
				}
			}
			
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("------cntTest------");
			System.out.println("musinsa_wordcnt <- musinsa_noun %>% filter(str_length(noun)>=2) %>% count(noun,sort=TRUE)");
			r.eval("musinsa_wordcnt <- musinsa_noun %>% filter(str_length(noun)>=2) %>% count(noun,sort=TRUE)");
			REXP musinsa_wordcnt_ = r.eval("musinsa_wordcnt");
			RList musinsa_wordcnt = musinsa_wordcnt_.asList();
			cols = musinsa_wordcnt.size();
			System.out.println("cols ==> " + cols);
			rows = musinsa_wordcnt.at(0).length();
			System.out.println("rows ==> " + rows);
			for(int i=0;i<rows;i++) {
				System.out.println("---[" + i + "]---");
				for(int j=0;j<cols;j++) {
					System.out.println(musinsa_wordcnt.at(j).asStrings()[i]);
				}
			}
			
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("------wordcloud2Test------");
			r.eval("library(wordcloud2)");
			r.eval("mypath <- musinsa_wordcnt %>% wordcloud2() %>% htmltools::html_print()");
			REXP path = r.eval("mypath");
			System.out.println("wordCloud path ==> " + path.asString());
			
			System.out.println("------cnt 해체------");
			r.eval("library(igraph)");
			r.eval("library(tidygraph)");
			r.eval("library(ggraph)");
			r.eval("musinsa_cnt <- musinsa_wordcnt %>% head(15)");
			REXP musinsa_cnt_ = r.eval("musinsa_cnt");
			RList musinsa_cnt = musinsa_cnt_.asList();
			cols = musinsa_cnt.size();
			System.out.println("cols ==> " + cols);
			rows = musinsa_cnt.at(0).length();
			System.out.println("rows ==> " + rows);
			for(int i=0;i<rows;i++) {
				System.out.println("---[" + i + "]---");
				for(int j=0;j<cols;j++) {
					System.out.println(musinsa_cnt.at(j).asStrings()[i]);
				}
			}
			
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("------꼬리표 해체------");
			r.eval("musinsa_df2 <- musinsa_noun %>% select(3,1)");
			REXP musinsa_df2_ = r.eval("musinsa_df2");
			RList musinsa_df2 = musinsa_df2_.asList();
			cols = musinsa_df2.size();
			System.out.println("cols ==> " + cols);
			rows = musinsa_df2.at(0).length();
			System.out.println("rows ==> " + rows);
			for(int i=0;i<rows;i++) {
				System.out.println("---[" + i + "]---");
				for(int j=0;j<cols;j++) {
					System.out.println(musinsa_df2.at(j).asStrings()[i]);
				}
			}
			
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("------같은열 단어------");
			r.eval("musinsa_df3 <- musinsa_df2 %>% filter(noun %in% musinsa_cnt$noun)");
			REXP musinsa_df3_ = r.eval("musinsa_df3");
			RList musinsa_df3 = musinsa_df3_.asList();
			cols = musinsa_df3.size();
			System.out.println("cols ==> " + cols);
			rows = musinsa_df3.at(0).length();
			System.out.println("rows ==> " + rows);
			for(int i=0;i<rows;i++) {
				System.out.println("---[" + i + "]---");
				for(int j=0;j<cols;j++) {
					System.out.println(musinsa_df3.at(j).asStrings()[i]);
				}
			}
			
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// 출력 불가
			// graph는 R의 의해서 데이터가 출력되는 것 같다.
			// 그래서 문자열로 뽑아내는 것이 힘들다. 나중에 시간되면 해봐야지
			System.out.println("------이분그래프------");
			r.eval("musinsa_graph <- graph_from_data_frame(musinsa_df3)");
			r.eval("V(musinsa_graph)$type <- bipartite_mapping(musinsa_graph)$type");
			r.eval("musinsa_matrix <- as_incidence_matrix(musinsa_graph) %*% t(as_incidence_matrix(musinsa_graph))");
			r.eval("diag(musinsa_matrix) <- 0");
			r.eval("musinsa_graph <- graph_from_adjacency_matrix(musinsa_matrix)");
			System.out.println("------그래픽변경------");
			r.eval("png(\'testPlot_2.png\')");
			//r.eval("plot(musinsa_graph)"); // png 없이 그냥 띄우면 서버에서 렉걸림
			//plot 함수 안써주면 서버 쪽에서 안 기다려준다. image 파일이 빈 그림으로 생성된다.
			r.eval("plot(musinsa_graph %>% as_tbl_graph() %>% "
			 + "ggraph() + "
			 + "geom_edge_link(aes(start_cap = label_rect(node1.name), end_cap = label_rect(node2.name))) + "
			 + "geom_node_text(aes(label=name)))");
			r.eval("dev.off()");
		} catch (RserveException e) {
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			r.close();
		}
	}

}
