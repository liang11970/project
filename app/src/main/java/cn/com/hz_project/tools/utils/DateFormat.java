package cn.com.hz_project.tools.utils;

/*
 * File Name：DateFormat.java
 * Copyright：Copyright 2008-2016 CiWong.Inc. All Rights Reserved.
 * Description： DateFormat.java
 * Modify By：wee
 * Modify Date：2016-7-6
 * Modify Type：Add
 */

import android.content.Context;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cn.com.projectdemos.R;

/**
 * 日期、时间格式化
 * 
 * @author PLA-ZJLIU
 * @version ciwong v.1.0 2013-4-26
 * @since ciwong v.1.0
 */
public class DateFormat {

	/** 24个小时内称为一天内 */
	public static final int FORMAT_TODAY_TIME = 60 * 60 * 72;

	/** 24个小时内称之前多少小时时间前 */
	public static final int FORMAT_BEFORE_HOUR_TIME = 60 * 60 * 24;

	/** 1个小时内称之前多少分钟时间前 */
	public static final int FORMAT_BEFORE_MINUTE_TIME = 60 * 60;

	public static final int ONE_MINUTE = 60*1000;

	/** 多少时间之内称为刚刚 */
	public static final int FORMAT_JUST_TIME = 60;
	
	private static long sTimeEarlyToday;
	private static long sTimeInMillis;
	private static long time_long_day = 3600 * 24 * 1000;
	private static long time_long_7_day = time_long_day * 7;

	static SimpleDateFormat sDayFormat = new SimpleDateFormat("yyyy年MM月dd日");
	static SimpleDateFormat yMonthDayFormat = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat sDayFormat2 = new SimpleDateFormat("yy/MM/dd");
	private static int sNowDay;
	private static int sNowYear;
	static SimpleDateFormat sWeekFormat = new SimpleDateFormat("EEEE");
	static  SimpleDateFormat sMinuteFormat= new SimpleDateFormat("HH:mm");
	private static SimpleDateFormat rankDataFormat = new SimpleDateFormat("MM月dd日HH");
	private static SimpleDateFormat monthDayFormat = new SimpleDateFormat("MM月dd日");
	public static SimpleDateFormat naturalFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * 日期转换(yyyy-MM-dd HH:mm:ss)<br />
	 * 转换中出现异常则返回null
	 * 
	 * @param dateLong
	 *            日期长整型
	 * @return 转换后的日期
	 */
	public static String formatDate1(long dateLong) {
		try {
			Date date = new Date(dateLong);
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			return format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 日期、时间格式化:今天、昨天、刚刚
	 * 
	 * @param creatmillis
	 *            要转换的时间戳
	 * @param mContext
	 *            上下文
	 * @return 转换后的格式
	 */
	public static String formatDateString(long creatmillis, Context mContext) {
		return getDateString(creatmillis, mContext);
	}
	
	public static String getRankListDateFormat(long dateLong){
		return rankDataFormat.format(dateLong);
		
	}

	/**
	 * 日期、时间格式化:今天、昨天、刚刚
	 * 
	 * @param creatmillis
	 *            要转换的时间戳
	 * @param mContext
	 *            上下文
	 * @return 转换后的格式
	 */
	public static String getDateString(long creatmillis, Context mContext) {
		String dateString = "";
		long delmillis = (System.currentTimeMillis() - creatmillis) / 1000;
		Date newdate = new Date();
		Calendar newcalendar = Calendar.getInstance(Locale.CHINA);
		newcalendar.setTime(newdate);
		Date date = new Date(creatmillis);
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		calendar.setTime(date);
//		int dateday = newcalendar.get(Calendar.DAY_OF_YEAR)
//				- calendar.get(Calendar.DAY_OF_YEAR);
		if ((delmillis / 60 / 60 / 24) >= 0 && (delmillis / 60 / 60 / 24) < 365) {
			if ( delmillis <= 60 * 60 * 96) {
				if (delmillis <= FORMAT_JUST_TIME) {
					dateString = mContext.getString(R.string.libs_just);
				} else if (delmillis > FORMAT_JUST_TIME
						&& delmillis <= FORMAT_BEFORE_MINUTE_TIME) {
					dateString = mContext.getString(
							R.string.libs_before_minute, delmillis / 60);
				} else if (delmillis > FORMAT_BEFORE_MINUTE_TIME
						&& delmillis <= FORMAT_BEFORE_HOUR_TIME) {
					dateString = mContext.getString(R.string.libs_before_hour,
							delmillis / 60 / 60);
				} else if (delmillis > FORMAT_BEFORE_HOUR_TIME
						&& delmillis <= 60 * 60 * 96) {
					if (delmillis > FORMAT_BEFORE_HOUR_TIME
							&& delmillis <= 60 * 60 * 48) {
						dateString = "1天前";
					} else if (delmillis > 60 * 60 * 48
							&& delmillis <= 60 * 60 * 72) {
						dateString = "2天前";
					} else if (delmillis > 60 * 60 * 72
							&& delmillis <= 60 * 60 * 96) {
						dateString = "3天前";
					}
				}
			} else {
				dateString = formatDate2(creatmillis);
			}
		} else if ((delmillis / 60 / 60 / 24) >= 0
				&& (delmillis / 60 / 60 / 24) >= 365) {
			dateString = formatDate3(creatmillis);
		}

		return dateString;
	}

	/**
	 * 日期转换(YYYY-MM-dd HH:mm)<br />
	 * 转换中出现异常则返回null
	 * 
	 * @param dateLong
	 *            日期长整型
	 * @return 转换后的格式
	 */
	public static String formatDate2(long dateLong) {
		try {
			Date date = new Date(dateLong);
			SimpleDateFormat format = new SimpleDateFormat("MM-dd");
			return format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 日期转换(YYYY-MM)<br />
	 * 转换中出现异常则返回null
	 * 
	 * @param dateLong
	 *            日期长整型
	 * @return 转换后的格式
	 */
	public static String formatDate3(long dateLong) {
		try {
			Date date = new Date(dateLong);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
			return format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String formatDate4(long dateLong) {
		try {
			Date date = new Date(dateLong);
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd");
			return format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static String formatDate5(long dateLong) {
		try {
			Date date = new Date(dateLong);
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm");
			return format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 日期转换(HH:mm)<br />
	 * 转换中出现异常则返回null
	 * 
	 * @param dateLong
	 *            日期长整型
	 * @return 转换结果
	 */
	public static String formatTime(long dateLong) {
		try {
			Date date = new Date(dateLong);
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			return format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	/***********   start:　将时间调整为友好模式，类似微信(互动列表和互动详情列表的时间设置公用该方法，根据type区分)　*************/
	public static String formatTimeStringForDetail(Context context, long dateLong, int type) {
		reset();
		String tmp = "";
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		calendar.setTimeInMillis(dateLong);
		// 获取时分格式
		String lastResult = sMinuteFormat.format(calendar.getTime());

		int nowDay = calendar.get(Calendar.DAY_OF_YEAR);//之前的时间
		int nowYear = calendar.get(Calendar.YEAR);
		long nowsTimeInMillis = calendar.getTimeInMillis();

		if ((sNowYear == nowYear) && (sNowDay == nowDay) && (sTimeInMillis - nowsTimeInMillis <= ONE_MINUTE)) {
			tmp = context.getString(R.string.libs_just);
		} else if ((sNowYear == nowYear) && (sNowDay == nowDay) && (sTimeInMillis - nowsTimeInMillis <= 60 * ONE_MINUTE)) {
			tmp = (sTimeInMillis - nowsTimeInMillis) / ONE_MINUTE + "分钟前";
		} else if ((sNowYear == nowYear) && (sNowDay == nowDay) && (sTimeInMillis - nowsTimeInMillis > 60 * ONE_MINUTE)) {
			tmp = (sTimeInMillis - nowsTimeInMillis) / (ONE_MINUTE * 60) + "小时前";
		} else if ((sNowYear == nowYear) && (sNowDay - nowDay) == 1) {
			if (type == 1) {
				tmp = context.getString(R.string.common_label_yesterday);
			} else if (type == 2) {
				tmp = context.getString(R.string.common_label_yesterday) + "  " + lastResult;
			}
		} else if ((sNowYear == nowYear) && (sNowDay - nowDay) > 1) {
			if (type == 1) {
				tmp = monthDayFormat.format(calendar.getTime());
			} else if (type == 2) {
				tmp = monthDayFormat.format(calendar.getTime()) + "  " + lastResult;
			}
		} else {
			if (type == 1) {
				tmp = yMonthDayFormat.format(calendar.getTime());
			} else if (type == 2) {
				tmp = yMonthDayFormat.format(calendar.getTime()) + "  " + lastResult;
			}
		}
		return tmp;
	}

	public static String formatTimeStringForList(Context context, long dateLong) {
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		calendar.setTimeInMillis(dateLong);

		//获取前半部分
		long offset = sTimeEarlyToday - dateLong;
		if (offset >= time_long_7_day) {
			return sDayFormat2.format(calendar.getTime());
		} else {
			int nowDay = calendar.get(Calendar.DAY_OF_YEAR);
			if (nowDay == sNowDay) {
				int am_pm = calendar.get(Calendar.AM_PM);
				//return  (am_pm==Calendar.AM?context.getString(R.string.common_label_am):context.getString(R.string.common_label_pm))+"  "+sMinuteFormat.format(calendar.getTime() );
				return sMinuteFormat.format(calendar.getTime());
			} else if (offset <= time_long_day) {//上面的if(){}排除了一天内的可能性
				return context.getString(R.string.common_label_yesterday);
			} else {
				return sWeekFormat.format(calendar.getTime());
			}
		}
	}
	
	public  static void reset(){
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTimeInMillis(System.currentTimeMillis());
        
        sNowDay =  calendar.get(Calendar.DAY_OF_YEAR);
		sNowYear = calendar.get(Calendar.YEAR);
		sTimeInMillis = calendar.getTimeInMillis();
        //获取中午１２点的时间值
//        calendar.set(Calendar.HOUR_OF_DAY,0);
//        calendar.set(Calendar.MINUTE, 0);
//        sTimeEarlyToday =calendar.getTimeInMillis();
	}
	
	
	/*public  static void reset(){
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTimeInMillis(System.currentTimeMillis());
        
        sNowDay =  calendar.get(Calendar.DAY_OF_YEAR);
        
        //获取中午１２点的时间值
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE, 0);
        sTimeEarlyToday =calendar.getTimeInMillis();
	}
	
	
	*//***********   start:　将时间调整为友好模式，类似微信　*************//*
	public static String formatTimeStringForDetail(Context context,long dateLong){
	       Calendar calendar = Calendar.getInstance(Locale.CHINA);
	       calendar.setTimeInMillis(dateLong);

	       // 获取时分格式
               int am_pm=   calendar.get(Calendar.AM_PM);
              String  lastResult=     (am_pm==Calendar.AM?context.getString(R.string.common_label_am):context.getString(R.string.common_label_pm))+"  "+sMinuteFormat.format(calendar.getTime() );
	           
              //获取前半部分
             long offset=  sTimeEarlyToday-dateLong;
              if(offset>=time_long_7_day){
                  return sDayFormat.format(calendar.getTime())+" "+lastResult;
              }else{
                  int nowDay =  calendar.get(Calendar.DAY_OF_YEAR);
                  if(nowDay==sNowDay){
                         return lastResult;
                  }else if(offset<=time_long_day){//上面的if()排除了一天内的可能性
                      return context.getString(R.string.common_label_yesterday)+"  "+lastResult;
                  }else{
                      return sWeekFormat.format(calendar.getTime())+" "+lastResult;
                  }
	       }
	       
	}
	
	   public static String formatTimeStringForList(Context context,long dateLong){
           Calendar calendar = Calendar.getInstance(Locale.CHINA);
           calendar.setTimeInMillis(dateLong);

              //获取前半部分
             long offset=  sTimeEarlyToday-dateLong;
              if(offset>=time_long_7_day){
                  return sDayFormat2.format(calendar.getTime());
              }else{
                  int nowDay =  calendar.get(Calendar.DAY_OF_YEAR);
                  if(nowDay==sNowDay){
                      int am_pm=   calendar.get(Calendar.AM_PM);
                         return  (am_pm==Calendar.AM?context.getString(R.string.common_label_am):context.getString(R.string.common_label_pm))+"  "+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE);
                  }else if(offset<=time_long_day){//上面的if(){}排除了一天内的可能性
                      return context.getString(R.string.common_label_yesterday) ;
                  }else{
                      return sWeekFormat.format(calendar.getTime()) ;
                  }
           }
           
    }*/
	
	
	

}
