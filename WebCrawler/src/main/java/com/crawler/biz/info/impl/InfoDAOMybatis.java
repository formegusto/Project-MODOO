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
		System.out.println("===> Mybatis�� insertInfo() ��� ó��");
		mybatis.insert("InfoDAO.insertInfo", vo);
	}
	
	public InfoVO getInfo(InfoVO vo) {
		System.out.println("===> Mybatis�� getInfo() ��� ó��");
		return mybatis.selectOne("InfoDAO.getInfo", vo);
	}
	
	public List<InfoVO> getInfoList(InfoVO vo) {
		System.out.println("===> Mybatis�� getInfoList() ��� ó��");
		return mybatis.selectList("InfoDAO.getInfoList", vo);
	}
}
