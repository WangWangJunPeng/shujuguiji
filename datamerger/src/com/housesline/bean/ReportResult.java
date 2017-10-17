package com.housesline.bean;

/**
 * �ܱ��±���������
 * @author cdh 2017-09-11
 *
 */
public class ReportResult {
	
	//�ܱ����ƣ�Excel���⣩
	private String reportName;
	//��Ŀid
	private String projectId;
	//��Ŀ����
	private String projectName;
	//��ʼʱ��
	private String startTime;
	//����ʱ��
	private String endTime;
	//�ӷ�����
	private Integer visitCount;
	//��Ч�ӷ���
	private String validVisitRate;
	//�׷���Ч��
	private String validNewCuVisitRate;
	//�Ͽͻ��ӷ�ռ��
	private String oldCuVisitRate;
	//��������
	private Integer newCuCount;
	// �ۼ��Ͽͻ�
	private Integer totalOldCuCount;
	// �ۼ��ܴ���
	private Integer totalCuCount;
	// �������������Ϲ�����
	private Integer subscribeHouseCount;
	// �Ϲ������Ƚ��ϼ��ȵ�����/����
	private String subscribeHouseRate;
	// ���������Ϲ����
	private Long subscribeMoney;
	// �Ϲ����Ƚ��ϼ��ȵ�����/����
	private String subscribeMoneyRate;
	// ����ǩԼ����
	private Integer signCount;
	// ����ǩԼ�����Ƚ��ϼ�������/����
	private String signRate;
	// ������ǩԼ���
	private Long signHouseMoney;
	// ������ǩԼ���Ƚ��ϼ��ȵ�����/����
	private String signHouseMoneyRate;
	// �������¿ͻ��ӷ�ǩԼ��
	private String newCustomerSignedRate;
	// ����ǩԼ��
	private String momeryCustomerSignedRate;
	// �Ͽͻ�ǩԼ��
	private String oldCustomerSignedRate;
	// �Ϲ��ͻ�ǩԼ��
	private String contratCuSignedRate;
	
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
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
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}
	public String getValidVisitRate() {
		return validVisitRate;
	}
	public void setValidVisitRate(String validVisitRate) {
		this.validVisitRate = validVisitRate;
	}
	public String getValidNewCuVisitRate() {
		return validNewCuVisitRate;
	}
	public void setValidNewCuVisitRate(String validNewCuVisitRate) {
		this.validNewCuVisitRate = validNewCuVisitRate;
	}
	public String getOldCuVisitRate() {
		return oldCuVisitRate;
	}
	public void setOldCuVisitRate(String oldCuVisitRate) {
		this.oldCuVisitRate = oldCuVisitRate;
	}
	public Integer getNewCuCount() {
		return newCuCount;
	}
	public void setNewCuCount(Integer newCuCount) {
		this.newCuCount = newCuCount;
	}
	public Integer getTotalOldCuCount() {
		return totalOldCuCount;
	}
	public void setTotalOldCuCount(Integer totalOldCuCount) {
		this.totalOldCuCount = totalOldCuCount;
	}
	public Integer getTotalCuCount() {
		return totalCuCount;
	}
	public void setTotalCuCount(Integer totalCuCount) {
		this.totalCuCount = totalCuCount;
	}
	public Integer getSubscribeHouseCount() {
		return subscribeHouseCount;
	}
	public void setSubscribeHouseCount(Integer subscribeHouseCount) {
		this.subscribeHouseCount = subscribeHouseCount;
	}
	public String getSubscribeHouseRate() {
		return subscribeHouseRate;
	}
	public void setSubscribeHouseRate(String subscribeHouseRate) {
		this.subscribeHouseRate = subscribeHouseRate;
	}
	public Long getSubscribeMoney() {
		return subscribeMoney;
	}
	public void setSubscribeMoney(Long subscribeMoney) {
		this.subscribeMoney = subscribeMoney;
	}
	public String getSubscribeMoneyRate() {
		return subscribeMoneyRate;
	}
	public void setSubscribeMoneyRate(String subscribeMoneyRate) {
		this.subscribeMoneyRate = subscribeMoneyRate;
	}
	public Integer getSignCount() {
		return signCount;
	}
	public void setSignCount(Integer signCount) {
		this.signCount = signCount;
	}
	public String getSignRate() {
		return signRate;
	}
	public void setSignRate(String signRate) {
		this.signRate = signRate;
	}
	public Long getSignHouseMoney() {
		return signHouseMoney;
	}
	public void setSignHouseMoney(Long signHouseMoney) {
		this.signHouseMoney = signHouseMoney;
	}
	public String getSignHouseMoneyRate() {
		return signHouseMoneyRate;
	}
	public void setSignHouseMoneyRate(String signHouseMoneyRate) {
		this.signHouseMoneyRate = signHouseMoneyRate;
	}
	public String getNewCustomerSignedRate() {
		return newCustomerSignedRate;
	}
	public void setNewCustomerSignedRate(String newCustomerSignedRate) {
		this.newCustomerSignedRate = newCustomerSignedRate;
	}
	public String getMomeryCustomerSignedRate() {
		return momeryCustomerSignedRate;
	}
	public void setMomeryCustomerSignedRate(String momeryCustomerSignedRate) {
		this.momeryCustomerSignedRate = momeryCustomerSignedRate;
	}
	public String getOldCustomerSignedRate() {
		return oldCustomerSignedRate;
	}
	public void setOldCustomerSignedRate(String oldCustomerSignedRate) {
		this.oldCustomerSignedRate = oldCustomerSignedRate;
	}
	public String getContratCuSignedRate() {
		return contratCuSignedRate;
	}
	public void setContratCuSignedRate(String contratCuSignedRate) {
		this.contratCuSignedRate = contratCuSignedRate;
	}
	@Override
	public String toString() {
		return "ReportResult [projectName=" + projectName + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", visitCount=" + visitCount + ", validVisitRate=" + validVisitRate + ", validNewCuVisitRate="
				+ validNewCuVisitRate + ", oldCuVisitRate=" + oldCuVisitRate + ", newCuCount=" + newCuCount
				+ ", totalOldCuCount=" + totalOldCuCount + ", totalCuCount=" + totalCuCount + ", subscribeHouseCount="
				+ subscribeHouseCount + ", subscribeHouseRate=" + subscribeHouseRate + ", subscribeMoney="
				+ subscribeMoney + ", subscribeMoneyRate=" + subscribeMoneyRate + ", signCount=" + signCount
				+ ", signRate=" + signRate + ", signHouseMoney=" + signHouseMoney + ", signHouseMoneyRate="
				+ signHouseMoneyRate + ", newCustomerSignedRate=" + newCustomerSignedRate
				+ ", momeryCustomerSignedRate=" + momeryCustomerSignedRate + ", oldCustomerSignedRate="
				+ oldCustomerSignedRate + ", contratCuSignedRate=" + contratCuSignedRate + "]";
	}

}
