package com.tracing;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class OrderService {
	@Autowired
	private RestTemplate restTemplate;
	
	@Bean RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
//	public String takeOrder() {
//		log.info("주문 완료");
//		String result = "";
//		result = restTemplate.getForObject("http://localhost:8082/cook", String.class);
//		return result;
//	}
	
	public String takeOrder() {
		log.info("주문 완료");
		String result = "";
		Mono<String> wcResult;
		try {
//			result = restTemplate.getForObject("http://localhost:8082/cook", String.class);
			WebClient wc = WebClient.builder()
							.baseUrl("http://localhost:8082")
							.build();
			wcResult = wc.get()
					.uri("/cook")
					.header("X-B3-TRACEID", MDC.get("X-B3-TraceId"))
					.retrieve()
					.bodyToMono(String.class);
		} catch (Exception e) {
			log.debug("품절");
			return "품절";
		}
//		return result;
		return wcResult.block();
	}
	

}
