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
		
		ArrayList<DataVO> dataList = WCrawl.getData(vo);
		
		for(DataVO data : dataList) {
			System.out.println(data.getData());
		}
	}
}
