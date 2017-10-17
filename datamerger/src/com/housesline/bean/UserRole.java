package com.housesline.bean;

/**
 * 角色
 * @author cdh
 *
 */
public class UserRole {

	//中介经纪人
	public static final Integer MEDI = 1;
	//店长
	public static final Integer SHOPOWNER = 2;
	//置业顾问
	public static final Integer AGENT = 3;
	//案场助理
	public static final Integer ENGINEER = 4;
	//超级管理员
	public static final Integer SYSTEMADMIN = 5;
	//合伙人
	public static final Integer PARTNER = 6;
	//案场经理
	public static final Integer DIRECTOR = 7;
	
	private String uId;
	private Integer rId;
	
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public Integer getrId() {
		return rId;
	}
	public void setrId(Integer rId) {
		this.rId = rId;
	}
	
	
}
