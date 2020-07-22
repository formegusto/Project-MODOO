package com.modoo.wrk.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modoo.wrk.users.impl.UsersDAO;
import com.modoo.wrk.users.impl.UsersService;

@Service("userService")
public class UsersServiceImpl implements UsersService {
	@Autowired
	private UsersDAO usersDAO;
	
	@Override
	public void signupUser(UsersVO vo) {
		// TODO Auto-generated method stub
		usersDAO.signupUser(vo);
	}

	@Override
	public UsersVO signinUser(UsersVO vo) {
		// TODO Auto-generated method stub
		return usersDAO.signinUser(vo);
	}

}
