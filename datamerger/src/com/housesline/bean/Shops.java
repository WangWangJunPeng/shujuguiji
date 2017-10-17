package com.housesline.bean;


/**
*
* @author wjp 2017-02-23
*/
public class Shops {
	//�н��ŵ���,�Զ�����,Ψһ��
	private Integer shopId;
	//�ŵ�״̬,0:����,1:����,2:ɾ��,3:����
	private Integer shopStatus;
	//����ʱ��
	private String applyTime;
	//��׼ʱ��
	private String approveTime;
	//��׼���û�����
	private String approveUserId;
	//�н��ŵ�����
	private String shopName;
	//�н鹫˾����
	private String companyName;
	//��ַ
	private String address;
	//ʡ,��,��
	private String city;
	//��γ��
	private String location;
	//��ϵ������
	private String contactPerson;
	//��ϵ�˵绰
	private String phone;
	//����
	private String email;
	//����Ȥ������(����)
	private String interstAreas;
	//����Ȥ�İ���(����)
	private String interstProjects;
	//���һ�ε�½��ʱ��
	private String lastLoginTime;
	//Ԥ���������ֶ�
	private String description;
	//Ԥ���ı�ǩ�ֶ�
	private String tags;
	//�ŵ���Ƭurl
	private String photo;
	//���п�����
	private String bank_cards;
	//������������
	private String bankName;
	//���п����˴���
	private String representative;
	//Ӫҵִ�ո�ӡ��url
	private String licensePhoto;
	//�ŵ�Ӫҵִ�պ�
	private String licenseNo;
	//������
	private String auditOpinion;
	//�ŵ��ַ�ľ�γ��
	private String lngLat;
	//�ŵ���סƽ̨״̬
	private Integer inSystemStutas;
	//����
	private String longitude;
	//γ��
	private String latitude;
	
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public Integer getShopStatus() {
		return shopStatus;
	}
	public void setShopStatus(Integer shopStatus) {
		this.shopStatus = shopStatus;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public String getApproveTime() {
		return approveTime;
	}
	public void setApproveTime(String approveTime) {
		this.approveTime = approveTime;
	}
	public String getApproveUserId() {
		return approveUserId;
	}
	public void setApproveUserId(String approveUserId) {
		this.approveUserId = approveUserId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getInterstAreas() {
		return interstAreas;
	}
	public void setInterstAreas(String interstAreas) {
		this.interstAreas = interstAreas;
	}
	public String getInterstProjects() {
		return interstProjects;
	}
	public void setInterstProjects(String interstProjects) {
		this.interstProjects = interstProjects;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
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
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getBank_cards() {
		return bank_cards;
	}
	public void setBank_cards(String bank_cards) {
		this.bank_cards = bank_cards;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getRepresentative() {
		return representative;
	}
	public void setRepresentative(String representative) {
		this.representative = representative;
	}
	public String getLicensePhoto() {
		return licensePhoto;
	}
	public void setLicensePhoto(String licensePhoto) {
		this.licensePhoto = licensePhoto;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public String getAuditOpinion() {
		return auditOpinion;
	}
	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}
	public String getLngLat() {
		return lngLat;
	}
	public void setLngLat(String lngLat) {
		this.lngLat = lngLat;
	}
	public Integer getInSystemStutas() {
		return inSystemStutas;
	}
	public void setInSystemStutas(Integer inSystemStutas) {
		this.inSystemStutas = inSystemStutas;
	}
	
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	@Override
	public String toString() {
		return "Shops [shopId=" + shopId + ", shopStatus=" + shopStatus + ", applyTime=" + applyTime + ", approveTime="
				+ approveTime + ", approveUserId=" + approveUserId + ", shopName=" + shopName + ", companyName="
				+ companyName + ", address=" + address + ", city=" + city + ", location=" + location
				+ ", contactPerson=" + contactPerson + ", phone=" + phone + ", email=" + email + ", interstAreas="
				+ interstAreas + ", interstProjects=" + interstProjects + ", lastLoginTime=" + lastLoginTime
				+ ", description=" + description + ", tags=" + tags + ", photo=" + photo + ", bank_cards=" + bank_cards
				+ ", bankName=" + bankName + ", representative=" + representative + ", licensePhoto=" + licensePhoto
				+ ", licenseNo=" + licenseNo + ", auditOpinion=" + auditOpinion + ", lngLat=" + lngLat
				+ ", inSystemStutas=" + inSystemStutas + ", longitude=" + longitude + ", latitude=" + latitude + "]";
	}
}
