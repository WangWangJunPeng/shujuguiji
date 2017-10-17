package com.housesline.bean;


/**
*
* @author wjp 2017-01-11
*/
public class ProjectBenefits {
	//�Żݱ���
	private String benefitId;
	//�������
	private String projectId;
	//�Ż�˵��
	private String caption;
	//��ʼʱ��
	private String startTime;
	//����ʱ��
	private String endTime;
	//���ɵĽ��
	private Double fee;
	//������λС��,���ɵĽ��
	private String feeStr;
	//�Żݽ��
	private Double benefitMoney;
	//�Żݽ�� ������λС��
	private String benefitMoneyStr;
	//�Żݱ���
	private Double benefitPercent;
	//�Żݱ��� ������λС��
	private String benefitPercentStr;
	//money:0,pencent:1
	private Integer type;
	//�Ż�����
	private String benefitsName;
	//�Ƿ���Զ�ѡ(0:����,1:������)
	private Integer isChooseMore;
	
	public String getBenefitId() {
		return benefitId;
	}

	public void setBenefitId(String benefitId) {
		this.benefitId = benefitId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}
	public String getFeeStr() {
		return feeStr;
	}

	public void setFeeStr(String feeStr) {
		this.feeStr = feeStr;
	}

	public Double getBenefitMoney() {
		return benefitMoney;
	}

	public void setBenefitMoney(Double benefitMoney) {
		this.benefitMoney = benefitMoney;
	}

	public Double getBenefitPercent() {
		return benefitPercent;
	}

	public void setBenefitPercent(Double benefitPercent) {
		this.benefitPercent = benefitPercent;
	}
	
	public String getBenefitMoneyStr() {
		return benefitMoneyStr;
	}

	public void setBenefitMoneyStr(String benefitMoneyStr) {
		this.benefitMoneyStr = benefitMoneyStr;
	}

	public String getBenefitPercentStr() {
		return benefitPercentStr;
	}

	public void setBenefitPercentStr(String benefitPercentStr) {
		this.benefitPercentStr = benefitPercentStr;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	 
	public String getBenefitsName() {
		return benefitsName;
	}

	public void setBenefitsName(String benefitsName) {
		this.benefitsName = benefitsName;
	}

	public Integer getIsChooseMore() {
		return isChooseMore;
	}

	public void setIsChooseMore(Integer isChooseMore) {
		this.isChooseMore = isChooseMore;
	}

	@Override
	public String toString() {
		return "ProjectBenefits [benefitId=" + benefitId + ", projectId=" + projectId + ", caption=" + caption
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", fee=" + fee + ", feeStr=" + feeStr
				+ ", benefitMoney=" + benefitMoney + ", benefitMoneyStr=" + benefitMoneyStr + ", benefitPercent="
				+ benefitPercent + ", benefitPercentStr=" + benefitPercentStr + ", type=" + type + ", benefitsName="
				+ benefitsName + ", isChooseMore=" + isChooseMore + "]";
	}
}
