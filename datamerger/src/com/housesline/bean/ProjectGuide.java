package com.housesline.bean;


/**
* ����
* @author wjp 2017-01-11
*/
public class ProjectGuide {
	//����id
	private String projectId;
	//�Ƿ��ϻ���
	private Integer isAvailable;
	//��Ч����
	private Integer validDays;
	//0:�ȸ�,1:�󸶹̶����,2:�󸶰ٷֱ�
	private Integer payType;
	//�ɽ��������
	private Double rewardMoney;
	//�ɽ������ɽ����İٷֱ�
	private Double rewardpercent;
	//˵��
	private String description;
	//�Ƿ�֧�ִ���,0:��֧��,1:֧��
	private Integer isDaiKan;
	//�Ƿ�֧�����,0:��֧��,1:֧��
	private Integer isYiDi;
	//�������Ӷ��
	private Double yiDiSalesCommission;
	private String yiDiSalesCommissionStr;
	//�����Ч����
	private String yiDiValidity;
	//�Ƿ�֧�ֿ��ٽ�Ӷ,0:��֧��,1:֧��
	private Integer isFast;
	//����Ӷ��
	private Double daiKanMoney;
	private String daiKanMoneyStr;
	//����Ӷ��
	private Double fenXiaoMoney;
	private String fenXiaoMoneyStr;
	//�ͻ�������
	private String custormerProtectDays;
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public Integer getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(Integer isAvailable) {
		this.isAvailable = isAvailable;
	}
	public Integer getValidDays() {
		return validDays;
	}
	public void setValidDays(Integer validDays) {
		this.validDays = validDays;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Double getRewardMoney() {
		return rewardMoney;
	}
	public void setRewardMoney(Double rewardMoney) {
		this.rewardMoney = rewardMoney;
	}
	public Double getRewardpercent() {
		return rewardpercent;
	}
	public void setRewardpercent(Double rewardpercent) {
		this.rewardpercent = rewardpercent;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getIsDaiKan() {
		return isDaiKan;
	}
	public void setIsDaiKan(Integer isDaiKan) {
		this.isDaiKan = isDaiKan;
	}
	public Integer getIsYiDi() {
		return isYiDi;
	}
	public void setIsYiDi(Integer isYiDi) {
		this.isYiDi = isYiDi;
	}
	public Integer getIsFast() {
		return isFast;
	}
	public void setIsFast(Integer isFast) {
		this.isFast = isFast;
	}
	public Double getDaiKanMoney() {
		return daiKanMoney;
	}
	public void setDaiKanMoney(Double daiKanMoney) {
		this.daiKanMoney = daiKanMoney;
	}
	public Double getFenXiaoMoney() {
		return fenXiaoMoney;
	}
	public void setFenXiaoMoney(Double fenXiaoMoney) {
		this.fenXiaoMoney = fenXiaoMoney;
	}
	public Double getYiDiSalesCommission() {
		return yiDiSalesCommission;
	}
	public void setYiDiSalesCommission(Double yiDiSalesCommission) {
		this.yiDiSalesCommission = yiDiSalesCommission;
	}
	public String getYiDiValidity() {
		return yiDiValidity;
	}
	public void setYiDiValidity(String yiDiValidity) {
		this.yiDiValidity = yiDiValidity;
	}
	public String getCustormerProtectDays() {
		return custormerProtectDays;
	}
	public void setCustormerProtectDays(String custormerProtectDays) {
		this.custormerProtectDays = custormerProtectDays;
	}
	public String getYiDiSalesCommissionStr() {
		return yiDiSalesCommissionStr;
	}
	public void setYiDiSalesCommissionStr(String yiDiSalesCommissionStr) {
		this.yiDiSalesCommissionStr = yiDiSalesCommissionStr;
	}
	public String getDaiKanMoneyStr() {
		return daiKanMoneyStr;
	}
	public void setDaiKanMoneyStr(String daiKanMoneyStr) {
		this.daiKanMoneyStr = daiKanMoneyStr;
	}
	public String getFenXiaoMoneyStr() {
		return fenXiaoMoneyStr;
	}
	public void setFenXiaoMoneyStr(String fenXiaoMoneyStr) {
		this.fenXiaoMoneyStr = fenXiaoMoneyStr;
	}
	@Override
	public String toString() {
		return "{projectId:" + projectId + ", isAvailable:" + isAvailable + ", validDays:" + validDays
				+ ", payType:" + payType + ", rewardMoney:" + rewardMoney + ", rewardpercent:" + rewardpercent
				+ ", description:" + description + ", isDaiKan:" + isDaiKan + ", isYiDi:" + isYiDi
				+ ", yiDiSalesCommission:" + yiDiSalesCommission + ", yiDiValidity:" + yiDiValidity + ", isFast:"
				+ isFast + ", daiKanMoney:" + daiKanMoney + ", fenXiaoMoney:" + fenXiaoMoney + ", custormerProtectDays:"
				+ custormerProtectDays + "}";
	}
}
