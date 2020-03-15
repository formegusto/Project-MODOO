package com.crawler.biz.info.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crawler.biz.info.InfoVO;

@Repository
public class InfoDAOMybatis {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertInfo(InfoVO vo) {
		System.out.println("===> Mybatis로 insertInfo() 기능 처리");
		mybatis.insert("InfoDAO.insertInfo", vo);
	}
	
	public InfoVO getInfo(InfoVO vo) {
		System.out.println("===> Mybatis로 getInfo() 기능 처리");
		return mybatis.selectOne("InfoDAO.getInfo", vo);
	}
	
	public List<InfoVO> getInfoList(InfoVO vo) {
		System.out.println("===> Mybatis로 getInfoList() 기능 처리");
		return mybatis.selectList("InfoDAO.getInfoList", vo);
	}
}
