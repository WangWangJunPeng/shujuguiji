package com.housesline.bean;


public class ContractRecordsFlowRecord {
	
	//������ˮ����
	//����
	public static final Integer RECORDSTATUS_APPLY = 2001;
	//ͬ��
	public static final Integer RECORDSTATUS_AGREE = 2002;
	//�ϴ�ƾ֤,��ȷ��
	public static final Integer RECORDSTATUS_GETMONEY = 2003;
	//��ǩԼ,�¶�
	public static final Integer RECORDSTATUS_REDYSIGN = 2004;
	//��ǩԼ
	public static final Integer RECORDSTATUS_SIGNED = 2005;
	//���
	public static final Integer RECORDSTATUS_REFUSE = 2006;
	//����
	public static final Integer RECORDSTATUS_BACKOUT = 2007;
	
	
	// ��ˮ��¼����
	private Integer fr_crId;
	// �������(��ӣ�1001,ɾ����1002���޸ģ�1003)
	private Integer operateSort;
	//������ˮ״̬(���룺2001;ͬ�⣺2002;�ϴ�ƾ֤����ȷ�ϣ�2003;��ǩԼ���¶���2004;��ǩԼ��2005;�����ܾ���2006;����ȡ����2007)
	private Integer orderSort;
	// ����ʱ��
	private String operateTime;
	// ������id
	private String operateUserId;
	// ����������
	private String operateUserName;
	// �������ֻ���
	private String operateUserPhone;
	// ��ʷ��¼
	private String historyRecord;
	// ���º�ļ�¼
	private String newRecord;
	
	private Integer recordNo;
	
	private String projectId;
	
	public Integer getFr_crId() {
		return fr_crId;
	}
	public void setFr_crId(Integer fr_crId) {
		this.fr_crId = fr_crId;
	}
	public Integer getOperateSort() {
		return operateSort;
	}
	public void setOperateSort(Integer operateSort) {
		this.operateSort = operateSort;
	}
	public Integer getOrderSort() {
		return orderSort;
	}
	public void setOrderSort(Integer orderSort) {
		this.orderSort = orderSort;
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
	public Integer getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(Integer recordNo) {
		this.recordNo = recordNo;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	
}
