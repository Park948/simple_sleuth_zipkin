package com.example.demo;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import brave.http.HttpServerHandler;
import brave.propagation.CurrentTraceContext;

//@Configuration
//public class FilterConfiguration implements WebMvcConfigurer {
//	
//	@Bean
//	public FilterRegistrationBean getFilterRegistrationBean() {
//		FilterRegistrationBean registrationBean = new FilterRegistrationBean(new TestFilter(new CurrentTraceContext();, new HttpServerHandler(null, null)));
//		registrationBean.setOrder(Integer.MIN_VALUE);
//		//registrationBean.addUrlPatterns("/*");
//		registrationBean.setUrlPatterns(Arrays.asList("/*"));
//		return registrationBean;
//	}
//}