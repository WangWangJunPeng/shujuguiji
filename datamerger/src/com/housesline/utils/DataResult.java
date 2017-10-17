package com.housesline.utils;

public class DataResult<T> {

	//�Ƿ�ɹ�
	private boolean success;
	//�ɹ����ص�����
	private T data;
	//ʧ�ܷ��صĴ�����Ϣ
	private String error;
	
	
	
	/**
	 * �ɹ�����
	 * @param success
	 * @param data
	 */
	public DataResult(boolean success, T data) {
		super();
		this.success = success;
		this.data = data;
	}
	
	/**
	 * ʧ�ܷ���
	 * @param success
	 * @param error
	 */
	public DataResult(boolean success, String error) {
		super();
		this.success = success;
		this.error = error;
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	@Override
	public String toString() {
		return "DataResult [success=" + success + ", data=" + data + ", error=" + error + "]";
	}
	
	
}
