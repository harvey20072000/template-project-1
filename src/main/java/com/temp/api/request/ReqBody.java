package com.temp.api.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReqBody implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Data
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class ReqPage {
		private int page;
		private int rows;
	}
	
	private ReqPage reqPage;
	private String queryDatetime;
	
	/** 檔案名稱 */
	private String fileName;
	
	/** 檔案內容 */
	private byte[] fileContent;

}
