package com.housesline.service.project;

import java.util.List;

import com.housesline.bean.AgentVisitOrder;
import com.housesline.bean.CollectionRecord;
import com.housesline.bean.DataBase;
import com.housesline.bean.HouseTypeHouseAndOrder;
import com.housesline.bean.OutSideProject;
import com.housesline.bean.Project;

/**
 * project���ݹ鼯�����
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
	 * ͳ��ʱ��λ��ߵ����"�ӷ�ͼ��"������ --�Զ�
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	Integer insertAnalysisOfDataByProjectIdAndTime(String projectId, String oneDay);
	
	
	/**
	 * ��ȡ����������Ķ���
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	List<AgentVisitOrder> getAnalysisOfData(String projectId, String oneDay);
	/**
	 * ��ȡ����ӷ�����
	 * ���ѡ���ʱ��Ϊ���죬��ô���߹鼯������ȡ���ݣ����������ݵ����
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	List<AgentVisitOrder> selectAnalysisOfDataByProjectIdAndDay(String projectId, String oneDay);
	/**
	 * ͳ��ʱ��λ��ߵ����"�ӷ�ͼ��"������ -- �ֶ�
	 * @param projectId
	 * @param startTime ��ʽ �� yyyy-MM-dd
	 * @param endTime	��ʽ �� yyyy-MM-dd
	 * @param oneDay	��ʽ �� yyyy-MM-dd
	 * @return
	 */
	Integer insertAnalysisOfDataByProjectIdAndTime(String projectId, String startTime, String endTime, String oneDay, String userId);
	
	
	/**
	 * ͳ����ҵ���ʵġ����������� -- �Զ�
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	Integer saveOrUpdateOrderData(String projectId, String oneDay);
	
	/**
	 * ��ȡ���충������
	 * 
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	List<AgentVisitOrder> selectOrderData(String projectId, String oneDay);
	
	/**
	 * ͳ����ҵ���ʵġ����������� -- �ֶ�
	 * @param projectId
	 * @param startTime
	 * @param endTime
	 * @param oneDay
	 * @param userId
	 * @return
	 */
	Integer saveOrUpdateOrderData(String projectId, String startTime, String endTime, String oneDay, String userId);
	
	
	/**
	 * ͳ����ҵ���ʵġ����͡����� -- �Զ�
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	Integer saveOrUpdateMeneryCustomerData(String projectId, String oneDay);
	
	/**
	 * ��ȡ���촢�� ����
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	List<AgentVisitOrder> selectMeneryCustomerData(String projectId, String oneDay);
	/**
	 * ͳ����ҵ���ʵġ����͡����� -- �ֶ�
	 * @param projectId
	 * @param startTime
	 * @param endTime
	 * @param oneDay
	 * @param userId
	 * @return
	 */
	Integer saveOrUpdateMeneryCustomerData(String projectId, String startTime, String endTime, String oneDay, String userId);
	
	
	
	
	/**
	 * ͳ��ְҵ���ʵġ�ǩ�������� -- �Զ�
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	Integer saveSignDataForProjectAgent(String projectId, String oneDay);
	
	
	/**
	 * ͳ��ְҵ���ʵġ�ǩ�������� -- �ֶ�
	 * @param projectId
	 * @param startTime
	 * @param endTime
	 * @param oneDay
	 * @param userId
	 * @return
	 */
	Integer saveSignDataForProjectAgent(String projectId, String startTime, String endTime, String oneDay, String userId);
	
	
	
	/**
	 * �鼯���ͷ�Դ�������� -- �Զ�
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	Integer saveHouseTypeHouseAndOrderByOrder(String projectId, String oneDay);
	
	
	/**
	 * ��ȡ���췿Դ����������
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	HouseTypeHouseAndOrder selectHouseTypeHouseAndOrderByOrder(String projectId, String oneDay);
	/**
	 * �鼯���ͷ�Դ�������� -- �ֶ�
	 * @param projectId
	 * @param startTime
	 * @param endTime
	 * @param oneDay
	 * @param userId
	 * @return
	 */
	Integer saveHouseTypeHouseAndOrderByOrder(String projectId, String startTime, String endTime, String oneDay, String userId);
	
	
	
	/**
	 * �鼯�ⳡ -- �Զ�
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	Integer saveOutSideProject(String projectId, String oneDay);
	
	/**
	 * ��ȡ������ⳡ����
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	OutSideProject selectOutSideProject(String projectId, String oneDay);
	
	/**
	 * �鼯�ⳡ -- �ֶ�
	 * @param projectId
	 * @param startTime
	 * @param endTime
	 * @param oneDay
	 * @param userId
	 * @return
	 */
	Integer saveOutSideProject(String projectId, String startTime, String endTime, String oneDay, String userId);
	
	
	/**
	 * �������е���Ŀ
	 * @return
	 */
	List<Project> findAllProject();
	
	/**
	 * ����id����project
	 * @param projectId
	 * @return
	 */
	List<Project> findProjectById(String projectId);
	
	/**
	 * ������ҵ�������ݣ���ôҪʹ�ø÷�������ʵʱ�鼯���������
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	public Integer createDataForOneDay(String projectId, String oneDay);
	
	/**
	 * �鼯���ݿ�ɹ�����־
	 * @param cr
	 */
	void createLogForCollection(CollectionRecord cr);
	
	/**
	 * ��ָ�����ݱ�����ֶ�
	 * @param tableName ����
	 * @param field	�ֶ���
	 * @param type	�ֶ�����
	 * @param projectId
	 * @return
	 */
	Integer addFieldToTable(String tableName, String field, String type, String projectId);
	
	/**
	 * ��ҵ���ʽӷ�����--����
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	List<AgentVisitOrder> getAgentVisitTopData(String projectId, String oneDay);
	

}
