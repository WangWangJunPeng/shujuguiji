package com.housesline.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.housesline.bean.AgentVisitOrder;
import com.housesline.bean.CollectionRecord;
import com.housesline.bean.DataBase;
import com.housesline.bean.Handle;
import com.housesline.bean.HouseTypeHouseAndOrder;
import com.housesline.bean.OutSideProject;
import com.housesline.utils.DateUtil;
import com.housesline.utils.JDBCUtils;
import com.housesline.utils.PropertiesUtil;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

@Repository
public class BaseDao {
	
	
	/***  主项目用的数据源   ***/
	public static final String PRODUCT_DRIVER = PropertiesUtil.PRODUCT_DRIVER;
	public static final String PRODUCT_USERNAME = PropertiesUtil.PRODUCT_USERNAME;
	public static final String PRODUCT_PASSWORD = PropertiesUtil.PRODUCT_PASSWORD;
	public static final String PRODUCT_URL = PropertiesUtil.PRODUCT_URL;

	/**
	 * 获取数据库连接
	 * @param dataBase
	 * @return
	 */
	public static Connection getMyConnection(DataBase dataBase) {
		String driver = dataBase.getDriver();
		String userName = dataBase.getUserName();
		String password = dataBase.getPassword();
		String url = dataBase.getUrl();
		Connection conn = (Connection) JDBCUtils.getMyConnection(driver, userName, password, url);
		return conn;
	}
	
	/**
	 * 通过projectId创建数据库并将数据库信息加入到项目数据库信息中
	 * @param targetId
	 * @param handleState 创建数据库的方式 0：自动 (定时) 1：手动
	 * @param userId 只有在手动创建的时候才会有userId,否则传""即可;
	 */
	public DataBase createDataBaseForProject(String targetId, Integer handleState, String userId){
		//创建数据库用的数据源
		DataBase dataBase = new DataBase(JDBCUtils.DEFAULT_DRIVER, JDBCUtils.DEFAULT_USERNAME, JDBCUtils.DEFAULT_PASSWORD, JDBCUtils.DEFAULT_URL);
		Connection conn = getMyConnection(dataBase);
		//项目用的数据库
		DataBase productDataBase = new DataBase(PRODUCT_DRIVER, PRODUCT_USERNAME, PRODUCT_PASSWORD, PRODUCT_URL);
		Connection productConn = getMyConnection(productDataBase);
		
		
		String sql = "create dataBase t_"+targetId + ";";
		Statement stmt = null;
		
		Statement productStmt = null;
		//数据库url
		String ip = "";
		String[] urls = JDBCUtils.DEFAULT_URL.split("/");
		for (int i = 0; i < urls.length; i++) {
			if(urls[i].matches("[0-9]+.*")){
				ip = urls[i];
			}
		}
		String url = "jdbc:mysql://" + ip + "/t_" + targetId + "?characterEncoding=utf8";
		
		try {
			conn.setAutoCommit(false);
			productConn.setAutoCommit(false);
			stmt = (Statement) conn.createStatement();
			//创建数据库
			stmt.executeUpdate(sql);
			
			//将数据库信息存入到主项目数据库
			
			productStmt = (Statement) productConn.createStatement();
			String proSql = "insert into t_databaseinfo(id, targetId, databaseName, url, userName, password)"
					+ " values(" + null + ",'" + targetId + "','t_" + targetId + "','" + url + "',"
							+ "'" + JDBCUtils.DEFAULT_USERNAME + "','" + JDBCUtils.DEFAULT_PASSWORD + "')";
			productStmt.executeUpdate(proSql);
			//记录日志(数据归集数据库)
			String message = "创建数据库";
			String nowTime = DateUtil.format(new Date());
			String logSql = "insert into t_datahandledynamic(id, handleDate, handleState, handleUser, targetId, targetName, message)"
					+ " values(" + null + " ,'" + nowTime + "','"+ handleState + "','" + userId + "','" + targetId + "','t_" + targetId + "','" + message + "')";
			stmt.executeUpdate(logSql);
			
			conn.commit();
			productConn.commit();
			
			stmt.close();
			productStmt.close();
			conn.close();
			productConn.close();
			
		} catch (SQLException e) {
			try {
				if(conn != null){
					conn.rollback();
				}
				if(productConn != null){
					productConn.rollback();
				}
			} catch (SQLException e1) {
			}
			e.printStackTrace();
		}catch(Exception ex){
			try {
				if(conn != null){
					conn.rollback();
				}
				if(productConn != null){
					productConn.rollback();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}finally{
			try {
				if(stmt != null){
					stmt.close();
				}
				if(productStmt != null){
					productStmt.close();
				}
			} catch (SQLException e) {
			}
			
			try {
				if(conn != null){
					conn.close();
				}
				if(productConn != null){
					productConn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		DataBase returnDataBase = new DataBase(JDBCUtils.DEFAULT_DRIVER, JDBCUtils.DEFAULT_USERNAME, JDBCUtils.DEFAULT_PASSWORD, url);
		
		return returnDataBase;
		
	}
	
	/**
	 * 为数据库添加字段
	 * @param tableName 表名
	 * @param field	字段名
	 * @param type	字段类型
	 * @param projectId
	 * @return
	 */
	public Integer addField(String tableName, String field, String type, String projectId){
		DataBase dataBase = selectDataBaseFromTargetId(projectId);
        Connection conn = getMyConnection(dataBase);
        Statement  stmt = null;
        
        try {
        	conn.setAutoCommit(false);
			stmt = (Statement) conn.createStatement();
			String sql = "alter table " + tableName + " add " + field + " " + type;
			stmt.executeUpdate(sql);
			conn.commit();
			return 1;
		} catch (SQLException e) {
			try {
				if(conn != null){
					conn.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch(Exception ex){
			try {
				if(conn != null){
					conn.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			ex.printStackTrace();
		} finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	/**
	 * 获取主项目的数据源(主项目数据库)
	 * @return
	 */
	public static Connection getDefaultDataBase(){
		DataBase dataBase = new DataBase(BaseDao.PRODUCT_DRIVER, BaseDao.PRODUCT_USERNAME, BaseDao.PRODUCT_PASSWORD, BaseDao.PRODUCT_URL);
		return getMyConnection(dataBase);
	}
	
	
	/**
	 * 创建目标对象的数据表
	 * @param dataBase
	 * @param targetId
	 * @param userId
	 * @param handleState 0：自动创建  1：手动创建
	 */
	public void createTableForTarget(DataBase dataBase, String targetId, String userId, Integer handleState){
		//连接创建的数据库
		Connection conn = getMyConnection(dataBase);
		Statement stmt = null;
		
		//连接默认数据源
	 	Connection defaultConn = getDefaultDataBase();
	 	Statement defaultStmt = null;
		
		try {
			
			conn.setAutoCommit(false);
			defaultConn.setAutoCommit(false);
			
			stmt = (Statement) conn.createStatement();
			//创建t_outsideproject表
			String outSideSql = "create table t_outsideproject("
					+ "copDate varchar(255) not null,"
					+ "collDateTime varchar(255) not null,"
					+ "guideCount int,"
					+ "visitedCount int,"
					+ "recordCustomerCount int,"
					+ "recordVisitCount int,"
					+ "recordNotVisitCount int,"
					+ "guiCustomerRecordCount int,"
					+ "outSideCustomerRecordCount int,"
					+ "guideCustomerVisitCount int,"
					+ "outSideDealCount int,"
					+ "intFiledToDealNum int,"
					+ "outSideAgreeRecordCount int,"
					+ "outSideRefuseRecordCount int,"
					+ "outSideWaitSignCount int,"
					+ "outSideRevokeOrderCount int,"
					+ "outSideSubscribeHouseCount int,"
					+ "outSideSubscribeMoney double,"
					+ "outSideSubscribeGetMoney double,"
					+ "outSideSubscribeLockHouseMoney double,"
					+ "outSideGiveUpHouseMoney double,"
					+ "outSideWaitSignHouseMoney double,"
					+ "outSideSignHouseMoney double,"
					+ "outSideRecordCuCount int,"
					+ "outSideNewCuRecordCount int,"
					+ "outSideOldCuRecordCount int,"
					+ "outSideSignCuCount int,"
					+ "outSideNewCuSignCount int,"
					+ "outSideOldSignCount int,"
					+ "guidedHouseNum LONGTEXT,"
					+ "visitedHouseNum longtext,"
					+ "guidedShopId longtext,"
					+ "guidedShopAgentId longtext,"
					+ "visitedShopId longtext,"
					+ "visitedShopAgentId longtext,"
					+ "reserveField varchar(255),"
					+ "primary key(copDate),"
					+ "key idx_copdate(copDate),"
					+ "key idx_collDateTime(collDateTime)"
					+ ")ENGINE=INNODB DEFAULT CHARSET=utf8;";
			stmt.executeUpdate(outSideSql);
			
			//创建t_housetypehouseandorder数据表
			String houseTypeSql = "create table t_housetypehouseandorder("
					+ "chDate varchar(255) not null,"
					+ "collDateTime varchar(255) not null,"
					+ "houseTypeId varchar(255),"
					+ "houseTypeName varchar(255),"
					+ "area double,"
					+ "enterBuyCount int,"
					+ "agreeEnterCount int,"
					+ "refuseEnterCount int,"
					+ "payCount int,"
					+ "signCount int,"
					+ "revokeOrderCount int,"
					+ "houseTotalCount int,"
					+ "putAwayCount int,"
					+ "downAwayCount int,"
					+ "sallingCount int,"
					+ "waitSignCount int,"
					+ "signedCount int,"
					+ "revokedHouseCount int,"
					+ "discrption varchar(255),"
					+ "primary key(chDate,houseTypeId),"
					+ "key idx_chdate(chDate),"
					+ "key idx_collDateTime(collDateTime)"
					+ ")ENGINE=INNODB DEFAULT CHARSET=utf8;";
			stmt.executeUpdate(houseTypeSql);
			
			//创建t_agentvisitorder
			String agentSql = "create table t_agentvisitorder("
					+ "cvDate varchar(255) not null,"
					+ "collDateTime varchar(255) not null,"
					+ "agentId varchar(255),"
					+ "agentName varchar(255),"
					+ "agentPhone varchar(255),"
					+ "agentStatus int,"
					+ "visitCount int,"
					+ "validVisitCount int,"
					+ "newVisitCount int,"
					+ "visitAgain int,"
					+ "secondVisitCount int,"
					+ "moreVisitCount int,"
					+ "appointCount int,"
					+ "newWayVisitCount int,"
					+ "validNewWayVisitCount int,"
					+ "appointNewWayVisitCount int,"
					+ "affirmOldCustomerVisitNum int,"
					+ "oldWayVisitCount int,"
					+ "validOldWayVisitCount int,"
					+ "appointOldWayVisitCount int,"
					+ "replaceCount int,"
					+ "orderReplaceVisitCount int,"
					+ "appointLosedCount int,"
					+ "losedCount int,"
					+ "newAppointCount int,"
					+ "newCustomerVisitTime varchar(255),"
					+ "oldCustomerVisitTime varchar(255),"
					+ "replaceVisitTime varchar(255),"
					+ "totalVisitTime varchar(255),"
					+ "averageVisitTime varchar(255),"
					+ "visitId longtext,"
					+ "enterBuyCount int,"
					+ "agreeEnterCount int,"
					+ "refuseEnterCount int,"
					+ "payCount int,"
					+ "signCount int,"
					+ "revokeOrderCount int,"
					+ "confirmHouseMoney double,"
					+ "subscribeHouseCount int,"
					+ "subscribeMoney double,"
					+ "subscribeGetMoney double,"
					+ "subscribeLockHouseMoney double,"
					+ "giveUpSignCount int,"
					+ "waitSignCount int,"
					+ "momeryCuDealCount int,"
					+ "signHouseMoney double,"
					+ "giveUpHouseMoney double,"
					+ "waitSignHouseMoney double,"
					+ "signedOrderId longtext,"
					+ "refusedOrderId longtext,"
					+ "revokeOrderId longtext,"
					+ "payOrderId longtext,"
					+ "newAddCollCustomerCount int,"
					+ "grandTotalCollCustomerCount int,"
					+ "grandTotalOldCustomerCount int,"
					+ "totalCustomerCount int,"
					+ "totalOldCustomerCount int,"
					+ "newTwoVisitCustomerCount int,"
					+ "customerReturnBackVisitNum int,"
					+ "platformCustomerCount int,"
					+ "subscribeCustomerCount int,"
					+ "oldCustomerSignedCount int,"
					+ "newCustomerSignedCount int,"
					+ "newSubscribeCustomerCount int,"
					+ "oldSubscribeCustomerCount int,"
					+ "visitToDealCount int,"
					+ "newAddCollCustomerId longtext,"
					+ "grandTotalCollCustomerId longtext,"
					+ "grandTotalOldCustomerId longtext,"
					+ "signInTime varchar(255),"
					+ "signOutTime varchar(255),"
					+ "reserveField varchar(255),"
					+ "newCustomerVisitCount int,"
					+ "validNewCustomerVisitCount int,"
					+ "oldCustomerVisitCount int,"
					+ "validOldCustomerVisitCount int,"
					+ "primary key(cvDate,agentId),"
					+ "key idx_cvdate(cvDate),"
					+ "key idx_collDateTime(collDateTime)"
					+ ")ENGINE=INNODB DEFAULT CHARSET=utf8;";
			
			stmt.executeUpdate(agentSql);
			
			//记录日志
			defaultStmt = (Statement) defaultConn.createStatement();
			String message = "创建归集数据表";
			String nowTime = DateUtil.format(new Date());
			String logSql = "insert into t_datahandledynamic(id, handleDate, handleState, handleUser, targetId, targetName, message)"
					+ " values(" + null + ",'" + nowTime + "','"+ handleState + "','" + userId + "','" + targetId + "','t_" + targetId + "','" + message + "')";
			defaultStmt.executeUpdate(logSql);
			
			conn.commit();
			defaultConn.commit();
			
			stmt.close();
			defaultStmt.close();
			conn.close();
			defaultConn.close();
			
			
		} catch (SQLException e) {
			try {
				if(conn != null){
					conn.rollback();
				}
				if(defaultConn != null){
					defaultConn.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (Exception ex){
			try {
				if(conn != null){
					conn.rollback();
				}
				if(defaultConn != null){
					defaultConn.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			ex.printStackTrace();
		} finally{
			
			try {
				if(stmt != null){
					stmt.close();
				}
				if(defaultStmt != null){
					defaultStmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null){
					conn.close();
				}
				if(defaultConn != null){
					defaultConn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	/**
	 * 根据目标id找到数据库连接信息
	 * @param targetId
	 * @return
	 */
	public static DataBase selectDataBaseFromTargetId(String targetId){
		
		DataBase productDataBase = new DataBase(PRODUCT_DRIVER, PRODUCT_USERNAME, PRODUCT_PASSWORD, PRODUCT_URL);
		Connection productConn = getMyConnection(productDataBase);
		Statement stmt = null;
		DataBase dataBase = new DataBase();
		
		
		try {
			stmt = (Statement) productConn.createStatement();
			String sql = "select * from t_databaseinfo where targetId = '" + targetId + "'";
			
			ResultSet result = (ResultSet) stmt.executeQuery(sql);
			
			while(result.next()){
				dataBase.setDriver("com.mysql.jdbc.Driver");
				dataBase.setUrl(result.getString("url"));
				dataBase.setUserName(result.getString("userName"));
				dataBase.setPassword(result.getString("password"));
			}
			return dataBase;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex){
			ex.printStackTrace();
		} finally{
			
			try {
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(productConn != null){
					productConn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
		
	}
	
	/**
	 * 判断到访表中这一天的置业顾问的数据是否已经添加一行，如果已经添加返回true，反之false
	 * @param projectId
	 * @param day yyyy-MM-dd
	 * @param userId
	 * @return
	 */
	public static boolean selectVisitRowByProjectIdAndTimeAndUserId(String projectId, String day, String userId){
		//首先要根据projectId找到所属的数据库
		DataBase dataBase = BaseDao.selectDataBaseFromTargetId(projectId);
		Connection conn = BaseDao.getMyConnection(dataBase);
		Statement stmt = null;
		
		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_agentvisitorder where cvDate = '" + day + "' "
					+ " and agentId = '" + userId + "' ";
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while(rs.next()){
				return true;//表示已经建立过这一天的置业顾问的那一行数据
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	
	
	/**
	 * 归集到访记录部分----1
	 * @param avo
	 * @param projectId
	 * @param userId
	 */
	public void saveOrUpdateVisitAnalyzeData(AgentVisitOrder avo, String projectId, String userId){
		DataBase dataBase = selectDataBaseFromTargetId(projectId);
        Connection conn = getMyConnection(dataBase);
        Statement  pstmt = null;
        //连接默认数据库
        Connection defaultConn = getDefaultDataBase();
        Statement defaultStmt = null;
        
        try {
        	defaultConn.setAutoCommit(false);
        	conn.setAutoCommit(false);
        	//首先查出当前这条数据是否已经存在在数据库，如果存在就更新，不存在就添加
        	boolean flag = selectVisitRowByProjectIdAndTimeAndUserId(projectId, avo.getCvDate(), avo.getAgentId());
        	pstmt = (Statement) conn.createStatement();
        	defaultStmt = (Statement) defaultConn.createStatement();
        	if(flag){//已经存在
        		String sql = "update t_agentvisitorder SET collDateTime = '" + avo.getCollDateTime() + "', "
        				+ " agentStatus = " + avo.getAgentStatus() + ", "
        				+ " visitCount = " + avo.getVisitCount() + ", "
        				+ " validVisitCount = " + avo.getValidVisitCount() + ", "
        				+ " newVisitCount = " + avo.getNewVisitCount() + ", "
        				+ " visitAgain = " + avo.getVisitAgain() + ", "
        				+ " secondVisitCount = " + avo.getSecondVisitCount() + ", "
        				+ " moreVisitCount = " + avo.getMoreVisitCount() + ", "
        				+ " appointCount = " + avo.getAppointCount() + ", "
        				+ " newWayVisitCount = " + avo.getNewWayVisitCount() + ", "
        				+ " validNewWayVisitCount = " + avo.getValidNewWayVisitCount() + ", "
        				+ " newCustomerVisitCount = " + avo.getNewCustomerVisitCount() + ", "
        				+ " validNewCustomerVisitCount = " + avo.getValidNewCustomerVisitCount() + ", "
        				+ " oldCustomerVisitCount = " + avo.getOldCustomerVisitCount() + ", "
        				+ " validOldCustomerVisitCount = " + avo.getValidOldCustomerVisitCount() + ", "
        				+ " appointNewWayVisitCount = " + avo.getAppointNewWayVisitCount() + ", "
        				+ " oldWayVisitCount = " + avo.getOldWayVisitCount() + ", "
        				+ " validOldWayVisitCount = " + avo.getValidOldWayVisitCount() + ", "
        				+ " appointOldWayVisitCount = " + avo.getAppointOldWayVisitCount() + ", "
        				+ " replaceCount = " + avo.getReplaceCount() + ", "
        				+ " orderReplaceVisitCount = " + avo.getOrderReplaceVisitCount() + ", "
        				+ " appointLosedCount = " + avo.getAppointLosedCount() + ", "
        				+ " losedCount = " + avo.getLosedCount() + ", "
        				+ " newAppointCount = " + avo.getNewAppointCount() + ", "
        				+ " newCustomerVisitTime = '" + avo.getNewCustomerVisitTime() + "', "
        				+ " oldCustomerVisitTime = '" + avo.getOldCustomerVisitTime() + "', "
        				+ " replaceVisitTime = '" + avo.getReplaceVisitTime() + "', "
        				+ " totalVisitTime = '" + avo.getTotalVisitTime() + "', "
        				+ " affirmOldCustomerVisitNum = " + avo.getAffirmOldCustomerVisitNum() + ", "
        				+ " averageVisitTime = '" + avo.getAverageVisitTime() + "' where cvDate = '" + avo.getCvDate() + "' "
        				+ "	and agentId = '" + avo.getAgentId() + "'";
        		pstmt.executeUpdate(sql);
        		conn.commit();
        		
        		//记录日志
                defaultStmt = (Statement) defaultConn.createStatement();
                String message = "更新t_agentvisitorder表的-到访-数据1条";
                String nowTime = DateUtil.format(new Date());
                String logSql = "insert into t_datahandledynamic(id, handleDate, handleState, handleUser, targetId, targetName, message)"
                        + " values(" + null + " ,'" + nowTime + "','"+ Handle.HANDLE_AUTO + "','" + userId + "','" + projectId + "','t_" + projectId + "','" + message + "')";
                defaultStmt.executeUpdate(logSql);
                defaultConn.commit();
        	} else {//不存在就新增
        		String sql = "insert into t_agentvisitorder(cvDate,collDateTime,agentStatus,visitCount,validVisitCount,"
        				+ "newVisitCount,secondVisitCount,moreVisitCount,appointCount,newWayVisitCount,"
        				+ "validNewWayVisitCount,appointNewWayVisitCount,oldWayVisitCount,validOldWayVisitCount,"
        				+ "appointOldWayVisitCount,replaceCount,orderReplaceVisitCount,appointLosedCount,"
        				+ "losedCount,newAppointCount,newCustomerVisitTime,oldCustomerVisitTime,replaceVisitTime,"
        				+ "totalVisitTime,averageVisitTime,agentId,agentName,agentPhone,affirmOldCustomerVisitNum,newCustomerVisitCount,"
        				+ "validNewCustomerVisitCount,oldCustomerVisitCount,validOldCustomerVisitCount,visitAgain) values('"
        				+ avo.getCvDate() + "','"
        				+ avo.getCollDateTime() +"',"
        				+ avo.getAgentStatus() + ","
        				+ avo.getVisitCount() + ","
        				+ avo.getValidVisitCount() + ","
        				+ avo.getNewVisitCount() + ","
        				+ avo.getSecondVisitCount() + ","
        				+ avo.getMoreVisitCount() + ","
        				+ avo.getAppointCount() + ","
        				+ avo.getNewWayVisitCount() + ","
        				+ avo.getValidNewWayVisitCount() + ","
        				+ avo.getAppointNewWayVisitCount() + ","
        				+ avo.getOldWayVisitCount() + ","
        				+ avo.getValidOldWayVisitCount() + ","
        				+ avo.getAppointOldWayVisitCount() + ","
        				+ avo.getReplaceCount() + ","
        				+ avo.getOrderReplaceVisitCount() + ","
        				+ avo.getAppointLosedCount() + ","
        				+ avo.getLosedCount() + ","
        				+ avo.getNewAppointCount() + ",'"
        				+ avo.getNewCustomerVisitTime() + "','"
        				+ avo.getOldCustomerVisitTime() + "','"
        				+ avo.getReplaceVisitTime() + "','"
        				+ avo.getTotalVisitTime() + "','"
        				+ avo.getAverageVisitTime() + "','"
        				+ avo.getAgentId() + "','"
        				+ avo.getAgentName() + "','"
        				+ avo.getAgentPhone() + "',"
        				+ avo.getAffirmOldCustomerVisitNum() + ","
        				+ avo.getNewCustomerVisitCount() + ","
        				+ avo.getValidNewCustomerVisitCount() + ","
        				+ avo.getOldCustomerVisitCount() + ","
        				+ avo.getValidOldCustomerVisitCount() + ","
        				+ avo.getVisitAgain() + ")";
        		pstmt.executeUpdate(sql);
        		conn.commit();
        		//记录日志
                defaultStmt = (Statement) defaultConn.createStatement();
                String message = "新增t_agentvisitorder表的-到访-数据1条";
                String nowTime = DateUtil.format(new Date());
                String logSql = "insert into t_datahandledynamic(id, handleDate, handleState, handleUser, targetId, targetName, message)"
                        + " values(" + null + ",'" + nowTime + "',"+ Handle.HANDLE_AUTO + ",'" + userId + "','" + projectId + "','t_" + projectId + "','" + message + "')";
                defaultStmt.executeUpdate(logSql);
                defaultConn.commit();
        	}
        	pstmt.close();
            defaultStmt.close();
            conn.close();
            defaultConn.close();
        } catch (SQLException e) {
            
            try {
                if(conn != null){
                    conn.rollback();
                }
                if(defaultConn != null){
                    defaultConn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } catch (Exception ex){
            try {
                if(conn != null){
                    conn.rollback();
                }
                if(defaultConn != null){
                    defaultConn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            ex.printStackTrace();
        } finally {
            try {
                if(pstmt != null){
                    pstmt.close();
                }
                if(defaultStmt != null){
                    defaultStmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            try {
                if(conn != null){
                    conn.close();
                }
                if(defaultConn != null){
                    defaultConn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
	
	
	/**
	 * 归集订单部分----2
	 * @param avo
	 * @param projectId
	 * @param userId
	 */
	public void saveOrUpdateAgentDataForOrder(AgentVisitOrder avo, String projectId, String userId){
		 DataBase dataBase = selectDataBaseFromTargetId(projectId);
	        Connection conn = getMyConnection(dataBase);
	        Statement  pstmt = null;
	        //连接默认数据库
	        Connection defaultConn = getDefaultDataBase();
	        Statement defaultStmt = null;
	        
	        try {
	        	pstmt = (Statement) conn.createStatement();
	        	defaultStmt = (Statement) defaultConn.createStatement();
	            defaultConn.setAutoCommit(false);
	            conn.setAutoCommit(false);
	            //首先查出当前这条数据是否已经存在在数据库，如果存在就更新，不存在就添加
	            boolean flag = selectVisitRowByProjectIdAndTimeAndUserId(projectId, avo.getCvDate(), avo.getAgentId());
	            if(flag) {//已经存在
	            	String sql = "update t_agentvisitorder SET collDateTime = '" + avo.getCollDateTime() + "', "
	            			+ " enterBuyCount = " + avo.getEnterBuyCount() + ","
	            			+ " agreeEnterCount = " + avo.getAgreeEnterCount() + ","
	            			+ " refuseEnterCount = " + avo.getRefuseEnterCount() + ","
	            			+ " payCount = " + avo.getPayCount() + ","
	            			+ " waitSignCount = " + avo.getWaitSignCount() + ","
	            			+ " signCount = " + avo.getSignCount() + ","
	            			+ " revokeOrderCount = " + avo.getRevokeOrderCount() + ","
	            			+ " confirmHouseMoney = " + avo.getConfirmHouseMoney() + ","
	            			+ " subscribeHouseCount = " + avo.getSubscribeHouseCount() + ","
	            			+ " subscribeMoney = " + avo.getSubscribeMoney() + ","
	            			+ " subscribeGetMoney = " + avo.getSubscribeGetMoney() + ","
	            			+ " subscribeLockHouseMoney = " + avo.getSubscribeLockHouseMoney() + ","
	            			+ "	giveUpSignCount = " + avo.getGiveUpSignCount() + ","
	            			+ " waitSignCount = " + avo.getWaitSignCount() + ","
	            			+ " signHouseMoney = " + avo.getSignHouseMoney() + ","
	            			+ " giveUpHouseMoney = " + avo.getGiveUpHouseMoney() + ","
	            			+ " waitSignHouseMoney = " + avo.getWaitSignHouseMoney() + ","
	            			+ " signedOrderId = '" + avo.getSignedOrderId() + "',"
	            			+ " refusedOrderId = '" + avo.getRefusedOrderId() + "',"
	            			+ " revokeOrderId = '" + avo.getRevokeOrderId() + "',"
	            			+ " payOrderId = '" + avo.getPayOrderId() + "' where cvDate = '" + avo.getCvDate() + "' "
	            			+ " and agentId = '" + avo.getAgentId() + "'";
	            	pstmt.executeUpdate(sql);
	            	conn.commit();
	            	//记录日志
	                defaultStmt = (Statement) defaultConn.createStatement();
	                String message = "更新t_agentvisitorder表的-订单-数据1条";
	                String nowTime = DateUtil.format(new Date());
	                String logSql = "insert into t_datahandledynamic(id, handleDate, handleState, handleUser, targetId, targetName, message)"
	                        + " values(" + null + " ,'" + nowTime + "','"+ Handle.HANDLE_AUTO + "','" + userId + "','" + projectId + "','t_" + projectId + "','" + message + "')";
	                defaultStmt.executeUpdate(logSql);
	                defaultConn.commit();
	            } else {
	            	String sql = "insert into t_agentvisitorder(cvDate,agentId,agentName,agentPhone,agentStatus,collDateTime,agentStatus,enterBuyCount,"
	            			+ "agreeEnterCount,refuseEnterCount,payCount,waitSignCount,signCount,revokeOrderCount,confirmHouseMoney,"
	            			+ "subscribeHouseCount,subscribeMoney,subscribeGetMoney,subscribeLockHouseMoney,giveUpSignCount,"
	            			+ "waitSignCount,signHouseMoney,giveUpHouseMoney,waitSignHouseMoney,signedOrderId,refusedOrderId,"
	            			+ "revokeOrderId,payOrderId) values('" + avo.getCvDate() + "','" + avo.getAgentId() + "','" + avo.getAgentName() + "','"
	            			+ avo.getAgentPhone() +"'," + avo.getAgentStatus() + "," + avo.getEnterBuyCount() + ","
	            			+ avo.getAgreeEnterCount() + ","
	            			+ avo.getRefuseEnterCount() + ","
	            			+ avo.getPayCount() + ","
	            			+ avo.getWaitSignCount() + ","
	            			+ avo.getSignCount() + ","
	            			+ avo.getRevokeOrderCount() + ","
	            			+ avo.getConfirmHouseMoney() + ","
	            			+ avo.getSubscribeHouseCount() + ","
	            			+ avo.getSubscribeMoney() + ","
	            			+ avo.getSubscribeGetMoney() + ","
	            			+ avo.getSubscribeLockHouseMoney() + ","
	            			+ avo.getGiveUpSignCount() + ","
	            			+ avo.getWaitSignCount() + ","
	            			+ avo.getSignHouseMoney() + ","
	            			+ avo.getGiveUpHouseMoney() + ","
	            			+ avo.getWaitSignHouseMoney() + ",'"
	            			+ avo.getSignedOrderId() + "','"
	            			+ avo.getRefusedOrderId() + "','"
	            			+ avo.getRevokeOrderId() + "','"
	            			+ avo.getPayOrderId() + "')";
	            	pstmt.executeUpdate(sql);
	            	conn.commit();
	            	//记录日志
	                defaultStmt = (Statement) defaultConn.createStatement();
	                String message = "新增t_agentvisitorder表的-订单-数据1条";
	                String nowTime = DateUtil.format(new Date());
	                String logSql = "insert into t_datahandledynamic(id, handleDate, handleState, handleUser, targetId, targetName, message)"
	                        + " values(" + null + " ,'" + nowTime + "','"+ Handle.HANDLE_AUTO + "','" + userId + "','" + projectId + "','t_" + projectId + "','" + message + "')";
	                defaultStmt.executeUpdate(logSql);
	                defaultConn.commit();
	            }
	            pstmt.close();
	            defaultStmt.close();
	            conn.close();
	            defaultConn.close();
	        } catch (SQLException e) {
	            
	            try {
	                if(conn != null){
	                    conn.rollback();
	                }
	                if(defaultConn != null){
	                    defaultConn.rollback();
	                }
	            } catch (SQLException e1) {
	                e1.printStackTrace();
	            }
	            e.printStackTrace();
	        } catch (Exception ex){
	            try {
	                if(conn != null){
	                    conn.rollback();
	                }
	                if(defaultConn != null){
	                    defaultConn.rollback();
	                }
	            } catch (SQLException e1) {
	                e1.printStackTrace();
	            }
	            ex.printStackTrace();
	        } finally {
	            try {
	                if(pstmt != null){
	                    pstmt.close();
	                }
	                if(defaultStmt != null){
	                    defaultStmt.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            
	            try {
	                if(conn != null){
	                    conn.close();
	                }
	                if(defaultConn != null){
	                    defaultConn.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	}
	
	/**
	 * 归集储客记录部分----3
	 * @param avo
	 * @param projectId
	 * @param userId
	 */
	public void saveOrUpdateMemoryCustomerData(AgentVisitOrder avo, String projectId, String userId){
		DataBase dataBase = selectDataBaseFromTargetId(projectId);
        Connection conn = getMyConnection(dataBase);
        Statement  pstmt = null;
        //连接默认数据库
        Connection defaultConn = getDefaultDataBase();
        Statement defaultStmt = null;
        
        try {
            defaultConn.setAutoCommit(false);
            conn.setAutoCommit(false);
            //首先查出当前这条数据是否已经存在在数据库，如果存在就更新，不存在就添加
            boolean flag = selectVisitRowByProjectIdAndTimeAndUserId(projectId, avo.getCvDate(), avo.getAgentId());
            pstmt = (Statement) conn.createStatement();
            defaultStmt = (Statement) defaultConn.createStatement();
            if(flag){//已经存在
            	String sql = "update t_agentvisitorder SET collDateTime = '" + avo.getCollDateTime() + "', "
                        + " agentStatus = " + avo.getAgentStatus() + ", "
                        + " newAddCollCustomerCount = " + avo.getNewAddCollCustomerCount() + ","
                        + " grandTotalCollCustomerCount = " + avo.getGrandTotalCollCustomerCount() + ","
                        + "	grandTotalOldCustomerCount = " + avo.getGrandTotalOldCustomerCount() + ","
                        + " totalCustomerCount = " + avo.getTotalCustomerCount() + ","
                        + " totalOldCustomerCount = " + avo.getTotalOldCustomerCount() + ","
                        + "	newTwoVisitCustomerCount = " + avo.getNewTwoVisitCustomerCount() + ","
                        + " customerReturnBackVisitNum = " + avo.getCustomerReturnBackVisitNum() + ","
                        + " subscribeCustomerCount = " + avo.getSubscribeCustomerCount() + ","
                        + " newSubscribeCustomerCount = " + avo.getNewSubscribeCustomerCount() + ","
                        + " oldCustomerSignedCount = " + avo.getOldCustomerSignedCount() + ","
                        + " newCustomerSignedCount = " + avo.getNewCustomerSignedCount() + ","
                        + "	oldSubscribeCustomerCount = " + avo.getOldSubscribeCustomerCount() + ","
                        + " visitToDealCount = " + avo.getVisitToDealCount() + ","
                        + " momeryCuDealCount = " + avo.getMomeryCuDealCount() + ","
                        + "	newAddCollCustomerId = '" + avo.getNewAddCollCustomerId() + "',"
                        + " grandTotalCollCustomerId = '" + avo.getGrandTotalCollCustomerId() + "',"
                        + " grandTotalOldCustomerId = '" + avo.getGrandTotalOldCustomerId() + "',"
                        + " platformCustomerCount = " + avo.getPlatformCustomerCount() + " "
                        + " where cvDate = '" + avo.getCvDate() + "' "
                        + " and agentId = '" + avo.getAgentId() + "'";
            	pstmt.executeUpdate(sql);
            	conn.commit();
            	//记录日志
                defaultStmt = (Statement) defaultConn.createStatement();
                String message = "更新t_agentvisitorder表的-储客-数据1条";
                String nowTime = DateUtil.format(new Date());
                String logSql = "insert into t_datahandledynamic(id, handleDate, handleState, handleUser, targetId, targetName, message)"
                        + " values(" + null + " ,'" + nowTime + "','"+ Handle.HANDLE_AUTO + "','" + userId + "','" + projectId + "','t_" + projectId + "','" + message + "')";
                defaultStmt.executeUpdate(logSql);
                defaultConn.commit();
            } else {
            	String sql = "insert into t_agentvisitorder(cvDate,agentId,collDateTime,agentStatus,"
            			+ "newAddCollCustomerCount,grandTotalCollCustomerCount,grandTotalOldCustomerCount,"
            			+ "newTwoVisitCustomerCount,subscribeCustomerCount,newSubscribeCustomerCount,"
            			+ "oldSubscribeCustomerCount,newAddCollCustomerId,grandTotalCollCustomerId,"
            			+ "grandTotalOldCustomerId,platformCustomerCount,customerReturnBackVisitNum,visitToDealCount,totalCustomerCount,totalOldCustomerCount,momeryCuDealCount) values('" + avo.getCvDate() + "','"
            			+ avo.getAgentId() + "','" + avo.getCollDateTime() + "',"
            			+ avo.getAgentStatus() + ","
            			+ avo.getNewAddCollCustomerCount() + ","
            			+ avo.getGrandTotalCollCustomerCount() + ","
            			+ avo.getGrandTotalOldCustomerCount() + ","
            			+ avo.getNewTwoVisitCustomerCount() + ","
            			+ avo.getSubscribeCustomerCount() + ","
            			+ avo.getNewSubscribeCustomerCount() + ","
            			+ avo.getOldSubscribeCustomerCount() + ",'"
            			+ avo.getNewAddCollCustomerId() + "','"
            			+ avo.getGrandTotalCollCustomerId() + "','"
            			+ avo.getGrandTotalOldCustomerId() + "',"
            			+ avo.getPlatformCustomerCount() + ","
            			+ avo.getCustomerReturnBackVisitNum() + ","
            			+ avo.getVisitToDealCount() +","
            			+ avo.getTotalCustomerCount() + ","
            			+ avo.getTotalOldCustomerCount() + ","
            			+ avo.getMomeryCuDealCount() + ")";
            	pstmt.executeUpdate(sql);
            	conn.commit();
            	 //记录日志
                defaultStmt = (Statement) defaultConn.createStatement();
                String message = "新增t_agentvisitorder表的-储客-数据1条";
                String nowTime = DateUtil.format(new Date());
                String logSql = "insert into t_datahandledynamic(id, handleDate, handleState, handleUser, targetId, targetName, message)"
                        + " values(" + null + " ,'" + nowTime + "','"+ Handle.HANDLE_AUTO + "','" + userId + "','" + projectId + "','t_" + projectId + "','" + message + "')";
                defaultStmt.executeUpdate(logSql);
                defaultConn.commit();
            }
            pstmt.close();
            defaultStmt.close();
            conn.close();
            defaultConn.close();
        } catch (SQLException e) {
            
            try {
                if(conn != null){
                    conn.rollback();
                }
                if(defaultConn != null){
                    defaultConn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } catch (Exception ex){
            try {
                if(conn != null){
                    conn.rollback();
                }
                if(defaultConn != null){
                    defaultConn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            ex.printStackTrace();
        } finally {
            try {
                if(pstmt != null){
                    pstmt.close();
                }
                if(defaultStmt != null){
                    defaultStmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            try {
                if(conn != null){
                    conn.close();
                }
                if(defaultConn != null){
                    defaultConn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
	
	/**
	 * 归集考勤记录部分----4
	 * @param avo
	 * @param projectId
	 * @param userId
	 */
	public void saveOrUpdateSignData(AgentVisitOrder avo, String projectId, String userId){
		DataBase dataBase = selectDataBaseFromTargetId(projectId);
        Connection conn = getMyConnection(dataBase);
        Statement  pstmt = null;
        //连接默认数据库
        Connection defaultConn = getDefaultDataBase();
        Statement defaultStmt = null;
        
        try {
            defaultConn.setAutoCommit(false);
            conn.setAutoCommit(false);
            //首先查出当前这条数据是否已经存在在数据库，如果存在就更新，不存在就添加
            boolean flag = selectVisitRowByProjectIdAndTimeAndUserId(projectId, avo.getCvDate(), avo.getAgentId());
            pstmt = (Statement) conn.createStatement();
            defaultStmt = (Statement) defaultConn.createStatement();
            if(flag){//已经存在
            	String sql = "update t_agentvisitorder SET collDateTime = '" + avo.getCollDateTime() + "', "
                        + " agentStatus = " + avo.getAgentStatus() + ", "
                        + "	signInTime = '" + avo.getSignInTime() + "',"
                        + "	signOutTime = '" + avo.getSignOutTime() + "' where cvDate = '" + avo.getCvDate() + "' "
                        + " and agentId = '" + avo.getAgentId() + "'";
            	pstmt.executeUpdate(sql);
            	conn.commit();
            	 //记录日志
                defaultStmt = (Statement) defaultConn.createStatement();
                String message = "更新t_agentvisitorder表的-考勤-数据1条";
                String nowTime = DateUtil.format(new Date());
                String logSql = "insert into t_datahandledynamic(id, handleDate, handleState, handleUser, targetId, targetName, message)"
                        + " values(" + null + " ,'" + nowTime + "','"+ Handle.HANDLE_AUTO + "','" + userId + "','" + projectId + "','t_" + projectId + "','" + message + "')";
                defaultStmt.executeUpdate(logSql);
                defaultConn.commit();
            } else {
            	String sql = "insert into t_agentvisitorder(cvDate,collDateTime,agentStatus,"
            			+ "signInTime,signOutTime,agentId) values('"
            			+ avo.getCvDate() + "','"
                        + avo.getCollDateTime() +"',"
                        + avo.getAgentStatus() + ",'"
                        + avo.getSignInTime() + "','"
                        + avo.getSignOutTime() + "','"
                        + avo.getAgentId() + "')";
            	pstmt.executeUpdate(sql);
            	conn.commit();
            	//记录日志
                defaultStmt = (Statement) defaultConn.createStatement();
                String message = "新增t_agentvisitorder表的-考勤-数据1条";
                String nowTime = DateUtil.format(new Date());
                String logSql = "insert into t_datahandledynamic(id, handleDate, handleState, handleUser, targetId, targetName, message)"
                        + " values(" + null + " ,'" + nowTime + "','"+ Handle.HANDLE_AUTO + "','" + userId + "','" + projectId + "','t_" + projectId + "','" + message + "')";
                defaultStmt.executeUpdate(logSql);
                defaultConn.commit();
            }
            pstmt.close();
            defaultStmt.close();
            conn.close();
            defaultConn.close();
        } catch (SQLException e) {
            
            try {
                if(conn != null){
                    conn.rollback();
                }
                if(defaultConn != null){
                    defaultConn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } catch (Exception ex){
            try {
                if(conn != null){
                    conn.rollback();
                }
                if(defaultConn != null){
                    defaultConn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            ex.printStackTrace();
        } finally {
            try {
                if(pstmt != null){
                    pstmt.close();
                }
                if(defaultStmt != null){
                    defaultStmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            try {
                if(conn != null){
                    conn.close();
                }
                if(defaultConn != null){
                    defaultConn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
	
	
	
	
	/**
	 * 判断房源信息这一行是否已经存在于数据表
	 * @param projectId
	 * @param Date
	 * @param houseTypeId
	 * @return
	 */
	public boolean selectHouseTypeDataIsExist(String projectId, String date, String houseTypeId){
		//首先要根据projectId找到所属的数据库
				DataBase dataBase = BaseDao.selectDataBaseFromTargetId(projectId);
				Connection conn = BaseDao.getMyConnection(dataBase);
				Statement stmt = null;
				
				try {
					stmt = (Statement) conn.createStatement();
					String sql = "select * from t_housetypehouseandorder where chDate = '" + date + "' "
							+ " and houseTypeId = '" + houseTypeId + "' ";
					ResultSet rs = (ResultSet) stmt.executeQuery(sql);
					while(rs.next()){
						return true;//表示已经建立过这一天的房源的那一行数据
					}
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e){
					e.printStackTrace();
				} finally {
					try {
						if(stmt != null){
							stmt.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					try {
						if(conn != null){
							conn.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
		return false;
	}
	
	
	/**
	 * 归集户型房源订单的数据  - - 1
	 * @param hth
	 * @param projectId
	 * @param userId
	 */
	public void saveOrUpdateHouseTypeOrderData(HouseTypeHouseAndOrder hth, String projectId, String userId){
		DataBase dataBase = selectDataBaseFromTargetId(projectId);
        Connection conn = getMyConnection(dataBase);
        Statement  pstmt = null;
        //连接默认数据库
        Connection defaultConn = getDefaultDataBase();
        Statement defaultStmt = null;
        
        try {
        	pstmt = (Statement) conn.createStatement();
        	defaultStmt = (Statement) defaultConn.createStatement();
            defaultConn.setAutoCommit(false);
            conn.setAutoCommit(false);
            //查找当前的数据行是否已经存在了
            boolean flag = selectHouseTypeDataIsExist(projectId, hth.getChDate(), hth.getHouseTypeId());
            if(flag){
            	String sql = "update t_housetypehouseandorder SET collDateTime = '" + hth.getCollDateTime() + "',"
            			+ " enterBuyCount = " + hth.getEnterBuyCount() + ","
            			+ " agreeEnterCount = " + hth.getAgreeEnterCount() + ","
            			+ " area = " + hth.getArea() + ","
            			+ " refuseEnterCount = " + hth.getRefuseEnterCount() + ","
            			+ " payCount = " + hth.getPayCount() + ","
            			+ " waitSignCount = " + hth.getWaitSignCount() + ","
            			+ " signCount = " + hth.getSignCount() + ","
            			+ " revokeOrderCount = " + hth.getRevokeOrderCount() + " where chDate = '" + hth.getChDate() + "' "
            					+ "and houseTypeId = '" + hth.getHouseTypeId() + "'";
            	pstmt.executeUpdate(sql);
            	conn.commit();
            	//记录日志
    			defaultStmt = (Statement) defaultConn.createStatement();
    			String message = "更新t_housetypehouseandorder表的-订单-数据1条";
    			String nowTime = DateUtil.format(new Date());
    			String logSql = "insert into t_datahandledynamic(id, handleDate, handleState, handleUser, targetId, targetName, message)"
    					+ " values(" + null + ",'" + nowTime + "','"+ Handle.HANDLE_AUTO + "','" + userId + "','" + projectId + "','t_" + projectId + "','" + message + "')";
    			defaultStmt.executeUpdate(logSql);
    			defaultConn.commit();
            } else {
            	String sql = "insert into t_housetypehouseandorder(chDate,collDateTime,houseTypeId,houseTypeName,"
            			+ "enterBuyCount,agreeEnterCount,refuseEnterCount,payCount,waitSignCount,signCount,revokeOrderCount,area)"
            			+ " values('" + hth.getChDate() + "','" + hth.getCollDateTime() + "','" 
            			+ hth.getHouseTypeId() + "','"
            			+ hth.getHouseTypeName() + "',"
            			+ hth.getEnterBuyCount() + ","
            			+ hth.getAgreeEnterCount() + ","
            			+ hth.getRefuseEnterCount() + ","
            			+ hth.getPayCount() + ","
            			+ hth.getWaitSignCount() + ","
            			+ hth.getSignCount() + ","
            			+ hth.getRevokeOrderCount() + ","
            			+ hth.getArea() + ")";
            	pstmt.executeUpdate(sql);
            	conn.commit();
            	//记录日志
    			defaultStmt = (Statement) defaultConn.createStatement();
    			String message = "新增t_housetypehouseandorder表的-订单-数据1条";
    			String nowTime = DateUtil.format(new Date());
    			String logSql = "insert into t_datahandledynamic(id, handleDate, handleState, handleUser, targetId, targetName, message)"
    					+ " values(" + null + ",'" + nowTime + "','"+ Handle.HANDLE_AUTO + "','" + userId + "','" + projectId + "','t_" + projectId + "','" + message + "')";
    			defaultStmt.executeUpdate(logSql);
    			defaultConn.commit();		
            }
            pstmt.close();
			defaultStmt.close();
			conn.close();
			defaultConn.close();
        } catch (SQLException e) {
            
            try {
                if(conn != null){
                    conn.rollback();
                }
                if(defaultConn != null){
                    defaultConn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } catch (Exception ex){
            try {
                if(conn != null){
                    conn.rollback();
                }
                if(defaultConn != null){
                    defaultConn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            ex.printStackTrace();
        } finally {
            try {
                if(pstmt != null){
                    pstmt.close();
                }
                if(defaultStmt != null){
                    defaultStmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            try {
                if(conn != null){
                    conn.close();
                }
                if(defaultConn != null){
                    defaultConn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
	
	/**
	 * 归集房源的数据 -- 2
	 * @param hth
	 * @param projectId
	 * @param userId
	 */
	public void saveOrUpdateHouseData(HouseTypeHouseAndOrder hth, String projectId, String userId){
		 DataBase dataBase = selectDataBaseFromTargetId(projectId);
	        Connection conn = getMyConnection(dataBase);
	        Statement  pstmt = null;
	        //连接默认数据库
	        Connection defaultConn = getDefaultDataBase();
	        Statement defaultStmt = null;
	        
	        try {
	        	pstmt = (Statement) conn.createStatement();
	        	defaultStmt = (Statement) defaultConn.createStatement();
	            defaultConn.setAutoCommit(false);
	            conn.setAutoCommit(false);
	            //查找当前的数据行是否已经存在了
	            boolean flag = selectHouseTypeDataIsExist(projectId, hth.getChDate(), hth.getHouseTypeId());
	            if(flag){//已经存在
	                String sql = "update t_housetypehouseandorder SET collDateTime = '" + hth.getCollDateTime() + "',"
	                		+ " houseTotalCount = " + hth.getHouseTotalCount() + ","
	                		+ " putAwayCount = " + hth.getPutAwayCount() + ","
	                		+ " downAwayCount = " + hth.getDownAwayCount() + ","
	                		+ " sallingCount = " + hth.getSallingCount() + ","
	                		+ " signedCount = " + hth.getSignedCount() + ","
	                		+ " revokedHouseCount = " + hth.getRevokedHouseCount() + " where chDate = '" + hth.getChDate() + "' "
                            + "and houseTypeId = '" + hth.getHouseTypeId() + "'";
	                pstmt.executeUpdate(sql);
	                conn.commit();
	                //记录日志
	                defaultStmt = (Statement) defaultConn.createStatement();
	                String message = "更新t_housetypehouseandorder表的-房源-数据1条";
	                String nowTime = DateUtil.format(new Date());
	                String logSql = "insert into t_datahandledynamic(id, handleDate, handleState, handleUser, targetId, targetName, message)"
	                        + " values(" + null + ",'" + nowTime + "','"+ Handle.HANDLE_AUTO + "','" + userId + "','" + projectId + "','t_" + projectId + "','" + message + "')";
	                defaultStmt.executeUpdate(logSql);
	                defaultConn.commit();
	            } else {//尚不存在
	            	String sql = "insert into t_housetypehouseandorder(chDate,collDateTime,houseTypeId,houseTypeName,"
	            			+ "houseTotalCount,putAwayCount,downAwayCount,sallingCount,signedCount,revokedHouseCount)"
	            			+ " values('" + hth.getChDate() + "','" + hth.getCollDateTime() + "','" 
                        + hth.getHouseTypeId() + "','"
                        + hth.getHouseTypeName() + "',"
                        + hth.getHouseTotalCount() + ","
                        + hth.getPutAwayCount() + ","
                        + hth.getDownAwayCount() + ","
                        + hth.getSallingCount() + ","
                        + hth.getSignedCount() + ","
                        + hth.getRevokedHouseCount() + ")";
	            	pstmt.executeUpdate(sql);
	            	conn.commit();
	            	//记录日志
	                defaultStmt = (Statement) defaultConn.createStatement();
	                String message = "新增t_housetypehouseandorder表的-房源-数据1条";
	                String nowTime = DateUtil.format(new Date());
	                String logSql = "insert into t_datahandledynamic(id, handleDate, handleState, handleUser, targetId, targetName, message)"
	                        + " values(" + null + ",'" + nowTime + "','"+ Handle.HANDLE_AUTO + "','" + userId + "','" + projectId + "','t_" + projectId + "','" + message + "')";
	                defaultStmt.executeUpdate(logSql);
	                defaultConn.commit();
	            }
	        }  catch (SQLException e) {
	            
	            try {
	                if(conn != null){
	                    conn.rollback();
	                }
	                if(defaultConn != null){
	                    defaultConn.rollback();
	                }
	            } catch (SQLException e1) {
	                e1.printStackTrace();
	            }
	            e.printStackTrace();
	        } catch (Exception ex){
	            try {
	                if(conn != null){
	                    conn.rollback();
	                }
	                if(defaultConn != null){
	                    defaultConn.rollback();
	                }
	            } catch (SQLException e1) {
	                e1.printStackTrace();
	            }
	            ex.printStackTrace();
	        } finally {
	            try {
	                if(pstmt != null){
	                    pstmt.close();
	                }
	                if(defaultStmt != null){
	                    defaultStmt.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            
	            try {
	                if(conn != null){
	                    conn.close();
	                }
	                if(defaultConn != null){
	                    defaultConn.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	}
	
	
	
	/**
	 * 查找外场这一行是否已经存在
	 * @param projectId
	 * @param date
	 * @return
	 */
	public boolean selectOutsideRowIsExist(String projectId, String date){
		//首先要根据projectId找到所属的数据库
		DataBase dataBase = BaseDao.selectDataBaseFromTargetId(projectId);
		Connection conn = BaseDao.getMyConnection(dataBase);
		Statement stmt = null;
		
		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_outsideproject where copDate = '" + date + "' ";
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while(rs.next()){
				return true;//表示已经建立过这一天的房源的那一行数据
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * 归集外场数据
	 * @param os
	 * @param projectId
	 * @param userId
	 */
	public void saveOrUpdateOutSideData(OutSideProject os, String projectId, String userId){
		DataBase dataBase = selectDataBaseFromTargetId(projectId);
        Connection conn = getMyConnection(dataBase);
        Statement  pstmt = null;
        //连接默认数据库
        Connection defaultConn = getDefaultDataBase();
        Statement defaultStmt = null;
        
        try {
        	pstmt = (Statement) conn.createStatement();
        	defaultStmt = (Statement) defaultConn.createStatement();
            defaultConn.setAutoCommit(false);
            conn.setAutoCommit(false);
            //查找当前的数据行是否已经存在了
            boolean flag = selectOutsideRowIsExist(projectId, os.getCopDate());
            if(flag) {//如果存在
            	String sql = "update t_outsideproject SET collDateTime = '" + os.getCollDateTime() + "',"
            			+ " guideCount = " + os.getGuideCount() + ","
            			+ " visitedCount = " + os.getVisitedCount() + ","
            			+ " recordCustomerCount = " + os.getRecordCustomerCount() + ","
            			+ " recordVisitCount = " + os.getRecordVisitCount() + ","
            			+ " guiCustomerRecordCount = " + os.getGuiCustomerRecordCount() + ","
            			+ " outSideCustomerRecordCount = " + os.getOutSideCustomerRecordCount() + ","
            			+ " recordNotVisitCount = " + os.getRecordNotVisitCount() + ","
            			+ " guideCustomerVisitCount = " + os.getGuideCustomerVisitCount() + ","
            			+ " outSideDealCount = " + os.getOutSideDealCount() + ","
            			+ " intFiledToDealNum = " + os.getIntFiledToDealNum() + ","
            			+ " outSideAgreeRecordCount = " + os.getOutSideAgreeRecordCount() + ","
            			+ " outSideRefuseRecordCount = " + os.getOutSideRefuseRecordCount() + ","
            			+ " outSideWaitSignCount = " + os.getOutSideWaitSignCount() + ","
            			+ " outSideRevokeOrderCount = " + os.getOutSideRevokeOrderCount() + ","
            			+ " outSideSubscribeHouseCount = " + os.getOutSideSubscribeHouseCount() + ","
            			+ " outSideSubscribeMoney = " + os.getOutSideSubscribeMoney() + ","
            			+ " outSideSubscribeGetMoney = " + os.getOutSideSubscribeGetMoney() + ","
            			+ " outSideSubscribeLockHouseMoney = " + os.getOutSideSubscribeLockHouseMoney() + ","
            			+ " outSideGiveUpHouseMoney = " + os.getOutSideGiveUpHouseMoney() + ","
            			+ " outSideWaitSignHouseMoney = " + os.getOutSideWaitSignHouseMoney() + ","
            			+ " outSideSignHouseMoney = " + os.getOutSideSignHouseMoney() + ","
            			+ " outSideRecordCuCount = " + os.getOutSideRecordCuCount() + ","
            			+ " outSideNewCuRecordCount = " + os.getOutSideNewCuRecordCount() + ","
            			+ " outSideOldCuRecordCount = " + os.getOutSideOldCuRecordCount() + ","
            			+ " outSideSignCuCount = " + os.getOutSideSignCuCount() + ","
            			+ " outSideNewCuSignCount = " + os.getOutSideNewCuSignCount() + ","
            			+ " outSideOldSignCount = " + os.getOutSideOldSignCount() + ","
            			+ " guidedHouseNum = '" + os.getGuidedHouseNum() + "',"
            			+ " visitedHouseNum = '" + os.getVisitedHouseNum() + "',"
            			+ " guidedShopId = '" + os.getGuidedShopId() + "',"
            			+ " guidedShopAgentId = '" + os.getGuidedShopAgentId() + "',"
            			+ " visitedShopId = '" + os.getVisitedShopId() + "',"
            			+ " visitedShopAgentId = '" + os.getVisitedShopAgentId() 
            			+ "' where copDate = '" + os.getCopDate() + "'";
            	pstmt.executeUpdate(sql);
            	conn.commit();
            	//记录日志
    			defaultStmt = (Statement) defaultConn.createStatement();
                String message = "更新t_outsideproject表的数据1条";
                String nowTime = DateUtil.format(new Date());
                String logSql = "insert into t_datahandledynamic(id, handleDate, handleState, handleUser, targetId, targetName, message)"
                        + " values(" + null + ",'" + nowTime + "','"+ Handle.HANDLE_AUTO + "','" + userId + "','" + projectId + "','t_" + projectId + "','" + message + "')";
                defaultStmt.executeUpdate(logSql);
                defaultConn.commit();
            } else {
            	String sql = "insert into t_outsideproject(copDate,collDateTime,"
            			+ "guideCount,visitedCount,recordCustomerCount,recordVisitCount,"
            			+ "recordNotVisitCount,guideCustomerVisitCount,outSideDealCount,"
            			+ "guidedHouseNum,visitedHouseNum,guidedShopId,guidedShopAgentId,"
            			+ "visitedShopId,visitedShopAgentId,intFiledToDealNum,guiCustomerRecordCount,outSideCustomerRecordCount,"
            			+ "outSideAgreeRecordCount,outSideRefuseRecordCount,outSideWaitSignCount,outSideRevokeOrderCount,"
            			+ "outSideSubscribeHouseCount,outSideSubscribeMoney,outSideSubscribeGetMoney,"
            			+ "outSideSubscribeLockHouseMoney,outSideGiveUpHouseMoney,outSideWaitSignHouseMoney,outSideSignHouseMoney,"
            			+ "outSideRecordCuCount,outSideNewCuRecordCount,outSideOldCuRecordCount,outSideSignCuCount,"
            			+ "outSideNewCuSignCount,outSideOldSignCount) values('" + os.getCopDate() + "','" 
            			+ os.getCollDateTime() + "'," + os.getGuideCount() + ","
            			+ os.getVisitedCount() + ","
            			+ os.getRecordCustomerCount() + ","
            			+ os.getRecordVisitCount() + ","
            			+ os.getRecordNotVisitCount() + ","
            			+ os.getGuideCustomerVisitCount() + ","
            			+ os.getOutSideDealCount() + ",'"
            			+ os.getGuidedHouseNum() + "','"
            			+ os.getVisitedHouseNum() + "','"
            			+ os.getGuidedShopId() + "','"
            			+ os.getGuidedShopAgentId() + "','"
            			+ os.getVisitedShopId() + "','"
            			+ os.getVisitedShopAgentId() + "',"
            			+ os.getIntFiledToDealNum() + ","
            			+ os.getGuiCustomerRecordCount() + ","
            			+ os.getOutSideCustomerRecordCount() + ","
            			+ os.getOutSideAgreeRecordCount() + ","
            			+ os.getOutSideRefuseRecordCount() + ","
            			+ os.getOutSideWaitSignCount() + ","
            			+ os.getOutSideRevokeOrderCount() + ","
            			+ os.getOutSideSubscribeHouseCount() + ","
            			+ os.getOutSideSubscribeMoney() + ","
            			+ os.getOutSideSubscribeGetMoney() + ","
            			+ os.getOutSideSubscribeLockHouseMoney() + ","
            			+ os.getOutSideGiveUpHouseMoney() + ","
            			+ os.getOutSideWaitSignHouseMoney() + ","
            			+ os.getOutSideSignHouseMoney() + ","
            			+ os.getOutSideRecordCuCount() + ","
            			+ os.getOutSideNewCuRecordCount() + ","
            			+ os.getOutSideOldCuRecordCount() + ","
            			+ os.getOutSideSignCuCount() + ","
            			+ os.getOutSideNewCuSignCount() + ","
            			+ os.getOutSideOldSignCount() + ")";
            	pstmt.executeUpdate(sql);
            	conn.commit();
            	//记录日志
    			defaultStmt = (Statement) defaultConn.createStatement();
                String message = "添加t_outsideproject表的数据1条";
                String nowTime = DateUtil.format(new Date());
                String logSql = "insert into t_datahandledynamic(id, handleDate, handleState, handleUser, targetId, targetName, message)"
                        + " values(" + null + ",'" + nowTime + "','"+ Handle.HANDLE_AUTO + "','" + userId + "','" + projectId + "','t_" + projectId + "','" + message + "')";
                defaultStmt.executeUpdate(logSql);
                defaultConn.commit();
            }
            pstmt.close();
            defaultStmt.close();
            conn.close();
            defaultConn.close();
        } catch (SQLException e) {
            
            try {
                if(conn != null){
                    conn.rollback();
                }
                if(defaultConn != null){
                    defaultConn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } catch (Exception ex){
            try {
                if(conn != null){
                    conn.rollback();
                }
                if(defaultConn != null){
                    defaultConn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            ex.printStackTrace();
        } finally {
            try {
                if(pstmt != null){
                    pstmt.close();
                }
                if(defaultStmt != null){
                    defaultStmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            try {
                if(conn != null){
                    conn.close();
                }
                if(defaultConn != null){
                    defaultConn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
	
	/**
	 * 根据projectId查找所属的置业顾问的接访数据
	 * @param projectId
	 * @param startTime  格式 yyyy-MM-dd
	 * @param endTime 格式 yyyy-MM-dd
	 * @param oneDay  格式 yyyy-MM-dd
	 * @return
	 */
	public List<AgentVisitOrder> selectAgentVisitOrderListForProject(String projectId, String startTime, String endTime, String oneDay){
		
		//项目归集的数据库
		DataBase targetDataBase = selectDataBaseFromTargetId(projectId);
		Connection conn = getMyConnection(targetDataBase);
		Statement stmt = null;
		List<AgentVisitOrder> list = new LinkedList<>();
		
		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_agentvisitorder where 1=1 ";
			if(oneDay != null && !"".equals(oneDay)){
				sql += " and cvDate like '%" + oneDay + "%'";
			}else{
				if(startTime != null && !"".equals(startTime)){
					sql += " and cvDate >= '" + startTime + "' ";
				}
				if(endTime != null && !"".equals(endTime)){
					sql += " and cvDate <= '" + endTime + "' ";
				}
			}
            ResultSet rs = (ResultSet) stmt.executeQuery(sql);
            
            while(rs.next()){
            	AgentVisitOrder avo = new AgentVisitOrder();
            	avo.setAgentId(rs.getString("agentId"));
            	avo.setAgentName(rs.getString("agentName"));
            	avo.setAgentPhone(rs.getString("agentPhone"));
            	avo.setCvDate(rs.getString("cvDate"));
            	avo.setCollDateTime(rs.getString("collDateTime"));
            	avo.setAgentStatus(rs.getInt("agentStatus"));
            	//到访
            	avo.setVisitCount(rs.getInt("visitCount"));
            	avo.setValidVisitCount(rs.getInt("validVisitCount"));
            	avo.setNewVisitCount(rs.getInt("newVisitCount"));
            	avo.setVisitAgain(rs.getInt("visitAgain"));
            	avo.setSecondVisitCount(rs.getInt("secondVisitCount"));
            	avo.setMoreVisitCount(rs.getInt("moreVisitCount"));
            	avo.setAppointCount(rs.getInt("appointCount"));
            	avo.setNewWayVisitCount(rs.getInt("newWayVisitCount"));
            	avo.setValidNewWayVisitCount(rs.getInt("validNewWayVisitCount"));
            	avo.setAppointNewWayVisitCount(rs.getInt("appointNewWayVisitCount"));
            	avo.setOldWayVisitCount(rs.getInt("oldWayVisitCount"));
            	avo.setValidOldWayVisitCount(rs.getInt("validOldWayVisitCount"));
            	avo.setAppointOldWayVisitCount(rs.getInt("appointOldWayVisitCount"));
            	avo.setReplaceCount(rs.getInt("replaceCount"));
            	avo.setOrderReplaceVisitCount(rs.getInt("orderReplaceVisitCount"));
            	avo.setAppointLosedCount(rs.getInt("appointLosedCount"));
            	avo.setLosedCount(rs.getInt("losedCount"));
            	avo.setNewAppointCount(rs.getInt("newAppointCount"));
            	avo.setNewCustomerVisitTime(rs.getString("newCustomerVisitTime"));
            	avo.setOldCustomerVisitTime(rs.getString("oldCustomerVisitTime"));
            	avo.setReplaceVisitTime(rs.getString("replaceVisitTime"));
            	avo.setTotalVisitTime(rs.getString("totalVisitTime"));
            	avo.setAverageVisitTime(rs.getString("averageVisitTime"));
            	avo.setVisitId(rs.getString("visitId"));
            	avo.setAffirmOldCustomerVisitNum(rs.getInt("affirmOldCustomerVisitNum"));
            	avo.setNewCustomerVisitCount(rs.getInt("newCustomerVisitCount"));
            	avo.setValidNewCustomerVisitCount(rs.getInt("validNewCustomerVisitCount"));
            	avo.setOldCustomerVisitCount(rs.getInt("oldCustomerVisitCount"));
            	avo.setValidOldCustomerVisitCount(rs.getInt("validOldCustomerVisitCount"));
            	
            	//订单
            	avo.setEnterBuyCount(rs.getInt("enterBuyCount"));
            	avo.setAgreeEnterCount(rs.getInt("agreeEnterCount"));
            	avo.setRefuseEnterCount(rs.getInt("refuseEnterCount"));
            	avo.setPayCount(rs.getInt("payCount"));
            	avo.setWaitSignCount(rs.getInt("waitSignCount"));
            	avo.setSignCount(rs.getInt("signCount"));
            	avo.setRevokeOrderCount(rs.getInt("revokeOrderCount"));
            	avo.setConfirmHouseMoney(rs.getDouble("confirmHouseMoney"));
            	avo.setSubscribeHouseCount(rs.getInt("subscribeHouseCount"));
            	avo.setSubscribeMoney(rs.getDouble("subscribeMoney"));
            	avo.setSubscribeGetMoney(rs.getDouble("subscribeGetMoney"));
            	avo.setSubscribeLockHouseMoney(rs.getDouble("subscribeLockHouseMoney"));
            	avo.setGiveUpSignCount(rs.getInt("giveUpSignCount"));
            	avo.setWaitSignCount(rs.getInt("waitSignCount"));
            	avo.setSignHouseMoney(rs.getDouble("signHouseMoney"));
            	avo.setGiveUpHouseMoney(rs.getDouble("giveUpHouseMoney"));
            	avo.setWaitSignHouseMoney(rs.getDouble("waitSignHouseMoney"));
            	avo.setSignedOrderId(rs.getString("signedOrderId"));
            	avo.setRefusedOrderId(rs.getString("refusedOrderId"));
            	avo.setRevokeOrderId(rs.getString("revokeOrderId"));
            	avo.setPayOrderId(rs.getString("payOrderId"));
            	//储客
            	avo.setNewAddCollCustomerCount(rs.getInt("newAddCollCustomerCount"));
            	avo.setGrandTotalCollCustomerCount(rs.getInt("grandTotalCollCustomerCount"));
            	avo.setGrandTotalOldCustomerCount(rs.getInt("grandTotalOldCustomerCount"));
            	avo.setTotalCustomerCount(rs.getInt("totalCustomerCount"));
            	avo.setTotalOldCustomerCount(rs.getInt("totalOldCustomerCount"));
            	avo.setNewTwoVisitCustomerCount(rs.getInt("newTwoVisitCustomerCount"));
            	avo.setCustomerReturnBackVisitNum(rs.getInt("customerReturnBackVisitNum"));
            	avo.setSubscribeCustomerCount(rs.getInt("subscribeCustomerCount"));
            	avo.setNewSubscribeCustomerCount(rs.getInt("newSubscribeCustomerCount"));
            	avo.setOldCustomerSignedCount(rs.getInt("oldCustomerSignedCount"));
            	avo.setNewCustomerSignedCount(rs.getInt("newCustomerSignedCount"));
            	avo.setOldSubscribeCustomerCount(rs.getInt("oldSubscribeCustomerCount"));
            	avo.setVisitToDealCount(rs.getInt("visitToDealCount"));
            	avo.setMomeryCuDealCount(rs.getInt("momeryCuDealCount"));
            	avo.setNewAddCollCustomerId(rs.getString("newAddCollCustomerId"));
            	avo.setGrandTotalCollCustomerId(rs.getString("grandTotalCollCustomerId"));
            	avo.setGrandTotalOldCustomerId(rs.getString("grandTotalOldCustomerId"));
            	avo.setPlatformCustomerCount(rs.getInt("platformCustomerCount"));
            	//考勤
            	avo.setSignInTime(rs.getString("signInTime"));
            	avo.setSignOutTime(rs.getString("signOutTime"));
            	
            	list.add(avo);
            }
			
            rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex){
			ex.printStackTrace();
		} finally {
			
			try {
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	/**
	 * 根据projectId查找所属数据库的房源类型的所有交易信息
	 * @param projectId
	 * @param startTime 格式 yyyy-MM-dd
	 * @param endTime 格式 yyyy-MM-dd
	 * @param oneDay 格式 yyyy-MM-dd
	 * @return
	 */
	public List<HouseTypeHouseAndOrder>  selectHouseTypeHouseAndOrderForProject(String projectId,
			String startTime, String endTime, String oneDay){
		List<HouseTypeHouseAndOrder> list = new LinkedList<>();
		DataBase dataBase = selectDataBaseFromTargetId(projectId);
		Connection conn = getMyConnection(dataBase);
		Statement stmt = null;
		
		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_housetypehouseandorder where 1=1 ";
			if(oneDay != null && !"".equals(oneDay)){
				sql += " and chDate like '%" + oneDay + "%' ";
			}else{
				if(startTime != null && !"".equals(startTime)){
					sql += " and chDate >= '" + startTime + "' ";
				}
				if(endTime != null && !"".equals(endTime)){
					sql += " and chDate <= '" + endTime + "' ";
				}
			}
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while(rs.next()){
				HouseTypeHouseAndOrder hh = new HouseTypeHouseAndOrder();
				hh.setChDate(rs.getString("chDate"));
				hh.setHouseTypeId(rs.getString("houseTypeId"));
				hh.setHouseTypeName(rs.getString("houseTypeName"));
				hh.setArea(rs.getDouble("area"));
				//订单
				hh.setEnterBuyCount(rs.getInt("enterBuyCount"));
				hh.setAgreeEnterCount(rs.getInt("agreeEnterCount"));
				hh.setRefuseEnterCount(rs.getInt("refuseEnterCount"));
				hh.setPayCount(rs.getInt("payCount"));
				hh.setWaitSignCount(rs.getInt("waitSignCount"));
				hh.setSignCount(rs.getInt("signCount"));
				hh.setRevokeOrderCount(rs.getInt("revokeOrderCount"));
				//房源
				hh.setHouseTotalCount(rs.getInt("houseTotalCount"));
				hh.setPutAwayCount(rs.getInt("putAwayCount"));
				hh.setDownAwayCount(rs.getInt("downAwayCount"));
				hh.setSallingCount(rs.getInt("sallingCount"));
				hh.setSignedCount(rs.getInt("signedCount"));
				hh.setRevokedHouseCount(rs.getInt("revokedHouseCount"));
				
				list.add(hh);
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex){
			ex.printStackTrace();
		} finally {
			
			try {
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	/**
	 * 根据projectId查找外场的数据
	 * @param projectId 
	 * @param startTime 格式 yyyy-MM-dd
	 * @param endTime 格式 yyyy-MM-dd
	 * @param oneDay 
	 * @return
	 */
	public List<OutSideProject> selectOutSideProjectForProject(String projectId,String startTime,
			String endTime, String oneDay){
		
		List<OutSideProject> list = new LinkedList<>();
		DataBase dataBase = selectDataBaseFromTargetId(projectId);
		Connection conn = getMyConnection(dataBase);
		
		Statement stmt = null;
		
		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_outsideproject where 1=1 ";
			if(oneDay != null && !"".equals(oneDay)){
				sql += " and copDate like '%" + oneDay + "%'";
			}else{
				if(startTime != null && !"".equals(startTime)){
					sql += " and copDate >= '" + startTime + "' ";
				}
				if(endTime != null && !"".equals(endTime)){
					sql += " and copDate <= '" + endTime + "' ";
				}
			}
			
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while(rs.next()){
				OutSideProject op = new OutSideProject();
				op.setCopDate(rs.getString("copDate"));
				op.setGuideCount(rs.getInt("guideCount"));
				op.setVisitedCount(rs.getInt("visitedCount"));
				op.setRecordCustomerCount(rs.getInt("recordCustomerCount"));
				op.setRecordVisitCount(rs.getInt("recordVisitCount"));
				op.setRecordNotVisitCount(rs.getInt("recordNotVisitCount"));
				op.setGuideCustomerVisitCount(rs.getInt("guideCustomerVisitCount"));
				op.setOutSideDealCount(rs.getInt("outSideDealCount"));
				op.setIntFiledToDealNum(rs.getInt("intFiledToDealNum"));
				op.setGuidedHouseNum(rs.getString("guidedHouseNum"));
				op.setVisitedHouseNum(rs.getString("visitedHouseNum"));
				op.setGuidedShopId(rs.getString("guidedShopId"));
				op.setGuidedShopAgentId(rs.getString("guidedShopAgentId"));
				op.setVisitedShopId(rs.getString("visitedShopId"));
				op.setVisitedShopAgentId(rs.getString("visitedShopAgentId"));
				op.setGuiCustomerRecordCount(rs.getInt("guiCustomerRecordCount"));
				op.setOutSideCustomerRecordCount(rs.getInt("outSideCustomerRecordCount"));
				op.setOutSideAgreeRecordCount(rs.getInt("outSideAgreeRecordCount"));
				op.setOutSideRefuseRecordCount(rs.getInt("outSideRefuseRecordCount"));
				op.setOutSideWaitSignCount(rs.getInt("outSideWaitSignCount"));
				op.setOutSideRevokeOrderCount(rs.getInt("outSideRevokeOrderCount"));
				op.setOutSideSubscribeHouseCount(rs.getInt("outSideSubscribeHouseCount"));
				op.setOutSideSubscribeMoney(rs.getDouble("outSideSubscribeMoney"));
				op.setOutSideSubscribeGetMoney(rs.getDouble("outSideSubscribeGetMoney"));
				op.setOutSideSubscribeLockHouseMoney(rs.getDouble("outSideSubscribeLockHouseMoney"));
				op.setOutSideGiveUpHouseMoney(rs.getDouble("outSideGiveUpHouseMoney"));
				op.setOutSideWaitSignHouseMoney(rs.getDouble("outSideWaitSignHouseMoney"));
				op.setOutSideSignHouseMoney(rs.getDouble("outSideSignHouseMoney"));
				op.setOutSideRecordCuCount(rs.getInt("outSideRecordCuCount"));
				op.setOutSideNewCuRecordCount(rs.getInt("outSideNewCuRecordCount"));
				op.setOutSideOldCuRecordCount(rs.getInt("outSideOldCuRecordCount"));
				op.setOutSideSignCuCount(rs.getInt("outSideSignCuCount"));
				op.setOutSideNewCuSignCount(rs.getInt("outSideNewCuSignCount"));
				op.setOutSideOldSignCount(rs.getInt("outSideOldSignCount"));
				list.add(op);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex){
			ex.printStackTrace();
		} finally {
			
			try {
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	/**
	 * 数据归集结果生成日志
	 * @param cr
	 */
	public void addCollectionLog(CollectionRecord cr){
		Connection conn = BaseDao.getDefaultDataBase();

		Statement stmt = null;

		try {
			conn.setAutoCommit(false);
			stmt = (Statement) conn.createStatement();
			String sql = "insert into t_collectionrecord(id,auto,userId,time,success,elapsedTime) values"
					+ "(" + null + "," + cr.getAuto() + ",'" + cr.getUserId() + "','" + cr.getTime() + "','" + cr.getSuccess() + "','" + cr.getElapsedTime() + "')";
			stmt.executeUpdate(sql);
			conn.commit();
			
			
		}catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ex.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
