package com.housesline.bean;

public class ProjectGuideFlowRecord {
	//��ˮ��¼����
	private Integer fr_pgId;
	//�������(��ӣ�0,ɾ����1���޸ģ�2)
	private Integer operateSort;
	//����ʱ��
	private String operateTime;
	//������id
	private String operateUserId;
	//����������
	private String operateUserName;
	//�������ֻ���
	private String operateUserPhone;
	//��ʷ��¼
	private String historyRecord;
	//���º�ļ�¼
	private String newRecord;
	
	public Integer getFr_pgId() {
		return fr_pgId;
	}
	public void setFr_pgId(Integer fr_pgId) {
		this.fr_pgId = fr_pgId;
	}
	public Integer getOperateSort() {
		return operateSort;
	}
	public void setOperateSort(Integer operateSort) {
		this.operateSort = operateSort;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	public String getOperateUserId() {
		return operateUserId;
	}
	public void setOperateUserId(String operateUserId) {
		this.operateUserId = operateUserId;
	}
	public String getOperateUserName() {
		return operateUserName;
	}
	public void setOperateUserName(String operateUserName) {
		this.operateUserName = operateUserName;
	}
	public String getOperateUserPhone() {
		return operateUserPhone;
	}
	public void setOperateUserPhone(String operateUserPhone) {
		this.operateUserPhone = operateUserPhone;
	}
	public String getHistoryRecord() {
		return historyRecord;
	}
	public void setHistoryRecord(String historyRecord) {
		this.historyRecord = historyRecord;
	}
	public String getNewRecord() {
		return newRecord;
	}
	public void setNewRecord(String newRecord) {
		this.newRecord = newRecord;
	}
}
