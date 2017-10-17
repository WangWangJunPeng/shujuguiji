package com.housesline.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

import com.housesline.dao.BaseDao;
import com.housesline.service.project.ProjectCreateService;
import com.housesline.utils.DateUtil;
import com.housesline.utils.SysContent;

/**
 *  顾问到访订单归集
 *  以每个置业顾问每天为元组归集到访数据
 * @author cdh 2017-07-27
 *
 */
public class AgentVisitOrder {
	
	//使用cvDate+agentId联合主键
	/**------------ 到访----------- */
	//数据日期  yyyy-MM-dd
	private String cvDate;
	//归集操作时间 yyyy-MM-dd HH:mm:ss
	private String collDateTime;
	//置业顾问id
	private String agentId;
	//置业顾问名称
	private String agentName;
	//置业顾问电话
	private String agentPhone;
	//置业顾问状态 0:非正常；1:正常
	private Integer agentStatus;
	//到访数
	private Integer visitCount;
	//有效接访数
	private Integer validVisitCount;
	//无效到访数 = 到访数 - 有效到访数
	private Integer unValidVisitCount;
	//新增到访数
	private Integer newVisitCount;
	//再到访
	private Integer visitAgain;
	//二次到访数
	private Integer secondVisitCount;
	//二次以上到访（不含二次）
	private Integer moreVisitCount;
	//指定到访数（总数）
	private Integer appointCount;
	//新客户通道接访数
	private Integer newWayVisitCount;
	//新客户通道有效接访数
	private Integer validNewWayVisitCount;
	//新客户通道指定接访
	private Integer appointNewWayVisitCount;
	//老客户通道接访数
	private Integer oldWayVisitCount;
	//老客户通道有效接访数
	private Integer validOldWayVisitCount;
	//老客户通道指定接访数
	private Integer appointOldWayVisitCount;
	//替接数
	private Integer replaceCount;
	//按序接访替接数
	private Integer orderReplaceVisitCount;
	//指定接访替接数 = 替接数 - 按序接访替接数
	private Integer appointReplaceVisitCount;
	//确认老客户到访数（第一次到访填写状态1  第二次到访也是1）
	private Integer affirmOldCustomerVisitNum;
	//指定流失
	private Integer appointLosedCount;
	//指定有效到访
	private Integer appointValidVisitCount;
	//流失数
	private Integer losedCount;
	//指定新登
	private Integer newAppointCount;
	//新客户接访时长
	private String newCustomerVisitTime;
	//老客户接访时长
	private String oldCustomerVisitTime;
	//替接时长
	private String replaceVisitTime;
	//总接访时长
	private String totalVisitTime;
	//平均接访时长
	private String averageVisitTime;
	//到访id（集合）
	private String visitId;
	//新客户接访数
	private Integer newCustomerVisitCount;
	//新客户有效接访
	private Integer validNewCustomerVisitCount;
	//老客户接访数
	private Integer oldCustomerVisitCount;
	//老客户有效接访数
	private Integer validOldCustomerVisitCount;

	
	/**------------ 订单 ----------- */
	//认购数
	private Integer enterBuyCount;
	//同意认购数
	private Integer agreeEnterCount;
	//拒绝认购数
	private Integer refuseEnterCount;
	//下定数
	private Integer payCount;
	//签约数
	private Integer signCount;
	//撤单数
	private Integer revokeOrderCount;
	//下定房源货值
	private Double confirmHouseMoney;
	//总认购套数
	private Integer subscribeHouseCount;
	//认购金额
	private Double subscribeMoney;
	//认购到款金额
	private Double subscribeGetMoney;
	//认购锁定房源货值
	private Double subscribeLockHouseMoney;
	//放弃签约数
	private Integer giveUpSignCount;
	//等待签约数
	private Integer waitSignCount;
	//已签约房源货值
	private Double signHouseMoney;
	//放弃签约房源货值
	private Double giveUpHouseMoney;
	//等待签约房源货值
	private Double waitSignHouseMoney;
	//签约的订单id（集合）
	private String signedOrderId;
	//拒绝的订单id（集合）
	private String refusedOrderId;
	//撤单的订单（集合）
	private String revokeOrderId;
	//下定的订单 （集合）
	private String payOrderId;
	
	/**------------ 储客 -------------*/
	//新增储客数 新客户数
	private Integer newAddCollCustomerCount;
	//总储客数
	private Integer grandTotalCollCustomerCount;
	//老客户数
	private Integer grandTotalOldCustomerCount;
	//总储客（截止当前时间所有累计）
	private Integer totalCustomerCount;
	//老客户数(截止当前时间所有累计)
	private Integer totalOldCustomerCount;
	//新增二次来访客户数
	private Integer newTwoVisitCustomerCount;
	//签约总客户数(借用该字段)
	private Integer customerReturnBackVisitNum;
	//老客户签约数
	private Integer oldCustomerSignedCount;
	//新客户签约数
	private Integer newCustomerSignedCount;
	//平台导客数
	private Integer platformCustomerCount;
	//总认购客户数
	private Integer subscribeCustomerCount;
	//新客户认购数
	private Integer newSubscribeCustomerCount;
	//老客户认购数
	private Integer oldSubscribeCustomerCount;
	//来访成交数
	private Integer visitToDealCount;
	//储客成交数
	private Integer momeryCuDealCount;
	//集合字符串，新增储客的id
	private String newAddCollCustomerId;
	//集合字符串，总储客客户的id
	private String grandTotalCollCustomerId;
	//集合字符串，老客户的id
	private String grandTotalOldCustomerId;
	
	/**------------- 考勤 ---------------*/
	//签到时间
	private String signInTime;
	//签退时间
	private String signOutTime;
	//预留字段
	private String reserveField;
	
	/**-------------备用比率字段------------**/
	//每次平均接访时长
	private String everyOnceAverageReplaceTimeLong = "0";
	//每日平均接访时长
	private String everyDayAverageReplaceTimeLong = "0";
	//有效接访率
	private String validRate = "0.00";
	//老客户通道占比
	private String oldWayRate = "0.00";
	//新客户通道有效占比
	private String newWayValidRate = "0.00";
	//老客户通道有效占比
	private String oldWayValidRate = "0.00";
	//老客户接访次数占比
	private String oldReceptRate = "0.00";
	//新客户接访次数占比
	private String newReceptRate = "0.00";
	//指定接访率
	private String appointReceptRate = "0.00";
	//新客户通道指定接访率
	private String newWayAppointReceptRate = "0.00";
	//老客户通道指定接访率
	private String oldWayAppointReceptRate = "0.00";
	//指定有效接访率
	private String appointValidReceptRate = "0.00";
	//指定无效接访率
	private String appointUnValidReceptRate = "0.00";
	//总替接率
	private String totalReplaceReceptRate = "0.00";
	//客户回头率
	private String customerTurnHandRate = "0.00";
	//新增客户认购率
	private String newAddCusEnterBuyRate = "0.00";
	//老客户认购率
	private String oldCusEnterBuyRate = "0.00";
	//来访成交比
	private String visitDealRate = "0.00";
	//储客成交比
	private String grandCusDealRate = "0.00";
	//认购签约率
	private String enterBuySignRate = "0.00";
	//外场导客比
	//private String outSideLeadCusRate = "0.00";
	//新客户无有效接访
	private Integer unValidNewCustomerVisitCount;
	//下定签约率(已签约数/已下定数)
	private String paySignRate = "0.00";
	
	
	
	
	/**
	 * 数据封装类
	 * @param proId
	 * @param agentId
	 * @param startDate
	 * @param endDate
	 * @param baseDao
	 * @return
	 */
	public AgentVisitOrder creatMoreObjTurnOne(String proId,String agentId, String startDate, String endDate, BaseDao baseDao, ProjectCreateService projectCreateService){
		return creatMoreObjTurnOne(proId, agentId, startDate, endDate, baseDao, null, null,projectCreateService);
	}
	
	/**
	 * 数据封装类
	 * @param proId
	 * @param agentId
	 * @param startDate
	 * @param endDate
	 * @param baseDao
	 * @param osp
	 * @return
	 */
	public AgentVisitOrder creatMoreObjTurnOne(String proId,String agentId, String startDate, String endDate, BaseDao baseDao, OutSideProject osp, ProjectCreateService projectCreateService){
		return creatMoreObjTurnOne(proId, agentId, startDate, endDate, baseDao, osp, null,projectCreateService);
	}
	
	
	/**
	 * 数据封装类
	 * cdh
	 * @param proId
	 * @param baseDao
	 * @param aos
	 * @return
	 */
	public AgentVisitOrder creatMoreObjTurnOne(String proId, String startDate, String endDate, BaseDao baseDao, List<AgentVisitOrder> aos, ProjectCreateService projectCreateService){
		return creatMoreObjTurnOne(proId, null, startDate, endDate, baseDao, null, aos,projectCreateService);
	}
	
	/**
	 * 数据封装工具
	 * @param proId
	 * @param agentId
	 * @param startDate
	 * @param endDate
	 * @param baseDao
	 * @return
	 */
	public AgentVisitOrder creatMoreObjTurnOne(String proId,String agentId, String startDate, String endDate, BaseDao baseDao, OutSideProject osp, List<AgentVisitOrder> aos, ProjectCreateService projectCreateService){
		int visitCount = 0;//接访数
		int totalVisitTime = 0;
		String strAverageLongTime = "0";
		int validReceCount = 0;
		int unValidReceCount = 0;
		int oldCusCount = 0;
		int newCustomerVisitTime = 0;
		int oldCustomerVisitTime = 0;
		int replaceVisitTime = 0;
		String validRate = "0.00";
		String grVisiteRate = "0.00";
		String oldCusRate = "0.00";
		String replaceRate = "0.00";
		long averageVisitTime = 0L;
		if(this.visitCount==null){
			this.visitCount = 0;
		}
		if(this.validVisitCount==null){
			this.validVisitCount = 0;
		}
		if(this.replaceCount==null){
			this.replaceCount = 0;
		}
		if(this.orderReplaceVisitCount==null){
			this.orderReplaceVisitCount = 0;
		}
		if(this.newWayVisitCount==null){
			this.newWayVisitCount = 0;
		}
		if(this.oldWayVisitCount==null){
			this.oldWayVisitCount = 0;
		}
		if(this.appointCount==null){
			this.appointCount = 0;
		}
		if(this.grandTotalCollCustomerCount==null){
			this.grandTotalCollCustomerCount = 0;
		}
		if(this.newAddCollCustomerCount==null){
			this.newAddCollCustomerCount = 0;
		}
		if(this.grandTotalOldCustomerCount==null){
			this.grandTotalOldCustomerCount = 0;
		}
		if(this.subscribeCustomerCount==null){
			this.subscribeCustomerCount = 0;
		}
		if(this.payCount == null){
			this.payCount = 0;
		}
		if(this.confirmHouseMoney==null){
			this.confirmHouseMoney = 0.00;
		}
		if(this.signHouseMoney==null){
			this.signHouseMoney = 0.00;
		}
		if(this.newCustomerVisitCount==null){
			this.newCustomerVisitCount = 0;
		}
		if(this.unValidNewCustomerVisitCount==null){
			this.unValidNewCustomerVisitCount = 0;
		}
		if(this.appointLosedCount==null){
			this.appointLosedCount = 0;
		}
		if(this.appointValidVisitCount==null){
			this.appointValidVisitCount = 0;
		}
		if(this.subscribeGetMoney==null){
			this.subscribeGetMoney = 0.00;
		}
		if(this.validNewCustomerVisitCount==null){
			this.validNewCustomerVisitCount = 0;
		}
		if(this.enterBuyCount==null){
			this.enterBuyCount = 0;
		}
		if(this.signCount==null){
			this.signCount = 0;
		}
		if(this.newCustomerSignedCount==null){
			this.newCustomerSignedCount = 0;
		}
		if(this.losedCount==null){
			this.losedCount = 0;
		}
		if(this.momeryCuDealCount==null){
			this.momeryCuDealCount = 0;
		}
		if(this.agreeEnterCount == null){
			this.agreeEnterCount = 0;
		}
		if(this.refuseEnterCount == null){
			this.refuseEnterCount = 0;
		}
		if(this.revokeOrderCount == null){
			this.revokeOrderCount = 0;
		}
		if(this.totalCustomerCount == null){
			this.totalCustomerCount = 0;
		}
		if(this.totalOldCustomerCount == null){
			this.totalOldCustomerCount = 0;
		}
		if(this.oldCustomerSignedCount == null){
			this.oldCustomerSignedCount = 0;
		}
		if(this.newCustomerSignedCount == null){
			this.newCustomerSignedCount = 0;
		}
		if(this.platformCustomerCount == null){
			this.platformCustomerCount =0;
		}
		if(this.subscribeMoney == null){
			this.subscribeMoney = 0d;
		}
		if(this.subscribeLockHouseMoney == null){
			this.subscribeLockHouseMoney = 0d;	
		}
		if(this.giveUpSignCount == null){
			this.giveUpSignCount = 0;
		}
		if(this.giveUpHouseMoney == null){
			this.giveUpHouseMoney = 0d;
		}
		if(this.waitSignHouseMoney == null){
			this.waitSignHouseMoney = 0d;
		}
		if(this.newSubscribeCustomerCount == null){
			this.newSubscribeCustomerCount = 0;
		}
		if(this.customerReturnBackVisitNum == null){
			this.customerReturnBackVisitNum = 0;
		}
		if(this.subscribeHouseCount == null){
			this.subscribeHouseCount = 0;
		}
		if(this.newVisitCount == null){
			this.newVisitCount = 0;
		}
		if(this.secondVisitCount == null){
			this.secondVisitCount = 0;
		}
		if(this.moreVisitCount == null){
			this.moreVisitCount = 0;
		}
		if(this.newAppointCount == null){
			this.newAppointCount = 0;
		}
		if(this.averageVisitTime == null){
			this.averageVisitTime = "";
		}
		if(this.oldSubscribeCustomerCount == null){
			this.oldSubscribeCustomerCount = 0;
		}
		
		if(StringUtils.isEmpty(agentId) && osp != null){
			this.enterBuyCount += osp.getOutSideCustomerRecordCount();
			this.agreeEnterCount += osp.getOutSideAgreeRecordCount();
			this.refuseEnterCount += osp.getOutSideRefuseRecordCount();
			this.subscribeHouseCount += osp.getOutSideSubscribeHouseCount();
			this.payCount += osp.getOutSideWaitSignCount();
			this.giveUpSignCount += osp.getOutSideRevokeOrderCount();
			this.subscribeHouseCount += osp.getOutSideRevokeOrderCount();
			this.subscribeMoney += osp.getOutSideSubscribeMoney();
			this.subscribeGetMoney += osp.getOutSideSubscribeGetMoney();
			this.subscribeLockHouseMoney += osp.getOutSideSubscribeLockHouseMoney();
			this.giveUpHouseMoney += osp.getOutSideGiveUpHouseMoney();
			this.waitSignHouseMoney += osp.getOutSideWaitSignHouseMoney();
			this.signHouseMoney += osp.getOutSideSignHouseMoney();
			this.subscribeCustomerCount += osp.getOutSideRecordCuCount();
			this.newSubscribeCustomerCount += osp.getOutSideNewCuRecordCount();
			this.oldSubscribeCustomerCount += osp.getOutSideOldCuRecordCount();
			this.customerReturnBackVisitNum += osp.getOutSideSignCuCount();
			this.oldCustomerSignedCount += osp.getOutSideNewCuSignCount();
			this.newCustomerSignedCount += osp.getOutSideOldSignCount();
		}
		//判断选择的时间是当天的
		String thisDate = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		boolean flag = false;
		if(startDate.equals(endDate)){
			if(thisDate.equals(startDate)){
				flag = true;
			}
		}
		
		List<AgentVisitOrder> list = new ArrayList<>();
		if(flag){
			list = projectCreateService.getAnalysisOfData(proId, thisDate);
		}else{
			
			if(aos == null){
				if(startDate!=null && !startDate.equals("") && endDate!=null && !endDate.equals("")){
					startDate = DateUtil.format(DateUtil.parse(startDate, DateUtil.PATTERN_CLASSICAL_SIMPLE), DateUtil.PATTERN_CLASSICAL_SIMPLE);
					endDate = DateUtil.format(DateUtil.parse(endDate, DateUtil.PATTERN_CLASSICAL_SIMPLE), DateUtil.PATTERN_CLASSICAL_SIMPLE);
					list = baseDao.selectAgentVisitOrderListForProject(proId, startDate, endDate, null);
				}else{
					list = baseDao.selectAgentVisitOrderListForProject(proId, null, null, DateUtil.format(new Date(),DateUtil.PATTERN_CLASSICAL_SIMPLE));
				}
			}else{
				list = aos;
			}
		}
		for(AgentVisitOrder avo : list){
			if(agentId!=null && !avo.getAgentId().equals(agentId)){
				continue;
			}
			averageVisitTime += new Long(avo.getAverageVisitTime());
			this.newAppointCount += avo.getNewAppointCount();
			this.moreVisitCount += avo.getMoreVisitCount();
			this.secondVisitCount += avo.getSecondVisitCount();
			this.newVisitCount += avo.getNewVisitCount();
			this.agreeEnterCount += avo.getAgreeEnterCount();
			this.refuseEnterCount += avo.getRefuseEnterCount();
			this.revokeOrderCount += avo.getRevokeOrderCount();
			this.totalCustomerCount += avo.getTotalCustomerCount();
			this.totalOldCustomerCount += avo.getTotalOldCustomerCount();
			this.oldCustomerSignedCount += avo.getOldCustomerSignedCount();
			this.platformCustomerCount += avo.getPlatformCustomerCount();
			
			this.newCustomerSignedCount += avo.getNewCustomerSignedCount();//新客户签约数
			this.momeryCuDealCount += avo.getMomeryCuDealCount();//储客成交数
			this.losedCount += avo.getLosedCount();//流失客户数
			this.enterBuyCount += avo.getEnterBuyCount();//总认购数
			this.visitCount += avo.getVisitCount();//总接访次数
			totalVisitTime += Integer.valueOf(avo.getTotalVisitTime());//总接访时间
			this.validVisitCount += avo.getValidVisitCount();//有效到访数
			this.grandTotalOldCustomerCount += avo.getGrandTotalOldCustomerCount();//老客户数
			this.newWayVisitCount += avo.getNewWayVisitCount();//新客户通道到访数
			this.oldWayVisitCount += avo.getOldWayVisitCount();//老客户通道到访数
			if(this.validNewWayVisitCount==null){
				this.validNewWayVisitCount = 0;
			}
			this.validNewWayVisitCount += avo.getValidNewWayVisitCount();//新客户通道有效到访
			if(this.validOldWayVisitCount==null){
				this.validOldWayVisitCount = 0;
			}
			this.validOldWayVisitCount += avo.getValidOldWayVisitCount();//老客户通道有效到访
			this.newAddCollCustomerCount += avo.getNewAddCollCustomerCount();//新增储客数
			if(this.affirmOldCustomerVisitNum==null){
				this.affirmOldCustomerVisitNum = 0;
			}
			this.affirmOldCustomerVisitNum += avo.getAffirmOldCustomerVisitNum();//确认老客户数
			this.appointCount += avo.getAppointCount();//指定接访数
			if(this.appointNewWayVisitCount==null){
				this.appointNewWayVisitCount = 0;
			}
			this.appointNewWayVisitCount += avo.getAppointNewWayVisitCount();//新客户通道指定接访
			if(this.appointOldWayVisitCount==null){
				this.appointOldWayVisitCount = 0;
			}
			this.appointOldWayVisitCount += avo.getAppointOldWayVisitCount();//老客户通道指定接访
			this.appointLosedCount += avo.getAppointLosedCount();//指定无效接访数
			this.replaceCount += avo.getReplaceCount();//总替接数
			this.orderReplaceVisitCount = avo.getOrderReplaceVisitCount();//按序接访替接数
			newCustomerVisitTime += Integer.valueOf(avo.getNewCustomerVisitTime());//新客户接访时长
			oldCustomerVisitTime += Integer.valueOf(avo.getOldCustomerVisitTime());//老客户接访时长
			replaceVisitTime += Integer.valueOf(avo.getReplaceVisitTime());//替接接访时长
			if(this.newTwoVisitCustomerCount==null){
				this.newTwoVisitCustomerCount = 0;
			}
			this.newTwoVisitCustomerCount += avo.getNewTwoVisitCustomerCount();//新增二次来访客户数
			this.subscribeCustomerCount += avo.getSubscribeCustomerCount();//总认购客户数
			if(this.newSubscribeCustomerCount==null){
				this.newSubscribeCustomerCount = 0;
			}
			this.newSubscribeCustomerCount += avo.getNewSubscribeCustomerCount();//新增客户认购数 //首访客户认购数
			if(this.subscribeHouseCount==null){
				this.subscribeHouseCount = 0;
			}
			this.subscribeHouseCount += avo.getSubscribeHouseCount();//认购套数
			if(this.subscribeMoney==null){
				this.subscribeMoney = 0.00;
			}
			this.subscribeMoney += avo.getSubscribeMoney();//认购金额
			this.subscribeGetMoney += avo.getSubscribeGetMoney();//认购到款金额
			if(this.subscribeLockHouseMoney==null){
				this.subscribeLockHouseMoney = 0.00;
			}
			this.subscribeLockHouseMoney += avo.getSubscribeLockHouseMoney();//认购锁定房源货值
			if(this.oldSubscribeCustomerCount==null){
				this.oldSubscribeCustomerCount = 0;
			}
			this.oldSubscribeCustomerCount += avo.getOldSubscribeCustomerCount();//老客户认购数
			this.signCount += avo.getSignCount();//已签约数
			if(this.giveUpSignCount==null){
				this.giveUpSignCount = 0;
			}
			this.giveUpSignCount += avo.getGiveUpSignCount();//放弃签约数
			if(this.giveUpHouseMoney==null){
				this.giveUpHouseMoney = 0.00;
			}
			this.giveUpHouseMoney += avo.getGiveUpHouseMoney();//放弃签约房源货值
			if(this.waitSignCount==null){
				this.waitSignCount = 0;
			}
			this.waitSignCount +=avo.getWaitSignCount(); //待签约数
			if(this.waitSignHouseMoney==null){
				this.waitSignHouseMoney = 0.00;
			}
			this.waitSignHouseMoney += avo.getWaitSignHouseMoney();//待签约房源货值
			if(this.visitToDealCount==null){
				this.visitToDealCount = 0;
			}
			this.visitToDealCount += avo.getVisitToDealCount();//来访成交数
			if(this.customerReturnBackVisitNum==null){
				this.customerReturnBackVisitNum = 0;
			}
			this.customerReturnBackVisitNum = avo.getCustomerReturnBackVisitNum();//签约客户数
			this.grandTotalCollCustomerCount += avo.getGrandTotalCollCustomerCount();//总储客数
			if(this.oldCustomerVisitCount==null){
				this.oldCustomerVisitCount = 0;
			}
			this.oldCustomerVisitCount += avo.getOldCustomerVisitCount();//老客户接访数
			this.payCount += avo.getPayCount();//今日下定数
			this.confirmHouseMoney += avo.getConfirmHouseMoney();//已下定锁定房源货值（定金确认后的）
			this.signHouseMoney += avo.getSignHouseMoney();//已签约锁定房源货值 
			this.newCustomerVisitCount += avo.getNewCustomerVisitCount();//新客户到访数
			this.validNewCustomerVisitCount += avo.getValidNewCustomerVisitCount();//新客户有效到访数
		}
		this.averageVisitTime = new Long(averageVisitTime).toString();
		this.totalVisitTime = String.valueOf(totalVisitTime);//总接访时间
		this.unValidVisitCount = this.visitCount - this.validVisitCount;//无效接访数
		this.appointReplaceVisitCount = this.replaceCount - this.orderReplaceVisitCount;//指定接访替接数
		this.unValidNewCustomerVisitCount = this.newCustomerVisitCount - this.validNewCustomerVisitCount;//新客户无效到访数
		this.appointValidVisitCount = this.appointCount - this.appointLosedCount;//指定有效接访数
		//每次平均接访时长
		if(this.visitCount>0){
			this.everyOnceAverageReplaceTimeLong = String.valueOf(Integer.valueOf(this.totalVisitTime) / this.visitCount);
		}
		//每日平均接访时长
		if(list.size()>0){
			this.everyDayAverageReplaceTimeLong = String.valueOf(Integer.valueOf(this.totalVisitTime)/DateUtil.getTwoDateEveryDay(startDate, endDate).size());
		}
		//新客户接访时长
		this.newCustomerVisitTime = String.valueOf(newCustomerVisitTime);
		//老客户接访时长
		this.oldCustomerVisitTime = String.valueOf(totalVisitTime - newCustomerVisitTime);
		//替接接访时长
		this.replaceVisitTime = String.valueOf(replaceVisitTime);
		//总接访数
		if(this.visitCount > 0){
			//有效接访率
			double d =  (double)this.validVisitCount / (double)this.visitCount * 100;
			this.validRate = SysContent.get2Double(d);
			
			//老客户通道占比
			double d1 =  (double)this.oldWayVisitCount / (double)this.visitCount * 100;
			this.oldWayRate = SysContent.get2Double(d1);
			
			//老客户接访次数占比
			double d2 =  (double)this.oldCustomerVisitCount / (double)this.visitCount * 100;
			this.oldReceptRate = SysContent.get2Double(d2);
			
			//新客户接访次数占比
			double d3 =  (double)this.newCustomerVisitCount / (double)this.visitCount * 100;
			this.newReceptRate = SysContent.get2Double(d3);
			
			//指定接访率
			double d4 =  (double)this.appointCount / (double)this.visitCount * 100;
			this.appointReceptRate = SysContent.get2Double(d4);
			
			//总替接率
			double d5 =  (double)this.replaceCount / (double)this.visitCount * 100;
			this.totalReplaceReceptRate = SysContent.get2Double(d5);
			
			
		}
		
		//新客户通道接访数
		if(this.newWayVisitCount>0){
			//新客户通道有效接访占比
			double d0 =  (double)this.validNewWayVisitCount / (double)this.newWayVisitCount * 100;
			this.newWayValidRate = SysContent.get2Double(d0);
		}
		
		//老客户通道接访数
		if(this.oldWayVisitCount>0){
			//老客户通道有效接访占比
			double d0 =  (double)this.validOldWayVisitCount / (double)this.oldWayVisitCount * 100;
			this.oldWayValidRate = SysContent.get2Double(d0);
		}
		
		//指定接访数
		if(this.appointCount>0){
			//新客户通道指定接访率
			double d0 =  (double)this.appointNewWayVisitCount / (double)this.appointCount * 100;
			this.newWayAppointReceptRate = SysContent.get2Double(d0);
			
			//老客户通道指定接访率
			double d1 =  (double)this.appointOldWayVisitCount / (double)this.appointCount * 100;
			this.oldWayAppointReceptRate = SysContent.get2Double(d1);
			
			//指定无效接访率
			double d2 =  (double)this.appointLosedCount / (double)this.appointCount * 100;
			this.appointUnValidReceptRate = SysContent.get2Double(d2);
			
			//指定有效接访率
			double d3 =  (double)this.appointValidVisitCount / (double)this.appointCount * 100;
			this.appointValidReceptRate = SysContent.get2Double(d3);
			
		}
		
		//总储客数
		if(this.grandTotalCollCustomerCount>0){
			//客户回头率
			double d0 =  (double)this.newTwoVisitCustomerCount / (double)this.grandTotalCollCustomerCount * 100;
			this.customerTurnHandRate = SysContent.get2Double(d0);
			
			//外场导客比
			//double d2 =  (double)this.recordVisitCount / (double)this.grandTotalCollCustomerCount * 100;
			//this.outSideLeadCusRate = SysContent.get2Double(d2);
			
		}
		
		//总认购数
		if(this.enterBuyCount>0){
			//新增客户认购率
			double d0 =  (double)this.newSubscribeCustomerCount / (double)this.enterBuyCount * 100;
			this.newAddCusEnterBuyRate = SysContent.get2Double(d0);
			
			//老客户认购率
			double d1 =  (double)this.oldSubscribeCustomerCount / (double)this.enterBuyCount * 100;
			this.oldCusEnterBuyRate = SysContent.get2Double(d1);
			
			//认购签约率
			double d2 =  (double)this.signCount / (double)this.enterBuyCount * 100;
			this.enterBuySignRate = SysContent.get2Double(d2);
		}
		/**
		//新增总储客属
		if(this.newAddCollCustomerCount>0){
			//新增客户认购率
			double d0 =  (double)this.newSubscribeCustomerCount / (double)this.newAddCollCustomerCount * 100;
			this.newAddCusEnterBuyRate = SysContent.get2Double(d0);
		}
		
		//老客户数
		if(this.grandTotalOldCustomerCount>0){
			//老客户认购率
			double d0 =  (double)this.oldSubscribeCustomerCount / (double)this.grandTotalOldCustomerCount * 100;
			this.oldCusEnterBuyRate = SysContent.get2Double(d0);
		}
		*/
		/**
		//认购总客户数
		if(this.subscribeCustomerCount>0){
			//认购签约率
			double d0 =  (double)this.customerReturnBackVisitNum / (double)this.subscribeCustomerCount * 100;
			this.enterBuySignRate = SysContent.get2Double(d0);
		}
		*/
		//
		if(this.payCount>0){
			//下定签约率
			double d0 =  (double)this.signCount / (double)this.payCount * 100;
			this.paySignRate = SysContent.get2Double(d0);
		}
		
		
		if(this.newAddCollCustomerCount+this.losedCount>0){
			//来访成交比 = 签约新客户数/新增储客+流失数
			double d6 =  (double)this.newCustomerSignedCount / (double)(this.newAddCollCustomerCount+this.losedCount) * 100;
			this.visitDealRate = SysContent.get2Double(d6);

		}
		
		//新增储客数
		if(this.newAddCollCustomerCount>0){
			//储客成交比
			double d1 =  (double)this.momeryCuDealCount / (double)this.newAddCollCustomerCount * 100;
			this.grandCusDealRate = SysContent.get2Double(d1);
			
		}
		
		return this;
	}
	
	
	/**
	 * 数据封装工具
	 * @param baseDao 
	 * @param endDate 
	 * @param startDate 
	 * @param proId 
	public AgentVisitOrder creatMoreObjTurnOne(String proId,String agentId, String startDate, String endDate, BaseDao baseDao){
		int visitCount = 0;//接访数
		int totalVisitTime = 0;
		String strAverageLongTime = "0";
		int validReceCount = 0;
		int unValidReceCount = 0;
		int oldCusCount = 0;
		int newCustomerVisitTime = 0;
		int oldCustomerVisitTime = 0;
		int replaceVisitTime = 0;
		String validRate = "0.00";
		String grVisiteRate = "0.00";
		String oldCusRate = "0.00";
		String replaceRate = "0.00";
		if(this.visitCount==null){
			this.visitCount = 0;
		}
		if(this.validVisitCount==null){
			this.validVisitCount = 0;
		}
		if(this.replaceCount==null){
			this.replaceCount = 0;
		}
		if(this.orderReplaceVisitCount==null){
			this.orderReplaceVisitCount = 0;
		}
		if(this.newWayVisitCount==null){
			this.newWayVisitCount = 0;
		}
		if(this.oldWayVisitCount==null){
			this.oldWayVisitCount = 0;
		}
		if(this.appointCount==null){
			this.appointCount = 0;
		}
		if(this.grandTotalCollCustomerCount==null){
			this.grandTotalCollCustomerCount = 0;
		}
		if(this.newAddCollCustomerCount==null){
			this.newAddCollCustomerCount = 0;
		}
		if(this.grandTotalOldCustomerCount==null){
			this.grandTotalOldCustomerCount = 0;
		}
		if(this.subscribeCustomerCount==null){
			this.subscribeCustomerCount = 0;
		}
		if(this.payCount == null){
			this.payCount = 0;
		}
		if(this.confirmHouseMoney==null){
			this.confirmHouseMoney = 0.00;
		}
		if(this.signHouseMoney==null){
			this.signHouseMoney = 0.00;
		}
		if(this.newCustomerVisitCount==null){
			this.newCustomerVisitCount = 0;
		}
		if(this.unValidNewCustomerVisitCount==null){
			this.unValidNewCustomerVisitCount = 0;
		}
		if(this.appointLosedCount==null){
			this.appointLosedCount = 0;
		}
		if(this.appointValidVisitCount==null){
			this.appointValidVisitCount = 0;
		}
		if(this.subscribeGetMoney==null){
			this.subscribeGetMoney = 0.00;
		}
		if(this.validNewCustomerVisitCount==null){
			this.validNewCustomerVisitCount = 0;
		}
		if(this.signCount==null){
			this.signCount = 0;
		}
		if(this.losedCount==null){
			this.losedCount = 0;
		}
		if(this.momeryCuDealCount==null){
			this.momeryCuDealCount = 0;
		}
		List<AgentVisitOrder> list = new ArrayList<>();
		if(startDate!=null && !startDate.equals("") && endDate!=null && !endDate.equals("")){
			startDate = DateUtil.format(DateUtil.parse(startDate, DateUtil.PATTERN_CLASSICAL_SIMPLE), DateUtil.PATTERN_CLASSICAL_SIMPLE);
			endDate = DateUtil.format(DateUtil.parse(endDate, DateUtil.PATTERN_CLASSICAL_SIMPLE), DateUtil.PATTERN_CLASSICAL_SIMPLE);
			list = baseDao.selectAgentVisitOrderListForProject(proId, startDate, endDate, null);
		}else{
			list = baseDao.selectAgentVisitOrderListForProject(proId, null, null, DateUtil.format(new Date(),DateUtil.PATTERN_CLASSICAL_SIMPLE));
		}
		for(AgentVisitOrder avo : list){
			if(!avo.getAgentId().equals(agentId)){
				continue;
			}
			this.newCustomerSignedCount += avo.getNewCustomerSignedCount();//新客户签约数
			this.momeryCuDealCount += avo.getMomeryCuDealCount();//储客成交数
			this.losedCount += avo.getLosedCount();//流失客户数
			this.enterBuyCount += avo.getEnterBuyCount();//总认购数
			this.visitCount += avo.getVisitCount();//总接访次数
			totalVisitTime += Integer.valueOf(avo.getTotalVisitTime());//总接访时间
			this.validVisitCount += avo.getValidVisitCount();//有效到访数
			this.grandTotalOldCustomerCount += avo.getGrandTotalOldCustomerCount();//老客户数
			this.newWayVisitCount += avo.getNewWayVisitCount();//新客户通道到访数
			this.oldWayVisitCount += avo.getOldWayVisitCount();//老客户通道到访数
			if(this.validNewWayVisitCount==null){
				this.validNewWayVisitCount = 0;
			}
			this.validNewWayVisitCount += avo.getValidNewWayVisitCount();//新客户通道有效到访
			if(this.validOldWayVisitCount==null){
				this.validOldWayVisitCount = 0;
			}
			this.validOldWayVisitCount += avo.getValidOldWayVisitCount();//老客户通道有效到访
			this.newAddCollCustomerCount += avo.getNewAddCollCustomerCount();//新增储客数
			if(this.affirmOldCustomerVisitNum==null){
				this.affirmOldCustomerVisitNum = 0;
			}
			this.affirmOldCustomerVisitNum += avo.getAffirmOldCustomerVisitNum();//确认老客户数
			this.appointCount += avo.getAppointCount();//指定接访数
			if(this.appointNewWayVisitCount==null){
				this.appointNewWayVisitCount = 0;
			}
			this.appointNewWayVisitCount += avo.getAppointNewWayVisitCount();//新客户通道指定接访
			if(this.appointOldWayVisitCount==null){
				this.appointOldWayVisitCount = 0;
			}
			this.appointOldWayVisitCount += avo.getAppointOldWayVisitCount();//老客户通道指定接访
			this.appointLosedCount += avo.getAppointLosedCount();//指定无效接访数
			this.replaceCount += avo.getReplaceCount();//总替接数
			this.orderReplaceVisitCount = avo.getOrderReplaceVisitCount();//按序接访替接数
			newCustomerVisitTime += Integer.valueOf(avo.getNewCustomerVisitTime());//新客户接访时长
			oldCustomerVisitTime = Integer.valueOf(avo.getOldCustomerVisitTime());//老客户接访时长
			replaceVisitTime = Integer.valueOf(avo.getReplaceVisitTime());//替接接访时长
			if(this.newTwoVisitCustomerCount==null){
				this.newTwoVisitCustomerCount = 0;
			}
			this.newTwoVisitCustomerCount += avo.getNewTwoVisitCustomerCount();//新增二次来访客户数
			this.subscribeCustomerCount += avo.getSubscribeCustomerCount();//总认购客户数
			if(this.newSubscribeCustomerCount==null){
				this.newSubscribeCustomerCount = 0;
			}
			this.newSubscribeCustomerCount += avo.getNewSubscribeCustomerCount();//新增客户认购数 //首访客户认购数
			if(this.subscribeHouseCount==null){
				this.subscribeHouseCount = 0;
			}
			this.subscribeHouseCount += avo.getSubscribeHouseCount();//认购套数
			if(this.subscribeMoney==null){
				this.subscribeMoney = 0.00;
			}
			this.subscribeMoney += avo.getSubscribeMoney();//认购金额
			this.subscribeGetMoney += avo.getSubscribeGetMoney();//认购到款金额
			if(this.subscribeLockHouseMoney==null){
				this.subscribeLockHouseMoney = 0.00;
			}
			this.subscribeLockHouseMoney += avo.getSubscribeLockHouseMoney();//认购锁定房源货值
			if(this.oldSubscribeCustomerCount==null){
				this.oldSubscribeCustomerCount = 0;
			}
			this.oldSubscribeCustomerCount += avo.getOldSubscribeCustomerCount();//老客户认购数
			this.signCount += avo.getSignCount();//已签约数
			if(this.giveUpSignCount==null){
				this.giveUpSignCount = 0;
			}
			this.giveUpSignCount += avo.getGiveUpSignCount();//放弃签约数
			if(this.giveUpHouseMoney==null){
				this.giveUpHouseMoney = 0.00;
			}
			this.giveUpHouseMoney += avo.getGiveUpHouseMoney();//放弃签约房源货值
			if(this.waitSignCount==null){
				this.waitSignCount = 0;
			}
			this.waitSignCount +=avo.getWaitSignCount(); //待签约数
			if(this.waitSignHouseMoney==null){
				this.waitSignHouseMoney = 0.00;
			}
			this.waitSignHouseMoney += avo.getWaitSignHouseMoney();//待签约房源货值
			if(this.visitToDealCount==null){
				this.visitToDealCount = 0;
			}
			this.visitToDealCount += avo.getVisitToDealCount();//来访成交数
			if(this.customerReturnBackVisitNum==null){
				this.customerReturnBackVisitNum = 0;
			}
			this.customerReturnBackVisitNum = avo.getCustomerReturnBackVisitNum();//签约客户数
			this.grandTotalCollCustomerCount += avo.getGrandTotalCollCustomerCount();//总储客数
			if(this.oldCustomerVisitCount==null){
				this.oldCustomerVisitCount = 0;
			}
			this.oldCustomerVisitCount += avo.getOldCustomerVisitCount();//老客户接访数
			this.payCount += avo.getPayCount();//今日下定数
			this.confirmHouseMoney += avo.getConfirmHouseMoney();//已下定锁定房源货值（定金确认后的）
			this.signHouseMoney += avo.getSignHouseMoney();//已签约锁定房源货值 
			this.newCustomerVisitCount += avo.getNewCustomerVisitCount();//新客户到访数
			this.validNewCustomerVisitCount += avo.getValidNewCustomerVisitCount();//新客户有效到访数
		}
		this.totalVisitTime = String.valueOf(totalVisitTime);//总接访时间
		this.unValidVisitCount = this.visitCount - this.validVisitCount;//无效接访数
		this.appointReplaceVisitCount = this.replaceCount - this.orderReplaceVisitCount;//指定接访替接数
		this.unValidNewCustomerVisitCount = this.newCustomerVisitCount - this.validNewCustomerVisitCount;//新客户无效到访数
		this.appointValidVisitCount = this.appointCount - this.appointLosedCount;//指定有效接访数
		//每次平均接访时长
		if(this.visitCount>0){
			this.everyOnceAverageReplaceTimeLong = String.valueOf(Integer.valueOf(this.totalVisitTime) / this.visitCount);
		}
		//每日平均接访时长
		if(list.size()>0){
			this.everyDayAverageReplaceTimeLong = String.valueOf(Integer.valueOf(this.totalVisitTime)/list.size());
		}
		//新客户接访时长
		this.newCustomerVisitTime = String.valueOf(newCustomerVisitTime);
		//老客户接访时长
		this.oldCustomerVisitTime = String.valueOf(totalVisitTime - newCustomerVisitTime);
		//替接接访时长
		this.replaceVisitTime = String.valueOf(replaceVisitTime);
		//总接访数
		if(this.visitCount > 0){
			//有效接访率
			double d =  (double)this.validVisitCount / (double)this.visitCount * 100;
			this.validRate = SysContent.get2Double(d);
			
			//老客户通道占比
			double d1 =  (double)this.oldWayVisitCount / (double)this.visitCount * 100;
			this.oldWayRate = SysContent.get2Double(d1);
			
			//老客户接访次数占比
			double d2 =  (double)this.oldCustomerVisitCount / (double)this.visitCount * 100;
			this.oldReceptRate = SysContent.get2Double(d2);
			
			//新客户接访次数占比
			double d3 =  (double)this.newCustomerVisitCount / (double)this.visitCount * 100;
			this.newReceptRate = SysContent.get2Double(d3);
			
			//指定接访率
			double d4 =  (double)this.appointCount / (double)this.visitCount * 100;
			this.appointReceptRate = SysContent.get2Double(d3);
			
			//总替接率
			double d5 =  (double)this.replaceCount / (double)this.visitCount * 100;
			this.totalReplaceReceptRate = SysContent.get2Double(d5);
		}
		
		//新客户通道接访数
		if(this.newWayVisitCount>0){
			//新客户通道有效接访占比
			double d0 =  (double)this.validNewWayVisitCount / (double)this.newWayVisitCount * 100;
			this.newWayValidRate = SysContent.get2Double(d0);
		}
		
		//老客户通道接访数
		if(this.oldWayVisitCount>0){
			//老客户通道有效接访占比
			double d0 =  (double)this.validOldWayVisitCount / (double)this.oldWayVisitCount * 100;
			this.oldWayValidRate = SysContent.get2Double(d0);
		}
		
		//指定接访数
		if(this.appointCount>0){
			//新客户通道指定接访率
			double d0 =  (double)this.appointNewWayVisitCount / (double)this.appointCount * 100;
			this.newWayAppointReceptRate = SysContent.get2Double(d0);
			
			//老客户通道指定接访率
			double d1 =  (double)this.appointOldWayVisitCount / (double)this.appointCount * 100;
			this.oldWayAppointReceptRate = SysContent.get2Double(d1);
			
			//指定无效接访率
			double d2 =  (double)this.appointLosedCount / (double)this.appointCount * 100;
			this.appointUnValidReceptRate = SysContent.get2Double(d2);
			
			//指定有效接访率
			double d3 =  (double)this.appointValidVisitCount / (double)this.appointCount * 100;
			this.appointValidReceptRate = SysContent.get2Double(d3);
			
		}
		
		//总储客数
		if(this.grandTotalCollCustomerCount>0){
			//客户回头率
			double d0 =  (double)this.newTwoVisitCustomerCount / (double)this.grandTotalCollCustomerCount * 100;
			this.customerTurnHandRate = SysContent.get2Double(d0);
			
			//外场导客比
			//double d2 =  (double)this.recordVisitCount / (double)this.grandTotalCollCustomerCount * 100;
			//this.outSideLeadCusRate = SysContent.get2Double(d2);
			
		}
		
		// 总认购数
		if (this.enterBuyCount > 0) {
			// 新增客户认购率
			double d0 = (double) this.newSubscribeCustomerCount / (double) this.enterBuyCount * 100;
			this.newAddCusEnterBuyRate = SysContent.get2Double(d0);

			// 老客户认购率
			double d1 = (double) this.oldSubscribeCustomerCount / (double) this.enterBuyCount * 100;
			this.oldCusEnterBuyRate = SysContent.get2Double(d1);
			
			//认购签约率
			double d2 =  (double)this.signCount / (double)this.enterBuyCount * 100;
			this.enterBuySignRate = SysContent.get2Double(d2);
		}
		/*
		//新增总储客属
//		if(this.newAddCollCustomerCount>0){
//			//新增客户认购率
//			double d0 =  (double)this.newSubscribeCustomerCount / (double)this.newAddCollCustomerCount * 100;
//			this.newAddCusEnterBuyRate = SysContent.get2Double(d0);
//		}
//		
//		//老客户数
//		if(this.grandTotalOldCustomerCount>0){
//			//老客户认购率
//			double d0 =  (double)this.oldSubscribeCustomerCount / (double)this.grandTotalOldCustomerCount * 100;
//			this.oldCusEnterBuyRate = SysContent.get2Double(d0);
//		}
//		
//		//认购总客户数
//		if(this.subscribeCustomerCount>0){
//			//认购签约率
//			double d0 =  (double)this.customerReturnBackVisitNum / (double)this.subscribeCustomerCount * 100;
//			this.enterBuySignRate = SysContent.get2Double(d0);
//		}
		
		//
		if(this.payCount>0){
			//下定签约率
			double d0 =  (double)this.signCount / (double)this.payCount * 100;
			this.paySignRate = SysContent.get2Double(d0);
		}
		
		if(this.newAddCollCustomerCount+this.losedCount>0){
			//来访成交比 = 签约新客户数/新增储客+流失数
			double d6 =  (double)this.newCustomerSignedCount / (double)(this.newAddCollCustomerCount+this.losedCount) * 100;
			this.visitDealRate = SysContent.get2Double(d6);

		}
		
		// 新增储客数
		if (this.newAddCollCustomerCount > 0) {
			// 储客成交比
			double d1 = (double) this.momeryCuDealCount / (double) this.newAddCollCustomerCount * 100;
			this.grandCusDealRate = SysContent.get2Double(d1);

		}
		return this;
	}**/
	
	public String getCvDate() {
		return cvDate;
	}
	public void setCvDate(String cvDate) {
		this.cvDate = cvDate;
	}
	public String getCollDateTime() {
		return collDateTime;
	}
	public void setCollDateTime(String collDateTime) {
		this.collDateTime = collDateTime;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getAgentPhone() {
		return agentPhone;
	}
	public void setAgentPhone(String agentPhone) {
		this.agentPhone = agentPhone;
	}
	public Integer getAgentStatus() {
		return agentStatus;
	}
	public void setAgentStatus(Integer agentStatus) {
		this.agentStatus = agentStatus;
	}
	public Integer getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}
	public Integer getNewVisitCount() {
		return newVisitCount;
	}
	public void setNewVisitCount(Integer newVisitCount) {
		this.newVisitCount = newVisitCount;
	}
	public Integer getSecondVisitCount() {
		return secondVisitCount;
	}
	public void setSecondVisitCount(Integer secondVisitCount) {
		this.secondVisitCount = secondVisitCount;
	}
	public Integer getMoreVisitCount() {
		return moreVisitCount;
	}
	public void setMoreVisitCount(Integer moreVisitCount) {
		this.moreVisitCount = moreVisitCount;
	}
	public Integer getAppointCount() {
		return appointCount;
	}
	public void setAppointCount(Integer appointCount) {
		this.appointCount = appointCount;
	}
	public Integer getNewWayVisitCount() {
		return newWayVisitCount;
	}
	public void setNewWayVisitCount(Integer newWayVisitCount) {
		this.newWayVisitCount = newWayVisitCount;
	}
	public Integer getOldWayVisitCount() {
		return oldWayVisitCount;
	}
	public Integer getAffirmOldCustomerVisitNum() {
		return affirmOldCustomerVisitNum;
	}
	public void setAffirmOldCustomerVisitNum(Integer affirmOldCustomerVisitNum) {
		this.affirmOldCustomerVisitNum = affirmOldCustomerVisitNum;
	}
	public void setOldWayVisitCount(Integer oldWayVisitCount) {
		this.oldWayVisitCount = oldWayVisitCount;
	}
	public Integer getReplaceCount() {
		return replaceCount;
	}
	public void setReplaceCount(Integer replaceCount) {
		this.replaceCount = replaceCount;
	}
	public String getVisitId() {
		return visitId;
	}
	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}
	public Integer getEnterBuyCount() {
		return enterBuyCount;
	}
	public void setEnterBuyCount(Integer enterBuyCount) {
		this.enterBuyCount = enterBuyCount;
	}
	public Integer getAgreeEnterCount() {
		return agreeEnterCount;
	}
	public void setAgreeEnterCount(Integer agreeEnterCount) {
		this.agreeEnterCount = agreeEnterCount;
	}
	public Integer getRefuseEnterCount() {
		return refuseEnterCount;
	}
	public void setRefuseEnterCount(Integer refuseEnterCount) {
		this.refuseEnterCount = refuseEnterCount;
	}
	public Integer getPayCount() {
		return payCount;
	}
	public void setPayCount(Integer payCount) {
		this.payCount = payCount;
	}
	public Integer getSignCount() {
		return signCount;
	}
	public void setSignCount(Integer signCount) {
		this.signCount = signCount;
	}
	public Integer getRevokeOrderCount() {
		return revokeOrderCount;
	}
	public void setRevokeOrderCount(Integer revokeOrderCount) {
		this.revokeOrderCount = revokeOrderCount;
	}
	public String getSignedOrderId() {
		return signedOrderId;
	}
	public void setSignedOrderId(String signedOrderId) {
		this.signedOrderId = signedOrderId;
	}
	public String getRefusedOrderId() {
		return refusedOrderId;
	}
	public void setRefusedOrderId(String refusedOrderId) {
		this.refusedOrderId = refusedOrderId;
	}
	public String getRevokeOrderId() {
		return revokeOrderId;
	}
	public void setRevokeOrderId(String revokeOrderId) {
		this.revokeOrderId = revokeOrderId;
	}
	public String getPayOrderId() {
		return payOrderId;
	}
	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}
	public Integer getNewAddCollCustomerCount() {
		return newAddCollCustomerCount;
	}
	public void setNewAddCollCustomerCount(Integer newAddCollCustomerCount) {
		this.newAddCollCustomerCount = newAddCollCustomerCount;
	}
	public Integer getGrandTotalCollCustomerCount() {
		return grandTotalCollCustomerCount;
	}
	public void setGrandTotalCollCustomerCount(Integer grandTotalCollCustomerCount) {
		this.grandTotalCollCustomerCount = grandTotalCollCustomerCount;
	}
	public Integer getGrandTotalOldCustomerCount() {
		return grandTotalOldCustomerCount;
	}
	public void setGrandTotalOldCustomerCount(Integer grandTotalOldCustomerCount) {
		this.grandTotalOldCustomerCount = grandTotalOldCustomerCount;
	}
	public String getNewAddCollCustomerId() {
		return newAddCollCustomerId;
	}
	public void setNewAddCollCustomerId(String newAddCollCustomerId) {
		this.newAddCollCustomerId = newAddCollCustomerId;
	}
	public String getGrandTotalCollCustomerId() {
		return grandTotalCollCustomerId;
	}
	public void setGrandTotalCollCustomerId(String grandTotalCollCustomerId) {
		this.grandTotalCollCustomerId = grandTotalCollCustomerId;
	}
	public String getGrandTotalOldCustomerId() {
		return grandTotalOldCustomerId;
	}
	public void setGrandTotalOldCustomerId(String grandTotalOldCustomerId) {
		this.grandTotalOldCustomerId = grandTotalOldCustomerId;
	}
	public String getSignInTime() {
		return signInTime;
	}
	public void setSignInTime(String signInTime) {
		this.signInTime = signInTime;
	}
	public String getSignOutTime() {
		return signOutTime;
	}
	public void setSignOutTime(String signOutTime) {
		this.signOutTime = signOutTime;
	}
	public String getReserveField() {
		return reserveField;
	}
	public void setReserveField(String reserveField) {
		this.reserveField = reserveField;
	}
	public Integer getValidVisitCount() {
		return validVisitCount;
	}
	public void setValidVisitCount(Integer validVisitCount) {
		this.validVisitCount = validVisitCount;
	}
	public Integer getValidNewWayVisitCount() {
		return validNewWayVisitCount;
	}
	public void setValidNewWayVisitCount(Integer validNewWayVisitCount) {
		this.validNewWayVisitCount = validNewWayVisitCount;
	}
	public Integer getAppointNewWayVisitCount() {
		return appointNewWayVisitCount;
	}
	public void setAppointNewWayVisitCount(Integer appointNewWayVisitCount) {
		this.appointNewWayVisitCount = appointNewWayVisitCount;
	}
	public Integer getValidOldWayVisitCount() {
		return validOldWayVisitCount;
	}
	public void setValidOldWayVisitCount(Integer validOldWayVisitCount) {
		this.validOldWayVisitCount = validOldWayVisitCount;
	}
	public Integer getAppointOldWayVisitCount() {
		return appointOldWayVisitCount;
	}
	public void setAppointOldWayVisitCount(Integer appointOldWayVisitCount) {
		this.appointOldWayVisitCount = appointOldWayVisitCount;
	}
	public Integer getOrderReplaceVisitCount() {
		return orderReplaceVisitCount;
	}
	public void setOrderReplaceVisitCount(Integer orderReplaceVisitCount) {
		this.orderReplaceVisitCount = orderReplaceVisitCount;
	}
	public Integer getAppointLosedCount() {
		return appointLosedCount;
	}
	public void setAppointLosedCount(Integer appointLosedCount) {
		this.appointLosedCount = appointLosedCount;
	}
	public Integer getLosedCount() {
		return losedCount;
	}
	public void setLosedCount(Integer losedCount) {
		this.losedCount = losedCount;
	}
	public Integer getNewAppointCount() {
		return newAppointCount;
	}
	public void setNewAppointCount(Integer newAppointCount) {
		this.newAppointCount = newAppointCount;
	}
	public String getNewCustomerVisitTime() {
		return newCustomerVisitTime;
	}
	public void setNewCustomerVisitTime(String newCustomerVisitTime) {
		this.newCustomerVisitTime = newCustomerVisitTime;
	}
	public String getOldCustomerVisitTime() {
		return oldCustomerVisitTime;
	}
	public void setOldCustomerVisitTime(String oldCustomerVisitTime) {
		this.oldCustomerVisitTime = oldCustomerVisitTime;
	}
	public String getReplaceVisitTime() {
		return replaceVisitTime;
	}
	public void setReplaceVisitTime(String replaceVisitTime) {
		this.replaceVisitTime = replaceVisitTime;
	}
	public String getTotalVisitTime() {
		return totalVisitTime;
	}
	public void setTotalVisitTime(String totalVisitTime) {
		this.totalVisitTime = totalVisitTime;
	}
	public String getAverageVisitTime() {
		return averageVisitTime;
	}
	public void setAverageVisitTime(String averageVisitTime) {
		this.averageVisitTime = averageVisitTime;
	}
	public Double getConfirmHouseMoney() {
		return confirmHouseMoney;
	}
	public void setConfirmHouseMoney(Double confirmHouseMoney) {
		this.confirmHouseMoney = confirmHouseMoney;
	}
	public Integer getSubscribeHouseCount() {
		return subscribeHouseCount;
	}
	public void setSubscribeHouseCount(Integer subscribeHouseCount) {
		this.subscribeHouseCount = subscribeHouseCount;
	}
	public Double getSubscribeMoney() {
		return subscribeMoney;
	}
	public void setSubscribeMoney(Double subscribeMoney) {
		this.subscribeMoney = subscribeMoney;
	}
	public Double getSubscribeGetMoney() {
		return subscribeGetMoney;
	}
	public void setSubscribeGetMoney(Double subscribeGetMoney) {
		this.subscribeGetMoney = subscribeGetMoney;
	}
	public Double getSubscribeLockHouseMoney() {
		return subscribeLockHouseMoney;
	}
	public void setSubscribeLockHouseMoney(Double subscribeLockHouseMoney) {
		this.subscribeLockHouseMoney = subscribeLockHouseMoney;
	}
	public Integer getGiveUpSignCount() {
		return giveUpSignCount;
	}
	public void setGiveUpSignCount(Integer giveUpSignCount) {
		this.giveUpSignCount = giveUpSignCount;
	}
	public Integer getWaitSignCount() {
		return waitSignCount;
	}
	public void setWaitSignCount(Integer waitSignCount) {
		this.waitSignCount = waitSignCount;
	}
	public Double getSignHouseMoney() {
		return signHouseMoney;
	}
	public void setSignHouseMoney(Double signHouseMoney) {
		this.signHouseMoney = signHouseMoney;
	}
	public Double getGiveUpHouseMoney() {
		return giveUpHouseMoney;
	}
	public void setGiveUpHouseMoney(Double giveUpHouseMoney) {
		this.giveUpHouseMoney = giveUpHouseMoney;
	}
	public Double getWaitSignHouseMoney() {
		return waitSignHouseMoney;
	}
	public void setWaitSignHouseMoney(Double waitSignHouseMoney) {
		this.waitSignHouseMoney = waitSignHouseMoney;
	}
	public Integer getNewTwoVisitCustomerCount() {
		return newTwoVisitCustomerCount;
	}
	public void setNewTwoVisitCustomerCount(Integer newTwoVisitCustomerCount) {
		this.newTwoVisitCustomerCount = newTwoVisitCustomerCount;
	}
	public Integer getSubscribeCustomerCount() {
		return subscribeCustomerCount;
	}
	public void setSubscribeCustomerCount(Integer subscribeCustomerCount) {
		this.subscribeCustomerCount = subscribeCustomerCount;
	}
	public Integer getNewSubscribeCustomerCount() {
		return newSubscribeCustomerCount;
	}
	public void setNewSubscribeCustomerCount(Integer newSubscribeCustomerCount) {
		this.newSubscribeCustomerCount = newSubscribeCustomerCount;
	}
	public Integer getOldSubscribeCustomerCount() {
		return oldSubscribeCustomerCount;
	}
	public void setOldSubscribeCustomerCount(Integer oldSubscribeCustomerCount) {
		this.oldSubscribeCustomerCount = oldSubscribeCustomerCount;
	}
	
	
	public Integer getPlatformCustomerCount() {
		return platformCustomerCount;
	}
	public void setPlatformCustomerCount(Integer platformCustomerCount) {
		this.platformCustomerCount = platformCustomerCount;
	}
	public Integer getCustomerReturnBackVisitNum() {
		return customerReturnBackVisitNum;
	}
	public void setCustomerReturnBackVisitNum(Integer customerReturnBackVisitNum) {
		this.customerReturnBackVisitNum = customerReturnBackVisitNum;
	}
	public Integer getVisitToDealCount() {
		return visitToDealCount;
	}
	public void setVisitToDealCount(Integer visitToDealCount) {
		this.visitToDealCount = visitToDealCount;
	}
	public Integer getTotalCustomerCount() {
		return totalCustomerCount;
	}
	public void setTotalCustomerCount(Integer totalCustomerCount) {
		this.totalCustomerCount = totalCustomerCount;
	}
	public Integer getTotalOldCustomerCount() {
		return totalOldCustomerCount;
	}
	public void setTotalOldCustomerCount(Integer totalOldCustomerCount) {
		this.totalOldCustomerCount = totalOldCustomerCount;
	}
	public Integer getNewCustomerSignedCount() {
		return newCustomerSignedCount;
	}
	public void setNewCustomerSignedCount(Integer newCustomerSignedCount) {
		this.newCustomerSignedCount = newCustomerSignedCount;
	}
	
	public Integer getNewCustomerVisitCount() {
		return newCustomerVisitCount;
	}
	public void setNewCustomerVisitCount(Integer newCustomerVisitCount) {
		this.newCustomerVisitCount = newCustomerVisitCount;
	}
	public Integer getValidNewCustomerVisitCount() {
		return validNewCustomerVisitCount;
	}
	public void setValidNewCustomerVisitCount(Integer validNewCustomerVisitCount) {
		this.validNewCustomerVisitCount = validNewCustomerVisitCount;
	}
	public Integer getOldCustomerVisitCount() {
		return oldCustomerVisitCount;
	}
	public void setOldCustomerVisitCount(Integer oldCustomerVisitCount) {
		this.oldCustomerVisitCount = oldCustomerVisitCount;
	}
	public Integer getValidOldCustomerVisitCount() {
		return validOldCustomerVisitCount;
	}
	public void setValidOldCustomerVisitCount(Integer validOldCustomerVisitCount) {
		this.validOldCustomerVisitCount = validOldCustomerVisitCount;
	}
	public Integer getOldCustomerSignedCount() {
		return oldCustomerSignedCount;
	}
	public void setOldCustomerSignedCount(Integer oldCustomerSignedCount) {
		this.oldCustomerSignedCount = oldCustomerSignedCount;
	}
	public Integer getUnValidVisitCount() {
		return unValidVisitCount;
	}
	public void setUnValidVisitCount(Integer unValidVisitCount) {
		this.unValidVisitCount = unValidVisitCount;
	}
	public Integer getAppointReplaceVisitCount() {
		return appointReplaceVisitCount;
	}
	public void setAppointReplaceVisitCount(Integer appointReplaceVisitCount) {
		this.appointReplaceVisitCount = appointReplaceVisitCount;
	}
	public String getEveryOnceAverageReplaceTimeLong() {
		return everyOnceAverageReplaceTimeLong;
	}
	public void setEveryOnceAverageReplaceTimeLong(String everyOnceAverageReplaceTimeLong) {
		this.everyOnceAverageReplaceTimeLong = everyOnceAverageReplaceTimeLong;
	}
	public String getEveryDayAverageReplaceTimeLong() {
		return everyDayAverageReplaceTimeLong;
	}
	public void setEveryDayAverageReplaceTimeLong(String everyDayAverageReplaceTimeLong) {
		this.everyDayAverageReplaceTimeLong = everyDayAverageReplaceTimeLong;
	}
	public String getValidRate() {
		return validRate;
	}
	public void setValidRate(String validRate) {
		this.validRate = validRate;
	}
	public String getOldWayRate() {
		return oldWayRate;
	}
	public void setOldWayRate(String oldWayRate) {
		this.oldWayRate = oldWayRate;
	}
	public String getNewWayValidRate() {
		return newWayValidRate;
	}
	public void setNewWayValidRate(String newWayValidRate) {
		this.newWayValidRate = newWayValidRate;
	}
	public String getOldReceptRate() {
		return oldReceptRate;
	}
	public void setOldReceptRate(String oldReceptRate) {
		this.oldReceptRate = oldReceptRate;
	}
	public String getNewReceptRate() {
		return newReceptRate;
	}
	public void setNewReceptRate(String newReceptRate) {
		this.newReceptRate = newReceptRate;
	}
	public String getAppointReceptRate() {
		return appointReceptRate;
	}
	public void setAppointReceptRate(String appointReceptRate) {
		this.appointReceptRate = appointReceptRate;
	}
	public String getNewWayAppointReceptRate() {
		return newWayAppointReceptRate;
	}
	public void setNewWayAppointReceptRate(String newWayAppointReceptRate) {
		this.newWayAppointReceptRate = newWayAppointReceptRate;
	}
	public String getOldWayAppointReceptRate() {
		return oldWayAppointReceptRate;
	}
	public void setOldWayAppointReceptRate(String oldWayAppointReceptRate) {
		this.oldWayAppointReceptRate = oldWayAppointReceptRate;
	}
	public String getAppointUnValidReceptRate() {
		return appointUnValidReceptRate;
	}
	public void setAppointUnValidReceptRate(String appointUnValidReceptRate) {
		this.appointUnValidReceptRate = appointUnValidReceptRate;
	}
	public String getTotalReplaceReceptRate() {
		return totalReplaceReceptRate;
	}
	public void setTotalReplaceReceptRate(String totalReplaceReceptRate) {
		this.totalReplaceReceptRate = totalReplaceReceptRate;
	}
	public String getCustomerTurnHandRate() {
		return customerTurnHandRate;
	}
	public void setCustomerTurnHandRate(String customerTurnHandRate) {
		this.customerTurnHandRate = customerTurnHandRate;
	}
	public String getNewAddCusEnterBuyRate() {
		return newAddCusEnterBuyRate;
	}
	public void setNewAddCusEnterBuyRate(String newAddCusEnterBuyRate) {
		this.newAddCusEnterBuyRate = newAddCusEnterBuyRate;
	}
	public String getOldCusEnterBuyRate() {
		return oldCusEnterBuyRate;
	}
	public void setOldCusEnterBuyRate(String oldCusEnterBuyRate) {
		this.oldCusEnterBuyRate = oldCusEnterBuyRate;
	}
	public String getVisitDealRate() {
		return visitDealRate;
	}
	public void setVisitDealRate(String visitDealRate) {
		this.visitDealRate = visitDealRate;
	}
	public String getGrandCusDealRate() {
		return grandCusDealRate;
	}
	public void setGrandCusDealRate(String grandCusDealRate) {
		this.grandCusDealRate = grandCusDealRate;
	}
	public String getEnterBuySignRate() {
		return enterBuySignRate;
	}
	public void setEnterBuySignRate(String enterBuySignRate) {
		this.enterBuySignRate = enterBuySignRate;
	}

	
	public Integer getVisitAgain() {
		return visitAgain;
	}
	public void setVisitAgain(Integer visitAgain) {
		this.visitAgain = visitAgain;
	}
	public Integer getUnValidNewCustomerVisitCount() {
		return unValidNewCustomerVisitCount;
	}
	public void setUnValidNewCustomerVisitCount(Integer unValidNewCustomerVisitCount) {
		this.unValidNewCustomerVisitCount = unValidNewCustomerVisitCount;
	}

	public Integer getAppointValidVisitCount() {
		return appointValidVisitCount;
	}


	public void setAppointValidVisitCount(Integer appointValidVisitCount) {
		this.appointValidVisitCount = appointValidVisitCount;
	}


	public String getAppointValidReceptRate() {
		return appointValidReceptRate;
	}


	public void setAppointValidReceptRate(String appointValidReceptRate) {
		this.appointValidReceptRate = appointValidReceptRate;
	}

	public String getPaySignRate() {
		return paySignRate;
	}


	public void setPaySignRate(String paySignRate) {
		this.paySignRate = paySignRate;
	}

	public String getOldWayValidRate() {
		return oldWayValidRate;
	}


	public void setOldWayValidRate(String oldWayValidRate) {
		this.oldWayValidRate = oldWayValidRate;
	}


	public Integer getMomeryCuDealCount() {
		return momeryCuDealCount;
	}


	public void setMomeryCuDealCount(Integer momeryCuDealCount) {
		this.momeryCuDealCount = momeryCuDealCount;
	}


	@Override
	public String toString() {
		return "AgentVisitOrder [cvDate=" + cvDate + ", collDateTime=" + collDateTime + ", agentId=" + agentId
				+ ", agentName=" + agentName + ", agentPhone=" + agentPhone + ", agentStatus=" + agentStatus
				+ ", visitCount=" + visitCount + ", validVisitCount=" + validVisitCount + ", newVisitCount="
				+ newVisitCount + ", secondVisitCount=" + secondVisitCount + ", moreVisitCount=" + moreVisitCount
				+ ", appointCount=" + appointCount + ", newWayVisitCount=" + newWayVisitCount
				+ ", validNewWayVisitCount=" + validNewWayVisitCount + ", appointNewWayVisitCount="
				+ appointNewWayVisitCount + ", oldWayVisitCount=" + oldWayVisitCount + ", validOldWayVisitCount="
				+ validOldWayVisitCount + ", appointOldWayVisitCount=" + appointOldWayVisitCount + ", replaceCount="
				+ replaceCount + ", orderReplaceVisitCount=" + orderReplaceVisitCount + ", affirmOldCustomerVisitNum="
				+ affirmOldCustomerVisitNum + ", appointLosedCount=" + appointLosedCount + ", losedCount=" + losedCount
				+ ", newAppointCount=" + newAppointCount + ", newCustomerVisitTime=" + newCustomerVisitTime
				+ ", oldCustomerVisitTime=" + oldCustomerVisitTime + ", replaceVisitTime=" + replaceVisitTime
				+ ", totalVisitTime=" + totalVisitTime + ", averageVisitTime=" + averageVisitTime + ", visitId="
				+ visitId + ", enterBuyCount=" + enterBuyCount + ", agreeEnterCount=" + agreeEnterCount
				+ ", refuseEnterCount=" + refuseEnterCount + ", payCount=" + payCount + ", signCount=" + signCount
				+ ", revokeOrderCount=" + revokeOrderCount + ", confirmHouseMoney=" + confirmHouseMoney
				+ ", subscribeHouseCount=" + subscribeHouseCount + ", subscribeMoney=" + subscribeMoney
				+ ", subscribeGetMoney=" + subscribeGetMoney + ", subscribeLockHouseMoney=" + subscribeLockHouseMoney
				+ ", giveUpSignCount=" + giveUpSignCount + ", waitSignCount=" + waitSignCount + ", signHouseMoney="
				+ signHouseMoney + ", giveUpHouseMoney=" + giveUpHouseMoney + ", waitSignHouseMoney="
				+ waitSignHouseMoney + ", signedOrderId=" + signedOrderId + ", refusedOrderId=" + refusedOrderId
				+ ", revokeOrderId=" + revokeOrderId + ", payOrderId=" + payOrderId + ", newAddCollCustomerCount="
				+ newAddCollCustomerCount + ", grandTotalCollCustomerCount=" + grandTotalCollCustomerCount
				+ ", grandTotalOldCustomerCount=" + grandTotalOldCustomerCount + ", newTwoVisitCustomerCount="
				+ newTwoVisitCustomerCount + ", customerReturnBackVisitNum=" + customerReturnBackVisitNum
				+ ", platformCustomerCount=" + platformCustomerCount + ", subscribeCustomerCount="
				+ subscribeCustomerCount + ", newSubscribeCustomerCount=" + newSubscribeCustomerCount
				+ ", oldSubscribeCustomerCount=" + oldSubscribeCustomerCount + ", visitToDealCount=" + visitToDealCount
				+ ", newAddCollCustomerId=" + newAddCollCustomerId + ", grandTotalCollCustomerId="
				+ grandTotalCollCustomerId + ", grandTotalOldCustomerId=" + grandTotalOldCustomerId + ", signInTime="
				+ signInTime + ", signOutTime=" + signOutTime + ", reserveField=" + reserveField + "]";
	}

	
	
	
	
	

	
}
