package com.crawler.view.info;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.crawler.biz.data.DataVO;
import com.crawler.biz.data.impl.DataDAO;
import com.crawler.biz.info.InfoVO;
import com.crawler.biz.info.impl.InfoDAO;
import com.crawler.view.controller.Controller;

public class GetInfoController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("[MVC Framework] ũ�ѷ� �� ���� ��� ó��");
		// 1. �˻��� ũ�ѷ� ������ ��ȣ ����
		String seq = request.getParameter("seq");

		// 2. DB ���� ó��(info)
		InfoVO ivo = new InfoVO();
		ivo.setSeq(Integer.parseInt(seq));
		InfoDAO infoDAO = new InfoDAO();
		InfoVO info = infoDAO.getInfo(ivo);
		
		// 3. DB ���� ó��(Data)
		DataVO dvo = new DataVO();
		dvo.setInum(Integer.parseInt(seq));
		DataDAO dataDAO = new DataDAO();
		List<DataVO> dataList = dataDAO.getData(dvo);
		
		// 4. �˻� ����� ���ǿ� �����ϰ� �� ȭ������ �̵��Ѵ�.
		HttpSession session = request.getSession();
		session.setAttribute("info", info);
		session.setAttribute("dataList", dataList);
		return "getInfo";
	}

}
