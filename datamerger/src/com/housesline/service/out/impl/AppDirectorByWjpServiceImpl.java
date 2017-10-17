package com.housesline.service.out.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.housesline.bean.AgentVisitOrder;
import com.housesline.bean.OutSideProject;
import com.housesline.bean.User;
import com.housesline.bean.UserRole;
import com.housesline.dao.BaseDao;
import com.housesline.dao.SelectDao;
import com.housesline.service.out.AppDirectorByWjpService;
import com.housesline.service.project.ProjectCreateService;
import com.housesline.utils.SysContent;



@Service(value="appDirectorByWjpService")
public class AppDirectorByWjpServiceImpl implements AppDirectorByWjpService{

	@Autowired
	private BaseDao baseDao;
	
	@Autowired
	private SelectDao selectDao;
	@Autowired
	private ProjectCreateService projectCreateService;
	
	@Override
	public Map<String, Object> findVisitForTablesInVisit(String proId, String startDate, String endDate) {
		
		Map<String, Object> map = new HashMap<>();
		//内场
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,projectCreateService);
		
		map.put("length", 29);
		// 总接访量
		map.put("name_1", "总接访数");
		map.put("name_2", "有效接访");
		map.put("name_3", "无效接访");
		map.put("name_4", "有效接访率");
		map.put("name_5", "新客户通道接访");
		map.put("name_6", "老客户通道接访");
		map.put("name_7", "新客户通道有效接访");
		map.put("name_8", "老客户通道有效接访");
		map.put("name_9", "新客户通道有效接访率");
		map.put("name_10", "老客户通道有效接访率");
		map.put("name_11", "老客户通道占比");
		map.put("name_12", "确认老客户接访次数");
		map.put("name_13", "老客户接访次数占比");
		map.put("name_14", "指定接访");
		map.put("name_15", "新客户通道指定接访");
		map.put("name_16", "老客户通道指定接访");
		map.put("name_17", "指定有效接访");
		map.put("name_18", "指定有效接访率");
		map.put("name_19", "指定接访率");
		map.put("name_20", "新客户通道指定接访率");
		map.put("name_21", "老客户通道指定接访率");
		map.put("name_22", "总替接数");
		map.put("name_23", "总替接率");
		map.put("name_24", "总接访时长");
		map.put("name_25", "新客户通道接访时长");
		map.put("name_26", "老客户通道接访时长");
		map.put("name_27", "替接接访时长");
		map.put("name_28", "每日接访平均时长");
		map.put("name_29", "每次接访平均时长");
		//_____________________________________________________
		map.put("data_1", avo.getVisitCount());
		map.put("data_2", avo.getValidVisitCount());
		map.put("data_3", avo.getUnValidVisitCount());
		map.put("data_4", avo.getValidRate());
		map.put("data_5", avo.getNewWayVisitCount());
		map.put("data_6", avo.getOldWayVisitCount());
		map.put("data_7", avo.getValidNewWayVisitCount());
		map.put("data_8", avo.getValidOldWayVisitCount());
		map.put("data_9", avo.getNewWayValidRate());
		//map.put("data_10", avo.getOldReceptRate());
		
		map.put("data_10", avo.getOldWayValidRate());
		map.put("data_11", avo.getOldWayRate());
		map.put("data_12", avo.getAffirmOldCustomerVisitNum());
		map.put("data_13", avo.getOldReceptRate());
		map.put("data_14", avo.getAppointCount());
		map.put("data_15", avo.getAppointNewWayVisitCount());
		map.put("data_16", avo.getAppointOldWayVisitCount());
		map.put("data_17", avo.getAppointValidVisitCount());
		map.put("data_18", avo.getAppointValidReceptRate());
		map.put("data_19", avo.getAppointReceptRate());
		map.put("data_20", avo.getNewWayAppointReceptRate());
		map.put("data_21", avo.getOldWayAppointReceptRate());
		map.put("data_22", avo.getReplaceCount());
		map.put("data_23", avo.getTotalReplaceReceptRate());
		map.put("data_24", avo.getTotalVisitTime());
		map.put("data_25", avo.getNewCustomerVisitTime());
		map.put("data_26", avo.getOldCustomerVisitTime());
		map.put("data_27", avo.getReplaceVisitTime());
		map.put("data_28", avo.getEveryDayAverageReplaceTimeLong());
		map.put("data_29", avo.getEveryOnceAverageReplaceTimeLong());
		
		
		return map;
	}

	@Override
	public Map<String, Object> findDealForTablesInDeal(String proId, String startDate, String endDate) {
		Map<String, Object> map = new HashMap<>();
		//外场
		OutSideProject osp = new OutSideProject();
		osp = osp.creatMoreObjTurnOne(proId,startDate,endDate,baseDao,projectCreateService);
		//内场
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,osp,projectCreateService);
		
		map.put("length", 23);

		map.put("name_1", "新增总储客数");
		// 新增二次来访客户数
		map.put("name_2", "新增二次来访客户数");
		map.put("name_3", "老客户数");
		map.put("name_4", "客户回头率");
		map.put("name_5", "总认购客户数");
		map.put("name_6", "老客户认购数");
		map.put("name_7", "新客户认购数");
		map.put("name_8", "老客户认购率");
		map.put("name_9", "新客户认购率");
		map.put("name_10", "认购套数");
		map.put("name_11", "认购金额");
		map.put("name_12", "认购到款金额");
		map.put("name_13", "认购锁定房源货值");
		map.put("name_14", "已签约数");
		map.put("name_15", "放弃签约数");
		map.put("name_16", "待签约数");
		map.put("name_17", "下定数");
		map.put("name_18", "已签约房源货值");
		map.put("name_19", "放弃签约房源货值");
		map.put("name_20", "待签约房源货值");
		map.put("name_21", "来访成交比");
		map.put("name_22", "认购签约率");
		map.put("name_23", "储客成交比");
		//_______________________________________________________
		map.put("data_1", avo.getNewAddCollCustomerCount());
		map.put("data_2", avo.getNewTwoVisitCustomerCount());
		map.put("data_3", avo.getGrandTotalOldCustomerCount());
		map.put("data_4", avo.getCustomerTurnHandRate());
		map.put("data_5", avo.getNewSubscribeCustomerCount());
		map.put("data_6", avo.getOldSubscribeCustomerCount());
		map.put("data_7", avo.getNewSubscribeCustomerCount());
		map.put("data_8", avo.getOldCusEnterBuyRate());
		map.put("data_9", avo.getNewAddCusEnterBuyRate());
		map.put("data_10", avo.getSubscribeHouseCount());
		map.put("data_11", avo.getSubscribeMoney());
		map.put("data_12", avo.getSubscribeGetMoney());
		map.put("data_13", avo.getSubscribeLockHouseMoney());
		map.put("data_14", avo.getSignCount());
		map.put("data_15", avo.getGiveUpSignCount());
		map.put("data_16", avo.getWaitSignCount());
		map.put("data_17", avo.getPayCount());
		map.put("data_18", avo.getSignHouseMoney());
		map.put("data_19", avo.getGiveUpHouseMoney());
		map.put("data_20", avo.getWaitSignHouseMoney());
		map.put("data_21", avo.getVisitDealRate());
		map.put("data_22", avo.getEnterBuySignRate());
		map.put("data_23", avo.getGrandCusDealRate());

		
		return map;
	}

	@Override
	public Map<String, Object> findOutSideInfoForTablesInDeal(String proId, String startDate, String endDate) {
		
		Map<String, Object> map = new HashMap<>();
		//外场
		OutSideProject osp = new OutSideProject();
		osp = osp.creatMoreObjTurnOne(proId,startDate,endDate,baseDao,projectCreateService);
		//内场
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,projectCreateService);
		
		map.put("length", 9);

		map.put("name_1", "报备客户数");
		map.put("name_2", "报备到访客户数");
		map.put("name_3", "备案未到访客户数");
		map.put("name_4", "报备到访率");
		map.put("name_5", "外场导客占比");
		map.put("name_6", "带客成交数");
		map.put("name_7", "外场成交数");
		map.put("name_8", "外场成交占比");
		map.put("name_9", "内场成交数");
		
		String outSideLeadCusRate = "0.00";
		map.put("data_1", osp.getRecordCustomerCount());
		map.put("data_2", osp.getRecordVisitCount());
		map.put("data_3", osp.getRecordNotVisitCount());
		map.put("data_4", osp.getGrVisiteRate());
//		map.put("data_5", avo.getOutSideLeadCusRate());
		if(avo.getGrandTotalCollCustomerCount()>0){
			double d = (double)osp.getRecordVisitCount() / (double)avo.getGrandTotalCollCustomerCount() * 100;
			outSideLeadCusRate = SysContent.get2Double(d);
		}
		map.put("data_5", outSideLeadCusRate+"%");//平台导客比 = 外场导客比
		
		map.put("data_6", osp.getGuideCustomerVisitCount());
		map.put("data_7", osp.getOutSideDealCount());
		map.put("data_8", osp.getOutSideDealRate());
		map.put("data_9", osp.getIntFiledToDealNum());
		
		
		return map;
	}

	@Override
	public List<AgentVisitOrder> findVisitRankingInfo(String proId, String startDate, String endDate) {
		
		return null;
	}

	@Override
	public Map<String, Object> findReceptionByManagerOutSide(String proId, String startDate, String endDate) {
		
		Map<String, Object> map = new HashMap<>();
		//内场
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,projectCreateService);
		
		map.put("title", "观察客户接待成效");
		map.put("name_1", "有效接访数");
		map.put("name_2", "无效接访数");
		map.put("length", 2);
		
		map.put("data_1", avo.getValidVisitCount());
		map.put("data_2", avo.getUnValidVisitCount());
		
		return map;
	}

	@Override
	public Map<String, Object> findReceptionByCustomerFirstVisitOutSide(String proId, String startDate,
			String endDate) {
		Map<String, Object> map = new HashMap<>();
		//内场
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,projectCreateService);
		
		map.put("title", "观察首访有效接访");
		map.put("name_1", "首访有效接访数");
		map.put("name_2", "首访无效接访数");
		map.put("length", 2);
		
		map.put("data_1", avo.getValidNewCustomerVisitCount());
		map.put("data_2", avo.getUnValidNewCustomerVisitCount());
		
		return map;
	}

	@Override
	public Map<String, Object> findSeeAppointCustomerReceptionInfoOutSide(String proId, String startDate,
			String endDate) {
		Map<String, Object> map = new HashMap<>();
		//内场
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,projectCreateService);
		
		map.put("title", "观察指定接访");
		map.put("name_1", "指定接访");
		map.put("name_2", "新客户通道接访");
		map.put("name_3", "老客户通道接访");
		map.put("name_4", "指定有效接访");
		// map.put("name_5", "指定接访率");
		map.put("name_5", "新客户通道指定接访");
		map.put("name_6", "老客户通道指定接访");
		// map.put("name_8", "指定无效接访率");
		map.put("length", 6);
		
		map.put("data_1", avo.getAppointCount());
		map.put("data_2", avo.getNewWayVisitCount());
		map.put("data_3", avo.getOldWayVisitCount());
		map.put("data_4", avo.getAppointValidVisitCount());
		map.put("data_5", avo.getAppointNewWayVisitCount());
		map.put("data_6", avo.getAppointOldWayVisitCount());
		
		return map;
	}

	@Override
	public Map<String, Object> findSeeReplaceCustomerReceptionInfoOutSide(String proId, String startDate,
			String endDate) {
		Map<String, Object> map = new HashMap<>();
		//内场
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,projectCreateService);
		
		map.put("title", "观察替接");
		map.put("name_1", "总替接数");
		// map.put("name_2", "总替接率");
		map.put("length", 1);
		
		map.put("data_1", avo.getReplaceCount());
		
		return map;
	}

	@Override
	public Map<String, Object> findSeeCustomerReceptionTimeInfoOutSide(String proId, String startDate, String endDate) {
		Map<String, Object> map = new HashMap<>();
		//内场
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,projectCreateService);
		
		map.put("title", "观察接访时长");
		map.put("name_1", "总接访时长");
		map.put("name_2", "新客户接访时长");
		map.put("name_3", "老客户接访时长");
		map.put("name_4", "替接接访时长");
		map.put("name_5", "每次接访平均时长");
		map.put("name_6", "每日平均接访时长");
		map.put("length", 6);
		
		map.put("data_1", avo.getTotalVisitTime());
		map.put("data_2", avo.getNewCustomerVisitTime());
		map.put("data_3", avo.getOldCustomerVisitTime());
		map.put("data_4", avo.getReplaceVisitTime());
		map.put("data_5", avo.getEveryOnceAverageReplaceTimeLong());
		map.put("data_6", avo.getEveryDayAverageReplaceTimeLong());
		
		return map;
	}

	@Override
	public Map<String, Object> findNewAndOldCustomerPassagewayInfoOutSide(String proId, String startDate,
			String endDate) {
		Map<String, Object> map = new HashMap<>();
		//内场
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,projectCreateService);
		
		map.put("title", "观察新老客户通道");
		// map.put("name_1", "新客户通道接访");
		// map.put("name_2", "老客户通道接访");
		map.put("name_1", "新客户通道有效接访");
		map.put("name_2", "老客户通道有效接访");
		map.put("name_3", "新客户通道无效接访");
		map.put("name_4", "老客户通道无效接访");
		// map.put("name_5", "新客户通道有效接访率");
		// map.put("name_6", "老客户通道有效接访率");
		// map.put("name_7", "老客户通道占比");
		map.put("length", 4);
		
		map.put("data_1", avo.getValidNewWayVisitCount());
		map.put("data_2", avo.getValidOldWayVisitCount());
		map.put("data_3", avo.getNewWayVisitCount() - avo.getValidNewWayVisitCount());
		map.put("data_4", avo.getOldWayVisitCount() - avo.getValidOldWayVisitCount());
		
		return map;
	}

	@Override
	public Map<String, Object> findSeeCustomerContractRecordsInfoOutSide(String proId, String startDate,
			String endDate) {
		Map<String, Object> map = new HashMap<>();
		//外场
		OutSideProject osp = new OutSideProject();
		osp = osp.creatMoreObjTurnOne(proId,startDate,endDate,baseDao,projectCreateService);
		//内场
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,osp,projectCreateService);
		
		map.put("title", "认购货值分析");
		map.put("name_1", "认购套数");
		map.put("name_2", "认购金额");
		map.put("name_3", "认购到款金额");
		map.put("name_4", "认购锁定房源货值");
		map.put("length", 4);
		
		map.put("data_1", avo.getSubscribeHouseCount());
		map.put("data_2", avo.getSubscribeMoney());
		map.put("data_3", avo.getSubscribeGetMoney());
		map.put("data_4", avo.getSubscribeLockHouseMoney());
		
		return map;
	}

	@Override
	public Map<String, Object> findSeeCustomerHaveDealInfoOutSide(String proId, String startDate, String endDate) {
		Map<String, Object> map = new HashMap<>();
		//外场
		OutSideProject osp = new OutSideProject();
		osp = osp.creatMoreObjTurnOne(proId,startDate,endDate,baseDao,projectCreateService);
		//内场
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,osp,projectCreateService);
		
		map.put("title", "观察客户签约");
		map.put("name_1", "已签约数");
		map.put("name_2", "放弃签约数");
		map.put("name_3", "待签约数");
		map.put("length", 3);
		
		map.put("data_1", avo.getSignCount());
		map.put("data_2", avo.getGiveUpSignCount());
		map.put("data_3", avo.getWaitSignCount());
		
		return map;
	}

	@Override
	public Map<String, Object> findSeeCustomerHaveDealMoneyInfoOutSide(String proId, String startDate, String endDate) {
		Map<String, Object> map = new HashMap<>();
		//外场
		OutSideProject osp = new OutSideProject();
		osp = osp.creatMoreObjTurnOne(proId,startDate,endDate,baseDao,projectCreateService);
		//内场
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,osp,projectCreateService);
		
		map.put("title", "签约货值分析");
		map.put("name_1", "已签约房源货值");
		map.put("name_2", "放弃签约房源货值");
		map.put("name_3", "待签约房源货值");
		map.put("length", 3);
		
		map.put("data_1", avo.getSignHouseMoney());
		map.put("data_2", avo.getGiveUpHouseMoney());
		map.put("data_3", avo.getWaitSignHouseMoney());
		
		return map;
	}

	@Override
	public Map<String, Object> findSeeCustomerHaveDealNumberInfoOutSide(String proId, String startDate,
			String endDate) {
		Map<String, Object> map = new HashMap<>();
		//外场
		OutSideProject osp = new OutSideProject();
		osp = osp.creatMoreObjTurnOne(proId,startDate,endDate,baseDao,projectCreateService);
		//内场
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,osp,projectCreateService);
		
		map.put("title", "成交量分析");
		map.put("name_1", "来访成交比");
		map.put("name_2", "储客成交比");
		map.put("name_3", "认购签约率");
		map.put("length", 3);
		
		map.put("data_1", avo.getVisitDealRate());
		map.put("data_2", avo.getEnterBuySignRate());
		map.put("data_3", avo.getGrandCusDealRate());
		
		return map;
	}

	@Override
	public Map<String, Object> findSeeSaleScheduleInfoOutSide(String proId, String startDate, String endDate) {
		Map<String, Object> map = new HashMap<>();
		//外场
		OutSideProject osp = new OutSideProject();
		osp = osp.creatMoreObjTurnOne(proId,startDate,endDate,baseDao,projectCreateService);
		//内场
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,osp,projectCreateService);
		
		map.put("title", "销售进度观察");
		map.put("name_1", "认购套数占比");
		map.put("name_2", "签约套数占比");
		map.put("name_3", "首付套数占比");
		map.put("name_4", "按揭到款套数占比");
		map.put("length", 4);
		
		return null;
	}

	@Override
	public Map<String, Object> findSeeCustomerGuideRecprdsToVisitInfoOutSide(String proId, String startDate,
			String endDate) {
		Map<String, Object> map = new HashMap<>();
		//内场
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,projectCreateService);
		//外场
		OutSideProject osp = new OutSideProject();
		osp = osp.creatMoreObjTurnOne(proId,startDate,endDate,baseDao,projectCreateService);
		
		map.put("title", "备案到访情况");
		// map.put("name_1", "报备客户数");
		map.put("name_1", "备案到访客户数");
		map.put("name_2", "备案未到访客户数");
		// map.put("name_4", "报备到访率");
		// map.put("name_5", "外场导客占比");
		map.put("length", 2);
		
		map.put("data_1", osp.getRecordVisitCount());
		map.put("data_2", osp.getRecordNotVisitCount());
		
		return map;
	}

	@Override
	public Map<String, Object> findSeeOutCustomerDealInfoOutSide(String proId, String startDate, String endDate) {
		Map<String, Object> map = new HashMap<>();
		//内场
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,projectCreateService);
		//外场
		OutSideProject osp = new OutSideProject();
		osp = osp.creatMoreObjTurnOne(proId,startDate,endDate,baseDao,projectCreateService);
		
		map.put("title", "外场成交分析");
		map.put("name_1", "带客成交");
		map.put("name_2", "外场成交数");
		// map.put("name_3", "外场成交占比");
		map.put("name_3", "内场成交数");
		map.put("length", 3);
		
		map.put("data_1", osp.getGuideCustomerVisitCount());
		map.put("data_2", osp.getOutSideDealCount());
		map.put("data_3", osp.getIntFiledToDealNum());
		
		return map;
	}

	@Override
	public Map<String, Object> findSeeOldCustomerVisitInfoOutSide(String proId, String startDate, String endDate) {
		Map<String, Object> map = new HashMap<>();
		//内场
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,projectCreateService);
		//外场
		OutSideProject osp = new OutSideProject();
		osp = osp.creatMoreObjTurnOne(proId,startDate,endDate,baseDao,projectCreateService);
		
		map.put("title", "观察老客户接访");
		map.put("name_1", "确认老客户接访次数");
		// map.put("name_2", "老客户接访次数占比");
		map.put("length", 1);
		
		map.put("data_1", avo.getAffirmOldCustomerVisitNum());
		
		return map;
	}

	@Override
	public Map<String, Object> findSeeReserveCustomerInfoOutSide(String proId, String startDate, String endDate) {
		Map<String, Object> map = new HashMap<>();
		//内场
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,projectCreateService);
		//外场
		OutSideProject osp = new OutSideProject();
		osp = osp.creatMoreObjTurnOne(proId,startDate,endDate,baseDao,projectCreateService);
		
		map.put("title", "观察储客分析");
		map.put("name_1", "新增总储客数");
		map.put("name_2", "新增二次来访客户数");
		map.put("name_3", "老客户数");
		//map.put("name_4", "客户回头率");
		map.put("length", 3);
		
		map.put("data_1", avo.getNewAddCollCustomerCount());
		map.put("data_2", avo.getNewTwoVisitCustomerCount());
		map.put("data_3", avo.getGrandTotalOldCustomerCount());
		//map.put("data_4", avo.getCustomerTurnHandRate());
		
		return map;
	}

	@Override
	public Map<String, Object> findSeeAllCustomerContractRecordInfoOutSide(String proId, String startDate,
			String endDate) {
		Map<String, Object> map = new HashMap<>();
		//外场
		OutSideProject osp = new OutSideProject();
		osp = osp.creatMoreObjTurnOne(proId,startDate,endDate,baseDao,projectCreateService);
		//内场
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,osp,projectCreateService);
		
		map.put("title", "观察老客户接访");
		map.put("name_1", "总认购客户数");
		map.put("name_2", "老客户认购数");
		map.put("name_3", "新客户认购数");
		map.put("length", 3);
		
		map.put("data_1", avo.getNewSubscribeCustomerCount());
		map.put("data_2", avo.getOldSubscribeCustomerCount());
		map.put("data_3", avo.getNewSubscribeCustomerCount());
		
		return map;
	}

}
