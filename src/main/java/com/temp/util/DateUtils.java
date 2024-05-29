package com.temp.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.temp.exception.UtilException;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	/** ISO日期格式的分隔子 */
	public final static String ISO_LINK = "-";

	/** 民國年預設之分隔子 */
	public final static String ROC_LINK = "/";

	/**
	 * 取得當前系統時間
	 * 
	 * @return
	 */
	public static Date now() {
		return new Date();
	}
	
	/**
	 * 取得當前時間(不含 hh:mm:ss)
	 * @return
	 */
	public static Date todayNoneTime() {
		
		Calendar calendar = Calendar.getInstance();
		
		clearTime(calendar);
		
		return calendar.getTime();
	}

	/**
	 * 取得現在之時間物件(<code>java.sql.Timestamp</code>)
	 * 
	 * @return java.sql.Timestamp
	 */
	public static Timestamp getCurrentTimestamp() {

		java.util.Date dt = new java.util.Date();

		return ConvertUtils.date2Timestamp(dt);
	}

	/**
	 * 取得現在之日期物件(<code>java.sql.Date</code>)
	 * 
	 * @return java.sql.Date
	 */
	public static java.sql.Date getCurrentSQLDate() {
		java.util.Date dt = new java.util.Date();

		return new java.sql.Date(dt.getTime());
	}

	/**
	 * 取得ISO日期格式字串，年、月、日之間以分隔子(<code>sLink</code>)連接
	 * 
	 * <pre>
	 * DateUtils.getISODateStr(null) = &quot;&quot;
	 * DateUtils.getISODateStr(new Date(), &quot;/&quot;) = &quot;yyyy/MM/dd&quot;
	 * </pre>
	 * 
	 * @param dt    日期物件
	 * @param sLink 分隔子
	 * @return 以分隔子的ISO格式字串，如果傳入日期為<code>null</code>，則回傳空字串
	 */
	public static String getISODateStr(java.util.Date dt, String sLink) {
		if (null == dt) {
			return "";
		}

		Calendar ctime = Calendar.getInstance();
		ctime.setTime(dt);

		StringBuffer sb = new StringBuffer();
		sb.append(ctime.get(Calendar.YEAR));
		sb.append(sLink);
		String sMonth = String.valueOf(ctime.get(Calendar.MONTH) + 1);
		sMonth = StringUtils.leftPad(sMonth, 2, "0");
		sb.append(sMonth);
		sb.append(sLink);
		String sDay = String.valueOf(ctime.get(Calendar.DAY_OF_MONTH));
		sDay = StringUtils.leftPad(sDay, 2, "0");
		sb.append(sDay);

		return sb.toString();
	}

	/**
	 * 將ISO Date字串轉換成 java.util.Date
	 * 
	 * <pre>
	 * DateUtils.getISODate(&quot;2005-10-10&quot;, &quot;-&quot;) = Date(2005 - 10 - 10)
	 * </pre>
	 * 
	 * @param sISODate ISO Date字串
	 * @param sLink
	 * @return
	 */
	public static java.util.Date getISODate(String sISODate, String sLink) {
		if (StringUtils.isBlank(sISODate)) {
			return null;
		}
		java.util.Date dt = null;
		// 有分隔符號
		if (StringUtils.isNotBlank(sLink)) {
			String[] tokens = sISODate.split(sLink);

			if (tokens.length == 3) {

				int iYear = ConvertUtils.str2Int(tokens[0]);
				int iMonth = ConvertUtils.str2Int(tokens[1]);
				int iDay = ConvertUtils.str2Int(tokens[2]);

				dt = DateUtils.getDate(iYear, iMonth, iDay);
			}
		}
		// 沒有分隔符號 YYYYMMDD
		else {

			if (sISODate.length() == 8) {
				int iYear = ConvertUtils.str2Int(sISODate.substring(0, 4));
				int iMonth = ConvertUtils.str2Int(sISODate.substring(4, 6));
				int iDay = ConvertUtils.str2Int(sISODate.substring(6, 8));
				dt = DateUtils.getDate(iYear, iMonth, iDay);
			}
		}
		return dt;
	}

	/**
	 * 將ISO Date字串轉換成 java.util.Date
	 * 
	 * <pre>
	 * DateUtils.getISODate(&quot;2005-10-10&quot;) = Date(2005 - 10 - 10)
	 * </pre>
	 * 
	 * @param sISODate 日期字串
	 * @return java.util.Date
	 */
	public static java.util.Date getISODate(String sISODate) {
		return getISODate(sISODate, ISO_LINK);
	}

	/**
	 * 根據西元年/月/日取得<code>java.util.Date</code>物件
	 * 
	 * @param iYear  西元年
	 * @param iMonth 月
	 * @param iDay   日
	 * @return java.util.Date
	 */
	public static java.util.Date getDate(int iYear, int iMonth, int iDay) {

		if (iYear <= 0 || iMonth <= 0 || iDay <= 0) {
			return null;
		}
		// TODO:資料檢核
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, iYear);
		calendar.set(Calendar.MONTH, iMonth - 1);
		calendar.set(Calendar.DAY_OF_MONTH, iDay);
		return calendar.getTime();
	}

	/**
	 * 根據西元年/月/日 時/分/秒取得<code>java.util.Date</code>物件
	 * 
	 * @param iYear  西元年
	 * @param iMonth 月
	 * @param iDay   日
	 * @param iHour  時 (24小時制)
	 * @param iMin   分
	 * @param iSec   表
	 * @return java.util.Date
	 */
	public static java.util.Date getDateTime(int iYear, int iMonth, int iDay, int iHour, int iMin, int iSec) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		// TODO:資料檢核
		calendar.set(Calendar.YEAR, iYear);
		calendar.set(Calendar.MONTH, iMonth - 1);
		calendar.set(Calendar.DAY_OF_MONTH, iDay);
		calendar.set(Calendar.HOUR_OF_DAY, iHour);
		calendar.set(Calendar.MINUTE, iMin);
		calendar.set(Calendar.SECOND, iSec);

		return calendar.getTime();
	}

	/**
	 * 取得民國格式的日期字串，以分隔子連接年、月、日
	 * 
	 * <pre>
	 * DateUtils.getROCDateStr(new Date(), &quot;-&quot;) = &quot;yyy-MM-dd&quot;
	 * </pre>
	 * 
	 * @param dt    日期物件
	 * @param sLink 分隔子
	 * @return 民國日期字串
	 */
	public static String getROCDateStr(java.util.Date dt, String sLink) {
		if (dt == null) {
			return "";
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);

		// Date
		int iYear = calendar.get(Calendar.YEAR) - 1911;
		String sYear = String.valueOf(iYear);
		sYear = StringUtils.leftPad(sYear, 3, "0");

		int iMonth = calendar.get(Calendar.MONTH) + 1;
		String sMonth = String.valueOf(iMonth);
		sMonth = StringUtils.leftPad(sMonth, 2, "0");

		int iDay = calendar.get(Calendar.DAY_OF_MONTH);
		String sDay = String.valueOf(iDay);
		sDay = StringUtils.leftPad(sDay, 2, "0");

		StringBuffer sb = new StringBuffer();
		sb.append(sYear).append(sLink).append(sMonth).append(sLink).append(sDay);

		return sb.toString();
	}

	/**
	 * 將民國年字串轉換成日期物件(code>java.util.Date</code>)
	 * 
	 * <pre>
	 * DateUtils.getROCDate(&quot;095/10/10&quot;, &quot;/&quot;) = Date(2005 - 10 - 10)
	 * </pre>
	 * 
	 * @param sRocDate 民國年字串
	 * @param sLink    分隔子
	 * @return java.util.Date
	 */
	public static java.util.Date getROCDate(String sRocDate, String sLink) {
		if (StringUtils.isBlank(sRocDate)) {
			return null;
		}
		Date dt = null;
		// 有分隔符號
		if (StringUtils.isNotBlank(sLink)) {

			String[] tokens = sRocDate.split(sLink);

			if (tokens.length != 3) {
				return null;
			}

			int iYear = ConvertUtils.str2Int(tokens[0]) + 1911;
			int iMonth = ConvertUtils.str2Int(tokens[1]);
			int iDay = ConvertUtils.str2Int(tokens[2]);

			dt = DateUtils.getDate(iYear, iMonth, iDay);
		}
		// 沒有分隔符號
		else {
			int iLen = sRocDate.length();
			// ccccMMdd/ cccMMdd / ccMMdd
			if (iLen == 8 || iLen == 7 || iLen == 6) {
				int iDay = ConvertUtils.str2Int(sRocDate.substring(iLen - 2));
				int iMonth = ConvertUtils.str2Int(sRocDate.substring(iLen - 4, iLen - 2));
				int iYear = ConvertUtils.str2Int(sRocDate.substring(0, iLen - 4));

				// 太小的民國年不合理，應該是民國100年後
				if (iYear > 20) {
					iYear += 1911;
					dt = DateUtils.getDate(iYear, iMonth, iDay);
				} else {
					iYear += 2011;
					dt = DateUtils.getDate(iYear, iMonth, iDay);
				}
			}
		}
		return dt;
	}

	/**
	 * 取得ISO格式的時間字串
	 * 
	 * <pre>
	 * DateUtils.getISOTimeStr(null) = &quot;&quot;
	 * DateUtils.getISOTimeStr(new Date()) = &quot;HH:mm:ss&quot;
	 * </pre>
	 * 
	 * @param dt 日期物件
	 * @return ISO格式的時間字串，如果傳入日期為<code>null</code>，則回傳空字串
	 */
	public static String getISOTimeStr(java.util.Date dt) {
		if (null == dt) {
			return "";
		}

		// String sFormat = DateFormatUtils.ISO_TIME_FORMAT.getPattern();
		String sFormat = DateFormatUtils.ISO_TIME_NO_T_FORMAT.getPattern();
		return DateFormatUtils.format(dt, sFormat);
	}

	/**
	 * 取得ISO格式的日期時間字串
	 * 
	 * <pre>
	 * DateUtils.getISODateTimeStr(new Date()) = &quot;yyyy-MM-ddTHH:mm:ss&quot;
	 * </pre>
	 * 
	 * @param dt 日期物件
	 * @return 如果傳入日期為<code>null</code>，則回傳空字串
	 */
	public static String getISODateTimeStr(java.util.Date dt) {
		if (null == dt) {
			return "";
		}

		String sFormat = DateFormatUtils.ISO_DATETIME_FORMAT.getPattern();
		return DateFormatUtils.format(dt, sFormat);
	}

	/**
	 * 取得SQL格式的日期時間字串
	 * 
	 * <pre>
	 * DateUtils.getSQLDateTimeStr(new Date()) = &quot;yyyy-MM-dd HH:mm:ss&quot;
	 * </pre>
	 * 
	 * @param dt 日期物件
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
	 * 取得SQL格式的日期字串
	 * 
	 * <pre>
	 * DateUtils.getSQLDateStr(new Date()) = &quot;yyyy-MM-dd&quot;
	 * </pre>
	 * 
	 * @param dt 日期物件
	 * @return 如果傳入日期為<code>null</code>，則回傳空字串
	 */
	public static String getSQLDateStr(java.util.Date dt) {
		if (null == dt) {
			return "";
		}

		String sFormat = DateFormatUtils.SQL_DATE_FORMAT.getPattern();
		return DateFormatUtils.format(dt, sFormat);
	}

	/**
	 * 取得簡易ISO格式的日期格式字串
	 * 
	 * <pre>
	 * DateUtils.getSimpleISODateStr(new Date()) = &quot;20071220&quot;
	 * </pre>
	 * 
	 * @param dt 日期物件
	 * @return 簡易的日期格式字串
	 * 
	 */
	public static String getSimpleISODateStr(java.util.Date dt) {
		if (null == dt) {
			return "";
		}

		String sFormat = DateFormatUtils.SIMPLE_ISO_DATE_FORMAT.getPattern();
		return DateFormatUtils.format(dt, sFormat);
	}

	/**
	 * 取得簡易ISO格式的時間格式字串
	 * 
	 * <pre>
	 * DateUtils.getSimpleISOTimeStr(new Date()) = &quot;132001&quot;
	 * </pre>
	 * 
	 * @param dt 日期物件
	 * @return 簡易的ISO時間格式字串，日期錯誤時回傳空字串
	 * 
	 */
	public static String getSimpleISOTimeStr(java.util.Date dt) {
		if (null == dt) {
			return "";
		}

		String sFormat = DateFormatUtils.SIMPLE_ISO_TIME_FORMAT.getPattern();
		return DateFormatUtils.format(dt, sFormat);
	}

	/**
	 * 取得簡易ISO格式的日期和時間字串
	 * 
	 * <pre>
	 * DateUtils.getSimpleISODateTimeStr(new Date()) = &quot;yyyyMMddTHHmmss&quot;
	 * </pre>
	 * 
	 * @param dt 日期物件
	 * @return 簡易ISO格式的日期和時間字串
	 */
	public static String getSimpleISODateTimeStr(java.util.Date dt) {
		if (null == dt) {
			return "";
		}

		String sFormat = DateFormatUtils.SIMPLE_ISO_DATETIME_FORMAT.getPattern();
		return DateFormatUtils.format(dt, sFormat);
	}

	public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	/**
	 * 取得簡易格式的日期
	 * 
	 * <pre>
	 * DateUtils.getSimpleDate(&quot;yyyyMMdd&quot) = Date(yyyy/MM/dd);
	 * </pre>
	 * 
	 * @param simpleDate
	 * @return
	 */
	public static Date getSimpleDate(String simpleDate) {
		if (null == simpleDate) {
			return null;
		}

		try {
			
			String sFormat = DateFormatUtils.SIMPLE_DATE_FORMAT.getPattern();

			SimpleDateFormat format = new SimpleDateFormat(sFormat);

			Date dt = format.parse(simpleDate);

			return dt;
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 取得 日期時間格式 yyyy/mm/dd hh:mm:ss
	 * @param date
	 * @return
	 */
	public static Date getDateTime(String date) {
		
		if (null == date) {
			return null;
		}
		
		try {
			
			String sFormat = DateFormatUtils.SIMPLE_DATETIME_FORMAT.getPattern();
			
			SimpleDateFormat format = new SimpleDateFormat(sFormat);

			Date dt = format.parse(date);

			return dt;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得簡易格式的日期時間(至秒)字串
	 * 
	 * <pre>
	 * DateUtils.getSimpleDateTimeStr(new Date()) = &quot;yyyyMMddHHmmss&quot;
	 * </pre>
	 * 
	 * @param dt
	 * @return
	 */
	public static String getSimpleDateTimeStr(java.util.Date dt) {
		if (null == dt) {
			return "";
		}

		String sFormat = DateFormatUtils.SIMPLE_DATETIME_FORMAT.getPattern();
		return DateFormatUtils.format(dt, sFormat);
	}

	/**
	 * 取得簡易格式的日期時間(至毫秒)字串
	 * 
	 * <pre>
	 * DateUtils.getSimpleDateTimeMillisStr(new Date()) = &quot;yyyyMMddHHmmssSSS&quot;
	 * </pre>
	 * 
	 * @param dt
	 * @return
	 */
	public static String getSimpleDateTimeMillisStr(java.util.Date dt) {
		if (null == dt) {
			return "";
		}

		String sFormat = DateFormatUtils.SIMPLE_DATETIME_MS_FORMAT.getPattern();
		return DateFormatUtils.format(dt, sFormat);
	}

	/**
	 * 取得簡易格式的日期字串
	 * 
	 * <pre>
	 * DateUtils.getSimpleDateStr(new Date()) = &quot;yyyyMMdd&quot;
	 * </pre>
	 * 
	 * @param dt
	 * @return
	 */
	public static String getSimpleDateStr(java.util.Date dt) {
		if (null == dt) {
			return "";
		}

		String sFormat = DateFormatUtils.SIMPLE_DATE_FORMAT.getPattern();
		return DateFormatUtils.format(dt, sFormat);
	}

	/**
	 * 取得簡易格式的現在日期時間(至毫秒)字串
	 * 
	 * <pre>
	 * DateUtils.getCurrentTimeMillis() = &quot;yyyyMMddHHmmssSSS&quot;
	 * </pre>
	 * 
	 * @return 簡易格式的現在日期時間(至毫秒)
	 */
	public static String getSimpleCurrentTimeMillis() {
		String sFormat = DateFormatUtils.SIMPLE_DATETIME_MS_FORMAT.getPattern();
		return DateFormatUtils.format(new Date(), sFormat);
	}

	/**
	 * 取得現在日期時間並依據傳入格式進行格式化
	 * 
	 * <pre>
	 * DateUtils.getNowMillisFormated() = &quot;yyyy/MM/dd HH:mm:ss.SSS&quot;
	 * </pre>
	 * 
	 * @param pattern 格式
	 * @return 格式化日期時間
	 */
	public static String getCurrentTimeMillis(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * 取得格式化的現在日期時間(至毫秒)字串
	 * 
	 * <pre>
	 * DateUtils.getSimpleRocDate() = &quot;cccMMdd&quot;
	 * </pre>
	 * 
	 * @param dt
	 * @return
	 */
	public static String getSimpleRocDate() {
		return DateFormatUtils.format(new Date(), DateFormatUtils.SIMPLE_ROC_DATE_FORMAT);
	}

	/**
	 * 取得格式化的現在日期時間(至毫秒)字串
	 * 
	 * <pre>
	 * DateUtils.getRocDate() = &quot;ccc/MM/dd&quot;
	 * </pre>
	 * 
	 * @param dt
	 * @return
	 */
	public static String getRocDate() {
		return DateFormatUtils.format(new Date(), DateFormatUtils.ROC_DATE_FORMAT);
	}

	/**
	 * 依據傳入 time1 與 time2 計算統計秒數
	 * 
	 * @param time1 起始時間
	 * @param time2 結束時間
	 * @return
	 */
	public static BigDecimal countSeconds(long time1, long time2) {

		BigDecimal basetime = new BigDecimal(1000L);
		BigDecimal totalCost = new BigDecimal(time2 - time1);
		return totalCost.divide(basetime);
	}

	/**
	 * 增加/減少年數
	 * 
	 * @param source 原始日期時間
	 * @param years  加減年數
	 * @return 返回計算後日期物件
	 */
	public static Date addYears(Date source, int years) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(source);
		calendar.add(Calendar.YEAR, years);

		return calendar.getTime();
	}

	/**
	 * 增加/減少月數
	 * 
	 * @param source 原始日期時間
	 * @param months 加減月數
	 * @return 返回計算後日期物件
	 */
	public static Date addMonths(Date source, int months) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(source);
		calendar.add(Calendar.MONTH, months);

		return calendar.getTime();
	}

	/**
	 * 增加/減少天數
	 * 
	 * @param source 原始日期時間
	 * @param days   加減天數
	 * @return 返回計算後日期物件
	 */
	public static Date addDays(Date source, int days) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(source);
		calendar.add(Calendar.DAY_OF_MONTH, days);

		return calendar.getTime();
	}

	/**
	 * 增加/減少小時數
	 * 
	 * @param source 原始日期時間
	 * @param hours  加減小時數
	 * @return 返回計算後日期物件
	 */
	public static Date addHours(Date source, int hours) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(source);
		calendar.add(Calendar.HOUR_OF_DAY, hours);

		return calendar.getTime();
	}

	/**
	 * 增加/減少分鐘數
	 * 
	 * @param source  原始日期時間
	 * @param minutes 加減分鐘數
	 * @return 返回計算後日期物件
	 */
	public static Date addMinutes(Date source, int minutes) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(source);
		calendar.add(Calendar.MINUTE, minutes);

		return calendar.getTime();
	}

	/**
	 * 增加/減少秒數
	 * 
	 * @param source  原始日期時間
	 * @param seconds 加減秒數
	 * @return 返回計算後日期物件
	 */
	public static Date addSeconds(Date source, int seconds) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(source);
		calendar.add(Calendar.SECOND, seconds);

		return calendar.getTime();
	}

	/**
	 * 字串轉Date(不支援民國年)
	 * 
	 * @param source  日期時間字串
	 * @param pattern 日期時間格式
	 * @return Date物件
	 * @throws UtilException
	 */
	public static Date strToDate(String datreStr, String pattern) throws UtilException {

		if (StringUtils.isAnyBlank(datreStr, pattern)) {
			throw new UtilException("Iillegal parameters!");
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		Date date = null;
		try {

			date = dateFormat.parse(datreStr);

		} catch (ParseException e) {
			throw new UtilException(e);
		}

		return date;
	}

	/**
	 * 民國年字串轉Date(不支援民國年)
	 * 
	 * <pre>
	 * DateUtils.strROCToDate(&quot;111/4/16&quot;, &quot;ccc/MM/dd&quot;) = Date(2022 - 4 - 15)
	 * DateUtils.strROCToDate(&quot;111/4/16 12:35:45.123&quot;, &quot;ccc/MM/dd HH:mm:ss.SSS&quot;) = DateFormat.parse(&quot;2022/4/16 12:35:45.123&quot;)
	 * </pre>
	 * 
	 * @param source  日期時間字串
	 * @param pattern 日期時間格式
	 * @return Date物件
	 * @throws UtilException
	 */
	public static Date strROCToDate(String datreStr, String pattern) {

		if (StringUtils.isAnyBlank(datreStr, pattern)) {
			return null;
		}
		
		int yearStartPos = StringUtils.indexOf(pattern, "c");
		int yearEndStartPos = StringUtils.lastIndexOf(pattern, "c");
		if (yearStartPos < 0 || yearEndStartPos < 0) {
			return null;
		}

		String yearRocStr = datreStr.substring(yearStartPos, yearEndStartPos + 1);
		String yearStr = String.valueOf(Integer.parseInt(yearRocStr) + 1911);
		datreStr = StringUtils.replace(datreStr, yearRocStr, yearStr);
		pattern = pattern.replace("cccc", "yyyy").replace("ccc", "yyyy");

		DateFormat dateFormat = new SimpleDateFormat(pattern);
		Date date = null;
		try {

			date = dateFormat.parse(datreStr);

		} catch (ParseException e) {
			return null;
		}

		return date;
	}
	
	/**
	 * 將日期字串 "yyyyMMdd" 轉換 Date 物件
	 * @param date
	 * @return Date or null
	 */
	public static Date toSimpleDate(String date) {
		
		try {
			
			Date simpleDate  = strToDate(date, DateFormatUtils.SIMPLE_DATE_FORMAT.getPattern());

			return simpleDate;
			
		} catch (UtilException e) {
			// do nothing
		}
		
		return null;
	}

	/**
	 * 清除物件中之各時間欄位值，僅保留日期欄位
	 * @param calendar
	 */
	public static void clearTime(Calendar calendar) {
		if (null == calendar) {
			return;
		}

		int iYear = calendar.get(Calendar.YEAR);
		int iMonth = calendar.get(Calendar.MONTH);
		int iDay = calendar.get(Calendar.DAY_OF_MONTH);

		calendar.clear();

		calendar.set(Calendar.YEAR, iYear);
		calendar.set(Calendar.MONTH, iMonth);
		calendar.set(Calendar.DAY_OF_MONTH, iDay);
	}
	
	/**
	 * 將DateTimeStr字串轉換成 java.util.Date
	 * 
	 * <pre>
	 * DateUtils.getDateByDateFormat(&quot;20051010123456789&quot;,&quot;yyyyMMddHHmmssSSS&quot;) = Date(2005-10-10 12:34:56789)
	 * </pre>
	 * 
	 * <p/>
	 * 異常ㄧ律回傳null
	 * 
	 * @param sSimpleDateTimeStr
	 * @return
	 */
	public static Date getDateByDateFormat(String sDateTimeStr, String sDateFormat) {
		try {
			SimpleDateFormat aDateFormat = new SimpleDateFormat(sDateFormat);
			return aDateFormat.parse(sDateTimeStr);
		}
		catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 取得指定格式的日期時間字串
	 * 
	 * @param dt
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date dt, String pattern) {
		if (null == dt) {
			return "";
		}

		return DateFormatUtils.format(dt, pattern);
	}
	
	/**
	 * 將時間字串"日月年"轉換為"年月日"
	 * 
	 * <pre>
	 * DateUtils.getBanCsDate(24052022)=&quot;20220524&quot;
	 * 
	 * @param banCsDate
	 * @return
	 */
	public static String getBanCsDate(String banCsDate) {

		return StringUtils.substring(banCsDate, 4, banCsDate.length()) + StringUtils.substring(banCsDate, 2, 4) + StringUtils.substring(banCsDate, 0, 2);

	}
	
	/**
	 * 將 Date物件轉換成String
	 * 
	 * <pre>
	 * DateUtils.getDateTimeStrByDateFormat(Date(2005-10-10 12:34:56789),&quot;yyyyMMddHHmmssSSS&quot;) = 20051010123456789
	 * </pre>
	 * 
	 * <p/>
	 * 異常ㄧ律回傳空字串
	 * 
	 * @param aDt
	 * @param sDateTimeFormat
	 * @return
	 */
	public static String getDateTimeStrByDateFormat(Date aDt, String sDateTimeFormat) {
		try {
			SimpleDateFormat aDateTimeFormat = new SimpleDateFormat(sDateTimeFormat);
			return aDateTimeFormat.format(aDt);
		}
		catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * 取得兩日期差幾日(有清除時分秒)
	 * 
	 * @param txDate
	 * @param eDate
	 * @return
	 */
	public static int calDateSub(Date sDate, Date eDate) {

		Calendar a = Calendar.getInstance();
		a.setTime(sDate);
		DateUtils.clearTime(a);
		Date d1 = a.getTime();

		Calendar b = Calendar.getInstance();
		b.setTime(eDate);
		DateUtils.clearTime(b);
		Date d2 = b.getTime();

		long daterange = d1.getTime() - d2.getTime();
		long time = 1000 * 3600 * 24; // A day in milliseconds

		long diff = daterange / time;

		return (int) diff;

	}
	
	/**
	 * 將dd/MM/yyyy的字串，轉換為yyyymmdd
	 * 
	 * 2022/08/01 B-Frank新增
	 * 
	 * @param inDate
	 * @return
	 */
	public static String reverseDateFormat(String inDate) {
		SimpleDateFormat inSDF = new SimpleDateFormat(DateFormatUtils.CE_DATE_REVERSE_FORMAT.getPattern());
		SimpleDateFormat outSDF = new SimpleDateFormat(DateFormatUtils.SIMPLE_DATE_FORMAT.getPattern());
	    String outDate = "";
	    if (inDate != null) {
	        try {
	            Date date = inSDF.parse(inDate);
	            outDate = outSDF.format(date);
	        } catch (ParseException ex){ 
	        	return outDate;
	        }
	    }
	    return outDate;
	}
	
	/**
	 * 將dd/MM/yyyy的字串，轉換為yyyy/mm/dd
	 * 
	 * 2022/08/01 B-Frank新增
	 * 
	 * @param inDate
	 * @return
	 */
	public static String reverseDateFormatAddSlash(String inDate) {
		SimpleDateFormat inSDF = new SimpleDateFormat(DateFormatUtils.CE_DATE_REVERSE_FORMAT.getPattern());
		SimpleDateFormat outSDF = new SimpleDateFormat(DateFormatUtils.CE_DATE_FORMAT.getPattern());
	    String outDate = "";
	    if (inDate != null) {
	        try {
	            Date date = inSDF.parse(inDate);
	            outDate = outSDF.format(date);
	        } catch (ParseException ex){ 
	        	return outDate;
	        }
	    }
	    return outDate;
	}
	
	/**
	 * 取得當前系統日期
	 * @return yyyyMMdd
	 */
	public static String getSimpleDate() {
		
		SimpleDateFormat format = new SimpleDateFormat(DateFormatUtils.SIMPLE_DATE_FORMAT.getPattern());
		
		return format.format(now());
	}
	
	/**
	 * 取得指定年份系統日期
	 * @param years 加減年數
	 * @return yyyyMMdd
	 */
	public static String getSimpleDateByYears(int years) {
		
		SimpleDateFormat format = new SimpleDateFormat(DateFormatUtils.SIMPLE_DATE_FORMAT.getPattern());
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now());
		calendar.add(Calendar.YEAR, years);
		
		return format.format(calendar.getTime());
	}
	
	/**
	 * 取得指定月份系統日期
	 * @param months 加減月數
	 * @return yyyyMMdd
	 */
	public static String getSimpleDateByMonths(int months) {
		
		SimpleDateFormat format = new SimpleDateFormat(DateFormatUtils.SIMPLE_DATE_FORMAT.getPattern());
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now());
		calendar.add(Calendar.MONTH, months);
		
		return format.format(calendar.getTime());
	}

	/**
	 * 取得指定日期系統日期
	 * @param days 加減天數
	 * @return yyyyMMdd
	 */
	public static String getSimpleDateByDays(int days) {
		
		SimpleDateFormat format = new SimpleDateFormat(DateFormatUtils.SIMPLE_DATE_FORMAT.getPattern());
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now());
		calendar.add(Calendar.DAY_OF_MONTH, days);
		
		return format.format(calendar.getTime());
	}
	
	/**
	 * 取得當前系統時間
	 * @return yyyyMMddHHmmss
	 */
	public static String getSimpleDatetime() {
		
		SimpleDateFormat format = new SimpleDateFormat(DateFormatUtils.SIMPLE_DATETIME_FORMAT.getPattern());
		
		return format.format(now());
	}
	
	/**
	 * 取得指定日期系統日期
	 * @param minutes 加減分鐘
	 * @return yyyyMMddHHmmss
	 */
	public static String getSimpleDateTimeByMinutes(int minutes) {
		
		SimpleDateFormat format = new SimpleDateFormat(DateFormatUtils.SIMPLE_DATETIME_FORMAT.getPattern());
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now());
		calendar.add(Calendar.MINUTE, minutes);
		
		return format.format(calendar.getTime());
	}
	
	/**
	 * 清除日期 HH:MM:SS
	 * @param startDate
	 * @return Date or null
	 */
	public static Date getStartDate000000(Date startDate) {
		
		if (startDate == null) {
			return null;
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime((Date)startDate.clone());
		
		clearTime(cal);
		
		return cal.getTime();
	}
	
	/**
	 * 設定日期時間 yyyy/mm/dd 23:59:59 999
	 * @param startDate
	 * @return Date or null
	 */
	public static Date getEndDate235959(Date endDate) {
		
		if (endDate == null) {
			return null;
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(endDate);
		
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		
		return cal.getTime();
	}
}