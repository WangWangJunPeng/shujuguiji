package com.housesline.bean;

/**
 * 户型房源订单归集
 *   以每种户型和每天为元组。
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class HouseTypeHouseAndOrder {
	//使用chDate + houseTypeId 作为联合主键
	/**---------订单------------*/
	//数据日期 yyyy-MM-dd
	private String chDate;
	//归集操作时间 yyyy-MM-dd HH:mm:ss
	private String collDateTime;
	//户型id
	private String houseTypeId;
	//户型名称
	private String houseTypeName;
	//面积
	private Double area;
	//认购数
	private Integer enterBuyCount;
	//同意认购数
	private Integer agreeEnterCount;
	//拒绝认购数
	private Integer refuseEnterCount;
	//下定数
	private Integer payCount;
	//待签约数
	private Integer waitSignCount;
	//签约数
	private Integer signCount;
	//撤单数
	private Integer revokeOrderCount;
	
	/**----------房源-------------*/
	//房源总数
	private Integer houseTotalCount;
	//上架数
	private Integer putAwayCount;
	//下架数
	private Integer downAwayCount;
	//在售中数
	private Integer sallingCount;
	//签约数
	private Integer signedCount;
	//撤销数
	private Integer revokedHouseCount;
	//预留字段
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
