package com.housesline.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.housesline.bean.RankingDto;
import com.housesline.service.out.AppDirectorByGrlService;
import com.housesline.service.out.AppDirectorByWjpService;
import com.housesline.service.out.OutDataService;
import com.housesline.service.project.ProjectCreateService;
import net.sf.json.JSONArray;

@Controller
public class AppDirectorByWjpController {
	@Autowired
	private OutDataService outDataService;
	
	@Autowired
	private AppDirectorByWjpService appDirectorByWjpService;
	
	@Autowired
	private ProjectCreateService projectCreateService;
	
	
	/**
	 * ����___________________�ӷ�________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_getAllVisitRecoredsInfo_byOutSide")
	public Object getToDayDetailedReceiveData(String proId,String startDate,String endDate){
		Map<String, Object> map = appDirectorByWjpService.findVisitForTablesInVisit(proId, startDate, endDate);
		//String mapStr = JSON.toJSONString(map);
		return map;
	}

	
	/**
	 * ����_________________�ɽ�_________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_getAllDealRecoredsInfo_byOutSide")
	public Object getToDayDetailedDealData(String proId,String startDate,String endDate){
		Map<String, Object> map = appDirectorByWjpService.findDealForTablesInDeal(proId, startDate, endDate);
//		String mapStr = JSON.toJSONString(map);
		return map;
	}

	/**
	 * ����_________________�ⳡ____________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_getAllOutFieldRecoredsInfo_byOutSide")
	public Object getToDayDetailedOutFieldData(String proId,String startDate,String endDate){
		Map<String, Object> map = appDirectorByWjpService.findOutSideInfoForTablesInDeal(proId, startDate, endDate);
//		String mapStr = JSON.toJSONString(map);
		return map;
	}
	
	
	/**
	 * �ӷ�����__________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_getAllVisitRecoredsRankingInfo_byOutSide")
	public Object getToDayVisitRecoredsRankingData(String proId,String startDate,String endDate){
		List<RankingDto> rankinglist = new ArrayList<>();
		List<Map<String, Object>> list = outDataService.findVisitTopAndData(proId, startDate, endDate, null);
		for (Map map : list) {
			RankingDto dto = new RankingDto();
			dto.setUserId((String)map.get("userId"));
			dto.setUserName((String)map.get("userName"));
			dto.setPhoto((String)map.get("photo"));
			dto.setVisitNum((Integer)map.get("visitCount"));
			rankinglist.add(dto);
		}
		return rankinglist;
	}

	/**
	 * �ɽ�����___________________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_getAllDealRecoredsRankingInfo_byOutSide")
	public Object getToDayDealRecoredsRankingData(String proId,String startDate,String endDate){
		List<RankingDto> rankinglist = new ArrayList<>();
		List<Map<String, Object>> list = outDataService.findDealTopAndData(proId, startDate, endDate, null);
		for (Map map : list) {
			RankingDto dto = new RankingDto();
			dto.setUserId((String)map.get("userId"));
			dto.setUserName((String)map.get("userName"));
			dto.setPhoto((String)map.get("photo"));
			dto.setContractNum((Integer)map.get("signCount"));
			rankinglist.add(dto);
		}
		return rankinglist;
	}

	
	/**
	 * �Ϲ�����___________________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_getAllReadyDealRecoredsRankingInfo_byOutSide")
	public Object getToDayReadyDealRecoredsRankingData(String proId,String startDate,String endDate){
		List<RankingDto> rankinglist = new ArrayList<>();
		List<Map<String, Object>> list = outDataService.findReadyToDealTopAndData(proId, startDate, endDate, null);
		for (Map map : list) {
			RankingDto dto = new RankingDto();
			dto.setUserId((String)map.get("userId"));
			dto.setUserName((String)map.get("userName"));
			dto.setPhoto((String)map.get("photo"));
			dto.setRengouNum((Integer)map.get("recordCount"));
			rankinglist.add(dto);
		}
		return rankinglist;
	}

	/**
	 * �۲�ͻ��Ӵ���Ч������ʾ___________________________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_getSeeCustomerReceptionInfo_byOutSide")
	public Object getSeeCustomerReceptionInfo(String proId,String startDate,String endDate){
		Map<String, Object> map = appDirectorByWjpService.findReceptionByManagerOutSide(proId, startDate, endDate);
//		String mapStr = JSON.toJSONString(map);
		return map;
	}
	
	/**
	 * �۲��׷���Ч��______________________________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_getSeeCustomerFirstVisitInfo_byOutSide")
	public Object getSeeCustomerReceptionInfoByOutSide(String proId,String startDate,String endDate){
		Map<String, Object> map = appDirectorByWjpService.findReceptionByCustomerFirstVisitOutSide(proId, startDate, endDate);
//		String mapStr = JSON.toJSONString(map);
		return map;
	}

	/**
	 * �۲����Ͽͻ�ͨ��_________________________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_getSeeNewAndOldCustomerPassagewayInfo_byOutSide")
	public Object getSeeNewAndOldCustomerPassagewayInfoByOutSide(String proId,String startDate,String endDate){
		Map<String, Object> map = appDirectorByWjpService.findNewAndOldCustomerPassagewayInfoOutSide(proId, startDate, endDate);
//		String mapStr = JSON.toJSONString(map);
		return map;
	}

	/**
	 * �۲�ָ���ӷ�_____________________________���ݹ鼯_________________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_SeeAppointCustomerReceptionInfo_byOutSide")
	public Object getSeeAppointCustomerReceptionInfoByOutSide(String proId,String startDate,String endDate){
		Map<String, Object> map = appDirectorByWjpService.findSeeAppointCustomerReceptionInfoOutSide(proId, startDate, endDate);
//		String mapStr = JSON.toJSONString(map);
		return map;
	}

	/**
	 * �۲���ӽӷ�_____________________________���ݹ鼯_________________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_SeeReplaceCustomerReceptionInfo_byOutSide")
	public Object getSeeReplaceCustomerReceptionInfoByOutSide(String proId,String startDate,String endDate){
		
		Map<String, Object> map = appDirectorByWjpService.findSeeReplaceCustomerReceptionInfoOutSide(proId, startDate, endDate);
//		String mapStr = JSON.toJSONString(map);
		return map;
	}

	/**
	 * �۲���ʱ��_________________________���ݹ鼯_________________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_SeeCustomerReceptionTimeInfo_byOutSide")
	public Object getSeeCustomerReceptionTimeInfoByOutSide(String proId,String startDate,String endDate){
		
		Map<String, Object> map = appDirectorByWjpService.findSeeCustomerReceptionTimeInfoOutSide(proId, startDate, endDate);
//		String mapStr = JSON.toJSONString(map);
		return map;
	}

	/**
	 * �۲��Ϲ���ֵ____________���ݹ鼯_________________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_SeeCustomerContractRecordsInfo_byOutSide")
	public Object getSeeCustomerContractRecordsInfoByOutSide(String proId,String startDate,String endDate){
		
		Map<String, Object> map = appDirectorByWjpService.findSeeCustomerContractRecordsInfoOutSide(proId, startDate, endDate);
//		String mapStr = JSON.toJSONString(map);
		return map;
	}
	
	/**
	 * �۲�ͻ�ǩԼ_____________________���ݹ鼯_________________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_SeeCustomerHaveDealInfo_byOutSide")
	public Object getSeeCustomerHaveDealInfoByOutSide(String proId,String startDate,String endDate){
		
		Map<String, Object> map = appDirectorByWjpService.findSeeCustomerHaveDealInfoOutSide(proId, startDate, endDate);
//		String mapStr = JSON.toJSONString(map);
		return map;
	}

	/**
	 *  ǩԼ��ֵ����_____________________________���ݹ鼯_________________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_SeeCustomerHaveDealMoneyInfo_byOutSide")
	public Object getSeeCustomerHaveDealMoneyInfoByOutSide(String proId,String startDate,String endDate){
		
		Map<String, Object> map = appDirectorByWjpService.findSeeCustomerHaveDealMoneyInfoOutSide(proId, startDate, endDate);
//		String mapStr = JSON.toJSONString(map);
		return map;
	}

	/**
	 * �ɽ�������_____________________________���ݹ鼯_________________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_SeeCustomerHaveDealNumberInfoByOutSide")
	public Object getSeeCustomerHaveDealNumberInfoByOutSide(String proId,String startDate,String endDate){
		
		Map<String, Object> map = appDirectorByWjpService.findSeeCustomerHaveDealNumberInfoOutSide(proId, startDate, endDate);
//		String mapStr = JSON.toJSONString(map);
		return map;
	}
	
	/**
	 * ���۽��ȹ۲�______________________________���ݹ鼯_________________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_SeeSaleScheduleInfoByOutSide")
	public Object getSeeSaleScheduleInfoByOutSide(String proId,String startDate,String endDate){
		
		Map<String, Object> map = appDirectorByWjpService.findSeeSaleScheduleInfoOutSide(proId, startDate, endDate);
//		String mapStr = JSON.toJSONString(map);
		return map;
	}

	/**
	 * �����������____________________________���ݹ鼯_________________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_SeeCustomerGuideRecprdsToVisitInfoByOutSide")
	public Object getSeeCustomerGuideRecprdsToVisitInfoByOutSide(String proId,String startDate,String endDate){
		
		Map<String, Object> map = appDirectorByWjpService.findSeeCustomerGuideRecprdsToVisitInfoOutSide(proId, startDate, endDate);
//		String mapStr = JSON.toJSONString(map);
		return map;
	}

	/**
	 * �ⳡ�ɽ�����_______________________________���ݹ鼯_________________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_SeeOutCustomerDealInfoByOutSide")
	public Object getSeeOutCustomerDealInfoByOutSide(String proId,String startDate,String endDate){
		
		Map<String, Object> map = appDirectorByWjpService.findSeeOutCustomerDealInfoOutSide(proId, startDate, endDate);
//		String mapStr = JSON.toJSONString(map);
		return map;
	}

	/**
	 * �۲��Ͽͻ��ӷ�_________________________________���ݹ鼯_________________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_SeeOldCustomerVisitInfoByOutSide")
	public Object getSeeOldCustomerVisitInfoByOutSide(String proId,String startDate,String endDate){
		
		Map<String, Object> map = appDirectorByWjpService.findSeeOldCustomerVisitInfoOutSide(proId, startDate, endDate);
//		String mapStr = JSON.toJSONString(map);
		return map;
	}

	/**
	 * �۲촢�ͷ���___________________________���ݹ鼯_________________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_SeeReserveCustomerInfoByOutSide")
	public Object getSeeReserveCustomerInfoByOutSide(String proId,String startDate,String endDate){
		
		Map<String, Object> map = appDirectorByWjpService.findSeeReserveCustomerInfoOutSide(proId, startDate, endDate);
//		String mapStr = JSON.toJSONString(map);
		return map;
	}

	/**
	 * �۲�ͻ��Ϲ�_______________________���ݹ鼯_________________________________
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/to_SeeAllCustomerContractRecordInfoByOutSide")
	public Object getSeeAllCustomerContractRecordInfoByOutSide(String proId,String startDate,String endDate){
		
		Map<String, Object> map = appDirectorByWjpService.findSeeAllCustomerContractRecordInfoOutSide(proId, startDate, endDate);
//		String mapStr = JSON.toJSONString(map);
		return map;
	}
	
	
	
}
