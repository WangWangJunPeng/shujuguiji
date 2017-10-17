package com.housesline.service.out.impl.dto;

public class StorageCustomerData {

	// 新客户
	private long newCustomer;
	// 老客户
	private long oldCustomer;
	// 客户创建时间
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
