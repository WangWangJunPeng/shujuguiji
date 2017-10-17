package com.housesline.utils;

public class PropertiesUtil {

	//归集项目使用的数据源
	public static final String DEFAULT_DRIVER="com.mysql.jdbc.Driver";
	//本地
	//public static final String DEFAULT_URL="jdbc:mysql://127.0.0.1:3306/housesline?characterEncoding=utf8";
	//测试
	public static final String DEFAULT_URL="jdbc:mysql://120.27.146.151:3306/bdm23928059_db?characterEncoding=utf8&amp;autoReconnect=true";
	//正式
	//public static final String PRODUCT_URL="jdbc:mysql://118.178.235.30:3306/housesline?characterEncoding=utf8";
	
	//测试
	public static final String DEFAULT_USERNAME="root";
	public static final String DEFAULT_PASSWORD="sanchong.123";
	//正式
	//public static final String DEFAULT_USERNAME="root";
	//public static final String DEFAULT_PASSWORD="sanchong.123";
	
	//myssh项目使用的数据源
	public static final String PRODUCT_DRIVER = "com.mysql.jdbc.Driver";
	//本地
	//public static final String PRODUCT_URL = "jdbc:mysql://127.0.0.1:3306/housesline?characterEncoding=utf8";
	//测试
	public static final String PRODUCT_URL = "jdbc:mysql://120.27.146.151:3306/bdm23928059_db?characterEncoding=utf8&amp;autoReconnect=true";
	public static final String PRODUCT_USERNAME = "root";
	public static final String PRODUCT_PASSWORD = "sanchong.123";
	//正式
	//public static final String PRODUCT_URL = "jdbc:mysql://118.178.235.30:3306/housesline?characterEncoding=utf8";
	//public static final String PRODUCT_USERNAME = "housesline";
	//public static final String PRODUCT_PASSWORD = "HZsc@Houses123";

}
