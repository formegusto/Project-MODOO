package com.crawler.biz.user.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crawler.biz.user.UserVO;

@Repository
public class UserDAOMybatis {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	//CRUD ����� �޼ҵ� ����
	//���� ���
	public void insertUser(UserVO vo) {
		System.out.println("===> Mybatis�� insertUser() ��� ó��");
		mybatis.insert("UserDAO.insertUser", vo);
	}
	
	//���� ��ȸ
	public UserVO getUser(UserVO vo) {
		System.out.println("===> Mybatis�� getUser() ��� ó��");
		return mybatis.selectOne("UserDAO.getUser", vo);
	}
}
