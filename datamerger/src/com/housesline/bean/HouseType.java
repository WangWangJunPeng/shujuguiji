package com.housesline.bean;

import java.util.Set;


/**
*
* @author wjp 2017-01-09
*/
public class HouseType {
	//����id
	private String houseTypeId;
	//��Ŀid
	private String projectId;
	//��ƬURL
	private String photoURL;
	//����
	private String caption;
	//���
	private Double area;
	private String areaStr;
	//����
	private String housType;
	//����˵��
	private String housTypeDesc;
	//���õ�˵�����ֶ�
	private String description;
	//��ǩ
	private String tags;
	//�����Ƿ�����
	private String isUse;
	public String getHouseTypeId() {
		return houseTypeId;
	}


	public void setHouseTypeId(String houseTypeId) {
		this.houseTypeId = houseTypeId;
	}


	public String getProjectId() {
		return projectId;
	}


	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}


	public String getPhotoURL() {
		return photoURL;
	}


	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}


	public String getCaption() {
		return caption;
	}


	public void setCaption(String caption) {
		this.caption = caption;
	}


	public Double getArea() {
		return area;
	}


	public void setArea(Double area) {
		this.area = area;
	}


	public String getHousType() {
		return housType;
	}


	public void setHousType(String housType) {
		this.housType = housType;
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


	public String getHousTypeDesc() {
		return housTypeDesc;
	}


	public void setHousTypeDesc(String housTypeDesc) {
		this.housTypeDesc = housTypeDesc;
	}
	
	public String getIsUse() {
		return isUse;
	}


	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public String getAreaStr() {
		return areaStr;
	}


	public void setAreaStr(String areaStr) {
		this.areaStr = areaStr;
	}


	@Override
	public String toString() {
		return "HouseType [houseTypeId=" + houseTypeId + ", projectId=" + projectId + ", photoURL=" + photoURL
				+ ", caption=" + caption + ", area=" + area + ", housType=" + housType + ", housTypeDesc=" + housTypeDesc + "]";
	}
	
	
}
