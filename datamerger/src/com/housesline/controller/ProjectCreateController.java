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
	 * 为项目创建数据库和数据表
	 * 
	 * @param projectId
	 */
	@ResponseBody
	@RequestMapping(value = "/create/database/auto/{projectId}", method = RequestMethod.GET)
	public DataResult<Object> createDataBaseAndTableForProjectByAuto(@PathVariable("projectId") String projectId) {
		// 如果是自动创建则userId为空
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
				DataResult<Object> rs = new DataResult<Object>(true, (Object)"字段添加成功");
				return rs;
			}else{
				DataResult<Object> rs = new DataResult<Object>(false, "字段添加失败");
				return rs;
			}
		} catch (Exception e) {
			e.printStackTrace();
			DataResult<Object> rs = new DataResult<Object>(false, "字段添加失败--异常");
			return rs;
		}
		
	}

	/**
	 * 手动归集数据--时间段
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
			// 耗时
			new String();
			String elTime = String.valueOf(endDate.getTime() - startDate.getTime());
			CollectionRecord cr = new CollectionRecord(CollectionRecord.HAND, userId, DateUtil.format(new Date()),
					"手动归集成功", elTime);
			projectCreateService.createLogForCollection(cr);
			DataResult<Object> rs = new DataResult<>(true, (Object) "请求成功");
			return rs;
		} else {

			Date endDate = new Date();
			// 耗时
			new String();
			String elTime = String.valueOf(endDate.getTime() - startDate.getTime());
			CollectionRecord cr = new CollectionRecord(CollectionRecord.HAND, userId, DateUtil.format(new Date()),
					"手动归集失败", elTime);
			projectCreateService.createLogForCollection(cr);
			DataResult<Object> rs = new DataResult<>(false, "请求失败");
			return rs;
		}
	}

	/**
	 * 手动归集数据--某一天
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
			DataResult<Object> rs = new DataResult<Object>(true, "请求成功");
			return rs;
		} else {
			DataResult<Object> rs = new DataResult<>(false, "请求失败");
			return rs;
		}
	}

	/**
	 * 临时方法
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
