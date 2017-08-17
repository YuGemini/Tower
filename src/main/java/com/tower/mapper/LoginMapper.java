package com.tower.mapper;

import com.tower.entity.UserLogin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginMapper {

	public UserLogin getUser(@Param(value = "name") String name);
	
	public void saveUser(UserLogin user);
}
