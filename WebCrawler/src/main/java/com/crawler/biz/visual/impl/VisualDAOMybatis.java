package com.crawler.biz.visual.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crawler.biz.visual.VisualHaveInfoVO;
import com.crawler.biz.visual.VisualVO;

@Repository
public class VisualDAOMybatis {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertVisual(VisualVO vo) {
		System.out.println("===> Mybatis로 insertVisual() 등록 기능 처리");
		mybatis.insert("VisualDAO.insertVisual", vo);
	}
	public void insertVHI(VisualHaveInfoVO vo) {
		System.out.println("===> Mybatis로 insertVHI() 등록 기능 처리");
		mybatis.insert("VisualDAO.insertVHI", vo);
	}
	public List<VisualHaveInfoVO> getVHIList(VisualHaveInfoVO vo){
		System.out.println("===> Mybatis로 getVHIList() 등록 기능 처리");
		return mybatis.selectList("VisualDAO.getVHIList",vo);
	}
	public VisualVO getVisual(VisualVO vo) {
		System.out.println("===> Mybatis로 getVisual() 등록 기능 처리");
		return mybatis.selectOne("VisualDAO.getVisual", vo);
	}
	public List<VisualVO> getVisualList(VisualVO vo){
		System.out.println("===> Mybatis로 getVisualList() 등록 기능 처리");
		return mybatis.selectList("VisualDAO.getVisualList",vo);
	}
	
}
