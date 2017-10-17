package com.housesline.service.project;

import java.util.List;

import com.housesline.bean.AgentVisitOrder;
import com.housesline.bean.CollectionRecord;
import com.housesline.bean.DataBase;
import com.housesline.bean.HouseTypeHouseAndOrder;
import com.housesline.bean.OutSideProject;
import com.housesline.bean.Project;

/**
 * project数据归集服务层
 * @author cdh 2017-07-28
 *
 */
/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public interface ProjectCreateService {

	
	public List<String> findAllProjectId();
	
	/**
	 * create dataBase by taegetId
	 * @param targetId
	 * @param handleState 0: auto 1:hand
	 * @param userId
	 */
	DataBase createDataBaseForTarget(String targetId, Integer handleState, String userId);
	
	/**
	 * create datatable by target
	 * @param dataBase
	 * @param targetId
	 * @param handleState 0: auto 1:hand
	 * @param userId
	 */
	void createDataTableForTarget(DataBase dataBase, String targetId, Integer handleState, String userId);
	
	
	
	/**
	 * 统计时间段或者当天的"接访图表"的数据 --自动
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	Integer insertAnalysisOfDataByProjectIdAndTime(String projectId, String oneDay);
	
	
	/**
	 * 获取当天的完整的对象
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	List<AgentVisitOrder> getAnalysisOfData(String projectId, String oneDay);
	/**
	 * 获取当天接访数据
	 * 如果选择的时间为当天，那么就走归集方法获取数据，不进行数据的添加
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	List<AgentVisitOrder> selectAnalysisOfDataByProjectIdAndDay(String projectId, String oneDay);
	/**
	 * 统计时间段或者当天的"接访图表"的数据 -- 手动
	 * @param projectId
	 * @param startTime 格式 ： yyyy-MM-dd
	 * @param endTime	格式 ： yyyy-MM-dd
	 * @param oneDay	格式 ： yyyy-MM-dd
	 * @return
	 */
	Integer insertAnalysisOfDataByProjectIdAndTime(String projectId, String startTime, String endTime, String oneDay, String userId);
	
	
	/**
	 * 统计置业顾问的“订单”数据 -- 自动
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	Integer saveOrUpdateOrderData(String projectId, String oneDay);
	
	/**
	 * 获取当天订单数据
	 * 
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	List<AgentVisitOrder> selectOrderData(String projectId, String oneDay);
	
	/**
	 * 统计置业顾问的“订单”数据 -- 手动
	 * @param projectId
	 * @param startTime
	 * @param endTime
	 * @param oneDay
	 * @param userId
	 * @return
	 */
	Integer saveOrUpdateOrderData(String projectId, String startTime, String endTime, String oneDay, String userId);
	
	
	/**
	 * 统计置业顾问的“储客”数据 -- 自动
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	Integer saveOrUpdateMeneryCustomerData(String projectId, String oneDay);
	
	/**
	 * 获取当天储客 数据
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	List<AgentVisitOrder> selectMeneryCustomerData(String projectId, String oneDay);
	/**
	 * 统计置业顾问的“储客”数据 -- 手动
	 * @param projectId
	 * @param startTime
	 * @param endTime
	 * @param oneDay
	 * @param userId
	 * @return
	 */
	Integer saveOrUpdateMeneryCustomerData(String projectId, String startTime, String endTime, String oneDay, String userId);
	
	
	
	
	/**
	 * 统计职业顾问的“签到”数据 -- 自动
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	Integer saveSignDataForProjectAgent(String projectId, String oneDay);
	
	
	/**
	 * 统计职业顾问的“签到”数据 -- 手动
	 * @param projectId
	 * @param startTime
	 * @param endTime
	 * @param oneDay
	 * @param userId
	 * @return
	 */
	Integer saveSignDataForProjectAgent(String projectId, String startTime, String endTime, String oneDay, String userId);
	
	
	
	/**
	 * 归集户型房源“订单” -- 自动
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	Integer saveHouseTypeHouseAndOrderByOrder(String projectId, String oneDay);
	
	
	/**
	 * 获取当天房源订单的数据
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	HouseTypeHouseAndOrder selectHouseTypeHouseAndOrderByOrder(String projectId, String oneDay);
	/**
	 * 归集户型房源“订单” -- 手动
	 * @param projectId
	 * @param startTime
	 * @param endTime
	 * @param oneDay
	 * @param userId
	 * @return
	 */
	Integer saveHouseTypeHouseAndOrderByOrder(String projectId, String startTime, String endTime, String oneDay, String userId);
	
	
	
	/**
	 * 归集外场 -- 自动
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	Integer saveOutSideProject(String projectId, String oneDay);
	
	/**
	 * 获取当天的外场数据
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	OutSideProject selectOutSideProject(String projectId, String oneDay);
	
	/**
	 * 归集外场 -- 手动
	 * @param projectId
	 * @param startTime
	 * @param endTime
	 * @param oneDay
	 * @param userId
	 * @return
	 */
	Integer saveOutSideProject(String projectId, String startTime, String endTime, String oneDay, String userId);
	
	
	/**
	 * 查找所有的项目
	 * @return
	 */
	List<Project> findAllProject();
	
	/**
	 * 根据id查找project
	 * @param projectId
	 * @return
	 */
	List<Project> findProjectById(String projectId);
	
	/**
	 * 如果查找当天的数据，那么要使用该方法进行实时归集当天的数据
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	public Integer createDataForOneDay(String projectId, String oneDay);
	
	/**
	 * 归集数据库成功的日志
	 * @param cr
	 */
	void createLogForCollection(CollectionRecord cr);
	
	/**
	 * 给指定数据表添加字段
	 * @param tableName 表名
	 * @param field	字段名
	 * @param type	字段类型
	 * @param projectId
	 * @return
	 */
	Integer addFieldToTable(String tableName, String field, String type, String projectId);
	
	/**
	 * 置业顾问接访排行--今天
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	List<AgentVisitOrder> getAgentVisitTopData(String projectId, String oneDay);
	

}
