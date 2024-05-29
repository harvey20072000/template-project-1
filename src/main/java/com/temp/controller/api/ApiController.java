package com.temp.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.temp.api.request.LoginReqBody;
import com.temp.api.response.LoginResBody;
import com.temp.exception.DBException;
import com.temp.service.LoginService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("api")
@Log4j2
public class ApiController extends BaseController<LoginReqBody, LoginResBody>{
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping("login")
	public ResponseEntity<LoginResBody> login(@RequestBody LoginReqBody reqBody) throws DBException {
		LoginResBody resbody = loginService.login(reqBody);
		return super.APIResponse(resbody);
	}
	
}
