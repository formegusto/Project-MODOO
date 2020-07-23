package com.modoo.wrk.info;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.modoo.wrk.data.DataVO;

@Service
public class ModooCrawler {
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
}
