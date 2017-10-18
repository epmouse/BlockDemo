package org.cityu.cs.ian.util.unuse;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

public class DateUtil{
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	/** 
     * 将日期格式转换成yyyy-MM-dd的字符串格式 
     * 返回值如：2010-10-06 
     * @param time 要转换的日期 
     * @return 
     */  
    public static  String dateToString(Date time)  {  
        SimpleDateFormat formatter = new  SimpleDateFormat ("yyyy-MM-dd"); //定义将日期格式要换成的格式   
        String stringTime  =  formatter.format(time);        
        return  stringTime;            
      }  
    
    /** 
     * 将日期格式转换成yyyy-MM的字符串格式 
     * 返回值如：2010-10 
     * @param time 要转换的日期 
     * @return 
     */  
    public static  String dateToStr(Date time)  {  
        SimpleDateFormat formatter = new  SimpleDateFormat ("yyyy-MM"); //定义将日期格式要换成的格式   
        String stringTime  =  formatter.format(time);        
        return  stringTime;            
      }  
    
    /** 
     * 将日期格式转换成yyyyMMdd的字符串格式 
     * 返回值如：2010-10-06 
     * @param time 要转换的日期 
     * @return 
     */  
    public static  String dateTimeToString(Date time)  {            
        SimpleDateFormat formatter = new  SimpleDateFormat ("yyyyMMdd"); //定义将日期格式要换成的格式   
        String stringTime  =  formatter.format(time);      
        return  stringTime;        
      }  
      
       
    /** 
     * 将日期格式转换成yyyy-MM-dd的字符串格式 
     * 返回值如：2010-10-06 
     * @param time 要转换的日期 
     * @return 
     */  
    public static  Date dateToDate(Date time)  {  
        SimpleDateFormat formatter = new  SimpleDateFormat ("yyyy-MM-dd"); //定义将日期格式要换成的格式   
        String stringTime  =  formatter.format(time);  
        Date date = null;  
    try {  
        date = formatter.parse(stringTime);  
    } catch (ParseException e) {  
        e.printStackTrace();  
    }  
        return  date;  
          
    }  
    
    /** 
     * 将日期格式转换成yyyy-MM-dd的字符串格式 
     * 返回值如：2010-10-06 
     * @param time 要转换的日期 
     * @return 
     */  
    public static  Date dateToDate2(Date time)  {  
        SimpleDateFormat formatter = new  SimpleDateFormat ("yyyy-MM-dd"); //定义将日期格式要换成的格式   
        String stringTime  =  formatter.format(time);  
        Date date = null;  
    try {  
        date = formatter.parse(stringTime);  
    } catch (ParseException e) {  
        e.printStackTrace();  
    }  
        return  new Date(date.getTime());  
          
    }  
    
    /** 
     * 将字符串转换成日期 
     *  
     * @param date 
     * @return 
     * @throws Exception 
     */  
    public static Date formateDate(String date) throws Exception {  
    	SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd"); 
    	Date dat = null;
    	try {
    		if(date!=null){
    			dat=day.parse(date);
    		}
		} catch (Exception e) {
			 System.out.println("日期转换错误---"+date);
		}
        return  dat; 
    } 
    /** 
     * 将字符串转换成日期 
     *  
     * @param date 
     * @return 
     * @throws Exception 
     */  
    public static Date formateDate2(String date) throws Exception {  
    	SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        return day.parse(date);  
    } 
    /** 
     * 将日期转字符串
     *  
     * @param date 
     * @return yyyy-MM-dd HH:mm:ss
     * @throws Exception 
     */  
    public static String dateToString2(Date date) throws Exception {  
    	SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        return day.format(date);  
      
    } 
    /** 
     * 得到当前时间，以字符串表示 
     * @return 
     */  
    public static String getDate(){  
        Date date = new Date();  
        return DateUtil.dateToString(date);  
    } 
    /** 
     * 比较日期大小
     * 第一个比第二个大则返回 1
     * 第一个日期比第二小则返回 -1
     * 其他返回 0
     * @return 
     */
  public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
  /** 
   * 获得每月第一天
   * 
   * 
   * 
   * @return 
   */
	public static Date getMonthFirstDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);// +1今天的时间加一天
		date = calendar.getTime();
		return date;
	}
	  /** 
	   * 获得每月第一天
	   * 
	   * 
	   * 
	   * @return 
	   */
	public static String  getMonthLastDayString(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		//calendar.set(Calendar.DAY_OF_MONTH, 1);// +1今天的时间加一天
		calendar.set(Calendar.DAY_OF_MONTH, calendar     
                .getActualMaximum(Calendar.DAY_OF_MONTH));   
		date = calendar.getTime();
		return sdf.format(date);
	}
	public static Date getMonthFirstDay() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);// +1今天的时间加一天
		date = calendar.getTime();
		return date;
	}

	public static String getNextDayString(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, +1);// +1今天的时间加一天
		date = calendar.getTime();
		return sdf.format(date);
	}
	public static String getMonthFirstDayString() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);// +1今天的时间加一天
		date = calendar.getTime();
		return sdf.format(date);
	}
	public static String  getMonthFirstDayString(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);// +1今天的时间加一天
		date = calendar.getTime();
		return sdf.format(date);
	}
	
	
	public static List<String> betweenStartAndDate(String str) {
		Calendar startCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM");
		Date startDate;
		List<String> list = new ArrayList<String>();
		try {
			String dateStart = getMonthFirstDayString(df2.parse(str));
			String dateEnd = getMonthLastDayString(df2.parse(str));
			startDate = df.parse(dateStart);
			startCalendar.setTime(startDate);
			Date endDate = df.parse(dateEnd);
			endCalendar.setTime(endDate);
			
			list.add(dateStart);
			while (true) {
				startCalendar.add(Calendar.DAY_OF_MONTH, 1);
				if (startCalendar.getTimeInMillis() < endCalendar
						.getTimeInMillis()) {// TODO
					String s = df.format(startCalendar.getTime());
					list.add(s);
				} else {
					break;
				}
			}
			list.add(dateEnd);
		} catch (ParseException e) {
			e.printStackTrace();
			//log.error(e.getMessage());
		}
		return list;
	}
	
	public static String sfm(String str) {
		Long a = Long.valueOf(str);
		long hour = a / 3600; // !小时
		long minute = a % 3600 / 60; // !分钟
		long second = a % 60; // !秒
		return hour+":"+minute+":"+second;
	}
	public static Date getNextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, +1);// +1今天的时间加一天
		date = calendar.getTime();
		return date;
	}
	
	public static Date getPerDay(Date date) {
		return date;
	}
	
	public static Date getPerDay() {
		return new Date();
	}
	
	public static String getPerDayString(Date date) {
		return sdf.format(date);
	}
	
	public static String getPerDayString() {
		return sdf.format(new Date());
	}
}

