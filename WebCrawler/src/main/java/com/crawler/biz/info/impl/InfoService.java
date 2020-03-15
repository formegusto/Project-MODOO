package com.crawler.biz.info.impl;

import java.util.List;

import com.crawler.biz.info.InfoVO;

public interface InfoService {
	
	//CRUD 기능의 메소드 구현
	//크롤링 정보 등록
	public void insertInfo(InfoVO vo);
	//크롤링 정보 리스트 보기
	public List<InfoVO> getInfoList(InfoVO vo);
}
