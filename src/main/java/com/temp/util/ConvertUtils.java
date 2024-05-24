/**
 * @Description : 型別轉換
 * @ClassName : ConvertUtils.java
 * @Copyright : Copyright (c) 2024 Harvey.Liu All Rights Reserved.
 */
package com.temp.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Calendar;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @see {@link org.apache.commons.beanutils.ConvertUtils}
 *
 */
public class ConvertUtils extends org.apache.commons.beanutils.ConvertUtils {
	public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
	/**
	 * convert java.util.Date to java.sql.Timestamp
	 * 
	 * @param dt Date物件
	 * @return java.sql.Timestamp物件
	 */
	public static java.sql.Timestamp date2Timestamp(java.util.Date dt) {
		if (dt == null) {
			return null;
		}

		return new Timestamp(dt.getTime());
	}
	
	/**
	 * 字串轉換成BigDecimal
	 * 
	 * <pre>
	 * 
	 * str2BigDecimal(null, 1, 0) = 0
	 * str2BigDecimal(&quot;AA&quot;, 1, 0) = 0
	 * str2BigDecimal(&quot;2.12&quot;, 1, 0) = 2.1
	 * str2BigDecimal(&quot;2.15&quot;, 1, 0) = 2.2
	 * </pre>
	 * 
	 * @param sValue 欲轉換的字串
	 * @param iScale 小數位數(<0 維持原小數位數)
	 * @param defaultValue 預設
	 * @return 採無條件捨去，發生異常時回傳預設值defaultValue, never null
	 */
	public static BigDecimal str2BigDecimal(String sValue, int iScale, BigDecimal defaultValue) {

		BigDecimal value = null;
		try {
			sValue = StringUtils.trim(sValue);
			sValue = StringUtils.remove(sValue, ",");

			value = new BigDecimal(StringUtils.trim(sValue));

			if (value.compareTo(BigDecimal.ZERO) == 0){
				value = defaultValue;
			}else{
				if (iScale >= 0) {

					value = value.setScale(iScale, BigDecimal.ROUND_HALF_UP);
				}
			}
		}
		catch (Exception e) {
			value = defaultValue;
		}
		return value;
	}
	
	/**
	 * 字串轉換成BigDecimal
	 * <ul>
	 * <li>str2BigDecimal(null) = 0</li>
	 * <li>str2BigDecimal("AA") = 0</li>
	 * <li>str2BigDecimal("2.12") = 2.12</li>
	 * </ul>
	 * 
	 * @param sValue 欲轉換的字串
	 * @return 維持原小數位數
	 */
	public static BigDecimal str2BigDecimal(String sValue) {
		return str2BigDecimal(sValue, -1, new BigDecimal(0));
	}

	/**
	 * convert string to big decimal
	 * <ul>
	 * <li>str2BigDecimal(null, 2) = 0</li>
	 * <li>str2BigDecimal("AA", 2) = 0</li>
	 * <li>str2BigDecimal("2.12", 1) = 2.1</li>
	 * <li>str2BigDecimal("2.15", 1) = 2.2</li>
	 * </ul>
	 * 
	 * @param sValue 欲轉換的字串
	 * @param iScale 小數位數(<0 維持原小數位數)
	 * @return 採無條件捨去，發生異常時回傳new BigDecimal(0), never null
	 */
	public static BigDecimal str2BigDecimal(String sValue, int iScale) {
		return str2BigDecimal(sValue, iScale, new BigDecimal(0));
	}
	
	/**
	 * 將字串轉換為int，如果轉換失敗則回傳預設值
	 * <ul>
	 * <li>ConvertUtils.str2Int(null) = 0</li>
	 * <li>ConvertUtils.str2Int("abc") = 0</li>
	 * <li>ConvertUtils.str2Int("1") = 1</li>
	 * </ul>
	 * 
	 * @param sValue
	 *            待轉換之字串
	 * @param iDefaultValue
	 *            轉換失敗時之預設值
	 * @return 轉換後之數字，失敗則傳回iDefaultValue
	 * 
	 */
	public static int str2Int(String sValue, int iDefaultValue) {

		sValue = StringUtils.trim(sValue);

		int iValue = iDefaultValue;
		try {
			iValue = Integer.parseInt(sValue);
		}
		catch (Exception e) {
			iValue = iDefaultValue;
		}

		return iValue;
	}

	/**
	 * 將字串轉成Integer
	 * 
	 * @param sValue
	 *            待轉換的字串
	 * @return 轉換後的數字，如果無法轉換時回傳0
	 */
	public static int str2Int(String sValue) {
		return ConvertUtils.str2Int(sValue, 0);
	}
	
	/**
	 * convert big decimal to string，四捨五入
	 * 
	 * <pre>
	 * bigDecimal2Str(null, 2, &quot;2&quot;) = &quot;2&quot;
	 * bigDecimal2Str(2.12, 1, &quot;&quot;) = &quot;2.1&quot;
	 * bigDecimal2Str(2.15, 1, &quot;&quot;) = &quot;2.2&quot;
	 * </pre>
	 * 
	 * @param value
	 *            待設定之BigDecimal Value
	 * @param iScale
	 *            小數有效位數(<0 維持原小數位數)
	 * @param sDefaultValue
	 *            預設值
	 * @return 採用四捨五入制，發生異常時回傳預設值sDefaultValue, never null
	 */
	public static String bigDecimal2Str(BigDecimal value, int iScale, String sDefaultValue) {
		if (null == value) {

			return (sDefaultValue != null) ? sDefaultValue : "";
		}

		BigDecimal amount = null;
		
		if (value.compareTo(BigDecimal.ZERO) == 0){
			amount = value;
		}else{
			if (iScale >= 0) {
				amount = NumberUtils.setScale(value, iScale);
			}
			else {
				amount = value;
			}
		}
		
		return amount.toString();
	}
	
	/**
	 * convert big decimal to string
	 * 
	 * <pre>
	 * bigDecimal2Str(null, 2) = &quot;&quot;
	 * bigDecimal2Str(2.12, 1) = &quot;2.1&quot;
	 * bigDecimal2Str(2.15, 1) = &quot;2.2&quot;
	 * </pre>
	 * 
	 * @param value
	 *            待設定之BigDecimal Value
	 * @param iScale
	 *            小數有效位數(<0 維持原小數位數)
	 * 
	 * @return 採用四捨五入制，發生異常時回傳預設值""
	 */
	public static String bigDecimal2Str(BigDecimal value, int iScale) {
		return bigDecimal2Str(value, iScale, "");
	}

	/**
	 * convert big decimal to string
	 * <ul>
	 * <li>bigDecimal2Str(null) = "0"</li>
	 * <li>bigDecimal2Str(2.12) = "2.12"</li>
	 * <li>bigDecimal2Str(2.15) = "2.15"</li>
	 * </ul>
	 * 
	 * @param value 待設定之BigDecimal Value
	 * @return 採用四捨五入制，發生異常時回傳預設值"0"
	 */
	public static String bigDecimal2Str(BigDecimal value) {
		return bigDecimal2Str(value, -1, "0");
	}
	
	/**
	 * Convert java.util.Date to java.util.Calendar
	 * 
	 * @param dt 待轉換java.util.Date
	 * @return java.util.Calendar
	 */
	public static Calendar date2Calendar(java.util.Date dt) {
		if (null == dt) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);

		return calendar;
	}
	
	/**
	 * Convert java.util.Canlendar to java.util.Date
	 * 
	 * @param calendar 待轉換java.util.Canlendar
	 * @return java.util.Date
	 */
	public static java.util.Date calendar2Date(Calendar calendar) {
		if (calendar != null) {
			return calendar.getTime();
		}
		else {
			return null;
		}
	}
	
	/**
	 * Convert java.util.Calendar to java.sql.Date
	 * 
	 * @param calendar 待轉換java.util.Canlendar
	 * @return java.sql.Date
	 */
	public static java.sql.Date calendar2SQLDate(Calendar calendar) {

		if (calendar == null) {
			return null;
		}

		java.util.Date dt = calendar2Date(calendar);

		if (dt != null) {
			return new java.sql.Date(dt.getTime());
		}
		else {
			return null;
		}
	}
	
	/**
	 *  convert bytes 2 str (不產生exception)
	 *  
	 * @param data
	 * @param encoding
	 * @return
	 */
	public static String bytes2Str(byte[] data, String encoding) {
		String str = "";
		
		try {
			str = new String(data, encoding);
		}
		catch (UnsupportedEncodingException e) {
		}
		return str;
	}
	
	/**
	 * 十六進位字串轉成 byte array
	 * 
	 * <pre>
	 * ConvertUtils.hexStringToByteArray(&quot;1234&quot;) = [0x12, 0x34]
	 * ConvertUtils.hexStringToByteArray(&quot;0x2e) = [0x2E]
	 * ConvertUtils.hexStringToByteArray(&quot;x'2e') = [0x2E]
	 * ConvertUtils.hexStringToByteArray(&quot;&quot;) = []
	 * ConvertUtils.hexStringToByteArray(&quot;1&quot;) = null
	 * ConvertUtils.hexStringToByteArray(&quot;gg&quot;) = null
	 * </pre>
	 * 
	 * @param sData
	 *            十六進位字串
	 * @return 十六進位所代表的Bye Array
	 */
	public static byte[] hexString2ByteArray(String sData) {

		if (StringUtils.isBlank(sData)) {
			return new byte[0];
		}

		// TODO: 待確認
		// x'2E' format => 2E
		if (sData.startsWith("x") || sData.startsWith("X")) {
			int start = sData.indexOf("'");
			int end = sData.lastIndexOf("'");
			sData = sData.substring(start + 1, end);
		}
		// delete leading "0x" or "0X"
		// "0x2f" => "2f
		else if (sData.startsWith("0x") || sData.startsWith("0X")) {
			sData = sData.substring(2);
		}

		if (sData.length() % 2 != 0) {
			return ArrayUtils.EMPTY_BYTE_ARRAY;
		}

		byte[] raw = new byte[sData.length() / 2];
	 
		try {
			for (int i = 0; i < sData.length() / 2; i++) {

				raw[i] = (byte) Integer.parseInt(sData.substring(i * 2, i * 2 + 2), 16);

			}
		}
		catch (Exception e) {

			raw = null;
		}
		return raw;
	}
	/**
	 * 將byte array資料轉為十六進位字串
	 * 
	 * <pre>
	 * ConvertUtils.byteArrayToHexString([0x01, 0x02]) = &quot;0102&quot;
	 * </pre>
	 * 
	 * @param raw
	 *            待轉換之bye array
	 * @return 十六進位字串
	 */
	public static String byteArray2HexString(byte[] raw) {
		StringBuffer sHex = new StringBuffer("");

		for (int i = 0; i < raw.length; i++) {

			sHex.append(byte2HexString(raw[i]));
		}
		return sHex.toString();
	}
	
	/**
	 * 將Byte資料轉換為十六進位字串
	 * 
	 * <pre>
	 * ConvertUtils.byte2Hex(0xab) = &quot;ab&quot;
	 * ConvertUtils.byte2Hex(0x01) = &quot;01&quot;
	 * </pre>
	 * 
	 * @param raw
	 * @return
	 */
	public static String byte2HexString(byte raw) {

		StringBuffer sHex = new StringBuffer("");
		int iByte = (raw & 0xF0) >> 4;
		// sHex.append(getHexChar(iByte));
		sHex.append(Character.forDigit(iByte, 16));
		iByte = (raw & 0x0F);
		sHex.append(Character.forDigit(iByte, 16));
		return sHex.toString();

		// String sHex = Integer.toHexString(raw).toUpperCase();

		// return StringUtils.leftPad(sHex, 2, "0");
	}
	
	/**
	 * convert str to bytes (不產生exception)
	 * 
	 * @param str
	 * @param encoding
	 * @return
	 */
	public static byte[] str2Bytes(String str, String encoding) {
		byte[] data = null;

		try {
			data = str.getBytes(encoding);
		}
		catch (UnsupportedEncodingException e) {
		}
		return data;
	}
}
