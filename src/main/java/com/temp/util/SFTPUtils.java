/**
 * @Description : SFTP工具
 * @ClassName : SFTPUtils.java
 * @Copyright : Copyright (c) 2024 Harvey.Liu All Rights Reserved.
 */
package com.temp.util;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SFTPUtils {
	private static String SFTP_CHANNEL = "sftp";
	/**
	 * 連接sftp伺服器
	 * 
	 * @param host ip
	 * @param port port
	 * @param username uername
	 * @param pwd 密碼
	 * @param workingDir 遠端路徑
	 * @return ChannelSftp
	 * @throws JSchException, SftpException
	 */
	public static ChannelSftp connect(String host, int port, String username, String pwd, String workingDir) throws JSchException, SftpException{
		ChannelSftp sftp = null;
		JSch jsch = new JSch();
		jsch.getSession(username, host, port);
		Session sshSession = jsch.getSession(username, host, port);
		log.debug("Session created.");
		sshSession.setPassword(pwd);
		Properties sshConfig = new Properties();
		sshConfig.put("StrictHostKeyChecking", "no");
		sshSession.setConfig(sshConfig);
		log.debug("Session begin connect....");
		sshSession.connect();
		log.debug("Session connected.");
		Channel channel = sshSession.openChannel(SFTPUtils.SFTP_CHANNEL);
		channel.connect();
		sftp = (ChannelSftp) channel;
		log.debug("Connected to " + host + ".");
		if(!StringUtils.equals("/", workingDir)) {
			sftp.cd(workingDir);
		}
		
		return sftp;
	}
	
	/**
	 * SFTP斷線
	 * 
	 * @return
	 * @throws JSchException 
	 */
	public static void disconnect(ChannelSftp sftp) throws JSchException{
		
		if(sftp != null){
			if(sftp.getSession() != null) {
				sftp.getSession().disconnect();
			}
			sftp.exit();
		}
	}

}