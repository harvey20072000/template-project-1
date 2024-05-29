package com.temp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.temp.api.request.UserReqBody;
import com.temp.jpa.UserJpaRepository;
import com.temp.jpa.entity.UserEntity;

@Service
public class UserService {
	
	@Autowired
	private UserJpaRepository userRepository;
	
	public List<UserEntity> findAllUser() {
		List<UserEntity> resultList = new ArrayList<UserEntity>();
		for(UserEntity user : userRepository.findAll()) {
			resultList.add(user);
		}
		return resultList;
	}
	
	public List<UserEntity> findByReq(UserReqBody req) {
		Pageable pageable = PageRequest.of(req.getReqPage().getPage(), req.getReqPage().getRows(), Sort.by("userKey"));
		return userRepository.findByRoleKeyPaging(pageable);
		
	}

}
