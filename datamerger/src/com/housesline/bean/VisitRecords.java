package com.housesline.bean;


/**
*
* @author wjp 2017-02-03
*/
public class VisitRecords {
	//��¼��,�Զ�����
	private Integer visitNo;
	//�ӷ���ҵ���ʱ���
	private String userId;
	//��������
	private String projectId;
	//����״̬,0:����,1:�Ӵ�,2:ɾ��,3:�ͱ�
	private Integer visitStatus;
	//��������
	private Integer customerCount;
	//�ͻ�����
	private String customerName;
	//�ͻ��绰
	private String phone;
	//������
	private Integer recordNo;
	//����ʱ��
	private String arriveTime;
	//�Ӵ�ʱ��
	private String receptTime;
	//�ͱ�ʱ��
	private String leaveTime;
	//ָ���Ӵ�����ҵ���ʵı���
	private String appointUserId;
	//�ͻ�����
	private String customerId;
	//Ԥ����˵���ֶ�
	private String description;
	//Ԥ���ı�ǩ�ֶ�
	private String tags;
	//�ü�¼��д״̬,0:δ��д,1:����д
	private Integer writeState;
	//�Ƿ����¿ͻ�ͨ�� ��Ĭ��Ϊtrue���¿ͻ���
	private Boolean isNew;
	
	public Integer getVisitNo() {
		return visitNo;
	}
	public void setVisitNo(Integer visitNo) {
		this.visitNo = visitNo;
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
	public Integer getVisitStatus() {
		return visitStatus;
	}
	public void setVisitStatus(Integer visitStatus) {
		this.visitStatus = visitStatus;
	}
	public Integer getCustomerCount() {
		return customerCount;
	}
	public void setCustomerCount(Integer customerCount) {
		this.customerCount = customerCount;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(Integer recordNo) {
		this.recordNo = recordNo;
	}
	public String getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getReceptTime() {
		return receptTime;
	}
	public void setReceptTime(String receptTime) {
		this.receptTime = receptTime;
	}
	public String getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}
	public String getAppointUserId() {
		return appointUserId;
	}
	public void setAppointUserId(String appointUserId) {
		this.appointUserId = appointUserId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
	public Integer getWriteState() {
		return writeState;
	}
	public void setWriteState(Integer writeState) {
		this.writeState = writeState;
	}
	public Boolean getIsNew() {
		return isNew;
	}
	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}
	@Override
	public String toString() {
		return "VisitRecords [visitNo=" + visitNo + ", userId=" + userId + ", projectId=" + projectId + ", visitStatus="
				+ visitStatus + ", customerCount=" + customerCount + ", customerName=" + customerName + ", phone="
				+ phone + ", recordNo=" + recordNo + ", arriveTime=" + arriveTime + ", receptTime=" + receptTime
				+ ", leaveTime=" + leaveTime + ", appointUserId=" + appointUserId + ", customerId=" + customerId
				+ ", description=" + description + ", tags=" + tags + ", writeState=" + writeState + "]";
	}
	
}
