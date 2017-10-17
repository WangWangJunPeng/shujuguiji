package com.housesline.service.out;

import java.util.List;
import java.util.Map;

import com.housesline.bean.AgentVisitOrder;
import com.housesline.bean.HouseTypeHouseAndOrder;
import com.housesline.bean.OutSideProject;
import com.housesline.bean.ReportResult;
import com.housesline.service.out.impl.dto.DataAnalysis;

public interface OutDataService {

	List<AgentVisitOrder> findAgentVisitOrderListForProject(String projectId, String startTime, String endTime, String oneDay);
	
	List<HouseTypeHouseAndOrder>  findHouseTypeHouseAndOrderForProject(String projectId,
			String startTime, String endTime, String oneDay);
	
	List<OutSideProject> selectOutSideProjectForProject(String projectId,String startTime,
			String endTime, String oneDay);
	
	/**
	 * 图表---接访
	 * @param projectId
	 * @param startTime	yyyy-MM-dd
	 * @param endTime	yyyy-MM-dd
	 * @param oneDay
	 * @return
	 */
	List<DataAnalysis> findVisitListData(String projectId, String startTime, String endTime, String oneDay);
	
	
	/**
	 * 图表 ---储客
	 * @param projectId
	 * @param startTime	yyyy-MM-dd
	 * @param endTime	yyyy-MM-dd
	 * @param oneDay
	 * @return
	 */
	Map<String, Object> findMemoryCustomerData(String projectId, String startTime, String endTime, String oneDay);
	
	/**
	 * 报表 ---到访
	 * @param projectId
	 * @param startTime	yyyy-MM-dd
	 * @param endTime	yyyy-MM-dd
	 * @param oneDay
	 * @return
	 */
	List<Map<String,Object>> findVisitForTables(String projectId, String startTime, String endTime, String oneDay);
	
	/**
	 * 报表 ---成交与储客
	 * @param projectId
	 * @param startTime	yyyy-MM-dd
	 * @param endTime	yyyy-MM-dd
	 * @param oneDay
	 * @return
	 */
	List<Map<String, Object>> findDealForTables(String projectId, String startTime, String endTime, String oneDay);
	
	/**
	 * 报表 ---外场
	 * @param projectId
	 * @param startTime	yyyy-MM-dd
	 * @param endTime	yyyy-MM-dd
	 * @param oneDay
	 * @return
	 */
	List<Map<String, Object>> findOutsideForTables(String projectId, String startTime, String endTime, String oneDay);
	
	/**
	 * 侧边 -- 接访详细
	 * @param projectId
	 * @param startTime	yyyy-MM-dd
	 * @param endTime	yyyy-MM-dd
	 * @param oneDay
	 * @return
	 */
	Map<String, Object> findVisitDataForLabel(String projectId, String startTime, String endTime, String oneDay);
	
 	/**
 	 * 侧边 -- 储客详细
 	 * @param projectId
 	 * @param startTime	yyyy-MM-dd
 	 * @param endTime	yyyy-MM-dd
 	 * @param oneDay
 	 * @return
 	 */
 	Map<String, Object> findMomeryCustomerDataForLabel(String projectId, String startTime, String endTime, String oneDay);
 	
 	
  	/**
  	 * 获取累计成交的数据，不跟随时间变动
  	 * @param projectId
  	 * @return
  	 */
  	Map<String, Object> findDealDataForLabels(String projectId);
  	
  	/**
  	 * 获取累计的成交客户分析,不随时间变动，获取最新的
  	 * @param projectId
  	 * @return
  	 */
  	Map<String,Object> findHouseBuyDataforTables(String projectId);
  	
  	/**
  	 * 获取置业顾问接访排行
  	 * @param projectId	
  	 * @param startTime	yyyy-MM-dd
  	 * @param endTime	yyyy-MM-dd
  	 * @param oneDay
  	 * @return
  	 */
  	List<Map<String, Object>> findVisitTopAndData(String projectId, String startTime, String endTime, String oneDay);
  	
  	/**
  	 * 获取置业顾问储客排行
  	 * @param projectId
  	 * @param startTime	yyyy-MM-dd
  	 * @param endTime	yyyy-MM-dd
  	 * @param oneDay
  	 * @return
  	 */
  	List<Map<String, Object>> findMemoryCuTop(String projectId, String startTime, String endTime, String oneDay);
  	
  	/**
  	 * 获取置业顾问的成交排行
  	 * @param projectId
  	 * @param startTime	yyyy-MM-dd
  	 * @param endTime	yyyy-MM-dd
  	 * @param oneDay
  	 * @return
  	 */
  	List<Map<String, Object>> findDealTopAndData(String projectId, String startTime, String endTime, String oneDay);
  	List<Map<String, Object>> findReadyToDealTopAndData(String projectId, String startTime, String endTime, String oneDay);
  	
  	/**
  	 * 团队 -- 置业顾问的信息
  	 * @param projectId
  	 * @return
  	 */
  	List<Map<String, Object>> findAgentInfo(String projectId);
  	
  	/**
  	 * 项目周报
  	 * @param projectId
  	 * @param startTime	yyyy-MM-dd
  	 * @param endTime	yyyy-MM-dd
  	 * @return
  	 */
  	ReportResult findProjectWeekReport(String projectId, String startTime, String endTime);
  	
  	/**
  	 * 项目季度、半年、年报
  	 * @param projectId
  	 * @param startTime yyyy-MM-dd
  	 * @param endTime	yyyy-MM-dd
  	 * @return
  	 */
  	ReportResult findProjectYearReport(String projectId, String startTime, String endTime);
  	
}
