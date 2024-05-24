/**
 * @Description : 產生圖形驗證碼
 * @ClassName : RandomGraphic.java
 * @Copyright : Copyright (c) 2024 Harvey.Liu All Rights Reserved.
 */
package com.temp.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class RandomGraphic {

	/**
	 * @param args
	 */
	private static final int WORD_WIDTH = 20; // 字和字之間的間距

	private static final int BORDER_WIDTH = 10; // 邊寬

	private static final int MAX_CHARCOUNT = 16;

	private static final Color[] CHAR_COLOR = { // 顯示字體顏色對照表
	new Color(255, 0, 0), new Color(0, 0, 0), new Color(0, 190, 0), new Color(255, 80, 30), new Color(0, 0, 255), new Color(60, 30, 60), new Color(255, 120, 0), new Color(128, 20, 255), new Color(10, 120, 60) };

	private static int IMAGE_HEIGHT = 40; // 圖檔高度

	private static int FONT_SIZE_MIN = 20; // 字體大小變動範圍

	private static int FONT_SIZE_MAX = 32; // 字體大小變動範圍

	private int charCount = 0;

	private static Font[] fontArray;

	private Random r = new Random();

	public static String GRAPHIC_JPEG = "JPEG";

	public static String GRAPHIC_PNG = "PNG";

	protected RandomGraphic(int charCount, Font[] fontArray) {
		this.charCount = charCount;
		this.fontArray = fontArray;
	}

	public static RandomGraphic createInstance(int charCount, int fontSizeMin, int fontSizeMax, int imageHeight, Font[] fontArray) throws Exception {
		if (charCount < 1 || charCount > MAX_CHARCOUNT) {
			throw new Exception("Invalid parameter charCount,charCount should between in 1 and 16");
		}
		// 設定字體大小
		if (fontSizeMin > 0 && fontSizeMin < 100)
			FONT_SIZE_MIN = fontSizeMin;
		if (fontSizeMax > 0 && fontSizeMax < 100)
			FONT_SIZE_MAX = fontSizeMax;
		if (FONT_SIZE_MAX <= FONT_SIZE_MIN)
			FONT_SIZE_MAX = FONT_SIZE_MIN + 2;

		// 設定圖檔高度
		if (imageHeight > 10 && imageHeight < 200)
			IMAGE_HEIGHT = imageHeight;

		return new RandomGraphic(charCount, fontArray);
	}

	public String drawNumber(String graphicFormat, OutputStream out) throws Exception {
		String charValue = "";
		charValue = randNumber();
		return draw(charValue, graphicFormat, out);
	}

	public String drawAlpha(String graphicFormat, OutputStream out) throws Exception {
		String charValue = "";
		charValue = randAlpha();
		return draw(charValue, graphicFormat, out);
	}

	protected String draw(String charValue, String graphicFormat, OutputStream out) throws Exception {

		// 計算圖形寬度 = 字數 * 字寬 + 兩邊寬
		int w = charCount * WORD_WIDTH + (BORDER_WIDTH * 2);

		// 設定繪圖記憶區塊
		BufferedImage bi = new BufferedImage(w, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);

		// 建立繪圖板
		Graphics2D g = bi.createGraphics();

		// 畫背景
		g.setBackground(Color.WHITE);

		Color c1 = new Color(randomInt(220, 250), randomInt(220, 250), randomInt(220, 250));

		c1 = new Color(220, 250, 250);

		g.setColor(c1);
		g.fillRect(0, 0, w, IMAGE_HEIGHT);

		/*
		 * g.setColor(new
		 * Color(randomInt(220,250),randomInt(220,250),randomInt(220,250)));
		 * g.fillRect(0,0,w,IMAGE_HEIGHT);
		 * 
		 * g.setColor(new
		 * Color(randomInt(220,250),randomInt(200,220),randomInt(200,220)));
		 * g.fillRect(0,0,w,IMAGE_HEIGHT/2);
		 */

		// 字型檔實體檔名變數
		String font_file = ".";

		// 產生第一個字的顏色索引，為避免連續亂數產生顏色不易識別，顏色採用照表列遞延索引產生
		// int color_index = randomInt(0,CHAR_COLOR.length);
		int color_index = 0;
		for (int i = 0; i < charCount; i++) {
			// float font_size =
			// (float)(randomInt(FONT_SIZE_MIN,FONT_SIZE_MAX));
			float font_size = (float) 28f;

			try {
				Font myFont = fontArray[randomInt(0, fontArray.length)];
				// myFont = myFont.deriveFont(Font.BOLD+Font.ITALIC,font_size);
				myFont = myFont.deriveFont(Font.BOLD, font_size);
				g.setFont(myFont);
			}
			catch (Exception ex) {
				throw new Exception("錯誤!! 無法使用傳入的字型陣列");
			}

			String c = charValue.substring(i, i + 1);

			// 水平變動, 因為字型變動，字體已經使圖形移動很大，所以不再多做變化
			int xpos = BORDER_WIDTH + i * WORD_WIDTH;

			// 垂直變動
			int ypos = randomInt((int) font_size / 2 + 6, IMAGE_HEIGHT - 6) + 3;

			Color font_color = CHAR_COLOR[color_index];
			// color_index = (color_index+1)%CHAR_COLOR.length;
			color_index = (color_index) % CHAR_COLOR.length;
			g.setColor(font_color);
			g.drawString(charValue, xpos, ypos);
			break;
		}

		// 水平遮罩線
		color_index = randomInt(0, CHAR_COLOR.length);
		for (int i = 0; i < IMAGE_HEIGHT;) {
			i += randomInt(5, 20); // 水平線間距
			g.setColor(CHAR_COLOR[(color_index++) % CHAR_COLOR.length]);
			// g.drawLine(0,i,w,i);
		}
		// 垂直遮罩線
		Color lightsalmon = new Color(255, 160, 122);

		for (int i = 0; i < w;) // 垂直線間距
		{
			i += randomInt(10, 40);
			// g.setColor(new
			// Color(randomInt(0,255),randomInt(0,255),randomInt(0,255)));
			g.setColor(lightsalmon);
			g.drawLine(i, 0, randomInt(0, w), IMAGE_HEIGHT);
		}
		for (int i = 0; i < w;) // 垂直線間距
		{
			i += randomInt(10, 40);
			// g.setColor(new
			// Color(randomInt(0,255),randomInt(0,255),randomInt(0,255)));
			g.setColor(lightsalmon);
			g.drawLine(i, 0, randomInt(0, w), IMAGE_HEIGHT);
		}
		g.dispose();
		// bi.flush();
		ImageIO.write(bi, graphicFormat, out);

		// 回傳本次產生的亂數值
		return charValue;
	}

	protected String randNumber() {
		String charValue = "";
		for (int i = 0; i < charCount; i++) {
			charValue += String.valueOf(randomInt(0, 10));
		}
		return charValue;
	}

	private String randAlpha() {
		String charValue = "";
		for (int i = 0; i < charCount; i++) {
			char c = (char) (randomInt(0, 26) + 'a');
			charValue += String.valueOf(c);
		}
		return charValue;
	}

	protected int randomInt(int from, int to) {
		return from + r.nextInt(to - from);
	}
}