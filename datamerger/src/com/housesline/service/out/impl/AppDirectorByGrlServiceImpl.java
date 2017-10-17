package com.housesline.service.out.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.housesline.bean.AgentVisitOrder;
import com.housesline.bean.OutSideProject;
import com.housesline.dao.BaseDao;
import com.housesline.service.out.AppDirectorByGrlService;
import com.housesline.service.project.ProjectCreateService;
import com.housesline.utils.DateUtil;
import com.housesline.utils.SysContent;

/**
 * �����app
 * @author Administrator
 *
 */
@Service(value="appDirectorService")
public class AppDirectorByGrlServiceImpl implements AppDirectorByGrlService {

	@Autowired
	private BaseDao baseDao;
	
	@Autowired
	private ProjectCreateService projectCreateService;

	//��ȡ��ϸ�ӷ�����(1.1)
	@Override
	public Map findToDayDetailedReceiveDataByTime(String proId, String startDate, String endDate) {
		Map map = new HashMap<>();
		String outSideLeadCusRate = "0.00";
		//�ⳡ
		OutSideProject osp = new OutSideProject();
		osp = osp.creatMoreObjTurnOne(proId,startDate,endDate,baseDao,projectCreateService);
		//�ڳ�
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId, null, startDate, endDate, baseDao, osp, null, projectCreateService);
		map.put("averrageTime",avo.getEveryOnceAverageReplaceTimeLong());//ƽ���ӷ�ʱ��
		map.put("recCount", avo.getVisitCount());//�ӷ�����
		map.put("valid", avo.getValidVisitCount());//��Ч������
		map.put("unValid", avo.getUnValidVisitCount());//��Ч������
		map.put("oldCustomer", avo.getGrandTotalOldCustomerCount());//�Ͽͻ���
		map.put("validRate", avo.getValidRate()+"%");//��Ч������
		map.put("grVisitedRate", osp.getGrVisiteRate()+"%");//����������
		map.put("oldRate", avo.getOldReceptRate()+"%");//�Ͽͻ��ӷô���ռ��
		map.put("replaceRate", avo.getTotalReplaceReceptRate()+"%");//�������
		//�ⳡ���ͱ�
		if(avo.getGrandTotalCollCustomerCount()>0){
			double d = (double)osp.getRecordVisitCount() / (double)avo.getGrandTotalCollCustomerCount() * 100;
			outSideLeadCusRate = SysContent.get2Double(d);
		}
		map.put("systemRate", outSideLeadCusRate+"%");//ƽ̨���ͱ� = �ⳡ���ͱ�
		return map;
	}

	//��ȡ��ϸ�ɽ�����(1.2.2)
	@Override
	public Map findDealDataByTime(String proId, String startDate, String endDate) {
		Map map = new HashMap<>();
		//�ⳡ
		OutSideProject osp = new OutSideProject();
		osp = osp.creatMoreObjTurnOne(proId, startDate, endDate, baseDao,projectCreateService);
		//�ڳ�
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId, null, startDate, endDate, baseDao, osp, null, projectCreateService);
		//�ɽ�����
		map.put("dealCount", avo.getSignCount());
		//�����¶�
		map.put("payMoney", avo.getPayCount());
		//����ǩԼ
		map.put("signCount",avo.getSignCount());
		//���¶�������Դ��ֵ
		map.put("lockedValue", SysContent.get2Double(avo.getConfirmHouseMoney()));
		//��ǩԼ��Դ��ֵ
		map.put("signedHouseValue", SysContent.get2Double(avo.getSignHouseMoney()));
		//ƽ̨�ͻ��Ϲ�ռ��
		map.put("systemCusEnterBuyRate", osp.getSystemEnterBuyRate()+"%");
		//ƽ̨�ͻ�ǩԼռ��
		map.put("systemCusSignRate", osp.getSysCusSignedRate()+"%");
		return map;
	}

	//����״̬����
	@Override
	public Map findAgentStatusDataById(String proId, String startDate, String endDate,String agentId) {
		Map map = new HashMap<>();
		//�ڳ�
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,agentId,startDate,endDate,baseDao,projectCreateService);
		map.put("totalVisit",avo.getVisitCount());//�ܽӷ�����
		map.put("validRate", avo.getValidRate());//��Ч�ӷ���
		map.put("validRateCompare",0);
		map.put("valid",avo.getValidVisitCount());//��Ч�ӷ�����
		map.put("unvalid",avo.getUnValidVisitCount());//��Ч�ӷ�����
		map.put("newCus", avo.getValidNewCustomerVisitCount());//�¿ͻ��ӷ���
		map.put("oleCus",avo.getValidOldCustomerVisitCount());//�Ͽͻ��ӷ���
		
		//�ܴ�����
		map.put("totalSaveCusCount", avo.getGrandTotalCollCustomerCount());
		//��ͷ��
		map.put("secondRate", avo.getCustomerTurnHandRate()+"%");
		//��ͷ�ʱȽ�
		map.put("secondRateCompare", 0);
		//�����ͻ���
		map.put("newAddCusCount", avo.getNewAddCollCustomerCount());
		//������������
		map.put("secondCount", avo.getNewTwoVisitCustomerCount());
		
		//�Ϲ���Ϣ
		map.put("getMoney", avo.getSubscribeGetMoney());//�Ϲ�������
		map.put("money",avo.getSignHouseMoney());//�ɽ�����(��ǩԼ��Դ��ֵ)
		map.put("dealCount", avo.getSignCount());//�ɽ���
		map.put("paySignRate",avo.getPaySignRate());//�¶�ǩԼ��
		map.put("paySignRateCompare", 0);//�¶��ʱȽ�
		map.put("areadlyPayCount",avo.getPayCount());//�¶�����
		map.put("sign", avo.getSignCount());//��ǩԼ��
		//�ȴ�ǩԼ
		map.put("waitSign", avo.getWaitSignCount());
		//����ǩԼ
		//map.put("overSign",avo.getGiveUpSignCount());
		//�����Ϲ� 	
		//map.put("overEnterBuy",0);
		return map;
	}
	
	
}
