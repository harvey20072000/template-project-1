package com.temp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.temp.jpa.entity.UserEntity;

@Service
public interface IUserService {

	/**
	 * @return
	 */
	List<UserEntity> findAllUser();

}
