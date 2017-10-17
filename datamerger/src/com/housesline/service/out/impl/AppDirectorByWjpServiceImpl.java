package com.housesline.service.out.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.housesline.bean.AgentVisitOrder;
import com.housesline.bean.OutSideProject;
import com.housesline.bean.User;
import com.housesline.bean.UserRole;
import com.housesline.dao.BaseDao;
import com.housesline.dao.SelectDao;
import com.housesline.service.out.AppDirectorByWjpService;
import com.housesline.service.project.ProjectCreateService;
import com.housesline.utils.SysContent;



@Service(value="appDirectorByWjpService")
public class AppDirectorByWjpServiceImpl implements AppDirectorByWjpService{

	@Autowired
	private BaseDao baseDao;
	
	@Autowired
	private SelectDao selectDao;
	@Autowired
	private ProjectCreateService projectCreateService;
	
	@Override
	public Map<String, Object> findVisitForTablesInVisit(String proId, String startDate, String endDate) {
		
		Map<String, Object> map = new HashMap<>();
		//�ڳ�
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,projectCreateService);
		
		map.put("length", 29);
		// �ܽӷ���
		map.put("name_1", "�ܽӷ���");
		map.put("name_2", "��Ч�ӷ�");
		map.put("name_3", "��Ч�ӷ�");
		map.put("name_4", "��Ч�ӷ���");
		map.put("name_5", "�¿ͻ�ͨ���ӷ�");
		map.put("name_6", "�Ͽͻ�ͨ���ӷ�");
		map.put("name_7", "�¿ͻ�ͨ����Ч�ӷ�");
		map.put("name_8", "�Ͽͻ�ͨ����Ч�ӷ�");
		map.put("name_9", "�¿ͻ�ͨ����Ч�ӷ���");
		map.put("name_10", "�Ͽͻ�ͨ����Ч�ӷ���");
		map.put("name_11", "�Ͽͻ�ͨ��ռ��");
		map.put("name_12", "ȷ���Ͽͻ��ӷô���");
		map.put("name_13", "�Ͽͻ��ӷô���ռ��");
		map.put("name_14", "ָ���ӷ�");
		map.put("name_15", "�¿ͻ�ͨ��ָ���ӷ�");
		map.put("name_16", "�Ͽͻ�ͨ��ָ���ӷ�");
		map.put("name_17", "ָ����Ч�ӷ�");
		map.put("name_18", "ָ����Ч�ӷ���");
		map.put("name_19", "ָ���ӷ���");
		map.put("name_20", "�¿ͻ�ͨ��ָ���ӷ���");
		map.put("name_21", "�Ͽͻ�ͨ��ָ���ӷ���");
		map.put("name_22", "�������");
		map.put("name_23", "�������");
		map.put("name_24", "�ܽӷ�ʱ��");
		map.put("name_25", "�¿ͻ�ͨ���ӷ�ʱ��");
		map.put("name_26", "�Ͽͻ�ͨ���ӷ�ʱ��");
		map.put("name_27", "��ӽӷ�ʱ��");
		map.put("name_28", "ÿ�սӷ�ƽ��ʱ��");
		map.put("name_29", "ÿ�νӷ�ƽ��ʱ��");
		//_____________________________________________________
		map.put("data_1", avo.getVisitCount());
		map.put("data_2", avo.getValidVisitCount());
		map.put("data_3", avo.getUnValidVisitCount());
		map.put("data_4", avo.getValidRate());
		map.put("data_5", avo.getNewWayVisitCount());
		map.put("data_6", avo.getOldWayVisitCount());
		map.put("data_7", avo.getValidNewWayVisitCount());
		map.put("data_8", avo.getValidOldWayVisitCount());
		map.put("data_9", avo.getNewWayValidRate());
		//map.put("data_10", avo.getOldReceptRate());
		
		map.put("data_10", avo.getOldWayValidRate());
		map.put("data_11", avo.getOldWayRate());
		map.put("data_12", avo.getAffirmOldCustomerVisitNum());
		map.put("data_13", avo.getOldReceptRate());
		map.put("data_14", avo.getAppointCount());
		map.put("data_15", avo.getAppointNewWayVisitCount());
		map.put("data_16", avo.getAppointOldWayVisitCount());
		map.put("data_17", avo.getAppointValidVisitCount());
		map.put("data_18", avo.getAppointValidReceptRate());
		map.put("data_19", avo.getAppointReceptRate());
		map.put("data_20", avo.getNewWayAppointReceptRate());
		map.put("data_21", avo.getOldWayAppointReceptRate());
		map.put("data_22", avo.getReplaceCount());
		map.put("data_23", avo.getTotalReplaceReceptRate());
		map.put("data_24", avo.getTotalVisitTime());
		map.put("data_25", avo.getNewCustomerVisitTime());
		map.put("data_26", avo.getOldCustomerVisitTime());
		map.put("data_27", avo.getReplaceVisitTime());
		map.put("data_28", avo.getEveryDayAverageReplaceTimeLong());
		map.put("data_29", avo.getEveryOnceAverageReplaceTimeLong());
		
		
		return map;
	}

	@Override
	public Map<String, Object> findDealForTablesInDeal(String proId, String startDate, String endDate) {
		Map<String, Object> map = new HashMap<>();
		//�ⳡ
		OutSideProject osp = new OutSideProject();
		osp = osp.creatMoreObjTurnOne(proId,startDate,endDate,baseDao,projectCreateService);
		//�ڳ�
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,osp,projectCreateService);
		
		map.put("length", 23);

		map.put("name_1", "�����ܴ�����");
		// �����������ÿͻ���
		map.put("name_2", "�����������ÿͻ���");
		map.put("name_3", "�Ͽͻ���");
		map.put("name_4", "�ͻ���ͷ��");
		map.put("name_5", "���Ϲ��ͻ���");
		map.put("name_6", "�Ͽͻ��Ϲ���");
		map.put("name_7", "�¿ͻ��Ϲ���");
		map.put("name_8", "�Ͽͻ��Ϲ���");
		map.put("name_9", "�¿ͻ��Ϲ���");
		map.put("name_10", "�Ϲ�����");
		map.put("name_11", "�Ϲ����");
		map.put("name_12", "�Ϲ�������");
		map.put("name_13", "�Ϲ�������Դ��ֵ");
		map.put("name_14", "��ǩԼ��");
		map.put("name_15", "����ǩԼ��");
		map.put("name_16", "��ǩԼ��");
		map.put("name_17", "�¶���");
		map.put("name_18", "��ǩԼ��Դ��ֵ");
		map.put("name_19", "����ǩԼ��Դ��ֵ");
		map.put("name_20", "��ǩԼ��Դ��ֵ");
		map.put("name_21", "���óɽ���");
		map.put("name_22", "�Ϲ�ǩԼ��");
		map.put("name_23", "���ͳɽ���");
		//_______________________________________________________
		map.put("data_1", avo.getNewAddCollCustomerCount());
		map.put("data_2", avo.getNewTwoVisitCustomerCount());
		map.put("data_3", avo.getGrandTotalOldCustomerCount());
		map.put("data_4", avo.getCustomerTurnHandRate());
		map.put("data_5", avo.getNewSubscribeCustomerCount());
		map.put("data_6", avo.getOldSubscribeCustomerCount());
		map.put("data_7", avo.getNewSubscribeCustomerCount());
		map.put("data_8", avo.getOldCusEnterBuyRate());
		map.put("data_9", avo.getNewAddCusEnterBuyRate());
		map.put("data_10", avo.getSubscribeHouseCount());
		map.put("data_11", avo.getSubscribeMoney());
		map.put("data_12", avo.getSubscribeGetMoney());
		map.put("data_13", avo.getSubscribeLockHouseMoney());
		map.put("data_14", avo.getSignCount());
		map.put("data_15", avo.getGiveUpSignCount());
		map.put("data_16", avo.getWaitSignCount());
		map.put("data_17", avo.getPayCount());
		map.put("data_18", avo.getSignHouseMoney());
		map.put("data_19", avo.getGiveUpHouseMoney());
		map.put("data_20", avo.getWaitSignHouseMoney());
		map.put("data_21", avo.getVisitDealRate());
		map.put("data_22", avo.getEnterBuySignRate());
		map.put("data_23", avo.getGrandCusDealRate());

		
		return map;
	}

	@Override
	public Map<String, Object> findOutSideInfoForTablesInDeal(String proId, String startDate, String endDate) {
		
		Map<String, Object> map = new HashMap<>();
		//�ⳡ
		OutSideProject osp = new OutSideProject();
		osp = osp.creatMoreObjTurnOne(proId,startDate,endDate,baseDao,projectCreateService);
		//�ڳ�
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,projectCreateService);
		
		map.put("length", 9);

		map.put("name_1", "�����ͻ���");
		map.put("name_2", "�������ÿͻ���");
		map.put("name_3", "����δ���ÿͻ���");
		map.put("name_4", "����������");
		map.put("name_5", "�ⳡ����ռ��");
		map.put("name_6", "���ͳɽ���");
		map.put("name_7", "�ⳡ�ɽ���");
		map.put("name_8", "�ⳡ�ɽ�ռ��");
		map.put("name_9", "�ڳ��ɽ���");
		
		String outSideLeadCusRate = "0.00";
		map.put("data_1", osp.getRecordCustomerCount());
		map.put("data_2", osp.getRecordVisitCount());
		map.put("data_3", osp.getRecordNotVisitCount());
		map.put("data_4", osp.getGrVisiteRate());
//		map.put("data_5", avo.getOutSideLeadCusRate());
		if(avo.getGrandTotalCollCustomerCount()>0){
			double d = (double)osp.getRecordVisitCount() / (double)avo.getGrandTotalCollCustomerCount() * 100;
			outSideLeadCusRate = SysContent.get2Double(d);
		}
		map.put("data_5", outSideLeadCusRate+"%");//ƽ̨���ͱ� = �ⳡ���ͱ�
		
		map.put("data_6", osp.getGuideCustomerVisitCount());
		map.put("data_7", osp.getOutSideDealCount());
		map.put("data_8", osp.getOutSideDealRate());
		map.put("data_9", osp.getIntFiledToDealNum());
		
		
		return map;
	}

	@Override
	public List<AgentVisitOrder> findVisitRankingInfo(String proId, String startDate, String endDate) {
		
		return null;
	}

	@Override
	public Map<String, Object> findReceptionByManagerOutSide(String proId, String startDate, String endDate) {
		
		Map<String, Object> map = new HashMap<>();
		//�ڳ�
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,projectCreateService);
		
		map.put("title", "�۲�ͻ��Ӵ���Ч");
		map.put("name_1", "��Ч�ӷ���");
		map.put("name_2", "��Ч�ӷ���");
		map.put("length", 2);
		
		map.put("data_1", avo.getValidVisitCount());
		map.put("data_2", avo.getUnValidVisitCount());
		
		return map;
	}

	@Override
	public Map<String, Object> findReceptionByCustomerFirstVisitOutSide(String proId, String startDate,
			String endDate) {
		Map<String, Object> map = new HashMap<>();
		//�ڳ�
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,projectCreateService);
		
		map.put("title", "�۲��׷���Ч�ӷ�");
		map.put("name_1", "�׷���Ч�ӷ���");
		map.put("name_2", "�׷���Ч�ӷ���");
		map.put("length", 2);
		
		map.put("data_1", avo.getValidNewCustomerVisitCount());
		map.put("data_2", avo.getUnValidNewCustomerVisitCount());
		
		return map;
	}

	@Override
	public Map<String, Object> findSeeAppointCustomerReceptionInfoOutSide(String proId, String startDate,
			String endDate) {
		Map<String, Object> map = new HashMap<>();
		//�ڳ�
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,projectCreateService);
		
		map.put("title", "�۲�ָ���ӷ�");
		map.put("name_1", "ָ���ӷ�");
		map.put("name_2", "�¿ͻ�ͨ���ӷ�");
		map.put("name_3", "�Ͽͻ�ͨ���ӷ�");
		map.put("name_4", "ָ����Ч�ӷ�");
		// map.put("name_5", "ָ���ӷ���");
		map.put("name_5", "�¿ͻ�ͨ��ָ���ӷ�");
		map.put("name_6", "�Ͽͻ�ͨ��ָ���ӷ�");
		// map.put("name_8", "ָ����Ч�ӷ���");
		map.put("length", 6);
		
		map.put("data_1", avo.getAppointCount());
		map.put("data_2", avo.getNewWayVisitCount());
		map.put("data_3", avo.getOldWayVisitCount());
		map.put("data_4", avo.getAppointValidVisitCount());
		map.put("data_5", avo.getAppointNewWayVisitCount());
		map.put("data_6", avo.getAppointOldWayVisitCount());
		
		return map;
	}

	@Override
	public Map<String, Object> findSeeReplaceCustomerReceptionInfoOutSide(String proId, String startDate,
			String endDate) {
		Map<String, Object> map = new HashMap<>();
		//�ڳ�
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,projectCreateService);
		
		map.put("title", "�۲����");
		map.put("name_1", "�������");
		// map.put("name_2", "�������");
		map.put("length", 1);
		
		map.put("data_1", avo.getReplaceCount());
		
		return map;
	}

	@Override
	public Map<String, Object> findSeeCustomerReceptionTimeInfoOutSide(String proId, String startDate, String endDate) {
		Map<String, Object> map = new HashMap<>();
		//�ڳ�
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,projectCreateService);
		
		map.put("title", "�۲�ӷ�ʱ��");
		map.put("name_1", "�ܽӷ�ʱ��");
		map.put("name_2", "�¿ͻ��ӷ�ʱ��");
		map.put("name_3", "�Ͽͻ��ӷ�ʱ��");
		map.put("name_4", "��ӽӷ�ʱ��");
		map.put("name_5", "ÿ�νӷ�ƽ��ʱ��");
		map.put("name_6", "ÿ��ƽ���ӷ�ʱ��");
		map.put("length", 6);
		
		map.put("data_1", avo.getTotalVisitTime());
		map.put("data_2", avo.getNewCustomerVisitTime());
		map.put("data_3", avo.getOldCustomerVisitTime());
		map.put("data_4", avo.getReplaceVisitTime());
		map.put("data_5", avo.getEveryOnceAverageReplaceTimeLong());
		map.put("data_6", avo.getEveryDayAverageReplaceTimeLong());
		
		return map;
	}

	@Override
	public Map<String, Object> findNewAndOldCustomerPassagewayInfoOutSide(String proId, String startDate,
			String endDate) {
		Map<String, Object> map = new HashMap<>();
		//�ڳ�
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,projectCreateService);
		
		map.put("title", "�۲����Ͽͻ�ͨ��");
		// map.put("name_1", "�¿ͻ�ͨ���ӷ�");
		// map.put("name_2", "�Ͽͻ�ͨ���ӷ�");
		map.put("name_1", "�¿ͻ�ͨ����Ч�ӷ�");
		map.put("name_2", "�Ͽͻ�ͨ����Ч�ӷ�");
		map.put("name_3", "�¿ͻ�ͨ����Ч�ӷ�");
		map.put("name_4", "�Ͽͻ�ͨ����Ч�ӷ�");
		// map.put("name_5", "�¿ͻ�ͨ����Ч�ӷ���");
		// map.put("name_6", "�Ͽͻ�ͨ����Ч�ӷ���");
		// map.put("name_7", "�Ͽͻ�ͨ��ռ��");
		map.put("length", 4);
		
		map.put("data_1", avo.getValidNewWayVisitCount());
		map.put("data_2", avo.getValidOldWayVisitCount());
		map.put("data_3", avo.getNewWayVisitCount() - avo.getValidNewWayVisitCount());
		map.put("data_4", avo.getOldWayVisitCount() - avo.getValidOldWayVisitCount());
		
		return map;
	}

	@Override
	public Map<String, Object> findSeeCustomerContractRecordsInfoOutSide(String proId, String startDate,
			String endDate) {
		Map<String, Object> map = new HashMap<>();
		//�ⳡ
		OutSideProject osp = new OutSideProject();
		osp = osp.creatMoreObjTurnOne(proId,startDate,endDate,baseDao,projectCreateService);
		//�ڳ�
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,osp,projectCreateService);
		
		map.put("title", "�Ϲ���ֵ����");
		map.put("name_1", "�Ϲ�����");
		map.put("name_2", "�Ϲ����");
		map.put("name_3", "�Ϲ�������");
		map.put("name_4", "�Ϲ�������Դ��ֵ");
		map.put("length", 4);
		
		map.put("data_1", avo.getSubscribeHouseCount());
		map.put("data_2", avo.getSubscribeMoney());
		map.put("data_3", avo.getSubscribeGetMoney());
		map.put("data_4", avo.getSubscribeLockHouseMoney());
		
		return map;
	}

	@Override
	public Map<String, Object> findSeeCustomerHaveDealInfoOutSide(String proId, String startDate, String endDate) {
		Map<String, Object> map = new HashMap<>();
		//�ⳡ
		OutSideProject osp = new OutSideProject();
		osp = osp.creatMoreObjTurnOne(proId,startDate,endDate,baseDao,projectCreateService);
		//�ڳ�
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,osp,projectCreateService);
		
		map.put("title", "�۲�ͻ�ǩԼ");
		map.put("name_1", "��ǩԼ��");
		map.put("name_2", "����ǩԼ��");
		map.put("name_3", "��ǩԼ��");
		map.put("length", 3);
		
		map.put("data_1", avo.getSignCount());
		map.put("data_2", avo.getGiveUpSignCount());
		map.put("data_3", avo.getWaitSignCount());
		
		return map;
	}

	@Override
	public Map<String, Object> findSeeCustomerHaveDealMoneyInfoOutSide(String proId, String startDate, String endDate) {
		Map<String, Object> map = new HashMap<>();
		//�ⳡ
		OutSideProject osp = new OutSideProject();
		osp = osp.creatMoreObjTurnOne(proId,startDate,endDate,baseDao,projectCreateService);
		//�ڳ�
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,osp,projectCreateService);
		
		map.put("title", "ǩԼ��ֵ����");
		map.put("name_1", "��ǩԼ��Դ��ֵ");
		map.put("name_2", "����ǩԼ��Դ��ֵ");
		map.put("name_3", "��ǩԼ��Դ��ֵ");
		map.put("length", 3);
		
		map.put("data_1", avo.getSignHouseMoney());
		map.put("data_2", avo.getGiveUpHouseMoney());
		map.put("data_3", avo.getWaitSignHouseMoney());
		
		return map;
	}

	@Override
	public Map<String, Object> findSeeCustomerHaveDealNumberInfoOutSide(String proId, String startDate,
			String endDate) {
		Map<String, Object> map = new HashMap<>();
		//�ⳡ
		OutSideProject osp = new OutSideProject();
		osp = osp.creatMoreObjTurnOne(proId,startDate,endDate,baseDao,projectCreateService);
		//�ڳ�
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,osp,projectCreateService);
		
		map.put("title", "�ɽ�������");
		map.put("name_1", "���óɽ���");
		map.put("name_2", "���ͳɽ���");
		map.put("name_3", "�Ϲ�ǩԼ��");
		map.put("length", 3);
		
		map.put("data_1", avo.getVisitDealRate());
		map.put("data_2", avo.getEnterBuySignRate());
		map.put("data_3", avo.getGrandCusDealRate());
		
		return map;
	}

	@Override
	public Map<String, Object> findSeeSaleScheduleInfoOutSide(String proId, String startDate, String endDate) {
		Map<String, Object> map = new HashMap<>();
		//�ⳡ
		OutSideProject osp = new OutSideProject();
		osp = osp.creatMoreObjTurnOne(proId,startDate,endDate,baseDao,projectCreateService);
		//�ڳ�
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,osp,projectCreateService);
		
		map.put("title", "���۽��ȹ۲�");
		map.put("name_1", "�Ϲ�����ռ��");
		map.put("name_2", "ǩԼ����ռ��");
		map.put("name_3", "�׸�����ռ��");
		map.put("name_4", "���ҵ�������ռ��");
		map.put("length", 4);
		
		return null;
	}

	@Override
	public Map<String, Object> findSeeCustomerGuideRecprdsToVisitInfoOutSide(String proId, String startDate,
			String endDate) {
		Map<String, Object> map = new HashMap<>();
		//�ڳ�
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,projectCreateService);
		//�ⳡ
		OutSideProject osp = new OutSideProject();
		osp = osp.creatMoreObjTurnOne(proId,startDate,endDate,baseDao,projectCreateService);
		
		map.put("title", "�����������");
		// map.put("name_1", "�����ͻ���");
		map.put("name_1", "�������ÿͻ���");
		map.put("name_2", "����δ���ÿͻ���");
		// map.put("name_4", "����������");
		// map.put("name_5", "�ⳡ����ռ��");
		map.put("length", 2);
		
		map.put("data_1", osp.getRecordVisitCount());
		map.put("data_2", osp.getRecordNotVisitCount());
		
		return map;
	}

	@Override
	public Map<String, Object> findSeeOutCustomerDealInfoOutSide(String proId, String startDate, String endDate) {
		Map<String, Object> map = new HashMap<>();
		//�ڳ�
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,projectCreateService);
		//�ⳡ
		OutSideProject osp = new OutSideProject();
		osp = osp.creatMoreObjTurnOne(proId,startDate,endDate,baseDao,projectCreateService);
		
		map.put("title", "�ⳡ�ɽ�����");
		map.put("name_1", "���ͳɽ�");
		map.put("name_2", "�ⳡ�ɽ���");
		// map.put("name_3", "�ⳡ�ɽ�ռ��");
		map.put("name_3", "�ڳ��ɽ���");
		map.put("length", 3);
		
		map.put("data_1", osp.getGuideCustomerVisitCount());
		map.put("data_2", osp.getOutSideDealCount());
		map.put("data_3", osp.getIntFiledToDealNum());
		
		return map;
	}

	@Override
	public Map<String, Object> findSeeOldCustomerVisitInfoOutSide(String proId, String startDate, String endDate) {
		Map<String, Object> map = new HashMap<>();
		//�ڳ�
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,projectCreateService);
		//�ⳡ
		OutSideProject osp = new OutSideProject();
		osp = osp.creatMoreObjTurnOne(proId,startDate,endDate,baseDao,projectCreateService);
		
		map.put("title", "�۲��Ͽͻ��ӷ�");
		map.put("name_1", "ȷ���Ͽͻ��ӷô���");
		// map.put("name_2", "�Ͽͻ��ӷô���ռ��");
		map.put("length", 1);
		
		map.put("data_1", avo.getAffirmOldCustomerVisitNum());
		
		return map;
	}

	@Override
	public Map<String, Object> findSeeReserveCustomerInfoOutSide(String proId, String startDate, String endDate) {
		Map<String, Object> map = new HashMap<>();
		//�ڳ�
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,projectCreateService);
		//�ⳡ
		OutSideProject osp = new OutSideProject();
		osp = osp.creatMoreObjTurnOne(proId,startDate,endDate,baseDao,projectCreateService);
		
		map.put("title", "�۲촢�ͷ���");
		map.put("name_1", "�����ܴ�����");
		map.put("name_2", "�����������ÿͻ���");
		map.put("name_3", "�Ͽͻ���");
		//map.put("name_4", "�ͻ���ͷ��");
		map.put("length", 3);
		
		map.put("data_1", avo.getNewAddCollCustomerCount());
		map.put("data_2", avo.getNewTwoVisitCustomerCount());
		map.put("data_3", avo.getGrandTotalOldCustomerCount());
		//map.put("data_4", avo.getCustomerTurnHandRate());
		
		return map;
	}

	@Override
	public Map<String, Object> findSeeAllCustomerContractRecordInfoOutSide(String proId, String startDate,
			String endDate) {
		Map<String, Object> map = new HashMap<>();
		//�ⳡ
		OutSideProject osp = new OutSideProject();
		osp = osp.creatMoreObjTurnOne(proId,startDate,endDate,baseDao,projectCreateService);
		//�ڳ�
		AgentVisitOrder avo= new AgentVisitOrder();
		avo = avo.creatMoreObjTurnOne(proId,null,startDate,endDate,baseDao,osp,projectCreateService);
		
		map.put("title", "�۲��Ͽͻ��ӷ�");
		map.put("name_1", "���Ϲ��ͻ���");
		map.put("name_2", "�Ͽͻ��Ϲ���");
		map.put("name_3", "�¿ͻ��Ϲ���");
		map.put("length", 3);
		
		map.put("data_1", avo.getNewSubscribeCustomerCount());
		map.put("data_2", avo.getOldSubscribeCustomerCount());
		map.put("data_3", avo.getNewSubscribeCustomerCount());
		
		return map;
	}

}
