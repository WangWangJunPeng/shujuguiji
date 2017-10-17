package com.housesline.service.project.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.housesline.bean.AgentVisitOrder;
import com.housesline.bean.CollectionRecord;
import com.housesline.bean.ContractRecords;
import com.housesline.bean.ContractRecordsFlowRecord;
import com.housesline.bean.DataBase;
import com.housesline.bean.EnterBuy;
import com.housesline.bean.GuideRecords;
import com.housesline.bean.House;
import com.housesline.bean.HouseType;
import com.housesline.bean.HouseTypeHouseAndOrder;
import com.housesline.bean.OutSideProject;
import com.housesline.bean.Project;
import com.housesline.bean.ProjectCustomers;
import com.housesline.bean.ProjectGuide;
import com.housesline.bean.SignRecords;
import com.housesline.bean.User;
import com.housesline.bean.UserRole;
import com.housesline.bean.VisitRecords;
import com.housesline.dao.BaseDao;
import com.housesline.dao.SelectDao;
import com.housesline.service.project.ProjectCreateService;
import com.housesline.utils.DateUtil;

@Service(value = "projectCreateService")
public class ProjectCreateServiceImpl implements ProjectCreateService {

	@Resource
	private BaseDao baseDao;

	@Resource
	private SelectDao selectDao;

	@Override
	public DataBase createDataBaseForTarget(String targetId, Integer handleState, String userId) {

		DataBase dataBase = baseDao.createDataBaseForProject(targetId, handleState, userId);

		return dataBase;
	}

	@Override
	public void createDataTableForTarget(DataBase dataBase, String targetId, Integer handleState, String userId) {
		baseDao.createTableForTarget(dataBase, targetId, userId, handleState);
	}

	@Override
	public List<String> findAllProjectId() {
		return selectDao.selectAllProjectId();
	}

	/**
	 * 定时器的主方法 每天晚上的23点钟触发
	 */
	@Scheduled(cron = "0 0 1 * * ?")
	public void timingData() {

		List<String> projectIds = selectDao.selectAllProjectId();
		Date beforeDay = DateUtil.rollDay(new Date(), -1);
		String day = DateUtil.format(beforeDay, DateUtil.PATTERN_CLASSICAL_SIMPLE);
		Date startDate = new Date();
		String projectId = "";
		try {

			for (String pid : projectIds) {
				projectId = pid;
				Integer count1 = insertAnalysisOfDataByProjectIdAndTime(pid, day);
				if (count1 != 0) {
					Integer count2 = saveOrUpdateOrderData(pid, day);
					if (count2 != 0) {
						Integer count3 = saveOrUpdateMeneryCustomerData(pid, day);
						if (count3 != 0) {
							Integer count4 = saveSignDataForProjectAgent(pid, day);
							if (count4 != 0) {
								Integer count5 = saveHouseTypeHouseAndOrderByOrder(pid, day);
								if (count5 != 0) {

									saveOutSideProject(pid, day);

								}
							}
						}
					}
				}
				System.out.println(pid);
			}
			Date endDate = new Date();
			// 耗时
			new String();
			String elTime = String.valueOf(endDate.getTime() - startDate.getTime());
			CollectionRecord cr = new CollectionRecord(CollectionRecord.AUTO, DateUtil.format(new Date()), "自动归集成功",
					elTime);
			createLogForCollection(cr);
		} catch (Exception e) {
			CollectionRecord cr = new CollectionRecord(CollectionRecord.AUTO, DateUtil.format(new Date()),
					"自动归集出错，导致中断，请检查之后再重新归集,错误的项目编号:" + projectId + "堆栈信息：" + e.getMessage() + "", null);
			createLogForCollection(cr);
		}
		System.out.println("--end--");
	}

	@Override
	public void createLogForCollection(CollectionRecord cr) {
		baseDao.addCollectionLog(cr);
	}

	@Override
	public Integer createDataForOneDay(String projectId, String oneDay) {

		Date startDate = new Date();
		try {
			Integer count1 = insertAnalysisOfDataByProjectIdAndTime(projectId, oneDay);
			if (count1 != 0) {
				Integer count2 = saveOrUpdateOrderData(projectId, oneDay);
				if (count2 != 0) {
					Integer count3 = saveOrUpdateMeneryCustomerData(projectId, oneDay);
					if (count3 != 0) {
						Integer count4 = saveSignDataForProjectAgent(projectId, oneDay);
						if (count4 != 0) {
							Integer count5 = saveHouseTypeHouseAndOrderByOrder(projectId, oneDay);
							if (count5 != 0) {
								Integer count6 = saveOutSideProject(projectId, oneDay);
								if (count6 != 0) {
									Date endDate = new Date();
									new String();
									String elTime = String.valueOf(endDate.getTime() - startDate.getTime());
									CollectionRecord cr = new CollectionRecord(CollectionRecord.HAND,
											DateUtil.format(new Date()), "自动归集成功", elTime);
									createLogForCollection(cr);
								}
								return count6;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			CollectionRecord cr = new CollectionRecord(CollectionRecord.AUTO, DateUtil.format(new Date()),
					"自动归集出错，导致中断，请检查之后再重新归集,错误的项目编号:" + projectId + "堆栈信息：" + e.getMessage() + "", null);
			createLogForCollection(cr);
			return 0;
		}
		return 0;
	}

	@Override
	public Integer addFieldToTable(String tableName, String field, String type, String projectId) {
		return baseDao.addField(tableName, field, type, projectId);
	}

	/**
	 * 获取两个日期之间的每一天
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	private List<String> getTwoDateEveryDay(String startTime, String endTime) {
		List<String> list = new ArrayList<>();
		if (!startTime.equals(endTime)) {

			Date startDate = DateUtil.parse(startTime, DateUtil.PATTERN_CLASSICAL_SIMPLE);
			Date endDate = DateUtil.parse(endTime, DateUtil.PATTERN_CLASSICAL_SIMPLE);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

			Long i = startDate.getTime();
			list.add(df.format(startDate));
			list.add(df.format(DateUtil.rollDay(startDate, 1)));
			while (DateUtil.rollDay(new Date(i), 1).getTime() < endDate.getTime()) {

				i = DateUtil.rollDay(new Date(i), 1).getTime();
				list.add(df.format(DateUtil.rollDay(new Date(i), 1)));

			}
		} else {
			list.add(startTime);
		}

		return list;

	}
	
	

	@Override
	public List<AgentVisitOrder> getAnalysisOfData(String projectId, String oneDay) {
		
		List<AgentVisitOrder> list = selectAnalysisOfDataByProjectIdAndDay(projectId, oneDay);
		List<AgentVisitOrder> list1 = selectOrderData(projectId, oneDay);
		List<AgentVisitOrder> list2 = selectMeneryCustomerData(projectId, oneDay);
		List<AgentVisitOrder> rs = new ArrayList<>();
		
		for(AgentVisitOrder avo : list){
			for(AgentVisitOrder avo1 : list1){
				if(avo.getAgentId().equals(avo1.getAgentId())){
					avo.setEnterBuyCount(avo1.getEnterBuyCount());
					avo.setAgreeEnterCount(avo1.getAgreeEnterCount());
					avo.setRefuseEnterCount(avo1.getRefuseEnterCount());
					avo.setPayCount(avo1.getPayCount());
					avo.setSignCount(avo1.getSignCount());
					avo.setVisitToDealCount(avo1.getVisitToDealCount());
					avo.setRevokeOrderCount(avo1.getRevokeOrderCount());
					avo.setConfirmHouseMoney(avo1.getConfirmHouseMoney());
					avo.setSubscribeHouseCount(avo1.getSubscribeHouseCount());
					avo.setSubscribeMoney(avo1.getSubscribeMoney());
					avo.setSubscribeGetMoney(avo1.getSubscribeGetMoney());
					avo.setSubscribeLockHouseMoney(avo1.getSubscribeLockHouseMoney());
					avo.setGiveUpSignCount(avo1.getGiveUpSignCount());
					avo.setWaitSignCount(avo1.getWaitSignCount());
					avo.setSignHouseMoney(avo1.getSignHouseMoney());
					avo.setGiveUpHouseMoney(avo1.getGiveUpHouseMoney());
					avo.setWaitSignHouseMoney(avo1.getWaitSignHouseMoney());
				}
			}
			
			for(AgentVisitOrder avo2 : list2){
				if(avo.getAgentId().equals(avo2.getAgentId())){
					avo.setNewAddCollCustomerCount(avo2.getNewAddCollCustomerCount());
					avo.setGrandTotalCollCustomerCount(avo2.getGrandTotalCollCustomerCount());
					avo.setGrandTotalOldCustomerCount(avo2.getGrandTotalOldCustomerCount());
					avo.setTotalCustomerCount(avo2.getTotalCustomerCount());
					avo.setTotalOldCustomerCount(avo2.getTotalOldCustomerCount());
					avo.setNewTwoVisitCustomerCount(avo2.getNewTwoVisitCustomerCount());
					avo.setPlatformCustomerCount(avo2.getPlatformCustomerCount());
					avo.setSubscribeCustomerCount(avo2.getSubscribeCustomerCount());
					avo.setNewSubscribeCustomerCount(avo2.getNewSubscribeCustomerCount());
					avo.setOldSubscribeCustomerCount(avo2.getOldSubscribeCustomerCount());
					avo.setCustomerReturnBackVisitNum(avo2.getCustomerReturnBackVisitNum());
					avo.setOldCustomerSignedCount(avo2.getOldCustomerSignedCount());
					avo.setNewCustomerSignedCount(avo2.getNewCustomerSignedCount());
					avo.setMomeryCuDealCount(avo2.getMomeryCuDealCount());
				}
			}
			
			rs.add(avo);
		}
		
		
		
		
		return rs;
	}

	@Override
	public Integer insertAnalysisOfDataByProjectIdAndTime(String projectId, String oneDay) {

		return insertAnalysisOfDataByProjectIdAndTime(projectId, null, null, oneDay, null);
	}

	public Integer insertAnalysisOfDataByProjectIdAndTime(String projectId, String startTime, String endTime,
			String oneDay, String userId) {

		// 最后要保存到额数据
		List<AgentVisitOrder> agentDataList = new LinkedList<>();
		// 时间的集合
		List<String> day = new ArrayList<>();
		if (oneDay != null && !"".equals(oneDay)) {
			day.add(oneDay);
		} else {
			if (startTime != null && !"".equals(startTime)) {
				if (endTime != null && !"".equals(endTime)) {
					day = getTwoDateEveryDay(startTime, endTime);
				}
			}
		}

		for (String od : day) {
			System.out.println("到访: " + od);

			getAgentOneData(projectId, od, agentDataList);
		}
		return saveAgentVisitList(agentDataList, projectId, userId);
	}

	/**
	 * 获取到访需要归集的数据
	 * 
	 * @param projectId
	 * @param userList
	 * @param od
	 * @param list
	 */
	private void getAgentOneData(String projectId, String od, List<AgentVisitOrder> list) {

		List<User> userList = selectDao.selectUserByProject(projectId, UserRole.AGENT);

		for (User user : userList) {

			AgentVisitOrder avo = new AgentVisitOrder();

			// 数据时间
			String dataDate = od;
			// 流失
			int loseCustomerCount = 0;
			// 指定流失
			int LoseCustomerAppoint = 0;
			// 新登
			int NewCustomer = 0;
			// 指定新登
			int NewCustomerAppoint = 0;
			// 再到访
			int visitAgain = 0;

			// 新增到访
			int newVisitCount = 0;
			// 二次到访
			int secondVisitCount = 0;
			// 二次以上到访（不含两次）
			int moreVisitCount = 0;
			// 到访总数
			int totalVisitCount = 0;
			// 有效接访
			int effectiveNum = 0;
			// 无效接访
			int invalidNum = 0;
			// 新客户通道
			int newCustomerNum = 0;
			// 老客户通道接访
			int oldCustomerNum = 0;
			// 新客户通道有效接访
			int newCustomerEffectiveNum = 0;
			// 老客户通道有效接访
			int oldCustomerEffectiveNum = 0;
			// 指定接访量
			int appointAgentNum = 0;
			// 新客户通道指定接访
			int newCustomerAppointAgentNum = 0;
			// 老客户通道指定接访
			int oldCustomerAppointAgentNum = 0;
			// 指定有效接访
			int appointAgentEffectiveNum = 0;
			// 总替接数
			int allReplaceNum = 0;
			// 按序接访替接
			int orderReplaceNum = 0;
			// 确认老客户接访次数
			int affirmOldCustomerVisitNum = 0;
			// 总接访时长
			long totalTime = 0L;
			// 新客户接访时长
			long newVisitTime = 0L;
			// 替接接访时长
			long replaceTime = 0L;
			// 老客户接访时长
			long oldVisitTime = 0L;
			// 平均接访时长
			long timeDiff = 0L;
			//
			int totalNum = 0;
			// 新客户接访数
			int newCustomerVisitCount = 0;
			// 新客户有效接访数
			int validNewCustomerVisitCount = 0;
			// 老客户接访数
			int oldCustomerVisitCount = 0;
			// 老客户有效接访数
			int validOldCustomerVisitCount = 0;

			// 到访id
			StringBuilder sb = new StringBuilder();

			List<VisitRecords> visitList = selectDao.selectVisitRecordsByProjectAndTimeForAnalysis(null, null,
					projectId, od, user.getUserId(), null);

			totalVisitCount = visitList.size();
			for (VisitRecords v : visitList) {
				Date date = DateUtil.parse(v.getArriveTime(), DateUtil.PATTERN_CLASSICAL);
				String tt = DateUtil.format(date, DateUtil.PATTERN_CLASSICAL_SIMPLE);
				if (od.equals(tt)) {

					// 总接访时间
					if (!StringUtils.isEmpty(v.getLeaveTime()) && !StringUtils.isEmpty(v.getArriveTime())) {
						long oldLeave = DateUtil.parse(v.getLeaveTime()).getTime();
						long oldRecept = DateUtil.parse(v.getArriveTime()).getTime();
						totalTime += oldLeave - oldRecept;
					} else {
						totalTime += 60 * 60 * 1000;
					}

					sb.append(v.getVisitNo() + ",");
					// 指定接访
					if (v.getAppointUserId() != null && !"".equals(v.getAppointUserId())) {
						// 指定流失
						if (v.getPhone() == null || "".equals(v.getPhone())) {
							LoseCustomerAppoint++;
						} else { // 指定有效接访
							ProjectCustomers pc = selectDao.selectProjectCustomersByProjectIdAndPhone(projectId,
									v.getPhone());
							if (pc != null) {
								if (pc.getCreateTime() != null && !"".equals(pc.getCreateTime())) {
									Date createTime = DateUtil.parse(pc.getCreateTime(), DateUtil.PATTERN_CLASSICAL);
									Date arrTime = DateUtil.parse(v.getArriveTime(), DateUtil.PATTERN_CLASSICAL);
									// 再到访
									if (arrTime.getTime() > createTime.getTime()) {
										visitAgain++;
										affirmOldCustomerVisitNum++;
									} else {
										// 指定新登
										NewCustomerAppoint++;
									}
								}
							}
						}
					} else { // 不指定接访
						// 流失
						if (v.getPhone() == null || "".equals(v.getPhone())) {
							loseCustomerCount++;
						} else {
							// 不指定有效接访
							ProjectCustomers cu = selectDao.selectProjectCustomersByProjectIdAndPhone(projectId,
									v.getPhone());
							if (cu != null) {
								if (cu.getCreateTime() != null && !"".equals(cu.getCreateTime())) {
									Date createTime = DateUtil.parse(cu.getCreateTime(), DateUtil.PATTERN_CLASSICAL);
									Date arrTime = DateUtil.parse(v.getArriveTime(), DateUtil.PATTERN_CLASSICAL);
									if (arrTime.getTime() > createTime.getTime()) {
										visitAgain++;
										affirmOldCustomerVisitNum++;
									} else {
										NewCustomer++;
									}
								}
							}
						}
					}

					// 查找当前到访记录之前的到访数据
					List<VisitRecords> beforeVisit = new ArrayList<>();

					if (v.getPhone() != null && !"".equals(v.getPhone().trim())) {
						beforeVisit = selectDao.selectVisitBeforeTimeAndByProjectIdPhone(v.getArriveTime(), projectId,
								v.getPhone());
					}

					if (beforeVisit.size() > 0) {
						// 当前到访一次，而且之前来过一次，属于二次到访
						if (beforeVisit.size() == 1) {
							secondVisitCount++;
						} else if (beforeVisit.size() > 1) {// 来了两次以上的
							moreVisitCount++;
						}
					} else {
						// 之前没有来过说明是新增
						newVisitCount++;
					}

					/**
					 * 新客户接访数 新客户有效接访 老客户接访数 老客户有效接访数
					 */
					int flag = 0;
					for (VisitRecords vrs : beforeVisit) {
						flag++;
					}
					if (flag > 0) {
						oldCustomerVisitCount++;// 老客户接访数
						if (v.getPhone() != null && !"".equals(v.getPhone())) {
							validOldCustomerVisitCount++;// 老客户有效接访数
						}
					} else {
						newCustomerVisitCount++;// 新客户接访数
						if (v.getPhone() != null && !"".equals(v.getPhone())) {
							validNewCustomerVisitCount++;// 新客户有效接访数
						}

					}

					if (v.getPhone() != null && !"".equals(v.getPhone())) {

						effectiveNum++;

					} else {
						invalidNum++;
					}
					if ((v.getIsNew() == null || "".equals(v.getIsNew())) || v.getIsNew()) {

						newCustomerNum++;
						if (v.getLeaveTime() != null && !"".equals(v.getLeaveTime()) && v.getReceptTime() != null
								&& !"".equals(v.getReceptTime())) {
							long newLeave = DateUtil.parse(v.getLeaveTime()).getTime();
							long newRecept = DateUtil.parse(v.getReceptTime()).getTime();
							// 新客户通道接访时长
							newVisitTime += newLeave - newRecept;
						} else {
							if (v.getArriveTime() != null) {
								newVisitTime += 60 * 60 * 1000;
							}
						}
						if (v.getPhone() != null && !"".equals(v.getPhone())) {
							newCustomerEffectiveNum++;
						}
					} else {
						oldCustomerNum++;
						if (v.getLeaveTime() != null && !"".equals(v.getLeaveTime()) && v.getReceptTime() != null
								&& !"".equals(v.getReceptTime())) {
							long oldLeave = DateUtil.parse(v.getLeaveTime()).getTime();

							long oldRecept = DateUtil.parse(v.getReceptTime()).getTime();
							oldVisitTime += oldLeave - oldRecept;
						} else {
							if (v.getArriveTime() != null) {
								oldVisitTime += 60 * 60 * 1000;
							}
						}
						if (v.getPhone() != null && !"".equals(v.getPhone())) {
							oldCustomerEffectiveNum++;
						}
					}
					// 指定接待只要是指定置业顾问的编码不为空即可
					if (v.getAppointUserId() != null && !"".equals(v.getAppointUserId())) {
						// 指定 只要指定接访的置业顾问的id不为空就是指定接待
						appointAgentNum++;
						if ((v.getIsNew() == null || "".equals(v.getIsNew())) || v.getIsNew()) {
							newCustomerAppointAgentNum++;
						} else {
							oldCustomerAppointAgentNum++;
						}
						if (v.getPhone() != null && !"".equals(v.getPhone())) {

							appointAgentEffectiveNum++;
						}

					}
					/**
					 * 替接 接访的置业顾问id跟所属置业顾问的id不相同
					 */
					ProjectCustomers pc = selectDao.selectProjectCustomersByProjectIdAndPhone(projectId, v.getPhone());
					if (pc != null) {
						if (!StringUtils.isEmpty(pc.getOwnerUserId()) && !pc.getOwnerUserId().equals(v.getUserId())) {
							allReplaceNum++;
							if (v.getLeaveTime() != null && !"".equals(v.getLeaveTime()) && v.getReceptTime() != null
									&& !"".equals(v.getReceptTime())) {
								long replaceLeave = DateUtil.parse(v.getLeaveTime()).getTime();
								long replaceRecept = DateUtil.parse(v.getReceptTime()).getTime();
								replaceTime += replaceLeave - replaceRecept;
							} else {
								if (v.getArriveTime() != null) {
									replaceTime += 60 * 60 * 1000;
								}
							}
						}
					}

					if (v.getLeaveTime() != null && !"".equals(v.getLeaveTime()) && v.getReceptTime() != null
							&& !"".equals(v.getReceptTime())) {
						long leave = DateUtil.parse(v.getLeaveTime()).getTime();
						long recept = DateUtil.parse(v.getReceptTime()).getTime();
						timeDiff += leave - recept;
						totalNum++;
					} else {
						// 如果没有离开时间，就默认1小时
						if (v.getArriveTime() != null && !"".equals(v.getArriveTime())) {
							timeDiff += 60 * 60 * 1000;
							totalNum++;
						}
					}
				}
			}

			avo.setCvDate(dataDate);
			avo.setCollDateTime(DateUtil.format(new Date()));
			avo.setAgentId(user.getUserId());
			avo.setAgentName(user.getUserCaption());
			avo.setAgentPhone(user.getPhone());
			avo.setAgentStatus(user.getUserStatus());
			avo.setVisitCount(totalVisitCount);
			avo.setValidVisitCount(effectiveNum);
			avo.setNewVisitCount(NewCustomer);
			avo.setVisitAgain(visitAgain);
			avo.setSecondVisitCount(secondVisitCount);
			avo.setMoreVisitCount(moreVisitCount);
			avo.setAppointCount(appointAgentNum);
			avo.setNewWayVisitCount(newCustomerNum);
			avo.setValidNewWayVisitCount(newCustomerEffectiveNum);
			avo.setAppointNewWayVisitCount(newCustomerAppointAgentNum);
			avo.setOldWayVisitCount(oldCustomerNum);
			avo.setValidOldWayVisitCount(oldCustomerEffectiveNum);
			avo.setAppointOldWayVisitCount(oldCustomerAppointAgentNum);
			avo.setReplaceCount(allReplaceNum);
			avo.setOrderReplaceVisitCount(orderReplaceNum);
			avo.setAppointLosedCount(LoseCustomerAppoint);
			avo.setLosedCount(loseCustomerCount);
			avo.setNewAppointCount(NewCustomerAppoint);
			avo.setNewCustomerVisitTime(String.valueOf(newVisitTime / 1000 / 60));
			avo.setOldCustomerVisitTime(String.valueOf(oldVisitTime / 1000 / 60));
			avo.setReplaceVisitTime(String.valueOf(replaceTime / 1000 / 60));
			avo.setAverageVisitTime(String.valueOf(timeDiff / 1000 / 60));
			avo.setVisitId(sb.toString());
			avo.setAffirmOldCustomerVisitNum(affirmOldCustomerVisitNum);
			avo.setTotalVisitTime(new Long(totalTime / 1000 / 60).toString());
			avo.setNewCustomerVisitCount(newCustomerVisitCount);
			avo.setValidNewCustomerVisitCount(validNewCustomerVisitCount);
			avo.setOldCustomerVisitCount(oldCustomerVisitCount);
			avo.setValidOldCustomerVisitCount(validOldCustomerVisitCount);
			list.add(avo);
		}
	}

	@Override
	public List<AgentVisitOrder> selectAnalysisOfDataByProjectIdAndDay(String projectId, String oneDay) {
		// 查找所有的置业顾问的Id

		List<AgentVisitOrder> list = new ArrayList<>();

		getAgentOneData(projectId, oneDay, list);

		return list;
	}

	/**
	 * 批量保存到访数据
	 * 
	 * @param list
	 * @return
	 */
	public Integer saveAgentVisitList(List<AgentVisitOrder> list, String projectId, String userId) {
		Integer count = 0;
		try {
			for (AgentVisitOrder a : list) {
				baseDao.saveOrUpdateVisitAnalyzeData(a, projectId, userId);
				System.out.println(count++);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public Integer saveOrUpdateOrderData(String projectId, String oneDay) {
		return saveOrUpdateOrderData(projectId, null, null, oneDay, null);
	}

	@Override
	public Integer saveOrUpdateOrderData(String projectId, String startTime, String endTime, String oneDay,
			String userId) {
		// 查找所有的置业顾问的Id
		List<User> userList = selectDao.selectUserByProject(projectId, UserRole.AGENT);
		// 最后要保存到额数据
		List<AgentVisitOrder> agentDataList = new LinkedList<>();
		// 时间的集合
		List<String> day = new ArrayList<>();
		if (oneDay != null && !"".equals(oneDay)) {
			day.add(oneDay);
		} else {
			if (startTime != null && !"".equals(startTime)) {
				if (endTime != null && !"".equals(endTime)) {
					day = getTwoDateEveryDay(startTime, endTime);
				}
			}
		}

		for (String od : day) {
			System.out.println("认购: " + od);
			getAgentTwoData(projectId, od, agentDataList);
		}

		return batchSaveAgentDealData(agentDataList, projectId, userId);
	}

	/**
	 * 获取到访-订单的数据
	 * 
	 * @param projectId
	 * @param od
	 * @param list
	 */
	private void getAgentTwoData(String projectId, String od, List<AgentVisitOrder> list) {

		List<User> userList = selectDao.selectUserByProject(projectId, UserRole.AGENT);

		List<ContractRecords> cList = selectDao.selectContractRecordsByProjectIdAndTimePhone(projectId, od);

		for (User user : userList) {

			AgentVisitOrder avo = new AgentVisitOrder();

			// 认购数
			int enterBuyCount = 0;
			// 同意认购数
			int agreeEnterCount = 0;
			// 拒绝认购数
			int refuseEnterCount = 0;
			// 下定数
			int payCount = 0;
			// 签约数
			int signCount = 0;
			// 来访成交数
			int visitToDealCount = 0;
			// 撤单数
			int revokeOrderCount = 0;
			// 下定房源货值
			double confirmHouseMoney = 0.0;
			// 总认购套数
			int subscribeHouseCount = 0;
			// 认购金额
			double subscribeMoney = 0.0;
			// 认购到款金额
			double subscribeGetMoney = 0.0;
			// 认购锁定房源货值
			double subscribeLockHouseMoney = 0.0;
			// 放弃签约数
			int giveUpSignCount = 0;
			// 等待签约数
			int waitSignCount = 0;
			// 已签约房源货值
			double signHouseMoney = 0.0;
			// 放弃签约房源货值
			double giveUpHouseMoney = 0.0;
			// 等待签约房源货值
			double waitSignHouseMoney = 0.0;
			// 签约的订单id（集合）
			String signedOrderId = "";
			// 拒绝的订单id（集合）
			String refusedOrderId = "";
			// 撤单的订单（集合）
			String revokeOrderId = "";
			// 下定的订单 （集合）
			String payOrderId = "";
			List<ContractRecordsFlowRecord> crfList = selectDao.selectContractRecordsFlowRecordByTime(od, projectId);
			Set<Integer> set = new HashSet<>();

			for (ContractRecords crs : cList) {
				Date date = DateUtil.parse(crs.getApplyTime(), DateUtil.PATTERN_CLASSICAL);
				String tt = DateUtil.format(date, DateUtil.PATTERN_CLASSICAL_SIMPLE);
				if (od.equals(tt)) {

					// 总认购套数

					if (crs.getUserId().equals(user.getUserId())) {
						set.add(crs.getHouseNum());
						// 使用流水进行查询
						for (ContractRecordsFlowRecord crf : crfList) {
							if (crs.getRecordNo().equals(crf.getRecordNo())) {
								// 申请数
								if (crf.getOrderSort().equals(ContractRecordsFlowRecord.RECORDSTATUS_APPLY)) {
									enterBuyCount++;
									// 认购金额
									subscribeMoney += crs.getBuyPrice();
								}
								// 同意数
								else if (crf.getOrderSort().equals(ContractRecordsFlowRecord.RECORDSTATUS_AGREE)) {
									agreeEnterCount++;
								}
								// 下定
								else if (crf.getOrderSort().equals(ContractRecordsFlowRecord.RECORDSTATUS_REDYSIGN)) {
									boolean flag = true;
									List<ContractRecordsFlowRecord> frList = selectDao
											.selectContractRecordsFlowRecordByRecordNo(crs.getRecordNo(), od);
									for (ContractRecordsFlowRecord crr : frList) {
										if (crr.getOrderSort().equals(ContractRecordsFlowRecord.RECORDSTATUS_SIGNED)) {
											flag = false;
										}
									}
									if (flag) {
										// 下定
										payCount++;
										// 待签约
										waitSignCount++;
										if (crs.getBuyPrice() != null && !"".equals(crs.getBuyPrice())) {
											waitSignHouseMoney += crs.getBuyPrice();
											confirmHouseMoney += crs.getBuyPrice();
											subscribeLockHouseMoney += crs.getBuyPrice();
										}
										payOrderId += crs.getRecordNo() + ",";
									}
									// 下的定金需要显示
									EnterBuy eb = selectDao.selectEnterBuyByProjectId(projectId);
									if (eb.getDposit() != null && !"".equals(eb.getDposit())) {
										subscribeGetMoney += eb.getDposit();
									}
								}

								// 已签约
								else if (crf.getOrderSort().equals(ContractRecordsFlowRecord.RECORDSTATUS_SIGNED)) {
									signCount++;
									visitToDealCount++;
									signHouseMoney += crs.getBuyPrice();
									signedOrderId += crs.getRecordNo() + ",";
								}
								// 否决
								else if (crf.getOrderSort().equals(ContractRecordsFlowRecord.RECORDSTATUS_REFUSE)) {
									refuseEnterCount++;
									refusedOrderId += crs.getRecordNo() + ",";
								}
								// 撤销
								else if (crf.getOrderSort().equals(ContractRecordsFlowRecord.RECORDSTATUS_BACKOUT)) {
									revokeOrderCount++;
									giveUpSignCount++;
									giveUpHouseMoney += crs.getBuyPrice();
									revokeOrderId += crs.getRecordNo() + ",";

								}

							}
						}

					}
				}
			}
			subscribeHouseCount = set.size();
			// 总认购套数
			avo.setCollDateTime(DateUtil.format(new Date()));
			avo.setCvDate(od);
			avo.setAgentId(user.getUserId());
			avo.setEnterBuyCount(enterBuyCount);
			avo.setAgreeEnterCount(agreeEnterCount);
			avo.setRefuseEnterCount(refuseEnterCount);
			avo.setPayCount(payCount);
			avo.setSignCount(signCount);
			// 来访成交数
			avo.setVisitToDealCount(visitToDealCount);
			avo.setRevokeOrderCount(revokeOrderCount);
			avo.setConfirmHouseMoney(confirmHouseMoney);
			avo.setSubscribeHouseCount(subscribeHouseCount);
			avo.setSubscribeMoney(subscribeMoney);
			avo.setSubscribeGetMoney(subscribeGetMoney);
			avo.setSubscribeLockHouseMoney(subscribeLockHouseMoney);
			avo.setGiveUpSignCount(giveUpSignCount);
			avo.setWaitSignCount(waitSignCount);
			avo.setSignHouseMoney(signHouseMoney);
			avo.setGiveUpHouseMoney(giveUpHouseMoney);
			avo.setWaitSignHouseMoney(waitSignHouseMoney);
			avo.setSignedOrderId(signedOrderId);
			avo.setRefusedOrderId(refusedOrderId);
			avo.setRevokeOrderId(revokeOrderId);
			avo.setPayOrderId(payOrderId);
			list.add(avo);
		}
	}

	@Override
	public List<AgentVisitOrder> selectOrderData(String projectId, String oneDay) {

		List<AgentVisitOrder> list = new ArrayList<>();
		getAgentTwoData(projectId, oneDay, list);

		return list;
	}

	public Integer batchSaveAgentDealData(List<AgentVisitOrder> list, String projectId, String userId) {
		Integer count = 0;
		try {
			for (AgentVisitOrder a : list) {
				baseDao.saveOrUpdateAgentDataForOrder(a, projectId, userId);
				System.out.println(count++);
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Integer saveOrUpdateMeneryCustomerData(String projectId, String oneDay) {
		return saveOrUpdateMeneryCustomerData(projectId, null, null, oneDay, null);
	}

	private String getThisTimeLastTime(String time) {

		Date date = DateUtil.parse(time, DateUtil.PATTERN_CLASSICAL_SIMPLE);
		String start1 = DateUtil.format(date);
		Date s = DateUtil.parse(start1, DateUtil.PATTERN_CLASSICAL);
		Date d = DateUtil.getIntegralEndTime(s);

		return DateUtil.format(d);
	}

	@Override
	public Integer saveOrUpdateMeneryCustomerData(String projectId, String startTime, String endTime, String oneDay,
			String userId) {

		// 查找所有的置业顾问的Id
		List<User> userList = selectDao.selectUserByProject(projectId, UserRole.AGENT);
		// 最后要保存到额数据
		List<AgentVisitOrder> agentDataList = new LinkedList<>();
		// 时间的集合
		List<String> day = new ArrayList<>();
		if (oneDay != null && !"".equals(oneDay)) {
			day.add(oneDay);
		} else {
			if (startTime != null && !"".equals(startTime)) {
				if (endTime != null && !"".equals(endTime)) {
					day = getTwoDateEveryDay(startTime, endTime);
				}
			}
		}

		for (String od : day) {

			System.out.println("储客: " + od);
			getAgentThreeData(projectId, od, agentDataList);

		}

		return batchSaveAgentMeneryCustomer(agentDataList, projectId, userId);
	}

	private void getAgentThreeData(String projectId, String od, List<AgentVisitOrder> dataList) {

		List<User> userList = selectDao.selectUserByProject(projectId, UserRole.AGENT);

		for (User user : userList) {

			AgentVisitOrder avo = new AgentVisitOrder();
			avo.setAgentId(user.getUserId());
			avo.setAgentStatus(user.getUserStatus());
			avo.setCvDate(od);
			avo.setCollDateTime(DateUtil.format(new Date()));

			// 新增储客数
			int newAddCollCustomerCount = 0;
			// 总储客数
			int grandTotalCollCustomerCount = 0;
			// 老客户数
			int grandTotalOldCustomerCount = 0;
			// 总储客（截止当前时间所有累计）
			int totalCustomerCount = 0;
			// 老客户数(截止当前时间所有累计)
			int totalOldCustomerCount = 0;
			// 新增二次来访客户数
			int newTwoVisitCustomerCount = 0;
			// 平台导客数
			int platformCustomerCount = 0;
			// 总认购客户数
			int subscribeCustomerCount = 0;
			// 新客户认购数
			int newSubscribeCustomerCount = 0;
			// 老客户认购数
			int oldSubscribeCustomerCount = 0;
			// 成交客户数(借用该字段)
			int customerReturnBackVisitNum = 0;
			// 签约老客户数
			int oldCustomerSignedCount = 0;
			// 签约新客户数
			int newCustomerSignedCount = 0;
			// 储客成交数
			int momeryCuDealCount = 0;

			Map<String, Set<String>> map = selectDao.selectMeneryCustomerPhone(user.getUserId(), projectId, od, null,
					null);

			List<ProjectCustomers> pcList = selectDao.selectTotalCustomerBeforeDay(projectId, od, user.getUserId());
			List<VisitRecords> vrList = selectDao.selectVisitDataBeforeDay(projectId, od, user.getUserId());
			List<ProjectCustomers> newList = selectDao.selectCustomerCreateByDay(od, projectId, user.getUserId());

			// 储客成交数
			for (ProjectCustomers pp : newList) {
				boolean flag = false;
				List<ContractRecords> ccList = selectDao.selectContractRecordsByCustomerPhone(projectId,
						pp.getProjectCustomerPhone(), od);
				for (ContractRecords ccs : ccList) {
					List<ContractRecordsFlowRecord> frList = selectDao.selectContractRecordsFlowRecordByTime(od,
							projectId);
					for (ContractRecordsFlowRecord contractRecordsFlowRecord : frList) {
						if (contractRecordsFlowRecord.getOrderSort()
								.equals(contractRecordsFlowRecord.RECORDSTATUS_SIGNED)) {
							flag = true;
						}
					}
				}
				if (flag) {
					momeryCuDealCount++;
				}
			}

			// 截止到这一天的老客户数 只要是截止这一天之前到访过两次
			for (ProjectCustomers pc : pcList) {
				Integer count = 0;
				for (VisitRecords crs : vrList) {
					if (!StringUtils.isEmpty(crs.getPhone()) && !StringUtils.isEmpty(pc.getProjectCustomerPhone())) {
						if (crs.getPhone().equals(pc.getProjectCustomerPhone())) {
							count++;
						}
					}
				}

				if (count > 1) {
					totalOldCustomerCount++;
				}
			}
			totalCustomerCount = pcList.size();

			Set<String> newSet = map.get("newCustomerCount");
			Set<String> twoSet = map.get("newTwoCustomerCount");
			Set<String> totalSet = map.get("totalCustomerCount");
			Set<String> pSet = map.get("platformCustomerCount");

			newAddCollCustomerCount = newSet.size();
			grandTotalCollCustomerCount = totalSet.size();
			grandTotalOldCustomerCount = twoSet.size();
			newTwoVisitCustomerCount = twoSet.size();
			platformCustomerCount = pSet.size();

			List<ContractRecordsFlowRecord> list = selectDao.selectContractRecordsFlowRecordByTime(od, projectId);
			Set<Integer> recordSet = new HashSet<>();
			Set<Integer> signedSet = new HashSet<>();
			for (ContractRecordsFlowRecord crs : list) {
				recordSet.add(crs.getRecordNo());
				if (crs.getOrderSort().equals(ContractRecordsFlowRecord.RECORDSTATUS_SIGNED)) {
					signedSet.add(crs.getRecordNo());
				}
			}

			Set<String> cuPhone = new HashSet<>();
			List<String> oldCuPhone = new ArrayList<>();
			List<String> newCuPhone = new ArrayList<>();
			// 认购客户
			for (Integer recordNo : recordSet) {

				ContractRecords cr = selectDao.findContractRecordsByRecordNo(projectId, recordNo);
				List<VisitRecords> vrrList = new ArrayList<>();

				if (!StringUtils.isEmpty(cr.getCustomerPhone())) {
					vrrList = selectDao.selectVisitBeforeTimeAndByProjectIdPhone(getThisTimeLastTime(od), projectId,
							cr.getCustomerPhone());
				}

				if (cr.getUserId().equals(user.getUserId())) {

					cuPhone.add(cr.getCustomerPhone());
					if (vrrList.size() >= 2) {// 老客户认购数
						oldCuPhone.add(cr.getCustomerPhone());
					} else { // 新客户认购数
						newCuPhone.add(cr.getCustomerPhone());
					}
				}

			}
			subscribeCustomerCount = cuPhone.size();
			oldSubscribeCustomerCount = oldCuPhone.size();
			newSubscribeCustomerCount = newCuPhone.size();

			Set<String> sCuPhone = new HashSet<>();
			List<String> sOldCuPhone = new ArrayList<>();
			List<String> sNewCuPhone = new ArrayList<>();
			// 签约客户
			for (Integer recordNo : signedSet) {
				ContractRecords cr = selectDao.findContractRecordsByRecordNo(projectId, recordNo);
				List<VisitRecords> vrrList = new ArrayList<>();
				if (!StringUtils.isEmpty(cr.getCustomerPhone())) {
					vrrList = selectDao.selectVisitBeforeTimeAndByProjectIdPhone(getThisTimeLastTime(od), projectId,
							cr.getCustomerPhone());
				}
				if (cr.getUserId().equals(user.getUserId())) {

					sCuPhone.add(cr.getCustomerPhone());
					if (vrrList.size() >= 2) {// 老客户签约数
						sOldCuPhone.add(cr.getCustomerPhone());
					} else { // 新客户签约数
						sNewCuPhone.add(cr.getCustomerPhone());
					}
				}
			}
			customerReturnBackVisitNum = sCuPhone.size();
			oldCustomerSignedCount = sOldCuPhone.size();
			newCustomerSignedCount = sNewCuPhone.size();

			avo.setNewAddCollCustomerCount(newAddCollCustomerCount);
			avo.setGrandTotalCollCustomerCount(grandTotalCollCustomerCount);
			avo.setGrandTotalOldCustomerCount(grandTotalOldCustomerCount);
			avo.setNewTwoVisitCustomerCount(newTwoVisitCustomerCount);
			avo.setPlatformCustomerCount(platformCustomerCount);
			avo.setSubscribeCustomerCount(subscribeCustomerCount);
			avo.setNewSubscribeCustomerCount(newSubscribeCustomerCount);
			avo.setOldSubscribeCustomerCount(oldSubscribeCustomerCount);
			avo.setCustomerReturnBackVisitNum(customerReturnBackVisitNum);// 储客成交数
			avo.setTotalCustomerCount(totalCustomerCount);
			avo.setTotalOldCustomerCount(totalOldCustomerCount);
			avo.setOldCustomerSignedCount(oldCustomerSignedCount);
			avo.setNewCustomerSignedCount(newCustomerSignedCount);
			avo.setMomeryCuDealCount(momeryCuDealCount);
			dataList.add(avo);
		}
	}

	@Override
	public List<AgentVisitOrder> selectMeneryCustomerData(String projectId, String oneDay) {
		List<AgentVisitOrder> list = new ArrayList<>();
		getAgentThreeData(projectId, oneDay, list);
		return list;
	}

	private Integer batchSaveAgentMeneryCustomer(List<AgentVisitOrder> list, String projectId, String userId) {
		Integer count = 0;
		try {
			for (AgentVisitOrder a : list) {
				baseDao.saveOrUpdateMemoryCustomerData(a, projectId, userId);
				System.out.println(count++);
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Integer saveSignDataForProjectAgent(String projectId, String oneDay) {
		return saveSignDataForProjectAgent(projectId, null, null, oneDay, null);
	}

	public Integer saveSignDataForProjectAgent(String projectId, String startTime, String endTime, String oneDay,
			String userId) {

		// 查找所有的置业顾问的Id
		List<User> userList = selectDao.selectUserByProject(projectId, UserRole.AGENT);
		// 最后要保存到额数据
		List<AgentVisitOrder> agentDataList = new LinkedList<>();
		// 时间的集合
		List<String> day = new ArrayList<>();
		if (oneDay != null && !"".equals(oneDay)) {
			day.add(oneDay);
		} else {
			if (startTime != null && !"".equals(startTime)) {
				if (endTime != null && !"".equals(endTime)) {
					day = getTwoDateEveryDay(startTime, endTime);
				}
			}
		}

		for (String od : day) {
			System.out.println("考勤: " + od);
			List<SignRecords> signList = selectDao.selectSignRecordsByProjectIdAndTime(projectId, od, startTime,
					endTime);

			for (User user : userList) {
				AgentVisitOrder avo = new AgentVisitOrder();
				avo.setAgentId(user.getUserId());
				avo.setAgentStatus(user.getUserStatus());
				avo.setCvDate(od);
				avo.setCollDateTime(DateUtil.format(new Date()));
				// 签到时间
				String signInTime = "";
				// 签退时间
				String signOutTime = "";

				for (SignRecords sr : signList) {
					Date date = DateUtil.parse(sr.getSignInTime(), DateUtil.PATTERN_CLASSICAL);
					String tt = DateUtil.format(date, DateUtil.PATTERN_CLASSICAL_SIMPLE);
					if (od.equals(tt)) {
						if (user.getUserId().equals(sr.getUserId())) {
							if (sr.getSignInTime() != null && !"".equals(sr.getSignInTime())) {
								signInTime = sr.getSignInTime();
							}
							if (sr.getSignOutTime() != null && !"".equals(sr.getSignOutTime())) {
								signOutTime = sr.getSignOutTime();
							}
						}
					}
				}

				avo.setSignInTime(signInTime);
				avo.setSignOutTime(signOutTime);
				agentDataList.add(avo);
			}
		}
		return batchSaveAgentSignData(agentDataList, projectId, userId);
	}

	private Integer batchSaveAgentSignData(List<AgentVisitOrder> list, String projectId, String userId) {
		Integer count = 0;
		try {
			for (AgentVisitOrder avo : list) {
				baseDao.saveOrUpdateSignData(avo, projectId, userId);
				System.out.println(count++);
			}
			return 1;
		} catch (Exception e) {

			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public Integer saveHouseTypeHouseAndOrderByOrder(String projectId, String oneDay) {
		return saveHouseTypeHouseAndOrderByOrder(projectId, null, null, oneDay, null);
	}

	@Override
	public Integer saveHouseTypeHouseAndOrderByOrder(String projectId, String startTime, String endTime, String oneDay,
			String userId) {
		// 查找所有房源类型
		List<HouseType> htList = selectDao.selectHouseTypeByProject(projectId);
		// 查出项目所有的房源
		List<House> houses = selectDao.selectHouseByProjectAndTime(projectId);
		// 最后要保存到额数据
		List<HouseTypeHouseAndOrder> list = new LinkedList<>();
		// 时间的集合
		List<String> day = new ArrayList<>();
		if (oneDay != null && !"".equals(oneDay)) {
			day.add(oneDay);
		} else {
			if (startTime != null && !"".equals(startTime)) {
				if (endTime != null && !"".equals(endTime)) {
					day = getTwoDateEveryDay(startTime, endTime);
				}
			}
		}

		/**
		 * 思路：1.首先找出每一天的所有的房源类型 2.然后根据房源类型查找所有的房源类型 3.根据房源类型查找该类型的房源
		 * 4.根据房源查找所属的对应订单 5.根据订单查找流水记录 6.根据记录生成最后需要的数据
		 */
		for (String od : day) {
			System.out.println("房源: " + od);
			getHouseData(projectId, od, houses, htList, list);

		}
		return batchSaveHouseTypeHouseAndOrder(list, projectId, userId);
	}

	private void getHouseData(String projectId, String od, List<House> houses, List<HouseType> htList,
			List<HouseTypeHouseAndOrder> list) {
		// 查出所有订单的流水
		List<ContractRecordsFlowRecord> crfList = selectDao.selectContractRecordsFlowRecordByTime(od, projectId);
		// 所有的订单
		List<ContractRecords> cList = selectDao.selectContractRecordsByProjectIdAndTimePhone(projectId, od);

		for (HouseType ht : htList) {

			HouseTypeHouseAndOrder ho = new HouseTypeHouseAndOrder();
			ho.setChDate(od);
			ho.setCollDateTime(DateUtil.format(new Date()));
			ho.setHouseTypeId(ht.getHouseTypeId());
			ho.setHouseTypeName(ht.getHousType());
			ho.setArea(ht.getArea());
			// 认购数
			int enterBuyCount = 0;
			// 同意认购数
			int agreeEnterCount = 0;
			// 拒绝认购数
			int refuseEnterCount = 0;
			// 下定数
			int payCount = 0;
			// 待签约数
			int waitSignCount = 0;
			// 签约数
			int signCount = 0;
			// 撤单数
			int revokeOrderCount = 0;

			/** ----------房源------------- */
			// 房源总数
			int houseTotalCount = 0;
			// 上架数
			int putAwayCount = 0;
			// 下架数
			int downAwayCount = 0;
			// 在售中数
			int sallingCount = 0;
			// 签约数
			int signedCount = 0;
			// 撤销数
			int revokedHouseCount = 0;

			for (House house : houses) {

				// 如果房源匹配房源类型
				if (StringUtils.isEmpty(house.getHouseTypeId())) {
					continue;
				}
				if (StringUtils.isEmpty(ht.getHouseTypeId())) {
					continue;
				}
				if (house.getHouseTypeId().equals(ht.getHouseTypeId())) {
					// 房源类型对应的房源总数
					houseTotalCount++;
					if (house.getHouseStatus() != null && !"".equals(house.getHouseStatus())) {
						if (house.getHouseStatus() == 1) {// 上架
							putAwayCount++;
						} else if (house.getHouseStatus() == 0) {// 下架
							downAwayCount++;
						} else if (house.getHouseStatus() == 4) {// 在售中
							sallingCount++;
						} else if (house.getHouseStatus() == 5) {// 签约
							signedCount++;
						} else if (house.getHouseStatus() == 3) {// 撤销
							revokedHouseCount++;
						}
					}

					for (ContractRecords crs : cList) {

						// 如果房源订单与房源匹配
						if (crs.getHouseNum().equals(house.getHouseNum())) {
							for (ContractRecordsFlowRecord crf : crfList) {
								// 如果订单跟流水匹配
								if (crf.getRecordNo().equals(crs.getRecordNo())) {

									// 申请数
									if (crf.getOrderSort() == ContractRecordsFlowRecord.RECORDSTATUS_APPLY) {
										enterBuyCount++;
									}
									// 同意数
									else if (crf.getOrderSort() == ContractRecordsFlowRecord.RECORDSTATUS_AGREE) {
										agreeEnterCount++;
									}
									// 下定
									else if (crf.getOrderSort() == ContractRecordsFlowRecord.RECORDSTATUS_REDYSIGN) {
										payCount++;
									}
									// 待签约
									else if (crf.getOrderSort() == ContractRecordsFlowRecord.RECORDSTATUS_REDYSIGN) {
										waitSignCount++;
									}
									// 已签约
									else if (crf.getOrderSort() == ContractRecordsFlowRecord.RECORDSTATUS_SIGNED) {
										signCount++;
									}
									// 否决
									else if (crf.getOrderSort() == ContractRecordsFlowRecord.RECORDSTATUS_REFUSE) {
										refuseEnterCount++;
									}
									// 撤销
									else if (crf.getOrderSort() == ContractRecordsFlowRecord.RECORDSTATUS_BACKOUT) {
										revokeOrderCount++;
									}

								}
							}

						}

					}
				}
			}

			ho.setEnterBuyCount(enterBuyCount);
			ho.setAgreeEnterCount(agreeEnterCount);
			ho.setRefuseEnterCount(refuseEnterCount);
			ho.setPayCount(payCount);
			ho.setWaitSignCount(waitSignCount);
			ho.setSignCount(signCount);
			ho.setRevokeOrderCount(revokeOrderCount);
			ho.setHouseTotalCount(houseTotalCount);
			ho.setPutAwayCount(putAwayCount);
			ho.setDownAwayCount(downAwayCount);
			ho.setSallingCount(sallingCount);
			ho.setSignedCount(signedCount);
			ho.setRevokedHouseCount(revokedHouseCount);

			list.add(ho);

		}
	}

	@Override
	public HouseTypeHouseAndOrder selectHouseTypeHouseAndOrderByOrder(String projectId, String oneDay) {

		//TODO 这个暂时不需要
		return null;
	}

	public Integer batchSaveHouseTypeHouseAndOrder(List<HouseTypeHouseAndOrder> list, String projectId, String userId) {
		Integer count = 0;
		try {
			for (HouseTypeHouseAndOrder ho : list) {
				baseDao.saveOrUpdateHouseTypeOrderData(ho, projectId, userId);
				baseDao.saveOrUpdateHouseData(ho, projectId, userId);
				System.out.println(count++);
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Integer saveOutSideProject(String projectId, String oneDay) {
		return saveOutSideProject(projectId, null, null, oneDay, null);
	}

	private boolean findUserRole(String userId) {
		Integer roleIdd = selectDao.selectRoleByUser(userId);
		if (roleIdd == 1 || roleIdd == 2) {
			return true;
		}
		if (roleIdd == 3) {
			return false;
		}
		return false;
	}

	/**
	 * @param projectId
	 * @param startTime
	 * @param endTime
	 * @param oneDay
	 * @param userId
	 * @return
	 */
	@Override
	public Integer saveOutSideProject(String projectId, String startTime, String endTime, String oneDay,
			String userId) {

		// 最后要保存的数据
		List<OutSideProject> list = new LinkedList<>();
		// 时间的集合
		List<String> day = new ArrayList<>();
		if (oneDay != null && !"".equals(oneDay)) {
			day.add(oneDay);
		} else {
			if (startTime != null && !"".equals(startTime)) {
				if (endTime != null && !"".equals(endTime)) {
					day = getTwoDateEveryDay(startTime, endTime);
				}
			}
		}

		for (String od : day) {
			System.out.println("外场: " + od);
			OutSideProject op = new OutSideProject();
			getOutSideData(projectId, od, op);

			list.add(op);
		}
		return batchSaveOutSideData(list, projectId, userId);
	}

	private void getOutSideData(String projectId, String od, OutSideProject op){
		
		op.setCopDate(od);
		op.setCollDateTime(DateUtil.format(new Date()));

		// 带看数(去除)
		int guideCount = 0;
		// 案场到访总数
		int visitedCount = 0;
		// 备案组数
		int recordCustomerCount = 0;
		// 备案到访数
		int recordVisitCount = 0;
		// 备案未到访数
		int recordNotVisitCount = 0;
		// 带看认购数
		int guiCustomerRecordCount = 0;
		// 分销认购数
		int outSideCustomerRecordCount = 0;
		// 带看成交数(中介备案，有效到访，置业顾问发起)
		int guideCustomerVisitCount = 0;
		// 分销成交数(中介备案，中介发起)
		int outSideDealCount = 0;
		// 内场成交数(中介无备案，置业顾问发起)
		int intFiledToDealNum = 0;
		// 分销同意认购数
		int outSideAgreeRecordCount = 0;
		// 分销拒绝认购数
		int outSideRefuseRecordCount = 0;
		// 分销待签约数(下定)
		int outSideWaitSignCount = 0;
		// 分销撤单数
		int outSideRevokeOrderCount = 0;
		// 分销认购套数
		int outSideSubscribeHouseCount = 0;
		// 分销认购金额
		double outSideSubscribeMoney = 0;
		// 分销认购到款金额
		double outSideSubscribeGetMoney = 0;
		// 分销认购锁定房源货值
		double outSideSubscribeLockHouseMoney = 0;
		// 分销撤单房源货值
		double outSideGiveUpHouseMoney = 0;
		// 分销等待签约房源货值
		double outSideWaitSignHouseMoney = 0;
		// 分销签约房源货值
		double outSideSignHouseMoney = 0;
		// 分销认购总客户数
		int outSideRecordCuCount = 0;
		// 分销新客户认购数
		int outSideNewCuRecordCount = 0;
		// 分销老客户认购数
		int outSideOldCuRecordCount = 0;
		// 签约总客户数
		int outSideSignCuCount = 0;
		// 分销新客户签约数
		int outSideNewCuSignCount = 0;
		// 分销老客户签约数
		int outSideOldSignCount = 0;

		// List<GuideRecords> grList =
		// selectDao.selectGuideRecordsByProjectAndTime(projectId, od);
		List<VisitRecords> vrList = selectDao.selectVisitRecordsByProjectAndTimeForAnalysis(projectId, od);
		List<ContractRecordsFlowRecord> cList = selectDao.selectContractRecordsFlowRecordByTime(od, projectId);

		for (VisitRecords vr : vrList) {
			visitedCount++;
		}

		// 报备到访数
		recordVisitCount = selectDao.selectRecordVisitCount(projectId, od);
		// 报备数
		recordCustomerCount = selectDao.selectRecordCount(projectId, od);
		// 报备未到访数
		recordNotVisitCount = recordCustomerCount - recordVisitCount;

		List<String> ssList = new ArrayList<>();
		List<String> rrList = new ArrayList<>();
		Set<Integer> houseSet = new HashSet<>();
		Set<Integer> recordSet = new HashSet<>();
		Set<Integer> signedSet = new HashSet<>();
		for (ContractRecordsFlowRecord cr : cList) {

			ContractRecords crr = selectDao.findContractRecordsByRecordNo(projectId, cr.getRecordNo());
			boolean flag = findUserRole(crr.getUserId());
			if (flag) {
				houseSet.add(crr.getHouseNum());
				recordSet.add(cr.getRecordNo());// 认购编号
			}
			// 申请
			if (cr.getOrderSort().equals(ContractRecordsFlowRecord.RECORDSTATUS_APPLY)) {
				// 认购
				if (flag) {
					outSideCustomerRecordCount++;
					outSideSubscribeMoney += crr.getBuyPrice();
				} else {
					rrList.add(crr.getCustomerPhone());
				}

			}
			// 同意认购
			else if (cr.getOrderSort().equals(ContractRecordsFlowRecord.RECORDSTATUS_AGREE)) {

				if (flag) {
					outSideAgreeRecordCount++;
				}
			} else if (cr.getOrderSort().equals(ContractRecordsFlowRecord.RECORDSTATUS_REDYSIGN)) {

				if (flag) {
					boolean flag1 = true;
					List<ContractRecordsFlowRecord> frList = selectDao
							.selectContractRecordsFlowRecordByRecordNo(crr.getRecordNo(), od);
					for (ContractRecordsFlowRecord crss : frList) {
						if (crss.getOrderSort().equals(ContractRecordsFlowRecord.RECORDSTATUS_SIGNED)) {
							flag1 = false;
						}
					}
					if (flag1) {
						// 下定
						outSideWaitSignCount++;
						// 待签约
						if (crr.getBuyPrice() != null && !"".equals(crr.getBuyPrice())) {
							outSideWaitSignHouseMoney += crr.getBuyPrice();
						}
						EnterBuy eb = selectDao.selectEnterBuyByProjectId(projectId);
						if (eb.getDposit() != null && !"".equals(eb.getDposit())) {
							outSideSubscribeGetMoney += eb.getDposit();// 分销认购到款金额
						}
					}
				}
			}

			// 签约
			else if (cr.getOrderSort().equals(ContractRecordsFlowRecord.RECORDSTATUS_SIGNED)) {// 签约

				if (flag) {
					signedSet.add(cr.getRecordNo());// 签约编号
					outSideDealCount++;
					outSideSignHouseMoney += crr.getBuyPrice();
				} else {
					ssList.add(crr.getCustomerPhone());
				}
			}
			// 否决
			else if (cr.getOrderSort().equals(ContractRecordsFlowRecord.RECORDSTATUS_REFUSE)) {
				if (flag) {
					outSideRefuseRecordCount++;
				}
			}

			// 撤销
			else if (cr.getOrderSort().equals(ContractRecordsFlowRecord.RECORDSTATUS_BACKOUT)) {
				if (flag) {
					outSideRevokeOrderCount++;
					outSideGiveUpHouseMoney += crr.getBuyPrice();
				}
			}

		}

		// 分销的客户成交分析
		Set<String> cuPhone = new HashSet<>();// 认购客户电话
		List<String> oldCuPhone = new ArrayList<>();// 老客户认购电话
		List<String> newCuPhone = new ArrayList<>();// 新客户认购电话

		// 认购客户
		for (Integer recordNo : recordSet) {

			ContractRecords cr = selectDao.findContractRecordsByRecordNo(projectId, recordNo);
			List<VisitRecords> vrrList = new ArrayList<>();

			if (!StringUtils.isEmpty(cr.getCustomerPhone())) {
				vrrList = selectDao.selectVisitBeforeTimeAndByProjectIdPhone(getThisTimeLastTime(od), projectId,
						cr.getCustomerPhone());
			}

			cuPhone.add(cr.getCustomerPhone());
			if (vrrList.size() >= 2) {// 认购老客户数
				oldCuPhone.add(cr.getCustomerPhone());
			} else { // 认购新客户数
				newCuPhone.add(cr.getCustomerPhone());
			}

		}
		outSideRecordCuCount = cuPhone.size();
		outSideNewCuRecordCount = newCuPhone.size();
		outSideOldCuRecordCount = oldCuPhone.size();

		Set<String> sCuPhone = new HashSet<>();// 签约客户电话
		List<String> sOldCuPhone = new ArrayList<>();// 老客户签约电话
		List<String> sNewCuPhone = new ArrayList<>();// 新客户签约电话
		// 签约客户
		for (Integer recordNo : signedSet) {
			ContractRecords cr = selectDao.findContractRecordsByRecordNo(projectId, recordNo);
			List<VisitRecords> vrrList = new ArrayList<>();
			if (!StringUtils.isEmpty(cr.getCustomerPhone())) {
				vrrList = selectDao.selectVisitBeforeTimeAndByProjectIdPhone(getThisTimeLastTime(od), projectId,
						cr.getCustomerPhone());
			}

			sCuPhone.add(cr.getCustomerPhone());
			if (vrrList.size() >= 2) {// 签约老客户数
				sOldCuPhone.add(cr.getCustomerPhone());
			} else { // 签约新客户数
				sNewCuPhone.add(cr.getCustomerPhone());
			}
		}
		outSideSignCuCount = sCuPhone.size();
		outSideNewCuSignCount = sNewCuPhone.size();
		outSideOldSignCount = sOldCuPhone.size();
		/**
		 * 带看成交：1.置业顾问发起的认购 2.客户保护期内的中介备案 分销成交：1.中介发起的认购 2.中介备案
		 * 内场成交：1.置业顾问发起的认购 2.中介无备案
		 */
		// 查找客户保护期
		ProjectGuide pg = selectDao.selectProjectGuideByProjectId(projectId);
		String proDate = pg.getCustormerProtectDays();
		if (StringUtils.isEmpty(proDate)) {
			proDate = "0";
		}
		Integer protectDay = 0;
		if (pg.getCustormerProtectDays() != null && !"".equals(pg.getCustormerProtectDays())) {
			protectDay = new Integer(pg.getCustormerProtectDays());
		}
		Map<String, String> dateMap = DateUtil.getPastAnyMonthOfDate(new Date(), protectDay);
		// 带看签约数
		for (String s : ssList) {// 职业顾问发起的认购
			List<GuideRecords> gList = selectDao.selectGuideRecordsByProjectAndTime(projectId, null,
					dateMap.get("pastAnydaysStartDay"), dateMap.get("currentDateEndDay"), s);
			if (gList.size() > 0) {// 在客户保护期内有备案记录，说明是带看成交
				guideCustomerVisitCount++;
			} else {// 置业顾问发起的认购，中介无备案
				intFiledToDealNum++;
			}
		}
		// 带看认购数
		for (String ss : rrList) {
			List<GuideRecords> gList = selectDao.selectGuideRecordsByProjectAndTime(projectId, null,
					dateMap.get("pastAnydaysStartDay"), dateMap.get("currentDateEndDay"), ss);
			if (gList.size() > 0) {// 在客户保护期内有备案记录，说明是带看成交
				guiCustomerRecordCount++;
			}
		}
		outSideSubscribeHouseCount = houseSet.size();
		op.setOutSideSubscribeHouseCount(outSideSubscribeHouseCount);
		op.setVisitedCount(visitedCount);
		op.setRecordCustomerCount(recordCustomerCount);
		op.setRecordVisitCount(recordVisitCount);
		op.setRecordNotVisitCount(recordNotVisitCount);
		op.setGuideCustomerVisitCount(guideCustomerVisitCount);
		op.setOutSideDealCount(outSideDealCount);
		op.setIntFiledToDealNum(intFiledToDealNum);
		op.setGuiCustomerRecordCount(guiCustomerRecordCount);
		op.setOutSideCustomerRecordCount(outSideCustomerRecordCount);
		op.setOutSideAgreeRecordCount(outSideAgreeRecordCount);
		op.setOutSideRefuseRecordCount(outSideRefuseRecordCount);
		op.setOutSideWaitSignCount(outSideWaitSignCount);
		op.setOutSideRevokeOrderCount(outSideRevokeOrderCount);
		op.setOutSideSubscribeMoney(outSideSubscribeMoney);
		op.setOutSideSubscribeGetMoney(outSideSubscribeGetMoney);
		op.setOutSideSubscribeLockHouseMoney(outSideSubscribeLockHouseMoney);
		op.setOutSideGiveUpHouseMoney(outSideGiveUpHouseMoney);
		op.setOutSideWaitSignHouseMoney(outSideWaitSignHouseMoney);
		op.setOutSideSignHouseMoney(outSideSignHouseMoney);
		op.setOutSideRecordCuCount(outSideRecordCuCount);
		op.setOutSideNewCuRecordCount(outSideNewCuRecordCount);
		op.setOutSideOldCuRecordCount(outSideOldCuRecordCount);
		op.setOutSideSignCuCount(outSideSignCuCount);
		op.setOutSideNewCuSignCount(outSideNewCuSignCount);
		op.setOutSideOldSignCount(outSideOldSignCount);
	}
	
	@Override
	public OutSideProject selectOutSideProject(String projectId, String oneDay) {
		OutSideProject op = new OutSideProject();
		getOutSideData(projectId, oneDay, op);
		return op;
	}

	public Integer batchSaveOutSideData(List<OutSideProject> list, String projectId, String userId) {
		Integer count = 0;
		try {
			for (OutSideProject op : list) {
				baseDao.saveOrUpdateOutSideData(op, projectId, userId);
				System.out.println(count++);
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Project> findAllProject() {
		return findProjectById(null);
	}

	@Override
	public List<Project> findProjectById(String projectId) {
		return selectDao.selectAllProject(projectId);
	}

	@Override
	public List<AgentVisitOrder> getAgentVisitTopData(String projectId, String oneDay) {
		List<AgentVisitOrder> list = new ArrayList<>();
		List<AgentVisitOrder> list2 = new ArrayList<>();
		List<AgentVisitOrder> rs = new ArrayList<>();
		getAgentOneData(projectId, oneDay, list);
		getAgentThreeData(projectId, oneDay, list2);
		
		for (AgentVisitOrder avo : list) {
			for (AgentVisitOrder ao : list2) {
				if(ao.getAgentId().equals(avo.getAgentId())){
					avo.setGrandTotalCollCustomerCount(ao.getGrandTotalCollCustomerCount());
				}
			}
			rs.add(avo);
		}
		return rs;
	}


	
	
	

}
