package com.crawler.view.info;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.crawler.biz.common.WCrawl;
import com.crawler.biz.data.DataVO;
import com.crawler.biz.info.InfoVO;
import com.crawler.view.controller.Controller;

public class CrawlerConfirmController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("[MVC Framework] ũ�ѷ� Ȯ�� ������ ó��");
		// 1. ����� �Է� ���� ����
		String title = request.getParameter("title");
		String link = request.getParameter("link");
		String content = request.getParameter("content");
		String field = request.getParameter("field");
		String cssQuery = request.getParameter("cssQuery");
		
		// 2. ũ�ѷ� ������ ���� ����
		InfoVO vo = new InfoVO();
		vo.setTitle(title); vo.setContent(content);
		vo.setLink(link); vo.setField(field); vo.setCssQuery(cssQuery);
		List<DataVO> dataList = WCrawl.getData(vo);
		
		// 3.session�� ��ü ����
		HttpSession session = request.getSession();
		session.setAttribute("info", vo);
		session.setAttribute("dataList", dataList);
		return "crawlerConfirm";
	}

}
