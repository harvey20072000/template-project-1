/**
 * @Description : 日期格式處理工具
 * @ClassName : DateFormatUtils.java
 * @Copyright : Copyright (c) 2024 Harvey.Liu All Rights Reserved.
 */
package com.temp.util;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

/**
 *  @see {@link org.apache.commons.lang3.time.DateFormatUtils}
 *
 */
public class DateFormatUtils extends org.apache.commons.lang3.time.DateFormatUtils {
	/** yyyyMMdd (eg. 20071001) */
	public static final FastDateFormat SIMPLE_ISO_DATE_FORMAT = FastDateFormat.getInstance("yyyyMMdd");

	/** HHmmss (eg. 131020) */
	public static final FastDateFormat SIMPLE_ISO_TIME_FORMAT = FastDateFormat.getInstance("HHmmss");
	
	/** yyyyMMdd'T'HHmmss (eg. 20071001T131020) */
	public static final FastDateFormat SIMPLE_ISO_DATETIME_FORMAT = FastDateFormat.getInstance("yyyyMMdd'T'HHmmss");
	
	/** yyyyMMdd (eg. 20071001) */
	public static final FastDateFormat SIMPLE_DATE_FORMAT = FastDateFormat.getInstance("yyyyMMdd");

	/** yyyyMMddHHmmss (eg. 20071001131020) */
	public static final FastDateFormat SIMPLE_DATETIME_FORMAT = FastDateFormat.getInstance("yyyyMMddHHmmss");
	
	/** yyyyMMddHHmmssSSS (eg. 20221001131020123) */
	public static final FastDateFormat SIMPLE_DATETIME_MS_FORMAT = FastDateFormat.getInstance("yyyyMMddHHmmssSSS");
	
	/** yyyy-MM-dd HHmmss (eg. 2007-10-01 13:10:20) */
	public static final FastDateFormat SQL_DATETIME_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
	
	/** yyyy-MM-dd (eg. 2007-10-01) */
	public static final FastDateFormat SQL_DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd");
	
	/** yyyy/MM/dd (eg. 2009/01/01) */
	public static final FastDateFormat CE_DATE_FORMAT = FastDateFormat.getInstance("yyyy/MM/dd");
	
	/** dd/MM/yyyy (eg.01/08/0222) */
	public static final FastDateFormat CE_DATE_REVERSE_FORMAT = FastDateFormat.getInstance("dd/MM/yyyy");

	/** yyyy/MM/dd HH:mm:ss (eg. 2009/01/01 13:10:20) */
	public static final FastDateFormat CE_DATETIME_FORMAT = FastDateFormat.getInstance("yyyy/MM/dd HH:mm:ss");

	/** yyyy/MM/dd HH:mm:ss.SSS (eg. 2009/01/01 13:10:20.123) */
	public static final FastDateFormat CE_DATETIME_MS_FORMAT = FastDateFormat.getInstance("yyyy/MM/dd HH:mm:ss.SSS");
	
	/** HH:mm:ss (eg. 13:10:20) */
	public static final FastDateFormat CE_TIME_FORMAT = FastDateFormat.getInstance("HH:mm:ss");
	
	/** HH:mm:ss.SSS (eg. 13:10:20.123) */
	public static final FastDateFormat CE_TIME_MS_FORMAT = FastDateFormat.getInstance("HH:mm:ss.SSS");
	
	/** yyyy-MM-dd'T'HH:mm:ss.SSSZZ (eg. 2009-01-01T13:10:20.123+08:00) */
	public static final FastDateFormat CLIENTDT_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");
	
	/**凱基外幣電文Header netMsgSeqno 用**/
	public static final String NETMSGSEQNO_FORMAT = "yyMMddHHmmssSSS";
	
	/**凱基外幣電文送上台幣主機 用**/
	public static final String NETMSGSEQNO_CBFX_FORMAT = "yyMMddHHmmss";
	
	/** 民國紀元 Pattrn字元 */
	public static final String ROC_YEAR_PATTERN = "c";
	
	/** 民國紀元年 ccc/MM/dd (eg. 099/01/01) */
	public static final String ROC_DATE_FORMAT = "ccc/MM/dd";

	/** 民國紀元年 ccc/MM/dd HH:mm:ss (eg. 099/01/01 13:10:20) */
	public static final String ROC_DATETIME_FORMAT = "ccc/MM/dd HH:mm:ss";
	
	/** 民國紀元年 cccMMdd (eg. 0990101) */
	public static final String SIMPLE_ROC_DATE_FORMAT = "cccMMdd";
	
	
	
	
	/**
	 * 取得SQL格式的日期時間字串
	 * 
	 * <pre>
	 * DateUtils.getSQLDateTimeStr(new Date()) = &quot;yyyy-MM-dd HH:mm:ss&quot;
	 * </pre>
	 * 
	 * @param dt 欲轉換之java.util.Date
	 * @return 如果傳入日期為<code>null</code>，則回傳空字串
	 */
	public static String getSQLDateTimeStr(java.util.Date dt) {
		if (null == dt) {
			return "";
		}
		String sFormat = DateFormatUtils.SQL_DATETIME_FORMAT.getPattern();
		return DateFormatUtils.format(dt, sFormat);
	}
	
	
	/**
	 * 轉換Date物件日期格式，除Apache原有格式，新增支援民國年格式轉換，'c'為民國年Pattern字元，其餘依循ISO規範。例：ccc/MM/dd
	 * @param date 欲轉換之java.util.Date
	 * @param format 日期格式,參考{@link org.apache.commons.lang3.time.DateFormatUtils}
	 * @return 格式化字串
	 */
	public static String format(Date date, String format) {
		if (date == null)
			return null;
		int pos = format.indexOf(DateFormatUtils.ROC_YEAR_PATTERN);
		if(pos >= 0) {
			//民國年Pattern處理
			int lastPos = format.lastIndexOf(ROC_YEAR_PATTERN);
			String minguoYearPattern = format.substring(pos, lastPos+1);
			String formatToConvert = format.replace(minguoYearPattern, "yyyy");
			String convertStr = org.apache.commons.lang3.time.DateFormatUtils.format(date, formatToConvert);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			String minguoDt,minguoYear  = null;
			if(minguoYearPattern.length()==2) {
				minguoYear = StringUtils.leftPad(String.valueOf((c.get(Calendar.YEAR) - 1911) % 100), 2, '0');
				if(pos==0) {
					minguoDt = minguoYear + convertStr.substring(4);
				}else {
					minguoDt = convertStr.substring(0,pos) + minguoYear + ((lastPos+1)>=format.length()?"":convertStr.substring(lastPos+3));
				}
			}else if(minguoYearPattern.length()==4) {
				minguoYear = StringUtils.leftPad(String.valueOf(c.get(Calendar.YEAR) - 1911), 4, '0');
				if(pos==0) {
					minguoDt = minguoYear + convertStr.substring(4);
				}else {
					minguoDt = convertStr.substring(0,pos) + minguoYear + ((lastPos+1)>=format.length()?"":convertStr.substring(lastPos+1));
				}
			}else {
				//預設3碼民國年
				minguoYear = StringUtils.leftPad(String.valueOf((c.get(Calendar.YEAR) - 1911) % 1000), 2, '0');
				if(pos==0) {
					minguoDt = minguoYear + convertStr.substring(4);
				}else {
					minguoDt = convertStr.substring(0,pos) + minguoYear + ((lastPos+1)>=format.length()?"":convertStr.substring(lastPos+2));
				}
			}
			return minguoDt;
		}else {
			return org.apache.commons.lang3.time.DateFormatUtils.format(date, format);
		}
	}
	
	/**
	 * 轉換milliseconds為日期格式，除Apache原有格式，新增支援民國年格式轉換，'c'為民國年Pattern字元，其餘依循ISO規範。例：ccc/MM/dd
	 * @param milliseconds milliseconds
	 * @param format 日期格式
	 * @return
	 */
	public static String format(long milliseconds, String format) {
		Date date = new Date(milliseconds);
		return format(date, format);
	}
	
	
	public static void main(String argu[]) {
		System.out.println(DateFormatUtils.format(new Date(), DateFormatUtils.NETMSGSEQNO_CBFX_FORMAT));
		System.out.println(DateFormatUtils.format(new Date(), DateFormatUtils.NETMSGSEQNO_FORMAT));
		System.out.println(DateFormatUtils.format(new Date(), "yy/MM/dd"));
		System.out.println("**************");
		System.out.println(DateFormatUtils.format(new Date(), "cc/MM/dd hh:mm"));
		System.out.println(DateFormatUtils.format(new Date(), "MM/dd/cc hh:mm"));
		System.out.println(DateFormatUtils.format(new Date(), "MM/dd/cc"));
		System.out.println(DateFormatUtils.format(new Date(), "ccc/MM/dd hh:mm"));
		System.out.println(DateFormatUtils.format(new Date(), "MM/dd/ccc hh:mm"));
		System.out.println(DateFormatUtils.format(new Date(), "MM/dd/ccc"));
		System.out.println(DateFormatUtils.format(new Date(), "cccc/MM/dd hh:mm"));
		System.out.println(DateFormatUtils.format(new Date(), "MM/dd/cccc hh:mm"));
		System.out.println(DateFormatUtils.format(new Date(), "MM/dd/cccc"));
		System.out.println(DateFormatUtils.format(new Date(), "ccc/MM/dd"));
		System.out.println(DateFormatUtils.format(new Date(), "cccc/MM/dd"));
		System.out.println(DateFormatUtils.format(new Date(), "ccc/MM/dd hh:mm:ss"));
		System.out.println(DateFormatUtils.format(new Date(), DateFormatUtils.SQL_DATETIME_FORMAT.getPattern()));
		System.out.println(DateFormatUtils.format(new Date(), DateFormatUtils.SIMPLE_DATETIME_FORMAT.getPattern()));
		System.out.println(DateFormatUtils.format(new Date(), DateFormatUtils.SIMPLE_DATETIME_MS_FORMAT.getPattern()));
		System.out.println(DateFormatUtils.format(new Date(), "cccMMdd"));
		System.out.println(org.apache.commons.lang3.time.DateFormatUtils.format(new Date(), "yy/MM/dd hh:mm:ss.SSSZZ"));
	}
}
