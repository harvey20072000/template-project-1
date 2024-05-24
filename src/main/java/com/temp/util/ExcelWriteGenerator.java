/**
 * @Description : Excel檔案寫出工具
 * @ClassName : ExcelWriteGenerator.java
 * @Copyright : Copyright (c) 2024 HARVEY All Rights Reserved.
 * @ModifyHistory : 
 *   v1.00, 2024/05/24, Harvey Liu
 *   1) First Release.
 */
package com.temp.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.temp.exception.BaseException;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ExcelWriteGenerator {

	private Workbook workbook;

	/** 儲存格樣式 */
	private CellStyle style;

	/** 資料表 */
	private Sheet sheet;

	public ExcelWriteGenerator(String templatePath) throws BaseException {

		InputStream in = null;

		try {
			in = new FileInputStream(templatePath);

			// 初始化
			this.workbook = new XSSFWorkbook(in);
			this.getSheet(0);
			this.getStyle(0, 0);

		} catch (FileNotFoundException e) {			
			log.error("");
			throw new BaseException(e);			
			
		} catch (IOException e) {	
			log.error("");
			throw new BaseException(e);
			
		} catch (Exception e) {
			log.error("");
			throw new BaseException(e);
			
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				
			} catch (IOException e) { }
		}
	}

	/**
	 * 加入要寫入指定單一儲存格的資料<br>
	 * <p>row、cell起始位置預設為0</p>
	 * @param rowNum  指定行號
	 * @param cellNum 指定儲存格號
	 * @param data    寫入的文字
	 */
	public void add(int rowNum, int cellNum, String data) {
		
		Row row = this.sheet.getRow(rowNum);

		if (row == null) {
			row = this.sheet.createRow(rowNum);
		}

		Cell cell = row.createCell(cellNum);
		
		if(StringUtils.contains(data, "\n")) {
			
            CellStyle style = this.style;
            style.setWrapText(true);
			cell.setCellStyle(style);
			cell.setCellValue(data);
		}
		else {
			
			cell.setCellStyle(this.style);
			cell.setCellValue(data);
		}
	}
	
	/***
	 * 使用原本樣式，寫入指定單一儲存格的資料<br>
	 * <p>row、cell起始位置預設為0</p>
	 * @param rowNum  指定行號
	 * @param cellNum 指定儲存格號
	 * @param data    寫入的文字
	 */
	public void useRawStyleToAdd(int rowNum, int cellNum, String data) {
		
		Row row = this.sheet.getRow(rowNum);
		if (row == null) {
			row = this.sheet.createRow(rowNum);
		}
		
		Cell cell = row.getCell(cellNum);
		if (cell == null) {
			cell = row.createCell(cellNum);
		}
		
		cell.setCellValue(data);
		
	}
	
	/***
	 * 複製上一列的樣式，寫入指定單一儲存格的資料<br>
	 * <p>row、cell起始位置預設為0</p>
	 * @param rowNum  指定行號
	 * @param cellNum 指定儲存格號
	 * @param data    寫入的文字
	 */
	public void copyStyleToAdd(int rowNum, int cellNum, String data) {
		
		Row row = this.sheet.getRow(rowNum);
		if (row == null) {
			row = this.sheet.createRow(rowNum);
		}
		
		Cell cell = row.getCell(cellNum);
		if (cell == null) {
			cell = row.createCell(cellNum);
		}
		
		CellStyle cellStyle = this.sheet.getRow(rowNum - 1).getCell(cellNum).getCellStyle();
		
		cell.setCellValue(data);
		cell.setCellStyle(cellStyle);
		
	}
	
	
	/**
	 * 加入要寫入指定單一儲存格的資料(是否有邊框)<br>
	 * <p>row、cell起始位置預設為0</p>
	 * @param rowNum  指定行號
	 * @param cellNum 指定儲存格號
	 * @param data    寫入的文字
	 */
	public void add(int rowNum, int cellNum, String data, boolean isBorder) {
		
		Row row = this.sheet.getRow(rowNum);

		if (row == null) {
			row = this.sheet.createRow(rowNum);
		}
		
		CellStyle cellStyle = this.style;
		
		if(isBorder) {
			cellStyle=(XSSFCellStyle) this.workbook.createCellStyle();
			cellStyle.setBorderBottom(BorderStyle.THIN);
			cellStyle.setBorderLeft(BorderStyle.THIN);
			cellStyle.setBorderRight(BorderStyle.THIN);
			cellStyle.setBorderTop(BorderStyle.THIN);
		}

		Cell cell = row.createCell(cellNum);
		
		if(StringUtils.contains(data, "\n")) {
			
			cellStyle.setWrapText(true);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(data);
		}
		else {
			
			cell.setCellStyle(cellStyle);
			cell.setCellValue(data);
		}
	}

	
	/**
	 * 指定行號如有資料，則往下移一行
	 * @param rowNum 指定行號
	 */
	public void insert(int rowNum) {
		if (hasData(rowNum)) {
			this.sheet.shiftRows(rowNum, this.sheet.getLastRowNum(), 1);
			
		}
	}

	/**
	 * 取得Excel檔案Base64字串
	 * @throws BaseException 
	 * @return String
	 */
	public String getBase64() throws BaseException {

		String file64 = "";

		ByteArrayOutputStream out = null;

		try {
			out = new ByteArrayOutputStream();
			this.workbook.write(out);

			file64 = Base64.getEncoder().encodeToString(out.toByteArray());
			
		} catch (IOException e) {
			log.error("");
			throw new BaseException(e);
			
		} catch (Exception e) {
			log.error("");
			throw new BaseException(e);
			
		} finally {
			try {
				if (out != null) {
					workbook.close();
					out.flush();
					out.close();
					
				}
			} catch (IOException e) { }
		}
		return file64;
		
	}

	/**
	 * 下載Excel檔案到指定路徑
	 * @param outputPath 檔案輸出路徑
	 * @exception BaseException
	 */
	public void getFile(String outputPath) throws BaseException {

		FileOutputStream out = null;

		try {
			out = new FileOutputStream(new File(outputPath));
			this.workbook.write(out);
			
		} catch (IOException e) {
			log.error("");
			throw new BaseException(e);
			
		} catch (Exception e) {
			log.error("");
			throw new BaseException(e);
			
		} finally {
			try {
				if (out != null) {
					this.workbook.close();
					out.flush();
					out.close();
					
				}
			} catch (IOException e) { }
		}
	}

	/**
	 * 指定要套用的儲存格樣式
	 * @param rowNum  指定行號
	 * @param cellNum 指定儲存格號
	 * @return CellStyle
	 */
	public void getStyle(int rowNum, int cellNum) {
		this.style = this.sheet.getRow(rowNum).getCell(cellNum).getCellStyle();	
	}

	/**
	 * 指定要使用的資料表 
	 * @param sheetNum 資料表
	 * @return Sheet
	 */
	public void getSheet(int sheetNum) {
		this.sheet = this.workbook.getSheetAt(sheetNum);
	}
	
	/**
	 * 判斷指定行數是否有資料
	 * @param rowNum 指定行號
	 * @return boolean
	 */
	private boolean hasData(int rowNum) {

		Row row = this.sheet.getRow(rowNum);

		if (row == null) {
			return false;
			
		}

		for (int i = 0; i < row.getLastCellNum(); i++) {
			Cell cell = row.getCell(i);
			
			if (cell != null && cell.getCellType() != CellType.BLANK) {
				return true;
				
			}
		}

		return false;
	}

	public byte[] getContentBytes() throws IOException {
		
		ByteArrayOutputStream baos = null;

		try {
			baos = new ByteArrayOutputStream();
			
			this.workbook.write(baos);

			byte[] bytes = baos.toByteArray();
			
			return bytes;
			
		} catch (Exception e) {
			
			throw e;
			
		} finally {
			baos.flush();
			baos.close();
		}
	}
}

