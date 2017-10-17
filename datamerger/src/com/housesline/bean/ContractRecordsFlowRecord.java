package com.housesline.bean;


public class ContractRecordsFlowRecord {
	
	//订单流水常量
	//申请
	public static final Integer RECORDSTATUS_APPLY = 2001;
	//同意
	public static final Integer RECORDSTATUS_AGREE = 2002;
	//上传凭证,待确认
	public static final Integer RECORDSTATUS_GETMONEY = 2003;
	//待签约,下定
	public static final Integer RECORDSTATUS_REDYSIGN = 2004;
	//已签约
	public static final Integer RECORDSTATUS_SIGNED = 2005;
	//否决
	public static final Integer RECORDSTATUS_REFUSE = 2006;
	//撤销
	public static final Integer RECORDSTATUS_BACKOUT = 2007;
	
	
	// 流水记录主键
	private Integer fr_crId;
	// 操作类别(添加：1001,删除：1002；修改：1003)
	private Integer operateSort;
	//订单流水状态(申请：2001;同意：2002;上传凭证，待确认：2003;待签约，下定：2004;已签约：2005;订单拒绝：2006;订单取消：2007)
	private Integer orderSort;
	// 操作时间
	private String operateTime;
	// 操作人id
	private String operateUserId;
	// 操作人姓名
	private String operateUserName;
	// 操作人手机号
	private String operateUserPhone;
	// 历史记录
	private String historyRecord;
	// 更新后的记录
	private String newRecord;
	
	private Integer recordNo;
	
	private String projectId;
	
	public Integer getFr_crId() {
		return fr_crId;
	}
	public void setFr_crId(Integer fr_crId) {
		this.fr_crId = fr_crId;
	}
	public Integer getOperateSort() {
		return operateSort;
	}
	public void setOperateSort(Integer operateSort) {
		this.operateSort = operateSort;
	}
	public Integer getOrderSort() {
		return orderSort;
	}
	public void setOrderSort(Integer orderSort) {
		this.orderSort = orderSort;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	public String getOperateUserId() {
		return operateUserId;
	}
	public void setOperateUserId(String operateUserId) {
		this.operateUserId = operateUserId;
	}
	public String getOperateUserName() {
		return operateUserName;
	}
	public void setOperateUserName(String operateUserName) {
		this.operateUserName = operateUserName;
	}
	public String getOperateUserPhone() {
		return operateUserPhone;
	}
	public void setOperateUserPhone(String operateUserPhone) {
		this.operateUserPhone = operateUserPhone;
	}
	public String getHistoryRecord() {
		return historyRecord;
	}
	public void setHistoryRecord(String historyRecord) {
		this.historyRecord = historyRecord;
	}
	public String getNewRecord() {
		return newRecord;
	}
	public void setNewRecord(String newRecord) {
		this.newRecord = newRecord;
	}
	public Integer getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(Integer recordNo) {
		this.recordNo = recordNo;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	
}
