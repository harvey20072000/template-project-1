package com.temp.api.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserReqBody extends ReqBody {
	
	private static final long serialVersionUID = 1L;

}
