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
	 * ͼ��---�ӷ�
	 * @param projectId
	 * @param startTime	yyyy-MM-dd
	 * @param endTime	yyyy-MM-dd
	 * @param oneDay
	 * @return
	 */
	List<DataAnalysis> findVisitListData(String projectId, String startTime, String endTime, String oneDay);
	
	
	/**
	 * ͼ�� ---����
	 * @param projectId
	 * @param startTime	yyyy-MM-dd
	 * @param endTime	yyyy-MM-dd
	 * @param oneDay
	 * @return
	 */
	Map<String, Object> findMemoryCustomerData(String projectId, String startTime, String endTime, String oneDay);
	
	/**
	 * ���� ---����
	 * @param projectId
	 * @param startTime	yyyy-MM-dd
	 * @param endTime	yyyy-MM-dd
	 * @param oneDay
	 * @return
	 */
	List<Map<String,Object>> findVisitForTables(String projectId, String startTime, String endTime, String oneDay);
	
	/**
	 * ���� ---�ɽ��봢��
	 * @param projectId
	 * @param startTime	yyyy-MM-dd
	 * @param endTime	yyyy-MM-dd
	 * @param oneDay
	 * @return
	 */
	List<Map<String, Object>> findDealForTables(String projectId, String startTime, String endTime, String oneDay);
	
	/**
	 * ���� ---�ⳡ
	 * @param projectId
	 * @param startTime	yyyy-MM-dd
	 * @param endTime	yyyy-MM-dd
	 * @param oneDay
	 * @return
	 */
	List<Map<String, Object>> findOutsideForTables(String projectId, String startTime, String endTime, String oneDay);
	
	/**
	 * ��� -- �ӷ���ϸ
	 * @param projectId
	 * @param startTime	yyyy-MM-dd
	 * @param endTime	yyyy-MM-dd
	 * @param oneDay
	 * @return
	 */
	Map<String, Object> findVisitDataForLabel(String projectId, String startTime, String endTime, String oneDay);
	
 	/**
 	 * ��� -- ������ϸ
 	 * @param projectId
 	 * @param startTime	yyyy-MM-dd
 	 * @param endTime	yyyy-MM-dd
 	 * @param oneDay
 	 * @return
 	 */
 	Map<String, Object> findMomeryCustomerDataForLabel(String projectId, String startTime, String endTime, String oneDay);
 	
 	
  	/**
  	 * ��ȡ�ۼƳɽ������ݣ�������ʱ��䶯
  	 * @param projectId
  	 * @return
  	 */
  	Map<String, Object> findDealDataForLabels(String projectId);
  	
  	/**
  	 * ��ȡ�ۼƵĳɽ��ͻ�����,����ʱ��䶯����ȡ���µ�
  	 * @param projectId
  	 * @return
  	 */
  	Map<String,Object> findHouseBuyDataforTables(String projectId);
  	
  	/**
  	 * ��ȡ��ҵ���ʽӷ�����
  	 * @param projectId	
  	 * @param startTime	yyyy-MM-dd
  	 * @param endTime	yyyy-MM-dd
  	 * @param oneDay
  	 * @return
  	 */
  	List<Map<String, Object>> findVisitTopAndData(String projectId, String startTime, String endTime, String oneDay);
  	
  	/**
  	 * ��ȡ��ҵ���ʴ�������
  	 * @param projectId
  	 * @param startTime	yyyy-MM-dd
  	 * @param endTime	yyyy-MM-dd
  	 * @param oneDay
  	 * @return
  	 */
  	List<Map<String, Object>> findMemoryCuTop(String projectId, String startTime, String endTime, String oneDay);
  	
  	/**
  	 * ��ȡ��ҵ���ʵĳɽ�����
  	 * @param projectId
  	 * @param startTime	yyyy-MM-dd
  	 * @param endTime	yyyy-MM-dd
  	 * @param oneDay
  	 * @return
  	 */
  	List<Map<String, Object>> findDealTopAndData(String projectId, String startTime, String endTime, String oneDay);
  	List<Map<String, Object>> findReadyToDealTopAndData(String projectId, String startTime, String endTime, String oneDay);
  	
  	/**
  	 * �Ŷ� -- ��ҵ���ʵ���Ϣ
  	 * @param projectId
  	 * @return
  	 */
  	List<Map<String, Object>> findAgentInfo(String projectId);
  	
  	/**
  	 * ��Ŀ�ܱ�
  	 * @param projectId
  	 * @param startTime	yyyy-MM-dd
  	 * @param endTime	yyyy-MM-dd
  	 * @return
  	 */
  	ReportResult findProjectWeekReport(String projectId, String startTime, String endTime);
  	
  	/**
  	 * ��Ŀ���ȡ����ꡢ�걨
  	 * @param projectId
  	 * @param startTime yyyy-MM-dd
  	 * @param endTime	yyyy-MM-dd
  	 * @return
  	 */
  	ReportResult findProjectYearReport(String projectId, String startTime, String endTime);
  	
}
