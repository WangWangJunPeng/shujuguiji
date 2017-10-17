package com.housesline.bean;

/**
 * 数据库归集日志类
 * @author cdh - 2017-09-13
 *
 */
public class CollectionRecord {

	//主键
	private Integer id;
	//生成方式：0 手动  1 自动
	private Integer auto;
	//如果是手动方式：那么userId不为空 否则为空
	private String userId;
	//归集操作结束时间
	private String time;
	//是否成功
	private String success;
	//归集耗时/毫秒
	private String elapsedTime;
	
	//自动
	public static final Integer AUTO = 1;
	//手动
	public static final Integer HAND = 0;
	
	public CollectionRecord() {
		super();
	}
	
	/**
	 * 自动
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
	 * 手动
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
