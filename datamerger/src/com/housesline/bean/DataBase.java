package com.housesline.bean;

/**
 * 数据库连接类
 * @author cdh
 *
 */
public class DataBase {

	private String driver;
	private String userName;
	private String password;
	private String url;
	
	
	
	public DataBase() {
		super();
	}
	public DataBase(String driver, String userName, String password, String url) {
		super();
		this.driver = driver;
		this.userName = userName;
		this.password = password;
		this.url = url;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
