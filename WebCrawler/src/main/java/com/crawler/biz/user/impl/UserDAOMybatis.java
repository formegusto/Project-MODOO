package com.crawler.biz.user.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crawler.biz.user.UserVO;

@Repository
public class UserDAOMybatis {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	//CRUD 기능의 메소드 구현
	//유저 등록
	public void insertUser(UserVO vo) {
		System.out.println("===> Mybatis로 insertUser() 기능 처리");
		mybatis.insert("UserDAO.insertUser", vo);
	}
	
	//유저 조회
	public UserVO getUser(UserVO vo) {
		System.out.println("===> Mybatis로 getUser() 기능 처리");
		return mybatis.selectOne("UserDAO.getUser", vo);
	}
}
