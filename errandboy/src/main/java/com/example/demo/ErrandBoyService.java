package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ErrandBoyService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Bean RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	public String errandBoyService() {
		log.info("심부름 완료");
		return "품절 되었습니다. 다시 주문해주세요";
	}
}
