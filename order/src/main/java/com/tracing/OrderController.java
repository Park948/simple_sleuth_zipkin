package com.tracing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@RequestMapping("/order")
	public String takeOrder() {
		log.info("주문 시작");
		String result = orderService.takeOrder();
		log.info("모든 과정 끝");
		return result;
	}
}
