/**
 * @Description : Excel 檔案內容 工具
 * @ClassName : ExcelUtils.java
 * @Copyright : Copyright (c) 2024 Harvey.Liu All Rights Reserved.
 */
package com.temp.util;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ExcelUtils {


	/**
	 * Excel查詢結果-條件資料-新增
	 * 1. 查詢條件為第一行開始
	 * 2. 查詢條件為每行只有兩個資料
	 * 
	 * @param writer 
	 * @param queryCondition 查詢條件
	 * @param separateRowNum 查詢條件與明細分隔行數
	 * @return int 明細起始行
	 */
	public static int setQueryCondition(ExcelWriteGenerator writer, Map<Integer, String> queryCondition, int separateRowNum) {
		
		// 從第0開始(第一行)
		int rowNum = 0;
		
		// 從第4開始(第5列，第一、二列為功能路徑)
		int cellNum = 4;
		
        for (Entry<Integer, String> entry : queryCondition.entrySet()) {
        	
        	if(cellNum == 4) {
        		writer.add(rowNum, cellNum, entry.getValue(), false);
        	} 
        	else {
        		writer.add(rowNum, cellNum, entry.getValue(), true);
        	}
            
        	
        	// 若該此次資料為列為3(第四列)，則將行數+1，列數初始為1(第二列)
        	if(cellNum == 3) {
        		rowNum++;
        		cellNum = 1;
        	}
        	// 若列數為4(第5列)，則跳行至第2(第3行)、列數1(第2列)
        	else if(cellNum == 4){
        		rowNum = 2;
        		cellNum = 1;	
			}
        	// 若列非為3(第四列)，則將列數+2(第四列)
        	else {
        		cellNum+=2;
        	}
        }
		
        // 將最後行數(查詢條件行)+，則為明細資料行第一行
        rowNum+=separateRowNum;
        
        if(cellNum == 1) {
        	rowNum-=1;
        }
        
		return rowNum;
	}
	
	/**
	 * Excel查詢結果-明細資料-新增
	 * 1. Excel無末行
	 * 
	 * @param writer
	 * @param detail 明細內容
	 * @param startRowNum 明細起始位置
	 */
	public static int setDetail(ExcelWriteGenerator writer, Map<Integer, List<String>> detail, int startRowNum) {
		
		// 明細起始行位置
		int rowNum = startRowNum;
		
		for(Entry<Integer, List<String>> entry : detail.entrySet()) {
			
			// 每筆明細從0開始(第一列)
			int cellNum = 0;
			
			for (String value : entry.getValue()) {
				
				// 如果為第一行(row)開始，則直接寫入資料
				if (rowNum == startRowNum) {
					writer.useRawStyleToAdd(rowNum, cellNum, value);
				// 否則複製上一行(row)的樣式，寫入資料	
				} else {
					writer.copyStyleToAdd(rowNum, cellNum, value);
				}
				
				// 下一列
				cellNum++;
			}
			
			rowNum+=1;
		}
		
		rowNum++;
		
		return rowNum;
	}
	
	/**
	 * Excel查詢結果-條件資料-新增-沒有功能路徑
	 * 1. 查詢條件為兩行
	 * 2. 無邊框
	 * 
	 * @param writer 
	 * @param queryCondition 查詢條件
	 * @param separateRowNum 查詢條件與明細分隔行數
	 * @return int 明細起始行
	 */
	public static int setQueryConditionNoTaskRow(ExcelWriteGenerator writer, Map<Integer, String> queryCondition, int separateRowNum) {
		
		// 從第0開始(第一行)
		int rowNum = 0;
		
		// 從第4開始(第5列，第一、二列為功能路徑)
		int cellNum = 6;
		
        for (Entry<Integer, String> entry : queryCondition.entrySet()) {

        	writer.add(rowNum, cellNum, entry.getValue());
            
        	// 若該此次資料為列為3(第四列)，則將行數+1，列數初始為1(第二列)
        	if(cellNum == 5) {
        		rowNum++;
        		cellNum = 1;
        	}
        	// 若列數為4(第5列)，則跳行至第2(第3行)、列數1(第2列)
        	else if(cellNum == 4){
        		rowNum = 2;
        		cellNum = 1;	
			}
        	// 若列非為3(第四列)，則將列數+2(第四列)
        	else {
        		cellNum+=2;
        	}
        }
		
        // 將最後行數(查詢條件行)+，則為明細資料行第一行
        rowNum+=separateRowNum;
        
        if(cellNum == 1) {
        	rowNum-=1;
        }
        
		return rowNum;
	}

	/**
	 * Excel查詢結果-寫入小計
	 * 
	 * @param writer
	 * @param detailMap
	 * @param nowRowNum
	 * @param cellNum
	 */
	public static void setSubtotal(ExcelWriteGenerator writer, Map<Integer, List<String>> detailMap, int nowRowNum,
			int cellNum) {

		// 明細起始行位置
		int rowNum = nowRowNum -1;
				
		for(Entry<Integer, List<String>> entry : detailMap.entrySet()) {
			
			int nowCellNum = cellNum;
		
			for (String value : entry.getValue()) {
				
				writer.add(rowNum, nowCellNum, value, true);
				
				// 下一列
				nowCellNum++;
			}	
			rowNum+=1;
		}
		rowNum++;
	}

}