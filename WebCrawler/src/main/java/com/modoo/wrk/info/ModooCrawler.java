package com.modoo.wrk.info;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modoo.wrk.data.DataVO;
import com.modoo.wrk.data.impl.DataService;
import com.modoo.wrk.info.impl.InfoService;

@Service
public class ModooCrawler {
	@Autowired
	private DataService dataService;
	@Autowired
	private InfoService infoService;
	
	public ArrayList<DataVO> getDataTypeText(InfoVO vo){
		System.out.println("[ModooCralwer] Type:text Crawling");
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
			System.out.println("크롤링 에러!");
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<DataVO> getDataTypeLink(InfoVO vo){
		System.out.println("[ModooCralwer] Type:link Crawling");
		Document doc;
		try {
			doc = Jsoup.connect(vo.getLink()).header("userAgent","Mozilla").get();
			Elements es = doc.select(vo.getCssQuery());
			ArrayList<DataVO> retData = new ArrayList<DataVO>();
			for(Element e : es) {
				DataVO data = new DataVO();
				String tmp = e.attr("href");
				
				if(!tmp.contains("http") || !tmp.contains("https")) {
					URL url = new URL(vo.getLink());
					tmp = "http://" + url.getHost() + tmp;
				}
				data.setData(tmp);
				retData.add(data);
			}
			return retData;
		} catch (IOException e) {
			System.out.println("크롤링 에러!");
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<DataVO> getDataTypeLinkList(InfoVO vo){
		System.out.println("[ModooCralwer] Type:linklist Crawling");
		
		InfoVO ivo = new InfoVO();
		ivo.setIseq(Integer.parseInt(vo.getLink()));
		InfoVO info = infoService.getInfo(ivo);
		
		DataVO dvo = new DataVO();
		dvo.setIseq(info.getIseq());
		List<DataVO> dataList = dataService.getData(dvo);
		
		Document doc;
		try {
			ArrayList<DataVO> retData = new ArrayList<DataVO>();
			for(DataVO data : dataList) {
				doc = Jsoup.connect(data.getData()).header("userAgent","Mozilla").get();
				Elements es = doc.select(vo.getCssQuery());
				for(Element e : es) {
					DataVO indata = new DataVO();
					indata.setData(e.text());
					
					retData.add(indata);
				}
			}
			return retData;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
