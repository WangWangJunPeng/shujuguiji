package com.housesline.bean;


/**
*
* @author wjp 2017-01-22
*/
public class ShopCustomers {
	//�ͻ�����
	private String shopCustomerId;
	//�ͻ�����
	private String shopCustomerName;
	//�ͻ��绰,Ψһ��
	private String shopCustomerPhone;
	//�н龭���˵��û�����
	private String userId;
	//����ʱ��
	private String createTime;
	//�Ա�,0:δ֪,1:male;2:female
	private Integer sex;
	//Ԥ���������ֶ�
	private String description;
	//Ԥ���ı�ǩ�ֶ�
	private String tags;
	//��Ӧ�ŵ�id
	private Integer shopId;
	//���֤��
	private String idCard;
	
	public String getShopCustomerId() {
		return shopCustomerId;
	}
	public void setShopCustomerId(String shopCustomerId) {
		this.shopCustomerId = shopCustomerId;
	}
	public String getShopCustomerName() {
		return shopCustomerName;
	}
	public void setShopCustomerName(String shopCustomerName) {
		this.shopCustomerName = shopCustomerName;
	}
	public String getShopCustomerPhone() {
		return shopCustomerPhone;
	}
	public void setShopCustomerPhone(String shopCustomerPhone) {
		this.shopCustomerPhone = shopCustomerPhone;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	@Override
	public String toString() {
		return "ShopCustomers [shopCustomerId=" + shopCustomerId + ", shopCustomerName=" + shopCustomerName
				+ ", shopCustomerPhone=" + shopCustomerPhone + ", userId=" + userId + ", createTime=" + createTime
				+ ", sex=" + sex + ", description=" + description + ", tags=" + tags + ", shopId=" + shopId
				+ ", idCard=" + idCard + "]";
	}
	
}
