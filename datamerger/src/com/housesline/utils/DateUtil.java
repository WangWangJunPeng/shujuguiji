package com.housesline.utils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




 
/**
 * ���ڹ����ࣺ����������صĴ���
 * 
 * @author grl
 * 
 */
public class DateUtil {
    public static final String PATTERN_GRACE = "yyyy/MM/dd HH:mm:ss";
    public static final String PATTERN_GRACE_MD = "yyyy/M/d HH:mm:ss";
    public static final String PATTERN_GRACE_NORMAL = "yyyy/MM/dd HH:mm";
    public static final String PATTERN_GRACE_SIMPLE = "yyyy/MM/dd";
 
    public static final String PATTERN_CLASSICAL = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_CLASSICAL_NORMAL = "yyyy-MM-dd HH:mm";
    public static final String PATTERN_CLASSICAL_SIMPLE = "yyyy-MM-dd";
 
    /**
     * ����Ĭ�ϸ�ʽ��ָ���ַ�������������
     * @param str ָ���ַ���
     * @return ���ؽ����������
     */
    public static Date parse(String str) {
        return parse(str, PATTERN_CLASSICAL);
    }
 
    /**
     * ����ָ����ʽ��ָ���ַ�������������
     * @param str ָ������
     * @param pattern ָ����ʽ
     * @return ���ؽ����������
     */
    public static Date parse(String str, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
 
    /**
     * ����Ĭ�ϸ�ʽ������ת��ʽ�����ַ���
     * @param date ָ������
     * @return ���ظ�ʽ������ַ���
     */
    public static String format(Date date) {
        return format(date, PATTERN_CLASSICAL);
    }
 
    /**
     * ����ָ����ʽ��ָ�����ڸ�ʽ�����ַ���
     * @param date ָ������
     * @param pattern ָ����ʽ
     * @return ���ظ�ʽ������ַ���
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
 
    /**
     * ��ȡʱ��date1��date2���ĺ�����
     * @param date1 ��ʼʱ��
     * @param date2 ����ʱ��
     * @return �������ĺ�����
     */
    public static long getOffsetMinute(Date date1, Date date2) {
        long seconds = ((date2.getTime() - date1.getTime()));
        return seconds;
    }
    
    /**
     * ��ȡʱ��date1��date2��������
     * @param date1 ��ʼʱ��
     * @param date2 ����ʱ��
     * @return ������������
     */
    public static int getOffsetSeconds(Date date1, Date date2) {
        int seconds = (int) ((date2.getTime() - date1.getTime()) / 1000);
        return seconds;
    }
 
    /**
     * ��ȡʱ��date1��date2���ķ�����
     * @param date1 ��ʼʱ��
     * @param date2 ����ʱ��
     * @return �������ķ�����
     */
    public static int getOffsetMinutes(Date date1, Date date2) {
        return getOffsetSeconds(date1, date2) / 60;
    }
 
    /**
     * ��ȡʱ��date1��date2����Сʱ��
     * @param date1 ��ʼʱ��
     * @param date2 ����ʱ��
     * @return ��������Сʱ��
     */
    public static int getOffsetHours(Date date1, Date date2) {
        return getOffsetMinutes(date1, date2) / 60;
    }
 
    /**
     * ��ȡʱ��date1��date2����������
     * @param date1 ��ʼʱ��
     * @param date2 ����ʱ��
     * @return ������������
     */
    public static int getOffsetDays(Date date1, Date date2) {
        return getOffsetHours(date1, date2) / 24;
    }
 
    /**
     * ��ȡʱ��date1��date2��������
     * @param date1 ��ʼʱ��
     * @param date2 ����ʱ��
     * @return ������������
     */
    public static int getOffsetWeeks(Date date1, Date date2) {
        return getOffsetDays(date1, date2) / 7;
    }
    
    /**
     * ��ȡʱ�� start��end��������
     * @param start
     * @param end
     * @return
     */
    public static int getMonth(Date start, Date end) {
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR)
                - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH)
                - startCalendar.get(Calendar.MONTH);

        if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month + 1;
        } else if ((startCalendar.get(Calendar.DATE) != 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) != 1)) {
            return year * 12 + month;
        } else {
            return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }
    }
 
    /**
     * ��ȡ����ָ�����ڵ�ʱ������ʱ��
     * @param date ָ������
     * @param hour ָ��Сʱ
     * @param minut ָ������
     * @param second ָ����
     * @return ��������ʱ������ʱ��
     */
    public static Date getResetTime(Date date, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.SECOND, minute);
        cal.set(Calendar.MINUTE, second);
        return cal.getTime();
    }
 
    /**
     * ����ָ�����ڵ���ʼʱ��
     * @param date ָ�����ڣ�����2014-08-01��
     * @return ������ʼʱ�䣨����2014-08-01 00:00:00��
     */
    public static Date getIntegralStartTime(Date date) {
        return getResetTime(date, 0, 0, 0);
    }
 
    /**
     * ����ָ�����ڵĽ���ʱ��
     * @param date ָ�����ڣ�����2014-08-01��
     * @return ���ؽ���ʱ�䣨����2014-08-01 23:59:59��
     */
    public static Date getIntegralEndTime(Date date) {
        return getResetTime(date, 23, 59, 59);
    }
 
    /**
     * ��ȡָ�������ۼ������պ��ʱ��
     * @param date ָ������
     * @param year ָ������
     * @param month ָ������
     * @param day ָ������
     * @return �����ۼ������պ��ʱ��
     */
    public static Date rollDate(Date date, int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.YEAR, year);
        cal.add(Calendar.MONTH, month);
        cal.add(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }
 
    /**
     * ��ȡָ�������ۼ�ָ���������ʱ��
     * @param date ָ������
     * @param month ָ������
     * @return �����ۼ��������ʱ��
     */
    public static Date rollMonth(Date date, int month) {
        return rollDate(date, 0, month, 0);
    }
 
    /**
     * ��ȡָ�������ۼ�ָ���������ʱ��
     * @param date ָ������
     * @param day ָ������
     * @return �����ۼ��������ʱ��
     */
    public static Date rollDay(Date date, int day) {
        return rollDate(date, 0, 0, day);
    }
 
    /**
     * ����ָ�����������·ݵ�����
     * @param date ָ������
     * @return ���������·ݵ�����
     */
    public static int getDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        int dayOfMonth = cal.getActualMaximum(Calendar.DATE);
        return dayOfMonth;
    }
 
    /**
     * ��ȡָ�������·ݵĵ�һ�����ʼʱ�䣬����2014-08-01 00:00:00
     * @return ���ص��µ�һ�����ʼʱ��
     */
    public static Date getCurrentMonthStartTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return getIntegralStartTime(cal.getTime());
    }
    
    /**
     * ��ȡָ�������µ����һ��Ľ���ʱ�䣬����2014-08-31 23:59:59
     * @return ���ص������һ��Ľ���ʱ��
     */
    public static Date getMonthEndTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, getDayOfMonth(cal.getTime()));
        return getIntegralEndTime(cal.getTime());
    }
    
    /**
     * ��ȡ���µ�һ�����ʼʱ�䣬����2014-08-01 00:00:00
     * @return ���ص��µ�һ�����ʼʱ��
     */
    public static Date getMonthStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return getIntegralStartTime(cal.getTime());
    }
 
    /**
     * ��ȡ�������һ��Ľ���ʱ�䣬����2014-08-31 23:59:59
     * @return ���ص������һ��Ľ���ʱ��
     */
    public static Date getMonthEndTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, getDayOfMonth(cal.getTime()));
        return getIntegralEndTime(cal.getTime());
    }
 
    /**
     * ��ȡ�ϸ��µ�һ�����ʼʱ�䣬����2014-07-01 00:00:00
     * @return �����ϸ��µ�һ�����ʼʱ��
     */
    public static Date getLastMonthStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return getIntegralStartTime(cal.getTime());
    }
 
    /**
     * ��ȡ�ϸ������һ��Ľ���ʱ�䣬����2014-07-31 23:59:59
     * @return �����ϸ������һ��Ľ���ʱ��
     */
    public static Date getLastMonthEndTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, getDayOfMonth(cal.getTime()));
        return getIntegralEndTime(cal.getTime());
    }
 
    /**
     * ��ȡ�¸��µ�һ�����ʼʱ�䣬����2014-09-01 00:00:00
     * @return �����¸��µ�һ�����ʼʱ��
     */
    public static Date getNextMonthStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return getIntegralStartTime(cal.getTime());
    }
 
    /**
     * ��ȡ�¸������һ��Ľ���ʱ�䣬����2014-09-30 23:59:59
     * @return �����¸������һ��Ľ���ʱ��
     */
    public static Date getNextMonthEndTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, getDayOfMonth(cal.getTime()));
        return getIntegralEndTime(cal.getTime());
    }
 
    /**
     * ��ȡ��ǰ���ȵ�һ�����ʼʱ��
     * @return ���ص�ǰ���ȵ�һ�����ʼʱ��
     */
    public static Date getQuarterStartTime() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        if (month < 3) {
            cal.set(Calendar.MONTH, 0);
        } else if (month < 6) {
            cal.set(Calendar.MONTH, 3);
        } else if (month < 9) {
            cal.set(Calendar.MONTH, 6);
        } else {
            cal.set(Calendar.MONTH, 9);
        }
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return getIntegralStartTime(cal.getTime());
    }
 
    /**
     * ��ȡ��ǰ�������һ��Ľ���ʱ��
     * @return ���ص�ǰ�������һ��Ľ���ʱ��
     */
    public static Date getQuarterEndTime() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        if (month < 3) {
            cal.set(Calendar.MONTH, 2);
        } else if (month < 6) {
            cal.set(Calendar.MONTH, 5);
        } else if (month < 9) {
            cal.set(Calendar.MONTH, 8);
        } else {
            cal.set(Calendar.MONTH, 11);
        }
        cal.set(Calendar.DAY_OF_MONTH, getDayOfMonth(cal.getTime()));
        return getIntegralEndTime(cal.getTime());
    }
 
    /**
     * ��ȡǰһ��������
     * @return ����ǰһ��������
     */
    public static Date getPrevWorkday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            cal.add(Calendar.DAY_OF_MONTH, -2);
        }
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        return getIntegralStartTime(cal.getTime());
    }
 
    /**
     * ��ȡ��һ��������
     * @return �����¸�������
     */
    public static Date getNextWorkday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            cal.add(Calendar.DAY_OF_MONTH, 2);
        }
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return getIntegralStartTime(cal.getTime());
    }
 
    /**
     * ��ȡ���ܵĵ�һ��������
     * @return ���ص�һ��������
     */
    public static Date getFirstWorkday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return getIntegralStartTime(cal.getTime());
    }
 
    /**
     * ��ȡ���ܵ����һ��������
     * @return �������һ��������
     */
    public static Date getLastWorkday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        return getIntegralStartTime(cal.getTime());
    }
 
    /**
     * �ж�ָ�������Ƿ��ǹ�����
     * @param date ָ������
     * @return ����ǹ����շ���true�����򷵻�false
     */
    public static boolean isWorkday(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return !(dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY);
    }
 
    /**
     * ��ȡָ�����������ڼ�
     * @param date ָ������
     * @return �������ڼ�������
     */
    public static String getWeekdayDesc(Date date) {
        final String[] weeks = new String[]{"������", "����һ", "���ڶ�", "������", "������",
                "������", "������"};
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        return weeks[cal.get(Calendar.DAY_OF_WEEK) - 1];
    }
 
    /**
     * ��ȡָ�����ھ��뵱ǰʱ���ʱ�����������3Сʱǰ��1��ǰ��
     * @param date ָ������
     * @return ����ʱ��������
     */
    public static String getTimeOffsetDesc(Date date) {
        int seconds = getOffsetSeconds(date, new Date());
        if (Math.abs(seconds) < 60) {
            return Math.abs(seconds) + "��" + (seconds > 0 ? "ǰ" : "��");
        }
        int minutes = seconds / 60;
        if (Math.abs(minutes) < 60) {
            return Math.abs(minutes) + "����" + (minutes > 0 ? "ǰ" : "��");
        }
        int hours = minutes / 60;
        if (Math.abs(hours) < 60) {
            return Math.abs(hours) + "Сʱ" + (hours > 0 ? "ǰ" : "��");
        }
        int days = hours / 24;
        if (Math.abs(days) < 7) {
            return Math.abs(days) + "��" + (days > 0 ? "ǰ" : "��");
        }
        int weeks = days / 7;
        if (Math.abs(weeks) < 5) {
            return Math.abs(weeks) + "��" + (weeks > 0 ? "ǰ" : "��");
        }
        int monthes = days / 30;
        if (Math.abs(monthes) < 12) {
            return Math.abs(monthes) + "����" + (monthes > 0 ? "ǰ" : "��");
        }
        int years = monthes / 12;
        return Math.abs(years) + "��" + (years > 0 ? "ǰ" : "��");
    }
    
    /**
     * ��ʱ����ϼ���Сʱ
     * @param day ��ǰʱ�� ��ʽ��yyyy-MM-dd HH:mm:ss
     * @param hour ��Ҫ�ӵ�ʱ��
     * @return
     */
    public static String addDateMinut(String day, int hour)//���ص����ַ����͵�ʱ�䣬�����
    //��String day, int x
    {   
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24Сʱ��  
    	//�����������ʽҲ������ HH:mm:ss����HH:mm�ȵȣ�������ģ�����������������ʱ��Ҫ������ı�
    	//��day��ʽһ��
    	Date date = null;   
    	try {   
    		date = format.parse(day);   
    	} catch (Exception ex) {   
    		ex.printStackTrace();   
    	}   
    	if (date == null)   
    		return "";   
    	System.out.println("front:" + format.format(date)); //��ʾ���������  
    	Calendar cal = Calendar.getInstance();   
    	cal.setTime(date);   
    	cal.add(Calendar.HOUR, hour);// 24Сʱ��   
    	date = cal.getTime();    
    	System.out.println("after:" + format.format(date));  //��ʾ���º������ 
    	cal = null;   
    	return format.format(date);   
    	
    }
    
    /**
     * ��ȡ��ǰdateǰ�ĵ�daysNum������
     * @param date
     * @return
     */
    public static Map<String,String> getPastAnyDaysOfDate(Date date,int daysNum){
    	Map map = new HashMap<>();
    	SimpleDateFormat format = new SimpleDateFormat(PATTERN_CLASSICAL);
        Calendar c = Calendar.getInstance();
        //��ȥ������
        c.setTime(date);
        c.add(Calendar.DATE, - daysNum+1);
        Date startD = getIntegralStartTime(c.getTime());
        Date endD = getIntegralEndTime(date);
        String startDay = format.format(startD);
        String endDay = format.format(endD);
        map.put("pastAnydaysStartDay", startDay);
        map.put("currentDateEndDay", endDay);
        return map;
    }
    
	public static List getBetweenTwoDateOfEveryDay(String startStr,String endStr){
    	List list = new ArrayList<>();
    	SimpleDateFormat format = new SimpleDateFormat(PATTERN_CLASSICAL_SIMPLE);
    	Date startDate = parse(startStr);
    	Date endDate = parse(endStr);
    	list.add(format(startDate,PATTERN_CLASSICAL_SIMPLE));
    	while(startDate.getTime() < getIntegralStartTime(endDate).getTime()){
    		startDate = rollDay(startDate,1);
    		list.add(format(startDate,PATTERN_CLASSICAL_SIMPLE));
    	}
    	return list;
    }
 
    /**
     * ��ȡ��ǰdateǰmonthNum�µ�����
     * @param date
     * @param monthNum
     * @return
     */
	public static Map<String, String> getPastAnyMonthOfDate(Date date, int monthNum) {
		Map map = new HashMap<>();
		SimpleDateFormat format = new SimpleDateFormat(PATTERN_CLASSICAL);
		Calendar c = Calendar.getInstance();
		// ��ȥ������
		c.setTime(date);
		c.add(Calendar.MONTH, -monthNum+1);
		Date startD = getIntegralStartTime(c.getTime());
		Date endD = getIntegralEndTime(date);
		String startDay = format.format(startD);
		String endDay = format.format(endD);
		map.put("pastAnydaysStartDay", startDay);
		map.put("currentDateEndDay", endDay);
		return map;

	}
	
	/**
	 * ��ȡ��������֮���ÿһ��
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static List<String> getTwoDateEveryDay(String startTime, String endTime) {
		List<String> list = new ArrayList<>();
		if(!startTime.equals(endTime)){
			
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
		}else{
			list.add(startTime);
		}

		return list;

	}
	
	/**
	 * ����ʱ���ŷ��ص�ǰʱ������ʱ��
	 * @param order
	 * @return
	 */
	public static String getTimeForOrder(String order){

		String result = "";
		
		if("week".equals(order)){
			result = DateUtil.format(DateUtil.rollDay(new Date(), -7), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}else if ("half_month".equals(order)){
			result = DateUtil.format(DateUtil.rollDay(new Date(), -15), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}else if("one_month".equals(order)){
			result = DateUtil.format(DateUtil.rollMonth(new Date(), -1), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}else if("half_year".equals(order)){
			result = DateUtil.format(DateUtil.rollMonth(new Date(), -6), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}else if("one_year".equals(order)){
			result = DateUtil.format(DateUtil.rollMonth(new Date(), -12), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}
		return result;
	}
	
	/**
	 * �����ʼʱ��Ϊ��--����ǰһ�ܵ�ʱ��
	 * @param startTime yyyy-MM-dd
	 * @return	yyyy-MM-dd
	 */
	public static String ifNoStartTime(String startTime){
		if (startTime == null || "".equals(startTime)) {
			startTime = DateUtil.format(DateUtil.rollDay(new Date(), -7), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}
		return startTime;
	}
	
	/**
	 * �������ʱ��Ϊ��--����ǰһ���ʱ��
	 * @param endTime	yyyy-MM-dd
	 * @return	yyyy-MM-dd
	 */
	public static String ifNoEndTime(String endTime){
		if (endTime == null || "".equals(endTime)) {
			endTime = DateUtil.format(DateUtil.rollDay(new Date(), -1), DateUtil.PATTERN_CLASSICAL_SIMPLE);
		}
		return endTime;
	}
    
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception{
       Date day = new Date();
       
    	Map<String, String> map = getPastAnyMonthOfDate(day, 3);
    	System.out.println(map.get("pastAnydaysStartDay"));
    	System.out.println(map.get("currentDateEndDay"));
    	
    }
    
    
    
  
 
}