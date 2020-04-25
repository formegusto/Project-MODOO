package com.crawler.biz.info.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crawler.biz.info.FrameHaveInfoVO;
import com.crawler.biz.info.FrameVO;

@Repository
public class FrameDAOMybatis {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertFrame(FrameVO vo) {
		System.out.println("===> Mybatis로 insertFrame() 등록 기능 처리");
		mybatis.insert("FrameDAO.insertFrame", vo);
	}
	public void insertFHI(FrameHaveInfoVO vo) {
		System.out.println("===> Mybatis로 insertFHI() 등록 기능 처리");
		mybatis.insert("FrameDAO.insertFHI", vo);
	}
	public void deleteFrame(FrameVO vo) {
		System.out.println("===> Mybatis로 deleteFrame() 등록 기능 처리");
		mybatis.delete("FrameDAO.deleteFrame", vo);
	}
	public List<FrameHaveInfoVO> getFHIList(FrameVO vo){
		System.out.println("===> Mybatis로 getFHIList() 조회 기능 처리");
		return mybatis.selectList("FrameDAO.getFHIList", vo);
	}
	public FrameVO getFrame(FrameVO vo) {
		System.out.println("===> Mybatis로 getFrame() 조회 기능 처리");
		return mybatis.selectOne("FrameDAO.getFrame", vo);
	}
	public List<FrameVO> getFrameList(FrameVO vo){
		System.out.println("===> Mybatis로 getBoardList() 조회 기능 처리");
		return mybatis.selectList("FrameDAO.getFrameList", vo);
	}
}
