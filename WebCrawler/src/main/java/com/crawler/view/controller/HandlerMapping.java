package com.crawler.view.controller;

import java.util.HashMap;
import java.util.Map;

import com.crawler.view.info.CrawlerAddController;
import com.crawler.view.info.CrawlerConfirmController;
import com.crawler.view.info.GetInfoController;
import com.crawler.view.info.GetInfoListController;

public class HandlerMapping {
	private Map<String, Controller> mappings;
	
	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		mappings.put("/getInfoList.do", new GetInfoListController());
		mappings.put("/getInfo.do", new GetInfoController());
		mappings.put("/crawlerConfirm.do", new CrawlerConfirmController());
		mappings.put("/crawlerAdd_proc.do", new CrawlerAddController());
	}
	
	public Controller getController(String path) {
		return mappings.get(path);
	}
}
