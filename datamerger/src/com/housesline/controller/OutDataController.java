package com.housesline.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.housesline.bean.AgentVisitOrder;
import com.housesline.bean.HouseTypeHouseAndOrder;
import com.housesline.bean.OutSideProject;
import com.housesline.bean.Project;
import com.housesline.bean.ReportResult;
import com.housesline.service.out.OutDataService;
import com.housesline.service.out.impl.dto.DataAnalysis;
import com.housesline.service.out.impl.dto.StorageCustomerData;
import com.housesline.service.project.ProjectCreateService;
import com.housesline.utils.DataResult;
import com.housesline.utils.DateUtil;

import exception.DataIsNullException;

/**
 * 归集数据返回api
 * 
 * @author cdh
 * 
 *
 */
@Controller
public class OutDataController {

	@Autowired
	private OutDataService outDataService;

	@Autowired
	private ProjectCreateService projectCreateService;

	@ResponseBody
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public DataResult<Object> home(){
		DataResult<Object> rs = new DataResult<Object>(true, (Object)"访问成功");
		return rs;
	}
	
	
	/**
	 * 获取所有的项目
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/project/all", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<Object> allProjectList() {
		List<Project> list = projectCreateService.findAllProject();
		DataResult<Object> rs = new DataResult<Object>(true, list);
		return rs;
	}

	/**
	 * 获取图表--接访柱状图的数据 -- 时间段
	 * 
	 * @param projectId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/charts/visit/{projectId}/{startTime}/{endTime}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<Object> dataForAnalysisVisit(@PathVariable("projectId") String projectId,
			@PathVariable("startTime") String startTime, @PathVariable("endTime") String endTime) {
		if (!StringUtils.isEmpty(endTime)) {
			// 如果结束时间是当天，那么先实时归集当天的数据
			/*if (thisDate.equals(endTime)) {
				projectCreateService.createDataForOneDay(projectId, endTime);
			}*/

			List<DataAnalysis> list = outDataService.findVisitListData(projectId, startTime, endTime, null);
			DataResult<Object> rs = new DataResult<Object>(true, list);
			return rs;

		} else {
			DataResult<Object> rs = new DataResult<>(false, "time is null");
			return rs;
		}
	}

	/**
	 * 获取图表--接访柱状图的数据 -- 某一天
	 * 
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/charts/visit/{projectId}/{oneDay}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<Object> dataForAnalysisVisitForOneDay(@PathVariable("projectId") String projectId,
			@PathVariable("oneDay") String oneDay) {
		if (!StringUtils.isEmpty(oneDay)) {
			// 如果时间是当天
			/*if (thisDate.equals(oneDay)) {
				projectCreateService.createDataForOneDay(projectId, oneDay);
			}*/
			List<DataAnalysis> list = outDataService.findVisitListData(projectId, null, null, oneDay);
			DataResult<Object> rs = new DataResult<Object>(true, list);
			return rs;
		} else {
			DataResult<Object> rs = new DataResult<>(false, "time is null");
			return rs;
		}
	}

	/**
	 * 获取图表--储客数据 -- 时间段
	 * 
	 * @param projectId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/charts/memory/{projectId}/{startTime}/{endTime}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<Object> memoryCustomerData(@PathVariable("projectId") String projectId,
			@PathVariable("startTime") String startTime, @PathVariable("endTime") String endTime) {
		if (!StringUtils.isEmpty(endTime)) {
			// 如果时间是当天，先归集当天的数据
			/*if (thisDate.equals(endTime)) {
				projectCreateService.createDataForOneDay(projectId, endTime);
			}*/
			Map<String, Object> result = outDataService.findMemoryCustomerData(projectId, startTime, endTime, null);
			
			DataResult<Object> rs = new DataResult<Object>(true, result);
			return rs;
		} else {
			DataResult<Object> rs = new DataResult<>(false, "time is null");
			return rs;
		}
	}

	/**
	 * 获取图表--储客数据 -- 某一天
	 * 
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/charts/memory/{projectId}/{oneDay}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<Object> memoryCustomerDataForOneDay(@PathVariable("projectId") String projectId,
			@PathVariable("oneDay") String oneDay) {
		if (!StringUtils.isEmpty(oneDay)) {
			/*if (thisDate.equals(oneDay)) {
				projectCreateService.createDataForOneDay(projectId, oneDay);
			}*/
			Map<String, Object> map = outDataService.findMemoryCustomerData(projectId, null, null, oneDay);
			DataResult<Object> rs = new DataResult<Object>(true, map);
			return rs;
		} else {
			DataResult<Object> rs = new DataResult<>(false, "time is null");
			return rs;
		}
	}

	/**
	 * 报表 -- 到访 --时间段
	 * 
	 * @param projectId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/table/visit/{projectId}/{startTime}/{endTime}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<Object> tableOfVisit(@PathVariable("projectId") String projectId,
			@PathVariable("startTime") String startTime, @PathVariable("endTime") String endTime) {
		if (!StringUtils.isEmpty(endTime)) {
			/*if (thisDate.equals(endTime)) {
				projectCreateService.createDataForOneDay(projectId, endTime);
			}*/
			List<Map<String, Object>> list = outDataService.findVisitForTables(projectId, startTime, endTime, null);
			DataResult<Object> rs = new DataResult<Object>(true, list);
			return rs;
		} else {
			DataResult<Object> rs = new DataResult<>(false, "time is null");
			return rs;
		}
	}

	/**
	 * 报表 -- 到访 -- 某一天
	 * 
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/table/visit/{projectId}/{oneDay}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<Object> tableOfVisitByOneDay(@PathVariable("projectId") String projectId,
			@PathVariable("oneDay") String oneDay) {
		if (!StringUtils.isEmpty(oneDay)) {
			/*if (thisDate.equals(oneDay)) {
				projectCreateService.createDataForOneDay(projectId, oneDay);
			}*/
			List<Map<String, Object>> list = outDataService.findVisitForTables(projectId, null, null, oneDay);
			DataResult<Object> rs = new DataResult<Object>(true, list);
			return rs;
		} else {
			DataResult<Object> rs = new DataResult<>(false, "time is null");
			return rs;
		}
	}

	/**
	 * 报表 -- 成交与储客 -- 时间段
	 * 
	 * @param projectId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/table/deal/{projectId}/{startTime}/{endTime}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<Object> dealStatementForTable(@PathVariable("projectId") String projectId,
			@PathVariable("startTime") String startTime, @PathVariable("endTime") String endTime) {
		if (!StringUtils.isEmpty(endTime)) {
			/*if (thisDate.equals(endTime)) {
				projectCreateService.createDataForOneDay(projectId, endTime);
			}*/
			List<Map<String, Object>> list = outDataService.findDealForTables(projectId, startTime, endTime, null);
			DataResult<Object> rs = new DataResult<Object>(true, list);
			return rs;
		} else {
			DataResult<Object> rs = new DataResult<>(false, "time is null");
			return rs;
		}
	}

	/**
	 * 报表 -- 成交与储客 -- 某一天
	 * 
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/table/deal/{projectId}/{oneDay}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<Object> dealStatementForTableByOneDay(@PathVariable("projectId") String projectId,
			@PathVariable("oneDay") String oneDay) {

		if (!StringUtils.isEmpty(oneDay)) {
			/*if (thisDate.equals(oneDay)) {
				projectCreateService.createDataForOneDay(projectId, oneDay);
			}*/
			List<Map<String, Object>> list = outDataService.findDealForTables(projectId, null, null, oneDay);
			DataResult<Object> rs = new DataResult<Object>(true, list);
			return rs;
		} else {
			DataResult<Object> rs = new DataResult<>(false, "time is null");
			return rs;
		}
	}

	/**
	 * 报表 -- 外场 --时间段
	 * 
	 * @param projectId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/table/out/{projectId}/{startTime}/{endTime}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<Object> OutFieldStatementForTables(@PathVariable("projectId") String projectId,
			@PathVariable("startTime") String startTime, @PathVariable("endTime") String endTime) {
		if (!StringUtils.isEmpty(endTime)) {
			/*if (thisDate.equals(endTime)) {
				projectCreateService.createDataForOneDay(projectId, endTime);
			}*/
			List<Map<String, Object>> list = outDataService.findOutsideForTables(projectId, startTime, endTime, null);
			DataResult<Object> rs = new DataResult<Object>(true, list);
			return rs;
		} else {
			DataResult<Object> rs = new DataResult<>(false, "time is null");
			return rs;
		}
	}

	/**
	 * 报表 -- 外场 -- 某一天
	 * 
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/table/out/{projectId}/{oneDay}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<Object> OutFieldStatementForTablesByOneDay(@PathVariable("projectId") String projectId,
			@PathVariable("oneDay") String oneDay) {
		if (!StringUtils.isEmpty(oneDay)) {
			/*if (thisDate.equals(oneDay)) {
				projectCreateService.createDataForOneDay(projectId, oneDay);
			}*/
			List<Map<String, Object>> list = outDataService.findOutsideForTables(projectId, null, null, oneDay);
			DataResult<Object> rs = new DataResult<Object>(true, list);
			return rs;
		} else {
			DataResult<Object> rs = new DataResult<>(false, "time is null");
			return rs;
		}
	}

	/**
	 * 数据分析 - 侧边 --接访 -- 时间段
	 * 
	 * @param projectId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/label/visit/{projectId}/{startTime}/{endTime}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<Object> visitDataForLabel(@PathVariable("projectId") String projectId,
			@PathVariable("startTime") String startTime, @PathVariable("endTime") String endTime) {
		if (!StringUtils.isEmpty(endTime)) {
			/*if (thisDate.equals(endTime)) {
				projectCreateService.createDataForOneDay(projectId, endTime);
			}*/
			Map<String, Object> map = outDataService.findVisitDataForLabel(projectId, startTime, endTime, null);
			DataResult<Object> rs = new DataResult<Object>(true, map);
			return rs;
		} else {
			DataResult<Object> rs = new DataResult<>(false, "time is null");
			return rs;
		}
	}

	/**
	 * 数据分析 -- 侧边 -- 接访 --某一天
	 * 
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/label/visit/{projectId}/{oneDay}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<Object> visitDataForLabelByOneDay(@PathVariable("projectId") String projectId,
			@PathVariable("oneDay") String oneDay) {
		if (!StringUtils.isEmpty(oneDay)) {
			/*if (thisDate.equals(oneDay)) {
				projectCreateService.createDataForOneDay(projectId, oneDay);
			}*/
			Map<String, Object> map = outDataService.findVisitDataForLabel(projectId, null, null, oneDay);
			DataResult<Object> rs = new DataResult<Object>(true, map);
			return rs;
		} else {
			DataResult<Object> rs = new DataResult<>(false, "time is null");
			return rs;
		}
	}

	/**
	 * 数据分析 -- 侧边 -- 储客 --时间段
	 * 
	 * @param projectId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/label/momery/{projectId}/{startTime}/{endTime}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<Object> momeryCustomerDataForLabel(@PathVariable("projectId") String projectId,
			@PathVariable("startTime") String startTime, @PathVariable("endTime") String endTime) {
		if (!StringUtils.isEmpty(endTime)) {
			/*if (thisDate.equals(endTime)) {
				projectCreateService.createDataForOneDay(projectId, endTime);
			}*/
			Map<String, Object> map = outDataService.findMomeryCustomerDataForLabel(projectId, startTime, endTime,
					null);
			DataResult<Object> rs = new DataResult<Object>(true, map);
			return rs;
		} else {
			DataResult<Object> rs = new DataResult<>(false, "time is null");
			return rs;
		}
	}

	/**
	 * 数据分析 -- 侧边 -- 储客 -- 某一天
	 * 
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/label/momery/{projectId}/{oneDay}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<Object> momeryCustomerDataForLabelByOneDay(@PathVariable("projectId") String projectId,
			@PathVariable("oneDay") String oneDay) {
		if (!StringUtils.isEmpty(oneDay)) {
			/*if (thisDate.equals(oneDay)) {
				projectCreateService.createDataForOneDay(projectId, oneDay);
			}*/
			Map<String, Object> map = outDataService.findMomeryCustomerDataForLabel(projectId, null, null, oneDay);
			DataResult<Object> rs = new DataResult<Object>(true, map);
			return rs;
		} else {
			DataResult<Object> rs = new DataResult<>(false, "time is null");
			return rs;
		}
	}

	/**
	 * 获取累计成交数据 不包含当天
	 * 
	 * @param projectId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/label/deals/{projectId}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<Object> dealDataForLabel(@PathVariable("projectId") String projectId) {
		Map<String, Object> map = outDataService.findDealDataForLabels(projectId);
		DataResult<Object> rs = new DataResult<Object>(true, map);
		return rs;
	}

	/**
	 * 获取成交客户分析(累计) 不包含当天
	 * 
	 * @param projectId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/charts/deals/{projectId}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<Object> houseBuyDataforTable(@PathVariable("projectId") String projectId) {
			Map<String, Object> map = outDataService.findHouseBuyDataforTables(projectId);
			DataResult<Object> rs = new DataResult<Object>(true, map);
			return rs;
	}

	/**
	 * 团队 -- 置业顾问接访排行 -- 时间段
	 * 
	 * @param projectId
	 * @param time
	 *            -- week/half_month/one_month/half_year/one_year/
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/team/visittop/{projectId}/{time}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<Object> visitTopAndData(@PathVariable("projectId") String projectId,
			@PathVariable("time") String time) {
		String startTime = DateUtil.getTimeForOrder(time);
		String endTime = DateUtil.format(DateUtil.rollDay(new Date(), -1), DateUtil.PATTERN_CLASSICAL_SIMPLE);

		List<Map<String, Object>> list = outDataService.findVisitTopAndData(projectId, startTime, endTime, null);
		DataResult<Object> rs = new DataResult<Object>(true, list);
		return rs;
	}

	/**
	 * 团队 -- 置业顾问接访排行 -- 某一天
	 * 
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/team/visittop/day/{projectId}/{oneDay}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<Object> visitTopAndDataByOneDay(@PathVariable("projectId") String projectId,
			@PathVariable("oneDay") String oneDay) {
		/*if (!StringUtils.isEmpty(oneDay) && oneDay.equals(today)) {
			projectCreateService.createDataForOneDay(projectId, today);
		}*/
		List<Map<String, Object>> list = outDataService.findVisitTopAndData(projectId, null, null, oneDay);
		DataResult<Object> rs = new DataResult<Object>(true, list);
		return rs;
	}

	/**
	 * 团队 -- 置业顾问储客排行 -- 时间段 -- 注意，不包含当天数据
	 * 
	 * @param projectId
	 * @param time
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/team/momery/{projectId}/{time}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<Object> memoryCustomerTop(@PathVariable("projectId") String projectId,
			@PathVariable("time") String time) {
		String startTime = DateUtil.getTimeForOrder(time);
		String endTime = DateUtil.format(DateUtil.rollDay(new Date(), -1), DateUtil.PATTERN_CLASSICAL_SIMPLE);

		List<Map<String, Object>> list = outDataService.findMemoryCuTop(projectId, startTime, endTime, null);
		DataResult<Object> rs = new DataResult<Object>(true, list);
		return rs;
	}

	/**
	 * 团队---置业顾问储客排行 -- 某一天
	 * 
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/team/momery/day/{projectId}/{oneDay}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<Object> memoryCustomerTopByOneDay(@PathVariable("projectId") String projectId,
			@PathVariable("oneDay") String oneDay) {
		/*if (!StringUtils.isEmpty(oneDay) && oneDay.equals(today)) {
			projectCreateService.createDataForOneDay(projectId, today);
		}*/
		List<Map<String, Object>> list = outDataService.findMemoryCuTop(projectId, null, null, oneDay);
		DataResult<Object> rs = new DataResult<Object>(true, list);
		return rs;
	}

	/**
	 * 团队---置业顾问成交排行 --时间段 -- 注意，不包含当天
	 * 
	 * @param projectId
	 * @param time
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/team/deal/{projectId}/{time}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<Object> dealTopAndData(@PathVariable("projectId") String projectId,
			@PathVariable("time") String time) {
		String startTime = DateUtil.getTimeForOrder(time);
		String endTime = DateUtil.format(DateUtil.rollDay(new Date(), -1), DateUtil.PATTERN_CLASSICAL_SIMPLE);

		List<Map<String, Object>> list = outDataService.findDealTopAndData(projectId, startTime, endTime, null);
		DataResult<Object> rs = new DataResult<Object>(true, list);
		return rs;
	}

	/**
	 * 团队 -- 置业顾问成交排行 -- 某一天
	 * 
	 * @param projectId
	 * @param oneDay
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/team/deal/day/{projectId}/{oneDay}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<Object> dealTopAndDataByOneDay(@PathVariable("projectId") String projectId,
			@PathVariable("oneDay") String oneDay) {
		/*if (!StringUtils.isEmpty(oneDay) && oneDay.equals(today)) {
			projectCreateService.createDataForOneDay(projectId, today);
		}*/
		List<Map<String, Object>> list = outDataService.findDealTopAndData(projectId, null, null, oneDay);
		DataResult<Object> rs = new DataResult<Object>(true, list);
		return rs;
	}

	/**
	 * 团队，团队管理
	 * 
	 * @param projectId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/team/agent/{projectId}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<Object> AgentImgData(@PathVariable("projectId") String projectId) {
		List<Map<String, Object>> list = outDataService.findAgentInfo(projectId);
		DataResult<Object> rs = new DataResult<Object>(true, list);
		return rs;
	}

	/**
	 * 项目周报数据
	 * 
	 * @param projectId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/report/week/{projectId}/{startTime}/{endTime}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<ReportResult> theReportByWeek(@PathVariable("projectId") String projectId,
			@PathVariable("startTime") String startTime, @PathVariable("endTime") String endTime) {
		ReportResult result = outDataService.findProjectWeekReport(projectId, startTime, endTime);
		DataResult<ReportResult> rs = new DataResult<>(true, result);
		return rs;
	}

	/**
	 * 季度报、半年报、年报数据
	 * 
	 * @param projectId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/report/quarter/{projectId}/{startTime}/{endTime}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public DataResult<ReportResult> theReportByQuarterOrYear(@PathVariable("projectId") String projectId,
			@PathVariable("startTime") String startTime, @PathVariable("endTime") String endTime) {
		ReportResult result = outDataService.findProjectYearReport(projectId, startTime, endTime);
		DataResult<ReportResult> rs = new DataResult<ReportResult>(true, result);
		return rs;
	}

}
