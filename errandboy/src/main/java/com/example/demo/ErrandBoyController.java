package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ErrandBoyController {
	
	@Autowired
	ErrandBoyService ebs;
	
	@RequestMapping("/errand")
	public String errand() {
		
		log.info("심부름 시작");
		return ebs.errandBoyService();
	}

}
