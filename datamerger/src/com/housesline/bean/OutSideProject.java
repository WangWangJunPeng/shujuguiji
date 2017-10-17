package com.housesline.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.housesline.dao.BaseDao;
import com.housesline.service.project.ProjectCreateService;
import com.housesline.utils.DateUtil;
import com.housesline.utils.SysContent;

public class OutSideProject {
	//使用copDate作为主键
	//日期 格式 yyyy-MM-dd
	private String copDate;
	//归集操作时间 yyyy-MM-dd HH:mm:ss
	private String collDateTime;
	//带看数(不使用该字段)
	private Integer guideCount;
	//案场到访总数
	private Integer visitedCount;
	//备案组数
	private Integer recordCustomerCount;
	//备案到访数
	private Integer recordVisitCount;
	//备案未到访数
	private Integer recordNotVisitCount;
	//带看认购数
	private Integer guiCustomerRecordCount;
	//分销认购数
	private Integer outSideCustomerRecordCount;
	
	
	//分销同意认购数
	private Integer outSideAgreeRecordCount;
	//分销拒绝认购数
	private Integer outSideRefuseRecordCount;
	//分销待签约数(下定)
	private Integer outSideWaitSignCount;
	//分销撤单数
	private Integer outSideRevokeOrderCount;
	//分销认购套数
	private Integer outSideSubscribeHouseCount;
	//分销认购金额
	private Double outSideSubscribeMoney;
	//分销认购到款金额
	private Double outSideSubscribeGetMoney;
	//分销认购锁定房源货值
	private Double outSideSubscribeLockHouseMoney;
	//分销撤单房源货值
	private Double outSideGiveUpHouseMoney;
	//分销等待签约房源货值
	private Double outSideWaitSignHouseMoney;
	//签约房源货值
	private Double outSideSignHouseMoney;
	
	//分销认购总客户数
	private Integer outSideRecordCuCount;
	//分销新客户认购数
	private Integer outSideNewCuRecordCount;
	//分销老客户认购数
	private Integer outSideOldCuRecordCount;
	//签约总客户数
	private Integer outSideSignCuCount;
	//分销新客户签约数
	private Integer outSideNewCuSignCount;
	//分销老客户签约数
	private Integer outSideOldSignCount;
	
	
	
	
	//带看成交数(中介备案，有效到访，置业顾问发起)签约的
	private Integer guideCustomerVisitCount;
	//分销成交数(中介备案，中介发起)外场成交 签约的
	private Integer outSideDealCount;
	//平台客户签约数 = 带看成交数 + 分销成交数
	private Integer systemCusSignedCount;
	//内场成交数(中介无备案，置业顾问发起)
	private Integer intFiledToDealNum;
	//总成交数 = 分销成交数（外场成交数） + 内场成交数
	private Integer totalDealCount;
	//集合字符串，带看房源的id
	private String guidedHouseNum;
	//集合字符串，到访房源的id
	private String visitedHouseNum;
	//集合字符串，带看门店的id
	private String guidedShopId;
	//集合字符串，带看门店的中介的id
	private String guidedShopAgentId;
	//集合字符串，到访的门店的id
	private String visitedShopId;
	//集合字符串，到访的门店中介的id
	private String visitedShopAgentId;
	//预留字段
	private String reserveField;
	
	public OutSideProject() {
		super();
		this.guideCount = 0;//带看数(不使用该字段)
		this.visitedCount = 0;//案场到访总数
		this.recordCustomerCount = 0;//备案组数
		this.recordVisitCount = 0;//备案到访数
		this.recordNotVisitCount = 0;//备案未到访数
		this.guiCustomerRecordCount = 0;//带看认购数
		this.outSideCustomerRecordCount = 0;//分销认购数
		this.guideCustomerVisitCount = 0;//带看成交数(中介备案，有效到访，置业顾问发起)
		this.outSideDealCount = 0;//分销成交数(中介备案，中介发起)外场成交
		this.intFiledToDealNum = 0;//内场成交数(中介无备案，置业顾问发起)
		this.totalDealCount = 0;//总成交数 = 分销成交数（外场成交数） + 内场成交数
		this.systemCusSignedCount = 0;//平台客户签约数 = 带看成交数 + 分销成交数
		this.outSideAgreeRecordCount = 0;//分销同意认购数
		this.outSideRefuseRecordCount = 0;//分销拒绝认购数
		this.outSideWaitSignCount = 0;//分销待签约数(下定)
		this.outSideRevokeOrderCount = 0;//分销撤单数
		this.outSideSubscribeHouseCount = 0;//分销认购套数
		this.outSideSubscribeMoney = 0d;//分销认购金额
		this.outSideSubscribeGetMoney = 0d;//分销认购到款金额
		this.outSideSubscribeLockHouseMoney = 0d;//分销认购锁定房源货值
		this.outSideGiveUpHouseMoney = 0d;//分销撤单房源货值
		this.outSideWaitSignHouseMoney = 0d;//分销等待签约房源货值
		this.outSideSignHouseMoney = 0d;//签约房源货值
		this.outSideRecordCuCount = 0;//分销认购总客户数
		this.outSideNewCuRecordCount = 0;//分销新客户认购数
		this.outSideOldCuRecordCount = 0;//分销老客户认购数
		this.outSideSignCuCount = 0;//签约总客户数
		this.outSideNewCuSignCount = 0;//分销新客户签约数
		this.outSideOldSignCount = 0;//分销老客户签约数
	}


	/**工具预留字段**/
	private String grVisiteRate = "0.00";
	private String outSideDealRate = "0.00";
	private String sysCusSignedRate = "0.00";
	private String systemEnterBuyRate = "0.00";
	private String outSideLeadCusRate = "0.00";
	
	
	
	
	/**
	 * 数据封装工具
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @param baseDao
	 * @return
	 */
	public OutSideProject creatMoreObjTurnOne(String proId, String startDate, String endDate, BaseDao baseDao, ProjectCreateService projectCreateService) {
		return creatMoreObjTurnOne(proId, startDate, endDate, baseDao, null, projectCreateService);
	}
	
	
	/**
	 * 数据封装工具
	 * cdh
	 * @param proId
	 * @param baseDao
	 * @param oss
	 * @return
	 */
	public OutSideProject creatMoreObjTurnOne(String proId, BaseDao baseDao, List<OutSideProject> oss, ProjectCreateService projectCreateService) {
		
		return creatMoreObjTurnOne(proId, null, null, baseDao, oss, projectCreateService);
	}
	
	/**
	 * 数据封装工具
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @param baseDao
	 * @return
	 */
	public OutSideProject creatMoreObjTurnOne(String proId, String startDate, String endDate, BaseDao baseDao, List<OutSideProject> oss, ProjectCreateService projectCreateService) {

		List<OutSideProject> list = new ArrayList<>();
		
		String thisDate = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		boolean flag = false;
		if(startDate.equals(endDate)){
			if(thisDate.equals(startDate)){
				flag = true;
			}
		}
		
		
		
		
		if(flag){
			list.add(projectCreateService.selectOutSideProject(proId, thisDate));
		}else{
			
			if(oss == null){
				if(startDate!=null && !startDate.equals("") && endDate!=null && !endDate.equals("")){
					startDate = DateUtil.format(DateUtil.parse(startDate, DateUtil.PATTERN_CLASSICAL_SIMPLE), DateUtil.PATTERN_CLASSICAL_SIMPLE);
					endDate = DateUtil.format(DateUtil.parse(endDate, DateUtil.PATTERN_CLASSICAL_SIMPLE), DateUtil.PATTERN_CLASSICAL_SIMPLE);
					list = baseDao.selectOutSideProjectForProject(proId, startDate, endDate, null);
				}else{
					list = baseDao.selectOutSideProjectForProject(proId, null, null, DateUtil.format(new Date(),DateUtil.PATTERN_CLASSICAL_SIMPLE));
				}
			}else{
				list = oss;
			}
		}
		
		for(OutSideProject osp : list){
			this.guideCount += osp.getGuideCount();//带看数(不使用该字段)
			this.visitedCount += osp.getVisitedCount();//案场到访总数
			this.recordCustomerCount += osp.getRecordCustomerCount();//备案组数
			this.recordVisitCount += osp.getRecordVisitCount();//备案到访数
			this.recordNotVisitCount += osp.getRecordNotVisitCount();//备案未到访数
			this.guideCustomerVisitCount += osp.getGuideCustomerVisitCount();//带看成交数(中介备案，有效到访，置业顾问发起)
			this.outSideDealCount += osp.getOutSideDealCount();//分销成交数(中介备案，中介发起)外场成交
			this.intFiledToDealNum += osp.getIntFiledToDealNum();//内场成交数(中介无备案，置业顾问发起)
			this.outSideCustomerRecordCount += osp.getOutSideCustomerRecordCount();//分销认购数
			this.outSideAgreeRecordCount += osp.getOutSideAgreeRecordCount();//分销同意认购数
			this.outSideRefuseRecordCount += osp.getOutSideRefuseRecordCount();//分销拒绝认购数
			this.outSideWaitSignCount += osp.getOutSideWaitSignCount();//分销待签约数(下定)
			this.outSideRevokeOrderCount += osp.getOutSideRevokeOrderCount();//分销撤单数
			this.outSideSubscribeHouseCount += osp.getOutSideSubscribeHouseCount();//分销认购套数
			this.outSideSubscribeMoney += osp.getOutSideSubscribeMoney();//分销认购金额
			this.outSideSubscribeGetMoney += osp.getOutSideSubscribeGetMoney();//分销认购到款金额
			this.outSideSubscribeLockHouseMoney += osp.getOutSideSubscribeLockHouseMoney();//分销认购锁定房源货值
			this.outSideGiveUpHouseMoney += osp.getOutSideGiveUpHouseMoney();//分销撤单房源货值
			this.outSideWaitSignHouseMoney += osp.getOutSideWaitSignHouseMoney();//分销等待签约房源货值
			this.outSideSignHouseMoney += osp.getOutSideSignHouseMoney();//签约房源货值
			this.outSideRecordCuCount += osp.getOutSideRecordCuCount();//分销认购总客户数
			this.outSideNewCuRecordCount += osp.getOutSideNewCuRecordCount();//分销新客户认购数
			this.outSideOldCuRecordCount += osp.getOutSideOldCuRecordCount();//分销老客户认购数
			this.outSideSignCuCount += osp.getOutSideSignCuCount();//签约总客户数
			this.outSideNewCuSignCount += osp.getOutSideNewCuSignCount();//分销新客户签约数
			this.outSideOldSignCount += osp.getOutSideOldSignCount();//分销老客户签约数
			
		}
		this.totalDealCount = this.intFiledToDealNum + this.outSideDealCount + this.guideCustomerVisitCount;//总成交数 = 分销成交数（外场成交数） + 内场成交数
		this.systemCusSignedCount = this.guideCustomerVisitCount + this.outSideDealCount;//平台客户签约数（成交数）
		//报备总数
		if(this.recordCustomerCount>0){
			//报备到访率
			double d =  (double)this.recordVisitCount / (double)this.recordCustomerCount * 100;
			this.grVisiteRate = SysContent.get2Double(d);
		}
		
		//成交总数
		if(totalDealCount > 0){
			//外场成交比
			double d =  (double)this.outSideDealCount / (double)this.totalDealCount * 100;
			this.outSideDealRate = SysContent.get2Double(d);
			
			//平台客户认购占比
			double d0 =  (double)this.outSideCustomerRecordCount / (double)this.totalDealCount * 100;
			this.systemEnterBuyRate = SysContent.get2Double(d0);
			
			//平台客户签约比
			double d1 =  (double)this.systemCusSignedCount / (double)this.totalDealCount * 100;
			this.sysCusSignedRate = SysContent.get2Double(d1);
		}
		return this;
	}
	
	
	public String getCopDate() {
		return copDate;
	}
	public void setCopDate(String copDate) {
		this.copDate = copDate;
	}
	public String getCollDateTime() {
		return collDateTime;
	}
	public void setCollDateTime(String collDateTime) {
		this.collDateTime = collDateTime;
	}
	public Integer getGuideCount() {
		return guideCount;
	}
	public void setGuideCount(Integer guideCount) {
		this.guideCount = guideCount;
	}
	public Integer getVisitedCount() {
		return visitedCount;
	}
	public void setVisitedCount(Integer visitedCount) {
		this.visitedCount = visitedCount;
	}
	public String getGuidedHouseNum() {
		return guidedHouseNum;
	}
	public void setGuidedHouseNum(String guidedHouseNum) {
		this.guidedHouseNum = guidedHouseNum;
	}
	public String getVisitedHouseNum() {
		return visitedHouseNum;
	}
	public void setVisitedHouseNum(String visitedHouseNum) {
		this.visitedHouseNum = visitedHouseNum;
	}
	public String getGuidedShopId() {
		return guidedShopId;
	}
	public void setGuidedShopId(String guidedShopId) {
		this.guidedShopId = guidedShopId;
	}
	public String getGuidedShopAgentId() {
		return guidedShopAgentId;
	}
	public void setGuidedShopAgentId(String guidedShopAgentId) {
		this.guidedShopAgentId = guidedShopAgentId;
	}
	public String getVisitedShopId() {
		return visitedShopId;
	}
	public void setVisitedShopId(String visitedShopId) {
		this.visitedShopId = visitedShopId;
	}
	public String getVisitedShopAgentId() {
		return visitedShopAgentId;
	}
	public void setVisitedShopAgentId(String visitedShopAgentId) {
		this.visitedShopAgentId = visitedShopAgentId;
	}
	public String getReserveField() {
		return reserveField;
	}
	public void setReserveField(String reserveField) {
		this.reserveField = reserveField;
	}
	public Integer getRecordCustomerCount() {
		return recordCustomerCount;
	}
	public void setRecordCustomerCount(Integer recordCustomerCount) {
		this.recordCustomerCount = recordCustomerCount;
	}
	public Integer getRecordVisitCount() {
		return recordVisitCount;
	}
	public void setRecordVisitCount(Integer recordVisitCount) {
		this.recordVisitCount = recordVisitCount;
	}
	public Integer getRecordNotVisitCount() {
		return recordNotVisitCount;
	}
	public void setRecordNotVisitCount(Integer recordNotVisitCount) {
		this.recordNotVisitCount = recordNotVisitCount;
	}
	public Integer getGuideCustomerVisitCount() {
		return guideCustomerVisitCount;
	}
	public void setGuideCustomerVisitCount(Integer guideCustomerVisitCount) {
		this.guideCustomerVisitCount = guideCustomerVisitCount;
	}
	public Integer getOutSideDealCount() {
		return outSideDealCount;
	}
	public void setOutSideDealCount(Integer outSideDealCount) {
		this.outSideDealCount = outSideDealCount;
	}
	public Integer getIntFiledToDealNum() {
		return intFiledToDealNum;
	}
	public void setIntFiledToDealNum(Integer intFiledToDealNum) {
		this.intFiledToDealNum = intFiledToDealNum;
	}
	
	public Integer getGuiCustomerRecordCount() {
		return guiCustomerRecordCount;
	}
	public void setGuiCustomerRecordCount(Integer guiCustomerRecordCount) {
		this.guiCustomerRecordCount = guiCustomerRecordCount;
	}
	public Integer getOutSideCustomerRecordCount() {
		return outSideCustomerRecordCount;
	}
	public void setOutSideCustomerRecordCount(Integer outSideCustomerRecordCount) {
		this.outSideCustomerRecordCount = outSideCustomerRecordCount;
	}
	public Integer getTotalDealCount() {
		return totalDealCount;
	}
	public void setTotalDealCount(Integer totalDealCount) {
		this.totalDealCount = totalDealCount;
	}
	public String getGrVisiteRate() {
		return grVisiteRate;
	}
	public void setGrVisiteRate(String grVisiteRate) {
		this.grVisiteRate = grVisiteRate;
	}
	public String getOutSideDealRate() {
		return outSideDealRate;
	}
	public void setOutSideDealRate(String outSideDealRate) {
		this.outSideDealRate = outSideDealRate;
	}
	public Integer getSystemCusSignedCount() {
		return systemCusSignedCount;
	}
	public void setSystemCusSignedCount(Integer systemCusSignedCount) {
		this.systemCusSignedCount = systemCusSignedCount;
	}
	public String getSysCusSignedRate() {
		return sysCusSignedRate;
	}
	public void setSysCusSignedRate(String sysCusSignedRate) {
		this.sysCusSignedRate = sysCusSignedRate;
	}
	public String getSystemEnterBuyRate() {
		return systemEnterBuyRate;
	}
	public void setSystemEnterBuyRate(String systemEnterBuyRate) {
		this.systemEnterBuyRate = systemEnterBuyRate;
	}


	public Integer getOutSideAgreeRecordCount() {
		return outSideAgreeRecordCount;
	}


	public void setOutSideAgreeRecordCount(Integer outSideAgreeRecordCount) {
		this.outSideAgreeRecordCount = outSideAgreeRecordCount;
	}


	public Integer getOutSideRefuseRecordCount() {
		return outSideRefuseRecordCount;
	}


	public void setOutSideRefuseRecordCount(Integer outSideRefuseRecordCount) {
		this.outSideRefuseRecordCount = outSideRefuseRecordCount;
	}


	public Integer getOutSideWaitSignCount() {
		return outSideWaitSignCount;
	}


	public void setOutSideWaitSignCount(Integer outSideWaitSignCount) {
		this.outSideWaitSignCount = outSideWaitSignCount;
	}


	public Integer getOutSideRevokeOrderCount() {
		return outSideRevokeOrderCount;
	}


	public void setOutSideRevokeOrderCount(Integer outSideRevokeOrderCount) {
		this.outSideRevokeOrderCount = outSideRevokeOrderCount;
	}


	public Integer getOutSideSubscribeHouseCount() {
		return outSideSubscribeHouseCount;
	}


	public void setOutSideSubscribeHouseCount(Integer outSideSubscribeHouseCount) {
		this.outSideSubscribeHouseCount = outSideSubscribeHouseCount;
	}


	public Double getOutSideSubscribeMoney() {
		return outSideSubscribeMoney;
	}


	public void setOutSideSubscribeMoney(Double outSideSubscribeMoney) {
		this.outSideSubscribeMoney = outSideSubscribeMoney;
	}


	public Double getOutSideSubscribeGetMoney() {
		return outSideSubscribeGetMoney;
	}


	public void setOutSideSubscribeGetMoney(Double outSideSubscribeGetMoney) {
		this.outSideSubscribeGetMoney = outSideSubscribeGetMoney;
	}


	public Double getOutSideSubscribeLockHouseMoney() {
		return outSideSubscribeLockHouseMoney;
	}


	public void setOutSideSubscribeLockHouseMoney(Double outSideSubscribeLockHouseMoney) {
		this.outSideSubscribeLockHouseMoney = outSideSubscribeLockHouseMoney;
	}


	public Double getOutSideGiveUpHouseMoney() {
		return outSideGiveUpHouseMoney;
	}


	public void setOutSideGiveUpHouseMoney(Double outSideGiveUpHouseMoney) {
		this.outSideGiveUpHouseMoney = outSideGiveUpHouseMoney;
	}


	public Double getOutSideWaitSignHouseMoney() {
		return outSideWaitSignHouseMoney;
	}


	public void setOutSideWaitSignHouseMoney(Double outSideWaitSignHouseMoney) {
		this.outSideWaitSignHouseMoney = outSideWaitSignHouseMoney;
	}


	public String getOutSideLeadCusRate() {
		return outSideLeadCusRate;
	}


	public void setOutSideLeadCusRate(String outSideLeadCusRate) {
		this.outSideLeadCusRate = outSideLeadCusRate;
	}


	public Double getOutSideSignHouseMoney() {
		return outSideSignHouseMoney;
	}


	public void setOutSideSignHouseMoney(Double outSideSignHouseMoney) {
		this.outSideSignHouseMoney = outSideSignHouseMoney;
	}


	public Integer getOutSideRecordCuCount() {
		return outSideRecordCuCount;
	}


	public void setOutSideRecordCuCount(Integer outSideRecordCuCount) {
		this.outSideRecordCuCount = outSideRecordCuCount;
	}


	public Integer getOutSideNewCuRecordCount() {
		return outSideNewCuRecordCount;
	}


	public void setOutSideNewCuRecordCount(Integer outSideNewCuRecordCount) {
		this.outSideNewCuRecordCount = outSideNewCuRecordCount;
	}


	public Integer getOutSideOldCuRecordCount() {
		return outSideOldCuRecordCount;
	}


	public void setOutSideOldCuRecordCount(Integer outSideOldCuRecordCount) {
		this.outSideOldCuRecordCount = outSideOldCuRecordCount;
	}


	public Integer getOutSideSignCuCount() {
		return outSideSignCuCount;
	}


	public void setOutSideSignCuCount(Integer outSideSignCuCount) {
		this.outSideSignCuCount = outSideSignCuCount;
	}


	public Integer getOutSideNewCuSignCount() {
		return outSideNewCuSignCount;
	}


	public void setOutSideNewCuSignCount(Integer outSideNewCuSignCount) {
		this.outSideNewCuSignCount = outSideNewCuSignCount;
	}


	public Integer getOutSideOldSignCount() {
		return outSideOldSignCount;
	}


	public void setOutSideOldSignCount(Integer outSideOldSignCount) {
		this.outSideOldSignCount = outSideOldSignCount;
	}


	@Override
	public String toString() {
		return "OutSideProject [copDate=" + copDate + ", collDateTime=" + collDateTime
				+ ", guideCount=" + guideCount + ", visitedCount=" + visitedCount + ", recordCustomerCount="
				+ recordCustomerCount + ", recordVisitCount=" + recordVisitCount + ", recordNotVisitCount="
				+ recordNotVisitCount + ", guideCustomerVisitCount=" + guideCustomerVisitCount + ", outSideDealCount="
				+ outSideDealCount + ", guidedHouseNum=" + guidedHouseNum + ", visitedHouseNum=" + visitedHouseNum
				+ ", guidedShopId=" + guidedShopId + ", guidedShopAgentId=" + guidedShopAgentId + ", visitedShopId="
				+ visitedShopId + ", visitedShopAgentId=" + visitedShopAgentId + ", reserveField=" + reserveField + "]";
	}
	
}
