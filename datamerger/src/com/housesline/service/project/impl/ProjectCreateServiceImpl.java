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
	 * ��ʱ���������� ÿ�����ϵ�23���Ӵ���
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
			// ��ʱ
			new String();
			String elTime = String.valueOf(endDate.getTime() - startDate.getTime());
			CollectionRecord cr = new CollectionRecord(CollectionRecord.AUTO, DateUtil.format(new Date()), "�Զ��鼯�ɹ�",
					elTime);
			createLogForCollection(cr);
		} catch (Exception e) {
			CollectionRecord cr = new CollectionRecord(CollectionRecord.AUTO, DateUtil.format(new Date()),
					"�Զ��鼯���������жϣ�����֮�������¹鼯,�������Ŀ���:" + projectId + "��ջ��Ϣ��" + e.getMessage() + "", null);
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
											DateUtil.format(new Date()), "�Զ��鼯�ɹ�", elTime);
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
					"�Զ��鼯���������жϣ�����֮�������¹鼯,�������Ŀ���:" + projectId + "��ջ��Ϣ��" + e.getMessage() + "", null);
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
	 * ��ȡ��������֮���ÿһ��
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

		// ���Ҫ���浽������
		List<AgentVisitOrder> agentDataList = new LinkedList<>();
		// ʱ��ļ���
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
			System.out.println("����: " + od);

			getAgentOneData(projectId, od, agentDataList);
		}
		return saveAgentVisitList(agentDataList, projectId, userId);
	}

	/**
	 * ��ȡ������Ҫ�鼯������
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

			// ����ʱ��
			String dataDate = od;
			// ��ʧ
			int loseCustomerCount = 0;
			// ָ����ʧ
			int LoseCustomerAppoint = 0;
			// �µ�
			int NewCustomer = 0;
			// ָ���µ�
			int NewCustomerAppoint = 0;
			// �ٵ���
			int visitAgain = 0;

			// ��������
			int newVisitCount = 0;
			// ���ε���
			int secondVisitCount = 0;
			// �������ϵ��ã��������Σ�
			int moreVisitCount = 0;
			// ��������
			int totalVisitCount = 0;
			// ��Ч�ӷ�
			int effectiveNum = 0;
			// ��Ч�ӷ�
			int invalidNum = 0;
			// �¿ͻ�ͨ��
			int newCustomerNum = 0;
			// �Ͽͻ�ͨ���ӷ�
			int oldCustomerNum = 0;
			// �¿ͻ�ͨ����Ч�ӷ�
			int newCustomerEffectiveNum = 0;
			// �Ͽͻ�ͨ����Ч�ӷ�
			int oldCustomerEffectiveNum = 0;
			// ָ���ӷ���
			int appointAgentNum = 0;
			// �¿ͻ�ͨ��ָ���ӷ�
			int newCustomerAppointAgentNum = 0;
			// �Ͽͻ�ͨ��ָ���ӷ�
			int oldCustomerAppointAgentNum = 0;
			// ָ����Ч�ӷ�
			int appointAgentEffectiveNum = 0;
			// �������
			int allReplaceNum = 0;
			// ����ӷ����
			int orderReplaceNum = 0;
			// ȷ���Ͽͻ��ӷô���
			int affirmOldCustomerVisitNum = 0;
			// �ܽӷ�ʱ��
			long totalTime = 0L;
			// �¿ͻ��ӷ�ʱ��
			long newVisitTime = 0L;
			// ��ӽӷ�ʱ��
			long replaceTime = 0L;
			// �Ͽͻ��ӷ�ʱ��
			long oldVisitTime = 0L;
			// ƽ���ӷ�ʱ��
			long timeDiff = 0L;
			//
			int totalNum = 0;
			// �¿ͻ��ӷ���
			int newCustomerVisitCount = 0;
			// �¿ͻ���Ч�ӷ���
			int validNewCustomerVisitCount = 0;
			// �Ͽͻ��ӷ���
			int oldCustomerVisitCount = 0;
			// �Ͽͻ���Ч�ӷ���
			int validOldCustomerVisitCount = 0;

			// ����id
			StringBuilder sb = new StringBuilder();

			List<VisitRecords> visitList = selectDao.selectVisitRecordsByProjectAndTimeForAnalysis(null, null,
					projectId, od, user.getUserId(), null);

			totalVisitCount = visitList.size();
			for (VisitRecords v : visitList) {
				Date date = DateUtil.parse(v.getArriveTime(), DateUtil.PATTERN_CLASSICAL);
				String tt = DateUtil.format(date, DateUtil.PATTERN_CLASSICAL_SIMPLE);
				if (od.equals(tt)) {

					// �ܽӷ�ʱ��
					if (!StringUtils.isEmpty(v.getLeaveTime()) && !StringUtils.isEmpty(v.getArriveTime())) {
						long oldLeave = DateUtil.parse(v.getLeaveTime()).getTime();
						long oldRecept = DateUtil.parse(v.getArriveTime()).getTime();
						totalTime += oldLeave - oldRecept;
					} else {
						totalTime += 60 * 60 * 1000;
					}

					sb.append(v.getVisitNo() + ",");
					// ָ���ӷ�
					if (v.getAppointUserId() != null && !"".equals(v.getAppointUserId())) {
						// ָ����ʧ
						if (v.getPhone() == null || "".equals(v.getPhone())) {
							LoseCustomerAppoint++;
						} else { // ָ����Ч�ӷ�
							ProjectCustomers pc = selectDao.selectProjectCustomersByProjectIdAndPhone(projectId,
									v.getPhone());
							if (pc != null) {
								if (pc.getCreateTime() != null && !"".equals(pc.getCreateTime())) {
									Date createTime = DateUtil.parse(pc.getCreateTime(), DateUtil.PATTERN_CLASSICAL);
									Date arrTime = DateUtil.parse(v.getArriveTime(), DateUtil.PATTERN_CLASSICAL);
									// �ٵ���
									if (arrTime.getTime() > createTime.getTime()) {
										visitAgain++;
										affirmOldCustomerVisitNum++;
									} else {
										// ָ���µ�
										NewCustomerAppoint++;
									}
								}
							}
						}
					} else { // ��ָ���ӷ�
						// ��ʧ
						if (v.getPhone() == null || "".equals(v.getPhone())) {
							loseCustomerCount++;
						} else {
							// ��ָ����Ч�ӷ�
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

					// ���ҵ�ǰ���ü�¼֮ǰ�ĵ�������
					List<VisitRecords> beforeVisit = new ArrayList<>();

					if (v.getPhone() != null && !"".equals(v.getPhone().trim())) {
						beforeVisit = selectDao.selectVisitBeforeTimeAndByProjectIdPhone(v.getArriveTime(), projectId,
								v.getPhone());
					}

					if (beforeVisit.size() > 0) {
						// ��ǰ����һ�Σ�����֮ǰ����һ�Σ����ڶ��ε���
						if (beforeVisit.size() == 1) {
							secondVisitCount++;
						} else if (beforeVisit.size() > 1) {// �����������ϵ�
							moreVisitCount++;
						}
					} else {
						// ֮ǰû������˵��������
						newVisitCount++;
					}

					/**
					 * �¿ͻ��ӷ��� �¿ͻ���Ч�ӷ� �Ͽͻ��ӷ��� �Ͽͻ���Ч�ӷ���
					 */
					int flag = 0;
					for (VisitRecords vrs : beforeVisit) {
						flag++;
					}
					if (flag > 0) {
						oldCustomerVisitCount++;// �Ͽͻ��ӷ���
						if (v.getPhone() != null && !"".equals(v.getPhone())) {
							validOldCustomerVisitCount++;// �Ͽͻ���Ч�ӷ���
						}
					} else {
						newCustomerVisitCount++;// �¿ͻ��ӷ���
						if (v.getPhone() != null && !"".equals(v.getPhone())) {
							validNewCustomerVisitCount++;// �¿ͻ���Ч�ӷ���
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
							// �¿ͻ�ͨ���ӷ�ʱ��
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
					// ָ���Ӵ�ֻҪ��ָ����ҵ���ʵı��벻Ϊ�ռ���
					if (v.getAppointUserId() != null && !"".equals(v.getAppointUserId())) {
						// ָ�� ֻҪָ���ӷõ���ҵ���ʵ�id��Ϊ�վ���ָ���Ӵ�
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
					 * ��� �ӷõ���ҵ����id��������ҵ���ʵ�id����ͬ
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
						// ���û���뿪ʱ�䣬��Ĭ��1Сʱ
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
		// �������е���ҵ���ʵ�Id

		List<AgentVisitOrder> list = new ArrayList<>();

		getAgentOneData(projectId, oneDay, list);

		return list;
	}

	/**
	 * �������浽������
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
		// �������е���ҵ���ʵ�Id
		List<User> userList = selectDao.selectUserByProject(projectId, UserRole.AGENT);
		// ���Ҫ���浽������
		List<AgentVisitOrder> agentDataList = new LinkedList<>();
		// ʱ��ļ���
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
			System.out.println("�Ϲ�: " + od);
			getAgentTwoData(projectId, od, agentDataList);
		}

		return batchSaveAgentDealData(agentDataList, projectId, userId);
	}

	/**
	 * ��ȡ����-����������
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

			// �Ϲ���
			int enterBuyCount = 0;
			// ͬ���Ϲ���
			int agreeEnterCount = 0;
			// �ܾ��Ϲ���
			int refuseEnterCount = 0;
			// �¶���
			int payCount = 0;
			// ǩԼ��
			int signCount = 0;
			// ���óɽ���
			int visitToDealCount = 0;
			// ������
			int revokeOrderCount = 0;
			// �¶���Դ��ֵ
			double confirmHouseMoney = 0.0;
			// ���Ϲ�����
			int subscribeHouseCount = 0;
			// �Ϲ����
			double subscribeMoney = 0.0;
			// �Ϲ�������
			double subscribeGetMoney = 0.0;
			// �Ϲ�������Դ��ֵ
			double subscribeLockHouseMoney = 0.0;
			// ����ǩԼ��
			int giveUpSignCount = 0;
			// �ȴ�ǩԼ��
			int waitSignCount = 0;
			// ��ǩԼ��Դ��ֵ
			double signHouseMoney = 0.0;
			// ����ǩԼ��Դ��ֵ
			double giveUpHouseMoney = 0.0;
			// �ȴ�ǩԼ��Դ��ֵ
			double waitSignHouseMoney = 0.0;
			// ǩԼ�Ķ���id�����ϣ�
			String signedOrderId = "";
			// �ܾ��Ķ���id�����ϣ�
			String refusedOrderId = "";
			// �����Ķ��������ϣ�
			String revokeOrderId = "";
			// �¶��Ķ��� �����ϣ�
			String payOrderId = "";
			List<ContractRecordsFlowRecord> crfList = selectDao.selectContractRecordsFlowRecordByTime(od, projectId);
			Set<Integer> set = new HashSet<>();

			for (ContractRecords crs : cList) {
				Date date = DateUtil.parse(crs.getApplyTime(), DateUtil.PATTERN_CLASSICAL);
				String tt = DateUtil.format(date, DateUtil.PATTERN_CLASSICAL_SIMPLE);
				if (od.equals(tt)) {

					// ���Ϲ�����

					if (crs.getUserId().equals(user.getUserId())) {
						set.add(crs.getHouseNum());
						// ʹ����ˮ���в�ѯ
						for (ContractRecordsFlowRecord crf : crfList) {
							if (crs.getRecordNo().equals(crf.getRecordNo())) {
								// ������
								if (crf.getOrderSort().equals(ContractRecordsFlowRecord.RECORDSTATUS_APPLY)) {
									enterBuyCount++;
									// �Ϲ����
									subscribeMoney += crs.getBuyPrice();
								}
								// ͬ����
								else if (crf.getOrderSort().equals(ContractRecordsFlowRecord.RECORDSTATUS_AGREE)) {
									agreeEnterCount++;
								}
								// �¶�
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
										// �¶�
										payCount++;
										// ��ǩԼ
										waitSignCount++;
										if (crs.getBuyPrice() != null && !"".equals(crs.getBuyPrice())) {
											waitSignHouseMoney += crs.getBuyPrice();
											confirmHouseMoney += crs.getBuyPrice();
											subscribeLockHouseMoney += crs.getBuyPrice();
										}
										payOrderId += crs.getRecordNo() + ",";
									}
									// �µĶ�����Ҫ��ʾ
									EnterBuy eb = selectDao.selectEnterBuyByProjectId(projectId);
									if (eb.getDposit() != null && !"".equals(eb.getDposit())) {
										subscribeGetMoney += eb.getDposit();
									}
								}

								// ��ǩԼ
								else if (crf.getOrderSort().equals(ContractRecordsFlowRecord.RECORDSTATUS_SIGNED)) {
									signCount++;
									visitToDealCount++;
									signHouseMoney += crs.getBuyPrice();
									signedOrderId += crs.getRecordNo() + ",";
								}
								// ���
								else if (crf.getOrderSort().equals(ContractRecordsFlowRecord.RECORDSTATUS_REFUSE)) {
									refuseEnterCount++;
									refusedOrderId += crs.getRecordNo() + ",";
								}
								// ����
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
			// ���Ϲ�����
			avo.setCollDateTime(DateUtil.format(new Date()));
			avo.setCvDate(od);
			avo.setAgentId(user.getUserId());
			avo.setEnterBuyCount(enterBuyCount);
			avo.setAgreeEnterCount(agreeEnterCount);
			avo.setRefuseEnterCount(refuseEnterCount);
			avo.setPayCount(payCount);
			avo.setSignCount(signCount);
			// ���óɽ���
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

		// �������е���ҵ���ʵ�Id
		List<User> userList = selectDao.selectUserByProject(projectId, UserRole.AGENT);
		// ���Ҫ���浽������
		List<AgentVisitOrder> agentDataList = new LinkedList<>();
		// ʱ��ļ���
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

			System.out.println("����: " + od);
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

			// ����������
			int newAddCollCustomerCount = 0;
			// �ܴ�����
			int grandTotalCollCustomerCount = 0;
			// �Ͽͻ���
			int grandTotalOldCustomerCount = 0;
			// �ܴ��ͣ���ֹ��ǰʱ�������ۼƣ�
			int totalCustomerCount = 0;
			// �Ͽͻ���(��ֹ��ǰʱ�������ۼ�)
			int totalOldCustomerCount = 0;
			// �����������ÿͻ���
			int newTwoVisitCustomerCount = 0;
			// ƽ̨������
			int platformCustomerCount = 0;
			// ���Ϲ��ͻ���
			int subscribeCustomerCount = 0;
			// �¿ͻ��Ϲ���
			int newSubscribeCustomerCount = 0;
			// �Ͽͻ��Ϲ���
			int oldSubscribeCustomerCount = 0;
			// �ɽ��ͻ���(���ø��ֶ�)
			int customerReturnBackVisitNum = 0;
			// ǩԼ�Ͽͻ���
			int oldCustomerSignedCount = 0;
			// ǩԼ�¿ͻ���
			int newCustomerSignedCount = 0;
			// ���ͳɽ���
			int momeryCuDealCount = 0;

			Map<String, Set<String>> map = selectDao.selectMeneryCustomerPhone(user.getUserId(), projectId, od, null,
					null);

			List<ProjectCustomers> pcList = selectDao.selectTotalCustomerBeforeDay(projectId, od, user.getUserId());
			List<VisitRecords> vrList = selectDao.selectVisitDataBeforeDay(projectId, od, user.getUserId());
			List<ProjectCustomers> newList = selectDao.selectCustomerCreateByDay(od, projectId, user.getUserId());

			// ���ͳɽ���
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

			// ��ֹ����һ����Ͽͻ��� ֻҪ�ǽ�ֹ��һ��֮ǰ���ù�����
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
			// �Ϲ��ͻ�
			for (Integer recordNo : recordSet) {

				ContractRecords cr = selectDao.findContractRecordsByRecordNo(projectId, recordNo);
				List<VisitRecords> vrrList = new ArrayList<>();

				if (!StringUtils.isEmpty(cr.getCustomerPhone())) {
					vrrList = selectDao.selectVisitBeforeTimeAndByProjectIdPhone(getThisTimeLastTime(od), projectId,
							cr.getCustomerPhone());
				}

				if (cr.getUserId().equals(user.getUserId())) {

					cuPhone.add(cr.getCustomerPhone());
					if (vrrList.size() >= 2) {// �Ͽͻ��Ϲ���
						oldCuPhone.add(cr.getCustomerPhone());
					} else { // �¿ͻ��Ϲ���
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
			// ǩԼ�ͻ�
			for (Integer recordNo : signedSet) {
				ContractRecords cr = selectDao.findContractRecordsByRecordNo(projectId, recordNo);
				List<VisitRecords> vrrList = new ArrayList<>();
				if (!StringUtils.isEmpty(cr.getCustomerPhone())) {
					vrrList = selectDao.selectVisitBeforeTimeAndByProjectIdPhone(getThisTimeLastTime(od), projectId,
							cr.getCustomerPhone());
				}
				if (cr.getUserId().equals(user.getUserId())) {

					sCuPhone.add(cr.getCustomerPhone());
					if (vrrList.size() >= 2) {// �Ͽͻ�ǩԼ��
						sOldCuPhone.add(cr.getCustomerPhone());
					} else { // �¿ͻ�ǩԼ��
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
			avo.setCustomerReturnBackVisitNum(customerReturnBackVisitNum);// ���ͳɽ���
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

		// �������е���ҵ���ʵ�Id
		List<User> userList = selectDao.selectUserByProject(projectId, UserRole.AGENT);
		// ���Ҫ���浽������
		List<AgentVisitOrder> agentDataList = new LinkedList<>();
		// ʱ��ļ���
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
			System.out.println("����: " + od);
			List<SignRecords> signList = selectDao.selectSignRecordsByProjectIdAndTime(projectId, od, startTime,
					endTime);

			for (User user : userList) {
				AgentVisitOrder avo = new AgentVisitOrder();
				avo.setAgentId(user.getUserId());
				avo.setAgentStatus(user.getUserStatus());
				avo.setCvDate(od);
				avo.setCollDateTime(DateUtil.format(new Date()));
				// ǩ��ʱ��
				String signInTime = "";
				// ǩ��ʱ��
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
		// �������з�Դ����
		List<HouseType> htList = selectDao.selectHouseTypeByProject(projectId);
		// �����Ŀ���еķ�Դ
		List<House> houses = selectDao.selectHouseByProjectAndTime(projectId);
		// ���Ҫ���浽������
		List<HouseTypeHouseAndOrder> list = new LinkedList<>();
		// ʱ��ļ���
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
		 * ˼·��1.�����ҳ�ÿһ������еķ�Դ���� 2.Ȼ����ݷ�Դ���Ͳ������еķ�Դ���� 3.���ݷ�Դ���Ͳ��Ҹ����͵ķ�Դ
		 * 4.���ݷ�Դ���������Ķ�Ӧ���� 5.���ݶ���������ˮ��¼ 6.���ݼ�¼���������Ҫ������
		 */
		for (String od : day) {
			System.out.println("��Դ: " + od);
			getHouseData(projectId, od, houses, htList, list);

		}
		return batchSaveHouseTypeHouseAndOrder(list, projectId, userId);
	}

	private void getHouseData(String projectId, String od, List<House> houses, List<HouseType> htList,
			List<HouseTypeHouseAndOrder> list) {
		// ������ж�������ˮ
		List<ContractRecordsFlowRecord> crfList = selectDao.selectContractRecordsFlowRecordByTime(od, projectId);
		// ���еĶ���
		List<ContractRecords> cList = selectDao.selectContractRecordsByProjectIdAndTimePhone(projectId, od);

		for (HouseType ht : htList) {

			HouseTypeHouseAndOrder ho = new HouseTypeHouseAndOrder();
			ho.setChDate(od);
			ho.setCollDateTime(DateUtil.format(new Date()));
			ho.setHouseTypeId(ht.getHouseTypeId());
			ho.setHouseTypeName(ht.getHousType());
			ho.setArea(ht.getArea());
			// �Ϲ���
			int enterBuyCount = 0;
			// ͬ���Ϲ���
			int agreeEnterCount = 0;
			// �ܾ��Ϲ���
			int refuseEnterCount = 0;
			// �¶���
			int payCount = 0;
			// ��ǩԼ��
			int waitSignCount = 0;
			// ǩԼ��
			int signCount = 0;
			// ������
			int revokeOrderCount = 0;

			/** ----------��Դ------------- */
			// ��Դ����
			int houseTotalCount = 0;
			// �ϼ���
			int putAwayCount = 0;
			// �¼���
			int downAwayCount = 0;
			// ��������
			int sallingCount = 0;
			// ǩԼ��
			int signedCount = 0;
			// ������
			int revokedHouseCount = 0;

			for (House house : houses) {

				// �����Դƥ�䷿Դ����
				if (StringUtils.isEmpty(house.getHouseTypeId())) {
					continue;
				}
				if (StringUtils.isEmpty(ht.getHouseTypeId())) {
					continue;
				}
				if (house.getHouseTypeId().equals(ht.getHouseTypeId())) {
					// ��Դ���Ͷ�Ӧ�ķ�Դ����
					houseTotalCount++;
					if (house.getHouseStatus() != null && !"".equals(house.getHouseStatus())) {
						if (house.getHouseStatus() == 1) {// �ϼ�
							putAwayCount++;
						} else if (house.getHouseStatus() == 0) {// �¼�
							downAwayCount++;
						} else if (house.getHouseStatus() == 4) {// ������
							sallingCount++;
						} else if (house.getHouseStatus() == 5) {// ǩԼ
							signedCount++;
						} else if (house.getHouseStatus() == 3) {// ����
							revokedHouseCount++;
						}
					}

					for (ContractRecords crs : cList) {

						// �����Դ�����뷿Դƥ��
						if (crs.getHouseNum().equals(house.getHouseNum())) {
							for (ContractRecordsFlowRecord crf : crfList) {
								// �����������ˮƥ��
								if (crf.getRecordNo().equals(crs.getRecordNo())) {

									// ������
									if (crf.getOrderSort() == ContractRecordsFlowRecord.RECORDSTATUS_APPLY) {
										enterBuyCount++;
									}
									// ͬ����
									else if (crf.getOrderSort() == ContractRecordsFlowRecord.RECORDSTATUS_AGREE) {
										agreeEnterCount++;
									}
									// �¶�
									else if (crf.getOrderSort() == ContractRecordsFlowRecord.RECORDSTATUS_REDYSIGN) {
										payCount++;
									}
									// ��ǩԼ
									else if (crf.getOrderSort() == ContractRecordsFlowRecord.RECORDSTATUS_REDYSIGN) {
										waitSignCount++;
									}
									// ��ǩԼ
									else if (crf.getOrderSort() == ContractRecordsFlowRecord.RECORDSTATUS_SIGNED) {
										signCount++;
									}
									// ���
									else if (crf.getOrderSort() == ContractRecordsFlowRecord.RECORDSTATUS_REFUSE) {
										refuseEnterCount++;
									}
									// ����
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

		//TODO �����ʱ����Ҫ
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

		// ���Ҫ���������
		List<OutSideProject> list = new LinkedList<>();
		// ʱ��ļ���
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
			System.out.println("�ⳡ: " + od);
			OutSideProject op = new OutSideProject();
			getOutSideData(projectId, od, op);

			list.add(op);
		}
		return batchSaveOutSideData(list, projectId, userId);
	}

	private void getOutSideData(String projectId, String od, OutSideProject op){
		
		op.setCopDate(od);
		op.setCollDateTime(DateUtil.format(new Date()));

		// ������(ȥ��)
		int guideCount = 0;
		// ������������
		int visitedCount = 0;
		// ��������
		int recordCustomerCount = 0;
		// ����������
		int recordVisitCount = 0;
		// ����δ������
		int recordNotVisitCount = 0;
		// �����Ϲ���
		int guiCustomerRecordCount = 0;
		// �����Ϲ���
		int outSideCustomerRecordCount = 0;
		// �����ɽ���(�н鱸������Ч���ã���ҵ���ʷ���)
		int guideCustomerVisitCount = 0;
		// �����ɽ���(�н鱸�����н鷢��)
		int outSideDealCount = 0;
		// �ڳ��ɽ���(�н��ޱ�������ҵ���ʷ���)
		int intFiledToDealNum = 0;
		// ����ͬ���Ϲ���
		int outSideAgreeRecordCount = 0;
		// �����ܾ��Ϲ���
		int outSideRefuseRecordCount = 0;
		// ������ǩԼ��(�¶�)
		int outSideWaitSignCount = 0;
		// ����������
		int outSideRevokeOrderCount = 0;
		// �����Ϲ�����
		int outSideSubscribeHouseCount = 0;
		// �����Ϲ����
		double outSideSubscribeMoney = 0;
		// �����Ϲ�������
		double outSideSubscribeGetMoney = 0;
		// �����Ϲ�������Դ��ֵ
		double outSideSubscribeLockHouseMoney = 0;
		// ����������Դ��ֵ
		double outSideGiveUpHouseMoney = 0;
		// �����ȴ�ǩԼ��Դ��ֵ
		double outSideWaitSignHouseMoney = 0;
		// ����ǩԼ��Դ��ֵ
		double outSideSignHouseMoney = 0;
		// �����Ϲ��ܿͻ���
		int outSideRecordCuCount = 0;
		// �����¿ͻ��Ϲ���
		int outSideNewCuRecordCount = 0;
		// �����Ͽͻ��Ϲ���
		int outSideOldCuRecordCount = 0;
		// ǩԼ�ܿͻ���
		int outSideSignCuCount = 0;
		// �����¿ͻ�ǩԼ��
		int outSideNewCuSignCount = 0;
		// �����Ͽͻ�ǩԼ��
		int outSideOldSignCount = 0;

		// List<GuideRecords> grList =
		// selectDao.selectGuideRecordsByProjectAndTime(projectId, od);
		List<VisitRecords> vrList = selectDao.selectVisitRecordsByProjectAndTimeForAnalysis(projectId, od);
		List<ContractRecordsFlowRecord> cList = selectDao.selectContractRecordsFlowRecordByTime(od, projectId);

		for (VisitRecords vr : vrList) {
			visitedCount++;
		}

		// ����������
		recordVisitCount = selectDao.selectRecordVisitCount(projectId, od);
		// ������
		recordCustomerCount = selectDao.selectRecordCount(projectId, od);
		// ����δ������
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
				recordSet.add(cr.getRecordNo());// �Ϲ����
			}
			// ����
			if (cr.getOrderSort().equals(ContractRecordsFlowRecord.RECORDSTATUS_APPLY)) {
				// �Ϲ�
				if (flag) {
					outSideCustomerRecordCount++;
					outSideSubscribeMoney += crr.getBuyPrice();
				} else {
					rrList.add(crr.getCustomerPhone());
				}

			}
			// ͬ���Ϲ�
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
						// �¶�
						outSideWaitSignCount++;
						// ��ǩԼ
						if (crr.getBuyPrice() != null && !"".equals(crr.getBuyPrice())) {
							outSideWaitSignHouseMoney += crr.getBuyPrice();
						}
						EnterBuy eb = selectDao.selectEnterBuyByProjectId(projectId);
						if (eb.getDposit() != null && !"".equals(eb.getDposit())) {
							outSideSubscribeGetMoney += eb.getDposit();// �����Ϲ�������
						}
					}
				}
			}

			// ǩԼ
			else if (cr.getOrderSort().equals(ContractRecordsFlowRecord.RECORDSTATUS_SIGNED)) {// ǩԼ

				if (flag) {
					signedSet.add(cr.getRecordNo());// ǩԼ���
					outSideDealCount++;
					outSideSignHouseMoney += crr.getBuyPrice();
				} else {
					ssList.add(crr.getCustomerPhone());
				}
			}
			// ���
			else if (cr.getOrderSort().equals(ContractRecordsFlowRecord.RECORDSTATUS_REFUSE)) {
				if (flag) {
					outSideRefuseRecordCount++;
				}
			}

			// ����
			else if (cr.getOrderSort().equals(ContractRecordsFlowRecord.RECORDSTATUS_BACKOUT)) {
				if (flag) {
					outSideRevokeOrderCount++;
					outSideGiveUpHouseMoney += crr.getBuyPrice();
				}
			}

		}

		// �����Ŀͻ��ɽ�����
		Set<String> cuPhone = new HashSet<>();// �Ϲ��ͻ��绰
		List<String> oldCuPhone = new ArrayList<>();// �Ͽͻ��Ϲ��绰
		List<String> newCuPhone = new ArrayList<>();// �¿ͻ��Ϲ��绰

		// �Ϲ��ͻ�
		for (Integer recordNo : recordSet) {

			ContractRecords cr = selectDao.findContractRecordsByRecordNo(projectId, recordNo);
			List<VisitRecords> vrrList = new ArrayList<>();

			if (!StringUtils.isEmpty(cr.getCustomerPhone())) {
				vrrList = selectDao.selectVisitBeforeTimeAndByProjectIdPhone(getThisTimeLastTime(od), projectId,
						cr.getCustomerPhone());
			}

			cuPhone.add(cr.getCustomerPhone());
			if (vrrList.size() >= 2) {// �Ϲ��Ͽͻ���
				oldCuPhone.add(cr.getCustomerPhone());
			} else { // �Ϲ��¿ͻ���
				newCuPhone.add(cr.getCustomerPhone());
			}

		}
		outSideRecordCuCount = cuPhone.size();
		outSideNewCuRecordCount = newCuPhone.size();
		outSideOldCuRecordCount = oldCuPhone.size();

		Set<String> sCuPhone = new HashSet<>();// ǩԼ�ͻ��绰
		List<String> sOldCuPhone = new ArrayList<>();// �Ͽͻ�ǩԼ�绰
		List<String> sNewCuPhone = new ArrayList<>();// �¿ͻ�ǩԼ�绰
		// ǩԼ�ͻ�
		for (Integer recordNo : signedSet) {
			ContractRecords cr = selectDao.findContractRecordsByRecordNo(projectId, recordNo);
			List<VisitRecords> vrrList = new ArrayList<>();
			if (!StringUtils.isEmpty(cr.getCustomerPhone())) {
				vrrList = selectDao.selectVisitBeforeTimeAndByProjectIdPhone(getThisTimeLastTime(od), projectId,
						cr.getCustomerPhone());
			}

			sCuPhone.add(cr.getCustomerPhone());
			if (vrrList.size() >= 2) {// ǩԼ�Ͽͻ���
				sOldCuPhone.add(cr.getCustomerPhone());
			} else { // ǩԼ�¿ͻ���
				sNewCuPhone.add(cr.getCustomerPhone());
			}
		}
		outSideSignCuCount = sCuPhone.size();
		outSideNewCuSignCount = sNewCuPhone.size();
		outSideOldSignCount = sOldCuPhone.size();
		/**
		 * �����ɽ���1.��ҵ���ʷ�����Ϲ� 2.�ͻ��������ڵ��н鱸�� �����ɽ���1.�н鷢����Ϲ� 2.�н鱸��
		 * �ڳ��ɽ���1.��ҵ���ʷ�����Ϲ� 2.�н��ޱ���
		 */
		// ���ҿͻ�������
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
		// ����ǩԼ��
		for (String s : ssList) {// ְҵ���ʷ�����Ϲ�
			List<GuideRecords> gList = selectDao.selectGuideRecordsByProjectAndTime(projectId, null,
					dateMap.get("pastAnydaysStartDay"), dateMap.get("currentDateEndDay"), s);
			if (gList.size() > 0) {// �ڿͻ����������б�����¼��˵���Ǵ����ɽ�
				guideCustomerVisitCount++;
			} else {// ��ҵ���ʷ�����Ϲ����н��ޱ���
				intFiledToDealNum++;
			}
		}
		// �����Ϲ���
		for (String ss : rrList) {
			List<GuideRecords> gList = selectDao.selectGuideRecordsByProjectAndTime(projectId, null,
					dateMap.get("pastAnydaysStartDay"), dateMap.get("currentDateEndDay"), ss);
			if (gList.size() > 0) {// �ڿͻ����������б�����¼��˵���Ǵ����ɽ�
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
