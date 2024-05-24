/**
 * @Description : FTP工具集
 * @ClassName : FTPUtils.java
 * @Copyright : Copyright (c) 2024 Harvey.Liu All Rights Reserved.
 */
package com.temp.util;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class FTPUtils {

	/**
	 * FTP連線
	 * 
	 * @param server FTP IP
	 * @param port 
	 * @param user 登入帳號
	 * @param password 登入密碼
	 * @param workingDir 主機資料位置
	 * @return
	 * @throws SocketException
	 * @throws IOException
	 */
	public static FTPClient connect(String server, int port, String user, String password, String workingDir) throws SocketException, IOException  {
		
		FTPClient ftpClient = new FTPClient();
		
		FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
		
		ftpClient.configure(config);
		
		ftpClient.connect(server, port);
		
		log.debug("(1) FTP connect return status : {}", ftpClient.getReplyString());
		
		ftpClient.login(user, password);
		
		log.debug("(2) FTP connect return status : {}", ftpClient.getReplyString());
		
		if (StringUtils.isNotBlank(workingDir)) {
			ftpClient.changeWorkingDirectory(workingDir);
		}
		
		log.debug("(3) FTP connect return status : {}", ftpClient.getReplyString());
		
		log.debug("(4) FTP work directory : {}", ftpClient.printWorkingDirectory());

		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		
		return ftpClient;
	}
	
	/**
	 * FTP斷線
	 * 
	 * @param ftpClient
	 * @throws IOException
	 */
	public static void disconnect(FTPClient ftpClient) throws IOException {
		
		if (ftpClient != null) {
			
			ftpClient.logout();
			
			log.debug("(1) FTP disconnect return status : {}", ftpClient.getReplyString());
			
			ftpClient.disconnect();
			
			log.debug("(2) FTP disconnect return status : {}", ftpClient.getReplyString());
		}
	}
	
	/**
	 * 移除目標路徑
	 * 
	 * @throws IOException
	 */
	public static void removeDir(FTPClient ftpClient, String dir) throws IOException {

		ftpClient.removeDirectory(dir);
		
		log.debug("(1) FTP removeDir return status : {}", ftpClient.getReplyString());
	}
}
