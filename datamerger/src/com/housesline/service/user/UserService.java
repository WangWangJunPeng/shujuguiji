package com.housesline.service.user;

import com.housesline.bean.User;

public interface UserService {

	/**
	 * �û���¼������֤
	 * @param userId
	 * @return
	 */
	User findUserByPhone(String phone);
	
}
