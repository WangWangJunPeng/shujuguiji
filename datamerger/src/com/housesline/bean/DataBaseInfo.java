package com.housesline.bean;

public class DataBaseInfo {

	//����������
	private String id;
	//���ݿ�����
	private String databaseName;
	//Ŀ��id
	private String targetId;
	//���ݿ�url
	private String url;
	
	private String userName;
	
	private String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	@Override
	public String toString() {
		return "DataBaseInfo [id=" + id + ", databaseName=" + databaseName + ", targetId=" + targetId + ", url=" + url
				+ ", userName=" + userName + ", password=" + password + "]";
	}
	
	
}
