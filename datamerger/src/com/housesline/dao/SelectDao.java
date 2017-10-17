package com.housesline.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.housesline.bean.ContractRecords;
import com.housesline.bean.ContractRecordsFlowRecord;
import com.housesline.bean.EnterBuy;
import com.housesline.bean.GuideRecords;
import com.housesline.bean.House;
import com.housesline.bean.HouseType;
import com.housesline.bean.Project;
import com.housesline.bean.ProjectBenefits;
import com.housesline.bean.ProjectCustomers;
import com.housesline.bean.ProjectGuide;
import com.housesline.bean.ShopCustomers;
import com.housesline.bean.Shops;
import com.housesline.bean.SignRecords;
import com.housesline.bean.User;
import com.housesline.bean.UserRole;
import com.housesline.bean.VisitRecords;
import com.housesline.utils.DateUtil;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

/**
 * ��Ҫ������ѯ����Ŀ�е�����
 * 
 * @author cdh 2017-07-31
 *
 */
@Repository
public class SelectDao {

	/**
	 * ����projectId���������Ľ�ɫ
	 * 
	 * @param projectId
	 * @param roleId
	 *            ��ɫ����
	 * @return
	 */
	public List<User> selectUserByProject(String projectId, Integer roleId) {

		List<User> list = new LinkedList<>();
		Connection conn = BaseDao.getDefaultDataBase();

		Statement stmt = null;

		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from (select * from t_users where parentId = '" + projectId
					+ "') u where u.userId in (select u_id from user_role where r_id = " + roleId + ")";
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);

			while (rs.next()) {
				User u = new User();
				u.setUserCaption(rs.getString("userCaption"));
				u.setUserId(rs.getString("userId"));
				u.setPhone(rs.getString("phone"));
				Integer flag = rs.getInt("userStatus");
				if (flag == 1) {
					u.setUserStatus(1);
				} else {
					u.setUserStatus(0);
				}
				list.add(u);
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * �����û�id���Ҷ�Ӧ�Ľ�ɫid
	 * 
	 * @param userId
	 * @return
	 */
	public Integer selectRoleByUser(String userId) {

		Connection conn = BaseDao.getDefaultDataBase();

		Statement stmt = null;
		Integer roleId = null;
		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select r_id from user_role where u_id = '" + userId + "' ";
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while (rs.next()) {
				roleId = rs.getInt("r_id");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return roleId;
	}

	/**
	 * ����projectId�͵���ʱ�����һ�������
	 * 
	 * @param projectId
	 * @param time
	 *            ��ʽ yyyy-MM-dd
	 * @return
	 */
	public List<VisitRecords> selectVisitRecordsByProjectId(String projectId, String time) {
		return selectVisitRecordsByProjectId(projectId, time, null, null);
	}

	/**
	 * ����projectId��ʱ��β���һ�������
	 * 
	 * @param projectId
	 * @param startTime
	 *            ��ʽ yyyy-MM-dd HH:mm:ss
	 * @param endTime
	 *            ��ʽ yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public List<VisitRecords> selectVisitRecordsByProjectId(String projectId, String startTime, String endTime) {
		return selectVisitRecordsByProjectId(projectId, null, startTime, endTime);
	}

	/**
	 * ���ҵ���Ľӷ�����
	 * 
	 * @param projectId
	 * @param time
	 *            ��ʽ yyyy-MM-dd
	 * @param startTime
	 *            ��ʽ yyyy-MM-dd HH:mm:ss
	 * @param endTime
	 *            ��ʽ yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public List<VisitRecords> selectVisitRecordsByProjectId(String projectId, String time, String startTime,
			String endTime) {

		List<VisitRecords> list = new LinkedList<>();
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;

		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_visitrecords where projectId = '" + projectId + "' ";
			if (time != null) {
				sql += " and arriveTime like '%" + time + "%'";
			} else {
				if (startTime != null && !"".equals(startTime)) {
					sql += " and arriveTime >= '" + startTime + "' ";
				}
				if (endTime != null && !"".equals(endTime)) {
					sql += " and arriveTime <= '" + endTime + "' ";
				}
			}
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while (rs.next()) {
				VisitRecords vr = new VisitRecords();
				vr.setVisitNo(rs.getInt("visitNo"));
				vr.setUserId(rs.getString("userId"));
				vr.setProjectId(rs.getString("projectId"));
				vr.setVisitStatus(rs.getInt("visitStatus"));
				vr.setCustomerCount(rs.getInt("customerCount"));
				vr.setCustomerName(rs.getString("customerName"));
				vr.setPhone(rs.getString("phone"));
				vr.setRecordNo(rs.getInt("recordNo"));
				vr.setArriveTime(rs.getString("arriveTime"));
				vr.setReceptTime(rs.getString("receptTime"));
				vr.setLeaveTime(rs.getString("leaveTime"));
				vr.setAppointUserId(rs.getString("appointUserId"));
				vr.setCustomerId(rs.getString("customerId"));
				vr.setWriteState(rs.getInt("writeState"));
				vr.setIsNew(rs.getBoolean("isNew"));
				list.add(vr);
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * ͨ��phone��ʱ��ȥ���Ҵ�����¼
	 * 
	 * @param projectId
	 * @param time
	 * @param phone
	 * @return
	 */
	public List<GuideRecords> selectGuideRecordsByProjectAndTimeAndPhone(String projectId, String time, String phone) {
		return selectGuideRecordsByProjectAndTime(projectId, time, null, null, phone);
	}

	/**
	 * ����projectId��һ���ʱ�����һ���ڵĴ�����¼
	 * 
	 * @param projectId
	 * @param time
	 *            ��ʽ yyyy-MM-dd
	 * @return
	 */
	public List<GuideRecords> selectGuideRecordsByProjectAndTime(String projectId, String time) {
		return selectGuideRecordsByProjectAndTime(projectId, time, null, null, null);
	}

	/**
	 * ����projectId��һ��ʱ��β���һ��ʱ����������еĴ�����¼
	 * 
	 * @param projectId
	 * @param startTime
	 *            ��ʽ yyyy-MM-dd HH:mm:ss
	 * @param endTime
	 * @return
	 */
	public List<GuideRecords> selectGuideRecordsByProjectAndTime(String projectId, String startTime, String endTime) {
		return selectGuideRecordsByProjectAndTime(projectId, null, startTime, endTime, null);
	}

	/**
	 * ������Ҫ�鼯��һ��Ĵ�����¼����
	 * 
	 * @param projectId
	 * @param time
	 *            ��ʽ yyyy-MM-dd
	 * @param startTime
	 *            ��ʽ yyyy-MM-dd HH:mm:ss
	 * @param endTime
	 *            ��ʽ yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public List<GuideRecords> selectGuideRecordsByProjectAndTime(String projectId, String time, String startTime,
			String endTime, String phone) {

		List<GuideRecords> list = new LinkedList<>();
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;

		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_guiderecords where projectId = '" + projectId + "' ";
			if (time != null && !"".equals(time)) {
				sql += " and applyTime like '%" + time + "' ";
			} else {
				if (startTime != null && !"".equals(startTime)) {
					sql += " and applyTime >= '" + startTime + "' ";
				}
				if (endTime != null && !"".equals(endTime)) {
					sql += " and applyTime <= '" + endTime + "' ";
				}
			}
			if (phone != null && !"".equals(phone)) {
				sql += " and customerPhone = '" + phone + "' ";
			}
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);

			while (rs.next()) {

				GuideRecords gr = new GuideRecords();
				gr.setRecordNo(rs.getInt("recordNo"));
				gr.setProjectCustomerId(rs.getString("projectCustomerId"));
				gr.setShopCustomerId(rs.getString("shopCustomerId"));
				gr.setCustomerName(rs.getString("customerName"));
				gr.setCustomerPhone(rs.getString("customerPhone"));
				gr.setUserId(rs.getString("userId"));
				gr.setProjectId(rs.getString("projectId"));
				gr.setApplyStatus(rs.getInt("applyStatus"));
				gr.setApplyTime(rs.getString("applyTime"));
				gr.setVisitNo(rs.getInt("visitNo"));
				gr.setIsDeal(rs.getInt("isDeal"));
				gr.setFail(rs.getString("fail"));
				gr.setRules(rs.getString("rules"));
				list.add(gr);
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * ��ѯ����һ������еķ�Դ��Ϣ
	 * 
	 * @param projectId
	 * @return
	 */
	public List<House> selectHouseByProjectAndTime(String projectId) {

		List<House> list = new LinkedList<>();
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;
		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_projecthouses where projectId = '" + projectId + "' ";

			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while (rs.next()) {
				House h = new House();
				h.setHouseId(rs.getString("houseId"));
				h.setProjectId(rs.getString("projectId"));
				h.setHouseNo(rs.getString("houseNo"));
				h.setDistrict(rs.getString("district"));
				h.setBuildingNo(rs.getString("buildingNo"));
				h.setHouseKind(rs.getInt("houseKind"));
				h.setUnit(rs.getString("unit"));
				h.setFloor(rs.getInt("floor"));
				h.setDirect(rs.getString("direct"));
				h.setBuildArea(rs.getDouble("buildArea"));
				h.setUsefulArea(rs.getDouble("usefulArea"));
				h.setListPrice(rs.getDouble("listPrice"));
				h.setMinimumPrice(rs.getDouble("minimumPrice"));
				h.setShopPrice(rs.getDouble("shopPrice"));
				h.setHouseTypeId(rs.getString("houseTypeId"));
				h.setHouseStatus(rs.getInt("houseStatus"));
				h.setDecorationStandard(rs.getInt("decorationStandard"));
				h.setPhotos(rs.getString("photos"));
				h.setShelvTime(rs.getString("shelvTime"));
				h.setRewardMoney(rs.getDouble("rewardMoney"));
				h.setIsOpen(rs.getInt("isOpen"));
				h.setHouseNum(rs.getInt("houseNum"));
				h.setPresalePermissionInfo(rs.getString("presalePermissionInfo"));
				h.setBestBenefitsId(rs.getString("bestBenefitsId"));

				list.add(h);
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * ����projectId���ҷ�Դ����
	 * 
	 * @param projectId
	 * @return
	 */
	public List<HouseType> selectHouseTypeByProject(String projectId) {

		List<HouseType> list = new LinkedList<>();
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;

		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_projecthousetypes where projectId = '" + projectId + "' ";
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while (rs.next()) {
				HouseType ht = new HouseType();
				ht.setHouseTypeId(rs.getString("houseTypeId"));
				ht.setProjectId(rs.getString("projectId"));
				ht.setPhotoURL(rs.getString("photoURL"));
				ht.setCaption(rs.getString("caption"));
				ht.setArea(rs.getDouble("area"));
				ht.setHousType(rs.getString("housType"));
				ht.setHousTypeDesc(rs.getString("housTypeDesc"));
				ht.setIsUse(rs.getString("isUse"));
				list.add(ht);
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	/**
	 * �������е���Ŀ����ĳһ����Ŀ
	 * 
	 * @param projectId
	 * @return
	 */
	public List<Project> selectAllProject(String projectId) {

		List<Project> list = new LinkedList<>();
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;

		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_projects where 1=1 ";
			if (projectId != null && !"".equals(projectId)) {
				sql += " and projectId = '" + projectId + "'";
			}
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while (rs.next()) {
				Project p = new Project();
				p.setProjectId(rs.getString("projectId"));
				p.setProjectName(rs.getString("projectName"));
				p.setCity(rs.getString("city"));
				p.setLandArea(rs.getDouble("landArea"));
				p.setBuildArea(rs.getDouble("buildArea"));
				p.setGroundArea(rs.getDouble("groundArea"));
				p.setUnderGroundArea(rs.getDouble("underGroundArea"));
				p.setUnitCount(rs.getInt("unitCount"));
				p.setVolumeRatio(rs.getDouble("volumeRatio"));
				p.setDensity(rs.getDouble("density"));
				p.setAfforestationRatio(rs.getDouble("afforestationRatio"));
				p.setPropertyType(rs.getString("propertyType"));
				p.setSaleAddress(rs.getString("saleAddress"));
				p.setPropertyAddress(rs.getString("propertyAddress"));
				p.setDistrict(rs.getString("district"));
				p.setAveragePrice(rs.getDouble("averagePrice"));
				p.setDeveloper(rs.getString("developer"));
				p.setInvestor(rs.getString("investor"));
				p.setManager(rs.getString("manager"));
				p.setPropertyCost(rs.getDouble("propertyCost"));
				p.setDecorationStandard(rs.getInt("decorationStandard"));
				p.setDeliverStandard(rs.getString("deliverStandard"));
				p.setStartTime(rs.getString("startTime"));
				p.setDeliverTime(rs.getString("deliverTime"));
				p.setRightsYears(rs.getInt("rightsYears"));
				p.setPresalePermissionURL(rs.getString("presalePermissionURL"));
				p.setTags(rs.getString("tags"));
				p.setSaleAddressGis(rs.getString("saleAddressGis"));
				p.setPropertyAddressGis(rs.getString("propertyAddressGis"));
				p.setEffectPic(rs.getString("effectPic"));
				p.setProInSystemStutas(rs.getInt("proInSystemStutas"));
				p.setSaleLongitude(rs.getString("saleLongitude"));
				p.setSaleLatitude(rs.getString("saleLatitude"));
				p.setPropertyLongitude(rs.getString("propertyLongitude"));
				p.setPropertyLatitude(rs.getString("propertyLatitude"));

				list.add(p);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	/**
	 * ����projectId�����Ż�����
	 * 
	 * @param projectId
	 * @return
	 */
	public List<ProjectBenefits> selectProjectBenefitsByProjectId(String projectId) {
		List<ProjectBenefits> list = new LinkedList<>();
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;

		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_platformdefinition where projectId = '" + projectId + "' ";
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while (rs.next()) {
				ProjectBenefits pb = new ProjectBenefits();
				pb.setBenefitId(rs.getString("benefitId"));
				pb.setProjectId(rs.getString("projectId"));
				pb.setCaption(rs.getString("caption"));
				pb.setStartTime(rs.getString("startTime"));
				pb.setEndTime(rs.getString("endTime"));
				pb.setFee(rs.getDouble("fee"));
				pb.setBenefitMoney(rs.getDouble("benefitMoney"));
				pb.setBenefitPercent(rs.getDouble("benefitPercent"));
				pb.setType(rs.getInt("type"));
				pb.setBenefitsName(rs.getString("benefitsName"));
				pb.setIsChooseMore(rs.getInt("isChooseMore"));
				list.add(pb);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	/**
	 * �ҳ���Ŀ�µ����еĿͻ�
	 * 
	 * @param projectId
	 * @return
	 */
	public List<ProjectCustomers> selectProjectCustomerByProjectId(String projectId) {
		return selectProjectCustomersByProjectIdAndTime(projectId, null, null, null, null);
	}

	/**
	 * ͨ��projectid��phone���ҿͻ�
	 * 
	 * @param projectId
	 * @param time
	 *            ��ʽ yyyy-MM-dd
	 * @param phone
	 * @return
	 */
	public List<ProjectCustomers> selectProjectCustomersByProjectIdAndTime(String projectId, String time,
			String phone) {
		return selectProjectCustomersByProjectIdAndTime(projectId, time, null, null, phone);
	}

	/**
	 * ͨ��projectId��ʱ����Ұ����ͻ�
	 * 
	 * @param projectId
	 * @param time
	 *            ��ʽ yyyy-MM-dd
	 * @param startTime
	 *            ��ʽ yyyy-MM-dd HH:mm:ss ��Ϊ��
	 * @param endTime
	 *            ��ʽ yyyy-MM-dd HH:mm:ss ��Ϊ��
	 * @param phone
	 *            ��Ϊ��
	 * @return
	 */
	public List<ProjectCustomers> selectProjectCustomersByProjectIdAndTime(String projectId, String time,
			String startTime, String endTime, String phone) {
		List<ProjectCustomers> list = new LinkedList<>();
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;
		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_projectcustomers where projectId = '" + projectId
					+ "' and projectCustomerPhone is not null ";
			if (time != null && !"".equals(time)) {
				sql += " and createTime like '%" + time + "' ";
			} else {
				if (startTime != null && !"".equals(startTime)) {
					sql += " and createTime >= '" + startTime + "' ";
				}
				if (endTime != null && !"".equals(endTime)) {
					sql += " and createTime <= '" + endTime + "' ";
				}
			}
			if (phone != null && !"".equals(phone)) {
				sql += " and projectCustomerPhone = '" + phone + "' ";
			}
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while (rs.next()) {
				ProjectCustomers pc = new ProjectCustomers();
				pc.setProjectId(rs.getString("projectId"));
				pc.setProjectCustomerId(rs.getString("projectCustomerId"));
				pc.setProjectCustomerName(rs.getString("projectCustomerName"));
				pc.setProjectCustomerPhone(rs.getString("projectCustomerPhone"));
				pc.setIdCard(rs.getString("idCard"));
				pc.setCreateUserId(rs.getString("createUserId"));
				pc.setCreateTime(rs.getString("createTime"));
				pc.setSex(rs.getInt("sex"));
				pc.setOwnerUserId(rs.getString("ownerUserId"));
				pc.setOwnerStartTime(rs.getString("ownerStartTime"));
				pc.setLastTime(rs.getString("lastTime"));
				pc.setEvaluate(rs.getString("evaluate"));

				list.add(pc);
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public ProjectGuide selectProjectGuideByProjectId(String projectId) {
		ProjectGuide pg = new ProjectGuide();
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;

		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_projectguide where projectId = '" + projectId + "' ";
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while (rs.next()) {
				pg.setProjectId(rs.getString("projectId"));
				pg.setIsAvailable(rs.getInt("isAvailable"));
				pg.setValidDays(rs.getInt("validDays"));
				pg.setPayType(rs.getInt("payType"));
				pg.setRewardMoney(rs.getDouble("rewardMoney"));
				pg.setRewardpercent(rs.getDouble("rewardpercent"));
				pg.setDescription(rs.getString("description"));
				pg.setIsDaiKan(rs.getInt("isDaiKan"));
				pg.setIsYiDi(rs.getInt("isYiDi"));
				pg.setYiDiSalesCommission(rs.getDouble("yiDiSalesCommission"));
				pg.setYiDiValidity(rs.getString("yiDiValidity"));
				pg.setIsFast(rs.getInt("isFast"));
				pg.setDaiKanMoney(rs.getDouble("daiKanMoney"));
				pg.setFenXiaoMoney(rs.getDouble("fenXiaoMoney"));
				pg.setCustormerProtectDays(rs.getString("custormerProtectDays"));
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pg;
	}

	/**
	 * ͨ��shopId�����ŵ�ͻ�
	 * 
	 * @param shopId
	 * @return
	 */
	public List<ShopCustomers> selectShopCustomersByShopId(String shopId) {
		List<ShopCustomers> list = new LinkedList<>();
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;
		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_shopcustomers where shopId = '" + shopId + "' ";
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while (rs.next()) {
				ShopCustomers sc = new ShopCustomers();
				sc.setShopCustomerId(rs.getString("shopCustomerId"));
				sc.setShopCustomerName(rs.getString("shopCustomerName"));
				sc.setShopCustomerPhone(rs.getString("shopCustomerPhone"));
				sc.setUserId(rs.getString("userId"));
				sc.setCreateTime(rs.getString("createTime"));
				sc.setSex(rs.getInt("sex"));
				sc.setShopId(rs.getInt("shopId"));
				sc.setIdCard(rs.getString("idCard"));
				list.add(sc);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	/**
	 * �������е��ŵ�
	 * 
	 * @return
	 */
	public List<Shops> selectAllShops() {
		List<Shops> list = new LinkedList<>();
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;
		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_shops";
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while (rs.next()) {
				Shops shop = new Shops();
				shop.setShopId(rs.getInt("shopId"));
				shop.setShopStatus(rs.getInt("shopStatus"));
				shop.setApplyTime(rs.getString("applyTime"));
				shop.setApproveTime(rs.getString("approveTime"));
				shop.setApproveUserId(rs.getString("approveUserId"));
				shop.setShopName(rs.getString("shopName"));
				shop.setCompanyName(rs.getString("companyName"));
				shop.setAddress(rs.getString("address"));
				shop.setCity(rs.getString("city"));
				shop.setLocation(rs.getString("location"));
				shop.setContactPerson(rs.getString("contactPerson"));
				shop.setPhone(rs.getString("phone"));
				shop.setEmail(rs.getString("email"));
				shop.setInterstAreas(rs.getString("interstAreas"));
				shop.setInterstProjects(rs.getString("interstProjects"));
				shop.setAddress(rs.getString("lastLoginTime"));
				shop.setPhoto(rs.getString("photo"));
				shop.setBank_cards(rs.getString("bank_cards"));
				shop.setBankName(rs.getString("bankName"));
				shop.setRepresentative(rs.getString("representative"));
				shop.setLicensePhoto(rs.getString("licensePhoto"));
				shop.setLicenseNo(rs.getString("licenseNo"));
				shop.setAuditOpinion(rs.getString("auditOpinion"));
				shop.setLngLat(rs.getString("lngLat"));
				shop.setInSystemStutas(rs.getInt("inSystemStutas"));
				shop.setLongitude(rs.getString("longitude"));
				shop.setLatitude(rs.getString("latitude"));
				list.add(shop);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * ����projectId��ʱ�����ǩ��ǩ�˵�����
	 * 
	 * @param projectId
	 * @param time
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<SignRecords> selectSignRecordsByProjectIdAndTime(String projectId, String time, String startTime,
			String endTime) {

		List<SignRecords> list = new LinkedList<>();
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;

		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_signrecords where parentId = '" + projectId + "' ";
			if (time != null && !"".equals(time)) {
				sql += " and signInTime like '%" + time + "%' ";
			} else {
				if (startTime != null && !"".equals(startTime)) {
					sql += " and signInTime >= '" + startTime + "' ";
				}
				if (endTime != null && !"".equals(endTime)) {
					sql += " and signInTime <= '" + endTime + "' ";
				}
			}
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while (rs.next()) {
				SignRecords sr = new SignRecords();
				sr.setRecordId(rs.getInt("recordId"));
				sr.setUserId(rs.getString("userId"));
				sr.setParentId(rs.getString("parentId"));
				sr.setSignInTime(rs.getString("signInTime"));
				sr.setSignOutTime(rs.getString("signOutTime"));
				list.add(sr);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	/**
	 * ��ȡyyyy-MM-dd��ʽ��yyyy-MM-dd 00:00:00ʱ��
	 * 
	 * @param time
	 * @return
	 */
	private String getThisTimeStartTime(String time) {

		Date date = DateUtil.parse(time, DateUtil.PATTERN_CLASSICAL_SIMPLE);
		String start1 = DateUtil.format(date);
		Date s = DateUtil.parse(start1, DateUtil.PATTERN_CLASSICAL);
		Date d = DateUtil.getIntegralStartTime(s);

		return DateUtil.format(d);
	}

	/**
	 * ��ȡyyyy-MM-dd��ʽ��yyyy-MM-dd 23:59:59ʱ��
	 * 
	 * @param time
	 * @return
	 */
	private String getThisTimeLastTime(String time) {

		Date date = DateUtil.parse(time, DateUtil.PATTERN_CLASSICAL_SIMPLE);
		String start1 = DateUtil.format(date);
		Date s = DateUtil.parse(start1, DateUtil.PATTERN_CLASSICAL);
		Date d = DateUtil.getIntegralEndTime(s);

		return DateUtil.format(d);
	}

	public List<VisitRecords> selectVisitRecordsByProjectAndTimeForAnalysis(String projectId, String oneDay) {
		return selectVisitRecordsByProjectAndTimeForAnalysis(null, null, projectId, oneDay, null, null);
	}

	/**
	 * ����һ���ʱ��͵绰���ҵ�������
	 * 
	 * @param projectId
	 * @param time
	 * @param phone
	 * @return
	 */
	public List<VisitRecords> selectVisitByProjectIdAndPhoneTime(String projectId, String time, String phone) {
		return selectVisitRecordsByProjectAndTimeForAnalysis(null, null, projectId, time, null, phone);
	}

	/**
	 * Ϊ"����ͼ��"���ҵ�������
	 * 
	 * @param startTime
	 *            ��ʽ yyyy-MM-dd
	 * @param endTime
	 *            ��ʽ yyyy-MM-dd
	 * @param projectId
	 * @param oneDay
	 *            ��ʽ yyyy-MM-dd
	 * @param userId
	 *            ��ҵ����Id
	 * @param phone
	 */
	public List<VisitRecords> selectVisitRecordsByProjectAndTimeForAnalysis(String startTime, String endTime,
			String projectId, String oneDay, String userId, String phone) {

		List<VisitRecords> list = new LinkedList<>();
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;

		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_visitrecords where projectId = '" + projectId + "' ";
			if (userId != null && !"".equals(userId)) {
				sql += " and userId = '" + userId + "' ";
			}
			if (oneDay != null && !"".equals(oneDay)) {
				sql += " and arriveTime like '%" + oneDay + "%' ";
			} else {
				if (startTime != null && !"".equals(startTime)) {
					sql += " and arriveTime >= '" + startTime + "' ";
				}
				if (endTime != null && !"".equals(endTime)) {
					sql += "and arriveTime <= '" + endTime + "' ";
				}
			}
			if (phone != null && !"".equals(phone)) {
				sql += " and phone = '" + phone + "' ";
			}
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while (rs.next()) {
				VisitRecords vrs = new VisitRecords();
				vrs.setVisitNo(rs.getInt("visitNo"));
				vrs.setUserId(rs.getString("userId"));
				vrs.setProjectId(rs.getString("projectId"));
				vrs.setVisitStatus(rs.getInt("visitStatus"));
				vrs.setCustomerCount(rs.getInt("customerCount"));
				vrs.setCustomerName(rs.getString("customerName"));
				vrs.setPhone(rs.getString("phone"));
				vrs.setRecordNo(rs.getInt("recordNo"));
				vrs.setArriveTime(rs.getString("arriveTime"));
				vrs.setReceptTime(rs.getString("receptTime"));
				vrs.setLeaveTime(rs.getString("leaveTime"));
				vrs.setAppointUserId(rs.getString("appointUserId"));
				vrs.setCustomerId(rs.getString("customerId"));
				vrs.setWriteState(rs.getInt("writeState"));
				vrs.setIsNew(rs.getBoolean("isNew"));
				list.add(vrs);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * ���ҵ�ǰ���ü�¼֮ǰ�ļ�¼
	 * 
	 * @param visitTime
	 * @param projectId
	 * @return
	 */
	public List<VisitRecords> selectVisitBeforeTimeAndByProjectIdPhone(String visitTime, String projectId,
			String phone) {
		return selectVisitBeforeTimeAndByProjectIdPhone(visitTime, null, projectId, phone);
	}

	/**
	 * ����ĳ��ʱ���֮ǰ�ĵ��ü�¼
	 * 
	 * @param visitTime
	 *            yyyy-MM-dd HH:mm:ss
	 * @param time
	 * @param projectId
	 * @param phone
	 * @return
	 */
	public List<VisitRecords> selectVisitBeforeTimeAndByProjectIdPhone(String visitTime, String time, String projectId,
			String phone) {
		List<VisitRecords> list = new LinkedList<>();
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;
		phone = phone.trim();
		try {
			
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_visitrecords where projectId = '" + projectId + "' ";
			if (phone != null && !"".equals(phone)) {
				sql += " and phone = '" + phone + "' ";
			}
			if (time != null && !"".equals(time)) {
				sql += " and arriveTime <= '" + getThisTimeStartTime(time) + "' ";
			} else {
				if (visitTime != null && !"".equals(visitTime)) {
					sql += " and arriveTime < '" + visitTime + "' ";
				}
			}
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while (rs.next()) {
				VisitRecords vrs = new VisitRecords();
				vrs.setVisitNo(rs.getInt("visitNo"));
				vrs.setUserId(rs.getString("userId"));
				vrs.setProjectId(rs.getString("projectId"));
				vrs.setVisitStatus(rs.getInt("visitStatus"));
				vrs.setCustomerCount(rs.getInt("customerCount"));
				vrs.setCustomerName(rs.getString("customerName"));
				vrs.setPhone(rs.getString("phone"));
				vrs.setRecordNo(rs.getInt("recordNo"));
				vrs.setArriveTime(rs.getString("arriveTime"));
				vrs.setReceptTime(rs.getString("receptTime"));
				vrs.setLeaveTime(rs.getString("leaveTime"));
				vrs.setAppointUserId(rs.getString("appointUserId"));
				vrs.setCustomerId(rs.getString("customerId"));
				vrs.setWriteState(rs.getInt("writeState"));
				vrs.setIsNew(rs.getBoolean("isNew"));
				list.add(vrs);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * ͨ��projectId��ʱ����Ұ����ͻ�
	 * 
	 * @param time
	 * @param projectId
	 * @return
	 */
	public List<ProjectCustomers> selectProjectCustomerByLastTimeAndProjectId(String time, String projectId) {
		List<ProjectCustomers> list = new LinkedList<>();
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;

		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_projectcustomers where projectId = '" + projectId + "' " + "and lastTime <= '"
					+ getThisTimeLastTime(time) + "' ";
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while (rs.next()) {
				ProjectCustomers p = new ProjectCustomers();
				p.setProjectId(rs.getString("projectId"));
				p.setProjectCustomerId(rs.getString("projectCustomerId"));
				p.setProjectCustomerName(rs.getString("projectCustomerName"));
				p.setProjectCustomerPhone(rs.getString("projectCustomerPhone"));
				p.setIdCard(rs.getString("idCard"));
				p.setCreateUserId(rs.getString("createUserId"));
				p.setCreateTime(rs.getString("createTime"));
				p.setSex(rs.getInt("sex"));
				p.setOwnerUserId(rs.getString("ownerUserId"));
				p.setOwnerStartTime(rs.getString("ownerStartTime"));
				p.setLastTime(rs.getString("lastTime"));
				p.setEvaluate(rs.getString("evaluate"));
				list.add(p);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * ��λһ�������ͻ�
	 * 
	 * @param projectId
	 * @param phone
	 * @return
	 */
	public ProjectCustomers selectProjectCustomersByProjectIdAndPhone(String projectId, String phone) {
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;
		ProjectCustomers p = new ProjectCustomers();

		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_projectcustomers where projectId = '" + projectId + "' "
					+ " and projectCustomerPhone = '" + phone + "' ";

			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while (rs.next()) {
				p.setProjectId(rs.getString("projectId"));
				p.setProjectCustomerId(rs.getString("projectCustomerId"));
				p.setProjectCustomerName(rs.getString("projectCustomerName"));
				p.setProjectCustomerPhone(rs.getString("projectCustomerPhone"));
				p.setIdCard(rs.getString("idCard"));
				p.setCreateUserId(rs.getString("createUserId"));
				p.setCreateTime(rs.getString("createTime"));
				p.setSex(rs.getInt("sex"));
				p.setOwnerUserId(rs.getString("ownerUserId"));
				p.setOwnerStartTime(rs.getString("ownerStartTime"));
				p.setLastTime(rs.getString("lastTime"));
				p.setEvaluate(rs.getString("evaluate"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return p;
	}

	/**
	 * ����ʱ���projectId���Ҷ���
	 * 
	 * @param projectId
	 * @param time
	 * @return
	 */
	public List<ContractRecords> selectContractRecordsByProjectIdAndTimePhone(String projectId, String time) {
		return selectContractRecordsByProjectIdAndTimePhone(projectId, time, null);
	}

	/**
	 * ͨ���������Ҷ���
	 * 
	 * @param projectId
	 * @param time
	 * @param phone
	 * @return
	 */
	public List<ContractRecords> selectContractRecordsByProjectIdAndTimePhone(String projectId, String time,
			String phone) {
		List<ContractRecords> list = new LinkedList<>();
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;

		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_contractrecords where projectId = '" + projectId + "' ";
			if (phone != null && !"".equals(phone)) {
				sql += " and customerPhone = '" + phone + "' ";
			}
			if (time != null && !"".equals(time)) {
				sql += " and applyTime like '%" + time + "%' ";
			}
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while (rs.next()) {
				ContractRecords c = new ContractRecords();
				c.setRecordNo(rs.getInt("recordNo"));
				c.setUserId(rs.getString("userId"));
				c.setProjectId(rs.getString("projectId"));
				c.setHouseNum(rs.getInt("houseNum"));
				c.setCustomerName(rs.getString("customerName"));
				c.setCustomerIDCard(rs.getString("customerIDCard"));
				c.setCustomerPhone(rs.getString("customerPhone"));
				c.setBenefitlds(rs.getString("benefitlds"));
				c.setPrice(rs.getDouble("price"));
				c.setRecordStatus(rs.getInt("recordStatus"));
				c.setApplyTime(rs.getString("applyTime"));
				c.setAuditingTime(rs.getString("auditingTime"));
				c.setAuditionUserId(rs.getString("auditionUserId"));
				c.setAuditionReson(rs.getString("auditionReson"));
				c.setRemitNo(rs.getString("remitNo"));
				c.setRemitConfirmTime(rs.getString("remitConfirmTime"));
				c.setRemitConfirmUserId(rs.getString("remitConfirmUserId"));
				c.setContractConfirmTime(rs.getString("contractConfirmTime"));
				c.setContractconfirmUseerId(rs.getString("contractconfirmUseerId"));
				c.setIsShopPayConfirm(rs.getInt("isShopPayConfirm"));
				c.setShopPayConfirmTime(rs.getString("shopPayConfirmTime"));
				c.setShopPayConfirmUserId(rs.getString("shopPayConfirmUserId"));
				c.setShopPayConfirmDesc(rs.getString("shopPayConfirmDesc"));
				c.setCancelShopPayConfirmDesc(rs.getString("cancelShopPayConfirmDesc"));
				c.setIsSystemPayConfirm(rs.getInt("isSystemPayConfirm"));
				c.setSystemPayConfirmTime(rs.getString("systemPayConfirmTime"));
				c.setSystemPayConfirmUserId(rs.getString("systemPayConfirmUserId"));
				c.setSystemPayConfirmDesc(rs.getString("systemPayConfirmDesc"));
				c.setCancelSystemPayConfirmDesc(rs.getString("cancelSystemPayConfirmDesc"));
				c.setShopReceiveConfirmTime(rs.getString("shopReceiveConfirmTime"));
				c.setShopReceiveConfirmUserId(rs.getString("shopReceiveConfirmUserId"));
				c.setShopCancelReceiveConfirmDesc(rs.getString("shopCancelReceiveConfirmDesc"));
				c.setSystemReceiveConfirmTime(rs.getString("systemReceiveConfirmTime"));
				c.setSystemReceiveConfirmUserId(rs.getString("systemReceiveConfirmUserId"));
				c.setCredentialsType(rs.getInt("credentialsType"));
				c.setCredentialsNum(rs.getString("credentialsNum"));
				c.setCredentialsPhoto(rs.getString("credentialsPhoto"));
				c.setBuyPrice(rs.getDouble("buyPrice"));
				c.setVoucherUploadTime(rs.getString("voucherUploadTime"));
				c.setGetMoneyDesc(rs.getString("getMoneyDesc"));
				c.setProjectCustomerId(rs.getString("projectCustomerId"));
				c.setIsOut(rs.getString("isOut"));
				c.setPayStyle(rs.getInt("payStyle"));
				c.setOrderProperty(rs.getInt("orderProperty"));
				c.setIsAlreadyRead(rs.getInt("isAlreadyRead"));
				c.setEnterBuyRelation(rs.getInt("enterBuyRelation"));
				c.setAccountStyle(rs.getInt("accountStyle"));
				c.setKillTheOrderReason(rs.getString("killTheOrderReason"));
				c.setRevokeOrderNotes(rs.getString("revokeOrderNotes"));
				c.setRevokeTime(rs.getString("revokeTime"));
				c.setBenefitInfo(rs.getString("benefitInfo"));
				c.setOrderNum(rs.getString("orderNum"));
				c.setShopCustomerId(rs.getString("shopCustomerId"));
				c.setRealCustomerId(rs.getString("realCustomerId"));
				c.setHaveToPayMoney(rs.getString("haveToPayMoney"));
				c.setGuideId(rs.getString("guideId"));
				list.add(c);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public House selectHouseByHouseNum(String projectId, Integer houseNum) {
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;

		try {
			House h = new House();
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_projecthouses where projectId = '" + projectId + "' ";
			if (houseNum != null && !"".equals(houseNum)) {
				sql += " and houseNum = " + houseNum;
			}
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while (rs.next()) {
				h.setHouseId(rs.getString("houseId"));
				h.setProjectId(rs.getString("projectId"));
				h.setHouseNo(rs.getString("houseNo"));
				h.setDistrict(rs.getString("district"));
				h.setBuildingNo(rs.getString("buildingNo"));
				h.setHouseKind(rs.getInt("houseKind"));
				h.setUnit(rs.getString("unit"));
				h.setFloor(rs.getInt("floor"));
				h.setDirect(rs.getString("direct"));
				h.setBuildArea(rs.getDouble("buildArea"));
				h.setUsefulArea(rs.getDouble("usefulArea"));
				h.setListPrice(rs.getDouble("listPrice"));
				h.setMinimumPrice(rs.getDouble("minimumPrice"));
				h.setShopPrice(rs.getDouble("shopPrice"));
				h.setHouseTypeId(rs.getString("houseTypeId"));
				h.setHouseTypeName(rs.getString("houseTypeName"));
				h.setHouseStatus(rs.getInt("houseStatus"));
				h.setDecorationStandard(rs.getInt("decorationStandard"));
				h.setPhotos(rs.getString("photos"));
				h.setShelvTime(rs.getString("shelvTime"));
				h.setRewardMoney(rs.getDouble("rewardMoney"));
				h.setIsOpen(rs.getInt("isOpen"));
				h.setHouseNum(rs.getInt("houseNum"));
				h.setPresalePermissionInfo(rs.getString("presalePermissionInfo"));
				h.setBestBenefitsId(rs.getString("bestBenefitsId"));
			}

			rs.close();
			return h;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * 
	 * @param projectId
	 * @return
	 */
	public EnterBuy selectEnterBuyByProjectId(String projectId) {
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;
		try {
			EnterBuy e = new EnterBuy();
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_enterbuy where projectId = '" + projectId + "' ";
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while (rs.next()) {
				e.setEnterBuyId(rs.getString("enterBuyId"));
				e.setProjectId(rs.getString("projectId"));
				e.setIsSupportBuy(rs.getString("isSupportBuy"));
				e.setOutOfTime(rs.getString("outOfTime"));
				e.setDposit(rs.getDouble("dposit"));
				e.setEnterBuyRule(rs.getString("enterBuyRule"));
				e.setContractTerms(rs.getString("contractTerms"));
				e.setCountermand(rs.getString("countermand"));
			}
			rs.close();
			return e;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * ���ҵ��óɽ���
	 * 
	 * @param projectId
	 * @param time
	 * @return
	 */
	public Integer selectVisitAndDealCount(String projectId, String time) {
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;

		try {
			Integer count = 0;
			stmt = (Statement) conn.createStatement();
			String sql = "SELECT c.recordNo,c.userId,c.houseNum,c.customerName,c.customerPhone,v.num ,c.recordStatus, v.arriveTime "
					+ "FROM t_contractrecords c, (SELECT customerId,phone,COUNT(phone) num, arriveTime FROM t_visitrecords "
					+ "WHERE writeState = 1 GROUP BY phone)v WHERE c.customerPhone = v.phone and c.recordStatus = 5 and c.projectId = '"
					+ projectId + "'";// ���óɽ�
			sql += " and c.contractConfirmTime like '%" + time
					+ "%' GROUP BY (c.customerPhone)";
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while (rs.next()) {
				count++;
			}
			rs.close();
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public List<ContractRecordsFlowRecord> selectContractRecordsFlowRecordByTime(String time, String projectId) {
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;

		List<ContractRecordsFlowRecord> list = new LinkedList<>();

		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_contractrecordsFR where projectId = '" + projectId + "' ";
			if (time != null && !"".equals(time)) {
				sql += " and operateTime like '%" + time + "%' ";
			}

			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while (rs.next()) {
				ContractRecordsFlowRecord crf = new ContractRecordsFlowRecord();
				crf.setFr_crId(rs.getInt("fr_crId"));
				crf.setOperateSort(rs.getInt("operateSort"));
				crf.setOrderSort(rs.getInt("orderSort"));
				crf.setOperateTime(rs.getString("operateTime"));
				crf.setOperateUserId(rs.getString("operateUserId"));
				crf.setOperateUserName(rs.getString("operateUserName"));
				crf.setOperateUserPhone(rs.getString("operateUserPhone"));
				crf.setHistoryRecord(rs.getString("historyRecord"));
				crf.setNewRecord(rs.getString("newRecord"));
				crf.setRecordNo(rs.getInt("recordNo"));
				crf.setProjectId(rs.getString("projectId"));
				list.add(crf);
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;

	}
	
	/**
	 * ���ݶ����Ų��Ҷ�����ˮ
	 * @param recordNo
	 * @param time
	 * @return
	 */
	public List<ContractRecordsFlowRecord> selectContractRecordsFlowRecordByRecordNo(Integer recordNo, String time){
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;

		List<ContractRecordsFlowRecord> list = new LinkedList<>();

		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_contractrecordsFR where 1=1";
			if (time != null && !"".equals(time)) {
				sql += " and operateTime like '%" + time + "%' ";
			}
			if(recordNo != null && !"".equals(recordNo)){
				sql += " and recordNo = " + recordNo;
			}

			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while (rs.next()) {
				ContractRecordsFlowRecord crf = new ContractRecordsFlowRecord();
				crf.setFr_crId(rs.getInt("fr_crId"));
				crf.setOperateSort(rs.getInt("operateSort"));
				crf.setOrderSort(rs.getInt("orderSort"));
				crf.setOperateTime(rs.getString("operateTime"));
				crf.setOperateUserId(rs.getString("operateUserId"));
				crf.setOperateUserName(rs.getString("operateUserName"));
				crf.setOperateUserPhone(rs.getString("operateUserPhone"));
				crf.setHistoryRecord(rs.getString("historyRecord"));
				crf.setNewRecord(rs.getString("newRecord"));
				crf.setRecordNo(rs.getInt("recordNo"));
				crf.setProjectId(rs.getString("projectId"));
				list.add(crf);
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	/**
	 * ͳ��ʱ�������ڵ��������������������λ�ͷ�͵�����
	 * 
	 * @param userId
	 * @param projectId
	 * @param oneDay
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public Map<String, Set<String>> selectMeneryCustomerPhone(String userId, String projectId, String oneDay,
			String startTime, String endTime) {

		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;

		Map<String, Set<String>> map = new HashMap<>();

		try {
			stmt = (Statement) conn.createStatement();

			String cuEndSql = "select * from t_projectcustomers where projectId = '" + projectId
					+ "' and projectCustomerPhone is not null and createTime is not null";
			String cuStartSql = "select * from t_projectcustomers where projectId = '" + projectId
					+ "' and projectCustomerPhone is not null and createTime is not null";
			String visitEndSql = "select * from t_visitrecords where projectId = '" + projectId
					+ "' and phone is not null";
			String visitStartSql = visitEndSql;
			String totalSql = cuEndSql;
			if (userId != null && !"".equals(userId)) {
				cuEndSql += " and createUserId = '" + userId + "' ";
				cuStartSql += " and createUserId = '" + userId + "' ";
				visitEndSql += " and userId = '" + userId + "' ";
				visitStartSql += " and userId = '" + userId + "' ";
				totalSql += " and createUserId = '" + userId + "' ";
			}
			if (oneDay != null && !"".equals(oneDay)) {
				cuEndSql += " and createTime <= '" + getThisTimeLastTime(oneDay) + "' ";
				cuStartSql += " and createTime <= '" + getThisTimeStartTime(oneDay) + "' ";
				visitEndSql += " and arriveTime <= '" + getThisTimeLastTime(oneDay) + "' ";
				visitStartSql += " and arriveTime <= '" + getThisTimeStartTime(oneDay) + "' ";
				totalSql += " and createTime like '%" + oneDay + "%'";
			} /*
				 * else { if(startTime != null && !"".equals(startTime)){
				 * cuStartSql += " and createTime <= '" +
				 * getThisTimeStartTime(startTime) + "' "; visitStartSql +=
				 * " and arriveTime <= '" + getThisTimeStartTime(startTime) +
				 * "' "; totalSql += " and createTime >= '" +
				 * getThisTimeStartTime(startTime) + "' "; } if(endTime != null
				 * && !"".equals(endTime)){ cuEndSql += " and createTime <= '" +
				 * getThisTimeLastTime(endTime) + "' "; visitEndSql +=
				 * " and arriveTime <= '" + getThisTimeLastTime(endTime) + "' ";
				 * totalSql += " and createTime <= '" +
				 * getThisTimeLastTime(endTime) + "' "; } }
				 */

			Integer cu1 = 0;
			Integer cu2 = 0;
			Integer vr1 = 0;
			Integer vr2 = 0;

			// �¿ͻ�
			Set<String> newSet = new HashSet<>();
			Set<String> set12 = new HashSet<>();
			Set<String> set13 = new HashSet<>();
			// �������λ�ͷ��
			Set<String> TwoSet = new HashSet<>();
			// �ܴ���
			Set<String> totalSet = new HashSet<>();

			Set<String> set11 = new HashSet<>();
			Set<String> set22 = new HashSet<>();
			List<String> list11 = new ArrayList<>();
			List<String> list22 = new ArrayList<>();

			ResultSet rs1 = (ResultSet) stmt.executeQuery(cuStartSql);
			while (rs1.next()) {
				set11.add(rs1.getString("projectCustomerPhone"));
			}
			rs1.close();
			ResultSet rs2 = (ResultSet) stmt.executeQuery(cuEndSql);
			while (rs2.next()) {
				set22.add(rs2.getString("projectCustomerPhone"));
			}
			rs2.close();
			ResultSet rs3 = (ResultSet) stmt.executeQuery(visitStartSql);
			while (rs3.next()) {
				list11.add(rs3.getString("phone"));
			}
			rs3.close();
			ResultSet rs4 = (ResultSet) stmt.executeQuery(visitEndSql);
			while (rs4.next()) {
				list22.add(rs4.getString("phone"));
			}
			rs4.close();
			ResultSet rs5 = (ResultSet) stmt.executeQuery(totalSql);
			while (rs5.next()) {
				totalSet.add(rs5.getString("projectCustomerPhone"));
			}
			rs5.close();

			for (String s : set22) {
				if (!set11.contains(s)) {
					newSet.add(s);
				}
			}

			for (String s1 : set11) {
				Integer count = 0;
				for (String s2 : list11) {
					if (s1.equals(s2)) {
						count++;
					}
				}
				if (count == 2) {
					set12.add(s1);
				}
			}
			for (String s3 : set22) {
				Integer count = 0;
				for (String s4 : list22) {
					if (s3.equals(s4)) {
						count++;
					}
				}
				if (count == 2) {
					set13.add(s3);
				}
			}

			for (String s : set13) {
				if (!set12.contains(s)) {
					TwoSet.add(s);
				}
			}

			// ƽ̨������
			Set<String> pSet = new HashSet<>();
			for (String s : totalSet) {
				int count = 0;
				// �е��ü�¼�ͱ�����¼��ƽ̨����,״̬��Ϊ2��ɾ�� 3�����
				String sql = "select * from t_guiderecords where customerPhone = '" + s
						+ "' and applyStatus != 2 and applyStatus != 3";
				ResultSet rs6 = (ResultSet) stmt.executeQuery(sql);
				while (rs6.next()) {
					count++;
				}
				if(count > 0){
					pSet.add(s);
				}
			}

			map.put("newCustomerCount", newSet);
			map.put("newTwoCustomerCount", TwoSet);
			map.put("totalCustomerCount", totalSet);
			map.put("platformCustomerCount", pSet);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return map;
	}

	public List<String> selectAllProjectId() {
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;
		List<String> list = new LinkedList<>();
		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_projects";
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(rs.getString("projectId"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public User selectUserByPhone(String phone) {
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;

		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_users where phone = '" + phone + "' ";
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			User user = new User();
			while (rs.next()) {
				user.setUserId(rs.getString("userId"));
				user.setPassword(rs.getString("password"));
				user.setUserCaption(rs.getString("userCaption"));
				user.setPhoto(rs.getString("photo"));
				user.setPhone(rs.getString("phone"));
				user.setCreateTime(rs.getString("createTime"));
				user.setUserStatus(rs.getInt("userStatus"));
				user.setLastLoginTime(rs.getString("lastLoginTime"));
				user.setParentId(rs.getString("parentId"));
				user.setUserToken(rs.getString("userToken"));
			}

			rs.close();
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	/**
	 * ���Ұ������ܿͻ���
	 * 
	 * @param projectId
	 * @param time
	 * @return
	 */
	public Integer selectProjectCustomerTotalCount(String projectId, String time) {
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;
		Integer count = 0;
		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_projectcustomers where projectId = '" + projectId + "' and lastTime <= '"
					+ getThisTimeLastTime(time) + "' ";
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while(rs.next()){
				count++;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	/**
	 * ��ѯ���óɽ���
	 * @param projectId
	 * @param time
	 * @return
	 */
	public Integer selectVisitToDealCount(String projectId, String time){
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;
		Integer count = 0;
		try {
			stmt = (Statement) conn.createStatement();
			String sql = "SELECT c.recordNo,c.userId,c.houseNum,c.customerName,c.customerPhone,v.num ,c.recordStatus, v.arriveTime "
					+ "FROM t_contractrecords c, (SELECT customerId,phone,COUNT(phone) num, arriveTime FROM t_visitrecords "
					+ "WHERE writeState = 1 GROUP BY phone)v WHERE c.customerPhone = v.phone and c.recordStatus = 5 and c.projectId = '"
					+ projectId + "'";
			sql += " and v.arriveTime like '%" + time + "%' " + " and c.contractConfirmTime like '%" + time
					+ "%' GROUP BY (c.customerPhone)";
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while(rs.next()){
				count++;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	/**
	 * ���ҽ�ֹ��ǰʱ������д���
	 * @param projectId
	 * @param day yyyy-MM-dd
	 * @return
	 */
	public List<ProjectCustomers> selectTotalCustomerBeforeDay(String projectId, String day, String userId){
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;
		Integer count = 0;
		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_projectcustomers where projectId = '" + projectId + "' "
					+ " and createTime <= '" + getThisTimeLastTime(day) + "' and projectCustomerPhone is not null "
							+ " and ownerUserId = '" + userId + "' ";
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			List<ProjectCustomers> list = new LinkedList<>();
			while(rs.next()){
				ProjectCustomers pc = new ProjectCustomers();
				pc.setProjectId(rs.getString("projectId"));
				pc.setProjectCustomerId(rs.getString("projectCustomerId"));
				pc.setProjectCustomerName(rs.getString("projectCustomerName"));
				pc.setProjectCustomerPhone(rs.getString("projectCustomerPhone"));
				pc.setIdCard(rs.getString("idCard"));
				pc.setCreateUserId(rs.getString("createUserId"));
				pc.setCreateTime(rs.getString("createTime"));
				pc.setSex(rs.getInt("sex"));
				pc.setOwnerUserId(rs.getString("ownerUserId"));
				pc.setOwnerStartTime(rs.getString("ownerStartTime"));
				pc.setLastTime(rs.getString("lastTime"));
				pc.setEvaluate(rs.getString("evaluate"));
				list.add(pc);
			}
			rs.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * ����һ���ڴ����Ĵ���
	 * @param day
	 * @param projectId
	 * @param userId
	 * @return
	 */
	public List<ProjectCustomers> selectCustomerCreateByDay(String day, String projectId, String userId){
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;
		Integer count = 0;
		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_projectcustomers where projectId = '" + projectId + "' "
					+ " and createTime like '%" + day + "%' and projectCustomerPhone is not null "
							+ " and ownerUserId = '" + userId + "' ";
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			List<ProjectCustomers> list = new LinkedList<>();
			while(rs.next()){
				ProjectCustomers pc = new ProjectCustomers();
				pc.setProjectId(rs.getString("projectId"));
				pc.setProjectCustomerId(rs.getString("projectCustomerId"));
				pc.setProjectCustomerName(rs.getString("projectCustomerName"));
				pc.setProjectCustomerPhone(rs.getString("projectCustomerPhone"));
				pc.setIdCard(rs.getString("idCard"));
				pc.setCreateUserId(rs.getString("createUserId"));
				pc.setCreateTime(rs.getString("createTime"));
				pc.setSex(rs.getInt("sex"));
				pc.setOwnerUserId(rs.getString("ownerUserId"));
				pc.setOwnerStartTime(rs.getString("ownerStartTime"));
				pc.setLastTime(rs.getString("lastTime"));
				pc.setEvaluate(rs.getString("evaluate"));
				list.add(pc);
			}
			rs.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * �����ۼƵ��ü�¼����ֹ��ĳһ�����ʱ��
	 * @param projectId
	 * @param day
	 * @return
	 */
	public List<VisitRecords> selectVisitDataBeforeDay(String projectId, String day, String userId){
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;
		try {
			List<VisitRecords> list = new LinkedList<>();
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_visitrecords where projectId = '" + projectId + "' "
					+ " and arriveTime <= '" + getThisTimeLastTime(day) + "' "
							+ " and userId = '" + userId + "' ";
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while(rs.next()){
				VisitRecords vrs = new VisitRecords();
				vrs.setVisitNo(rs.getInt("visitNo"));
				vrs.setUserId(rs.getString("userId"));
				vrs.setProjectId(rs.getString("projectId"));
				vrs.setVisitStatus(rs.getInt("visitStatus"));
				vrs.setCustomerCount(rs.getInt("customerCount"));
				vrs.setCustomerName(rs.getString("customerName"));
				vrs.setPhone(rs.getString("phone"));
				vrs.setRecordNo(rs.getInt("recordNo"));
				vrs.setArriveTime(rs.getString("arriveTime"));
				vrs.setReceptTime(rs.getString("receptTime"));
				vrs.setLeaveTime(rs.getString("leaveTime"));
				vrs.setAppointUserId(rs.getString("appointUserId"));
				vrs.setCustomerId(rs.getString("customerId"));
				vrs.setWriteState(rs.getInt("writeState"));
				vrs.setIsNew(rs.getBoolean("isNew"));
				list.add(vrs);
			}
			
			rs.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * ���ݶ����Ų��Ҷ���
	 * @param projectId
	 * @param recordNo
	 * @return
	 */
	public ContractRecords findContractRecordsByRecordNo(String projectId, Integer recordNo){
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;
		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_contractrecords where projectId = '" + projectId + "' ";
			if(recordNo != null && !"".equals(recordNo)){
				sql += " and recordNo = " + recordNo;
			}
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while(rs.next()){
				ContractRecords c = new ContractRecords();
				c.setRecordNo(rs.getInt("recordNo"));
				c.setUserId(rs.getString("userId"));
				c.setProjectId(rs.getString("projectId"));
				c.setHouseNum(rs.getInt("houseNum"));
				c.setCustomerName(rs.getString("customerName"));
				c.setCustomerIDCard(rs.getString("customerIDCard"));
				c.setCustomerPhone(rs.getString("customerPhone"));
				c.setBenefitlds(rs.getString("benefitlds"));
				c.setPrice(rs.getDouble("price"));
				c.setRecordStatus(rs.getInt("recordStatus"));
				c.setApplyTime(rs.getString("applyTime"));
				c.setAuditingTime(rs.getString("auditingTime"));
				c.setAuditionUserId(rs.getString("auditionUserId"));
				c.setAuditionReson(rs.getString("auditionReson"));
				c.setRemitNo(rs.getString("remitNo"));
				c.setRemitConfirmTime(rs.getString("remitConfirmTime"));
				c.setRemitConfirmUserId(rs.getString("remitConfirmUserId"));
				c.setContractConfirmTime(rs.getString("contractConfirmTime"));
				c.setContractconfirmUseerId(rs.getString("contractconfirmUseerId"));
				c.setIsShopPayConfirm(rs.getInt("isShopPayConfirm"));
				c.setShopPayConfirmTime(rs.getString("shopPayConfirmTime"));
				c.setShopPayConfirmUserId(rs.getString("shopPayConfirmUserId"));
				c.setShopPayConfirmDesc(rs.getString("shopPayConfirmDesc"));
				c.setCancelShopPayConfirmDesc(rs.getString("cancelShopPayConfirmDesc"));
				c.setIsSystemPayConfirm(rs.getInt("isSystemPayConfirm"));
				c.setSystemPayConfirmTime(rs.getString("systemPayConfirmTime"));
				c.setSystemPayConfirmUserId(rs.getString("systemPayConfirmUserId"));
				c.setSystemPayConfirmDesc(rs.getString("systemPayConfirmDesc"));
				c.setCancelSystemPayConfirmDesc(rs.getString("cancelSystemPayConfirmDesc"));
				c.setShopReceiveConfirmTime(rs.getString("shopReceiveConfirmTime"));
				c.setShopReceiveConfirmUserId(rs.getString("shopReceiveConfirmUserId"));
				c.setShopCancelReceiveConfirmDesc(rs.getString("shopCancelReceiveConfirmDesc"));
				c.setSystemReceiveConfirmTime(rs.getString("systemReceiveConfirmTime"));
				c.setSystemReceiveConfirmUserId(rs.getString("systemReceiveConfirmUserId"));
				c.setCredentialsType(rs.getInt("credentialsType"));
				c.setCredentialsNum(rs.getString("credentialsNum"));
				c.setCredentialsPhoto(rs.getString("credentialsPhoto"));
				c.setBuyPrice(rs.getDouble("buyPrice"));
				c.setVoucherUploadTime(rs.getString("voucherUploadTime"));
				c.setGetMoneyDesc(rs.getString("getMoneyDesc"));
				c.setProjectCustomerId(rs.getString("projectCustomerId"));
				c.setIsOut(rs.getString("isOut"));
				c.setPayStyle(rs.getInt("payStyle"));
				c.setOrderProperty(rs.getInt("orderProperty"));
				c.setIsAlreadyRead(rs.getInt("isAlreadyRead"));
				c.setEnterBuyRelation(rs.getInt("enterBuyRelation"));
				c.setAccountStyle(rs.getInt("accountStyle"));
				c.setKillTheOrderReason(rs.getString("killTheOrderReason"));
				c.setRevokeOrderNotes(rs.getString("revokeOrderNotes"));
				c.setRevokeTime(rs.getString("revokeTime"));
				c.setBenefitInfo(rs.getString("benefitInfo"));
				c.setOrderNum(rs.getString("orderNum"));
				c.setShopCustomerId(rs.getString("shopCustomerId"));
				c.setRealCustomerId(rs.getString("realCustomerId"));
				c.setHaveToPayMoney(rs.getString("haveToPayMoney"));
				c.setGuideId(rs.getString("guideId"));
				return c;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * ���ݿͻ����ֻ��Ų��Ҷ���
	 * @param projectId
	 * @param phone
	 * @return
	 */
	public List<ContractRecords> selectContractRecordsByCustomerPhone(String projectId, String phone, String time){
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;
		List<ContractRecords> list = new ArrayList<>();
		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_contractrecords where projectId = '" + projectId + "' ";
			if(phone != null && !"".equals(phone)){
				sql += " and customerPhone = '" + phone + "' ";
			}
			if(time != null && !"".equals(time)){
				sql += " and applyTime like '%" + time + "%' ";
			}
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while(rs.next()){
				ContractRecords c = new ContractRecords();
				c.setRecordNo(rs.getInt("recordNo"));
				c.setUserId(rs.getString("userId"));
				c.setProjectId(rs.getString("projectId"));
				c.setHouseNum(rs.getInt("houseNum"));
				c.setCustomerName(rs.getString("customerName"));
				c.setCustomerIDCard(rs.getString("customerIDCard"));
				c.setCustomerPhone(rs.getString("customerPhone"));
				c.setBenefitlds(rs.getString("benefitlds"));
				c.setPrice(rs.getDouble("price"));
				c.setRecordStatus(rs.getInt("recordStatus"));
				c.setApplyTime(rs.getString("applyTime"));
				c.setAuditingTime(rs.getString("auditingTime"));
				c.setAuditionUserId(rs.getString("auditionUserId"));
				c.setAuditionReson(rs.getString("auditionReson"));
				c.setRemitNo(rs.getString("remitNo"));
				c.setRemitConfirmTime(rs.getString("remitConfirmTime"));
				c.setRemitConfirmUserId(rs.getString("remitConfirmUserId"));
				c.setContractConfirmTime(rs.getString("contractConfirmTime"));
				c.setContractconfirmUseerId(rs.getString("contractconfirmUseerId"));
				c.setIsShopPayConfirm(rs.getInt("isShopPayConfirm"));
				c.setShopPayConfirmTime(rs.getString("shopPayConfirmTime"));
				c.setShopPayConfirmUserId(rs.getString("shopPayConfirmUserId"));
				c.setShopPayConfirmDesc(rs.getString("shopPayConfirmDesc"));
				c.setCancelShopPayConfirmDesc(rs.getString("cancelShopPayConfirmDesc"));
				c.setIsSystemPayConfirm(rs.getInt("isSystemPayConfirm"));
				c.setSystemPayConfirmTime(rs.getString("systemPayConfirmTime"));
				c.setSystemPayConfirmUserId(rs.getString("systemPayConfirmUserId"));
				c.setSystemPayConfirmDesc(rs.getString("systemPayConfirmDesc"));
				c.setCancelSystemPayConfirmDesc(rs.getString("cancelSystemPayConfirmDesc"));
				c.setShopReceiveConfirmTime(rs.getString("shopReceiveConfirmTime"));
				c.setShopReceiveConfirmUserId(rs.getString("shopReceiveConfirmUserId"));
				c.setShopCancelReceiveConfirmDesc(rs.getString("shopCancelReceiveConfirmDesc"));
				c.setSystemReceiveConfirmTime(rs.getString("systemReceiveConfirmTime"));
				c.setSystemReceiveConfirmUserId(rs.getString("systemReceiveConfirmUserId"));
				c.setCredentialsType(rs.getInt("credentialsType"));
				c.setCredentialsNum(rs.getString("credentialsNum"));
				c.setCredentialsPhoto(rs.getString("credentialsPhoto"));
				c.setBuyPrice(rs.getDouble("buyPrice"));
				c.setVoucherUploadTime(rs.getString("voucherUploadTime"));
				c.setGetMoneyDesc(rs.getString("getMoneyDesc"));
				c.setProjectCustomerId(rs.getString("projectCustomerId"));
				c.setIsOut(rs.getString("isOut"));
				c.setPayStyle(rs.getInt("payStyle"));
				c.setOrderProperty(rs.getInt("orderProperty"));
				c.setIsAlreadyRead(rs.getInt("isAlreadyRead"));
				c.setEnterBuyRelation(rs.getInt("enterBuyRelation"));
				c.setAccountStyle(rs.getInt("accountStyle"));
				c.setKillTheOrderReason(rs.getString("killTheOrderReason"));
				c.setRevokeOrderNotes(rs.getString("revokeOrderNotes"));
				c.setRevokeTime(rs.getString("revokeTime"));
				c.setBenefitInfo(rs.getString("benefitInfo"));
				c.setOrderNum(rs.getString("orderNum"));
				c.setShopCustomerId(rs.getString("shopCustomerId"));
				c.setRealCustomerId(rs.getString("realCustomerId"));
				c.setHaveToPayMoney(rs.getString("haveToPayMoney"));
				c.setGuideId(rs.getString("guideId"));
				list.add(c);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * ����������
	 * @param projectId
	 * @param time
	 * @return
	 */
	public Integer selectRecordVisitCount(String projectId, String time){
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;
		int count = 0;
		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_guiderecords where projectId = '" + projectId + "' and applyStatus = 1 ";
			if(!StringUtils.isEmpty(time)){
				sql += " and applyTime like '%" + time + "%'";
			}
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while(rs.next()){
				count++;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	/**
	 * ������
	 * @param projectId
	 * @param time
	 * @return
	 */
	public Integer selectRecordCount(String projectId, String time){
		Connection conn = BaseDao.getDefaultDataBase();
		Statement stmt = null;
		int count = 0;
		try {
			stmt = (Statement) conn.createStatement();
			String sql = "select * from t_guiderecords where projectId = '" + projectId + "' and applyStatus != 2 and applyStatus != 3 ";
			if(!StringUtils.isEmpty(time)){
				sql += " and applyTime like '%" + time + "%'";
			}
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);
			while(rs.next()){
				count++;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	/*
	 * public static void main(String[] args) {
	 * System.out.println(randomString(-229985452) + " " +
	 * randomString(-147909649)); }
	 * 
	 * public static String randomString(int seed){ Random rand = new
	 * Random(seed); StringBuilder sb = new StringBuilder(); while(true){ int n
	 * = rand.nextInt(27); if(n==0) break; sb.append((char)('`'+n));
	 * 
	 * } return sb.toString(); }
	 */
}
