package com.modoo.wrk.users.impl;

import com.modoo.wrk.users.UsersVO;

public interface UsersService {
	// SignUp User
	public void signupUser(UsersVO vo);
		
	// SignIn User
	public UsersVO signinUser(UsersVO vo);
}
