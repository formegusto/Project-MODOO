package com.crawler.biz.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crawler.biz.user.impl.UserDAOMybatis;
import com.crawler.biz.user.impl.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAOMybatis userDAO;
	@Override
	public void insertUser(UserVO vo) {
		userDAO.insertUser(vo);
	}
	@Override
	public UserVO getUser(UserVO vo) {
		return userDAO.getUser(vo);
	}

}
