package com.housesline.bean;

/**
 * ���ݿ�鼯��־��
 * @author cdh - 2017-09-13
 *
 */
public class CollectionRecord {

	//����
	private Integer id;
	//���ɷ�ʽ��0 �ֶ�  1 �Զ�
	private Integer auto;
	//������ֶ���ʽ����ôuserId��Ϊ�� ����Ϊ��
	private String userId;
	//�鼯��������ʱ��
	private String time;
	//�Ƿ�ɹ�
	private String success;
	//�鼯��ʱ/����
	private String elapsedTime;
	
	//�Զ�
	public static final Integer AUTO = 1;
	//�ֶ�
	public static final Integer HAND = 0;
	
	public CollectionRecord() {
		super();
	}
	
	/**
	 * �Զ�
	 * @param auto
	 * @param time
	 * @param success
	 */
	public CollectionRecord(Integer auto, String time, String success, String elapsedTime) {
		super();
		this.auto = auto;
		this.time = time;
		this.success = success;
		this.elapsedTime = elapsedTime;
	}
	

	/**
	 * �ֶ�
	 * @param auto
	 * @param userId
	 * @param time
	 * @param success
	 */
	public CollectionRecord(Integer auto, String userId, String time, String success, String elapsedTime) {
		super();
		this.auto = auto;
		this.userId = userId;
		this.time = time;
		this.success = success;
		this.elapsedTime = elapsedTime;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAuto() {
		return auto;
	}
	public void setAuto(Integer auto) {
		this.auto = auto;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(String elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	@Override
	public String toString() {
		return "CollectionRecord [id=" + id + ", auto=" + auto + ", userId=" + userId + ", time=" + time + ", success="
				+ success + ", elapsedTime=" + elapsedTime + "]";
	}

	
	
	
}
