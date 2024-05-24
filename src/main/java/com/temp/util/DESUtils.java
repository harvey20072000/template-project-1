/**
 * @Description : 資料加解密工具
 * @ClassName : DESUtils.java
 * @Copyright : Copyright (c) 2024 Harvey.Liu All Rights Reserved.
 */
package com.temp.util;

import java.io.ObjectInputStream;
import java.security.Key;
import java.util.regex.Pattern;

import javax.crypto.Cipher;

import com.temp.exception.BaseException;

public class DESUtils {
	/** Key */
	// private static String MYKEY = "IBMGBS";
	/** a secret key */
	private static Key key = null;

	/** cipher */
	private static Cipher cipher = null;

	/**  */
	private static Pattern m_aPattern = Pattern.compile("~[a-fA-F0-9]*~");

	/**
	 * @throws Exception
	 */
	private static void init() throws BaseException {
		// KeyGenerator generator = KeyGenerator.getInstance("DES");
		// generator.init(new SecureRandom(MYKEY.getBytes()));
		//
		// key = generator.generateKey();
		try {
			key = (Key) new ObjectInputStream(DESUtils.class.getResourceAsStream("key")).readObject();
			// Get a cipher object
			cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		}
		catch (Exception e) {
			throw new BaseException("cannot init DESUtils", e);
		}
	}

	/**
	 * 將資料加密
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String str) throws BaseException {
		return ConvertUtils.byteArray2HexString(encrypt(str.getBytes()));
	}

	/**
	 * 將資料加密
	 * 
	 * @param bytes
	 * @return
	 * @throws Exception
	 */
	public static synchronized byte[] encrypt(byte[] bytes) throws BaseException {
		if (key == null) {
			init();
		}

		byte[] result = null;
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key);

			result = cipher.doFinal(bytes);
		}
		catch (Exception e) {
			throw new BaseException("cannot encrypt data, data = " + ConvertUtils.byteArray2HexString(bytes), e);
		}

		return result;
	}

	/**
	 * 將資料解密
	 * 
	 * @param hexString
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String hexString) throws BaseException {
		return new String(decrypt(ConvertUtils.hexString2ByteArray(hexString)));
	}

	/**
	 * 將資料解密
	 * 
	 * @param bytes
	 * @return
	 * @throws Exception
	 */
	public static synchronized byte[] decrypt(byte[] bytes) throws BaseException {
		if (key == null) {
			init();
		}
		byte[] data = null;

		try {
			cipher.init(Cipher.DECRYPT_MODE, key);

			data = cipher.doFinal(bytes);
		}
		catch (Exception e) {

			throw new BaseException("cannot decrypt, data = " + ConvertUtils.byteArray2HexString(bytes), e);
		}

		return data;
	}

	/**
	 * 是否為加入識別字的DES加密資料
	 * 
	 * @param hexString
	 */
	public static boolean isMaskDESEncrypt(String sHexString) {
		return m_aPattern.matcher(sHexString).matches();
	}

	/**
	 * 取得 去除識別字 資料加密
	 * 
	 * @param makeHexString
	 * @return
	 */
	public static String getEncrypt(String sMaskHexString) {
		return sMaskHexString.replaceAll("~", "");
	}

	/**
	 * 取得 加入識別字 資料加密
	 * 
	 * @param hexString
	 * @return
	 */
	public static String getMaskEncrypt(String sHexString) {
		return "~" + sHexString + "~";
	}

}
