package com.temp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.temp.SpringAwareHelper;
import com.temp.jpa.UserRepository;
import com.temp.jpa.entity.UserEntity;
import com.temp.service.AnotherServciceImpl;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class MainController {
	
	@GetMapping("greeting")
	@ResponseBody
	public String greeting(@RequestParam(defaultValue = "Hello World !") String msg) {
		String returnMsg = String.format("I'm a running app. I get your message 「%s」.", msg);
		return returnMsg;
	}
	
	@Autowired
    UserRepository userRepository;
    
	@ResponseBody
	@PostMapping("/addUser")
	public String addUser() {
	    UserEntity user = new UserEntity();
	    user.setUserName("使用者");
	    user.setJoinDate(new Date());
	    user.setEmail("test@gmail.com");
	    userRepository.save(user);
	    return "AddUserSuccess";
	}
	
	@ResponseBody
	@GetMapping("/addRandomUsers")
	public String addRandomUsers() {
		for(int i=1;i<10;i++) {
			UserEntity user = new UserEntity();
		    user.setUserName("使用者" + i);
		    user.setJoinDate(new Date());
		    user.setEmail("test" + i + "@gmail.com");
		    userRepository.save(user);
		}
		return "AddUsersSuccess";
	}
	
	@ResponseBody
	@GetMapping("/checkAllUsers")
	public List checkAllUsers() {
		log.info("checkAllUsers called !!");
		return List.of(userRepository.findAll());
	}
	
	@ResponseBody
	@GetMapping("/updateAllUsers")
	public List updateAllUsers() {
		List<UserEntity> list = new ArrayList<UserEntity>();
	    boolean useLetters = false;
	    boolean useNumbers = true;
		for(UserEntity entity : userRepository.findAll()) {
			entity.setTel("$1-$2-$3"
					.replace("$1", RandomStringUtils.random(4, useLetters, useNumbers))
					.replace("$2", RandomStringUtils.random(3, useLetters, useNumbers))
					.replace("$3", RandomStringUtils.random(3, useLetters, useNumbers)));
			userRepository.save(entity);
			list.add(entity);
		}
		
		return list;
	}
	
	@ResponseBody
	@GetMapping("/getBean")
	public String getBean() {
		return SpringAwareHelper.getBean(AnotherServciceImpl.class).aMehtod();
	}
}
