package com.housesline.bean;


/**
*
* @author wjp 2017-01-22
*/
public class ProjectCustomers {
	//��������
	private String projectId;
	//�ͻ�����
	private String projectCustomerId;
	//�ͻ�����
	private String projectCustomerName;
	//�ͻ��绰,Ψһ��
	private String projectCustomerPhone;
	//���֤����
	private String idCard;
	//����������û�����(�������id)
	private String createUserId;
	//����ʱ��
	private String createTime;
	//�Ա�,0:δ֪,1:male,2;famle
	private Integer sex;
	//��ǰ������ְҵ�����û�����   �ɰ����������
	private String ownerUserId;
	//������ǰ��ҵ�������µ���ʼʱ��()
	private String ownerStartTime;
	//Ԥ���������ֶ�
	private String description;
	//Ԥ���ı�ǩ�ֶ�
	private String tags;
	//�ͻ���󵽰�����ʱ��
	private String lastTime;
	//�������
	private String evaluate;
	
	
	public String getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	public String getProjectCustomerId() {
		return projectCustomerId;
	}
	public void setProjectCustomerId(String projectCustomerId) {
		this.projectCustomerId = projectCustomerId;
	}
	public String getProjectCustomerName() {
		return projectCustomerName;
	}
	public void setProjectCustomerName(String projectCustomerName) {
		this.projectCustomerName = projectCustomerName;
	}
	public String getProjectCustomerPhone() {
		return projectCustomerPhone;
	}
	public void setProjectCustomerPhone(String projectCustomerPhone) {
		this.projectCustomerPhone = projectCustomerPhone;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getOwnerUserId() {
		return ownerUserId;
	}
	public void setOwnerUserId(String ownerUserId) {
		this.ownerUserId = ownerUserId;
	}
	public String getOwnerStartTime() {
		return ownerStartTime;
	}
	public void setOwnerStartTime(String ownerStartTime) {
		this.ownerStartTime = ownerStartTime;
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
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	@Override
	public String toString() {
		return "ProjectCustomers [projectId=" + projectId + ", projectCustomerId=" + projectCustomerId
				+ ", projectCustomerName=" + projectCustomerName + ", projectCustomerPhone=" + projectCustomerPhone
				+ ", idCard=" + idCard + ", createUserId=" + createUserId + ", createTime=" + createTime + ", sex="
				+ sex + ", ownerUserId=" + ownerUserId + ", ownerStartTime=" + ownerStartTime + ", description="
				+ description + ", tags=" + tags + ", lastTime=" + lastTime + "]";
	}
}
