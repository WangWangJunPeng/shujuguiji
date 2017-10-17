package com.housesline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.housesline.bean.User;
import com.housesline.service.user.UserService;
import com.housesline.utils.SysContent;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/tologin", method = RequestMethod.GET)
	public String toLoginPage() {
		return "/login";
	}

	/**
	 * 请求登录
	 * @param model
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/login/{phone}", method = RequestMethod.POST)
	public String toLogin(Model model, @PathVariable("phone") String phone) {
		User user = userService.findUserByPhone(phone);
		if(user != null){
			SysContent.getSession().setAttribute("userInfo", user);
			model.addAttribute("data", "登录成功");
			return "/home";
		}else{
			model.addAttribute("data", "登录失败,用户名不存在");
			return "/login";
		}
		
	}
}
