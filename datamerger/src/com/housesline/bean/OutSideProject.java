package com.housesline.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.housesline.dao.BaseDao;
import com.housesline.service.project.ProjectCreateService;
import com.housesline.utils.DateUtil;
import com.housesline.utils.SysContent;

public class OutSideProject {
	//ʹ��copDate��Ϊ����
	//���� ��ʽ yyyy-MM-dd
	private String copDate;
	//�鼯����ʱ�� yyyy-MM-dd HH:mm:ss
	private String collDateTime;
	//������(��ʹ�ø��ֶ�)
	private Integer guideCount;
	//������������
	private Integer visitedCount;
	//��������
	private Integer recordCustomerCount;
	//����������
	private Integer recordVisitCount;
	//����δ������
	private Integer recordNotVisitCount;
	//�����Ϲ���
	private Integer guiCustomerRecordCount;
	//�����Ϲ���
	private Integer outSideCustomerRecordCount;
	
	
	//����ͬ���Ϲ���
	private Integer outSideAgreeRecordCount;
	//�����ܾ��Ϲ���
	private Integer outSideRefuseRecordCount;
	//������ǩԼ��(�¶�)
	private Integer outSideWaitSignCount;
	//����������
	private Integer outSideRevokeOrderCount;
	//�����Ϲ�����
	private Integer outSideSubscribeHouseCount;
	//�����Ϲ����
	private Double outSideSubscribeMoney;
	//�����Ϲ�������
	private Double outSideSubscribeGetMoney;
	//�����Ϲ�������Դ��ֵ
	private Double outSideSubscribeLockHouseMoney;
	//����������Դ��ֵ
	private Double outSideGiveUpHouseMoney;
	//�����ȴ�ǩԼ��Դ��ֵ
	private Double outSideWaitSignHouseMoney;
	//ǩԼ��Դ��ֵ
	private Double outSideSignHouseMoney;
	
	//�����Ϲ��ܿͻ���
	private Integer outSideRecordCuCount;
	//�����¿ͻ��Ϲ���
	private Integer outSideNewCuRecordCount;
	//�����Ͽͻ��Ϲ���
	private Integer outSideOldCuRecordCount;
	//ǩԼ�ܿͻ���
	private Integer outSideSignCuCount;
	//�����¿ͻ�ǩԼ��
	private Integer outSideNewCuSignCount;
	//�����Ͽͻ�ǩԼ��
	private Integer outSideOldSignCount;
	
	
	
	
	//�����ɽ���(�н鱸������Ч���ã���ҵ���ʷ���)ǩԼ��
	private Integer guideCustomerVisitCount;
	//�����ɽ���(�н鱸�����н鷢��)�ⳡ�ɽ� ǩԼ��
	private Integer outSideDealCount;
	//ƽ̨�ͻ�ǩԼ�� = �����ɽ��� + �����ɽ���
	private Integer systemCusSignedCount;
	//�ڳ��ɽ���(�н��ޱ�������ҵ���ʷ���)
	private Integer intFiledToDealNum;
	//�ܳɽ��� = �����ɽ������ⳡ�ɽ����� + �ڳ��ɽ���
	private Integer totalDealCount;
	//�����ַ�����������Դ��id
	private String guidedHouseNum;
	//�����ַ��������÷�Դ��id
	private String visitedHouseNum;
	//�����ַ����������ŵ��id
	private String guidedShopId;
	//�����ַ����������ŵ���н��id
	private String guidedShopAgentId;
	//�����ַ��������õ��ŵ��id
	private String visitedShopId;
	//�����ַ��������õ��ŵ��н��id
	private String visitedShopAgentId;
	//Ԥ���ֶ�
	private String reserveField;
	
	public OutSideProject() {
		super();
		this.guideCount = 0;//������(��ʹ�ø��ֶ�)
		this.visitedCount = 0;//������������
		this.recordCustomerCount = 0;//��������
		this.recordVisitCount = 0;//����������
		this.recordNotVisitCount = 0;//����δ������
		this.guiCustomerRecordCount = 0;//�����Ϲ���
		this.outSideCustomerRecordCount = 0;//�����Ϲ���
		this.guideCustomerVisitCount = 0;//�����ɽ���(�н鱸������Ч���ã���ҵ���ʷ���)
		this.outSideDealCount = 0;//�����ɽ���(�н鱸�����н鷢��)�ⳡ�ɽ�
		this.intFiledToDealNum = 0;//�ڳ��ɽ���(�н��ޱ�������ҵ���ʷ���)
		this.totalDealCount = 0;//�ܳɽ��� = �����ɽ������ⳡ�ɽ����� + �ڳ��ɽ���
		this.systemCusSignedCount = 0;//ƽ̨�ͻ�ǩԼ�� = �����ɽ��� + �����ɽ���
		this.outSideAgreeRecordCount = 0;//����ͬ���Ϲ���
		this.outSideRefuseRecordCount = 0;//�����ܾ��Ϲ���
		this.outSideWaitSignCount = 0;//������ǩԼ��(�¶�)
		this.outSideRevokeOrderCount = 0;//����������
		this.outSideSubscribeHouseCount = 0;//�����Ϲ�����
		this.outSideSubscribeMoney = 0d;//�����Ϲ����
		this.outSideSubscribeGetMoney = 0d;//�����Ϲ�������
		this.outSideSubscribeLockHouseMoney = 0d;//�����Ϲ�������Դ��ֵ
		this.outSideGiveUpHouseMoney = 0d;//����������Դ��ֵ
		this.outSideWaitSignHouseMoney = 0d;//�����ȴ�ǩԼ��Դ��ֵ
		this.outSideSignHouseMoney = 0d;//ǩԼ��Դ��ֵ
		this.outSideRecordCuCount = 0;//�����Ϲ��ܿͻ���
		this.outSideNewCuRecordCount = 0;//�����¿ͻ��Ϲ���
		this.outSideOldCuRecordCount = 0;//�����Ͽͻ��Ϲ���
		this.outSideSignCuCount = 0;//ǩԼ�ܿͻ���
		this.outSideNewCuSignCount = 0;//�����¿ͻ�ǩԼ��
		this.outSideOldSignCount = 0;//�����Ͽͻ�ǩԼ��
	}


	/**����Ԥ���ֶ�**/
	private String grVisiteRate = "0.00";
	private String outSideDealRate = "0.00";
	private String sysCusSignedRate = "0.00";
	private String systemEnterBuyRate = "0.00";
	private String outSideLeadCusRate = "0.00";
	
	
	
	
	/**
	 * ���ݷ�װ����
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @param baseDao
	 * @return
	 */
	public OutSideProject creatMoreObjTurnOne(String proId, String startDate, String endDate, BaseDao baseDao, ProjectCreateService projectCreateService) {
		return creatMoreObjTurnOne(proId, startDate, endDate, baseDao, null, projectCreateService);
	}
	
	
	/**
	 * ���ݷ�װ����
	 * cdh
	 * @param proId
	 * @param baseDao
	 * @param oss
	 * @return
	 */
	public OutSideProject creatMoreObjTurnOne(String proId, BaseDao baseDao, List<OutSideProject> oss, ProjectCreateService projectCreateService) {
		
		return creatMoreObjTurnOne(proId, null, null, baseDao, oss, projectCreateService);
	}
	
	/**
	 * ���ݷ�װ����
	 * @param proId
	 * @param startDate
	 * @param endDate
	 * @param baseDao
	 * @return
	 */
	public OutSideProject creatMoreObjTurnOne(String proId, String startDate, String endDate, BaseDao baseDao, List<OutSideProject> oss, ProjectCreateService projectCreateService) {

		List<OutSideProject> list = new ArrayList<>();
		
		String thisDate = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		boolean flag = false;
		if(startDate.equals(endDate)){
			if(thisDate.equals(startDate)){
				flag = true;
			}
		}
		
		
		
		
		if(flag){
			list.add(projectCreateService.selectOutSideProject(proId, thisDate));
		}else{
			
			if(oss == null){
				if(startDate!=null && !startDate.equals("") && endDate!=null && !endDate.equals("")){
					startDate = DateUtil.format(DateUtil.parse(startDate, DateUtil.PATTERN_CLASSICAL_SIMPLE), DateUtil.PATTERN_CLASSICAL_SIMPLE);
					endDate = DateUtil.format(DateUtil.parse(endDate, DateUtil.PATTERN_CLASSICAL_SIMPLE), DateUtil.PATTERN_CLASSICAL_SIMPLE);
					list = baseDao.selectOutSideProjectForProject(proId, startDate, endDate, null);
				}else{
					list = baseDao.selectOutSideProjectForProject(proId, null, null, DateUtil.format(new Date(),DateUtil.PATTERN_CLASSICAL_SIMPLE));
				}
			}else{
				list = oss;
			}
		}
		
		for(OutSideProject osp : list){
			this.guideCount += osp.getGuideCount();//������(��ʹ�ø��ֶ�)
			this.visitedCount += osp.getVisitedCount();//������������
			this.recordCustomerCount += osp.getRecordCustomerCount();//��������
			this.recordVisitCount += osp.getRecordVisitCount();//����������
			this.recordNotVisitCount += osp.getRecordNotVisitCount();//����δ������
			this.guideCustomerVisitCount += osp.getGuideCustomerVisitCount();//�����ɽ���(�н鱸������Ч���ã���ҵ���ʷ���)
			this.outSideDealCount += osp.getOutSideDealCount();//�����ɽ���(�н鱸�����н鷢��)�ⳡ�ɽ�
			this.intFiledToDealNum += osp.getIntFiledToDealNum();//�ڳ��ɽ���(�н��ޱ�������ҵ���ʷ���)
			this.outSideCustomerRecordCount += osp.getOutSideCustomerRecordCount();//�����Ϲ���
			this.outSideAgreeRecordCount += osp.getOutSideAgreeRecordCount();//����ͬ���Ϲ���
			this.outSideRefuseRecordCount += osp.getOutSideRefuseRecordCount();//�����ܾ��Ϲ���
			this.outSideWaitSignCount += osp.getOutSideWaitSignCount();//������ǩԼ��(�¶�)
			this.outSideRevokeOrderCount += osp.getOutSideRevokeOrderCount();//����������
			this.outSideSubscribeHouseCount += osp.getOutSideSubscribeHouseCount();//�����Ϲ�����
			this.outSideSubscribeMoney += osp.getOutSideSubscribeMoney();//�����Ϲ����
			this.outSideSubscribeGetMoney += osp.getOutSideSubscribeGetMoney();//�����Ϲ�������
			this.outSideSubscribeLockHouseMoney += osp.getOutSideSubscribeLockHouseMoney();//�����Ϲ�������Դ��ֵ
			this.outSideGiveUpHouseMoney += osp.getOutSideGiveUpHouseMoney();//����������Դ��ֵ
			this.outSideWaitSignHouseMoney += osp.getOutSideWaitSignHouseMoney();//�����ȴ�ǩԼ��Դ��ֵ
			this.outSideSignHouseMoney += osp.getOutSideSignHouseMoney();//ǩԼ��Դ��ֵ
			this.outSideRecordCuCount += osp.getOutSideRecordCuCount();//�����Ϲ��ܿͻ���
			this.outSideNewCuRecordCount += osp.getOutSideNewCuRecordCount();//�����¿ͻ��Ϲ���
			this.outSideOldCuRecordCount += osp.getOutSideOldCuRecordCount();//�����Ͽͻ��Ϲ���
			this.outSideSignCuCount += osp.getOutSideSignCuCount();//ǩԼ�ܿͻ���
			this.outSideNewCuSignCount += osp.getOutSideNewCuSignCount();//�����¿ͻ�ǩԼ��
			this.outSideOldSignCount += osp.getOutSideOldSignCount();//�����Ͽͻ�ǩԼ��
			
		}
		this.totalDealCount = this.intFiledToDealNum + this.outSideDealCount + this.guideCustomerVisitCount;//�ܳɽ��� = �����ɽ������ⳡ�ɽ����� + �ڳ��ɽ���
		this.systemCusSignedCount = this.guideCustomerVisitCount + this.outSideDealCount;//ƽ̨�ͻ�ǩԼ�����ɽ�����
		//��������
		if(this.recordCustomerCount>0){
			//����������
			double d =  (double)this.recordVisitCount / (double)this.recordCustomerCount * 100;
			this.grVisiteRate = SysContent.get2Double(d);
		}
		
		//�ɽ�����
		if(totalDealCount > 0){
			//�ⳡ�ɽ���
			double d =  (double)this.outSideDealCount / (double)this.totalDealCount * 100;
			this.outSideDealRate = SysContent.get2Double(d);
			
			//ƽ̨�ͻ��Ϲ�ռ��
			double d0 =  (double)this.outSideCustomerRecordCount / (double)this.totalDealCount * 100;
			this.systemEnterBuyRate = SysContent.get2Double(d0);
			
			//ƽ̨�ͻ�ǩԼ��
			double d1 =  (double)this.systemCusSignedCount / (double)this.totalDealCount * 100;
			this.sysCusSignedRate = SysContent.get2Double(d1);
		}
		return this;
	}
	
	
	public String getCopDate() {
		return copDate;
	}
	public void setCopDate(String copDate) {
		this.copDate = copDate;
	}
	public String getCollDateTime() {
		return collDateTime;
	}
	public void setCollDateTime(String collDateTime) {
		this.collDateTime = collDateTime;
	}
	public Integer getGuideCount() {
		return guideCount;
	}
	public void setGuideCount(Integer guideCount) {
		this.guideCount = guideCount;
	}
	public Integer getVisitedCount() {
		return visitedCount;
	}
	public void setVisitedCount(Integer visitedCount) {
		this.visitedCount = visitedCount;
	}
	public String getGuidedHouseNum() {
		return guidedHouseNum;
	}
	public void setGuidedHouseNum(String guidedHouseNum) {
		this.guidedHouseNum = guidedHouseNum;
	}
	public String getVisitedHouseNum() {
		return visitedHouseNum;
	}
	public void setVisitedHouseNum(String visitedHouseNum) {
		this.visitedHouseNum = visitedHouseNum;
	}
	public String getGuidedShopId() {
		return guidedShopId;
	}
	public void setGuidedShopId(String guidedShopId) {
		this.guidedShopId = guidedShopId;
	}
	public String getGuidedShopAgentId() {
		return guidedShopAgentId;
	}
	public void setGuidedShopAgentId(String guidedShopAgentId) {
		this.guidedShopAgentId = guidedShopAgentId;
	}
	public String getVisitedShopId() {
		return visitedShopId;
	}
	public void setVisitedShopId(String visitedShopId) {
		this.visitedShopId = visitedShopId;
	}
	public String getVisitedShopAgentId() {
		return visitedShopAgentId;
	}
	public void setVisitedShopAgentId(String visitedShopAgentId) {
		this.visitedShopAgentId = visitedShopAgentId;
	}
	public String getReserveField() {
		return reserveField;
	}
	public void setReserveField(String reserveField) {
		this.reserveField = reserveField;
	}
	public Integer getRecordCustomerCount() {
		return recordCustomerCount;
	}
	public void setRecordCustomerCount(Integer recordCustomerCount) {
		this.recordCustomerCount = recordCustomerCount;
	}
	public Integer getRecordVisitCount() {
		return recordVisitCount;
	}
	public void setRecordVisitCount(Integer recordVisitCount) {
		this.recordVisitCount = recordVisitCount;
	}
	public Integer getRecordNotVisitCount() {
		return recordNotVisitCount;
	}
	public void setRecordNotVisitCount(Integer recordNotVisitCount) {
		this.recordNotVisitCount = recordNotVisitCount;
	}
	public Integer getGuideCustomerVisitCount() {
		return guideCustomerVisitCount;
	}
	public void setGuideCustomerVisitCount(Integer guideCustomerVisitCount) {
		this.guideCustomerVisitCount = guideCustomerVisitCount;
	}
	public Integer getOutSideDealCount() {
		return outSideDealCount;
	}
	public void setOutSideDealCount(Integer outSideDealCount) {
		this.outSideDealCount = outSideDealCount;
	}
	public Integer getIntFiledToDealNum() {
		return intFiledToDealNum;
	}
	public void setIntFiledToDealNum(Integer intFiledToDealNum) {
		this.intFiledToDealNum = intFiledToDealNum;
	}
	
	public Integer getGuiCustomerRecordCount() {
		return guiCustomerRecordCount;
	}
	public void setGuiCustomerRecordCount(Integer guiCustomerRecordCount) {
		this.guiCustomerRecordCount = guiCustomerRecordCount;
	}
	public Integer getOutSideCustomerRecordCount() {
		return outSideCustomerRecordCount;
	}
	public void setOutSideCustomerRecordCount(Integer outSideCustomerRecordCount) {
		this.outSideCustomerRecordCount = outSideCustomerRecordCount;
	}
	public Integer getTotalDealCount() {
		return totalDealCount;
	}
	public void setTotalDealCount(Integer totalDealCount) {
		this.totalDealCount = totalDealCount;
	}
	public String getGrVisiteRate() {
		return grVisiteRate;
	}
	public void setGrVisiteRate(String grVisiteRate) {
		this.grVisiteRate = grVisiteRate;
	}
	public String getOutSideDealRate() {
		return outSideDealRate;
	}
	public void setOutSideDealRate(String outSideDealRate) {
		this.outSideDealRate = outSideDealRate;
	}
	public Integer getSystemCusSignedCount() {
		return systemCusSignedCount;
	}
	public void setSystemCusSignedCount(Integer systemCusSignedCount) {
		this.systemCusSignedCount = systemCusSignedCount;
	}
	public String getSysCusSignedRate() {
		return sysCusSignedRate;
	}
	public void setSysCusSignedRate(String sysCusSignedRate) {
		this.sysCusSignedRate = sysCusSignedRate;
	}
	public String getSystemEnterBuyRate() {
		return systemEnterBuyRate;
	}
	public void setSystemEnterBuyRate(String systemEnterBuyRate) {
		this.systemEnterBuyRate = systemEnterBuyRate;
	}


	public Integer getOutSideAgreeRecordCount() {
		return outSideAgreeRecordCount;
	}


	public void setOutSideAgreeRecordCount(Integer outSideAgreeRecordCount) {
		this.outSideAgreeRecordCount = outSideAgreeRecordCount;
	}


	public Integer getOutSideRefuseRecordCount() {
		return outSideRefuseRecordCount;
	}


	public void setOutSideRefuseRecordCount(Integer outSideRefuseRecordCount) {
		this.outSideRefuseRecordCount = outSideRefuseRecordCount;
	}


	public Integer getOutSideWaitSignCount() {
		return outSideWaitSignCount;
	}


	public void setOutSideWaitSignCount(Integer outSideWaitSignCount) {
		this.outSideWaitSignCount = outSideWaitSignCount;
	}


	public Integer getOutSideRevokeOrderCount() {
		return outSideRevokeOrderCount;
	}


	public void setOutSideRevokeOrderCount(Integer outSideRevokeOrderCount) {
		this.outSideRevokeOrderCount = outSideRevokeOrderCount;
	}


	public Integer getOutSideSubscribeHouseCount() {
		return outSideSubscribeHouseCount;
	}


	public void setOutSideSubscribeHouseCount(Integer outSideSubscribeHouseCount) {
		this.outSideSubscribeHouseCount = outSideSubscribeHouseCount;
	}


	public Double getOutSideSubscribeMoney() {
		return outSideSubscribeMoney;
	}


	public void setOutSideSubscribeMoney(Double outSideSubscribeMoney) {
		this.outSideSubscribeMoney = outSideSubscribeMoney;
	}


	public Double getOutSideSubscribeGetMoney() {
		return outSideSubscribeGetMoney;
	}


	public void setOutSideSubscribeGetMoney(Double outSideSubscribeGetMoney) {
		this.outSideSubscribeGetMoney = outSideSubscribeGetMoney;
	}


	public Double getOutSideSubscribeLockHouseMoney() {
		return outSideSubscribeLockHouseMoney;
	}


	public void setOutSideSubscribeLockHouseMoney(Double outSideSubscribeLockHouseMoney) {
		this.outSideSubscribeLockHouseMoney = outSideSubscribeLockHouseMoney;
	}


	public Double getOutSideGiveUpHouseMoney() {
		return outSideGiveUpHouseMoney;
	}


	public void setOutSideGiveUpHouseMoney(Double outSideGiveUpHouseMoney) {
		this.outSideGiveUpHouseMoney = outSideGiveUpHouseMoney;
	}


	public Double getOutSideWaitSignHouseMoney() {
		return outSideWaitSignHouseMoney;
	}


	public void setOutSideWaitSignHouseMoney(Double outSideWaitSignHouseMoney) {
		this.outSideWaitSignHouseMoney = outSideWaitSignHouseMoney;
	}


	public String getOutSideLeadCusRate() {
		return outSideLeadCusRate;
	}


	public void setOutSideLeadCusRate(String outSideLeadCusRate) {
		this.outSideLeadCusRate = outSideLeadCusRate;
	}


	public Double getOutSideSignHouseMoney() {
		return outSideSignHouseMoney;
	}


	public void setOutSideSignHouseMoney(Double outSideSignHouseMoney) {
		this.outSideSignHouseMoney = outSideSignHouseMoney;
	}


	public Integer getOutSideRecordCuCount() {
		return outSideRecordCuCount;
	}


	public void setOutSideRecordCuCount(Integer outSideRecordCuCount) {
		this.outSideRecordCuCount = outSideRecordCuCount;
	}


	public Integer getOutSideNewCuRecordCount() {
		return outSideNewCuRecordCount;
	}


	public void setOutSideNewCuRecordCount(Integer outSideNewCuRecordCount) {
		this.outSideNewCuRecordCount = outSideNewCuRecordCount;
	}


	public Integer getOutSideOldCuRecordCount() {
		return outSideOldCuRecordCount;
	}


	public void setOutSideOldCuRecordCount(Integer outSideOldCuRecordCount) {
		this.outSideOldCuRecordCount = outSideOldCuRecordCount;
	}


	public Integer getOutSideSignCuCount() {
		return outSideSignCuCount;
	}


	public void setOutSideSignCuCount(Integer outSideSignCuCount) {
		this.outSideSignCuCount = outSideSignCuCount;
	}


	public Integer getOutSideNewCuSignCount() {
		return outSideNewCuSignCount;
	}


	public void setOutSideNewCuSignCount(Integer outSideNewCuSignCount) {
		this.outSideNewCuSignCount = outSideNewCuSignCount;
	}


	public Integer getOutSideOldSignCount() {
		return outSideOldSignCount;
	}


	public void setOutSideOldSignCount(Integer outSideOldSignCount) {
		this.outSideOldSignCount = outSideOldSignCount;
	}


	@Override
	public String toString() {
		return "OutSideProject [copDate=" + copDate + ", collDateTime=" + collDateTime
				+ ", guideCount=" + guideCount + ", visitedCount=" + visitedCount + ", recordCustomerCount="
				+ recordCustomerCount + ", recordVisitCount=" + recordVisitCount + ", recordNotVisitCount="
				+ recordNotVisitCount + ", guideCustomerVisitCount=" + guideCustomerVisitCount + ", outSideDealCount="
				+ outSideDealCount + ", guidedHouseNum=" + guidedHouseNum + ", visitedHouseNum=" + visitedHouseNum
				+ ", guidedShopId=" + guidedShopId + ", guidedShopAgentId=" + guidedShopAgentId + ", visitedShopId="
				+ visitedShopId + ", visitedShopAgentId=" + visitedShopAgentId + ", reserveField=" + reserveField + "]";
	}
	
}
