package com.housesline.service.out.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.housesline.bean.AgentVisitOrder;
import com.housesline.bean.OutSideProject;
import com.housesline.dao.BaseDao;
import com.housesline.service.out.AppDirectorByGrlService;
import com.housesline.service.project.ProjectCreateService;
import com.housesline.utils.DateUtil;
import com.housesline.utils.SysContent;

/**
 * 经理段app
 * @author Administrator
 *
 */
@Service(value="appDirectorService")
public class AppDirectorByGrlServiceImpl implements AppDirectorByGrlService {

	@Autowired
	private BaseDao baseDao;
	
	@Autowired
	private ProjectCreateService projectCreateService;

	//获取详细接访数据(1.1)
	@Override
	public Map findToDayDetailedReceiveDataByTime(String proId, String startDate, String endDate) {
		Map map = new HashMap<>();
		String outSideLeadCusRate = "0.00";
		//外场
		OutSideProject osp = new OutSideProject();
		osp = osp.creatMoreObjTurnOne(proId,startDate,endDate,baseDao,projectCreateService);
		//内场
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId, null, startDate, endDate, baseDao, osp, null, projectCreateService);
		map.put("averrageTime",avo.getEveryOnceAverageReplaceTimeLong());//平均接访时长
		map.put("recCount", avo.getVisitCount());//接访总数
		map.put("valid", avo.getValidVisitCount());//有效到访数
		map.put("unValid", avo.getUnValidVisitCount());//无效到访数
		map.put("oldCustomer", avo.getGrandTotalOldCustomerCount());//老客户数
		map.put("validRate", avo.getValidRate()+"%");//有效到访率
		map.put("grVisitedRate", osp.getGrVisiteRate()+"%");//报备到访率
		map.put("oldRate", avo.getOldReceptRate()+"%");//老客户接访次数占比
		map.put("replaceRate", avo.getTotalReplaceReceptRate()+"%");//总替接率
		//外场导客比
		if(avo.getGrandTotalCollCustomerCount()>0){
			double d = (double)osp.getRecordVisitCount() / (double)avo.getGrandTotalCollCustomerCount() * 100;
			outSideLeadCusRate = SysContent.get2Double(d);
		}
		map.put("systemRate", outSideLeadCusRate+"%");//平台导客比 = 外场导客比
		return map;
	}

	//获取详细成交数据(1.2.2)
	@Override
	public Map findDealDataByTime(String proId, String startDate, String endDate) {
		Map map = new HashMap<>();
		//外场
		OutSideProject osp = new OutSideProject();
		osp = osp.creatMoreObjTurnOne(proId, startDate, endDate, baseDao,projectCreateService);
		//内场
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId, null, startDate, endDate, baseDao, osp, null, projectCreateService);
		//成交数量
		map.put("dealCount", avo.getSignCount());
		//今日下定
		map.put("payMoney", avo.getPayCount());
		//今日签约
		map.put("signCount",avo.getSignCount());
		//已下定锁定房源货值
		map.put("lockedValue", SysContent.get2Double(avo.getConfirmHouseMoney()));
		//已签约房源货值
		map.put("signedHouseValue", SysContent.get2Double(avo.getSignHouseMoney()));
		//平台客户认购占比
		map.put("systemCusEnterBuyRate", osp.getSystemEnterBuyRate()+"%");
		//平台客户签约占比
		map.put("systemCusSignRate", osp.getSysCusSignedRate()+"%");
		return map;
	}

	//顾问状态数据
	@Override
	public Map findAgentStatusDataById(String proId, String startDate, String endDate,String agentId) {
		Map map = new HashMap<>();
		//内场
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,agentId,startDate,endDate,baseDao,projectCreateService);
		map.put("totalVisit",avo.getVisitCount());//总接访组数
		map.put("validRate", avo.getValidRate());//有效接访率
		map.put("validRateCompare",0);
		map.put("valid",avo.getValidVisitCount());//有效接访组数
		map.put("unvalid",avo.getUnValidVisitCount());//无效接访组数
		map.put("newCus", avo.getValidNewCustomerVisitCount());//新客户接访数
		map.put("oleCus",avo.getValidOldCustomerVisitCount());//老客户接访数
		
		//总储客数
		map.put("totalSaveCusCount", avo.getGrandTotalCollCustomerCount());
		//回头率
		map.put("secondRate", avo.getCustomerTurnHandRate()+"%");
		//回头率比较
		map.put("secondRateCompare", 0);
		//新增客户数
		map.put("newAddCusCount", avo.getNewAddCollCustomerCount());
		//新增二次来访
		map.put("secondCount", avo.getNewTwoVisitCustomerCount());
		
		//认购信息
		map.put("getMoney", avo.getSubscribeGetMoney());//认购到款金额
		map.put("money",avo.getSignHouseMoney());//成交数据(已签约房源货值)
		map.put("dealCount", avo.getSignCount());//成交数
		map.put("paySignRate",avo.getPaySignRate());//下定签约率
		map.put("paySignRateCompare", 0);//下定率比较
		map.put("areadlyPayCount",avo.getPayCount());//下定组数
		map.put("sign", avo.getSignCount());//已签约数
		//等待签约
		map.put("waitSign", avo.getWaitSignCount());
		//放弃签约
		//map.put("overSign",avo.getGiveUpSignCount());
		//放弃认购 	
		//map.put("overEnterBuy",0);
		return map;
	}
	
	
}
