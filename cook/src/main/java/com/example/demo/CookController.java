package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CookController {
	
	@Autowired
	CookService cs;
	
	@RequestMapping("/cook")
	public String cook() throws Exception{
		
		log.info("요리 시작");
		return cs.CookService();
	}
}
