package com.housesline.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 数据库链接工具类
 * @author cdh
 *
 */
public class JDBCUtils {
	//创建数据库使用的默认数据源
	public static final String DEFAULT_DRIVER = PropertiesUtil.DEFAULT_DRIVER;
	public static final String DEFAULT_URL = PropertiesUtil.DEFAULT_URL;
	public static final String DEFAULT_USERNAME = PropertiesUtil.DEFAULT_USERNAME;
	public static final String DEFAULT_PASSWORD = PropertiesUtil.DEFAULT_PASSWORD;
	
	/**
	 * 获取连接
	 */
	public static Connection getMyConnection(String driver, String userName, String password, String url){
		Connection conn = null;
		try {
			//加载驱动
			Class.forName(driver);
			//获取连接
			conn = DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//返回连接对象
		return conn;
	}
	
	
	
}
