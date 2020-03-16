package com.crawler.biz.user.impl;

import com.crawler.biz.user.UserVO;

public interface UserService {
	//CRUD 기능의 메소드 구현
	//유저 등록
	public void insertUser(UserVO vo);
	//유저 조회
	public UserVO getUser(UserVO vo);
}
