package com.tower.service.impl;

import com.tower.entity.UserLogin;
import com.tower.mapper.LoginMapper;
import com.tower.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserLoginServiceImpl implements UserLoginService {

	@Autowired
	private LoginMapper mapper;
	
	public UserLogin findUser(String name) {
		return this.mapper.getUser(name);
	}

	@Override
	public void saveUser(UserLogin user) {
		// TODO Auto-generated method stub
		this.mapper.saveUser(user);
	}


}
