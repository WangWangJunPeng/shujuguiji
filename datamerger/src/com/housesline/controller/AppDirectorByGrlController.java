package com.housesline.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.housesline.service.out.AppDirectorByGrlService;

/**
 * 经理段app
 * @author grl 2017-09-05
 *
 */

@Controller
public class AppDirectorByGrlController {
	
	@Autowired
	private AppDirectorByGrlService appDirectorService;
	
	@ResponseBody
	@RequestMapping("/test")
	public String testThisPro(){
		return "hello world";
	}
	
	//获取详细接访数据(1.1)
	@ResponseBody
	@RequestMapping("/get_toDay_detail_Receive_data")
	public String getToDayDetailedReceiveData(String proId,String startDate,String endDate){
		Map map = appDirectorService.findToDayDetailedReceiveDataByTime(proId,startDate,endDate);
		String mapStr = JSON.toJSONString(map);
		return mapStr;
	}
	
	//获取详细成交数据(1.2.2)
	@ResponseBody
	@RequestMapping("/get_deal_data")
	public String getDealDataData(String proId,String startDate,String endDate){
		Map map = appDirectorService.findDealDataByTime(proId,startDate,endDate);
		String mapStr = JSON.toJSONString(map);
		return mapStr;
	}
	
	// 获取顾问状态数据(1.1.2)
	@ResponseBody
	@RequestMapping("/get_agent_status_data")
	public Object getAgentStatusData(String proId,String startDate,String endDate,String agentId){
		Map map = appDirectorService.findAgentStatusDataById(proId,startDate,endDate,agentId);
		return map;
	}
}
