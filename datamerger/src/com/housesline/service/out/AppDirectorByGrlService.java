package com.housesline.service.out;

import java.util.Map;

public interface AppDirectorByGrlService {

	/**
	 * 获取详细接访数据(1.1)
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map findToDayDetailedReceiveDataByTime(String proId, String startDate, String endDate);

	/**
	 * 获取详细成交数据(1.2.2)
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map findDealDataByTime(String proId, String startDate, String endDate);

	/**
	 * 顾问状态数据
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @param agentId 
	 * @return
	 */
	Map findAgentStatusDataById(String proId, String startDate, String endDate, String agentId);

}
