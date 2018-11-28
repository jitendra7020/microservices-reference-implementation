package com.msref.course.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.msref.course.model.ErrorResponse;

@Configuration
public class CourseConfig {
	
	@Bean
	public ErrorResponse errorResponse() {
		return new ErrorResponse();
	}
	
	@Bean
	@LoadBalanced //This annotation is used to enable ribbon client side load balancer in rest template
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
