package com.tower.service;

import com.tower.entity.UserLogin;

public interface UserLoginService {
	public UserLogin findUser(String name);
	
	public void saveUser(UserLogin user);
}