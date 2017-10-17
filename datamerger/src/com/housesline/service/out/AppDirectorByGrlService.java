package com.housesline.service.out;

import java.util.Map;

public interface AppDirectorByGrlService {

	/**
	 * ��ȡ��ϸ�ӷ�����(1.1)
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map findToDayDetailedReceiveDataByTime(String proId, String startDate, String endDate);

	/**
	 * ��ȡ��ϸ�ɽ�����(1.2.2)
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map findDealDataByTime(String proId, String startDate, String endDate);

	/**
	 * ����״̬����
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @param agentId 
	 * @return
	 */
	Map findAgentStatusDataById(String proId, String startDate, String endDate, String agentId);

}
