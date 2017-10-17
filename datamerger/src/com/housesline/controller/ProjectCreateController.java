package com.housesline.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.housesline.bean.CollectionRecord;
import com.housesline.bean.DataBase;
import com.housesline.bean.Handle;
import com.housesline.service.project.ProjectCreateService;
import com.housesline.utils.DataResult;
import com.housesline.utils.DateUtil;

@Controller
public class ProjectCreateController {

	@Resource(name = "projectCreateService")
	private ProjectCreateService projectCreateService;

	/**
	 * Ϊ��Ŀ�������ݿ�����ݱ�
	 * 
	 * @param projectId
	 */
	@ResponseBody
	@RequestMapping(value = "/create/database/auto/{projectId}", method = RequestMethod.GET)
	public DataResult<Object> createDataBaseAndTableForProjectByAuto(@PathVariable("projectId") String projectId) {
		// ������Զ�������userIdΪ��
		DataBase dataBase = projectCreateService.createDataBaseForTarget(projectId, Handle.HANDLE_AUTO, "");
		try {
			projectCreateService.createDataTableForTarget(dataBase, projectId, Handle.HANDLE_AUTO, "");
			String result = "success";

			DataResult<Object> rs = new DataResult<Object>(true, (Object) result);

			return rs;
		} catch (Exception e) {
			DataResult<Object> rs = new DataResult<>(false, "error");
			return rs;
		}

	}

	@ResponseBody
	@RequestMapping(value = "/create/all", method = RequestMethod.GET)
	public DataResult<Object> createDateBaseForAllProject() {
		List<String> list = projectCreateService.findAllProjectId();
		try {
			for (String pid : list) {
				createDataBaseAndTableForProjectByAuto(pid);
			}
			String result = "success";

			DataResult<Object> rs = new DataResult<Object>(true, (Object) result);

			return rs;
		} catch (Exception e) {
			DataResult<Object> rs = new DataResult<>(false, "error");
			return rs;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/add/field/{tableName}/{field}/{type}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<Object> addField(@PathVariable("tableName") String tableName, @PathVariable("field") String field,
			@PathVariable("type") String type) {
		List<String> list = projectCreateService.findAllProjectId();
		Integer flag = 0;
		try {
			for (String pid : list) {
				flag = projectCreateService.addFieldToTable(tableName, field, type, pid);
			}
			if(flag > 0){
				DataResult<Object> rs = new DataResult<Object>(true, (Object)"�ֶ���ӳɹ�");
				return rs;
			}else{
				DataResult<Object> rs = new DataResult<Object>(false, "�ֶ����ʧ��");
				return rs;
			}
		} catch (Exception e) {
			e.printStackTrace();
			DataResult<Object> rs = new DataResult<Object>(false, "�ֶ����ʧ��--�쳣");
			return rs;
		}
		
	}

	/**
	 * �ֶ��鼯����--ʱ���
	 * 
	 * @param projectId
	 * @param startTime
	 * @param endTime
	 * @param oneDay
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/analysis/{projectId}/{userId}/{startTime}/{endTime}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<Object> saveAnalysisOfDataByProjectIdAndTime(@PathVariable("projectId") String projectId,
			@PathVariable("startTime") String startTime, @PathVariable("endTime") String endTime,
			@PathVariable("userId") String userId) {
		Integer flag = 0;
		Date startDate = new Date();
		Integer count1 = projectCreateService.insertAnalysisOfDataByProjectIdAndTime(projectId, startTime, endTime,
				null, userId);
		if (count1 > 0) {
			Integer count2 = projectCreateService.saveOrUpdateOrderData(projectId, startTime, endTime, null, userId);
			if (count2 > 0) {
				Integer count3 = projectCreateService.saveOrUpdateMeneryCustomerData(projectId, startTime, endTime,
						null, userId);
				if (count3 > 0) {
					Integer count4 = projectCreateService.saveSignDataForProjectAgent(projectId, startTime, endTime,
							null, userId);
					if (count4 > 0) {
						Integer count5 = projectCreateService.saveHouseTypeHouseAndOrderByOrder(projectId, startTime,
								endTime, null, userId);
						if (count5 > 0) {
							Integer count6 = projectCreateService.saveOutSideProject(projectId, startTime, endTime,
									null, userId);
							if (count6 > 0) {

								flag++;
							}
						}
					}
				}
			}
		}
		if (flag > 0) {
			Date endDate = new Date();
			// ��ʱ
			new String();
			String elTime = String.valueOf(endDate.getTime() - startDate.getTime());
			CollectionRecord cr = new CollectionRecord(CollectionRecord.HAND, userId, DateUtil.format(new Date()),
					"�ֶ��鼯�ɹ�", elTime);
			projectCreateService.createLogForCollection(cr);
			DataResult<Object> rs = new DataResult<>(true, (Object) "����ɹ�");
			return rs;
		} else {

			Date endDate = new Date();
			// ��ʱ
			new String();
			String elTime = String.valueOf(endDate.getTime() - startDate.getTime());
			CollectionRecord cr = new CollectionRecord(CollectionRecord.HAND, userId, DateUtil.format(new Date()),
					"�ֶ��鼯ʧ��", elTime);
			projectCreateService.createLogForCollection(cr);
			DataResult<Object> rs = new DataResult<>(false, "����ʧ��");
			return rs;
		}
	}

	/**
	 * �ֶ��鼯����--ĳһ��
	 * 
	 * @param projectId
	 * @param startTime
	 * @param endTime
	 * @param oneDay
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/analysisDay/{projectId}/{oneDay}/", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<Object> saveAnalysisOfDataByProjectIdByOneDay(@PathVariable("projectId") String projectId,
			@PathVariable("oneDay") String oneDay) {
		Integer count = projectCreateService.createDataForOneDay(projectId, oneDay);
		if (count > 0) {
			DataResult<Object> rs = new DataResult<Object>(true, "����ɹ�");
			return rs;
		} else {
			DataResult<Object> rs = new DataResult<>(false, "����ʧ��");
			return rs;
		}
	}

	/**
	 * ��ʱ����
	 * 
	 * @param projectId
	 * @param startTime
	 * @param endTime
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/analysis/outside/{projectId}/{userId}/{startTime}/{endTime}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<Object> saveOutSideData(@PathVariable("projectId") String projectId,
			@PathVariable("startTime") String startTime, @PathVariable("endTime") String endTime,
			@PathVariable("userId") String userId) {
		projectCreateService.saveOutSideProject(projectId, startTime, endTime, null, userId);
		DataResult<Object> rs = new DataResult<Object>(true, (Object) "success");
		return rs;
	}
}
