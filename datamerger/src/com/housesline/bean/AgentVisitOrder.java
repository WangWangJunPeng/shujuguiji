package com.housesline.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

import com.housesline.dao.BaseDao;
import com.housesline.service.project.ProjectCreateService;
import com.housesline.utils.DateUtil;
import com.housesline.utils.SysContent;

/**
 *  ���ʵ��ö����鼯
 *  ��ÿ����ҵ����ÿ��ΪԪ��鼯��������
 * @author cdh 2017-07-27
 *
 */
public class AgentVisitOrder {
	
	//ʹ��cvDate+agentId��������
	/**------------ ����----------- */
	//��������  yyyy-MM-dd
	private String cvDate;
	//�鼯����ʱ�� yyyy-MM-dd HH:mm:ss
	private String collDateTime;
	//��ҵ����id
	private String agentId;
	//��ҵ��������
	private String agentName;
	//��ҵ���ʵ绰
	private String agentPhone;
	//��ҵ����״̬ 0:��������1:����
	private Integer agentStatus;
	//������
	private Integer visitCount;
	//��Ч�ӷ���
	private Integer validVisitCount;
	//��Ч������ = ������ - ��Ч������
	private Integer unValidVisitCount;
	//����������
	private Integer newVisitCount;
	//�ٵ���
	private Integer visitAgain;
	//���ε�����
	private Integer secondVisitCount;
	//�������ϵ��ã��������Σ�
	private Integer moreVisitCount;
	//ָ����������������
	private Integer appointCount;
	//�¿ͻ�ͨ���ӷ���
	private Integer newWayVisitCount;
	//�¿ͻ�ͨ����Ч�ӷ���
	private Integer validNewWayVisitCount;
	//�¿ͻ�ͨ��ָ���ӷ�
	private Integer appointNewWayVisitCount;
	//�Ͽͻ�ͨ���ӷ���
	private Integer oldWayVisitCount;
	//�Ͽͻ�ͨ����Ч�ӷ���
	private Integer validOldWayVisitCount;
	//�Ͽͻ�ͨ��ָ���ӷ���
	private Integer appointOldWayVisitCount;
	//�����
	private Integer replaceCount;
	//����ӷ������
	private Integer orderReplaceVisitCount;
	//ָ���ӷ������ = ����� - ����ӷ������
	private Integer appointReplaceVisitCount;
	//ȷ���Ͽͻ�����������һ�ε�����д״̬1  �ڶ��ε���Ҳ��1��
	private Integer affirmOldCustomerVisitNum;
	//ָ����ʧ
	private Integer appointLosedCount;
	//ָ����Ч����
	private Integer appointValidVisitCount;
	//��ʧ��
	private Integer losedCount;
	//ָ���µ�
	private Integer newAppointCount;
	//�¿ͻ��ӷ�ʱ��
	private String newCustomerVisitTime;
	//�Ͽͻ��ӷ�ʱ��
	private String oldCustomerVisitTime;
	//���ʱ��
	private String replaceVisitTime;
	//�ܽӷ�ʱ��
	private String totalVisitTime;
	//ƽ���ӷ�ʱ��
	private String averageVisitTime;
	//����id�����ϣ�
	private String visitId;
	//�¿ͻ��ӷ���
	private Integer newCustomerVisitCount;
	//�¿ͻ���Ч�ӷ�
	private Integer validNewCustomerVisitCount;
	//�Ͽͻ��ӷ���
	private Integer oldCustomerVisitCount;
	//�Ͽͻ���Ч�ӷ���
	private Integer validOldCustomerVisitCount;

	
	/**------------ ���� ----------- */
	//�Ϲ���
	private Integer enterBuyCount;
	//ͬ���Ϲ���
	private Integer agreeEnterCount;
	//�ܾ��Ϲ���
	private Integer refuseEnterCount;
	//�¶���
	private Integer payCount;
	//ǩԼ��
	private Integer signCount;
	//������
	private Integer revokeOrderCount;
	//�¶���Դ��ֵ
	private Double confirmHouseMoney;
	//���Ϲ�����
	private Integer subscribeHouseCount;
	//�Ϲ����
	private Double subscribeMoney;
	//�Ϲ�������
	private Double subscribeGetMoney;
	//�Ϲ�������Դ��ֵ
	private Double subscribeLockHouseMoney;
	//����ǩԼ��
	private Integer giveUpSignCount;
	//�ȴ�ǩԼ��
	private Integer waitSignCount;
	//��ǩԼ��Դ��ֵ
	private Double signHouseMoney;
	//����ǩԼ��Դ��ֵ
	private Double giveUpHouseMoney;
	//�ȴ�ǩԼ��Դ��ֵ
	private Double waitSignHouseMoney;
	//ǩԼ�Ķ���id�����ϣ�
	private String signedOrderId;
	//�ܾ��Ķ���id�����ϣ�
	private String refusedOrderId;
	//�����Ķ��������ϣ�
	private String revokeOrderId;
	//�¶��Ķ��� �����ϣ�
	private String payOrderId;
	
	/**------------ ���� -------------*/
	//���������� �¿ͻ���
	private Integer newAddCollCustomerCount;
	//�ܴ�����
	private Integer grandTotalCollCustomerCount;
	//�Ͽͻ���
	private Integer grandTotalOldCustomerCount;
	//�ܴ��ͣ���ֹ��ǰʱ�������ۼƣ�
	private Integer totalCustomerCount;
	//�Ͽͻ���(��ֹ��ǰʱ�������ۼ�)
	private Integer totalOldCustomerCount;
	//�����������ÿͻ���
	private Integer newTwoVisitCustomerCount;
	//ǩԼ�ܿͻ���(���ø��ֶ�)
	private Integer customerReturnBackVisitNum;
	//�Ͽͻ�ǩԼ��
	private Integer oldCustomerSignedCount;
	//�¿ͻ�ǩԼ��
	private Integer newCustomerSignedCount;
	//ƽ̨������
	private Integer platformCustomerCount;
	//���Ϲ��ͻ���
	private Integer subscribeCustomerCount;
	//�¿ͻ��Ϲ���
	private Integer newSubscribeCustomerCount;
	//�Ͽͻ��Ϲ���
	private Integer oldSubscribeCustomerCount;
	//���óɽ���
	private Integer visitToDealCount;
	//���ͳɽ���
	private Integer momeryCuDealCount;
	//�����ַ������������͵�id
	private String newAddCollCustomerId;
	//�����ַ������ܴ��Ϳͻ���id
	private String grandTotalCollCustomerId;
	//�����ַ������Ͽͻ���id
	private String grandTotalOldCustomerId;
	
	/**------------- ���� ---------------*/
	//ǩ��ʱ��
	private String signInTime;
	//ǩ��ʱ��
	private String signOutTime;
	//Ԥ���ֶ�
	private String reserveField;
	
	/**-------------���ñ����ֶ�------------**/
	//ÿ��ƽ���ӷ�ʱ��
	private String everyOnceAverageReplaceTimeLong = "0";
	//ÿ��ƽ���ӷ�ʱ��
	private String everyDayAverageReplaceTimeLong = "0";
	//��Ч�ӷ���
	private String validRate = "0.00";
	//�Ͽͻ�ͨ��ռ��
	private String oldWayRate = "0.00";
	//�¿ͻ�ͨ����Чռ��
	private String newWayValidRate = "0.00";
	//�Ͽͻ�ͨ����Чռ��
	private String oldWayValidRate = "0.00";
	//�Ͽͻ��ӷô���ռ��
	private String oldReceptRate = "0.00";
	//�¿ͻ��ӷô���ռ��
	private String newReceptRate = "0.00";
	//ָ���ӷ���
	private String appointReceptRate = "0.00";
	//�¿ͻ�ͨ��ָ���ӷ���
	private String newWayAppointReceptRate = "0.00";
	//�Ͽͻ�ͨ��ָ���ӷ���
	private String oldWayAppointReceptRate = "0.00";
	//ָ����Ч�ӷ���
	private String appointValidReceptRate = "0.00";
	//ָ����Ч�ӷ���
	private String appointUnValidReceptRate = "0.00";
	//�������
	private String totalReplaceReceptRate = "0.00";
	//�ͻ���ͷ��
	private String customerTurnHandRate = "0.00";
	//�����ͻ��Ϲ���
	private String newAddCusEnterBuyRate = "0.00";
	//�Ͽͻ��Ϲ���
	private String oldCusEnterBuyRate = "0.00";
	//���óɽ���
	private String visitDealRate = "0.00";
	//���ͳɽ���
	private String grandCusDealRate = "0.00";
	//�Ϲ�ǩԼ��
	private String enterBuySignRate = "0.00";
	//�ⳡ���ͱ�
	//private String outSideLeadCusRate = "0.00";
	//�¿ͻ�����Ч�ӷ�
	private Integer unValidNewCustomerVisitCount;
	//�¶�ǩԼ��(��ǩԼ��/���¶���)
	private String paySignRate = "0.00";
	
	
	
	
	/**
	 * ���ݷ�װ��
	 * @param proId
	 * @param agentId
	 * @param startDate
	 * @param endDate
	 * @param baseDao
	 * @return
	 */
	public AgentVisitOrder creatMoreObjTurnOne(String proId,String agentId, String startDate, String endDate, BaseDao baseDao, ProjectCreateService projectCreateService){
		return creatMoreObjTurnOne(proId, agentId, startDate, endDate, baseDao, null, null,projectCreateService);
	}
	
	/**
	 * ���ݷ�װ��
	 * @param proId
	 * @param agentId
	 * @param startDate
	 * @param endDate
	 * @param baseDao
	 * @param osp
	 * @return
	 */
	public AgentVisitOrder creatMoreObjTurnOne(String proId,String agentId, String startDate, String endDate, BaseDao baseDao, OutSideProject osp, ProjectCreateService projectCreateService){
		return creatMoreObjTurnOne(proId, agentId, startDate, endDate, baseDao, osp, null,projectCreateService);
	}
	
	
	/**
	 * ���ݷ�װ��
	 * cdh
	 * @param proId
	 * @param baseDao
	 * @param aos
	 * @return
	 */
	public AgentVisitOrder creatMoreObjTurnOne(String proId, String startDate, String endDate, BaseDao baseDao, List<AgentVisitOrder> aos, ProjectCreateService projectCreateService){
		return creatMoreObjTurnOne(proId, null, startDate, endDate, baseDao, null, aos,projectCreateService);
	}
	
	/**
	 * ���ݷ�װ����
	 * @param proId
	 * @param agentId
	 * @param startDate
	 * @param endDate
	 * @param baseDao
	 * @return
	 */
	public AgentVisitOrder creatMoreObjTurnOne(String proId,String agentId, String startDate, String endDate, BaseDao baseDao, OutSideProject osp, List<AgentVisitOrder> aos, ProjectCreateService projectCreateService){
		int visitCount = 0;//�ӷ���
		int totalVisitTime = 0;
		String strAverageLongTime = "0";
		int validReceCount = 0;
		int unValidReceCount = 0;
		int oldCusCount = 0;
		int newCustomerVisitTime = 0;
		int oldCustomerVisitTime = 0;
		int replaceVisitTime = 0;
		String validRate = "0.00";
		String grVisiteRate = "0.00";
		String oldCusRate = "0.00";
		String replaceRate = "0.00";
		long averageVisitTime = 0L;
		if(this.visitCount==null){
			this.visitCount = 0;
		}
		if(this.validVisitCount==null){
			this.validVisitCount = 0;
		}
		if(this.replaceCount==null){
			this.replaceCount = 0;
		}
		if(this.orderReplaceVisitCount==null){
			this.orderReplaceVisitCount = 0;
		}
		if(this.newWayVisitCount==null){
			this.newWayVisitCount = 0;
		}
		if(this.oldWayVisitCount==null){
			this.oldWayVisitCount = 0;
		}
		if(this.appointCount==null){
			this.appointCount = 0;
		}
		if(this.grandTotalCollCustomerCount==null){
			this.grandTotalCollCustomerCount = 0;
		}
		if(this.newAddCollCustomerCount==null){
			this.newAddCollCustomerCount = 0;
		}
		if(this.grandTotalOldCustomerCount==null){
			this.grandTotalOldCustomerCount = 0;
		}
		if(this.subscribeCustomerCount==null){
			this.subscribeCustomerCount = 0;
		}
		if(this.payCount == null){
			this.payCount = 0;
		}
		if(this.confirmHouseMoney==null){
			this.confirmHouseMoney = 0.00;
		}
		if(this.signHouseMoney==null){
			this.signHouseMoney = 0.00;
		}
		if(this.newCustomerVisitCount==null){
			this.newCustomerVisitCount = 0;
		}
		if(this.unValidNewCustomerVisitCount==null){
			this.unValidNewCustomerVisitCount = 0;
		}
		if(this.appointLosedCount==null){
			this.appointLosedCount = 0;
		}
		if(this.appointValidVisitCount==null){
			this.appointValidVisitCount = 0;
		}
		if(this.subscribeGetMoney==null){
			this.subscribeGetMoney = 0.00;
		}
		if(this.validNewCustomerVisitCount==null){
			this.validNewCustomerVisitCount = 0;
		}
		if(this.enterBuyCount==null){
			this.enterBuyCount = 0;
		}
		if(this.signCount==null){
			this.signCount = 0;
		}
		if(this.newCustomerSignedCount==null){
			this.newCustomerSignedCount = 0;
		}
		if(this.losedCount==null){
			this.losedCount = 0;
		}
		if(this.momeryCuDealCount==null){
			this.momeryCuDealCount = 0;
		}
		if(this.agreeEnterCount == null){
			this.agreeEnterCount = 0;
		}
		if(this.refuseEnterCount == null){
			this.refuseEnterCount = 0;
		}
		if(this.revokeOrderCount == null){
			this.revokeOrderCount = 0;
		}
		if(this.totalCustomerCount == null){
			this.totalCustomerCount = 0;
		}
		if(this.totalOldCustomerCount == null){
			this.totalOldCustomerCount = 0;
		}
		if(this.oldCustomerSignedCount == null){
			this.oldCustomerSignedCount = 0;
		}
		if(this.newCustomerSignedCount == null){
			this.newCustomerSignedCount = 0;
		}
		if(this.platformCustomerCount == null){
			this.platformCustomerCount =0;
		}
		if(this.subscribeMoney == null){
			this.subscribeMoney = 0d;
		}
		if(this.subscribeLockHouseMoney == null){
			this.subscribeLockHouseMoney = 0d;	
		}
		if(this.giveUpSignCount == null){
			this.giveUpSignCount = 0;
		}
		if(this.giveUpHouseMoney == null){
			this.giveUpHouseMoney = 0d;
		}
		if(this.waitSignHouseMoney == null){
			this.waitSignHouseMoney = 0d;
		}
		if(this.newSubscribeCustomerCount == null){
			this.newSubscribeCustomerCount = 0;
		}
		if(this.customerReturnBackVisitNum == null){
			this.customerReturnBackVisitNum = 0;
		}
		if(this.subscribeHouseCount == null){
			this.subscribeHouseCount = 0;
		}
		if(this.newVisitCount == null){
			this.newVisitCount = 0;
		}
		if(this.secondVisitCount == null){
			this.secondVisitCount = 0;
		}
		if(this.moreVisitCount == null){
			this.moreVisitCount = 0;
		}
		if(this.newAppointCount == null){
			this.newAppointCount = 0;
		}
		if(this.averageVisitTime == null){
			this.averageVisitTime = "";
		}
		if(this.oldSubscribeCustomerCount == null){
			this.oldSubscribeCustomerCount = 0;
		}
		
		if(StringUtils.isEmpty(agentId) && osp != null){
			this.enterBuyCount += osp.getOutSideCustomerRecordCount();
			this.agreeEnterCount += osp.getOutSideAgreeRecordCount();
			this.refuseEnterCount += osp.getOutSideRefuseRecordCount();
			this.subscribeHouseCount += osp.getOutSideSubscribeHouseCount();
			this.payCount += osp.getOutSideWaitSignCount();
			this.giveUpSignCount += osp.getOutSideRevokeOrderCount();
			this.subscribeHouseCount += osp.getOutSideRevokeOrderCount();
			this.subscribeMoney += osp.getOutSideSubscribeMoney();
			this.subscribeGetMoney += osp.getOutSideSubscribeGetMoney();
			this.subscribeLockHouseMoney += osp.getOutSideSubscribeLockHouseMoney();
			this.giveUpHouseMoney += osp.getOutSideGiveUpHouseMoney();
			this.waitSignHouseMoney += osp.getOutSideWaitSignHouseMoney();
			this.signHouseMoney += osp.getOutSideSignHouseMoney();
			this.subscribeCustomerCount += osp.getOutSideRecordCuCount();
			this.newSubscribeCustomerCount += osp.getOutSideNewCuRecordCount();
			this.oldSubscribeCustomerCount += osp.getOutSideOldCuRecordCount();
			this.customerReturnBackVisitNum += osp.getOutSideSignCuCount();
			this.oldCustomerSignedCount += osp.getOutSideNewCuSignCount();
			this.newCustomerSignedCount += osp.getOutSideOldSignCount();
		}
		//�ж�ѡ���ʱ���ǵ����
		String thisDate = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		boolean flag = false;
		if(startDate.equals(endDate)){
			if(thisDate.equals(startDate)){
				flag = true;
			}
		}
		
		List<AgentVisitOrder> list = new ArrayList<>();
		if(flag){
			list = projectCreateService.getAnalysisOfData(proId, thisDate);
		}else{
			
			if(aos == null){
				if(startDate!=null && !startDate.equals("") && endDate!=null && !endDate.equals("")){
					startDate = DateUtil.format(DateUtil.parse(startDate, DateUtil.PATTERN_CLASSICAL_SIMPLE), DateUtil.PATTERN_CLASSICAL_SIMPLE);
					endDate = DateUtil.format(DateUtil.parse(endDate, DateUtil.PATTERN_CLASSICAL_SIMPLE), DateUtil.PATTERN_CLASSICAL_SIMPLE);
					list = baseDao.selectAgentVisitOrderListForProject(proId, startDate, endDate, null);
				}else{
					list = baseDao.selectAgentVisitOrderListForProject(proId, null, null, DateUtil.format(new Date(),DateUtil.PATTERN_CLASSICAL_SIMPLE));
				}
			}else{
				list = aos;
			}
		}
		for(AgentVisitOrder avo : list){
			if(agentId!=null && !avo.getAgentId().equals(agentId)){
				continue;
			}
			averageVisitTime += new Long(avo.getAverageVisitTime());
			this.newAppointCount += avo.getNewAppointCount();
			this.moreVisitCount += avo.getMoreVisitCount();
			this.secondVisitCount += avo.getSecondVisitCount();
			this.newVisitCount += avo.getNewVisitCount();
			this.agreeEnterCount += avo.getAgreeEnterCount();
			this.refuseEnterCount += avo.getRefuseEnterCount();
			this.revokeOrderCount += avo.getRevokeOrderCount();
			this.totalCustomerCount += avo.getTotalCustomerCount();
			this.totalOldCustomerCount += avo.getTotalOldCustomerCount();
			this.oldCustomerSignedCount += avo.getOldCustomerSignedCount();
			this.platformCustomerCount += avo.getPlatformCustomerCount();
			
			this.newCustomerSignedCount += avo.getNewCustomerSignedCount();//�¿ͻ�ǩԼ��
			this.momeryCuDealCount += avo.getMomeryCuDealCount();//���ͳɽ���
			this.losedCount += avo.getLosedCount();//��ʧ�ͻ���
			this.enterBuyCount += avo.getEnterBuyCount();//���Ϲ���
			this.visitCount += avo.getVisitCount();//�ܽӷô���
			totalVisitTime += Integer.valueOf(avo.getTotalVisitTime());//�ܽӷ�ʱ��
			this.validVisitCount += avo.getValidVisitCount();//��Ч������
			this.grandTotalOldCustomerCount += avo.getGrandTotalOldCustomerCount();//�Ͽͻ���
			this.newWayVisitCount += avo.getNewWayVisitCount();//�¿ͻ�ͨ��������
			this.oldWayVisitCount += avo.getOldWayVisitCount();//�Ͽͻ�ͨ��������
			if(this.validNewWayVisitCount==null){
				this.validNewWayVisitCount = 0;
			}
			this.validNewWayVisitCount += avo.getValidNewWayVisitCount();//�¿ͻ�ͨ����Ч����
			if(this.validOldWayVisitCount==null){
				this.validOldWayVisitCount = 0;
			}
			this.validOldWayVisitCount += avo.getValidOldWayVisitCount();//�Ͽͻ�ͨ����Ч����
			this.newAddCollCustomerCount += avo.getNewAddCollCustomerCount();//����������
			if(this.affirmOldCustomerVisitNum==null){
				this.affirmOldCustomerVisitNum = 0;
			}
			this.affirmOldCustomerVisitNum += avo.getAffirmOldCustomerVisitNum();//ȷ���Ͽͻ���
			this.appointCount += avo.getAppointCount();//ָ���ӷ���
			if(this.appointNewWayVisitCount==null){
				this.appointNewWayVisitCount = 0;
			}
			this.appointNewWayVisitCount += avo.getAppointNewWayVisitCount();//�¿ͻ�ͨ��ָ���ӷ�
			if(this.appointOldWayVisitCount==null){
				this.appointOldWayVisitCount = 0;
			}
			this.appointOldWayVisitCount += avo.getAppointOldWayVisitCount();//�Ͽͻ�ͨ��ָ���ӷ�
			this.appointLosedCount += avo.getAppointLosedCount();//ָ����Ч�ӷ���
			this.replaceCount += avo.getReplaceCount();//�������
			this.orderReplaceVisitCount = avo.getOrderReplaceVisitCount();//����ӷ������
			newCustomerVisitTime += Integer.valueOf(avo.getNewCustomerVisitTime());//�¿ͻ��ӷ�ʱ��
			oldCustomerVisitTime += Integer.valueOf(avo.getOldCustomerVisitTime());//�Ͽͻ��ӷ�ʱ��
			replaceVisitTime += Integer.valueOf(avo.getReplaceVisitTime());//��ӽӷ�ʱ��
			if(this.newTwoVisitCustomerCount==null){
				this.newTwoVisitCustomerCount = 0;
			}
			this.newTwoVisitCustomerCount += avo.getNewTwoVisitCustomerCount();//�����������ÿͻ���
			this.subscribeCustomerCount += avo.getSubscribeCustomerCount();//���Ϲ��ͻ���
			if(this.newSubscribeCustomerCount==null){
				this.newSubscribeCustomerCount = 0;
			}
			this.newSubscribeCustomerCount += avo.getNewSubscribeCustomerCount();//�����ͻ��Ϲ��� //�׷ÿͻ��Ϲ���
			if(this.subscribeHouseCount==null){
				this.subscribeHouseCount = 0;
			}
			this.subscribeHouseCount += avo.getSubscribeHouseCount();//�Ϲ�����
			if(this.subscribeMoney==null){
				this.subscribeMoney = 0.00;
			}
			this.subscribeMoney += avo.getSubscribeMoney();//�Ϲ����
			this.subscribeGetMoney += avo.getSubscribeGetMoney();//�Ϲ�������
			if(this.subscribeLockHouseMoney==null){
				this.subscribeLockHouseMoney = 0.00;
			}
			this.subscribeLockHouseMoney += avo.getSubscribeLockHouseMoney();//�Ϲ�������Դ��ֵ
			if(this.oldSubscribeCustomerCount==null){
				this.oldSubscribeCustomerCount = 0;
			}
			this.oldSubscribeCustomerCount += avo.getOldSubscribeCustomerCount();//�Ͽͻ��Ϲ���
			this.signCount += avo.getSignCount();//��ǩԼ��
			if(this.giveUpSignCount==null){
				this.giveUpSignCount = 0;
			}
			this.giveUpSignCount += avo.getGiveUpSignCount();//����ǩԼ��
			if(this.giveUpHouseMoney==null){
				this.giveUpHouseMoney = 0.00;
			}
			this.giveUpHouseMoney += avo.getGiveUpHouseMoney();//����ǩԼ��Դ��ֵ
			if(this.waitSignCount==null){
				this.waitSignCount = 0;
			}
			this.waitSignCount +=avo.getWaitSignCount(); //��ǩԼ��
			if(this.waitSignHouseMoney==null){
				this.waitSignHouseMoney = 0.00;
			}
			this.waitSignHouseMoney += avo.getWaitSignHouseMoney();//��ǩԼ��Դ��ֵ
			if(this.visitToDealCount==null){
				this.visitToDealCount = 0;
			}
			this.visitToDealCount += avo.getVisitToDealCount();//���óɽ���
			if(this.customerReturnBackVisitNum==null){
				this.customerReturnBackVisitNum = 0;
			}
			this.customerReturnBackVisitNum = avo.getCustomerReturnBackVisitNum();//ǩԼ�ͻ���
			this.grandTotalCollCustomerCount += avo.getGrandTotalCollCustomerCount();//�ܴ�����
			if(this.oldCustomerVisitCount==null){
				this.oldCustomerVisitCount = 0;
			}
			this.oldCustomerVisitCount += avo.getOldCustomerVisitCount();//�Ͽͻ��ӷ���
			this.payCount += avo.getPayCount();//�����¶���
			this.confirmHouseMoney += avo.getConfirmHouseMoney();//���¶�������Դ��ֵ������ȷ�Ϻ�ģ�
			this.signHouseMoney += avo.getSignHouseMoney();//��ǩԼ������Դ��ֵ 
			this.newCustomerVisitCount += avo.getNewCustomerVisitCount();//�¿ͻ�������
			this.validNewCustomerVisitCount += avo.getValidNewCustomerVisitCount();//�¿ͻ���Ч������
		}
		this.averageVisitTime = new Long(averageVisitTime).toString();
		this.totalVisitTime = String.valueOf(totalVisitTime);//�ܽӷ�ʱ��
		this.unValidVisitCount = this.visitCount - this.validVisitCount;//��Ч�ӷ���
		this.appointReplaceVisitCount = this.replaceCount - this.orderReplaceVisitCount;//ָ���ӷ������
		this.unValidNewCustomerVisitCount = this.newCustomerVisitCount - this.validNewCustomerVisitCount;//�¿ͻ���Ч������
		this.appointValidVisitCount = this.appointCount - this.appointLosedCount;//ָ����Ч�ӷ���
		//ÿ��ƽ���ӷ�ʱ��
		if(this.visitCount>0){
			this.everyOnceAverageReplaceTimeLong = String.valueOf(Integer.valueOf(this.totalVisitTime) / this.visitCount);
		}
		//ÿ��ƽ���ӷ�ʱ��
		if(list.size()>0){
			this.everyDayAverageReplaceTimeLong = String.valueOf(Integer.valueOf(this.totalVisitTime)/DateUtil.getTwoDateEveryDay(startDate, endDate).size());
		}
		//�¿ͻ��ӷ�ʱ��
		this.newCustomerVisitTime = String.valueOf(newCustomerVisitTime);
		//�Ͽͻ��ӷ�ʱ��
		this.oldCustomerVisitTime = String.valueOf(totalVisitTime - newCustomerVisitTime);
		//��ӽӷ�ʱ��
		this.replaceVisitTime = String.valueOf(replaceVisitTime);
		//�ܽӷ���
		if(this.visitCount > 0){
			//��Ч�ӷ���
			double d =  (double)this.validVisitCount / (double)this.visitCount * 100;
			this.validRate = SysContent.get2Double(d);
			
			//�Ͽͻ�ͨ��ռ��
			double d1 =  (double)this.oldWayVisitCount / (double)this.visitCount * 100;
			this.oldWayRate = SysContent.get2Double(d1);
			
			//�Ͽͻ��ӷô���ռ��
			double d2 =  (double)this.oldCustomerVisitCount / (double)this.visitCount * 100;
			this.oldReceptRate = SysContent.get2Double(d2);
			
			//�¿ͻ��ӷô���ռ��
			double d3 =  (double)this.newCustomerVisitCount / (double)this.visitCount * 100;
			this.newReceptRate = SysContent.get2Double(d3);
			
			//ָ���ӷ���
			double d4 =  (double)this.appointCount / (double)this.visitCount * 100;
			this.appointReceptRate = SysContent.get2Double(d4);
			
			//�������
			double d5 =  (double)this.replaceCount / (double)this.visitCount * 100;
			this.totalReplaceReceptRate = SysContent.get2Double(d5);
			
			
		}
		
		//�¿ͻ�ͨ���ӷ���
		if(this.newWayVisitCount>0){
			//�¿ͻ�ͨ����Ч�ӷ�ռ��
			double d0 =  (double)this.validNewWayVisitCount / (double)this.newWayVisitCount * 100;
			this.newWayValidRate = SysContent.get2Double(d0);
		}
		
		//�Ͽͻ�ͨ���ӷ���
		if(this.oldWayVisitCount>0){
			//�Ͽͻ�ͨ����Ч�ӷ�ռ��
			double d0 =  (double)this.validOldWayVisitCount / (double)this.oldWayVisitCount * 100;
			this.oldWayValidRate = SysContent.get2Double(d0);
		}
		
		//ָ���ӷ���
		if(this.appointCount>0){
			//�¿ͻ�ͨ��ָ���ӷ���
			double d0 =  (double)this.appointNewWayVisitCount / (double)this.appointCount * 100;
			this.newWayAppointReceptRate = SysContent.get2Double(d0);
			
			//�Ͽͻ�ͨ��ָ���ӷ���
			double d1 =  (double)this.appointOldWayVisitCount / (double)this.appointCount * 100;
			this.oldWayAppointReceptRate = SysContent.get2Double(d1);
			
			//ָ����Ч�ӷ���
			double d2 =  (double)this.appointLosedCount / (double)this.appointCount * 100;
			this.appointUnValidReceptRate = SysContent.get2Double(d2);
			
			//ָ����Ч�ӷ���
			double d3 =  (double)this.appointValidVisitCount / (double)this.appointCount * 100;
			this.appointValidReceptRate = SysContent.get2Double(d3);
			
		}
		
		//�ܴ�����
		if(this.grandTotalCollCustomerCount>0){
			//�ͻ���ͷ��
			double d0 =  (double)this.newTwoVisitCustomerCount / (double)this.grandTotalCollCustomerCount * 100;
			this.customerTurnHandRate = SysContent.get2Double(d0);
			
			//�ⳡ���ͱ�
			//double d2 =  (double)this.recordVisitCount / (double)this.grandTotalCollCustomerCount * 100;
			//this.outSideLeadCusRate = SysContent.get2Double(d2);
			
		}
		
		//���Ϲ���
		if(this.enterBuyCount>0){
			//�����ͻ��Ϲ���
			double d0 =  (double)this.newSubscribeCustomerCount / (double)this.enterBuyCount * 100;
			this.newAddCusEnterBuyRate = SysContent.get2Double(d0);
			
			//�Ͽͻ��Ϲ���
			double d1 =  (double)this.oldSubscribeCustomerCount / (double)this.enterBuyCount * 100;
			this.oldCusEnterBuyRate = SysContent.get2Double(d1);
			
			//�Ϲ�ǩԼ��
			double d2 =  (double)this.signCount / (double)this.enterBuyCount * 100;
			this.enterBuySignRate = SysContent.get2Double(d2);
		}
		/**
		//�����ܴ�����
		if(this.newAddCollCustomerCount>0){
			//�����ͻ��Ϲ���
			double d0 =  (double)this.newSubscribeCustomerCount / (double)this.newAddCollCustomerCount * 100;
			this.newAddCusEnterBuyRate = SysContent.get2Double(d0);
		}
		
		//�Ͽͻ���
		if(this.grandTotalOldCustomerCount>0){
			//�Ͽͻ��Ϲ���
			double d0 =  (double)this.oldSubscribeCustomerCount / (double)this.grandTotalOldCustomerCount * 100;
			this.oldCusEnterBuyRate = SysContent.get2Double(d0);
		}
		*/
		/**
		//�Ϲ��ܿͻ���
		if(this.subscribeCustomerCount>0){
			//�Ϲ�ǩԼ��
			double d0 =  (double)this.customerReturnBackVisitNum / (double)this.subscribeCustomerCount * 100;
			this.enterBuySignRate = SysContent.get2Double(d0);
		}
		*/
		//
		if(this.payCount>0){
			//�¶�ǩԼ��
			double d0 =  (double)this.signCount / (double)this.payCount * 100;
			this.paySignRate = SysContent.get2Double(d0);
		}
		
		
		if(this.newAddCollCustomerCount+this.losedCount>0){
			//���óɽ��� = ǩԼ�¿ͻ���/��������+��ʧ��
			double d6 =  (double)this.newCustomerSignedCount / (double)(this.newAddCollCustomerCount+this.losedCount) * 100;
			this.visitDealRate = SysContent.get2Double(d6);

		}
		
		//����������
		if(this.newAddCollCustomerCount>0){
			//���ͳɽ���
			double d1 =  (double)this.momeryCuDealCount / (double)this.newAddCollCustomerCount * 100;
			this.grandCusDealRate = SysContent.get2Double(d1);
			
		}
		
		return this;
	}
	
	
	/**
	 * ���ݷ�װ����
	 * @param baseDao 
	 * @param endDate 
	 * @param startDate 
	 * @param proId 
	public AgentVisitOrder creatMoreObjTurnOne(String proId,String agentId, String startDate, String endDate, BaseDao baseDao){
		int visitCount = 0;//�ӷ���
		int totalVisitTime = 0;
		String strAverageLongTime = "0";
		int validReceCount = 0;
		int unValidReceCount = 0;
		int oldCusCount = 0;
		int newCustomerVisitTime = 0;
		int oldCustomerVisitTime = 0;
		int replaceVisitTime = 0;
		String validRate = "0.00";
		String grVisiteRate = "0.00";
		String oldCusRate = "0.00";
		String replaceRate = "0.00";
		if(this.visitCount==null){
			this.visitCount = 0;
		}
		if(this.validVisitCount==null){
			this.validVisitCount = 0;
		}
		if(this.replaceCount==null){
			this.replaceCount = 0;
		}
		if(this.orderReplaceVisitCount==null){
			this.orderReplaceVisitCount = 0;
		}
		if(this.newWayVisitCount==null){
			this.newWayVisitCount = 0;
		}
		if(this.oldWayVisitCount==null){
			this.oldWayVisitCount = 0;
		}
		if(this.appointCount==null){
			this.appointCount = 0;
		}
		if(this.grandTotalCollCustomerCount==null){
			this.grandTotalCollCustomerCount = 0;
		}
		if(this.newAddCollCustomerCount==null){
			this.newAddCollCustomerCount = 0;
		}
		if(this.grandTotalOldCustomerCount==null){
			this.grandTotalOldCustomerCount = 0;
		}
		if(this.subscribeCustomerCount==null){
			this.subscribeCustomerCount = 0;
		}
		if(this.payCount == null){
			this.payCount = 0;
		}
		if(this.confirmHouseMoney==null){
			this.confirmHouseMoney = 0.00;
		}
		if(this.signHouseMoney==null){
			this.signHouseMoney = 0.00;
		}
		if(this.newCustomerVisitCount==null){
			this.newCustomerVisitCount = 0;
		}
		if(this.unValidNewCustomerVisitCount==null){
			this.unValidNewCustomerVisitCount = 0;
		}
		if(this.appointLosedCount==null){
			this.appointLosedCount = 0;
		}
		if(this.appointValidVisitCount==null){
			this.appointValidVisitCount = 0;
		}
		if(this.subscribeGetMoney==null){
			this.subscribeGetMoney = 0.00;
		}
		if(this.validNewCustomerVisitCount==null){
			this.validNewCustomerVisitCount = 0;
		}
		if(this.signCount==null){
			this.signCount = 0;
		}
		if(this.losedCount==null){
			this.losedCount = 0;
		}
		if(this.momeryCuDealCount==null){
			this.momeryCuDealCount = 0;
		}
		List<AgentVisitOrder> list = new ArrayList<>();
		if(startDate!=null && !startDate.equals("") && endDate!=null && !endDate.equals("")){
			startDate = DateUtil.format(DateUtil.parse(startDate, DateUtil.PATTERN_CLASSICAL_SIMPLE), DateUtil.PATTERN_CLASSICAL_SIMPLE);
			endDate = DateUtil.format(DateUtil.parse(endDate, DateUtil.PATTERN_CLASSICAL_SIMPLE), DateUtil.PATTERN_CLASSICAL_SIMPLE);
			list = baseDao.selectAgentVisitOrderListForProject(proId, startDate, endDate, null);
		}else{
			list = baseDao.selectAgentVisitOrderListForProject(proId, null, null, DateUtil.format(new Date(),DateUtil.PATTERN_CLASSICAL_SIMPLE));
		}
		for(AgentVisitOrder avo : list){
			if(!avo.getAgentId().equals(agentId)){
				continue;
			}
			this.newCustomerSignedCount += avo.getNewCustomerSignedCount();//�¿ͻ�ǩԼ��
			this.momeryCuDealCount += avo.getMomeryCuDealCount();//���ͳɽ���
			this.losedCount += avo.getLosedCount();//��ʧ�ͻ���
			this.enterBuyCount += avo.getEnterBuyCount();//���Ϲ���
			this.visitCount += avo.getVisitCount();//�ܽӷô���
			totalVisitTime += Integer.valueOf(avo.getTotalVisitTime());//�ܽӷ�ʱ��
			this.validVisitCount += avo.getValidVisitCount();//��Ч������
			this.grandTotalOldCustomerCount += avo.getGrandTotalOldCustomerCount();//�Ͽͻ���
			this.newWayVisitCount += avo.getNewWayVisitCount();//�¿ͻ�ͨ��������
			this.oldWayVisitCount += avo.getOldWayVisitCount();//�Ͽͻ�ͨ��������
			if(this.validNewWayVisitCount==null){
				this.validNewWayVisitCount = 0;
			}
			this.validNewWayVisitCount += avo.getValidNewWayVisitCount();//�¿ͻ�ͨ����Ч����
			if(this.validOldWayVisitCount==null){
				this.validOldWayVisitCount = 0;
			}
			this.validOldWayVisitCount += avo.getValidOldWayVisitCount();//�Ͽͻ�ͨ����Ч����
			this.newAddCollCustomerCount += avo.getNewAddCollCustomerCount();//����������
			if(this.affirmOldCustomerVisitNum==null){
				this.affirmOldCustomerVisitNum = 0;
			}
			this.affirmOldCustomerVisitNum += avo.getAffirmOldCustomerVisitNum();//ȷ���Ͽͻ���
			this.appointCount += avo.getAppointCount();//ָ���ӷ���
			if(this.appointNewWayVisitCount==null){
				this.appointNewWayVisitCount = 0;
			}
			this.appointNewWayVisitCount += avo.getAppointNewWayVisitCount();//�¿ͻ�ͨ��ָ���ӷ�
			if(this.appointOldWayVisitCount==null){
				this.appointOldWayVisitCount = 0;
			}
			this.appointOldWayVisitCount += avo.getAppointOldWayVisitCount();//�Ͽͻ�ͨ��ָ���ӷ�
			this.appointLosedCount += avo.getAppointLosedCount();//ָ����Ч�ӷ���
			this.replaceCount += avo.getReplaceCount();//�������
			this.orderReplaceVisitCount = avo.getOrderReplaceVisitCount();//����ӷ������
			newCustomerVisitTime += Integer.valueOf(avo.getNewCustomerVisitTime());//�¿ͻ��ӷ�ʱ��
			oldCustomerVisitTime = Integer.valueOf(avo.getOldCustomerVisitTime());//�Ͽͻ��ӷ�ʱ��
			replaceVisitTime = Integer.valueOf(avo.getReplaceVisitTime());//��ӽӷ�ʱ��
			if(this.newTwoVisitCustomerCount==null){
				this.newTwoVisitCustomerCount = 0;
			}
			this.newTwoVisitCustomerCount += avo.getNewTwoVisitCustomerCount();//�����������ÿͻ���
			this.subscribeCustomerCount += avo.getSubscribeCustomerCount();//���Ϲ��ͻ���
			if(this.newSubscribeCustomerCount==null){
				this.newSubscribeCustomerCount = 0;
			}
			this.newSubscribeCustomerCount += avo.getNewSubscribeCustomerCount();//�����ͻ��Ϲ��� //�׷ÿͻ��Ϲ���
			if(this.subscribeHouseCount==null){
				this.subscribeHouseCount = 0;
			}
			this.subscribeHouseCount += avo.getSubscribeHouseCount();//�Ϲ�����
			if(this.subscribeMoney==null){
				this.subscribeMoney = 0.00;
			}
			this.subscribeMoney += avo.getSubscribeMoney();//�Ϲ����
			this.subscribeGetMoney += avo.getSubscribeGetMoney();//�Ϲ�������
			if(this.subscribeLockHouseMoney==null){
				this.subscribeLockHouseMoney = 0.00;
			}
			this.subscribeLockHouseMoney += avo.getSubscribeLockHouseMoney();//�Ϲ�������Դ��ֵ
			if(this.oldSubscribeCustomerCount==null){
				this.oldSubscribeCustomerCount = 0;
			}
			this.oldSubscribeCustomerCount += avo.getOldSubscribeCustomerCount();//�Ͽͻ��Ϲ���
			this.signCount += avo.getSignCount();//��ǩԼ��
			if(this.giveUpSignCount==null){
				this.giveUpSignCount = 0;
			}
			this.giveUpSignCount += avo.getGiveUpSignCount();//����ǩԼ��
			if(this.giveUpHouseMoney==null){
				this.giveUpHouseMoney = 0.00;
			}
			this.giveUpHouseMoney += avo.getGiveUpHouseMoney();//����ǩԼ��Դ��ֵ
			if(this.waitSignCount==null){
				this.waitSignCount = 0;
			}
			this.waitSignCount +=avo.getWaitSignCount(); //��ǩԼ��
			if(this.waitSignHouseMoney==null){
				this.waitSignHouseMoney = 0.00;
			}
			this.waitSignHouseMoney += avo.getWaitSignHouseMoney();//��ǩԼ��Դ��ֵ
			if(this.visitToDealCount==null){
				this.visitToDealCount = 0;
			}
			this.visitToDealCount += avo.getVisitToDealCount();//���óɽ���
			if(this.customerReturnBackVisitNum==null){
				this.customerReturnBackVisitNum = 0;
			}
			this.customerReturnBackVisitNum = avo.getCustomerReturnBackVisitNum();//ǩԼ�ͻ���
			this.grandTotalCollCustomerCount += avo.getGrandTotalCollCustomerCount();//�ܴ�����
			if(this.oldCustomerVisitCount==null){
				this.oldCustomerVisitCount = 0;
			}
			this.oldCustomerVisitCount += avo.getOldCustomerVisitCount();//�Ͽͻ��ӷ���
			this.payCount += avo.getPayCount();//�����¶���
			this.confirmHouseMoney += avo.getConfirmHouseMoney();//���¶�������Դ��ֵ������ȷ�Ϻ�ģ�
			this.signHouseMoney += avo.getSignHouseMoney();//��ǩԼ������Դ��ֵ 
			this.newCustomerVisitCount += avo.getNewCustomerVisitCount();//�¿ͻ�������
			this.validNewCustomerVisitCount += avo.getValidNewCustomerVisitCount();//�¿ͻ���Ч������
		}
		this.totalVisitTime = String.valueOf(totalVisitTime);//�ܽӷ�ʱ��
		this.unValidVisitCount = this.visitCount - this.validVisitCount;//��Ч�ӷ���
		this.appointReplaceVisitCount = this.replaceCount - this.orderReplaceVisitCount;//ָ���ӷ������
		this.unValidNewCustomerVisitCount = this.newCustomerVisitCount - this.validNewCustomerVisitCount;//�¿ͻ���Ч������
		this.appointValidVisitCount = this.appointCount - this.appointLosedCount;//ָ����Ч�ӷ���
		//ÿ��ƽ���ӷ�ʱ��
		if(this.visitCount>0){
			this.everyOnceAverageReplaceTimeLong = String.valueOf(Integer.valueOf(this.totalVisitTime) / this.visitCount);
		}
		//ÿ��ƽ���ӷ�ʱ��
		if(list.size()>0){
			this.everyDayAverageReplaceTimeLong = String.valueOf(Integer.valueOf(this.totalVisitTime)/list.size());
		}
		//�¿ͻ��ӷ�ʱ��
		this.newCustomerVisitTime = String.valueOf(newCustomerVisitTime);
		//�Ͽͻ��ӷ�ʱ��
		this.oldCustomerVisitTime = String.valueOf(totalVisitTime - newCustomerVisitTime);
		//��ӽӷ�ʱ��
		this.replaceVisitTime = String.valueOf(replaceVisitTime);
		//�ܽӷ���
		if(this.visitCount > 0){
			//��Ч�ӷ���
			double d =  (double)this.validVisitCount / (double)this.visitCount * 100;
			this.validRate = SysContent.get2Double(d);
			
			//�Ͽͻ�ͨ��ռ��
			double d1 =  (double)this.oldWayVisitCount / (double)this.visitCount * 100;
			this.oldWayRate = SysContent.get2Double(d1);
			
			//�Ͽͻ��ӷô���ռ��
			double d2 =  (double)this.oldCustomerVisitCount / (double)this.visitCount * 100;
			this.oldReceptRate = SysContent.get2Double(d2);
			
			//�¿ͻ��ӷô���ռ��
			double d3 =  (double)this.newCustomerVisitCount / (double)this.visitCount * 100;
			this.newReceptRate = SysContent.get2Double(d3);
			
			//ָ���ӷ���
			double d4 =  (double)this.appointCount / (double)this.visitCount * 100;
			this.appointReceptRate = SysContent.get2Double(d3);
			
			//�������
			double d5 =  (double)this.replaceCount / (double)this.visitCount * 100;
			this.totalReplaceReceptRate = SysContent.get2Double(d5);
		}
		
		//�¿ͻ�ͨ���ӷ���
		if(this.newWayVisitCount>0){
			//�¿ͻ�ͨ����Ч�ӷ�ռ��
			double d0 =  (double)this.validNewWayVisitCount / (double)this.newWayVisitCount * 100;
			this.newWayValidRate = SysContent.get2Double(d0);
		}
		
		//�Ͽͻ�ͨ���ӷ���
		if(this.oldWayVisitCount>0){
			//�Ͽͻ�ͨ����Ч�ӷ�ռ��
			double d0 =  (double)this.validOldWayVisitCount / (double)this.oldWayVisitCount * 100;
			this.oldWayValidRate = SysContent.get2Double(d0);
		}
		
		//ָ���ӷ���
		if(this.appointCount>0){
			//�¿ͻ�ͨ��ָ���ӷ���
			double d0 =  (double)this.appointNewWayVisitCount / (double)this.appointCount * 100;
			this.newWayAppointReceptRate = SysContent.get2Double(d0);
			
			//�Ͽͻ�ͨ��ָ���ӷ���
			double d1 =  (double)this.appointOldWayVisitCount / (double)this.appointCount * 100;
			this.oldWayAppointReceptRate = SysContent.get2Double(d1);
			
			//ָ����Ч�ӷ���
			double d2 =  (double)this.appointLosedCount / (double)this.appointCount * 100;
			this.appointUnValidReceptRate = SysContent.get2Double(d2);
			
			//ָ����Ч�ӷ���
			double d3 =  (double)this.appointValidVisitCount / (double)this.appointCount * 100;
			this.appointValidReceptRate = SysContent.get2Double(d3);
			
		}
		
		//�ܴ�����
		if(this.grandTotalCollCustomerCount>0){
			//�ͻ���ͷ��
			double d0 =  (double)this.newTwoVisitCustomerCount / (double)this.grandTotalCollCustomerCount * 100;
			this.customerTurnHandRate = SysContent.get2Double(d0);
			
			//�ⳡ���ͱ�
			//double d2 =  (double)this.recordVisitCount / (double)this.grandTotalCollCustomerCount * 100;
			//this.outSideLeadCusRate = SysContent.get2Double(d2);
			
		}
		
		// ���Ϲ���
		if (this.enterBuyCount > 0) {
			// �����ͻ��Ϲ���
			double d0 = (double) this.newSubscribeCustomerCount / (double) this.enterBuyCount * 100;
			this.newAddCusEnterBuyRate = SysContent.get2Double(d0);

			// �Ͽͻ��Ϲ���
			double d1 = (double) this.oldSubscribeCustomerCount / (double) this.enterBuyCount * 100;
			this.oldCusEnterBuyRate = SysContent.get2Double(d1);
			
			//�Ϲ�ǩԼ��
			double d2 =  (double)this.signCount / (double)this.enterBuyCount * 100;
			this.enterBuySignRate = SysContent.get2Double(d2);
		}
		/*
		//�����ܴ�����
//		if(this.newAddCollCustomerCount>0){
//			//�����ͻ��Ϲ���
//			double d0 =  (double)this.newSubscribeCustomerCount / (double)this.newAddCollCustomerCount * 100;
//			this.newAddCusEnterBuyRate = SysContent.get2Double(d0);
//		}
//		
//		//�Ͽͻ���
//		if(this.grandTotalOldCustomerCount>0){
//			//�Ͽͻ��Ϲ���
//			double d0 =  (double)this.oldSubscribeCustomerCount / (double)this.grandTotalOldCustomerCount * 100;
//			this.oldCusEnterBuyRate = SysContent.get2Double(d0);
//		}
//		
//		//�Ϲ��ܿͻ���
//		if(this.subscribeCustomerCount>0){
//			//�Ϲ�ǩԼ��
//			double d0 =  (double)this.customerReturnBackVisitNum / (double)this.subscribeCustomerCount * 100;
//			this.enterBuySignRate = SysContent.get2Double(d0);
//		}
		
		//
		if(this.payCount>0){
			//�¶�ǩԼ��
			double d0 =  (double)this.signCount / (double)this.payCount * 100;
			this.paySignRate = SysContent.get2Double(d0);
		}
		
		if(this.newAddCollCustomerCount+this.losedCount>0){
			//���óɽ��� = ǩԼ�¿ͻ���/��������+��ʧ��
			double d6 =  (double)this.newCustomerSignedCount / (double)(this.newAddCollCustomerCount+this.losedCount) * 100;
			this.visitDealRate = SysContent.get2Double(d6);

		}
		
		// ����������
		if (this.newAddCollCustomerCount > 0) {
			// ���ͳɽ���
			double d1 = (double) this.momeryCuDealCount / (double) this.newAddCollCustomerCount * 100;
			this.grandCusDealRate = SysContent.get2Double(d1);

		}
		return this;
	}**/
	
	public String getCvDate() {
		return cvDate;
	}
	public void setCvDate(String cvDate) {
		this.cvDate = cvDate;
	}
	public String getCollDateTime() {
		return collDateTime;
	}
	public void setCollDateTime(String collDateTime) {
		this.collDateTime = collDateTime;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getAgentPhone() {
		return agentPhone;
	}
	public void setAgentPhone(String agentPhone) {
		this.agentPhone = agentPhone;
	}
	public Integer getAgentStatus() {
		return agentStatus;
	}
	public void setAgentStatus(Integer agentStatus) {
		this.agentStatus = agentStatus;
	}
	public Integer getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}
	public Integer getNewVisitCount() {
		return newVisitCount;
	}
	public void setNewVisitCount(Integer newVisitCount) {
		this.newVisitCount = newVisitCount;
	}
	public Integer getSecondVisitCount() {
		return secondVisitCount;
	}
	public void setSecondVisitCount(Integer secondVisitCount) {
		this.secondVisitCount = secondVisitCount;
	}
	public Integer getMoreVisitCount() {
		return moreVisitCount;
	}
	public void setMoreVisitCount(Integer moreVisitCount) {
		this.moreVisitCount = moreVisitCount;
	}
	public Integer getAppointCount() {
		return appointCount;
	}
	public void setAppointCount(Integer appointCount) {
		this.appointCount = appointCount;
	}
	public Integer getNewWayVisitCount() {
		return newWayVisitCount;
	}
	public void setNewWayVisitCount(Integer newWayVisitCount) {
		this.newWayVisitCount = newWayVisitCount;
	}
	public Integer getOldWayVisitCount() {
		return oldWayVisitCount;
	}
	public Integer getAffirmOldCustomerVisitNum() {
		return affirmOldCustomerVisitNum;
	}
	public void setAffirmOldCustomerVisitNum(Integer affirmOldCustomerVisitNum) {
		this.affirmOldCustomerVisitNum = affirmOldCustomerVisitNum;
	}
	public void setOldWayVisitCount(Integer oldWayVisitCount) {
		this.oldWayVisitCount = oldWayVisitCount;
	}
	public Integer getReplaceCount() {
		return replaceCount;
	}
	public void setReplaceCount(Integer replaceCount) {
		this.replaceCount = replaceCount;
	}
	public String getVisitId() {
		return visitId;
	}
	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}
	public Integer getEnterBuyCount() {
		return enterBuyCount;
	}
	public void setEnterBuyCount(Integer enterBuyCount) {
		this.enterBuyCount = enterBuyCount;
	}
	public Integer getAgreeEnterCount() {
		return agreeEnterCount;
	}
	public void setAgreeEnterCount(Integer agreeEnterCount) {
		this.agreeEnterCount = agreeEnterCount;
	}
	public Integer getRefuseEnterCount() {
		return refuseEnterCount;
	}
	public void setRefuseEnterCount(Integer refuseEnterCount) {
		this.refuseEnterCount = refuseEnterCount;
	}
	public Integer getPayCount() {
		return payCount;
	}
	public void setPayCount(Integer payCount) {
		this.payCount = payCount;
	}
	public Integer getSignCount() {
		return signCount;
	}
	public void setSignCount(Integer signCount) {
		this.signCount = signCount;
	}
	public Integer getRevokeOrderCount() {
		return revokeOrderCount;
	}
	public void setRevokeOrderCount(Integer revokeOrderCount) {
		this.revokeOrderCount = revokeOrderCount;
	}
	public String getSignedOrderId() {
		return signedOrderId;
	}
	public void setSignedOrderId(String signedOrderId) {
		this.signedOrderId = signedOrderId;
	}
	public String getRefusedOrderId() {
		return refusedOrderId;
	}
	public void setRefusedOrderId(String refusedOrderId) {
		this.refusedOrderId = refusedOrderId;
	}
	public String getRevokeOrderId() {
		return revokeOrderId;
	}
	public void setRevokeOrderId(String revokeOrderId) {
		this.revokeOrderId = revokeOrderId;
	}
	public String getPayOrderId() {
		return payOrderId;
	}
	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}
	public Integer getNewAddCollCustomerCount() {
		return newAddCollCustomerCount;
	}
	public void setNewAddCollCustomerCount(Integer newAddCollCustomerCount) {
		this.newAddCollCustomerCount = newAddCollCustomerCount;
	}
	public Integer getGrandTotalCollCustomerCount() {
		return grandTotalCollCustomerCount;
	}
	public void setGrandTotalCollCustomerCount(Integer grandTotalCollCustomerCount) {
		this.grandTotalCollCustomerCount = grandTotalCollCustomerCount;
	}
	public Integer getGrandTotalOldCustomerCount() {
		return grandTotalOldCustomerCount;
	}
	public void setGrandTotalOldCustomerCount(Integer grandTotalOldCustomerCount) {
		this.grandTotalOldCustomerCount = grandTotalOldCustomerCount;
	}
	public String getNewAddCollCustomerId() {
		return newAddCollCustomerId;
	}
	public void setNewAddCollCustomerId(String newAddCollCustomerId) {
		this.newAddCollCustomerId = newAddCollCustomerId;
	}
	public String getGrandTotalCollCustomerId() {
		return grandTotalCollCustomerId;
	}
	public void setGrandTotalCollCustomerId(String grandTotalCollCustomerId) {
		this.grandTotalCollCustomerId = grandTotalCollCustomerId;
	}
	public String getGrandTotalOldCustomerId() {
		return grandTotalOldCustomerId;
	}
	public void setGrandTotalOldCustomerId(String grandTotalOldCustomerId) {
		this.grandTotalOldCustomerId = grandTotalOldCustomerId;
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
	public String getReserveField() {
		return reserveField;
	}
	public void setReserveField(String reserveField) {
		this.reserveField = reserveField;
	}
	public Integer getValidVisitCount() {
		return validVisitCount;
	}
	public void setValidVisitCount(Integer validVisitCount) {
		this.validVisitCount = validVisitCount;
	}
	public Integer getValidNewWayVisitCount() {
		return validNewWayVisitCount;
	}
	public void setValidNewWayVisitCount(Integer validNewWayVisitCount) {
		this.validNewWayVisitCount = validNewWayVisitCount;
	}
	public Integer getAppointNewWayVisitCount() {
		return appointNewWayVisitCount;
	}
	public void setAppointNewWayVisitCount(Integer appointNewWayVisitCount) {
		this.appointNewWayVisitCount = appointNewWayVisitCount;
	}
	public Integer getValidOldWayVisitCount() {
		return validOldWayVisitCount;
	}
	public void setValidOldWayVisitCount(Integer validOldWayVisitCount) {
		this.validOldWayVisitCount = validOldWayVisitCount;
	}
	public Integer getAppointOldWayVisitCount() {
		return appointOldWayVisitCount;
	}
	public void setAppointOldWayVisitCount(Integer appointOldWayVisitCount) {
		this.appointOldWayVisitCount = appointOldWayVisitCount;
	}
	public Integer getOrderReplaceVisitCount() {
		return orderReplaceVisitCount;
	}
	public void setOrderReplaceVisitCount(Integer orderReplaceVisitCount) {
		this.orderReplaceVisitCount = orderReplaceVisitCount;
	}
	public Integer getAppointLosedCount() {
		return appointLosedCount;
	}
	public void setAppointLosedCount(Integer appointLosedCount) {
		this.appointLosedCount = appointLosedCount;
	}
	public Integer getLosedCount() {
		return losedCount;
	}
	public void setLosedCount(Integer losedCount) {
		this.losedCount = losedCount;
	}
	public Integer getNewAppointCount() {
		return newAppointCount;
	}
	public void setNewAppointCount(Integer newAppointCount) {
		this.newAppointCount = newAppointCount;
	}
	public String getNewCustomerVisitTime() {
		return newCustomerVisitTime;
	}
	public void setNewCustomerVisitTime(String newCustomerVisitTime) {
		this.newCustomerVisitTime = newCustomerVisitTime;
	}
	public String getOldCustomerVisitTime() {
		return oldCustomerVisitTime;
	}
	public void setOldCustomerVisitTime(String oldCustomerVisitTime) {
		this.oldCustomerVisitTime = oldCustomerVisitTime;
	}
	public String getReplaceVisitTime() {
		return replaceVisitTime;
	}
	public void setReplaceVisitTime(String replaceVisitTime) {
		this.replaceVisitTime = replaceVisitTime;
	}
	public String getTotalVisitTime() {
		return totalVisitTime;
	}
	public void setTotalVisitTime(String totalVisitTime) {
		this.totalVisitTime = totalVisitTime;
	}
	public String getAverageVisitTime() {
		return averageVisitTime;
	}
	public void setAverageVisitTime(String averageVisitTime) {
		this.averageVisitTime = averageVisitTime;
	}
	public Double getConfirmHouseMoney() {
		return confirmHouseMoney;
	}
	public void setConfirmHouseMoney(Double confirmHouseMoney) {
		this.confirmHouseMoney = confirmHouseMoney;
	}
	public Integer getSubscribeHouseCount() {
		return subscribeHouseCount;
	}
	public void setSubscribeHouseCount(Integer subscribeHouseCount) {
		this.subscribeHouseCount = subscribeHouseCount;
	}
	public Double getSubscribeMoney() {
		return subscribeMoney;
	}
	public void setSubscribeMoney(Double subscribeMoney) {
		this.subscribeMoney = subscribeMoney;
	}
	public Double getSubscribeGetMoney() {
		return subscribeGetMoney;
	}
	public void setSubscribeGetMoney(Double subscribeGetMoney) {
		this.subscribeGetMoney = subscribeGetMoney;
	}
	public Double getSubscribeLockHouseMoney() {
		return subscribeLockHouseMoney;
	}
	public void setSubscribeLockHouseMoney(Double subscribeLockHouseMoney) {
		this.subscribeLockHouseMoney = subscribeLockHouseMoney;
	}
	public Integer getGiveUpSignCount() {
		return giveUpSignCount;
	}
	public void setGiveUpSignCount(Integer giveUpSignCount) {
		this.giveUpSignCount = giveUpSignCount;
	}
	public Integer getWaitSignCount() {
		return waitSignCount;
	}
	public void setWaitSignCount(Integer waitSignCount) {
		this.waitSignCount = waitSignCount;
	}
	public Double getSignHouseMoney() {
		return signHouseMoney;
	}
	public void setSignHouseMoney(Double signHouseMoney) {
		this.signHouseMoney = signHouseMoney;
	}
	public Double getGiveUpHouseMoney() {
		return giveUpHouseMoney;
	}
	public void setGiveUpHouseMoney(Double giveUpHouseMoney) {
		this.giveUpHouseMoney = giveUpHouseMoney;
	}
	public Double getWaitSignHouseMoney() {
		return waitSignHouseMoney;
	}
	public void setWaitSignHouseMoney(Double waitSignHouseMoney) {
		this.waitSignHouseMoney = waitSignHouseMoney;
	}
	public Integer getNewTwoVisitCustomerCount() {
		return newTwoVisitCustomerCount;
	}
	public void setNewTwoVisitCustomerCount(Integer newTwoVisitCustomerCount) {
		this.newTwoVisitCustomerCount = newTwoVisitCustomerCount;
	}
	public Integer getSubscribeCustomerCount() {
		return subscribeCustomerCount;
	}
	public void setSubscribeCustomerCount(Integer subscribeCustomerCount) {
		this.subscribeCustomerCount = subscribeCustomerCount;
	}
	public Integer getNewSubscribeCustomerCount() {
		return newSubscribeCustomerCount;
	}
	public void setNewSubscribeCustomerCount(Integer newSubscribeCustomerCount) {
		this.newSubscribeCustomerCount = newSubscribeCustomerCount;
	}
	public Integer getOldSubscribeCustomerCount() {
		return oldSubscribeCustomerCount;
	}
	public void setOldSubscribeCustomerCount(Integer oldSubscribeCustomerCount) {
		this.oldSubscribeCustomerCount = oldSubscribeCustomerCount;
	}
	
	
	public Integer getPlatformCustomerCount() {
		return platformCustomerCount;
	}
	public void setPlatformCustomerCount(Integer platformCustomerCount) {
		this.platformCustomerCount = platformCustomerCount;
	}
	public Integer getCustomerReturnBackVisitNum() {
		return customerReturnBackVisitNum;
	}
	public void setCustomerReturnBackVisitNum(Integer customerReturnBackVisitNum) {
		this.customerReturnBackVisitNum = customerReturnBackVisitNum;
	}
	public Integer getVisitToDealCount() {
		return visitToDealCount;
	}
	public void setVisitToDealCount(Integer visitToDealCount) {
		this.visitToDealCount = visitToDealCount;
	}
	public Integer getTotalCustomerCount() {
		return totalCustomerCount;
	}
	public void setTotalCustomerCount(Integer totalCustomerCount) {
		this.totalCustomerCount = totalCustomerCount;
	}
	public Integer getTotalOldCustomerCount() {
		return totalOldCustomerCount;
	}
	public void setTotalOldCustomerCount(Integer totalOldCustomerCount) {
		this.totalOldCustomerCount = totalOldCustomerCount;
	}
	public Integer getNewCustomerSignedCount() {
		return newCustomerSignedCount;
	}
	public void setNewCustomerSignedCount(Integer newCustomerSignedCount) {
		this.newCustomerSignedCount = newCustomerSignedCount;
	}
	
	public Integer getNewCustomerVisitCount() {
		return newCustomerVisitCount;
	}
	public void setNewCustomerVisitCount(Integer newCustomerVisitCount) {
		this.newCustomerVisitCount = newCustomerVisitCount;
	}
	public Integer getValidNewCustomerVisitCount() {
		return validNewCustomerVisitCount;
	}
	public void setValidNewCustomerVisitCount(Integer validNewCustomerVisitCount) {
		this.validNewCustomerVisitCount = validNewCustomerVisitCount;
	}
	public Integer getOldCustomerVisitCount() {
		return oldCustomerVisitCount;
	}
	public void setOldCustomerVisitCount(Integer oldCustomerVisitCount) {
		this.oldCustomerVisitCount = oldCustomerVisitCount;
	}
	public Integer getValidOldCustomerVisitCount() {
		return validOldCustomerVisitCount;
	}
	public void setValidOldCustomerVisitCount(Integer validOldCustomerVisitCount) {
		this.validOldCustomerVisitCount = validOldCustomerVisitCount;
	}
	public Integer getOldCustomerSignedCount() {
		return oldCustomerSignedCount;
	}
	public void setOldCustomerSignedCount(Integer oldCustomerSignedCount) {
		this.oldCustomerSignedCount = oldCustomerSignedCount;
	}
	public Integer getUnValidVisitCount() {
		return unValidVisitCount;
	}
	public void setUnValidVisitCount(Integer unValidVisitCount) {
		this.unValidVisitCount = unValidVisitCount;
	}
	public Integer getAppointReplaceVisitCount() {
		return appointReplaceVisitCount;
	}
	public void setAppointReplaceVisitCount(Integer appointReplaceVisitCount) {
		this.appointReplaceVisitCount = appointReplaceVisitCount;
	}
	public String getEveryOnceAverageReplaceTimeLong() {
		return everyOnceAverageReplaceTimeLong;
	}
	public void setEveryOnceAverageReplaceTimeLong(String everyOnceAverageReplaceTimeLong) {
		this.everyOnceAverageReplaceTimeLong = everyOnceAverageReplaceTimeLong;
	}
	public String getEveryDayAverageReplaceTimeLong() {
		return everyDayAverageReplaceTimeLong;
	}
	public void setEveryDayAverageReplaceTimeLong(String everyDayAverageReplaceTimeLong) {
		this.everyDayAverageReplaceTimeLong = everyDayAverageReplaceTimeLong;
	}
	public String getValidRate() {
		return validRate;
	}
	public void setValidRate(String validRate) {
		this.validRate = validRate;
	}
	public String getOldWayRate() {
		return oldWayRate;
	}
	public void setOldWayRate(String oldWayRate) {
		this.oldWayRate = oldWayRate;
	}
	public String getNewWayValidRate() {
		return newWayValidRate;
	}
	public void setNewWayValidRate(String newWayValidRate) {
		this.newWayValidRate = newWayValidRate;
	}
	public String getOldReceptRate() {
		return oldReceptRate;
	}
	public void setOldReceptRate(String oldReceptRate) {
		this.oldReceptRate = oldReceptRate;
	}
	public String getNewReceptRate() {
		return newReceptRate;
	}
	public void setNewReceptRate(String newReceptRate) {
		this.newReceptRate = newReceptRate;
	}
	public String getAppointReceptRate() {
		return appointReceptRate;
	}
	public void setAppointReceptRate(String appointReceptRate) {
		this.appointReceptRate = appointReceptRate;
	}
	public String getNewWayAppointReceptRate() {
		return newWayAppointReceptRate;
	}
	public void setNewWayAppointReceptRate(String newWayAppointReceptRate) {
		this.newWayAppointReceptRate = newWayAppointReceptRate;
	}
	public String getOldWayAppointReceptRate() {
		return oldWayAppointReceptRate;
	}
	public void setOldWayAppointReceptRate(String oldWayAppointReceptRate) {
		this.oldWayAppointReceptRate = oldWayAppointReceptRate;
	}
	public String getAppointUnValidReceptRate() {
		return appointUnValidReceptRate;
	}
	public void setAppointUnValidReceptRate(String appointUnValidReceptRate) {
		this.appointUnValidReceptRate = appointUnValidReceptRate;
	}
	public String getTotalReplaceReceptRate() {
		return totalReplaceReceptRate;
	}
	public void setTotalReplaceReceptRate(String totalReplaceReceptRate) {
		this.totalReplaceReceptRate = totalReplaceReceptRate;
	}
	public String getCustomerTurnHandRate() {
		return customerTurnHandRate;
	}
	public void setCustomerTurnHandRate(String customerTurnHandRate) {
		this.customerTurnHandRate = customerTurnHandRate;
	}
	public String getNewAddCusEnterBuyRate() {
		return newAddCusEnterBuyRate;
	}
	public void setNewAddCusEnterBuyRate(String newAddCusEnterBuyRate) {
		this.newAddCusEnterBuyRate = newAddCusEnterBuyRate;
	}
	public String getOldCusEnterBuyRate() {
		return oldCusEnterBuyRate;
	}
	public void setOldCusEnterBuyRate(String oldCusEnterBuyRate) {
		this.oldCusEnterBuyRate = oldCusEnterBuyRate;
	}
	public String getVisitDealRate() {
		return visitDealRate;
	}
	public void setVisitDealRate(String visitDealRate) {
		this.visitDealRate = visitDealRate;
	}
	public String getGrandCusDealRate() {
		return grandCusDealRate;
	}
	public void setGrandCusDealRate(String grandCusDealRate) {
		this.grandCusDealRate = grandCusDealRate;
	}
	public String getEnterBuySignRate() {
		return enterBuySignRate;
	}
	public void setEnterBuySignRate(String enterBuySignRate) {
		this.enterBuySignRate = enterBuySignRate;
	}

	
	public Integer getVisitAgain() {
		return visitAgain;
	}
	public void setVisitAgain(Integer visitAgain) {
		this.visitAgain = visitAgain;
	}
	public Integer getUnValidNewCustomerVisitCount() {
		return unValidNewCustomerVisitCount;
	}
	public void setUnValidNewCustomerVisitCount(Integer unValidNewCustomerVisitCount) {
		this.unValidNewCustomerVisitCount = unValidNewCustomerVisitCount;
	}

	public Integer getAppointValidVisitCount() {
		return appointValidVisitCount;
	}


	public void setAppointValidVisitCount(Integer appointValidVisitCount) {
		this.appointValidVisitCount = appointValidVisitCount;
	}


	public String getAppointValidReceptRate() {
		return appointValidReceptRate;
	}


	public void setAppointValidReceptRate(String appointValidReceptRate) {
		this.appointValidReceptRate = appointValidReceptRate;
	}

	public String getPaySignRate() {
		return paySignRate;
	}


	public void setPaySignRate(String paySignRate) {
		this.paySignRate = paySignRate;
	}

	public String getOldWayValidRate() {
		return oldWayValidRate;
	}


	public void setOldWayValidRate(String oldWayValidRate) {
		this.oldWayValidRate = oldWayValidRate;
	}


	public Integer getMomeryCuDealCount() {
		return momeryCuDealCount;
	}


	public void setMomeryCuDealCount(Integer momeryCuDealCount) {
		this.momeryCuDealCount = momeryCuDealCount;
	}


	@Override
	public String toString() {
		return "AgentVisitOrder [cvDate=" + cvDate + ", collDateTime=" + collDateTime + ", agentId=" + agentId
				+ ", agentName=" + agentName + ", agentPhone=" + agentPhone + ", agentStatus=" + agentStatus
				+ ", visitCount=" + visitCount + ", validVisitCount=" + validVisitCount + ", newVisitCount="
				+ newVisitCount + ", secondVisitCount=" + secondVisitCount + ", moreVisitCount=" + moreVisitCount
				+ ", appointCount=" + appointCount + ", newWayVisitCount=" + newWayVisitCount
				+ ", validNewWayVisitCount=" + validNewWayVisitCount + ", appointNewWayVisitCount="
				+ appointNewWayVisitCount + ", oldWayVisitCount=" + oldWayVisitCount + ", validOldWayVisitCount="
				+ validOldWayVisitCount + ", appointOldWayVisitCount=" + appointOldWayVisitCount + ", replaceCount="
				+ replaceCount + ", orderReplaceVisitCount=" + orderReplaceVisitCount + ", affirmOldCustomerVisitNum="
				+ affirmOldCustomerVisitNum + ", appointLosedCount=" + appointLosedCount + ", losedCount=" + losedCount
				+ ", newAppointCount=" + newAppointCount + ", newCustomerVisitTime=" + newCustomerVisitTime
				+ ", oldCustomerVisitTime=" + oldCustomerVisitTime + ", replaceVisitTime=" + replaceVisitTime
				+ ", totalVisitTime=" + totalVisitTime + ", averageVisitTime=" + averageVisitTime + ", visitId="
				+ visitId + ", enterBuyCount=" + enterBuyCount + ", agreeEnterCount=" + agreeEnterCount
				+ ", refuseEnterCount=" + refuseEnterCount + ", payCount=" + payCount + ", signCount=" + signCount
				+ ", revokeOrderCount=" + revokeOrderCount + ", confirmHouseMoney=" + confirmHouseMoney
				+ ", subscribeHouseCount=" + subscribeHouseCount + ", subscribeMoney=" + subscribeMoney
				+ ", subscribeGetMoney=" + subscribeGetMoney + ", subscribeLockHouseMoney=" + subscribeLockHouseMoney
				+ ", giveUpSignCount=" + giveUpSignCount + ", waitSignCount=" + waitSignCount + ", signHouseMoney="
				+ signHouseMoney + ", giveUpHouseMoney=" + giveUpHouseMoney + ", waitSignHouseMoney="
				+ waitSignHouseMoney + ", signedOrderId=" + signedOrderId + ", refusedOrderId=" + refusedOrderId
				+ ", revokeOrderId=" + revokeOrderId + ", payOrderId=" + payOrderId + ", newAddCollCustomerCount="
				+ newAddCollCustomerCount + ", grandTotalCollCustomerCount=" + grandTotalCollCustomerCount
				+ ", grandTotalOldCustomerCount=" + grandTotalOldCustomerCount + ", newTwoVisitCustomerCount="
				+ newTwoVisitCustomerCount + ", customerReturnBackVisitNum=" + customerReturnBackVisitNum
				+ ", platformCustomerCount=" + platformCustomerCount + ", subscribeCustomerCount="
				+ subscribeCustomerCount + ", newSubscribeCustomerCount=" + newSubscribeCustomerCount
				+ ", oldSubscribeCustomerCount=" + oldSubscribeCustomerCount + ", visitToDealCount=" + visitToDealCount
				+ ", newAddCollCustomerId=" + newAddCollCustomerId + ", grandTotalCollCustomerId="
				+ grandTotalCollCustomerId + ", grandTotalOldCustomerId=" + grandTotalOldCustomerId + ", signInTime="
				+ signInTime + ", signOutTime=" + signOutTime + ", reserveField=" + reserveField + "]";
	}

	
	
	
	
	

	
}
