package com.temp.service;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.temp.api.request.LoginReqBody;
import com.temp.api.response.LoginResBody;
import com.temp.core.BaseMsgCode;
import com.temp.exception.ApiException;
import com.temp.exception.DBException;
import com.temp.jpa.UserRepository;
import com.temp.jpa.entity.UserEntity;

@Service
public class LoginService {
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * 登入
	 * 
	 * @param reqBody
	 * @return 暫時的apiToken，先用String測試
	 * @throws ApiException
	 */
	public LoginResBody login(LoginReqBody reqBody) throws DBException {
		String userName = reqBody.getUserName();
		String password = reqBody.getPassword();
		String encryptedPasssword = Base64.getEncoder().encodeToString(password.getBytes());
		List<UserEntity> userlist = userRepository.getJpaRepository().findByUserNameAndPassword(userName, password);
		if (userlist.isEmpty()) {
			throw new DBException(BaseMsgCode.LOGIN__ERROR__USERNAME_PASSWORD__NOT_FOUND);
		} else {
			LoginResBody resBody = new LoginResBody();
			resBody.setMsg(BaseMsgCode.LOGIN__SUCCESS.getMemo());
			resBody.setToken("token");
			return resBody;
		}
	}
	
}
