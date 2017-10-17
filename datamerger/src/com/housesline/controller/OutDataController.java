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
 * �鼯���ݷ���api
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
		DataResult<Object> rs = new DataResult<Object>(true, (Object)"���ʳɹ�");
		return rs;
	}
	
	
	/**
	 * ��ȡ���е���Ŀ
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
	 * ��ȡͼ��--�ӷ���״ͼ������ -- ʱ���
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
			// �������ʱ���ǵ��죬��ô��ʵʱ�鼯���������
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
	 * ��ȡͼ��--�ӷ���״ͼ������ -- ĳһ��
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
			// ���ʱ���ǵ���
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
	 * ��ȡͼ��--�������� -- ʱ���
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
			// ���ʱ���ǵ��죬�ȹ鼯���������
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
	 * ��ȡͼ��--�������� -- ĳһ��
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
	 * ���� -- ���� --ʱ���
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
	 * ���� -- ���� -- ĳһ��
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
	 * ���� -- �ɽ��봢�� -- ʱ���
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
	 * ���� -- �ɽ��봢�� -- ĳһ��
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
	 * ���� -- �ⳡ --ʱ���
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
	 * ���� -- �ⳡ -- ĳһ��
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
	 * ���ݷ��� - ��� --�ӷ� -- ʱ���
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
	 * ���ݷ��� -- ��� -- �ӷ� --ĳһ��
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
	 * ���ݷ��� -- ��� -- ���� --ʱ���
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
	 * ���ݷ��� -- ��� -- ���� -- ĳһ��
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
	 * ��ȡ�ۼƳɽ����� ����������
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
	 * ��ȡ�ɽ��ͻ�����(�ۼ�) ����������
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
	 * �Ŷ� -- ��ҵ���ʽӷ����� -- ʱ���
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
	 * �Ŷ� -- ��ҵ���ʽӷ����� -- ĳһ��
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
	 * �Ŷ� -- ��ҵ���ʴ������� -- ʱ��� -- ע�⣬��������������
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
	 * �Ŷ�---��ҵ���ʴ������� -- ĳһ��
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
	 * �Ŷ�---��ҵ���ʳɽ����� --ʱ��� -- ע�⣬����������
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
	 * �Ŷ� -- ��ҵ���ʳɽ����� -- ĳһ��
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
	 * �Ŷӣ��Ŷӹ���
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
	 * ��Ŀ�ܱ�����
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
	 * ���ȱ������걨���걨����
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
