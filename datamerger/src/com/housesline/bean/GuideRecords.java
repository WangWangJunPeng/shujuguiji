package com.housesline.bean;


/**
* 2017-01-22
* ������¼
* @author wjp 
*/

public class GuideRecords {
	//��¼��,�Զ�����
	private Integer recordNo;
	//�����ͻ�����
	private String projectCustomerId;
	//�н�ͻ�����
	private String shopCustomerId;
	//�ͻ�����
	private String customerName;
	//�ͻ��绰
	private String customerPhone;
	//�������û�����
	private String userId;
	//��������
	private String projectId;
	//����״̬,0:����(�����ɹ�);1:ȷ��(����,���ﰸ��);2:ɾ��;3:���(����ʧ��);4����������
	private Integer applyStatus;
	//����ʱ��
	private String applyTime;
	//���ü�¼����
	private Integer visitNo;
	//�ͻ������Ƿ�ɽ�,0:δ�ɽ�;1:�ɽ�
	private Integer isDeal;
	//Ԥ����˵���ֶ�
	private String description;
	//Ԥ���ı�ǩ�ֶ�
	private String tags;
	//����ʧ��˵���ֶ�,1:��֧�ִ���,2:�ÿͻ�Ϊ�ð����ͻ����ڱ�������,3:�ѱ������н鱸��
	private String fail;
	//����ʱ ҵ���� �ֶ� (����ҵ��������Ž�ȥ)
	private String rules;
	
	public Integer getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(Integer recordNo) {
		this.recordNo = recordNo;
	}
	public String getProjectCustomerId() {
		return projectCustomerId;
	}
	public void setProjectCustomerId(String projectCustomerId) {
		this.projectCustomerId = projectCustomerId;
	}
	public String getShopCustomerId() {
		return shopCustomerId;
	}
	public void setShopCustomerId(String shopCustomerId) {
		this.shopCustomerId = shopCustomerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public Integer getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public Integer getVisitNo() {
		return visitNo;
	}
	public void setVisitNo(Integer visitNo) {
		this.visitNo = visitNo;
	}
	public Integer getIsDeal() {
		return isDeal;
	}
	public void setIsDeal(Integer isDeal) {
		this.isDeal = isDeal;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getFail() {
		return fail;
	}
	public void setFail(String fail) {
		this.fail = fail;
	}
	public String getRules() {
		return rules;
	}
	public void setRules(String rules) {
		this.rules = rules;
	}
	@Override
	public String toString() {
		return "GuideRecords [recordNo=" + recordNo + ", projectCustomerId=" + projectCustomerId + ", shopCustomerId="
				+ shopCustomerId + ", customerName=" + customerName + ", customerPhone=" + customerPhone + ", userId="
				+ userId + ", projectId=" + projectId + ", applyStatus=" + applyStatus + ", applyTime=" + applyTime
				+ ", visitNo=" + visitNo + ", isDeal=" + isDeal + ", description=" + description + ", tags=" + tags
				+ ", fail=" + fail + ", rules=" + rules + "]";
	}
}
