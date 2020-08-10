package com.crawler.biz.info.impl;

import java.util.List;

import com.crawler.biz.info.InfoVO;

public interface InfoService {
	
	//CRUD ����� �޼ҵ� ����
	//ũ�Ѹ� ���� ���
	public void insertInfo(InfoVO vo);
	//ũ�ѷ� �� ���� ����
	public InfoVO getInfo(InfoVO vo);
	//ũ�Ѹ� ���� ����Ʈ ����
	public List<InfoVO> getInfoList(InfoVO vo);
	public void deleteInfo(InfoVO vo);
	public InfoVO getInfoTop(InfoVO vo);
	public List<InfoVO> getInfoListSearch(String keyword);
}
