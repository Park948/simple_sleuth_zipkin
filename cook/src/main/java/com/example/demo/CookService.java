package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CookService {
	
	private int amounts = 3;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Bean RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	public String CookService() throws Exception{
		String result;
		if(amounts == 0) {
			log.info("재료 없음");
			amounts = 3;
			result = restTemplate.getForObject("http://localhost:8084/errand", String.class);
		} else {
			log.info("요리 완료");
			amounts--;
			result = restTemplate.getForObject("http://localhost:8083/delivery", String.class);
		}
		
		return result;
	}
	
// 에러
//	public String CookService() throws Exception{
//		String result;
//
//		if(amounts == 0) {
//			log.info("오류 발생");
//			throw new Exception();
//		}
//		log.info("요리 완료");
//		amounts--;
//		result = restTemplate.getForObject("http://localhost:8083/delivery", String.class);
//		
//		return result;
//	}
}
