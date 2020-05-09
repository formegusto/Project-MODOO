package com.crawler.biz.tm.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crawler.biz.tm.TmHaveInfoVO;
import com.crawler.biz.tm.TmVO;

@Repository
public class TmDAOMybatis {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertTm(TmVO vo) {
		System.out.println("===> Mybatis로 insertTm() 등록 기능 처리");
		mybatis.insert("TmDAO.insertTm", vo);
	}
	public void insertTHI(TmHaveInfoVO vo) {
		System.out.println("===> Mybatis로 insertTHI() 등록 기능 처리");
		mybatis.insert("TmDAO.insertTHI", vo);
	}
	public List<TmHaveInfoVO> getTHIList(TmVO vo){
		System.out.println("===> Mybatis로 getTHIList() 등록 기능 처리");
		return mybatis.selectList("TmDAO.getTHIList",vo);
	}
	public TmVO getTm(TmVO vo) {
		System.out.println("===> Mybatis로 getTm() 등록 기능 처리");
		return mybatis.selectOne("TmDAO.getTm", vo);
	}
	public List<TmVO> getTmList(TmVO vo){
		System.out.println("===> Mybatis로 getTmList() 등록 기능 처리");
		return mybatis.selectList("TmDAO.getTmList",vo);
	}
	public TmVO getTmTop(TmVO vo) {
		System.out.println("===> Mybatis로 getTmTop() 등록 기능 처리");
		return mybatis.selectOne("TmDAO.getTmTop", vo);
	}
}
