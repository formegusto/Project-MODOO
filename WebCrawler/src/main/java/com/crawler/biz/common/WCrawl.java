package com.crawler.biz.common;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.crawler.biz.data.DataVO;
import com.crawler.biz.info.InfoVO;

public class WCrawl {
	public static ArrayList<DataVO> getData(InfoVO vo){
		System.out.println("===> WCrawl getData(vo) 크롤링 기능 처리");
		Document doc;
		try {
			doc = Jsoup.connect(vo.getLink()).header("userAgent","Mozilla").get();
			Elements es = doc.select(vo.getCssQuery());
			ArrayList<DataVO> retData = new ArrayList<DataVO>();
			for(Element e : es) {
				DataVO data = new DataVO();
				data.setData(e.text());
				
				retData.add(data);
			}
			return retData;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList<DataVO> getData(List<InfoVO> infoList){
		System.out.println("===> WCrawl getData(List<vo>) 크롤링 기능 처리");
		Document doc;
		try {
			ArrayList<DataVO> retData = new ArrayList<DataVO>();
			for(InfoVO vo : infoList) {
				doc = Jsoup.connect(vo.getLink()).header("userAgent","Mozilla").get();
				Elements es = doc.select(vo.getCssQuery());
				for(Element e : es) {
					DataVO data = new DataVO();
					data.setData(e.text());
					
					retData.add(data);
				}
			}
			return retData;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList<DataVO> getLinkData(InfoVO vo){
		System.out.println("===> WCrawl getData(vo) 크롤링 기능 처리");
		Document doc;
		try {
			doc = Jsoup.connect(vo.getLink()).header("userAgent","Mozilla").get();
			Elements es = doc.select(vo.getCssQuery());
			ArrayList<DataVO> retData = new ArrayList<DataVO>();
			for(Element e : es) {
				DataVO data = new DataVO();
				data.setData(e.attr("href"));
				if(!(data.getData().contains("http://"))) {
					URL url = new URL(vo.getLink());
					data.setData("http://" + url.getHost() + e.attr("href"));
				}
				retData.add(data);
			}
			return retData;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getDoc(InfoVO vo){
		System.out.println("===> WCrawl getDoc(vo) 크롤링 기능 처리");
		Document doc;
		String doc_str;
		try {
			doc = Jsoup.connect(vo.getLink()).header("userAgent","Mozilla").get();
			doc_str = doc.toString();

			return doc_str;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
