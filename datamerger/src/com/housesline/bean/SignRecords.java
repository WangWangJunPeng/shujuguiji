package com.housesline.bean;


/**
*
* @author wjp 2017-02-20
*/
public class SignRecords {
	
	//ǩ����¼��,������
	private Integer recordId;
	//ǩ���û�����
	private String userId;
	//������λ����
	private String parentId;
	//ǩ��ʱ��(ʱ����)
	private String signInTime;
	//ǩ��ʱ��(ʱ����)
	private String signOutTime;
	//ǩ��״̬,1:ǩ��,2:ǩ��
//	private Integer type;
	
	public Integer getRecordId() {
		return recordId;
	}
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getSignInTime() {
		return signInTime;
	}
	public void setSignInTime(String signInTime) {
		this.signInTime = signInTime;
	}
	public String getSignOutTime() {
		return signOutTime;
	}
	public void setSignOutTime(String signOutTime) {
		this.signOutTime = signOutTime;
	}
//	public Integer getType() {
//		return type;
//	}
//	public void setType(Integer type) {
//		this.type = type;
//	}
	
	@Override
	public String toString() {
		return "SignRecords [recordId=" + recordId + ", userId=" + userId + ", parentId=" + parentId + ", signInTime="
				+ signInTime + ", signOutTime=" + signOutTime + "]";
	}
	
}
