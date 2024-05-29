package com.temp.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class ViewController {
	
	@GetMapping("index")
	public String index() {
		return "index";
	}
	
	@GetMapping("login")
	public String login() {
		return "login_soft";
	}
	
}
