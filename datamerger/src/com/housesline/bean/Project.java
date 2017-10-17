package com.housesline.bean;

import java.util.ArrayList;
import java.util.Map;


public class Project {

	//��Ŀid
	private String projectId;
	//��Ŀ����
	private String projectName;
	//ʡ���С���
	private String city;
	//��Ŀ�õ����
	private Double landArea;
	//��Ŀ�ܽ������
	private Double buildArea;
	//���Ͻ������
	private Double groundArea;
	//���½������
	private Double underGroundArea;
	//�ܻ���
	private Integer unitCount;
	//�ݻ���
	private Double volumeRatio;
	//.00��ʾ�����ֶ�
	private String strVolumeRatio;
	//�ܶ�
	private Double density;
	//.00��ʾ����
	private String strDensity;
	//�̻���
	private Double afforestationRatio;
	private String strAfforestationRatio;
	//��ҵ����
	private String propertyType;
	//��¥��ַ
	private String saleAddress;
	//��ҵ��ַ
	private String propertyAddress;
	//��λ
	private String district;
	//����
	private Double averagePrice;
	//.00��ʾ�����ֶ�
	private String strAveragePrice;
	//������
	private String developer;
	//Ͷ����
	private String investor;
	//��ҵ�������
	private String manager;
	//��ҵ��
	private Double propertyCost;
	//��00��ʾ����
	private String strPropertyCost;
	//װ�ޱ�׼(0��ë����1����ͨװ�ޣ�2����װ�ޣ�3���Ҿ�ȫ�䣻4���ҵ�ȫ��)
	private Integer decorationStandard;
	//������׼
	private String deliverStandard;
	//����ʱ��
	private String startTime;
	//����ʱ��
	private String deliverTime;
	//��Ȩ����
	private Integer rightsYears;
	//Ԥ�����֤ͼƬ
	private String presalePermissionURL;
	//Ԥ��ĸ��ֱ�ǩ
	private String tags;
	//Ԥ��˵���ֶ�
	private String description;
	//��¥��λ�þ�γ��
	private String saleAddressGis;
	//��ҵλ�þ�γ��
	private String propertyAddressGis;
	//Ч��ͼ
	private String effectPic;
	//�Ƿ���סƽ̨ 0-������סƽ̨,1-ͬ����סƽ̨,2-�ܾ���סƽ̨
	private Integer proInSystemStutas;
	//��¥������
	private String saleLongitude;
	//��¥��γ��
	private String saleLatitude;
	//��ҵ����
	private String propertyLongitude;
	//��ҵγ��
	private String propertyLatitude;
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Double getLandArea() {
		return landArea;
	}
	public void setLandArea(Double landArea) {
		this.landArea = landArea;
	}
	public Double getBuildArea() {
		return buildArea;
	}
	public void setBuildArea(Double buildArea) {
		this.buildArea = buildArea;
	}
	public Double getGroundArea() {
		return groundArea;
	}
	public void setGroundArea(Double groundArea) {
		this.groundArea = groundArea;
	}
	public Double getUnderGroundArea() {
		return underGroundArea;
	}
	public void setUnderGroundArea(Double underGroundArea) {
		this.underGroundArea = underGroundArea;
	}
	public Integer getUnitCount() {
		return unitCount;
	}
	public void setUnitCount(Integer unitCount) {
		this.unitCount = unitCount;
	}
	public Double getVolumeRatio() {
		return volumeRatio;
	}
	public void setVolumeRatio(Double volumeRatio) {
		this.volumeRatio = volumeRatio;
	}
	public Double getDensity() {
		return density;
	}
	public void setDensity(Double density) {
		this.density = density;
	}
	public Double getAfforestationRatio() {
		return afforestationRatio;
	}
	public void setAfforestationRatio(Double afforestationRatio) {
		this.afforestationRatio = afforestationRatio;
	}
	public String getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	public String getSaleAddress() {
		return saleAddress;
	}
	public void setSaleAddress(String saleAddress) {
		this.saleAddress = saleAddress;
	}
	public String getPropertyAddress() {
		return propertyAddress;
	}
	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public Double getAveragePrice() {
		return averagePrice;
	}
	public void setAveragePrice(Double averagePrice) {
		this.averagePrice = averagePrice;
	}
	public String getStrAveragePrice() {
		return strAveragePrice;
	}
	public void setStrAveragePrice(String strAveragePrice) {
		this.strAveragePrice = strAveragePrice;
	}
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	public String getInvestor() {
		return investor;
	}
	public void setInvestor(String investor) {
		this.investor = investor;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public Double getPropertyCost() {
		return propertyCost;
	}
	public void setPropertyCost(Double propertyCost) {
		this.propertyCost = propertyCost;
	}
	public Integer getDecorationStandard() {
		return decorationStandard;
	}
	public void setDecorationStandard(Integer decorationStandard) {
		this.decorationStandard = decorationStandard;
	}
	public String getDeliverStandard() {
		return deliverStandard;
	}
	public void setDeliverStandard(String deliverStandard) {
		this.deliverStandard = deliverStandard;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getDeliverTime() {
		return deliverTime;
	}
	public void setDeliverTime(String deliverTime) {
		this.deliverTime = deliverTime;
	}
	public Integer getRightsYears() {
		return rightsYears;
	}
	public void setRightsYears(Integer rightsYears) {
		this.rightsYears = rightsYears;
	}
	public String getPresalePermissionURL() {
		return presalePermissionURL;
	}
	public void setPresalePermissionURL(String presalePermissionURL) {
		this.presalePermissionURL = presalePermissionURL;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSaleAddressGis() {
		return saleAddressGis;
	}
	public void setSaleAddressGis(String saleAddressGis) {
		this.saleAddressGis = saleAddressGis;
	}
	public String getPropertyAddressGis() {
		return propertyAddressGis;
	}
	public void setPropertyAddressGis(String propertyAddressGis) {
		this.propertyAddressGis = propertyAddressGis;
	}
	public String getEffectPic() {
		return effectPic;
	}
	public void setEffectPic(String effectPic) {
		this.effectPic = effectPic;
	}
	
	public String getStrVolumeRatio() {
		return strVolumeRatio;
	}
	public void setStrVolumeRatio(String strVolumeRatio) {
		this.strVolumeRatio = strVolumeRatio;
	}
	public String getStrDensity() {
		return strDensity;
	}
	public void setStrDensity(String strDensity) {
		this.strDensity = strDensity;
	}
	public String getStrAfforestationRatio() {
		return strAfforestationRatio;
	}
	public void setStrAfforestationRatio(String strAfforestationRatio) {
		this.strAfforestationRatio = strAfforestationRatio;
	}
	public String getStrPropertyCost() {
		return strPropertyCost;
	}
	public void setStrPropertyCost(String strPropertyCost) {
		this.strPropertyCost = strPropertyCost;
	}
	public Integer getProInSystemStutas() {
		return proInSystemStutas;
	}
	public void setProInSystemStutas(Integer proInSystemStutas) {
		this.proInSystemStutas = proInSystemStutas;
	}
	public String getSaleLongitude() {
		return saleLongitude;
	}
	public void setSaleLongitude(String saleLongitude) {
		this.saleLongitude = saleLongitude;
	}
	public String getSaleLatitude() {
		return saleLatitude;
	}
	public void setSaleLatitude(String saleLatitude) {
		this.saleLatitude = saleLatitude;
	}
	public String getPropertyLongitude() {
		return propertyLongitude;
	}
	public void setPropertyLongitude(String propertyLongitude) {
		this.propertyLongitude = propertyLongitude;
	}
	public String getPropertyLatitude() {
		return propertyLatitude;
	}
	public void setPropertyLatitude(String propertyLatitude) {
		this.propertyLatitude = propertyLatitude;
	}
	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", city=" + city + ", landArea="
				+ landArea + ", buildArea=" + buildArea + ", groundArea=" + groundArea + ", underGroundArea="
				+ underGroundArea + ", unitCount=" + unitCount + ", volumeRatio=" + volumeRatio + ", strVolumeRatio="
				+ strVolumeRatio + ", density=" + density + ", strDensity=" + strDensity + ", afforestationRatio="
				+ afforestationRatio + ", strAfforestationRatio=" + strAfforestationRatio + ", propertyType="
				+ propertyType + ", saleAddress=" + saleAddress + ", propertyAddress=" + propertyAddress + ", district="
				+ district + ", averagePrice=" + averagePrice + ", strAveragePrice=" + strAveragePrice + ", developer="
				+ developer + ", investor=" + investor + ", manager=" + manager + ", propertyCost=" + propertyCost
				+ ", strPropertyCost=" + strPropertyCost + ", decorationStandard=" + decorationStandard
				+ ", deliverStandard=" + deliverStandard + ", startTime=" + startTime + ", deliverTime=" + deliverTime
				+ ", rightsYears=" + rightsYears + ", presalePermissionURL=" + presalePermissionURL + ", tags=" + tags
				+ ", description=" + description + ", saleAddressGis=" + saleAddressGis + ", propertyAddressGis="
				+ propertyAddressGis + ", effectPic=" + effectPic + ", proInSystemStutas=" + proInSystemStutas
				+ ", saleLongitude=" + saleLongitude + ", saleLatitude=" + saleLatitude + ", propertyLongitude="
				+ propertyLongitude + ", propertyLatitude=" + propertyLatitude + "]";
	}
}
