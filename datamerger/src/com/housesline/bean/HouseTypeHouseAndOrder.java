package com.housesline.bean;

/**
 * ���ͷ�Դ�����鼯
 *   ��ÿ�ֻ��ͺ�ÿ��ΪԪ�顣
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class HouseTypeHouseAndOrder {
	//ʹ��chDate + houseTypeId ��Ϊ��������
	/**---------����------------*/
	//�������� yyyy-MM-dd
	private String chDate;
	//�鼯����ʱ�� yyyy-MM-dd HH:mm:ss
	private String collDateTime;
	//����id
	private String houseTypeId;
	//��������
	private String houseTypeName;
	//���
	private Double area;
	//�Ϲ���
	private Integer enterBuyCount;
	//ͬ���Ϲ���
	private Integer agreeEnterCount;
	//�ܾ��Ϲ���
	private Integer refuseEnterCount;
	//�¶���
	private Integer payCount;
	//��ǩԼ��
	private Integer waitSignCount;
	//ǩԼ��
	private Integer signCount;
	//������
	private Integer revokeOrderCount;
	
	/**----------��Դ-------------*/
	//��Դ����
	private Integer houseTotalCount;
	//�ϼ���
	private Integer putAwayCount;
	//�¼���
	private Integer downAwayCount;
	//��������
	private Integer sallingCount;
	//ǩԼ��
	private Integer signedCount;
	//������
	private Integer revokedHouseCount;
	//Ԥ���ֶ�
	private String discrption;
	
	public String getChDate() {
		return chDate;
	}
	public void setChDate(String chDate) {
		this.chDate = chDate;
	}
	public String getCollDateTime() {
		return collDateTime;
	}
	public void setCollDateTime(String collDateTime) {
		this.collDateTime = collDateTime;
	}
	public String getHouseTypeId() {
		return houseTypeId;
	}
	public void setHouseTypeId(String houseTypeId) {
		this.houseTypeId = houseTypeId;
	}
	public String getHouseTypeName() {
		return houseTypeName;
	}
	public void setHouseTypeName(String houseTypeName) {
		this.houseTypeName = houseTypeName;
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
	public Integer getHouseTotalCount() {
		return houseTotalCount;
	}
	public void setHouseTotalCount(Integer houseTotalCount) {
		this.houseTotalCount = houseTotalCount;
	}
	public Integer getPutAwayCount() {
		return putAwayCount;
	}
	public void setPutAwayCount(Integer putAwayCount) {
		this.putAwayCount = putAwayCount;
	}
	public Integer getDownAwayCount() {
		return downAwayCount;
	}
	public void setDownAwayCount(Integer downAwayCount) {
		this.downAwayCount = downAwayCount;
	}
	public Integer getSallingCount() {
		return sallingCount;
	}
	public void setSallingCount(Integer sallingCount) {
		this.sallingCount = sallingCount;
	}
	public Integer getSignedCount() {
		return signedCount;
	}
	public void setSignedCount(Integer signedCount) {
		this.signedCount = signedCount;
	}
	public Integer getRevokedHouseCount() {
		return revokedHouseCount;
	}
	public void setRevokedHouseCount(Integer revokedHouseCount) {
		this.revokedHouseCount = revokedHouseCount;
	}
	public String getDiscrption() {
		return discrption;
	}
	public void setDiscrption(String discrption) {
		this.discrption = discrption;
	}
	
	public Integer getWaitSignCount() {
		return waitSignCount;
	}
	public void setWaitSignCount(Integer waitSignCount) {
		this.waitSignCount = waitSignCount;
	}
	
	
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	@Override
	public String toString() {
		return "HouseTypeHouseAndOrder [chDate=" + chDate + ", collDateTime=" + collDateTime + ", houseTypeId="
				+ houseTypeId + ", houseTypeName=" + houseTypeName + ", area=" + area + ", enterBuyCount="
				+ enterBuyCount + ", agreeEnterCount=" + agreeEnterCount + ", refuseEnterCount=" + refuseEnterCount
				+ ", payCount=" + payCount + ", waitSignCount=" + waitSignCount + ", signCount=" + signCount
				+ ", revokeOrderCount=" + revokeOrderCount + ", houseTotalCount=" + houseTotalCount + ", putAwayCount="
				+ putAwayCount + ", downAwayCount=" + downAwayCount + ", sallingCount=" + sallingCount
				+ ", signedCount=" + signedCount + ", revokedHouseCount=" + revokedHouseCount + ", discrption="
				+ discrption + "]";
	}
	
	
	
	
	
}
