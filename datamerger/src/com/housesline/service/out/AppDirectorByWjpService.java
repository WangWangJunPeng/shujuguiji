package com.housesline.service.out;

import java.util.List;
import java.util.Map;

import com.housesline.bean.AgentVisitOrder;

public interface AppDirectorByWjpService {

	/**
	 * 业务报表_接访_______
	 * @param projectId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	Map<String,Object> findVisitForTablesInVisit(String proId, String startDate, String endDate);

	/**
	 * 业务报表_成交________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findDealForTablesInDeal(String proId, String startDate, String endDate);
	
	
	/**
	 * 业务报表_外场 ________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findOutSideInfoForTablesInDeal(String proId, String startDate, String endDate);
	

	/**
	 * 接访排名_______________________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<AgentVisitOrder> findVisitRankingInfo(String proId, String startDate, String endDate);
	
	/**
	 * 观察客户接待成效数据显示___________数据归集________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findReceptionByManagerOutSide(String proId, String startDate, String endDate);

	/**
	 * 观察首访___________数据归集_______________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findReceptionByCustomerFirstVisitOutSide(String proId, String startDate, String endDate);

	/**
	 * 观察新老客户通道____________________数据归集_____________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findNewAndOldCustomerPassagewayInfoOutSide(String proId, String startDate, String endDate);

	/**
	 * 观察zhiding接访____________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findSeeAppointCustomerReceptionInfoOutSide(String proId, String startDate, String endDate);

	/**
	 * 观察替接_____________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findSeeReplaceCustomerReceptionInfoOutSide(String proId, String startDate, String endDate);

	/**
	 * 观察接访时长___________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findSeeCustomerReceptionTimeInfoOutSide(String proId, String startDate, String endDate);

	/**
	 * 观察认购货值________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findSeeCustomerContractRecordsInfoOutSide(String proId, String startDate, String endDate);

	/**
	 * 观察客户签约___________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findSeeCustomerHaveDealInfoOutSide(String proId, String startDate, String endDate);

	/**
	 * 签约货值分析_________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findSeeCustomerHaveDealMoneyInfoOutSide(String proId, String startDate, String endDate);

	/**
	 * 成交量分析_______________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findSeeCustomerHaveDealNumberInfoOutSide(String proId, String startDate, String endDate);

	/**
	 * 成交量分析_______________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findSeeSaleScheduleInfoOutSide(String proId, String startDate, String endDate);

	/**
	 * 成交量分析_______________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findSeeCustomerGuideRecprdsToVisitInfoOutSide(String proId, String startDate, String endDate);

	/**
	 * 成交量分析_______________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findSeeOutCustomerDealInfoOutSide(String proId, String startDate, String endDate);

	/**
	 * 观察老客户接访__________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findSeeOldCustomerVisitInfoOutSide(String proId, String startDate, String endDate);

	/**
	 * 观察储客分析___________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findSeeReserveCustomerInfoOutSide(String proId, String startDate, String endDate);

	/**
	 * 观察客户认购_______________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findSeeAllCustomerContractRecordInfoOutSide(String proId, String startDate, String endDate);
	
}
