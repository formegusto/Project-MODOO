package com.crawler.biz.common;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.crawler.biz.data.DataVO;
import com.crawler.biz.info.InfoVO;

public class WCrawl {
	public static ArrayList<DataVO> getData(InfoVO vo){
		System.out.println("===> WCrawl getData(vo) 기능 처리");
		Document doc;
		try {
			doc = Jsoup.connect(vo.getLink()).get();
			Elements es = doc.select(vo.getCssQuery());
			ArrayList<DataVO> retData = new ArrayList<DataVO>();
			for(Element e : es) {
				DataVO data = new DataVO();
				data.setData(e.text());;
				retData.add(data);
			}
			return retData;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
