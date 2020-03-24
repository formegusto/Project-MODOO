package com.crawler.biz;

import java.util.ArrayList;

import com.crawler.biz.common.WCrawl;
import com.crawler.biz.data.DataVO;
import com.crawler.biz.info.InfoVO;

public class CrawlerTest {
	public static void main(String[] args) {
		InfoVO vo = new InfoVO();
		vo.setLink("https://sports.news.naver.com/wfootball/index.nhn");
		vo.setCssQuery(".title");
		
		String doc = WCrawl.getDoc(vo);
		String docList[] = doc.split("\n");
		
		for(int i=0;i<docList.length;i++) {
			System.out.println(i+":"+docList[i]);
		}
	}
}
