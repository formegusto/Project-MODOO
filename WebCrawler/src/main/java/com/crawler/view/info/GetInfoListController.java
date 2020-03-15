package com.crawler.view.info;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.crawler.biz.info.InfoVO;
import com.crawler.biz.info.impl.InfoDAO;
import com.crawler.view.controller.Controller;

public class GetInfoListController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("[MVC Framework] ũ�ѷ� ��� �˻� ó��");
		// 1. ����� �Է� ���� ����
		// 2. DB ���� ó��
		InfoVO vo = new InfoVO();
		InfoDAO infoDAO = new InfoDAO();
		List<InfoVO> infoList = infoDAO.getInfoList(vo);
		
		// 3. �˻� ����� ���ǿ� �����ϰ� ���ȭ������ �̵��Ѵ�.
		HttpSession session = request.getSession();
		session.setAttribute("infoList", infoList);
		return "getInfoList";
	}

}
