package com.housesline.bean;

/**
 * 
 * ���ݲ�����¼��
 * @author cdh 2017-07-27
 *
 */
public class DataHandleDynamic {
	//����������
	private Integer id;
	//���ݲ���ʱ��
	private String handleDate;
	//���ݲ���״̬  0���Զ�����ʱ����  1�� �ֶ�
	private Integer handleState;
	//���ݿ������ ��ʱ��������admin  �˹���ӣ�userId
	private String handleUser;
	//�鼯��Ŀ��id����Ŀid��
	private String targetId;
	//�鼯Ŀ�������
	private String targetName;
	//��ϸ��Ϣ
	private String message;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getHandleDate() {
		return handleDate;
	}
	public void setHandleDate(String handleDate) {
		this.handleDate = handleDate;
	}
	public Integer getHandleState() {
		return handleState;
	}
	public void setHandleState(Integer handleState) {
		this.handleState = handleState;
	}
	public String getHandleUser() {
		return handleUser;
	}
	public void setHandleUser(String handleUser) {
		this.handleUser = handleUser;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public String getTargetName() {
		return targetName;
	}
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "DataHandleDynamic [id=" + id + ", handleDate=" + handleDate + ", handleState=" + handleState
				+ ", handleUser=" + handleUser + ", targetId=" + targetId + ", targetName=" + targetName + ", message="
				+ message + "]";
	}
	
	
}
