package com.housesline.service.out.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.housesline.bean.AgentVisitOrder;
import com.housesline.bean.HouseTypeHouseAndOrder;
import com.housesline.bean.OutSideProject;
import com.housesline.bean.Project;
import com.housesline.bean.ReportResult;
import com.housesline.bean.User;
import com.housesline.bean.UserRole;
import com.housesline.dao.BaseDao;
import com.housesline.dao.SelectDao;
import com.housesline.service.out.OutDataService;
import com.housesline.service.out.impl.dto.DataAnalysis;
import com.housesline.service.out.impl.dto.StorageCustomerData;
import com.housesline.service.project.ProjectCreateService;
import com.housesline.utils.DateUtil;
import com.housesline.utils.SysContent;

import exception.DataIsNullException;

/**
 * 数据输出
 * 
 * @author Administrator
 *
 */
@Service(value = "outDataService")
public class OutDataServiceImpl implements OutDataService {

	@Autowired
	private BaseDao baseDao;

	@Autowired
	private SelectDao selectDao;

	@Autowired
	private ProjectCreateService projectCreateService;

	@Override
	public List<AgentVisitOrder> findAgentVisitOrderListForProject(String projectId, String startTime, String endTime,
			String oneDay) {
		if (StringUtils.isEmpty(projectId)) {
			throw new DataIsNullException("projectId is null");
		}

		return baseDao.selectAgentVisitOrderListForProject(projectId, startTime, endTime, oneDay);
	}

	@Override
	public List<HouseTypeHouseAndOrder> findHouseTypeHouseAndOrderForProject(String projectId, String startTime,
			String endTime, String oneDay) {
		if (StringUtils.isEmpty(projectId)) {
			throw new DataIsNullException("projectId is null");
		}

		return baseDao.selectHouseTypeHouseAndOrderForProject(projectId, startTime, endTime, oneDay);
	}

	@Override
	public List<OutSideProject> selectOutSideProjectForProject(String projectId, String startTime, String endTime,
			String oneDay) {
		if (StringUtils.isEmpty(projectId)) {
			throw new DataIsNullException("projectId is null");
		}

		return baseDao.selectOutSideProjectForProject(projectId, startTime, endTime, oneDay);
	}

	@Override
	public List<DataAnalysis> findVisitListData(String projectId, String startTime, String endTime, String oneDay) {

		if (startTime == null || "".equals(startTime)) {
			startTime = DateUtil.format(DateUtil.rollDay(new Date(), -7), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}
		if (endTime == null || "".equals(endTime)) {
			endTime = DateUtil.format(DateUtil.rollDay(new Date(), -1), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}

		String thisDate = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);

		List<DataAnalysis> dataList = new LinkedList<>();
		List<AgentVisitOrder> list = new ArrayList<>();
		if (!StringUtils.isEmpty(oneDay)) {
			if (thisDate.equals(oneDay)) {
				list = projectCreateService.selectAnalysisOfDataByProjectIdAndDay(projectId, oneDay);
			} else {
				list = findAgentVisitOrderListForProject(projectId, startTime, endTime, oneDay);
			}
		} else {
			list = findAgentVisitOrderListForProject(projectId, startTime, endTime, oneDay);
		}

		List<String> day = new ArrayList<>();
		if (oneDay != null && !"".equals(oneDay)) {
			day.add(oneDay);
		} else {
			if (startTime != null && !"".equals(startTime)) {
				if (endTime != null && !"".equals(endTime)) {
					day = DateUtil.getTwoDateEveryDay(startTime, endTime);
				}
			}
		}

		for (String od : day) {
			DataAnalysis da = new DataAnalysis();

			String dataDate = od;
			int loseCustomer = 0;
			int loseCustomerAppoint = 0;
			int newCustomer = 0;
			int newCustomerAppoint = 0;
			int visitAgain = 0;

			for (AgentVisitOrder ao : list) {
				if (ao.getCvDate().equals(od)) {
					loseCustomer += ao.getLosedCount();
					loseCustomerAppoint += ao.getAppointLosedCount();
					newCustomer += ao.getNewVisitCount();
					newCustomerAppoint += ao.getNewAppointCount();
					visitAgain += ao.getVisitAgain();
				}
			}

			da.setDataDate(dataDate);
			da.setLoseCustomer(loseCustomer);
			da.setLoseCustomerAppoint(loseCustomerAppoint);
			da.setNewCustomer(newCustomer);
			da.setNewCustomerAppoint(newCustomerAppoint);
			da.setVisitAgain(visitAgain);
			dataList.add(da);
		}

		return dataList;
	}

	@Override
	public Map<String, Object> findMemoryCustomerData(String projectId, String startTime, String endTime,
			String oneDay) {
		Map<String, Object> map = new HashMap<>();
		if (startTime == null || "".equals(startTime)) {
			startTime = DateUtil.format(DateUtil.rollDay(new Date(), -7), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}
		if (endTime == null || "".equals(endTime)) {
			endTime = DateUtil.format(DateUtil.rollDay(new Date(), -1), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}

		String thisDate = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);

		List<StorageCustomerData> dataList = new LinkedList<>();
		List<AgentVisitOrder> list = new ArrayList<>();

		if (!StringUtils.isEmpty(oneDay)) {
			if (thisDate.equals(oneDay)) {
				list = projectCreateService.selectMeneryCustomerData(projectId, oneDay);
			} else {
				list = findAgentVisitOrderListForProject(projectId, startTime, endTime, oneDay);
			}
		} else {
			list = findAgentVisitOrderListForProject(projectId, startTime, endTime, oneDay);
		}

		List<String> dateList = new LinkedList<>();
		List<String> day = new ArrayList<>();
		if (oneDay != null && !"".equals(oneDay)) {
			day.add(oneDay);
		} else {
			if (startTime != null && !"".equals(startTime)) {
				if (endTime != null && !"".equals(endTime)) {
					day = DateUtil.getTwoDateEveryDay(startTime, endTime);
				}
			}
		}

		for (String od : day) {
			dateList.add(od);
			StorageCustomerData sc = new StorageCustomerData();
			String DataDate = od;
			int newCustomer = 0;
			int oldCustomer = 0;

			for (AgentVisitOrder ao : list) {
				if (ao.getCvDate().equals(od)) {
					newCustomer += ao.getTotalCustomerCount();
					oldCustomer += ao.getTotalOldCustomerCount();
				}
			}

			sc.setCustomerCreateDate(DataDate);
			sc.setNewCustomer(newCustomer);
			sc.setOldCustomer(oldCustomer);
			dataList.add(sc);
		}
		map.put("data", dataList);
		map.put("date", dateList);
		return map;
	}

	@Override
	public List<Map<String, Object>> findVisitForTables(String projectId, String startTime, String endTime,
			String oneDay) {

		if (startTime == null || "".equals(startTime)) {
			startTime = DateUtil.format(DateUtil.rollDay(new Date(), -7), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}
		if (endTime == null || "".equals(endTime)) {
			endTime = DateUtil.format(DateUtil.rollDay(new Date(), -1), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}

		String thisDate = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);

		List<Map<String, Object>> dataList = new LinkedList<>();

		List<AgentVisitOrder> list = new ArrayList<>();

		if (!StringUtils.isEmpty(oneDay)) {
			if (thisDate.equals(oneDay)) {
				list = projectCreateService.selectAnalysisOfDataByProjectIdAndDay(projectId, oneDay);
			} else {
				list = findAgentVisitOrderListForProject(projectId, startTime, endTime, oneDay);
			}
		} else {
			list = findAgentVisitOrderListForProject(projectId, startTime, endTime, oneDay);
		}

		List<String> day = new ArrayList<>();
		if (oneDay != null && !"".equals(oneDay)) {
			day.add(oneDay);
		} else {
			if (startTime != null && !"".equals(startTime)) {
				if (endTime != null && !"".equals(endTime)) {
					day = DateUtil.getTwoDateEveryDay(startTime, endTime);
				}
			}
		}

		for (String od : day) {
			Map<String, Object> map = new HashMap<>();

			List<AgentVisitOrder> agentList = new ArrayList<>();
			AgentVisitOrder aoo = new AgentVisitOrder();
			for (AgentVisitOrder ao : list) {
				if (ao.getCvDate().equals(od)) {
					agentList.add(ao);
				}
			}

			aoo = aoo.creatMoreObjTurnOne(projectId, od, od, baseDao, agentList, projectCreateService);

			map.put("averageReceptionTime", aoo.getAverageVisitTime());
			map.put("allTime", aoo.getTotalVisitTime());
			map.put("newVisitTime", aoo.getNewCustomerVisitTime());
			map.put("oldVisitTime", aoo.getOldCustomerVisitTime());
			map.put("replaceTime", aoo.getReplaceVisitTime());
			map.put("totalVisitCount", aoo.getVisitCount());
			map.put("orderReplaceNum", aoo.getOrderReplaceVisitCount());
			map.put("effectiveNum", aoo.getValidVisitCount());
			map.put("effectiveRate", aoo.getValidRate());// 有效接访率
			map.put("invalidNum", aoo.getUnValidVisitCount());
			map.put("newCustomerNum", aoo.getNewWayVisitCount());
			map.put("oldCustomerNum", aoo.getOldWayVisitCount());
			map.put("newCustomerEffectiveNum", aoo.getValidNewWayVisitCount());
			map.put("oldCustomerEffectiveNum", aoo.getValidOldWayVisitCount());
			map.put("newCustomerEffectiveRate", aoo.getNewWayValidRate());// 新客户通道有效接访率
			map.put("newCustomerAppointAgentRate", aoo.getNewWayAppointReceptRate());// 新客户通道指定接访率
			map.put("oldCustomerEffectiveRate", aoo.getOldWayValidRate());// 老客户通道有效接访率
			map.put("oldCustomerAppointAgentRate", aoo.getOldWayAppointReceptRate());// 老客户通道指定接访率
			map.put("oldCustomerRate", aoo.getOldWayRate());
			map.put("appointAgentNum", aoo.getAppointCount());
			map.put("newCustomerAppointAgentNum", aoo.getAppointNewWayVisitCount());
			map.put("oldCustomerAppointAgentNum", aoo.getAppointOldWayVisitCount());
			map.put("appointAgentEffectiveNum", aoo.getAppointValidVisitCount());
			map.put("affirmOldCustomerVisitNum", aoo.getAffirmOldCustomerVisitNum());
			map.put("appointAgentEffectiveRate", aoo.getAppointValidReceptRate());
			map.put("appointAgentRate", aoo.getAppointReceptRate());// 指定接访率
			map.put("allReplaceNum", aoo.getReplaceCount());
			map.put("allReplaceRate", aoo.getTotalReplaceReceptRate());
			map.put("date", od);

			dataList.add(map);

		}
		return dataList;
	}

	@Override
	public List<Map<String, Object>> findDealForTables(String projectId, String startTime, String endTime,
			String oneDay) {
		if (startTime == null || "".equals(startTime)) {
			startTime = DateUtil.format(DateUtil.rollDay(new Date(), -7), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}
		if (endTime == null || "".equals(endTime)) {
			endTime = DateUtil.format(DateUtil.rollDay(new Date(), -1), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}

		String thisDate = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);

		List<Map<String, Object>> dataList = new LinkedList<>();

		List<AgentVisitOrder> list = new ArrayList<>();
		List<OutSideProject> outList = new ArrayList<>();

		if (!StringUtils.isEmpty(oneDay)) {
			if (thisDate.equals(oneDay)) {
				list = projectCreateService.getAnalysisOfData(projectId, oneDay);
				OutSideProject op = projectCreateService.selectOutSideProject(projectId, oneDay);
				outList.add(op);
			} else {
				list = findAgentVisitOrderListForProject(projectId, startTime, endTime, oneDay);
				outList = selectOutSideProjectForProject(projectId, startTime, endTime, oneDay);
			}
		} else {
			list = findAgentVisitOrderListForProject(projectId, startTime, endTime, oneDay);
			outList = selectOutSideProjectForProject(projectId, startTime, endTime, oneDay);
		}

		List<String> day = new ArrayList<>();
		if (oneDay != null && !"".equals(oneDay)) {
			day.add(oneDay);
		} else {
			if (startTime != null && !"".equals(startTime)) {
				if (endTime != null && !"".equals(endTime)) {
					day = DateUtil.getTwoDateEveryDay(startTime, endTime);
				}
			}
		}

		for (String od : day) {
			Map<String, Object> map = new HashMap<>();

			List<AgentVisitOrder> agentList = new ArrayList<>();
			AgentVisitOrder aoo = new AgentVisitOrder();
			for (AgentVisitOrder ao : list) {
				if (ao.getCvDate().equals(od)) {
					agentList.add(ao);
				}
			}

			for (OutSideProject out : outList) {
				if (out.getCopDate().equals(od)) {
					aoo = aoo.creatMoreObjTurnOne(projectId, null, od, od, baseDao, out, agentList, projectCreateService);
				}
			}

			map.put("customerReturnBackVisitRate", aoo.getCustomerTurnHandRate());// 客户回头率
			map.put("date", od);
			map.put("newAddTwiceVisitNum", aoo.getNewTwoVisitCustomerCount());
			map.put("newAddMyCustomer", aoo.getNewAddCollCustomerCount());
			map.put("reserveCustomerToDealRate", aoo.getGrandCusDealRate());// 储客成交比
			map.put("oldMyCustomerVisitNum", aoo.getGrandTotalOldCustomerCount());
			map.put("allReadyBuyCustomersNum", aoo.getSubscribeCustomerCount());
			map.put("oldCustomerReadyToBuyNum", aoo.getOldSubscribeCustomerCount());
			map.put("oldCustomerReadyToBuyRate", aoo.getOldCusEnterBuyRate());// 老客户认购率
			map.put("newCustomerReadyToBuyNum", aoo.getNewSubscribeCustomerCount());
			map.put("newCustomerReadyToBuyRate", aoo.getNewAddCusEnterBuyRate());// 新增客户认购率
			map.put("allContractNum", aoo.getSubscribeHouseCount());
			map.put("allContractMoney", SysContent.get2Double(aoo.getSubscribeMoney() / 10000));
			map.put("reachContractMoney", SysContent.get2Double(aoo.getSubscribeGetMoney() / 10000));
			map.put("haveDealNum", aoo.getSignCount());
			map.put("lockHousePrice", SysContent.get2Double(aoo.getSubscribeLockHouseMoney() / 10000));
			map.put("abandonDealNum", aoo.getGiveUpSignCount());
			map.put("readyToDealNum", aoo.getWaitSignCount());
			map.put("readyToAppointNum", aoo.getPayCount());
			map.put("haveDealHousePrice", SysContent.get2Double(aoo.getSignHouseMoney() / 10000));
			map.put("abandonDealHousePrice", SysContent.get2Double(aoo.getGiveUpHouseMoney() / 10000));
			map.put("readyToDealHousePrice", SysContent.get2Double(aoo.getWaitSignHouseMoney() / 10000));
			// 来访成交率 ： 签约新客户数/储客+流失数
			map.put("visitToDealRate", aoo.getVisitDealRate());
			map.put("readyToDealRate", aoo.getEnterBuySignRate());// 认购签约率

			dataList.add(map);

		}

		return dataList;
	}

	@Override
	public List<Map<String, Object>> findOutsideForTables(String projectId, String startTime, String endTime,
			String oneDay) {

		if (startTime == null || "".equals(startTime)) {
			startTime = DateUtil.format(DateUtil.rollDay(new Date(), -7), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}
		if (endTime == null || "".equals(endTime)) {
			endTime = DateUtil.format(DateUtil.rollDay(new Date(), -1), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}

		String thisDate = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);

		List<Map<String, Object>> dataList = new LinkedList<>();
		List<AgentVisitOrder> vvList = new ArrayList<>();
		List<OutSideProject> list = new ArrayList<>();

		if (!StringUtils.isEmpty(oneDay)) {
			if (thisDate.equals(oneDay)) {
				vvList = projectCreateService.selectMeneryCustomerData(projectId, oneDay);
				OutSideProject out = projectCreateService.selectOutSideProject(projectId, oneDay);
				list.add(out);
			} else {
				list = selectOutSideProjectForProject(projectId, startTime, endTime, oneDay);
				vvList = findAgentVisitOrderListForProject(projectId, startTime, endTime, oneDay);
			}
		} else {
			list = selectOutSideProjectForProject(projectId, startTime, endTime, oneDay);
			vvList = findAgentVisitOrderListForProject(projectId, startTime, endTime, oneDay);
		}

		List<String> day = new ArrayList<>();
		if (oneDay != null && !"".equals(oneDay)) {
			day.add(oneDay);
		} else {
			if (startTime != null && !"".equals(startTime)) {
				if (endTime != null && !"".equals(endTime)) {
					day = DateUtil.getTwoDateEveryDay(startTime, endTime);
				}
			}
		}

		for (String od : day) {

			Map<String, Object> map = new HashMap<>();
			// 总储客数
			int customerCount = 0;

			for (AgentVisitOrder ao : vvList) {
				if (ao.getCvDate().equals(od)) {
					customerCount += ao.getGrandTotalCollCustomerCount();
				}
			}

			List<OutSideProject> outList = new ArrayList<>();
			OutSideProject osp = new OutSideProject();

			for (OutSideProject op : list) {
				if (op.getCopDate().equals(od)) {
					outList.add(op);
				}
			}

			osp = osp.creatMoreObjTurnOne(projectId, baseDao, outList, projectCreateService);

			map.put("date", od);
			map.put("allGrNum", osp.getRecordCustomerCount());
			map.put("grToGetVisitNum", osp.getRecordVisitCount());
			map.put("grToNotGetVisitNum", osp.getRecordNotVisitCount());
			map.put("grToVisitRate", osp.getGrVisiteRate());
			map.put("outFieldToVisitRate", SysContent.getTwoNumberForValue(osp.getRecordVisitCount(), customerCount));
			map.put("outFieldToDealNum", osp.getOutSideDealCount());
			map.put("outFieldToDealRate", osp.getOutSideDealRate());
			map.put("guideToDealNum", osp.getGuideCustomerVisitCount());

			dataList.add(map);
		}
		return dataList;
	}

	@Override
	public Map<String, Object> findVisitDataForLabel(String projectId, String startTime, String endTime,
			String oneDay) {
		if (startTime == null || "".equals(startTime)) {
			startTime = DateUtil.format(DateUtil.rollDay(new Date(), -7), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}
		if (endTime == null || "".equals(endTime)) {
			endTime = DateUtil.format(DateUtil.rollDay(new Date(), -1), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}
		Map<String, Object> map = new HashMap<>();

		String thisDate = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		List<AgentVisitOrder> vvList = new ArrayList<>();
		if (!StringUtils.isEmpty(oneDay)) {
			startTime = oneDay;
			endTime = oneDay;
			if (thisDate.equals(oneDay)) {
				vvList = projectCreateService.selectAnalysisOfDataByProjectIdAndDay(projectId, oneDay);
			} else {
				vvList = findAgentVisitOrderListForProject(projectId, startTime, endTime, oneDay);
			}
		} else {
			vvList = findAgentVisitOrderListForProject(projectId, startTime, endTime, oneDay);
		}

		List<AgentVisitOrder> totalList = findAgentVisitOrderListForProject(projectId, null, null, null);

		List<AgentVisitOrder> agentList = new ArrayList<>();
		AgentVisitOrder avo = new AgentVisitOrder();
		for (AgentVisitOrder ao : vvList) {
			agentList.add(ao);
		}
		avo = avo.creatMoreObjTurnOne(projectId, startTime, endTime, baseDao, agentList, projectCreateService);

		map.put("totalVisitCount", avo.getVisitCount());
		map.put("validVisitCount", avo.getValidVisitCount());
		map.put("inValidVisitCount", avo.getUnValidVisitCount());
		map.put("newCustomerAccess", avo.getNewWayVisitCount());
		map.put("newCustomerAccessLosed", SysContent.getTwoNumberForValue(
				(avo.getNewWayVisitCount() - avo.getValidNewWayVisitCount()), avo.getNewWayVisitCount()));// 新客户通道流失率
		map.put("oldCustomerAccess", avo.getOldWayVisitCount());
		map.put("oldCustomerAccessLosed", SysContent.getTwoNumberForValue(
				(avo.getOldWayVisitCount() - avo.getValidOldWayVisitCount()), avo.getOldWayVisitCount()));
		map.put("appointVisitCount", avo.getAppointCount());
		map.put("appointVisitLosed", avo.getAppointUnValidReceptRate());
		map.put("replaceVisitCount", avo.getReplaceCount());
		if (avo.getVisitCount() > 0) {
			map.put("averageVisitTime", (new Long(avo.getTotalVisitTime()) / avo.getVisitCount()) + "分钟");
		} else {
			map.put("averageVisitTime", "0分钟");
		}

		List<AgentVisitOrder> totalAgentList = new ArrayList<>();
		AgentVisitOrder aoo = new AgentVisitOrder();

		// 累计接访
		for (AgentVisitOrder ao : totalList) {
			totalAgentList.add(ao);
		}
		aoo = aoo.creatMoreObjTurnOne(projectId, startTime, endTime, baseDao, totalAgentList, projectCreateService);

		// 累计接访数据
		map.put("allVisitCount", aoo.getVisitCount());
		map.put("allNewCustomerAccess", aoo.getNewWayVisitCount());
		map.put("allNewStomerAccessLosed", SysContent.getTwoNumberForValue(
				(aoo.getNewWayVisitCount() - aoo.getValidNewWayVisitCount()), aoo.getNewWayVisitCount()));
		map.put("allOldCustomerAccess", aoo.getOldWayVisitCount());
		map.put("allOldCustomerAccessLosed", SysContent.getTwoNumberForValue(
				(aoo.getOldWayVisitCount() - aoo.getValidOldWayVisitCount()), aoo.getOldWayVisitCount()));
		map.put("allAppointVisitCount", aoo.getAppointCount());
		map.put("allAppointLosed", aoo.getAppointUnValidReceptRate());
		map.put("allReplaceVisitCount", aoo.getReplaceCount());
		if (aoo.getVisitCount() > 0) {
			map.put("allAverageVisitTime", (new Long(aoo.getTotalVisitTime()) / aoo.getVisitCount()) + "分钟");
		} else {
			map.put("allAverageVisitTime", "0分钟");
		}

		return map;
	}

	@Override
	public Map<String, Object> findMomeryCustomerDataForLabel(String projectId, String startTime, String endTime,
			String oneDay) {
		if (startTime == null || "".equals(startTime)) {
			startTime = DateUtil.format(DateUtil.rollDay(new Date(), -7), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}
		if (endTime == null || "".equals(endTime)) {
			endTime = DateUtil.format(DateUtil.rollDay(new Date(), -1), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("thisDate", startTime);
		map.put("thisSevenDate", endTime);

		String thisDate = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);

		List<AgentVisitOrder> list = new ArrayList<>();

		if (!StringUtils.isEmpty(oneDay)) {
			startTime = oneDay;
			endTime = oneDay;
			if (thisDate.equals(oneDay)) {
				list = projectCreateService.selectMeneryCustomerData(projectId, oneDay);
			} else {
				list = findAgentVisitOrderListForProject(projectId, startTime, endTime, oneDay);
			}
		} else {
			list = findAgentVisitOrderListForProject(projectId, startTime, endTime, oneDay);
		}

		List<AgentVisitOrder> allList = findAgentVisitOrderListForProject(projectId, null, null, null);

		List<AgentVisitOrder> aList = new ArrayList<>();
		AgentVisitOrder ao1 = new AgentVisitOrder();
		for (AgentVisitOrder ao : list) {
			aList.add(ao);
		}
		ao1 = ao1.creatMoreObjTurnOne(projectId, startTime, endTime, baseDao, aList, projectCreateService);

		List<AgentVisitOrder> bList = new ArrayList<>();
		AgentVisitOrder ao2 = new AgentVisitOrder();
		for (AgentVisitOrder ao : allList) {
			bList.add(ao);
		}

		ao2 = ao2.creatMoreObjTurnOne(projectId, startTime, endTime, baseDao, bList, projectCreateService);

		map.put("newCustomerCount", ao1.getNewAddCollCustomerCount());
		map.put("customerReturnBackVisitNum", ao1.getNewTwoVisitCustomerCount());
		map.put("platformCustomerCount", ao1.getPlatformCustomerCount());
		// 平台到导客率
		map.put("platformCustomerRate",
				SysContent.getTwoNumberForValue(ao1.getPlatformCustomerCount(), ao1.getNewAddCollCustomerCount()));

		/** 累计储客 ***/
		map.put("totalNewCustomerCount", ao2.getNewAddCollCustomerCount());
		map.put("totalCustomerReturnBackVisitNum", ao2.getNewTwoVisitCustomerCount());
		map.put("totalPlatformCustomerCount", ao2.getPlatformCustomerCount());
		map.put("totalPlatformCustomerRate",
				SysContent.getTwoNumberForValue(ao2.getPlatformCustomerCount(), ao2.getNewAddCollCustomerCount()));

		return map;
	}

	@Override
	public Map<String, Object> findDealDataForLabels(String projectId) {

		Map<String, Object> map = new HashMap<>();
		String thisDate = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		List<AgentVisitOrder> allList = findAgentVisitOrderListForProject(projectId, null, null, null);
		List<OutSideProject> outList = selectOutSideProjectForProject(projectId, null, null, null);
		OutSideProject out = new OutSideProject();
		
		out = out.creatMoreObjTurnOne(projectId, baseDao, outList, projectCreateService);
		
		List<AgentVisitOrder> list = new ArrayList<>();
		AgentVisitOrder avo = new AgentVisitOrder();
		
		for (AgentVisitOrder ao : allList) {
			list.add(ao);
		}
		avo = avo.creatMoreObjTurnOne(projectId, null, thisDate, thisDate, baseDao, out, allList, projectCreateService);

		map.put("enterBuyCount", avo.getEnterBuyCount());
		map.put("appointCount", SysContent.get2Double(avo.getConfirmHouseMoney() / 10000));
		map.put("enterBuyRate", SysContent.getTwoNumberForValue(avo.getAgreeEnterCount(), avo.getEnterBuyCount()));
		map.put("signedCount", avo.getSignCount());
		map.put("visitAndBuyRate", avo.getVisitDealRate());
		map.put("recordsAndSignedRate", avo.getEnterBuySignRate());
		map.put("agreeEnterBuyCount", avo.getAgreeEnterCount());



		return map;
	}

	@Override
	public Map<String, Object> findHouseBuyDataforTables(String projectId) {

		Map<String, Object> map = new HashMap<>();
		String today = DateUtil.format(DateUtil.rollDay(new Date(), -1), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		List<HouseTypeHouseAndOrder> list = baseDao.selectHouseTypeHouseAndOrderForProject(projectId, null, null,
				today);

		List<Integer> inventoryHouseNum = new LinkedList<>();
		List<Integer> soldHouseNum = new LinkedList<>();
		List<String> houseTypeName = new LinkedList<>();

		for (HouseTypeHouseAndOrder ho : list) {
			houseTypeName.add(ho.getHouseTypeName() + "(" + ho.getArea() + "m²)");
			inventoryHouseNum.add(ho.getPutAwayCount());
			soldHouseNum.add(ho.getSignedCount());
		}

		map.put("inventoryHouseNum", inventoryHouseNum);
		map.put("soldHouseNum", soldHouseNum);
		map.put("houseTypeName", houseTypeName);

		return map;
	}

	@Override
	public List<Map<String, Object>> findVisitTopAndData(String projectId, String startTime, String endTime,
			String oneDay) {
		String thisDate = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);

		List<Map<String, Object>> dataList = new LinkedList<>();
		List<User> userList = selectDao.selectUserByProject(projectId, UserRole.AGENT);
		List<AgentVisitOrder> list = new ArrayList<>();

		if (!StringUtils.isEmpty(oneDay)) {
			if (thisDate.equals(oneDay)) {
				list = projectCreateService.getAgentVisitTopData(projectId, oneDay);
			} else {
				list = baseDao.selectAgentVisitOrderListForProject(projectId, startTime, endTime, oneDay);
			}

		} else {
			list = baseDao.selectAgentVisitOrderListForProject(projectId, startTime, endTime, oneDay);
		}

		for (User u : userList) {

			if (u.getUserStatus() == 1) {// 必须正常状态下的置业顾问

				Map<String, Object> map = new HashMap<>();
				// 接访（次）
				int visitCount = 0;
				// 有效接访（次）
				int validVisitCount = 0;
				// 接访时长（毫秒）
				long visitTime = 0L;
				// 储客数量（组）
				int momeryCuCount = 0;

				for (AgentVisitOrder ao : list) {
					if (ao.getAgentId().equals(u.getUserId())) {
						visitCount += ao.getVisitCount();
						validVisitCount += ao.getValidVisitCount();
						if (!StringUtils.isEmpty(ao.getTotalVisitTime())) {
							visitTime += new Long(ao.getTotalVisitTime());
						}
						momeryCuCount += ao.getGrandTotalCollCustomerCount();
					}
				}

				map.put("photo", u.getPhoto());
				map.put("userId", u.getUserId());
				map.put("userName", u.getUserCaption());
				map.put("visitCount", visitCount);
				map.put("validVisitCount", validVisitCount);
				map.put("averageVisitTime", visitTime / 1000 / 60);// 分钟
				map.put("momeryCuCount", momeryCuCount);
				map.put("validVisitRate", SysContent.getTwoNumberForValue(validVisitCount, visitCount));

				dataList.add(map);
			}

		}

		Collections.sort(dataList, new Comparator<Map<String, Object>>() {

			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				Integer s1 = (Integer) o1.get("visitCount");
				Integer s2 = (Integer) o2.get("visitCount");

				return s2 - s1;
			}

		});

		return dataList;
	}

	@Override
	public List<Map<String, Object>> findMemoryCuTop(String projectId, String startTime, String endTime,
			String oneDay) {
		List<Map<String, Object>> dataList = new LinkedList<>();
		List<User> userList = selectDao.selectUserByProject(projectId, UserRole.AGENT);
		String thisDate = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);

		List<AgentVisitOrder> list = new ArrayList<>();

		if (!StringUtils.isEmpty(oneDay)) {
			if (thisDate.equals(oneDay)) {
				list = projectCreateService.selectMeneryCustomerData(projectId, oneDay);
			} else {
				list = findAgentVisitOrderListForProject(projectId, startTime, endTime, oneDay);
			}
		} else {
			list = findAgentVisitOrderListForProject(projectId, startTime, endTime, oneDay);
		}

		list = baseDao.selectAgentVisitOrderListForProject(projectId, startTime, endTime, oneDay);

		for (User u : userList) {

			if (u.getUserStatus() == 1) {// 必须正常状态下的置业顾问
				Map<String, Object> map = new HashMap<>();
				int newCustomerCount = 0;

				for (AgentVisitOrder ao : list) {
					if (ao.getAgentId().equals(u.getUserId())) {
						newCustomerCount += ao.getNewAddCollCustomerCount();
					}
				}
				// 置业顾问的信息
				map.put("userImg", u.getPhoto());
				map.put("userName", u.getUserCaption());
				map.put("userId", u.getUserId());
				map.put("newCustomerCount", newCustomerCount);

				dataList.add(map);
			}
		}

		Collections.sort(dataList, new Comparator<Map<String, Object>>() {

			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				Integer s1 = (Integer) o1.get("newCustomerCount");
				Integer s2 = (Integer) o2.get("newCustomerCount");

				return s2 - s1;
			}

		});
		return dataList;
	}

	@Override
	public List<Map<String, Object>> findDealTopAndData(String projectId, String startTime, String endTime,
			String oneDay) {

		String thisDate = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		List<Map<String, Object>> dataList = new LinkedList<>();
		List<User> userList = selectDao.selectUserByProject(projectId, UserRole.AGENT);
		List<AgentVisitOrder> list = new ArrayList<>();

		if (!StringUtils.isEmpty(oneDay)) {
			if (thisDate.equals(oneDay)) {
				list = projectCreateService.selectOrderData(projectId, oneDay);
			} else {
				list = baseDao.selectAgentVisitOrderListForProject(projectId, startTime, endTime, oneDay);
			}
		} else {
			list = baseDao.selectAgentVisitOrderListForProject(projectId, startTime, endTime, oneDay);
		}

		for (User u : userList) {

			if (u.getUserStatus() == 1) {// 必须正常状态下的置业顾问
				Map<String, Object> map = new HashMap<>();
				// 认购数
				int recordCount = 0;
				// 签约数
				int signCount = 0;
				// 总货值
				long totalMoney = 0L;

				for (AgentVisitOrder ao : list) {
					recordCount += ao.getEnterBuyCount();
					signCount += ao.getSignCount();
					totalMoney += new Double(ao.getSignHouseMoney()).longValue();
				}
				map.put("userId", u.getUserId());
				map.put("userName", u.getUserCaption());
				map.put("recordCount", recordCount);
				map.put("signCount", signCount);
				map.put("totalMoney", SysContent.get2Double(new Long(totalMoney).doubleValue() / 10000));

				dataList.add(map);

			}

		}

		Collections.sort(dataList, new Comparator<Map<String, Object>>() {

			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				Integer s1 = (Integer) o1.get("signCount");
				Integer s2 = (Integer) o2.get("signCount");

				return s2 - s1;
			}

		});

		return dataList;
	}

	@Override
	public List<Map<String, Object>> findReadyToDealTopAndData(String projectId, String startTime, String endTime,
			String oneDay) {
		List<Map<String, Object>> dataList = new LinkedList<>();
		List<User> userList = selectDao.selectUserByProject(projectId, UserRole.AGENT);
		List<AgentVisitOrder> list = baseDao.selectAgentVisitOrderListForProject(projectId, startTime, endTime, oneDay);

		for (User u : userList) {

			if (u.getUserStatus() == 1) {// 必须正常状态下的置业顾问
				Map<String, Object> map = new HashMap<>();
				// 认购数
				int recordCount = 0;
				// 签约数
				int signCount = 0;
				// 总货值
				long totalMoney = 0L;

				for (AgentVisitOrder ao : list) {
					recordCount += ao.getEnterBuyCount();
					signCount += ao.getSignCount();
					totalMoney += new Double(ao.getSignHouseMoney()).longValue();
				}
				map.put("userId", u.getUserId());
				map.put("userName", u.getUserCaption());
				map.put("recordCount", recordCount);
				map.put("signCount", signCount);
				map.put("totalMoney", SysContent.get2Double(new Long(totalMoney).doubleValue() / 10000));

				dataList.add(map);

			}

		}

		Collections.sort(dataList, new Comparator<Map<String, Object>>() {

			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				Integer s1 = (Integer) o1.get("recordCount");
				Integer s2 = (Integer) o2.get("recordCount");

				return s2 - s1;
			}

		});

		return dataList;
	}

	@Override
	public List<Map<String, Object>> findAgentInfo(String projectId) {

		List<Map<String, Object>> dataList = new LinkedList<>();
		List<User> userList = selectDao.selectUserByProject(projectId, UserRole.AGENT);
		for (User u : userList) {
			if (u.getUserStatus() == 1) {
				Map<String, Object> map = new HashMap<>();
				map.put("userImg", u.getPhoto());
				map.put("userId", u.getUserId());
				map.put("userName", u.getUserCaption());
				map.put("phone", u.getPhone());
				dataList.add(map);
			}
		}

		return dataList;
	}

	@Override
	public ReportResult findProjectWeekReport(String projectId, String startTime, String endTime) {

		ReportResult result = new ReportResult();
		List<Project> project = selectDao.selectAllProject(projectId);
		for (Project p : project) {
			result.setProjectName(p.getProjectName());
		}
		result.setStartTime(startTime);
		result.setEndTime(endTime);
		result.setProjectId(projectId);
		// 接访情况
		int visitCount = 0;
		int validVisitCount = 0;
		int newCuVisitCount = 0;
		int validNewCuVisitCount = 0;
		int oldCuVisitCount = 0;

		// 储客情况
		int newCuCount = 0;
		int totalOldCuCount = 0;
		// 累计总储客数
		int totalCuCount = 0;

		List<AgentVisitOrder> list = baseDao.selectAgentVisitOrderListForProject(projectId, startTime, endTime, null);
		for (AgentVisitOrder ao : list) {
			visitCount += ao.getVisitCount();
			validVisitCount += ao.getValidVisitCount();
			newCuVisitCount += ao.getNewCustomerVisitCount();
			validNewCuVisitCount += ao.getValidNewCustomerVisitCount();
			oldCuVisitCount += ao.getOldCustomerVisitCount();
			newCuCount += ao.getNewAddCollCustomerCount();
			if (totalOldCuCount < ao.getTotalOldCustomerCount()) {
				totalOldCuCount = ao.getTotalOldCustomerCount();
			}
			if (totalCuCount < ao.getTotalCustomerCount()) {
				totalCuCount = ao.getTotalCustomerCount();
			}
		}

		// 接访客户组数
		result.setVisitCount(visitCount);
		// 有效接访率
		result.setValidVisitRate(SysContent.getTwoNumberForValue(validVisitCount, visitCount));
		// 首访有效率
		result.setValidNewCuVisitRate(SysContent.getTwoNumberForValue(validNewCuVisitCount, newCuVisitCount));
		// 老客户接访占比
		result.setOldCuVisitRate(SysContent.getTwoNumberForValue(oldCuVisitCount, visitCount));
		// 新增储客
		result.setNewCuCount(newCuCount);
		// 累计老客户
		result.setTotalOldCuCount(totalOldCuCount);
		// 累计总储客
		result.setTotalCuCount(totalCuCount);

		return result;
	}

	@Override
	public ReportResult findProjectYearReport(String projectId, String startTime, String endTime) {
		ReportResult result = new ReportResult();
		List<Project> project = selectDao.selectAllProject(projectId);
		for (Project p : project) {
			result.setProjectName(p.getProjectName());
		}
		result.setStartTime(startTime);
		result.setEndTime(endTime);
		result.setProjectId(projectId);

		// 上季度的时间
		String quarterStart = DateUtil.format(
				DateUtil.rollMonth(DateUtil.parse(startTime, DateUtil.PATTERN_CLASSICAL_SIMPLE), -3),
				DateUtil.PATTERN_CLASSICAL_SIMPLE);
		String quarterEnd = DateUtil.format(
				DateUtil.rollDay(DateUtil.parse(startTime, DateUtil.PATTERN_CLASSICAL_SIMPLE), -1),
				DateUtil.PATTERN_CLASSICAL_SIMPLE);

		// 接访情况
		int visitCount = 0;
		int validVisitCount = 0;
		int newCuVisitCount = 0;
		int validNewCuVisitCount = 0;
		int oldCuVisitCount = 0;

		// 储客情况
		int newCuCount = 0;
		int totalOldCuCount = 0;
		// 累计总储客数
		int totalCuCount = 0;
		// 总储客数
		int grandTotalCollCustomerCount = 0;

		// 本季订单详细
		// 新增认购套数
		int subscribeHouseCount = 0;
		// 新增认购金额
		long subscribeMoney = 0;
		// 新增签约套数--一套房源只有一个签约的
		int signCount = 0;
		// 新增签约金额
		long signHouseMoney = 0;
		// 签约新客户数
		int newCustomerSignedCount = 0;
		// 签约老客户数
		int oldCustomerSignedCount = 0;
		// 老客户数
		int grandTotalOldCustomerCount = 0;
		// 总认购客户数
		int subscribeCustomerCount = 0;
		// 签约总客户数
		int customerReturnBackVisitNum = 0;

		// 上季度订单详情
		// 新增认购套数
		int quarterSubscribeHouseCount = 0;
		// 新增认购金额
		long quarterSubscribeMoney = 0;
		// 新增签约套数
		int quarterSignCount = 0;
		// 新增签约金额
		long quarterSignHouseMoney = 0;

		List<AgentVisitOrder> list = baseDao.selectAgentVisitOrderListForProject(projectId, startTime, endTime, null);
		for (AgentVisitOrder ao : list) {
			visitCount += ao.getVisitCount();
			validVisitCount += ao.getValidVisitCount();
			newCuVisitCount += ao.getNewCustomerVisitCount();
			validNewCuVisitCount += ao.getValidNewCustomerVisitCount();
			oldCuVisitCount += ao.getOldCustomerVisitCount();
			newCuCount += ao.getNewAddCollCustomerCount();
			if (totalOldCuCount < ao.getTotalOldCustomerCount()) {
				totalOldCuCount = ao.getTotalOldCustomerCount();
			}
			if (totalCuCount < ao.getTotalCustomerCount()) {
				totalCuCount = ao.getTotalCustomerCount();
			}
			subscribeHouseCount += ao.getSubscribeHouseCount();
			subscribeMoney += ao.getSubscribeMoney();
			signCount += ao.getSignCount();
			signHouseMoney += ao.getSignHouseMoney();
			oldCustomerSignedCount += ao.getOldCustomerSignedCount();
			grandTotalOldCustomerCount += ao.getGrandTotalOldCustomerCount();
			subscribeCustomerCount += ao.getSubscribeCustomerCount();
			newCustomerSignedCount += ao.getNewCustomerSignedCount();
			customerReturnBackVisitNum += ao.getCustomerReturnBackVisitNum();
			grandTotalCollCustomerCount += ao.getGrandTotalCollCustomerCount();
		}

		// 上季度订单数据
		List<AgentVisitOrder> quarterList = baseDao.selectAgentVisitOrderListForProject(projectId, quarterStart,
				quarterEnd, null);

		for (AgentVisitOrder ao : quarterList) {
			quarterSubscribeHouseCount += ao.getSubscribeHouseCount();
			quarterSubscribeMoney += ao.getSubscribeMoney();
			quarterSignCount += ao.getSignCount();
			quarterSignHouseMoney += ao.getSignHouseMoney();
		}

		// 接访总数
		result.setVisitCount(visitCount);
		// 有效接访率
		result.setValidVisitRate(SysContent.getTwoNumberForValue(validVisitCount, visitCount));
		// 首访有效率
		result.setValidNewCuVisitRate(SysContent.getTwoNumberForValue(validNewCuVisitCount, newCuVisitCount));
		// 老客户接访占比
		result.setOldCuVisitRate(SysContent.getTwoNumberForValue(oldCuVisitCount, visitCount));
		// 新增储客
		result.setNewCuCount(newCuCount);
		// 累计老客户
		result.setTotalOldCuCount(totalOldCuCount);
		// 累计总储客
		result.setTotalCuCount(totalCuCount);
		// 本季新增新增认购套数
		result.setSubscribeHouseCount(subscribeHouseCount);
		// 认购套数比较上季度的增长/减少
		String subscribeHouseRate = SysContent.getTwoNumberForValue((subscribeHouseCount - quarterSubscribeHouseCount),
				quarterSubscribeHouseCount);
		result.setSubscribeHouseRate(subscribeHouseRate);
		// 本季新增认购金额
		result.setSubscribeMoney(subscribeMoney);
		// 认购金额比较上季度的增长/减少
		String subscribeMoneyRate = SysContent.getTwoNumberForValue(
				new Long(subscribeMoney - quarterSubscribeMoney).intValue(),
				new Long(quarterSubscribeMoney).intValue());
		result.setSubscribeMoneyRate(subscribeMoneyRate);
		// 本季签约套数
		result.setSignCount(signCount);
		// 本季签约套数比较上季度增长/减少
		String signRate = SysContent.getTwoNumberForValue((signCount - quarterSignCount), quarterSignCount);
		result.setSignRate(signRate);
		// 本季度签约金额
		result.setSignHouseMoney(signHouseMoney);
		// 本季度签约金额比较上季度的增长/减少
		String signHouseMoneyRate = SysContent.getTwoNumberForValue(
				new Long(signHouseMoney - quarterSignHouseMoney).intValue(),
				new Long(quarterSignHouseMoney).intValue());
		result.setSignHouseMoneyRate(signHouseMoneyRate);
		// 本季度新客户接访签约率
		result.setNewCustomerSignedRate(SysContent.getTwoNumberForValue(newCustomerSignedCount, newCuVisitCount));
		// 储客签约率
		result.setMomeryCustomerSignedRate(
				SysContent.getTwoNumberForValue(customerReturnBackVisitNum, grandTotalCollCustomerCount));
		// 老客户签约率
		result.setOldCustomerSignedRate(
				SysContent.getTwoNumberForValue(oldCustomerSignedCount, grandTotalOldCustomerCount));
		// 认购客户签约率
		result.setContratCuSignedRate(
				SysContent.getTwoNumberForValue(customerReturnBackVisitNum, subscribeCustomerCount));

		return result;
	}

}
