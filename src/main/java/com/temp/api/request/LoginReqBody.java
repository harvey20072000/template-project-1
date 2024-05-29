package com.temp.api.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginReqBody extends ReqBody {
	
	private static final long serialVersionUID = 1L;
	
	private String userName;
	
	private String password;

}
