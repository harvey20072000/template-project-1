package com.temp.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResBody extends ResBody {
	
	private static final long serialVersionUID = 1L;
	
	private String msg;
	private String token;

}
