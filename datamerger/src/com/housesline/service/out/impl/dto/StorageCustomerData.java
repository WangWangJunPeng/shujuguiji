package com.housesline.service.out.impl.dto;

public class StorageCustomerData {

	// �¿ͻ�
	private long newCustomer;
	// �Ͽͻ�
	private long oldCustomer;
	// �ͻ�����ʱ��
	private String customerCreateDate;

	public long getNewCustomer() {
		return newCustomer;
	}

	public void setNewCustomer(long newCustomer) {
		this.newCustomer = newCustomer;
	}

	public long getOldCustomer() {
		return oldCustomer;
	}

	public void setOldCustomer(long oldCustomer) {
		this.oldCustomer = oldCustomer;
	}

	public String getCustomerCreateDate() {
		return customerCreateDate;
	}

	public void setCustomerCreateDate(String customerCreateDate) {
		this.customerCreateDate = customerCreateDate;
	}

	@Override
	public String toString() {
		return "StorageCustomerData [newCustomer=" + newCustomer + ", oldCustomer=" + oldCustomer
				+ ", customerCreateDate=" + customerCreateDate + "]";
	}

}
