package org.cityu.cs.ian.util.unuse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间段
 * @author gxy
 * @date 2016年4月8日 上午9:36:03
 *
 */
public class TimeBucketUtil{
	/**
	 * 根据参数返回时间段
	 * @param timeType
	 * @param sTime
	 * @param eTime
	 * @return
	 */
	public static String getTimeBucket(Integer timeType, Integer sTime, Integer eTime) {
		StringBuffer sb = new StringBuffer();
		sb.append("");
		if (timeType == 0 && sTime == 0 && eTime == 24) {
			sb.append("不限时间段");
		} else if (timeType == 0 && (sTime != 0 || eTime != 24)) {
			sb.append("每天");
			sb.append(sTime);
			sb.append("点到");
			sb.append(eTime);
			sb.append("点");
		} else if (timeType == 4) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date sdate = new Date(sTime * 1000);
			Date edate = new Date(eTime * 1000);
			String sDateString = sdf.format(sdate);
			String eDateString = sdf.format(edate);
			sb.append(sDateString);
			sb.append("至");
			sb.append(eDateString);
		} else if (timeType == 11) {
			String sDate = sTime + "";
			String eDate = eTime + "";
			// 每周一至周日的0点至24点
			sb.append("每");
			sb.append(getZhou(sDate.substring(0,1)));
			sb.append("至");
			sb.append(getZhou(eDate.substring(0,1)));
			sb.append("的");
			sb.append(getDian(sDate.substring(1)));
			sb.append("至");
			sb.append(getDian(eDate.substring(1)));
		} else {
			sb.append("不限时间段");
		}
		return sb.toString();
	}
	/**
	 * 
	 * @param s
	 * @return
	 */
	private static String getZhou(String s) {
		if (s.equals("1")) {
			return "周一";
		} else if (s.equals("2")) {
			return "周二";
		} else if (s.equals("3")) {
			return "周三";
		} else if (s.equals("4")) {
			return "周四";
		} else if (s.equals("5")) {
			return "周五";
		} else if (s.equals("6")) {
			return "周六";
		} else {
			return "周日";
		}
	}
	/**
	 * 得到小时
	 * @param s
	 * @return
	 */
	private static String getDian(String s) {
		if(s.startsWith("0")){
			return s.substring(1)+"点";
		}else{
			return s+"点";
		}
	}
	/**
	 * 得到月份
	 * @param date
	 * @return
	 */
	public static Integer getMonth(Date date){
		int result = -1;
		try{
			SimpleDateFormat sdf=new SimpleDateFormat("M");
			String month = sdf.format(date);
			result = Integer.parseInt(month);
		}catch(Exception e){
			result = -1;
		}
		return result;
	}
	/**
	 * 得到月份
	 * @param dateString
	 * @return
	 */
	public static Integer getMonth(String  dateString){
		int result = -1;
		try {
			SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf2.parse(dateString);
			SimpleDateFormat sdf=new SimpleDateFormat("M");
			String month = sdf.format(date);
			result = Integer.parseInt(month);
		} catch (ParseException e) {
			e.printStackTrace();
			result = -1;
		}
		return result;
	}

	/**
	 * 根据字符串返回时间的Long
	 * @param str
	 * @return
     */
	public static Long getLongTime(String str){
		SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
		try{
			long timeStart=(sdf2.parse(str).getTime())/1000;
			return timeStart;
		}catch (Exception e){
			return 1L;
		}
	}
}
