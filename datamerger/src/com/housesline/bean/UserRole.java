package com.housesline.bean;

/**
 * ��ɫ
 * @author cdh
 *
 */
public class UserRole {

	//�н龭����
	public static final Integer MEDI = 1;
	//�곤
	public static final Integer SHOPOWNER = 2;
	//��ҵ����
	public static final Integer AGENT = 3;
	//��������
	public static final Integer ENGINEER = 4;
	//��������Ա
	public static final Integer SYSTEMADMIN = 5;
	//�ϻ���
	public static final Integer PARTNER = 6;
	//��������
	public static final Integer DIRECTOR = 7;
	
	private String uId;
	private Integer rId;
	
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public Integer getrId() {
		return rId;
	}
	public void setrId(Integer rId) {
		this.rId = rId;
	}
	
	
}
