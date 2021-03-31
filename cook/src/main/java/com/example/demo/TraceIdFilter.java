package com.example.demo;

import java.util.Map;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import lombok.var;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

//@Slf4j
//@Component
//public class TraceIdFilter implements WebFilter {
//
//    @SuppressWarnings("deprecation")
//	@Override
//    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//        Map<String, String> headers = exchange.getRequest().getHeaders().toSingleValueMap();
//        
//        System.out.println("Filter start");
//
//        return chain.filter(exchange)
//                .subscriberContext(context -> {
//                    var traceId = "";
//                    if (headers.containsKey("X-B3-TRACEID")) {
//                        traceId = headers.get("X-B3-TRACEID");
//                        MDC.put("X-B3-TraceId", traceId);
//                        System.out.println(traceId);
//                    } else if (!exchange.getRequest().getURI().getPath().contains("/actuator")) {
//                        log.warn("TRACE_ID not present in header: {}", exchange.getRequest().getURI());
//                    }
//
//                    // simple hack to provide the context with the exchange, so the whole chain can get the same trace id
//                    Context contextTmp = context.put("X-B3-TraceId", traceId);
//                    exchange.getAttributes().put("X-B3-TraceId", traceId);
//
//                    return contextTmp;
//                });
//    }
//}