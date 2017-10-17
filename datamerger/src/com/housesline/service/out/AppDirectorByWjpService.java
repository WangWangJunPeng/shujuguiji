package com.housesline.service.out;

import java.util.List;
import java.util.Map;

import com.housesline.bean.AgentVisitOrder;

public interface AppDirectorByWjpService {

	/**
	 * ҵ�񱨱�_�ӷ�_______
	 * @param projectId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	Map<String,Object> findVisitForTablesInVisit(String proId, String startDate, String endDate);

	/**
	 * ҵ�񱨱�_�ɽ�________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findDealForTablesInDeal(String proId, String startDate, String endDate);
	
	
	/**
	 * ҵ�񱨱�_�ⳡ ________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findOutSideInfoForTablesInDeal(String proId, String startDate, String endDate);
	

	/**
	 * �ӷ�����_______________________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<AgentVisitOrder> findVisitRankingInfo(String proId, String startDate, String endDate);
	
	/**
	 * �۲�ͻ��Ӵ���Ч������ʾ___________���ݹ鼯________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findReceptionByManagerOutSide(String proId, String startDate, String endDate);

	/**
	 * �۲��׷�___________���ݹ鼯_______________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findReceptionByCustomerFirstVisitOutSide(String proId, String startDate, String endDate);

	/**
	 * �۲����Ͽͻ�ͨ��____________________���ݹ鼯_____________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findNewAndOldCustomerPassagewayInfoOutSide(String proId, String startDate, String endDate);

	/**
	 * �۲�zhiding�ӷ�____________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findSeeAppointCustomerReceptionInfoOutSide(String proId, String startDate, String endDate);

	/**
	 * �۲����_____________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findSeeReplaceCustomerReceptionInfoOutSide(String proId, String startDate, String endDate);

	/**
	 * �۲�ӷ�ʱ��___________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findSeeCustomerReceptionTimeInfoOutSide(String proId, String startDate, String endDate);

	/**
	 * �۲��Ϲ���ֵ________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findSeeCustomerContractRecordsInfoOutSide(String proId, String startDate, String endDate);

	/**
	 * �۲�ͻ�ǩԼ___________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findSeeCustomerHaveDealInfoOutSide(String proId, String startDate, String endDate);

	/**
	 * ǩԼ��ֵ����_________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findSeeCustomerHaveDealMoneyInfoOutSide(String proId, String startDate, String endDate);

	/**
	 * �ɽ�������_______________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findSeeCustomerHaveDealNumberInfoOutSide(String proId, String startDate, String endDate);

	/**
	 * �ɽ�������_______________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findSeeSaleScheduleInfoOutSide(String proId, String startDate, String endDate);

	/**
	 * �ɽ�������_______________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findSeeCustomerGuideRecprdsToVisitInfoOutSide(String proId, String startDate, String endDate);

	/**
	 * �ɽ�������_______________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findSeeOutCustomerDealInfoOutSide(String proId, String startDate, String endDate);

	/**
	 * �۲��Ͽͻ��ӷ�__________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findSeeOldCustomerVisitInfoOutSide(String proId, String startDate, String endDate);

	/**
	 * �۲촢�ͷ���___________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findSeeReserveCustomerInfoOutSide(String proId, String startDate, String endDate);

	/**
	 * �۲�ͻ��Ϲ�_______________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String,Object> findSeeAllCustomerContractRecordInfoOutSide(String proId, String startDate, String endDate);
	
}
