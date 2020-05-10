package com.crawler.biz.board.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crawler.biz.board.CommentVO;

@Repository
public class CommentDAOMybatis {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertComment(CommentVO vo) {
		System.out.println("===> Mybatis로 insertComment() 등록 기능 처리");
		mybatis.insert("CommentDAO.insertComment", vo);
	}
	
	public List<CommentVO> getCommentList(CommentVO vo){
		System.out.println("===> Mybatis로 getCommentList() 등록 기능 처리");
		return mybatis.selectList("CommentDAO.getCommentList", vo);
	}
}
