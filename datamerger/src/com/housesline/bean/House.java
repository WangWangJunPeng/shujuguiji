package com.housesline.bean;


/**
 * @author wjp 2017-01-09
 */
public class House {
    //��Դ���
    private String houseId;
    //�������
    private String projectId;
    //����
    private String houseNo;
    //��λ��
    private String district;
    //¥����
    private String buildingNo;
    //(��Դ���ͣ�0:��Ԣ��1:���ݡ�2:������3:��ס���á�4:�칫�ҡ�5:�Ƶ�ʽ��Ԣ��6:���̡�7:��λ��8:���⡢9:������)
    private Integer houseKind;
    //��Ԫ
    private String unit;
    //¥��
    private Integer floor;
    //����
    private String direct;
    //�������
    private Double buildArea;
    private String buildAreaStr;
    //ʹ�����
    private Double usefulArea;
    private String usefulAreaStr;
    //�б��
    private Double listPrice;
    private String listPriceStr;
    //�׼�
    private Double minimumPrice;
    private String minimumPriceStr;
    //�н鹩����
    private Double shopPrice;
    private String shopPriceStr;
    //���ͱ���
    private String houseTypeId;
    //��������
    private String houseTypeName;
    //(��Դ״̬-0���¼ܣ�1���ϼܣ�2��ɾ����3��������4�������У�5������)
    private Integer houseStatus;
    //װ�ޱ�׼(0��ë����1����ͨװ�ޣ�2����װ�ޣ�3���Ҿ�ȫ�䣻4���ҵ�ȫ��)
    private Integer decorationStandard;
    //���õ�˵�����ֶ�
    private String description;
    //��ǩ�ֶ�
    private String tags;
    //����ͼƬURL
    private String photos;
    //����ʱ��
    private String shelvTime;

    private double rewardMoney;   //���ݿ����û������ֶ�,,,,
    //(�������Ƿ�ɼ�0:���ɼ�;1:�ɼ�)
    private Integer isOpen;
    //���ӵ�Ψһ����
    private Integer houseNum;
    //Ԥ��֤��Ϣ
    private String presalePermissionInfo;
    //����Ż����
    private String bestBenefitsId;

    
    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public Integer getHouseKind() {
        return houseKind;
    }

    public void setHouseKind(Integer houseKind) {
        this.houseKind = houseKind;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }

    public Double getBuildArea() {
        return buildArea;
    }

    public void setBuildArea(Double buildArea) {
        this.buildArea = buildArea;
    }

    public Double getUsefulArea() {
        return usefulArea;
    }

    public void setUsefulArea(Double usefulArea) {
        this.usefulArea = usefulArea;
    }

    public Double getListPrice() {
        return listPrice;
    }

    public void setListPrice(Double listPrice) {
        this.listPrice = listPrice;
    }

    public Double getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(Double minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public Double getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(Double shopPrice) {
        this.shopPrice = shopPrice;
    }

    public String getHouseTypeId() {
        return houseTypeId;
    }

    public void setHouseTypeId(String houseTypeId) {
        this.houseTypeId = houseTypeId;
    }

    public Integer getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(Integer houseStatus) {
        this.houseStatus = houseStatus;
    }

    public Integer getDecorationStandard() {
        return decorationStandard;
    }

    public void setDecorationStandard(Integer decorationStandard) {
        this.decorationStandard = decorationStandard;
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

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getShelvTime() {
        return shelvTime;
    }

    public void setShelvTime(String shelvTime) {
        this.shelvTime = shelvTime;
    }

    public double getRewardMoney() {
        return rewardMoney;
    }

    public void setRewardMoney(double rewardMoney) {
        this.rewardMoney = rewardMoney;
    }

    public Integer getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }

    public Integer getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(Integer houseNum) {
        this.houseNum = houseNum;
    }

    public String getPresalePermissionInfo() {
        return presalePermissionInfo;
    }

    public void setPresalePermissionInfo(String presalePermissionInfo) {
        this.presalePermissionInfo = presalePermissionInfo;
    }

    public String getBestBenefitsId() {
        return bestBenefitsId;
    }

    public void setBestBenefitsId(String bestBenefitsId) {
        this.bestBenefitsId = bestBenefitsId;
    }

    public String getHouseTypeName() {
		return houseTypeName;
	}

	public void setHouseTypeName(String houseTypeName) {
		this.houseTypeName = houseTypeName;
	}

	public String getBuildAreaStr() {
		return buildAreaStr;
	}

	public void setBuildAreaStr(String buildAreaStr) {
		this.buildAreaStr = buildAreaStr;
	}

	public String getUsefulAreaStr() {
		return usefulAreaStr;
	}

	public void setUsefulAreaStr(String usefulAreaStr) {
		this.usefulAreaStr = usefulAreaStr;
	}

	public String getListPriceStr() {
		return listPriceStr;
	}

	public void setListPriceStr(String listPriceStr) {
		this.listPriceStr = listPriceStr;
	}

	public String getMinimumPriceStr() {
		return minimumPriceStr;
	}

	public void setMinimumPriceStr(String minimumPriceStr) {
		this.minimumPriceStr = minimumPriceStr;
	}

	public String getShopPriceStr() {
		return shopPriceStr;
	}

	public void setShopPriceStr(String shopPriceStr) {
		this.shopPriceStr = shopPriceStr;
	}

	@Override
    public String toString() {
        return "House [houseId=" + houseId + ", projectId=" + projectId + ", houseNo=" + houseNo + ", district="
                + district + ", buildingNo=" + buildingNo + ", houseKind=" + houseKind + ", unit=" + unit + ", floor="
                + floor + ", direct=" + direct + ", buildArea=" + buildArea + ", usefulArea=" + usefulArea
                + ", listPrice=" + listPrice + ", minimumPrice=" + minimumPrice + ", shopPrice=" + shopPrice
                + ", houseTypeId=" + houseTypeId + ", houseStatus=" + houseStatus + ", decorationStandard="
                + decorationStandard + ", description=" + description + ", tags=" + tags + ", photos=" + photos
                + ", shelvTime=" + shelvTime + ", rewardMoney=" + rewardMoney + ", isOpen=" + isOpen + ", houseNum="
                + houseNum + ", presalePermissionInfo=" + presalePermissionInfo + ", bestBenefitsId=" + bestBenefitsId
                + "]";
    }

}
