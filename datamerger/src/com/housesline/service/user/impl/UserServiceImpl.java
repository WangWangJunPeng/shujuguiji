package com.housesline.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.housesline.bean.User;
import com.housesline.dao.SelectDao;
import com.housesline.service.user.UserService;

import exception.UserIsNullException;

@Service(value="userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private SelectDao selectDao;
	
	@Override
	public User findUserByPhone(String phone) {
		if(StringUtils.isEmpty(phone)){
			throw new UserIsNullException("user is null");
		}
		
		return selectDao.selectUserByPhone(phone);
	}

}
