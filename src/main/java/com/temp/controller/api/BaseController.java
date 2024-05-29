package com.temp.controller.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.temp.api.request.ReqBody;
import com.temp.api.response.ResBody;
import com.temp.exception.ApiException;
import com.temp.exception.DBException;
import com.temp.exception.ValidateException;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class BaseController<S extends ReqBody, R extends ResBody> {

	private S reqBody;
	
	private R resBody;
	
	@Autowired
	private HttpServletResponse response;

	/**
	 * 檔案上傳
	 * @param file
	 * @param reqData
	 * @throws DBException
	 * @throws ApiException
	 * @return
	 */
	public ResponseEntity<R> doFileUpload(@RequestPart("reqBody") S reqBody, @RequestPart("file") MultipartFile file) throws DBException, ApiException {	
		return null;
	}
	
	/**
	 * 檔案下行回應下行
	 * @param resBody
	 * @return
	 * @throws DBException
	 * @throws ApiException
	 * @throws IOException 
	 */
	public void APIFileDownload(R resBody) throws DBException, ApiException {
		
		String fileName = resBody.getFileName();
		byte[] fileContent = resBody.getFileContent();
		
		// file download contentType
		this.response.setContentType("application/x-download");
		
		// file content length
		this.response.setContentLength(fileContent.length);
		
		// 為避免HTTPS無法下載檔案
		this.response.setHeader("Expires", "0");
		this.response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		this.response.setHeader("Pragma", "public");
		
		// file name in CONTENT_DISPOSITION
		StringBuffer contentDisposition = new StringBuffer();
		contentDisposition.append("attachment;");
		contentDisposition.append("filename=\"");
		contentDisposition.append(fileName);
		contentDisposition.append("\"");
		
		ServletOutputStream out = null;
		try {
			
	        this.response.setHeader(HttpHeaders.CONTENT_DISPOSITION, new String(contentDisposition.toString().getBytes(System.getProperty("file.encoding")), "iso8859_1"));
	        
	        out = this.response.getOutputStream();        
			out.write(fileContent);
		
		} catch (UnsupportedEncodingException e) {
			log.error("contentDisposition getBytes(UTF-8) Failure", e);
			
		} catch (IOException e) {
			log.error("Close ServletOutputStream Failure", e);
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				log.error("Close ServletOutputStream Failure", e);
			}
		}
	}
	

	/**
	 * 檔案下載-業務邏輯實作
	 * @return
	 */
	protected Resource getDownloadFile(S reqBody) {
		return null;
	}

	/**
	 * 檔案上傳-業務邏輯實作
	 * @param file
	 * @param reqBody
	 * @return R
	 */
	protected R processFileUpload(MultipartFile file, S reqBody) {
		return null;
	}
	
	// ========== Base Methods ==========
	/**
	 * 畫面初始化
	 * @param reqBody
	 * @return
	 * @throws DBException
	 * @throws ValidateException
	 * @throws ApiException
	 */
	public ResponseEntity<R> doInitial(@RequestBody S reqBody) throws DBException, ValidateException, ApiException {
		return null;
	}
	
	/**
	 * 查詢
	 * @param reqBody
	 * @return
	 * @throws DBException
	 * @throws ValidateException
	 * @throws ApiException
	 */
	public ResponseEntity<R> doQuery(@RequestBody S reqBody) throws DBException, ValidateException, ApiException {
		return null;
	}
	
	/**
	 * 查詢明細
	 * @param reqBody
	 * @return
	 * @throws DBException
	 * @throws ValidateException
	 * @throws ApiException
	 */
	public ResponseEntity<R> doQueryDetail(@RequestBody S reqBody) throws DBException, ValidateException, ApiException {
		return null;
	}
	
	/**
	 * 設定
	 * @return
	 * @throws DBException
	 * @throws ValidateException
	 * @throws ApiException
	 * @throws Exception 
	 */
	public ResponseEntity<R> doSetting(@RequestBody S reqBody) throws DBException, ValidateException, ApiException {
		return null;
	}
	
	/**
	 * 將 resBody 轉換 JSON
	 * @param resBody
	 * @return
	 */
	@SuppressWarnings("deprecation")
	protected ResponseEntity<R> APIResponse(R resBody) {
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
				.body(resBody);
	}
	
}
