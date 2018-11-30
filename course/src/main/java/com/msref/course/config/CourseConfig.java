package com.msref.course.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CourseConfig {
	
	@Bean
	@LoadBalanced //This annotation is used to enable ribbon client side load balancer in rest template. Without this you are not able to call an endpoint with eureka service name
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
