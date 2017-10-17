package com.housesline.service.user;

import com.housesline.bean.User;

public interface UserService {

	/**
	 * 用户登录密码验证
	 * @param userId
	 * @return
	 */
	User findUserByPhone(String phone);
	
}
