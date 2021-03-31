package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DeliveryService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Bean RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	public String DeliveryService() {
		log.info("배달 완료");
		return "배달 완료";
	}
	
}
