package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DeliveryController {
	
	@Autowired
	DeliveryService ds;
	
	@RequestMapping("/delivery")
	public String delivery() {
		log.info("배달 시작");
		return ds.DeliveryService();
	}
}
