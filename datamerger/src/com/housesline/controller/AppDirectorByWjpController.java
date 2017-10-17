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
	 * 报表___________________接访________________
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
	 * 报表_________________成交_________________________
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
	 * 报表_________________外场____________________________
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
	 * 接访排名__________________________
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
	 * 成交排名___________________________________
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
	 * 认购排名___________________________________
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
	 * 观察客户接待成效数据显示___________________________________________
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
	 * 观察首访有效率______________________________________________
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
	 * 观察新老客户通道_________________________________________
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
	 * 观察指定接访_____________________________数据归集_________________________________
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
	 * 观察替接接访_____________________________数据归集_________________________________
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
	 * 观察解放时长_________________________数据归集_________________________________
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
	 * 观察认购货值____________数据归集_________________________________
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
	 * 观察客户签约_____________________数据归集_________________________________
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
	 *  签约货值分析_____________________________数据归集_________________________________
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
	 * 成交量分析_____________________________数据归集_________________________________
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
	 * 销售进度观察______________________________数据归集_________________________________
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
	 * 报备到访情况____________________________数据归集_________________________________
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
	 * 外场成交分析_______________________________数据归集_________________________________
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
	 * 观察老客户接访_________________________________数据归集_________________________________
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
	 * 观察储客分析___________________________数据归集_________________________________
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
	 * 观察客户认购_______________________数据归集_________________________________
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
