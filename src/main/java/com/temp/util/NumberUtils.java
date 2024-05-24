/**
 * @Description : 數字處理工具集
 * @ClassName : NumberUtils.java
 * @Copyright : Copyright (c) 2024 Harvey.Liu All Rights Reserved.
 */
package com.temp.util;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Date;

/**
 * @see {@link org.apache.commons.lang3.math.NumberUtils}
 *
 */
public class NumberUtils extends org.apache.commons.lang3.math.NumberUtils {
	/**
     * 根據有效位數轉換BigDecimal，採用四捨五入
     * <pre>
     * setScale(BigDecimal(12.24, 1)) = BigDecimal(12.2)
     * setScale(BigDecimal(12.25, 1)) = BigDecimal(12.3)
     * </pre>
     * 
     * @param sAmount
     * @param iScale
     * @return
     */    
    public static BigDecimal setScale(BigDecimal value, int iScale) {
        if ((null == value) || (iScale < 0)) {
            return null;
        }
        
        BigDecimal amount;
        // value=0 , BigDecimal.ROUND_HALF_UP結果會變成0E-8,故調整為0就直接回傳值.
        if (value.compareTo(BigDecimal.ZERO) == 0){
        	amount = value; 
        }else{
        	amount = value.setScale(iScale, BigDecimal.ROUND_HALF_UP);	
        }
        
        return amount;        
    }
    
    /**
	 * 產生亂數數字
	 * @param bound 上限(不包含), <=0表示不限制
	 * @return
	 */
    public static int randomInt(int bound) {
    	SecureRandom rand = new SecureRandom();
		rand.setSeed((new Date()).getTime());
		if(bound >0) {
			return rand.nextInt(bound);
		}else {
			return rand.nextInt();
		}
    }
    
    /**
	 * 產生亂數數字
	 * 
	 * @return
	 */
    public static int randomInt() {
		return randomInt(0);
    }
    
    
    public static void main(String[] args) {
		int a = randomInt(6);
		System.out.println(a);
	}
    
    /**
     * 計算查詢資料庫從第幾筆開始查詢
     * @param number 第幾個分頁
     * @param size 每一個分頁要顯示多少筆
     */
    public static Integer getDBStartPosition(Integer number, Integer size) {
    	
    	return size*(number-1)+1;
    }
}
