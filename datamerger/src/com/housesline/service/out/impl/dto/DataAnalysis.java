package com.housesline.service.out.impl.dto;

import java.util.List;

/*
 * ���ݷ�����
 * @author cdh-2017-06-13
 * */
public class DataAnalysis {

	//�ٵ���
	private long visitAgain;
	//�µǣ�(�����ͻ�)
	private long newCustomer;
	//�µ�(ָ)��(�����ͻ�ָ���ӷ�)
	private long newCustomerAppoint;
	//��ʧ
	private long loseCustomer;
	//��ʧ(ָ)
	private long loseCustomerAppoint;
	//��������ʱ��:�����ǵ�ѡ���ʱ��������������¾�ÿ��Ϊ�ڵ㣬��С�������¾���Ϊ�ڵ�
	private String dataDate;
	
	public long getVisitAgain() {
		return visitAgain;
	}
	public void setVisitAgain(long visitAgain) {
		this.visitAgain = visitAgain;
	}
	public long getNewCustomer() {
		return newCustomer;
	}
	public void setNewCustomer(long newCustomer) {
		this.newCustomer = newCustomer;
	}
	public long getNewCustomerAppoint() {
		return newCustomerAppoint;
	}
	public void setNewCustomerAppoint(long newCustomerAppoint) {
		this.newCustomerAppoint = newCustomerAppoint;
	}
	public long getLoseCustomer() {
		return loseCustomer;
	}
	public void setLoseCustomer(long loseCustomer) {
		this.loseCustomer = loseCustomer;
	}
	public long getLoseCustomerAppoint() {
		return loseCustomerAppoint;
	}
	public void setLoseCustomerAppoint(long loseCustomerAppoint) {
		this.loseCustomerAppoint = loseCustomerAppoint;
	}
	public String getDataDate() {
		return dataDate;
	}
	public void setDataDate(String dataDate) {
		this.dataDate = dataDate;
	}
	
	@Override
	public String toString() {
		return "DataAnalysis [visitAgain=" + visitAgain + ", newCustomer=" + newCustomer + ", newCustomerAppoint="
				+ newCustomerAppoint + ", loseCustomer=" + loseCustomer + ", loseCustomerAppoint=" + loseCustomerAppoint
				+ ", dataDate=" + dataDate + "]";
	}
	
}
