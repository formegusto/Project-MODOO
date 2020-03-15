package com.crawler.view.info;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crawler.biz.data.DataVO;
import com.crawler.biz.data.impl.DataDAO;
import com.crawler.biz.info.InfoVO;
import com.crawler.biz.info.impl.InfoDAO;
import com.crawler.view.controller.Controller;

public class CrawlerAddController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("[MVC Framework] ũ�ѷ� �� ������ ��� ó��");
		// 1. ����� �Է� ���� ����
		String title = request.getParameter("title");
		String link = request.getParameter("link");
		String content = request.getParameter("content");
		String field = request.getParameter("field");
		String cssQuery = request.getParameter("cssQuery");
		String[] datas = request.getParameterValues("data");
		
		// 2. DB ���� ó�� (info)
		InfoVO ivo = new InfoVO();
		ivo.setTitle(title); ivo.setLink(link); ivo.setContent(content);
		ivo.setField(field); ivo.setCssQuery(cssQuery);
		InfoDAO infoDAO = new InfoDAO();
		infoDAO.insertInfo(ivo);
		
		// 3. DB ���� ó�� (data)
		List<DataVO> dataList = new ArrayList<DataVO>();
		for(String data : datas){
			DataVO dvo = new DataVO();
			dvo.setData(data);
			dataList.add(dvo);
		}
		DataDAO dataDAO = new DataDAO();
		dataDAO.insertData(dataList);
		
		// 4. ȭ�� �׺���̼�
		return "getInfoList.do";
	}

}
