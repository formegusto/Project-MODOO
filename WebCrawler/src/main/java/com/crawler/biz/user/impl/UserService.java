package com.crawler.biz.user.impl;

import com.crawler.biz.user.UserVO;

public interface UserService {
	//CRUD ����� �޼ҵ� ����
	//���� ���
	public void insertUser(UserVO vo);
	//���� ��ȸ
	public UserVO getUser(UserVO vo);
}
