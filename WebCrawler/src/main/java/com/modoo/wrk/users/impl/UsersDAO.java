package com.modoo.wrk.users.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.modoo.wrk.users.UsersVO;

@Repository
public class UsersDAO {
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void signupUser(UsersVO vo) {
		System.out.println("[UsersDAO Log] signupUser() Call ");
		mybatis.insert("UsersDAO.signupUser", vo);
	}
	
	public UsersVO signinUser(UsersVO vo) {
		System.out.println("[UsersDAO Log] signinUser() Call ");
		return mybatis.selectOne("UsersDAO.signinUser", vo);
	}
}
